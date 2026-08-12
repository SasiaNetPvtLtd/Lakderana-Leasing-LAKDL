//package com.mkyong;
 
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;
 
public class HttpURLConnectionExample {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	public static void main(String[] args) throws Exception {
 
		HttpURLConnectionExample http = new HttpURLConnectionExample();
 
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
 
		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();
 
	}
 
	// HTTP GET request
	private void sendGet() throws Exception {
 
		String url = "http://www.google.com/search?q=mkyong";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
 
	// HTTP POST request
	private void sendPost() throws Exception {
 
		//String url = "https://selfsolve.apple.com/wcResults.do";
		//String url = "https://groupsms.etisalat.lk/sendsms.php?";
		String url = "https://groupsms.etisalat.lk/sendsms.php?USER=lakderena&password=L@k6e9A&MASK=LAKDERANA&NUM=0714199936&MSG=POST TESTING";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		String urlParameters = "USER=lakderena&password=L@k6e9A&MASK=LAKDERANA&NUM=0714199936&MSG=POST TESTING";
		
		//out.println("	 m_send_val = \"USER=lakderena&password=L@k6e9A&MASK=LAKDERANA&NUM=\"+unformat_noobject(document.Form1.TXT_CODE_NUM.value)+\"&MSG=\"+unformat_noobject(document.Form1.TXT_MESSAGE.value);");

 
		// Send post request
		con.setDoOutput(true);
		//DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		//wr.writeBytes(urlParameters);
		//wr.flush();
		//wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
 
}