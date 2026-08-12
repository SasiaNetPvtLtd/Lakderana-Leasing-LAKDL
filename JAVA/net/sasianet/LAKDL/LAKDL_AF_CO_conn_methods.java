// Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 2012-01-30 04:07:54 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   LAKDL_AF_CO_conn_methods.java

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import sun.misc.BASE64Decoder;

public class LAKDL_AF_CO_conn_methods
{
	
	public LAKDL_AF_CO_conn_methods()
	{
		html_client_url = "https://dev-lakdl.sasianet.com";
		servlet_client_url = "https://dev-lakdl.sasianet.com";
		client_name = "LAKDL_";
		client_t3_port = "/myserver/servlet";
		m_jsp_clent_url = "https://dev-lakdl.sasianet.com:/myserver/LKDASH";
		schema_name = "DEVLAKDL";
		company_name = "";
		bg_color1 = "#A3B2CC";
		bg_color2 = "white";
		fo_color1 = "black";
		fo_color2 = "black";
		header_name = "Asset Financing System 2.0.26.01.08";
		
	}
	
	public String format_text_area_string(String m_string)
	{
		String m_temp = "";
		String m_char = "";
		int i = m_string.indexOf("%0D%0A");
		if(i == -1)
			m_char = m_string;
		m_temp = m_string;
		for(; i != -1; i = m_temp.indexOf("%0D%0A"))
		{
			m_char = m_temp.substring(0, i) + " " + m_temp.substring(i + 6, m_temp.length());
			m_temp = m_char;
		}
		
		return m_char;
	}
	
