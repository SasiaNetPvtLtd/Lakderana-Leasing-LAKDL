/*
	*	Inesh Gunasekara
	*	2018-01-11
	*	JB02012018-02276 NetAsset  system documents upload facility
*/
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
import oracle.sql.*; 

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;

import java.awt.image.*;

import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

public class LAKDL_AF_MK_Document_Upload_New_Save extends javax.servlet.http.HttpServlet {
	Connection conn;
	Statement stmt,stmt2;
	CallableStatement callstmt1 =null;
	ResultSet rs,rs1,rs2;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException {
		try{
			
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
			conn.setAutoCommit(false);
			
			boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
			if (!isMultipartContent) {					
				return;
			}
			
			conn.setAutoCommit(false);            
            stmt = conn.createStatement();
			
			String m_uploadPath = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\DOCUMENT_UPLOAD";
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			int allowRowCount	= 0;
			int maxSize = 1024*1024*1;
			
			rs = stmt.executeQuery ("SELECT UPLOADPATH FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_PATH WHERE ACTIVE_STATUS ='Y'");
			if(rs.next()){
				m_uploadPath = rs.getString(1);				
			}
			//out.println("upload path CLZ"+m_uploadPath);
			//out.println("upload path db"+m_uploadPath);
			String[] allowedExtention =new String[5];
			rs = stmt.executeQuery ("SELECT COUNT(TYPE_NAME)*2 AV_ROWS FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_ALLOW_TYPE WHERE ACTIVE_STATUS ='Y'");
			while(rs.next()){
				allowRowCount = rs.getInt("AV_ROWS");
			}
			callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_DOC_UPLOAD_LOG_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;");
			
			if(allowRowCount>0){
				allowedExtention = new String[allowRowCount];
				String m_allowedTypesQ = " SELECT UPPER(TYPE_NAME) DOC_EXT "+
									 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_ALLOW_TYPE "+
									 " WHERE ACTIVE_STATUS ='Y' "+
									 " UNION ALL "+
									 " SELECT LOWER(TYPE_NAME) DOC_EXT "+
									 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_ALLOW_TYPE "+
									 " WHERE ACTIVE_STATUS ='Y' ";
 				rs = stmt.executeQuery (m_allowedTypesQ);
				int alRow =0;
				while(rs.next()){
					allowedExtention[alRow++] = rs.getString("DOC_EXT");
				}
			}
			String m_screenName = "";
			String m_FinanceNo  = "";
			String m_noOfRec    = "";
			FileItem fileItem ;
			
			List fields = upload.parseRequest(req);
			Iterator it = fields.iterator();
			if (!it.hasNext()) {
				return;
			}
			while (it.hasNext()) {
				fileItem = (FileItem)it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					if(fileItem.getFieldName().equals("SCREEN_NAME")){
						m_screenName = fileItem.getString();
					}
					if(fileItem.getFieldName().equals("TXT_FINANCE_NO")){
						m_FinanceNo = fileItem.getString();
					}
					if(fileItem.getFieldName().equals("HID_NUM_OF_REC")){
						m_noOfRec = fileItem.getString();
					}
				}
			}
			it=null;
			if(m_noOfRec==null || (m_noOfRec!=null && m_noOfRec.equals(""))){ //if there are no records
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('No Records to Save!');");
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_Upload_New';");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				return;
			}
			int numOfRows = Integer.parseInt(m_noOfRec);
			
			String[] m_HID_DOC_TYPE_ID 		=  new String[numOfRows+1];
			String[] m_HID_UPLOAD_ENABLE 	=  new String[numOfRows+1];
			String[] m_HID_UPLOAD_STATUS 	=  new String[numOfRows+1];
			String[] m_HID_DOC_NUM 			=  new String[numOfRows+1];
			String[] m_FILE_DOCUMENT		=  new String[numOfRows+1];
			int[]    m_FILE_LENGTH			=  new int[numOfRows+1];
			String m_field_name 			= "";
			String m_second_part_string 	= "";
			String m_fileName				= "";
			int m_second_part_int 			= 0;
			String mExceptionMsg 			= "";
			Iterator it2 = fields.iterator();
			FileItem item ;
			boolean isFilesValid = true;
			while(it2.hasNext()){
				item = (FileItem)it2.next();
				m_field_name = item.getFieldName();
				m_second_part_string = m_field_name.substring(m_field_name.lastIndexOf("_") + 1, m_field_name.length());
				
				if (item.isFormField()) {
					if (m_field_name.equals("HID_DOC_TYPE_ID_" + m_second_part_string)) {
						m_second_part_int = Integer.parseInt(m_second_part_string);
                        m_HID_DOC_TYPE_ID[m_second_part_int] = item.getString();
					}
					if (m_field_name.equals("HID_UPLOAD_ENABLE_" + m_second_part_string)) {
						m_second_part_int = Integer.parseInt(m_second_part_string);
                        m_HID_UPLOAD_ENABLE[m_second_part_int] = item.getString();
					}
					if (m_field_name.equals("HID_UPLOAD_STATUS_" + m_second_part_string)) {
						m_second_part_int = Integer.parseInt(m_second_part_string);
                        m_HID_UPLOAD_STATUS[m_second_part_int] = item.getString();
					}
					if (m_field_name.equals("HID_DOC_NUM_" + m_second_part_string)) {
						m_second_part_int = Integer.parseInt(m_second_part_string);
                        m_HID_DOC_NUM[m_second_part_int] = item.getString();
					}
				}else{
					if (m_field_name.equals("FILE_DOCUMENT_" + m_second_part_string)) {						
						if (item.getName().length() > 0) {							
							m_second_part_int = Integer.parseInt(m_second_part_string);		
							m_FILE_LENGTH[m_second_part_int] =item.get().length;
							m_FILE_DOCUMENT[m_second_part_int] = item.getName().substring(item.getName().lastIndexOf('\\') + 1, item.getName().length());
						}
					}
				}
			}
			it2 = null;
			
			for(int i = 0; i< m_FILE_DOCUMENT.length; i++){
					
					if(m_HID_DOC_TYPE_ID[i]!= null){
						if(m_HID_UPLOAD_ENABLE[i]!=null && m_HID_UPLOAD_ENABLE[i].equals("Y")){
							if(m_HID_UPLOAD_STATUS[i]!=null && m_HID_UPLOAD_STATUS[i].equals("Y")){								
								if(isValidExtention(m_FILE_DOCUMENT[i],allowedExtention,out)){
									//isFilesValid = true;									
									if(!(m_FILE_LENGTH[i]!=0 && m_FILE_LENGTH[i]<maxSize)){
										mExceptionMsg += "Document "+m_FILE_DOCUMENT[i]+" size is greater than 1Mb\\n";
										isFilesValid = false;
									}
								}else{
									mExceptionMsg += "Document "+m_FILE_DOCUMENT[i]+" is not allowed \\n";
									isFilesValid = false;
								}
								/*out.println("m_HID_DOC_TYPE_ID "+m_HID_DOC_TYPE_ID[i]);
								out.println("  m_HID_UPLOAD_ENABLE "+m_HID_UPLOAD_ENABLE[i]);
								out.println("  m_HID_UPLOAD_STATUS "+m_HID_UPLOAD_STATUS[i]);
								out.println("  m_HID_DOC_NUM "+m_HID_DOC_NUM[i]);
								out.println("  m_FILE_LENGTH "+m_FILE_LENGTH[i]);
								out.println("  Name "+m_FILE_DOCUMENT[i]+"<br>");*/
							}
						}
					}
					if(m_FILE_LENGTH[i]>0){
						if(isValidExtention(m_FILE_DOCUMENT[i],allowedExtention,out)){
						
						}else{
							mExceptionMsg += "Document "+m_FILE_DOCUMENT[i]+" is not allowed \\n";
							isFilesValid = false;
						}
					}
					
			} 
			String m_finNumDir ="";
			File m_new_dir =null;
			String m_FinanceNoDirectry = LAKDL_AF_MK_Document_Floder_Name_Gen.getFolderName(m_FinanceNo);
			if(isFilesValid){
				m_new_dir = new File(m_uploadPath);
				if (!m_new_dir.exists()) { 					//check n create master folder
                    m_new_dir.mkdirs();
                }
				m_new_dir = null;	
				m_finNumDir = m_uploadPath + "\\" + m_FinanceNoDirectry ;
				m_new_dir = new File(m_finNumDir);
				if (!m_new_dir.exists()) { 					//create the finance no folder
                    m_new_dir.mkdirs();
                }
				
				Iterator itrFileUpload = fields.iterator();
				String m_file_save_path;
				String m_docName;
				String f_file_extension;
				File   f_file_save_path;   
				int    intSecPart=0;
				
				while (itrFileUpload.hasNext()) {
					item = (FileItem) itrFileUpload.next();
					m_field_name = item.getFieldName();
				
					m_second_part_string = m_field_name.substring(m_field_name.lastIndexOf("_") + 1, m_field_name.length());
				
					
				
					if (!item.isFormField()) {
						if (m_field_name.equals("FILE_DOCUMENT_" + m_second_part_string)) {
							intSecPart = Integer.parseInt(m_second_part_string);
							if (item.getName().length() > 0) {
								if(m_HID_UPLOAD_ENABLE[intSecPart]!=null && m_HID_UPLOAD_ENABLE[intSecPart].equals("Y")){
									if(m_HID_UPLOAD_STATUS[intSecPart]!=null && m_HID_UPLOAD_ENABLE[intSecPart].equals("Y")){										
										f_file_extension = item.getName().substring(item.getName().lastIndexOf(".") + 1, item.getName().length());
										
										m_file_save_path = m_finNumDir +"\\"+ m_HID_DOC_TYPE_ID[intSecPart];
										m_docName		 = m_HID_DOC_TYPE_ID[intSecPart]+"-"+m_HID_DOC_NUM[intSecPart]+"."+f_file_extension;
										m_new_dir = new File(m_file_save_path);
										if (!m_new_dir.exists()) { 					
						                    m_new_dir.mkdirs();
						                }
										m_file_save_path = m_finNumDir+"\\"+m_HID_DOC_TYPE_ID[intSecPart] +"\\"+m_docName;
										
										if( (m_FILE_LENGTH[intSecPart]/1024) > 300 &&//only compress images >300 kb
												(f_file_extension.equals("jpg") || f_file_extension.equals("gif") || f_file_extension.equals("JPG") || f_file_extension.equals("GIF") || f_file_extension.equals("JPEG") || f_file_extension.equals("jpeg") || f_file_extension.equals("png")  || f_file_extension.equals("PNG") ) ){
											f_file_save_path = new File(m_file_save_path);
											byte[] m_document = null;
											m_document  = item.get();
											
											InputStream inputStream = new ByteArrayInputStream(m_document);
											OutputStream os =new FileOutputStream(f_file_save_path);
											BufferedImage image = ImageIO.read(inputStream);
											
											int orgHeight = image.getHeight();
											int orgWidth  = image.getWidth();
											float quality = 0.4f;
											Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(f_file_extension);
											if (!writers.hasNext())
												throw new IllegalStateException("No writers found");
											ImageWriter writer = writers.next();
											ImageOutputStream ios = ImageIO.createImageOutputStream(os);
											writer.setOutput(ios);
											if(f_file_extension!=null && !f_file_extension.equals("gif") && (m_FILE_LENGTH[intSecPart]/1024) >350 ){
												ImageWriteParam param = writer.getDefaultWriteParam();				
												param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
												if(orgHeight >800 || orgWidth>800 )
													param.setCompressionQuality(quality);                              //only reduce quality width or height more than 800
												writer.write(null, new IIOImage(image, null, null), param);
											}
											writer.write(image);
											
											if(ios!=null)
											ios.close();
											if(writer!=null)
												writer.dispose();
											
											File writtenFile = new File(m_file_save_path);
											if(writtenFile.exists()){
												m_FILE_LENGTH[intSecPart] = (int)writtenFile.length();
											}
										}else{
											
											f_file_save_path = new File(m_file_save_path);
											item.write(f_file_save_path);
											f_file_save_path=null;
										}
										callstmt1.setString(1,m_FinanceNo);		//FINANCE_NO
										callstmt1.setString(2,"");//DOC_ID
										callstmt1.setString(3,m_HID_DOC_TYPE_ID[intSecPart]);//DOC_TYPE_ID
										callstmt1.setString(4,m_HID_DOC_NUM[intSecPart]);//DOC_NUM
										callstmt1.setString(5,m_docName);//DOCUMENT_NAME
										callstmt1.setString(6,f_file_extension);//FILE_TYPE
										callstmt1.setString(7,m_FILE_LENGTH[intSecPart]+"");//FILE_SIZE
										callstmt1.setString(8,m_screenName);//SCREEN
										callstmt1.setString(9,m_username);//USER
										callstmt1.execute();
										
									}	
								}
							}
						}
					}
				}				
				itrFileUpload = null;
				conn.commit();
				callstmt1.close();
 
			}else{
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('"+mExceptionMsg+"');");
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_Upload_New';");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				return;
			}
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('Information saved successfully');");
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_Upload_New';");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				return;
				 
		}catch(Exception e){
			try{conn.rollback();}catch(Exception es){};
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'>Error:"+e.toString()+"</body>");
			out.println("</html>");
			out.flush();
			out.close();
		}	
	} 

	private boolean isValidExtention(String fileName,String[] extentionList,ServletOutputStream out){		
		try{
			if(fileName!=null){
				fileName = fileName.toUpperCase();	
				String f_file_extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				if(f_file_extension!=null && f_file_extension.length() >0){
					for(int i =0;i<extentionList.length;i++){						
						if(extentionList[i]!=null && extentionList[i].equals(f_file_extension)){							
							return true;
						}
					}
				}else{
					return false;	
				}
			}else{
				return false;	
			}
			return false;
		}catch(Exception e){}
		return false;
	}
} 