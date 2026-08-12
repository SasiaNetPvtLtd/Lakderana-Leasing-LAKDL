import java.io.*;
import java.util.*;
import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;
//import PTL_sn_methods;
import oracle.jdbc.driver.*;
import java.net.*;
//TAKEN FROM PTL AND ADDED TO LAKDL BY KANCHANA ON 2016-06-03  not used

public class LAKDL_AF_FileUploadServlet2 extends HttpServlet {
	protected int maxSize = 1024*1024*1; // 1MB 
		Vector MvaluesVec =new Vector();

	String html_client_url;
  	String servlet_client_url;
  	String client_t3_port;
	String imagename;	
	String reqstr;
	String UploadFileName="";
	String UploadFile="";
	String	cname="";
	int i;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	{

		ServletOutputStream out =null;
		Connection conn=null;

		
		try{
		   
			
	      LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
		  String m_schema_name=m_sn_methods.schema_name.trim();
          conn = m_sn_methods.met_user_validate(req); 
				
			
			//**********REQUEST THE VALUES FROM THE PREVIOUS SERVLET****************************
				
			/*	BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			  reqstr=input.readLine();
			  imagename=(String)m_PTL_method.met_formdata(reqstr,"TXT_CPCODE");*/

		    
				html_client_url 		= m_sn_methods.html_client_url.trim();
    			servlet_client_url 		= m_sn_methods.servlet_client_url.trim();
    			client_t3_port 			= m_sn_methods.client_t3_port.trim();
				String m_client_name 	= m_sn_methods.client_name.trim();
				String m_fschema_name	=m_sn_methods.client_name.trim();
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
	
		    out = res.getOutputStream();
		   Hashtable table;
				
  		//out.println("here");
		 
		if (!req.getContentType().toLowerCase().startsWith("multipart/form-data")) {		
			sendFailure(res,"Wrong content type set for file upload");
			return;
		}
		
		if (req.getContentLength() > maxSize) {
			sendFailure(res,
				"Content too long - sent files are limited in size");
			return;
		}
		else{
		//out.println("window.location.href='"+html_client_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=save_document';");
		//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=save_document;");	
		//out.println(" window.location.href=m_url;");
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Aploaded Successfully aaaa');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=save_document';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
	
		//out.println("	document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=save_document';");
			//	out.println("	document.Form1.submit();");
		}
		
		int ind = req.getContentType().indexOf("boundary=");
		//out.println("ind***"+ind);
		if (ind == -1) {
			sendFailure(res,"boundary is not set");
			return;
		}
		
		String boundary = req.getContentType().substring(ind+9);
		
			//out.println("boundary***"+boundary);
		
		if (boundary == null) {
			sendFailure(res,"boundary is not set");
			return;
		}
		
		
		try {
			
					
			table = parseMulti(boundary, req.getInputStream());
		
		} catch (Throwable t) {
			t.printStackTrace(new PrintStream(res.getOutputStream()));
			return;
		}
		
		out.println("<HTML><HEAD><TITLE>FileUpload Output");
		out.println("</TITLE>");
		out.println("<script language=\"JavaScript\"  src=\""+html_client_url+"validate.js\"></script>");
		out.println("<SCRIPT language='JavaScript'>");
	
    out.println("function close_window(fname){");
	    out.println("	 window.opener.SetFileNme(fname);");	
		out.println("	 window.close();");				
		out.println("  }");		
		 
		
		out.println("</script>");				
		out.println("</head>");
		out.println("<BODY BGCOLOUR = \"silver\" >");
		
		out.println("<h4>Contents of the Uploaded  file:</h4>");
		for (Enumeration fields = table.keys(); fields.hasMoreElements(); ) {
			String name = (String)fields.nextElement();
			Object obj = table.get(name);
			if (obj instanceof Hashtable) {
				// its a file!
				Hashtable filehash = (Hashtable)obj;
				out.println("<hr>Filename: "+filehash.get("filename")+"<br>");
				UploadFile=filehash.get("filename").toString();
    			UploadFileName= UploadFile.substring(UploadFile.lastIndexOf("\\")+1,UploadFile.length());
				out.println("Content-Type: "+filehash.get("content-type")+"<br>");
				obj = filehash.get("content");
				byte[] bytes = (byte[])obj;						
				try{									
				
						//FileOutputStream FoutStream = new FileOutputStream("d:\\Dealnet\\MBFSL\\Intranet\\client-signatures\\"+UploadFileName+"");
					// kan	FileOutputStream FoutStream = new FileOutputStream("D:\\SasiaNet_Products\\DealNet\\PTL\\Intranet\\client-signatures\\"+UploadFileName+""); // comment Amila 06-10-2015 
						//FileOutputStream FoutStream = new FileOutputStream("D:\\SasiaNet\\DealNet\\PTL\\Intranet\\client-signatures\\"+UploadFileName+"");// Add by Amila 06-10-2015 
					//	FoutStream.write(bytes);
					//	FoutStream.close();
			/*	    out.println("<form name='form1'>");		
						out.println("<H4>Client Photograph  is uploaded successfully.... </H4>");
						out.println("<TABLE WidTH=\"100%\"BORDER=\"0\" CELLPADDING=\"0\" CELLSPACING=\"0\" STYLE=\"{color: black; font: bold 9pt arial;}\">");
				    out.println("<TR>");
				    out.println("<TD WidTH=\"40%\"></td><td><input type=\"button\" onclick=\"close_window('"+UploadFileName+"')\" value=\"Close\" style=\"{cursor:hand; color: #FFFFFE; font: bold 9pt arial; border-color: #2C2C9C; background: #2C2C9C;}\"></td>");
				    out.println("</tr>");
				    out.println("</TABLE>");
					out.println("</form>");	*/
				}
				catch (Exception er){
					out.println("Error while uploading  , PLEASE TRY AGAIN "+er.toString());
				}
				
				
			} else if (obj instanceof String[]) {
				String[] values = (String[]) obj;
				for ( i =0; i < values.length; i++) {			
				//out.println(values[i]+"5555555555555555555555555555555555<br>");	
					 }
				//MvaluesVec.addElement(values[i]);
				//out.println(values[i]+"5555555555555555555555555555555555<br>");	
			}			
		}		 
		out.flush();
		
		
		/*try{		
				int response1=0;
				int incre=0;
				String one_record;		
				FileInputStream fin1=new FileInputStream("d:\\portals\\SRILNKN\\ssimfile.txt");
				BufferedInputStream  bin1=new BufferedInputStream(fin1);
				byte f_record[]=new byte[1002];
				while(response1!=-1){
				response1=bin1.read(f_record,0,1002);
				StringBuffer strb_one=new StringBuffer();		
				one_record=new String(f_record).trim();
				incre++;
				callstmt1= (weblogic.jdbc.common.OracleCallableStatement)conn.prepareCall( "BEGIN "+m_schema_name+".PR_READ_SSIM_FILE(:1,:2,:3,:4); END; ");				
				callstmt1.setString(1,one_record);
				callstmt1.setString(2,new Integer(incre).toString());
				callstmt1.setString(3,"SRILNKN");
				callstmt1.setString(4,"DEMO_LOGIN");
				callstmt1.execute();
	      callstmt1.close();		
				}
		
				}catch(Exception e){out.println("Error while uploading SSIM FILE , PLEASE TRY AGAIN");}*/
		return;
		
		}catch(Exception er1){
			try{out.println("Error while uploading  file , Please try again"); out.close();}
			catch(Exception er2){}
		}
		
	}
	
	
	