	public String met_formdata(String reqstr, String objectname)
	{
		String curr_str = "";
		try
		{
			if(reqstr.indexOf(objectname) > -1)
			{
				int m_lenobject = 0;
				int m_reqpos = 0;
				int m_curr_pos = 0;
				if(reqstr.indexOf(objectname) == 0)
				{
					m_lenobject = objectname.length();
					m_reqpos = reqstr.indexOf(objectname + "=") + m_lenobject + 1;
					m_curr_pos = m_reqpos;
				} else
				{
					m_lenobject = objectname.length() + 1;
					if(reqstr.indexOf("&" + objectname + "=") != -1)
					{
						m_reqpos = reqstr.indexOf("&" + objectname + "=") + m_lenobject + 1;
						m_curr_pos = m_reqpos;
					} else
					{
						m_reqpos = reqstr.length();
						m_curr_pos = m_reqpos;
					}
				}
				for(; m_curr_pos <= reqstr.length() - 1 && reqstr.substring(m_curr_pos, m_curr_pos + 1).compareTo("&") != 0; m_curr_pos++)
					curr_str = curr_str + reqstr.substring(m_curr_pos, m_curr_pos + 1);
				
				curr_str = curr_str.replace('+', ' ');
				Vector escape_str = new Vector();
				escape_str.addElement("%7E");
				escape_str.addElement("%21");
				escape_str.addElement("%23");
				escape_str.addElement("%24");
				escape_str.addElement("%25");
				escape_str.addElement("%5E");
				escape_str.addElement("%26");
				escape_str.addElement("%28");
				escape_str.addElement("%29");
				escape_str.addElement("%60");
				escape_str.addElement("%3D");
				escape_str.addElement("%7B");
				escape_str.addElement("%7D");
				escape_str.addElement("%7C");
				escape_str.addElement("%5B");
				escape_str.addElement("%5D");
				escape_str.addElement("%3A");
				escape_str.addElement("%22");
				escape_str.addElement("%3B");
				escape_str.addElement("%27");
				escape_str.addElement("%3C");
				escape_str.addElement("%3E");
				escape_str.addElement("%3F");
				escape_str.addElement("%2C");
				escape_str.addElement("%20");
				escape_str.addElement("%2F");
				escape_str.addElement("%5C");
				escape_str.addElement("%2B");
				escape_str.addElement("%92");
				escape_str.addElement("%96");
				Vector correct_str = new Vector();
				correct_str.addElement("~");
				correct_str.addElement("!");
				correct_str.addElement("#");
				correct_str.addElement("$");
				correct_str.addElement("%");
				correct_str.addElement("^");
				correct_str.addElement("&");
				correct_str.addElement("(");
				correct_str.addElement(")");
				correct_str.addElement("`");
				correct_str.addElement("=");
				correct_str.addElement("{");
				correct_str.addElement("}");
				correct_str.addElement("|");
				correct_str.addElement("[");
				correct_str.addElement("]");
				correct_str.addElement(":");
				char dubchar[] = {
					'"'
				};
				String m_dubstr = new String(dubchar);
				correct_str.addElement(m_dubstr);
				correct_str.addElement(";");
				correct_str.addElement("'");
				correct_str.addElement("<");
				correct_str.addElement(">");
				correct_str.addElement("?");
				correct_str.addElement(",");
				correct_str.addElement(" ");
				correct_str.addElement("/");
				correct_str.addElement("/");
				correct_str.addElement("+");
				correct_str.addElement("'");
				correct_str.addElement("-");
				for(int i = 0; i < curr_str.length(); i++)
					if(curr_str.substring(i, i + 1).equals("%"))
					{
						String chk_str = curr_str.substring(i, i + 3);
						int escape_pos = escape_str.indexOf(chk_str);
						if(escape_pos >= 0)
						{
							String rep_str = (String)correct_str.elementAt(escape_pos);
							curr_str = curr_str.substring(0, i) + rep_str + curr_str.substring(i + 3);
						}
					}
				
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		return curr_str;
	}
	
	public String met_unformat_number(String numstr)
	{
		String m_number = "";
		for(int i = 0; i < numstr.length(); i++)
		{
			String oneChar = numstr.substring(i, i + 1);
			if(!oneChar.equals(","))
				m_number = m_number + oneChar;
		}
		
		return m_number;
	}
	
	public String XPosition(int x)
	{
		String m_space = "";
		for(int i = 0; i < x; i++)
			m_space = m_space + " ";
		
		return m_space;
	}
	
	public String YPosition(int y)
	{
		String m_space = "";
		for(int i = 0; i < y; i++)
			m_space = m_space + "\n";
		
		return m_space;
	}
	
	public String Add_Space(int count1)
	{
		String m_space = "";
		for(int i = 0; i < count1; i++)
			m_space = m_space + " ";
		
		return m_space;
	}
	
	public static String convert(long number)
	{
		if(number == 0L)
			return "Zero";
		String snumber = Long.toString(number);
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);
		int billions = Integer.parseInt(snumber.substring(0, 3));
		int millions = Integer.parseInt(snumber.substring(3, 6));
		int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
		int thousands = Integer.parseInt(snumber.substring(9, 12));
		String tradBillions;
		switch(billions)
		{
		case 0: // '\0'
			tradBillions = "";
			break;
			
		case 1: // '\001'
			tradBillions = convertLessThanOneThousand(billions) + " Billion ";
			break;
			
		default:
			tradBillions = convertLessThanOneThousand(billions) + " Billion ";
			break;
		}
		String result = tradBillions;
		String tradMillions;
		switch(millions)
		{
		case 0: // '\0'
			tradMillions = "";
			break;
			
		case 1: // '\001'
			tradMillions = convertLessThanOneThousand(millions) + " Million ";
			break;
			
		default:
			tradMillions = convertLessThanOneThousand(millions) + " Million ";
			break;
		}
		result = result + tradMillions;
		String tradHundredThousands;
		switch(hundredThousands)
		{
		case 0: // '\0'
			tradHundredThousands = "";
			break;
			
		case 1: // '\001'
			tradHundredThousands = "One Thousand ";
			break;
			
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " Thousand ";
			break;
		}
		result = result + tradHundredThousands;
		String tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}
	
	private static String convertLessThanOneThousand(int number)
	{
		String soFar;
		if(number % 100 < 20)
		{
			soFar = numNames[number % 100];
			number /= 100;
		} else
		{
			soFar = numNames[number % 10];
			number /= 10;
			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if(number == 0)
			return soFar;
		else
			return numNames[number] + " Hundred" + soFar;
	}
	
	public String numbersToChar(String obj)
	{
		String m_value = "";
		String m_cents = "00";
		String numberinWords = "";
		int num = 0;
		int m_dot = -1;
		int m_length = 0;
		long decimal_amount = 0L;
		int cent = 0;
		m_length = obj.length();
		m_value = obj.toString();
		m_dot = obj.indexOf(".");
		if(m_dot != -1)
		{
			m_cents = obj.substring(m_dot + 1, m_length);
			num = m_cents.length();
			m_length = m_value.substring(0, m_dot).length();
			m_value = obj.substring(0, m_dot);
			cent = Integer.parseInt(m_cents);
		}
		decimal_amount = Long.parseLong(m_value);
		if(cent != 0)
			numberinWords = convert(decimal_amount) + " and " + LAKDL_AF_CO_conn_methods.convert(cent) + "  cents";
		else
			if(cent == 0)
				numberinWords = convert(decimal_amount);
		return numberinWords;
	}
	
	public Connection met_user_validate(HttpServletRequest in_req)
	{
		try
		{
			System.out.println("Request ====> "+in_req.toString());
			String rights = "norights";
			int count = 0;
			username = "Undefined";
			rights = in_req.getHeader("authorization");
			String bufferString = in_req.getHeader("authorization").substring(6, in_req.getHeader("authorization").length());
			BASE64Decoder base64 = new BASE64Decoder();
			byte mydata[] = base64.decodeBuffer(bufferString);
			String mydatastr = new String(mydata);
			int colpos = mydatastr.indexOf(":");
			username = (new String(mydata)).substring(0, colpos).toUpperCase();
			int colpos1 = mydatastr.length();
			password = (new String(mydata)).substring(colpos + 1, colpos1).toUpperCase();
			if(username == "")
				username = "Undefined";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// out_conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB", "OFSCLALL", "ALL123");
			out_conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB", "LAKDL", "EIGHTITENGPW82");
		}
		catch(Exception e) { }
		return out_conn;
	}
	
	public Connection met_user_validate_jsp(){//added new connection on 08-04-2014
		try{
			if(username != null &&(!username.equals("") || !username.equals("Undefined"))){
				Class.forName("oracle.jdbc.driver.OracleDriver");
				out_conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB", "LAKDL", "EIGHTITENGPW82");
				return out_conn;
			}
			return null;
		}catch(Exception ex){
			return null;
		}
		
		
	}
	
	Connection out_conn;
	String html_client_url;
	String servlet_client_url;
	String client_name;
	String client_t3_port;
	String m_jsp_clent_url;
	public String username;
	public String password;
	public String schema_name;
	String company_name;
	String bg_color1;
	String bg_color2;
	String fo_color1;
	String fo_color2;
	String header_name;
	private static final String tensNames[] = {
		"", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"
	};
	private static final String numNames[] = {
		"", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", 
		" Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"
	};
	
}