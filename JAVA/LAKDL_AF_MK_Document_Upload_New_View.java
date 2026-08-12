/*
	*	DEVELOPED BY : INESH
	*	2018-01-17
	*	JB02012018-02276 NetAsset system documents upload facility
*/
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


public class LAKDL_AF_MK_Document_Upload_New_View extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a;
		Statement stmt=null,stmt2=null,stmt3=null;
		Statement statement2=null;
		Statement statement=null;
		CallableStatement callstmt =null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs_drill_new=null;
		
		ResultSet resultSet=null;	
		ResultSet resultSet2=null;	
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
			
			stmt=conn.createStatement();
			statement =conn.createStatement();
			statement2=conn.createStatement();	
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			String m_chksql=req.getParameter("chksql");
			
			String m_screenTitle  = "Document Upload";
			String m_uploadPath = "D:\\SasiaNet_Products\\NetAsset\\LAKDL\\DOCUMENT_UPLOAD";
			String m_FileDirecpath ="";
			String m_directoryPath ="";
			String m_downloadPath  ="";
			int imgWidth		   	= 600;
			int imgHeight			= 400;
			int orgImgWidth			= 0;
			int orgImgHeight		= 0;
			rs = stmt.executeQuery ("SELECT UPLOADPATH FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_PATH WHERE ACTIVE_STATUS ='V'");
			if(rs.next()){
				m_uploadPath = rs.getString(1);				
			}
			rs = stmt.executeQuery ("SELECT UPLOADPATH FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_PATH WHERE ACTIVE_STATUS ='U'");
			if(rs.next()){
				m_downloadPath = rs.getString(1);				
			}
			
			
			if(m_chksql.equals("view_document")){
				try{
					String m_docId =  req.getParameter("doc_id");		
					String m_FinanceNoDirectry ="";
					 rs = stmt.executeQuery ( " SELECT  "+
							 "   NVL(FINANCE_NO,'_') FINANCE_NO, "+
							 "   NVL(DOC_ID,'_') DOC_ID, "+
							 "   NVL(DOC_TYPE_ID,'_') DOC_TYPE_ID, "+
							 "   NVL("+m_schema_name+".AF_GET_DOCUMENT_TYPES_DESC(DOC_TYPE_ID),'_') TYPES_DESC, "+
							 "   NVL(DOC_NUM,0) DOC_NUM, "+
							 "   NVL(DOCUMENT_NAME,'_') DOCUMENT_NAME, "+
							 "   NVL(FILE_TYPE,'_') FILE_TYPE, "+
							 "   NVL(FILE_SIZE,0) FILE_SIZE, "+
							 "   NVL(ACTIVE_STATUS,'N') ACTIVE_STATUS, "+
							 "   NVL(IMG_TYPE,'N') IMG_TYPE, "+
							 "   TO_CHAR(NVL(MOD_DATE,ENT_DATE),'DD-MM-YYYY HH:MI:SS AM') ENT_DATE "+
							 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_LOG A "+
							 " WHERE DOC_ID = '"+m_docId+"' ");
					
					if(rs.next()){
							m_FinanceNoDirectry = LAKDL_AF_MK_Document_Floder_Name_Gen.getFolderName(rs.getString("FINANCE_NO"));
							
							m_directoryPath = m_uploadPath+"/"+m_FinanceNoDirectry+"/"+rs.getString("DOC_TYPE_ID")+"/"+rs.getString("DOCUMENT_NAME");
							
							
							if(rs.getString("IMG_TYPE")!=null && rs.getString("IMG_TYPE").equals("Y") ){
								try{
									m_FileDirecpath = m_downloadPath+"/"+m_FinanceNoDirectry+"/"+rs.getString("DOC_TYPE_ID")+"/"+rs.getString("DOCUMENT_NAME");
									File imageFile = new File(m_FileDirecpath);
									if(imageFile.exists()){
										BufferedImage bimg 	= ImageIO.read(imageFile);
										orgImgWidth			= bimg.getWidth();
										orgImgHeight		= bimg.getHeight();
										imgWidth			= (imgHeight *orgImgWidth)/orgImgHeight;
									}
								}catch(Exception e){
									//out.println("Error Occured "+e.getMessage());
									imgHeight = 400;
									imgWidth  = 600;
								}
								
								out.println("<HTML>"); 
								out.println("   <HEAD>"); 
								out.println("      <TITLE>"+m_screenTitle+"</TITLE>"); 
								out.println("   </HEAD>"); 
								out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
								out.println("<SCRIPT language=\"JavaScript\">");
								out.println(" 	function get_document(doc_no){ ");
								out.println("   m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_Upload_New_View?chksql=get_document&doc_id="+m_docId+"'; ");
								out.println(" 	window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
								out.println(" 	}");
								out.println("</SCRIPT> "); 
								out.println("   <BODY> ");
								out.println("       <TABLE BORDER=0 ALIGN=CENTER > "); 
								out.println("          <TR> "); 
								out.println("             <TD> <B>Finance No</B> </TD> "); 
								out.println("             <TD> : "+rs.getString("FINANCE_NO")+" </TD> "); 
								out.println("          </TR> "); 
								
								out.println("          <TR> "); 
								out.println("             <TD> <B>File Size (KB)</B> </TD> "); 								
								out.println("             <TD onClick=\"get_document();\" STYLE='{cursor:hand;}' > : <u>"+nf.format(rs.getDouble("FILE_SIZE"))+"</u> </TD> ");
								out.println("          </TR> "); 
								
								out.println("          <TR> "); 
								out.println("             <TD> <B>File Name </B> </TD> "); 
								out.println("             <TD> : "+rs.getString("DOCUMENT_NAME")+" </TD> "); 
								out.println("          </TR> "); 
								
								out.println("          <TR> "); 
								out.println("             <TD> <B>Uploaded Time </B> </TD> "); 
								out.println("             <TD> : "+rs.getString("ENT_DATE")+" </TD> "); 
								out.println("          </TR> "); 
								
								out.println("       </TABLE> "); 
								
								out.println("       <BR> "); 
								
								out.println("       <TABLE BORDER=0 ALIGN=CENTER > "); 
								out.println("          <TR> "); 
								out.println("             <TD> &nbsp; </TD> ");
								out.println("             <TD>"); 
								out.println("	           		<img src=\""+m_directoryPath+"\" height=\""+imgHeight+"\" width=\""+imgWidth+"\" > "); 
								out.println("             </TD>"); 
								out.println("          </TR> "); 
								out.println("       </TABLE> "); 
								out.println("   </BODY>"); 
								
								out.println("</HTML>"); 
								out.flush();
								return;
							}else{
								//if the document is not declared as image it will prompt the download 
								res.sendRedirect(m_class_url+"/"+m_fschema_name+"AF_MK_Document_Upload_New_View?chksql=get_document&doc_id="+m_docId);
								return;
							}
								
					}else{
						out.println("<HTML>"); 
						out.println("   <HEAD>"); 
						out.println("      <TITLE>"+m_screenTitle+"</TITLE>"); 
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
						return;
					}
				}catch(Exception e){
					out.println("Error occured "+e.getMessage());
				}
		}else if(m_chksql.equals("get_document")){
			String m_docId =  req.getParameter("doc_id");	
			String m_FinanceNoDirectry = "";
			 rs = stmt.executeQuery ( " SELECT  "+
							 "   NVL(FINANCE_NO,'_') FINANCE_NO, "+
							 "   NVL(DOC_ID,'_') DOC_ID, "+
							 "   NVL(DOC_TYPE_ID,'_') DOC_TYPE_ID, "+
							 "   NVL("+m_schema_name+".AF_GET_DOCUMENT_TYPES_DESC(DOC_TYPE_ID),'_') TYPES_DESC, "+
							 "   NVL(DOC_NUM,0) DOC_NUM, "+
							 "   NVL(DOCUMENT_NAME,'_') DOCUMENT_NAME, "+
							 "   NVL(FILE_TYPE,'_') FILE_TYPE, "+
							 "   NVL(FILE_SIZE,0) FILE_SIZE, "+
							 "   NVL(ACTIVE_STATUS,'N') ACTIVE_STATUS, "+
							 "   NVL(IMG_TYPE,'N') IMG_TYPE, "+
							 "   TO_CHAR(NVL(MOD_DATE,ENT_DATE),'DD-MM-YYYY HH:MI:SS AM') ENT_DATE "+
							 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_LOG A "+
							 " WHERE DOC_ID = '"+m_docId+"' ");
			if(rs.next()){
				m_FinanceNoDirectry = LAKDL_AF_MK_Document_Floder_Name_Gen.getFolderName(rs.getString("FINANCE_NO"));
				m_directoryPath = m_downloadPath+"/"+m_FinanceNoDirectry+"/"+rs.getString("DOC_TYPE_ID")+"/"+rs.getString("DOCUMENT_NAME");				
				
				res.setContentType("APPLICATION/OCTET-STREAM");
				res.setHeader("Content-Disposition","attachment; filename=\"" + rs.getString("DOCUMENT_NAME") + "\"");  
				
				File downloadFile = new File(m_directoryPath);
				FileInputStream fileInputStream = new FileInputStream(downloadFile);  
				OutputStream os = res.getOutputStream();
				int i;  
				int bufferSize = 1024*10*10;
				byte[] buffer = new byte[bufferSize];
				while( (i = fileInputStream.read(buffer))>0){
					os.write(buffer, 0, bufferSize);
				}
				
				
				fileInputStream.close();   
			}	
		}else if(m_chksql.equals("viewAllDocuments")){
				
			String m_finance_no ="";
			m_finance_no=req.getParameter("finance_no").trim();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Marketing - Document Upload</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function get_document(doc_no){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=get_document&doc_no='+doc_no;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function get_document(doc_no,type){ "); 
				
				out.println(" 	if(type =='OLD'){");
				out.println(" 		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=get_document&doc_no='+doc_no;"); 
				out.println(" 		window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println(" 	}else if(type =='ZIP'){");				
				out.println(" 		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=get_document_ZIP&doc_no='+doc_no;"); 
				out.println(" 		window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 				
				out.println(" 	}else{");
				out.println(" 		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=get_document_NEW&doc_no='+doc_no;"); 
				out.println(" 		window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 				
				out.println(" 	}");
				out.println("}");
				
				
				out.println(" function view_document_new(docId){ ");
				out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_Upload_New_View?chksql=view_document&doc_id='+docId; ");
				out.println("	window.open(m_url,'slab','width=600,height=600,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');	");
				out.println(" } ");
				
				out.println("function delete_document(doc_no) {");
				out.println("	if(confirm('Are you sure, you wnat to delete?')){");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=delete_document&doc_no=\"+doc_no;"); 
				//out.println("		window.open(m_url)");
				//out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			alert('Document deleted successfully.');"); 
				out.println("			window.location.href= window.location.href;"); 
				
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when deleting...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				String Sql_data="";
				
				
				Sql_data=
					
					" SELECT DOCUMENT_NAME, DOCUMENT_NO, FILE_NAME, ENT_DATE, ENT_USER, DOC_LENGTH, FILE_STORAGE "+
					" FROM( "+
					" 	SELECT	A.DOCUMENT_NAME, A.DOCUMENT_NO, A.FILE_NAME, TO_CHAR(A.ENT_DATE,'YYYY-MM-DD HH:MI:SS') ENT_DATE, A.ENT_USER,  length(A.DOCUMENT) DOC_LENGTH,'OLD' FILE_STORAGE "+
					" 	FROM	AF_MK_DOCUMENT_UPLOAD A "+
					" 	WHERE	A.ACTIVE_STATUS = 'Y' AND A.FINANCE_NO = '"+m_finance_no+"'"+
					" UNION ALL "+
					" 	SELECT	A.DOCUMENT_NAME, A.DOCUMENT_NO, A.FILE_NAME, TO_CHAR(A.ENT_DATE,'YYYY-MM-DD HH:MI:SS') ENT_DATE, A.ENT_USER,  length(A.DOCUMENT) DOC_LENGTH, FILE_TYPE FILE_STORAGE "+
					" 	FROM	AF_MK_DOCUMENT_UPLOAD_NEW A "+
					" 	WHERE	A.ACTIVE_STATUS = 'Y' AND A.FINANCE_NO = '"+m_finance_no+"'"+
					" ) ORDER BY DOCUMENT_NO ASC ";
				
				/*
				" SELECT	A.DOCUMENT_NAME, A.DOCUMENT_NO, A.FILE_NAME, TO_CHAR(A.ENT_DATE,'YYYY-MM-DD HH:MI:SS'), A.ENT_USER,  length(A.DOCUMENT) "+ // mod by udara 20-03-2015 // mod by udara 23-02-2015
				"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
				"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.FINANCE_NO = '"+m_finance_no+"'"+
				"  ORDER BY A.DOCUMENT_NO ASC";	 
				*/
				
				rs=stmt.executeQuery(Sql_data);
				//out.println(""+Sql_data+"");
				boolean more=rs.next();
				int count=0;
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Uploaded Documents</u></td>"); 
				out.println("</tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Finance No: "+m_finance_no+"</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				if(!more){
					/*out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >"); */
				}else{
				
				out.println("<table id=mytable align=\"center\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				
				out.println("<tr >");
				out.println("<td class=factoring-letter-body STYLE='{text-align:center;}' ><b>No</b></td>"); 			
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; }'><b>Document Number</b></td>"); 
				out.println("<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Document Name</b></td>"); 	
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>File Name</b></td>"); 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>File Size (KB)</b></td>"); // added by udara 20-03-2015
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>Date/Time</b></td>"); // added by udara 23-02-2015 
				out.println("<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>User</b></td>"); // added by udara 23-02-2015
				out.println("<td class=factoring-letter-body STYLE='{text-align:center; }' ><b>&nbsp;</b></td>"); 
				//out.println("<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>&nbsp;</b></td>"); 																																										
				out.println("</tr >");
				
				}
				//=================================================
				
				
				int j=1;
				double m_file_size = 0;
				
				while(more){
					
					m_file_size = rs.getDouble(6);
					
					if(m_file_size>0)
						m_file_size = m_file_size / 1000;
					else
						m_file_size = 0;
					
					out.println("<tr  id=tr_id"+j+"   >"); //onMouseover=\"this.style.backgroundColor='yellow' \"  onMouseOut=\"this.style.backgroundColor='#FFFFFF' \"
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:center;}'  bgcolor='lightblue' >"+j+"</td>"); 
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(2)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(1)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(3)+"</td>");
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:right;}'  >"+nf.format(m_file_size)+"</td>"); // added by udara 20-03-2015
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(4)+"</td>"); // added by udara 23-02-2015
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:left;}'  >"+rs.getString(5)+"</td>"); // added by udara 23-02-2015
					out.println("<td  class=factoring-letter-body   STYLE='{text-align:center;}'  ><input type='button' class='but_input' value='View' onclick='get_document(\""+rs.getString(2)+"\",\""+rs.getString("FILE_STORAGE")+"\")'></td>");
					//out.println("<td  class=factoring-letter-body   STYLE='{text-align:center;}'  ><input type='button' class='but_input' value='Delete' onclick='delete_document(\""+rs.getString(2)+"\")'></td>");
					out.println("</tr>");
					
					
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				
				
				
				
				if(more)
					out.println("</table>");		
				
			
				out.println("</br>");
				out.println("</br>");
				out.println("</br>");
				//=======================================================================================================================
				/*String m_newDocuments =""+  changed on 2018-02-13
								 " SELECT  "+								 
								 "  NVL(A.FINANCE_NO,'_') FINANCE_NO, "+
								 "   NVL(A.DOC_ID,'_') DOC_ID, "+
								 "   NVL(B.DOC_NAME,'_') DOC_NAME,   "+
								 "   NVL(A.DOC_NUM,0) DOC_NUM, "+
								 "   NVL(B.DOC_DESCRIPTION,'_') DOC_DESCRIPTION, "+
								 "   NVL(A.DOCUMENT_NAME,'_') DOCUMENT_NAME,   "+
								 "	 NVL(A.FILE_SIZE,0) FILE_SIZE, "+
							  	 "	 NVL(TO_CHAR(A.MOD_DATE,'YYYY-MM-DD HH:MI:SS'),TO_CHAR(A.ENT_DATE,'YYYY-MM-DD HH:MI:SS')) ENT_DATE, "+
							  	 "	 NVL(A.MOD_USER,A.ENT_USER) ENT_USER "+
								 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_LOG A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_TYPES B "+
								 " WHERE a.DOC_TYPE_ID = B.DOC_ID "+
								 " AND A.ACTIVE_STATUS ='Y' "+
								 " AND A.FINANCE_NO = '"+m_finance_no+"' "+
								 " ORDER BY B.DOC_NAME,A.DOC_NUM ";
					
				rs=stmt.executeQuery(m_newDocuments);
				boolean isHeaderPrint = false;
				int docNum =1;
				while(rs.next()){
					if(!isHeaderPrint){
						isHeaderPrint = true;
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Uploaded New Documents</u></td>"); 
						out.println("</tr >");
						out.println("</table >");
						
						out.println("<table id=mytable align=\"center\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
						out.println("<tr >");
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;}' ><b>No</b></td>");
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center; }'><b>Document Number</b></td>"); 
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Document Name</b></td>"); 
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Document Description</b></td>"); 
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Document No</b></td>"); 
						out.println(" 	<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>File Name</b></td>"); 
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>File Size (KB)</b></td>"); 
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>Date/Time</b></td>"); 
						out.println(" 	<td class=factoring-letter-body STYLE='{text-align:center;  }'><b>User</b></td>"); 						
						out.println(" 	<td class=factoring-letter-body  STYLE='{text-align:center;  }'><b>&nbsp;</b></td>"); 
						out.println("</tr >");
					}
					out.println("<tr >");
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:center;}'  bgcolor='lightblue' >"+ docNum++ +"</td>");//No
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("DOC_ID")+"</td>"); //Document Number
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("DOC_NAME")+"</td>"); //Document Name
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("DOC_DESCRIPTION")+"</td>"); //Document Description
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:center;}' >"+rs.getString("DOC_NUM")+"</td>"); //Document No
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("DOCUMENT_NAME")+"</td>"); //File Name
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("FILE_SIZE")+"</td>"); //File Size (KB)
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("ENT_DATE")+"</td>"); //Date/Time
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:left;}' >"+rs.getString("ENT_USER")+"</td>"); //User
					out.println(" 	<td class=factoring-letter-body   STYLE='{text-align:center;}' ><input type='button' class='but_input' value='View' onclick='view_document_new(\""+rs.getString("DOC_ID")+"\")'></td>"); //
					out.println("</tr >"); 
					
				}
				*/
				boolean isHeaderPrint = false;
				int numOfDoc =0;
				int num_of_rec=0;
				int num_of_slots = 0;
				String m_subQuery ="";
				String m_query = ""+
								 " SELECT  "+
								 "   A.DOC_ID, "+
								 "   A.DOC_NAME, "+
								 "   NVL(A.DOC_DESCRIPTION,A.DOC_NAME) DOC_DESCRIPTION, "+
								 "   NVL(A.NUM_OF_DOC,0) NUM_OF_DOC, "+
								 "   A.ACTIVE_STATUS "+
								 " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_TYPES A "+
								 " WHERE A.ACTIVE_STATUS ='Y'  "+
								 " ORDER BY DOC_DESCRIPTION ASC ";
				resultSet= statement.executeQuery (m_query);
				
				out.println("<table align='center' width='95%' border='1px solid black' border-collapse='collapse' class='table'>");
				while(resultSet.next()){
					if(!isHeaderPrint){
						isHeaderPrint= true;
						out.println("<tr class='pdn_txtpos2' >"); 
						out.println(" 	<td width='5%'  align=center><b>No</b></td>");
						out.println(" 	<td width='25%' align=center><b>Document Type</b></td>");
						out.println(" 	<td width='15%' align=center></td>");
						out.println(" 	<td width='15%' align=center><b>File Size(KB)</b></td>");
						out.println(" 	<td width='15%' align=center><b>Date/Time</b></td>");
						out.println(" 	<td width='10%' align=center><b>User</b></td>");						
						
						out.println("</tr>");
					}
					numOfDoc = resultSet.getInt("NUM_OF_DOC");
					for(int i=1;i<=numOfDoc;i++){
						m_subQuery = " SELECT  "+
								 "   DOC_ID, "+
								 "   DOC_TYPE_ID, "+
								 "   NVL(DOC_NUM,0) DOC_NUM, "+
								 "   NVL(DOCUMENT_NAME,'_') DOCUMENT_NAME, "+
								 "   NVL(FILE_TYPE,'_') FILE_TYPE, "+
								 "   NVL(FILE_SIZE,0) FILE_SIZE, "+
								 "   NVL(ACTIVE_STATUS,'N'), "+
								 "   NVL(A.MOD_USER,A.ENT_USER) ENT_USER, "+
								 "   TO_CHAR(NVL(A.MOD_DATE,A.ENT_DATE),'YYYY-MM-DD HH:MI:AM') ENT_DATE "+
								 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_LOG A "+
								 " WHERE a.FINANCE_NO = '"+m_finance_no+"' "+
								 " AND A.ACTIVE_STATUS ='Y' "+
								 " AND a.DOC_TYPE_ID = '"+resultSet.getString("DOC_ID")+"' "+
								 " AND a.DOC_NUM ='"+i+"' ";
						resultSet2 = statement2.executeQuery (m_subQuery);
						if(resultSet2.next()){
								out.println("<tr >"); 
								++num_of_rec;
								//out.println(" 	<td align=center >"+ ++num_of_rec +"</td>"); //No
								if(i==1 && numOfDoc>1){
									out.println(" 	<td align=center rowspan='"+numOfDoc+"'>"+ ++num_of_slots +"</td>"); //No
									out.println(" 	<td rowspan='"+numOfDoc+"'>"+ resultSet.getString("DOC_DESCRIPTION")+"</td>"); //Document Type
								}else if(i==1 && numOfDoc==1){
									out.println(" 	<td align=center >"+ ++num_of_slots +"</td>"); //No
									out.println(" 	<td >"+ resultSet.getString("DOC_DESCRIPTION")+"</td>"); //Document Type
								}
								out.println(" 	<td align=center ><input type=\"button\" class='mainbut' onClick='view_document_new(\""+resultSet2.getString("DOC_ID")+"\")' width='100%' value=\"View\"></td>"); //view
								out.println(" 	<td align=center >"+ resultSet2.getString("FILE_SIZE")); //File Size(KB)
						 		out.println(" 		<input  type='hidden' value='"+resultSet2.getString("DOC_TYPE_ID")+"' name='HID_DOC_TYPE_ID_"+num_of_rec+"'>"); 
								out.println(" 		<input  type='hidden' value='N' name='HID_UPLOAD_STATUS_"+num_of_rec+"'>"); 
								out.println(" 		<input  type='hidden' value='N' name='HID_UPLOAD_ENABLE_"+num_of_rec+"'>");
								out.println(" 		<input  type='hidden' value='"+resultSet2.getString("DOC_NUM")+"' name='HID_DOC_NUM_"+num_of_rec+"'></td>"); 
								out.println(" 	<td>"+ resultSet2.getString("ENT_DATE")+"</td>"); //Date/Time
								out.println(" 	<td>"+ resultSet2.getString("ENT_USER")+"</td>"); //User								
								
								out.println("</tr>");
						}else{						
								out.println("<tr >"); 
								++num_of_rec;
								//out.println(" 	<td align=center >"+ ++num_of_rec +"</td>"); //No
								if(i==1 && numOfDoc>1){
									out.println(" 	<td align=center rowspan='"+numOfDoc+"'>"+ ++num_of_slots +"</td>"); //No
									out.println(" 	<td rowspan='"+numOfDoc+"'>"+ resultSet.getString("DOC_DESCRIPTION")+"</td>"); //Document Type
								}else if(i==1 && numOfDoc==1){
									out.println(" 	<td align=center >"+ ++num_of_slots +"</td>"); //No
									out.println(" 	<td >"+ resultSet.getString("DOC_DESCRIPTION")+"</td>"); //Document Type
								}
								out.println(" 	<td align=center >&nbsp;</td>"); //view <input type=\"button\" class='mainbut' onClick='' width='100%' value=\"View\" disabled>
								out.println(" 	<td >"); //File Size(KB)
								out.println(" 		<input  type='hidden' value='"+resultSet.getString("DOC_ID")+"' name='HID_DOC_TYPE_ID_"+num_of_rec+"'>");
								out.println(" 		<input  type='hidden' value='N' name='HID_UPLOAD_STATUS_"+num_of_rec+"'>"); 
								out.println(" 		<input  type='hidden' value='Y' name='HID_UPLOAD_ENABLE_"+num_of_rec+"'>");
								out.println(" 		<input  type='hidden' value='"+i+"' name='HID_DOC_NUM_"+num_of_rec+"'></td>");
								out.println(" 	<td>&nbsp;</td>"); //Date/Time
								out.println(" 	<td>&nbsp;</td>"); //User								
								
								out.println("</tr>");
						}
					}
				}
				out.println("</table >");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				//		out.println("<SCRIPT language1.2='JavaScript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js'></SCRIPT>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
		}
			
		
		}catch (Exception ex) {
			ex.printStackTrace();
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			
			if(out!=null){try{out.flush();out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
