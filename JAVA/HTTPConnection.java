

import java.io.*;
import java.net.*;
import java.security.*;
import sun.misc.BASE64Decoder; 


//import java.io.File;
//import java.io.FileInputStream;

//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
//import org.apache.commons.httpclient.methods.PostMethod;


public class HTTPConnection
{
	
	private String hostname = null;
	private int port;
	private String path = null;
	
	public HTTPConnection()
	{
	}
	
	public void Open ( String hostname, int port, String path )
	{
		
		this.hostname = hostname;
		this.port     = port;
		this.path     = path;
	}
	
	public void Close ()
	{
	}
	
	
	public boolean IsConnected()
	{
		return false;
	}
	
	
	
	
	public String HTTPRequest (  String xmldata ) 
	{
		String responce = new String();
		
		/*String inputLine = "";
		try
		{            
			// int port = 8081;
			// String hostname = "220.247.223.51";
			// String path = "/sendsms";
			
			// String username = "orient";
			// String password = "0riEn@t";
			
			
			System.out.println("madhawa");
			System.out.println(hostname);
			System.out.println(port);
			System.out.println(path);
			System.out.println(xmldata);
			
			InetAddress addr = InetAddress.getByName(hostname);
			Socket sock = new Socket(addr, port);
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"));
			wr.write("POST " + path + " HTTP/1.0\r\n");
			wr.write("Host: " + hostname + "\r\n");
			wr.write("Content-Length: " + xmldata.length() + "\r\n");
			wr.write("Content-Type: text/xml; charset=\"utf-8\"\r\n");
			wr.write("\r\n");
			wr.write(xmldata);
			wr.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

			while ((inputLine = in.readLine()) != null)
			{
				System.out.println(inputLine);
				responce =  responce + inputLine + "\n" ;
			}
			in.close();
			System.out.println("Sent");
		}
		catch (Exception e)
		{   
			System.out.println(e.getMessage());           
		}
		return responce;
		
		*/
		//--------------------
		
		/*String strURL = "http://www.bulksms.lk:8081/sendsms/";
		String strXMLFilename = "D:\\SasiaNet_Products\\NetAsset\\NETFAC\\UPLOAD\\xmlfile.xml";
		File input = new File(strXMLFilename);
		PostMethod post = new PostMethod(strURL);
		
		try {
			
			post.setRequestHeader("Content-type",
				"text/xml"); // ; charset=ISO-8859-1
			post.setRequestHeader("Content-length",
				"" + input.length()); 
			post.setRequestHeader("Content-transfer-encoding",
				"text"); 
	post.setRequestHeader("Connection",
				"close"); 
	       post.setRequestEntity(new InputStreamRequestEntity(
				new FileInputStream(input), input.length()));
			

			
			
			HttpClient httpclient = new HttpClient();
			
			int result = httpclient.executeMethod(post);
			System.out.println("Response status code: " + result);
			System.out.println("Response body: ");
			System.out.println(post.getResponseBodyAsString());
			return post.getResponseBodyAsString();
		} catch (IOException e) {
			e.printStackTrace();
			return post.getResponseBodyAsString();
		} finally {
			post.releaseConnection();
			return post.getResponseBodyAsString();
		}
		*/
		
		
		try{
			
			
			
			// PROXY
			System.setProperty("http.proxyHost","192.168.234.253") ;
			System.setProperty("http.proxyPort", "") ;
			
			URL objURL = new URL("http://" + hostname + ":" + port + path + "/" );
			
			URLConnection objURLConnection = objURL.openConnection();
			
			/*Authenticator.setDefault(new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new
							PasswordAuthentication("itadmin","Asanka1982".toCharArray());
					}});*/
			
			
			sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			String encodedUserPwd =
				encoder.encode("itadmin:Asanka1982".getBytes());
			objURLConnection.setRequestProperty
				("Proxy-Authorization", "Basic " + encodedUserPwd);

			
			
			
			//PasswordAuthentication("mydomain\\username","password".toCharArray());
			objURLConnection.setRequestProperty( "Content-Type","text/xml"); 
			objURLConnection.setRequestProperty("accept-charset", "UTF-8");
			objURLConnection.setRequestProperty("Content-Length", Integer.toString(xmldata.length()));
			objURLConnection.setUseCaches (false);
			objURLConnection.setDoOutput(true);
			objURLConnection.setDoInput(true);
			
			
			//Write Form Data
			OutputStreamWriter objOutputStreamWriter = new OutputStreamWriter(objURLConnection.getOutputStream());
			
			objOutputStreamWriter.write(xmldata);
			objOutputStreamWriter.flush();
			objOutputStreamWriter.close();
			
			
			//Get Response
			//StringBuilder objStringBuilder = new StringBuilder();
			
			BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objURLConnection.getInputStream()));
			String sLine;
			while ((sLine = objBufferedReader.readLine()) != null) 
			{
				//objStringBuilder.append ( sLine + "\n" ) ;
				responce=  responce + sLine + "\n" ;
			}
			
			
			objBufferedReader.close();
			
			//return objStringBuilder.toString();
			System.out.println("Responce " + responce);
			return responce;
			
			
			
		
			
			
			
			
			
			
		}catch (Exception e) {
			
			System.out.println("exception: " + e);
			e.printStackTrace();
			return responce;
		}
	} 
}
