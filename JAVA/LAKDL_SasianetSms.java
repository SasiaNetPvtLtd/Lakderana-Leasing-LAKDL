

import java.io.*;
import java.util.*; 

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.*;




public class LAKDL_SasianetSms
{
	
	
	
	public String SendMessage ( String hostname, int port ,String path ,String username, String password,String from,String toNumbers,String messages )  throws Exception  
	{
		
		
		
		String xmlData = null;
		String responce = "";
		//HTTPConnection objSMSConnection = new HTTPConnection();
		//objSMSConnection.Open (hostname, port, path );
		//xmlData = createXml(username,password,from,toNumbers,messages);
		
		//System.out.println("disnaka amila   " +xmlData );
		//System.out.println("disnaka  " +hostname );
		//System.out.println("disnaka  " +port );
		//System.out.println("disnaka  " +path );
		
		
		//responce = objSMSConnection.HTTPRequest (xmlData);
		
		//objSMSConnection.Close();


		System.setProperty("jsse.enableSNIExtension", "false");
		//System.setProperty("https.protocols", "SSLv3");
		
		responce = sendPost(hostname, username,  password, from, toNumbers, messages);
		
		return responce;
		
		
	}
	
	
	// HTTP GET request
	private String sendPost(String hostname,String username, String password,String from,String toNumbers,String messages) throws Exception {
		
		TrustManager[] trustAllCerts = new TrustManager[] { 
			new X509TrustManager() {     
				public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
					return new java.security.cert.X509Certificate[0];
				} 
				public void checkClientTrusted( 
					java.security.cert.X509Certificate[] certs, String authType) {
				} 
				public void checkServerTrusted( 
					java.security.cert.X509Certificate[] certs, String authType) {
				}
			} 
		}; 
		
		// Install the all-trusting trust manager
		try {// 
			SSLContext sc = SSLContext.getInstance("SSL"); 
			sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
			
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

			@Override
			
			              public boolean verify(String arg0, SSLSession arg1) {
			
			                             // TODO Auto-generated method stub
			
			                             return true;
			
			              }                                              
			
			});
			
			
			
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		} 
		
		//String url = "https://selfsolve.apple.com/wcResults.do";
		String url = hostname +"?USER="+username+"&PWD="+password+"&MASK="+from+"&NUM="+toNumbers+"&MSG="+messages+"";
		//String url = "https://www.google.lk/";
		System.out.println(url);
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection(); // commented by udara 28-09-2018
		
		
		
		
		
		
		
		
		
		//HttpURLConnection con = (HttpURLConnection) obj.openConnection(); // added by udara 28-09-2018
		
		
		
		//add reuqest header
		/*con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 */
		//String urlParameters = "USER="+m_user+"&password="+m_password+"&MASK="+m_mask+"&NUM="+m_num+"&MSG="+m_msg+"";
		
		//out.println("	 m_send_val = \"USER=lakderena&password=L@k6e9A&MASK=LAKDERANA&NUM=\"+unformat_noobject(document.Form1.TXT_CODE_NUM.value)+\"&MSG=\"+unformat_noobject(document.Form1.TXT_MESSAGE.value);");
		
		
		// Send post request
		/*
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 */
		BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		//System.out.println(response.toString());
		return  response.toString();
		
	}
	
	
	private String createXml (String username,String password,String from ,Vector toNumbers,Vector messages)  {
		//String xmldata= null;
		String xmldata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<sms_list>"
			+ "<user_name>" + username + "</user_name>"
			+ "<password>" + password + "</password>"
			+ "<from>" + from + "</from>";
		
		
		
		for (int i = 0; i < toNumbers.size(); i++) {
			
			xmldata = xmldata + "<sms>"
				+ "<msg_ref_num>M00" + i +"</msg_ref_num>"
				+ "<to>" +  toNumbers.get(i) + "</to>"
				+ "<msg>" + messages.get(i) + "</msg>"
				+ "</sms>";
			
		}
		
		xmldata = xmldata + "</sms_list>";
		
		return xmldata;
		
		
	}
	
	
	
}