	Hashtable parseMulti(String boundary, ServletInputStream in) throws IOException {
		
		int buffSize = 1024*8; // 8K
		Hashtable hash = new Hashtable();
		int result;
		String line;
		String lowerline;
		String boundaryStr = "--"+boundary;
		ByteArrayOutputStream content;
		String filename;
		String contentType;
		String name;
		String value;
		
		byte[] b = new byte[buffSize];
		
		result = in.readLine(b,0,b.length);
		// failure.
		if (result == -1) 
			throw new IllegalArgumentException("InputStream truncated");
		line = new String(b,0,0,result);
		// failure.
		if (!line.startsWith(boundaryStr)) 
			throw new IllegalArgumentException("MIME boundary missing: "+line);
		while (true) {
		System.out.println("here ");
			// Some initialization
			filename = null;
			contentType = null;
			content = new ByteArrayOutputStream();
			name = null;
			
			// get next line (should be content disposition)
			result = in.readLine(b,0,b.length);
			
			if (result == -1) return hash;
			line = new String(b,0,0,result-2);
			lowerline = line.toLowerCase();
			if (!lowerline.startsWith("content-disposition"))
				// don't know what to do, so we'll keep looking...
				continue;
			// determine what the disposition is
			int ind = lowerline.indexOf("content-disposition: ");
			int ind2 = lowerline.indexOf(";");
			if (ind == -1 || ind2 == -1) 
				throw new IllegalArgumentException(
					"Content Disposition line misformatted: "+line);
			String disposition = lowerline.substring(ind+21,ind2);
			if (!disposition.equals("form-data"))
				throw new IllegalArgumentException(
					"Content Disposition of "+disposition+" is not supported");
			// determine what the name is
			int ind3 = lowerline.indexOf("name=\"",ind2);
			int ind4 = lowerline.indexOf("\"",ind3+7);
			if (ind3 == -1 || ind4 == -1) 
				throw new IllegalArgumentException(
					"Content Disposition line misformatted: "+line);
			name = line.substring(ind3+6,ind4);
			// determine filename, if any
			int ind5 = lowerline.indexOf("filename=\"",ind4+2);
			int ind6 = lowerline.indexOf("\"",ind5+10);
			if (ind5 != -1 && ind6 != -1) {
				filename = line.substring(ind5+10,ind6);
			}
			
			// Whew!  We now move onto the next line, which
			// will either be blank, or Content-Type, followed by blank.
			result = in.readLine(b,0,b.length);
			if (result == -1) return hash;
			line = new String(b,0,0,result-2); // -2 to remove \r\n
			System.out.println("line** "+line);
			lowerline = line.toLowerCase();
			if (lowerline.startsWith("content-type")) {
				int ind7 = lowerline.indexOf(" ");
				if (ind7 == -1) 
					throw new IllegalArgumentException(
						"Content-Type line misformatted: "+line);
				contentType = lowerline.substring(ind7+1);
				//  read blank header line
				result = in.readLine(b,0,b.length);
				if (result == -1) return hash;
				line = new String(b,0,0,result-2); // -2 to remove \r\n
				if (line.length() != 0) {
					throw new IllegalArgumentException(
						"Unexpected line in MIMEpart header: "+line);
				}
			} else if (line.length() != 0) {
				throw new IllegalArgumentException(
					"Misformatted line following disposition: "+line);
			}
			
			//read content, implement readahead by one line
			boolean readingContent = true;
			boolean firstLine = true;
			byte[] buffbytes = new byte[buffSize];
			int buffnum = 0;
			
			result = in.readLine(b,0,b.length);
			if (result == -1) return hash;
			line = new String(b,0,0,result); 
			if (!line.startsWith(boundaryStr)) {
				System.arraycopy(b,0,buffbytes,0,result);
				buffnum = result;
				result = in.readLine(b,0,b.length);
				if (result == -1) return hash;
				line = new String(b,0,0,result); 
				firstLine = false;
				if (line.startsWith(boundaryStr)) {
					readingContent = false;
				}
			} else {
				readingContent = false;
			}
			
			while (readingContent) {
				content.write(buffbytes,0,buffnum);
				System.arraycopy(b,0,buffbytes,0,result);
				buffnum = result;
				result = in.readLine(b,0,b.length);
				if (result == -1) return hash;
				line = new String(b,0,0,result); 
				if (line.startsWith(boundaryStr)) readingContent = false;
			}
			if (!firstLine) {
				// -2 to trim \r\n
				if (buffnum>2)
					content.write(buffbytes,0,buffnum-2); 
			}
			
			
			//now set appropriate variable, populate hashtable
			if (filename == null) {
				if (hash.get(name) == null) {
					String[] values = new String[1];
					values[0] = content.toString();
					hash.put(name,values);
				} else {
					Object prevobj = hash.get(name);
					if (prevobj instanceof String[]) {
						String[] prev = (String[])prevobj;
						String[] newStr = new String[prev.length+1];
						System.arraycopy(prev,0,newStr,0,prev.length);
						newStr[prev.length] = content.toString();
						hash.put(name,newStr);
					} else {
						//now what? I think this breaks the standard.
						throw new IllegalArgumentException("failure in parseMulti hashtable building code");
					}
				}
			} else {
				// Yes, we don't return Hashtable[] for multiple files of same name.  AFAIK, that's not allowed.
				Hashtable filehash = new Hashtable(4);
				filehash.put("name",name);
				filehash.put("filename",filename);
				if (contentType == null) contentType = "application/octet-stream";
				filehash.put("content-type",contentType);
				filehash.put("content",content.toByteArray());
				hash.put(name,filehash);
			}
		}
	}
	
	private void sendFailure(HttpServletResponse res, String reason) 
		throws IOException {
		
		ServletOutputStream out = res.getOutputStream();
		
		out.println("<HTML><HEAD>Upload Failure</HEAD><BODY>");
		out.println("<h2>The upload failed, due to:</h2>");
		out.println(reason);
		out.println("<BR>You may wish to inform the system administrator.");
		out.println("</BODY></HTML>");
	}
	
}