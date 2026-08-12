/*
Extract Zip File With Subdirectories Using Command Line Argument Example.
This Java example shows how to extract a zip file and create required
sub-directories using Java ZipInputStream class.
*/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


import java.io.*;
import java.util.*;
import java.lang.String;



public class LAKDL_web_xml_creater {
	public static void main(String[] args) {
		
		PrintWriter log=null;
		String m_path="Z:\\NetAsset\\NETFAC\\JAVA";//"F:\\Temp\\java\\";
		//String m_error_log="C:\\BOSTONAC\\Java\\fail_files.txt";//"F:\\Temp\\New\\fail_files.txt";
		System.out.println("test");
		
		String m_dfilename ="D:\\Temp\\web.xml";
		
		StringBuffer buffer = new StringBuffer();
		
		File f1=new File(m_path);
		File[] flist;
		flist=f1.listFiles();
		
		
		String m_file_name;
		String m_file_name_org;
		String m_file_path="";
		String m_file_extension="";
		
		//String[] temp = new String[2];
		
		String cmd="";
		
		/*System.out.println("********************************************");
		System.out.println("Directory "+m_path+" *.java");
		System.out.println("Compling with "+m_cmd);
		System.out.println("classpath "+m_classpath);
		System.out.println("********************************************");*/
		
		int j=1;
		
		try{
			
			
			buffer.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
			buffer.append("<web-app xmlns=\"http://java.sun.com/xml/ns/j2ee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
			buffer.append("\txsi:schemaLocation=\"http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd\"\n");
			buffer.append("\tversion=\"2.4\">\n");
			
			buffer.append("\t<display-name>Netfac</display-name>\n");
			buffer.append("\t<description>");
			buffer.append("Factoring Solutions");
			buffer.append("</description>\n");
			
			
			//java.io.FileOutputStream error_file=new java.io.FileOutputStream(m_error_log);
			
			for(int i=0;i<flist.length;i++){
				if(flist[i].isFile()){
					m_file_path=flist[i].getAbsolutePath();
					m_file_name=flist[i].getName();
					m_file_extension=m_file_name.substring(m_file_name.length()-5);
				

					m_file_name_org = m_file_name.substring(0,m_file_name.lastIndexOf("."));  
					
					
					
					if(m_file_extension.toLowerCase().equals("class")){
						
						buffer.append("\t<servlet>\n");
						buffer.append("\t\t<servlet-name>");
						buffer.append(m_file_name_org);
						buffer.append("</servlet-name>\n");
						buffer.append("\t\t<servlet-class>");
						buffer.append(m_file_name_org);
						buffer.append("</servlet-class>\n");
						buffer.append("\t</servlet>\n");
						
						/*buffer.append("<servlet-mapping>");
						buffer.append("<servlet-name>");
						buffer.append(m_file_name);
						buffer.append("</servlet-name>");
						buffer.append("<url-pattern>/servlet/");
						buffer.append(m_file_name);
						buffer.append("</url-pattern>");
			buffer.append("</servlet-mapping>");
			*/
						//	System.out.println("m_file_name"+m_file_name);
					}
				}
			}
			
			
			for(int i=0;i<flist.length;i++){
				if(flist[i].isFile()){
					m_file_path=flist[i].getAbsolutePath();
					m_file_name=flist[i].getName();
					m_file_extension=m_file_name.substring(m_file_name.length()-5);
					
					
					m_file_name_org = m_file_name.substring(0,m_file_name.lastIndexOf("."));  
					
					
					if(m_file_extension.toLowerCase().equals("class")){
						
						/*buffer.append("<servlet>");
						buffer.append("<servlet-name>");
						buffer.append(m_file_name);
						buffer.append("</servlet-name>");
						buffer.append("<servlet-class>");
						buffer.append(m_file_name);
						buffer.append("</servlet-class>");
						buffer.append("</servlet>");
						*/
						buffer.append("\t<servlet-mapping>\n");
						buffer.append("\t\t<servlet-name>");
						buffer.append(m_file_name_org);
						buffer.append("</servlet-name>\n");
						buffer.append("\t\t<url-pattern>/servlet/");
						buffer.append(m_file_name_org);
						buffer.append("</url-pattern>\n");
						buffer.append("\t</servlet-mapping>\n");
						
						//	System.out.println("m_file_name"+m_file_name);
					}
				}
			}
			
			
			buffer.append("</web-app>");
			File xmlFile = new File(m_dfilename);	
			xmlFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(xmlFile);
			Writer out = new OutputStreamWriter(fos, "UTF8");
			out.write(buffer.toString());
			out.flush();
			out.close();
			fos.close();
			
		}
		catch(Exception e){
			
			System.out.println("Error>>"+e.toString());
			e.printStackTrace();
		}
		
	}
}





