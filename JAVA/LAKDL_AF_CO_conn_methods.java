import java.sql.DriverManager;
import sun.misc.BASE64Decoder;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Vector;
import java.sql.Connection;

// 
// Decompiled by Procyon v0.5.36
// 

public class LAKDL_AF_CO_conn_methods
{
	Connection out_conn;
	String html_client_url;
	String servlet_client_url;
	String client_name;
	String client_t3_port;
	public String username;
	public String password;
	String schema_name;
	String company_name;
	String bg_color1;
	String bg_color2;
	String fo_color1;
	String fo_color2;
	String header_name;
	private static final String[] tensNames;
	private static final String[] numNames;
	String m_jsp_clent_url;
	
	public LAKDL_AF_CO_conn_methods() {
		//[LIVE]
		this.html_client_url = "https://dev-lakdl.sasianet.com";
		this.servlet_client_url = "https://dev-lakdl.sasianet.com";
		this.client_name = "LAKDL_";
		this.client_t3_port = "/lakdllive/servlet";
		this.schema_name = "LAKDL";
		this.company_name = "";
		this.bg_color1 = "#A3B2CC";
		this.bg_color2 = "white";
		this.fo_color1 = "black";
		this.fo_color2 = "black";
		this.header_name = "Asset Financing System 2.0.26.01.08";
		//[DEV]
		/*this.html_client_url = "https://dev-lakdl.sasianet.com";
		this.servlet_client_url = "https://dev-lakdl.sasianet.com";
		this.client_name = "LAKDL_";
		this.client_t3_port = "/myserver/servlet";
		this.m_jsp_clent_url = "https://dev-lakdl.sasianet.com:/myserver/LKDASH";
		this.schema_name = "LAKDL";
		this.company_name = "";
		this.bg_color1 = "#A3B2CC";
		this.bg_color2 = "white";
		this.fo_color1 = "black";
		this.fo_color2 = "black";
		this.header_name = "Asset Financing System 2.0.26.01.08";*/
	}
	
	public String format_text_area_string(final String s) {
		String s2 = "";
		int i = s.indexOf("%0D%0A");
		if (i == -1) {
			s2 = s;
		}
		for (String string = s; i != -1; i = string.indexOf("%0D%0A")) {
			s2 = (string = string.substring(0, i) + " " + string.substring(i + 6, string.length()));
		}
		return s2;
	}
	
	public String met_formdata(final String s, final String str) {
		String str2 = "";
		try {
			if (s.indexOf(str) > -1) {
				int length;
				if (s.indexOf(str) == 0) {
					length = s.indexOf(str + "=") + str.length() + 1;
				}
				else {
					final int n = str.length() + 1;
					if (s.indexOf("&" + str + "=") != -1) {
						length = s.indexOf("&" + str + "=") + n + 1;
					}
					else {
						length = s.length();
					}
				}
				while (length <= s.length() - 1 && s.substring(length, length + 1).compareTo("&") != 0) {
					str2 += s.substring(length, length + 1);
					++length;
				}
				str2 = str2.replace('+', ' ');
				final Vector<String> vector = new Vector<String>();
				vector.addElement("%7E");
				vector.addElement("%21");
				vector.addElement("%23");
				vector.addElement("%24");
				vector.addElement("%25");
				vector.addElement("%5E");
				vector.addElement("%26");
				vector.addElement("%28");
				vector.addElement("%29");
				vector.addElement("%60");
				vector.addElement("%3D");
				vector.addElement("%7B");
				vector.addElement("%7D");
				vector.addElement("%7C");
				vector.addElement("%5B");
				vector.addElement("%5D");
				vector.addElement("%3A");
				vector.addElement("%22");
				vector.addElement("%3B");
				vector.addElement("%27");
				vector.addElement("%3C");
				vector.addElement("%3E");
				vector.addElement("%3F");
				vector.addElement("%2C");
				vector.addElement("%20");
				vector.addElement("%2F");
				vector.addElement("%5C");
				vector.addElement("%2B");
				vector.addElement("%92");
				vector.addElement("%96");
				final Vector<String> vector2 = new Vector<String>();
				vector2.addElement("~");
				vector2.addElement("!");
				vector2.addElement("#");
				vector2.addElement("$");
				vector2.addElement("%");
				vector2.addElement("^");
				vector2.addElement("&");
				vector2.addElement("(");
				vector2.addElement(")");
				vector2.addElement("`");
				vector2.addElement("=");
				vector2.addElement("{");
				vector2.addElement("}");
				vector2.addElement("|");
				vector2.addElement("[");
				vector2.addElement("]");
				vector2.addElement(":");
				vector2.addElement(new String(new char[] { '\"' }));
				vector2.addElement(";");
				vector2.addElement("'");
				vector2.addElement("<");
				vector2.addElement(">");
				vector2.addElement("?");
				vector2.addElement(",");
				vector2.addElement(" ");
				vector2.addElement("/");
				vector2.addElement("/");
				vector2.addElement("+");
				vector2.addElement("'");
				vector2.addElement("-");
				for (int i = 0; i < str2.length(); ++i) {
					if (str2.substring(i, i + 1).equals("%")) {
						final int index = vector.indexOf(str2.substring(i, i + 3));
						if (index >= 0) {
							str2 = str2.substring(0, i) + vector2.elementAt(index) + str2.substring(i + 3);
						}
					}
				}
			}
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		//ADDED MILINDA FOR REPLACE SPECIAL CHARACTOR ON 15-10-2021
		str2 = str2.replace("&", "&amp;");
		str2 = str2.replace("<", "&lt;");
		str2 = str2.replace(">", "&gt;");
		char dubchar[] = {'"'};
		String m_dubstr=new String(dubchar);		
		str2 = str2.replace(m_dubstr, "&quot;");
		//[END]
		return str2;
	}
	
	//[ADDED BY MILINDA ]
	public String replace_special_charactors(String objectname)
	{
		String m_charactor = "";
		String curr_str="";
		//m_charactor = str_charactor;
		
		StringBuffer sb = new StringBuffer();
		int n = objectname.length();
		for (int i = 0; i < n; i++) {
			char c = objectname.charAt(i);
			switch (c) {
			case '<': sb.append("&lt;"); break;
			case '>': sb.append("&gt;"); break;
			case '&': sb.append("&amp;"); break;
			case '"': sb.append("&quot;"); break;
			case 'à': sb.append("&agrave;");break;
			case 'À': sb.append("&Agrave;");break;
			case 'â': sb.append("&acirc;");break;
			case 'Â': sb.append("&Acirc;");break;
			case 'ä': sb.append("&auml;");break;
			case 'Ä': sb.append("&Auml;");break;
			case 'å': sb.append("&aring;");break;
			case 'Å': sb.append("&Aring;");break;
			case 'æ': sb.append("&aelig;");break;
			case 'Æ': sb.append("&AElig;");break;
			case 'ç': sb.append("&ccedil;");break;
			case 'Ç': sb.append("&Ccedil;");break;
			case 'é': sb.append("&eacute;");break;
			case 'É': sb.append("&Eacute;");break;
			case 'è': sb.append("&egrave;");break;
			case 'È': sb.append("&Egrave;");break;
			case 'ê': sb.append("&ecirc;");break;
			case 'Ê': sb.append("&Ecirc;");break;
			case 'ë': sb.append("&euml;");break;
			case 'Ë': sb.append("&Euml;");break;
			case 'ï': sb.append("&iuml;");break;
			case 'Ï': sb.append("&Iuml;");break;
			case 'ô': sb.append("&ocirc;");break;
			case 'Ô': sb.append("&Ocirc;");break;
			case 'ö': sb.append("&ouml;");break;
			case 'Ö': sb.append("&Ouml;");break;
			case 'ø': sb.append("&oslash;");break;
			case 'Ø': sb.append("&Oslash;");break;
			case 'ß': sb.append("&szlig;");break;
			case 'ù': sb.append("&ugrave;");break;
			case 'Ù': sb.append("&Ugrave;");break;         
			case 'û': sb.append("&ucirc;");break;         
			case 'Û': sb.append("&Ucirc;");break;
			case 'ü': sb.append("&uuml;");break;
			case 'Ü': sb.append("&Uuml;");break;
			case '®': sb.append("&reg;");break;         
			case '©': sb.append("&copy;");break;   
			case '€': sb.append("&euro;"); break;
				// be carefull with this one (non-breaking whitee space)
				//case ' ': sb.append("&nbsp;");break;         
				
			default:  sb.append(c); break;
			}
		}
		return sb.toString();
		
		
		
	}
	
	public String replace_special_cha(String str_charactor)
	{
		String m_charactor = "";
		m_charactor = str_charactor;
		
		
		m_charactor = str_charactor.replace("&apos;", "'").replace("&quot;", "\"").replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&AMP;", "&").replace("&Amp;", "&").replace("&Quot;", "\"").replace("&Apos;", "'").replace("&QUOT;", "\"").replace("&APOS;", "'");
		return m_charactor;
	}
	/*
	public String met_unformat_number(String numstr) {
		
		String m_number="";
		for (int i = 0; i < numstr.length(); i++) {
			String oneChar = numstr.substring(i,i+1);
			if (!oneChar.equals(",")) {
				m_number=m_number+oneChar;
				
			}
		}
		//return nf.format(numstr);
		return m_number;
		
	}
	*/
	
	public String met_unformat_number(final String s) {
		String string = "";
		for (int i = 0; i < s.length(); ++i) {
			final String substring = s.substring(i, i + 1);
			if (!substring.equals(",")) {
				string += substring;
			}
		}
		return string;
	}
	
	public String XPosition(final int n) {
		String string = "";
		for (int i = 0; i < n; ++i) {
			string += " ";
		}
		return string;
	}
	
	public String YPosition(final int n) {
		String string = "";
		for (int i = 0; i < n; ++i) {
			string += "\n";
		}
		return string;
	}
	
	public String Add_Space(final int n) {
		String string = "";
		for (int i = 0; i < n; ++i) {
			string += " ";
		}
		return string;
	}
	
	public static String convert(final long n) {
		if (n == 0L) {
			return "Zero";
		}
		Long.toString(n);
		final String format = new DecimalFormat("000000000000").format(n);
		final int int1 = Integer.parseInt(format.substring(0, 3));
		final int int2 = Integer.parseInt(format.substring(3, 6));
		final int int3 = Integer.parseInt(format.substring(6, 9));
		final int int4 = Integer.parseInt(format.substring(9, 12));
		String s = null;
		switch (int1) {
		case 0: {
				s = "";
				break;
			}
		case 1: {
				s = convertLessThanOneThousand(int1) + " Billion ";
				break;
			}
		default: {
				s = convertLessThanOneThousand(int1) + " Billion ";
				break;
			}
		}
		final String str = s;
		String str2 = null;
		switch (int2) {
		case 0: {
				str2 = "";
				break;
			}
		case 1: {
				str2 = convertLessThanOneThousand(int2) + " Million ";
				break;
			}
		default: {
				str2 = convertLessThanOneThousand(int2) + " Million ";
				break;
			}
		}
		final String string = str + str2;
		String string2 = null;
		switch (int3) {
		case 0: {
				string2 = "";
				break;
			}
		case 1: {
				string2 = "One Thousand ";
				break;
			}
		default: {
				string2 = convertLessThanOneThousand(int3) + " Thousand ";
				break;
			}
		}
		return (string + string2 + convertLessThanOneThousand(int4)).replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}
	
	private static String convertLessThanOneThousand(int n) {
		String string;
		if (n % 100 < 20) {
			string = LAKDL_AF_CO_conn_methods.numNames[n % 100];
			n /= 100;
		}
		else {
			final String str = LAKDL_AF_CO_conn_methods.numNames[n % 10];
			n /= 10;
			string = LAKDL_AF_CO_conn_methods.tensNames[n % 10] + str;
			n /= 10;
		}
		if (n == 0) {
			return string;
		}
		return LAKDL_AF_CO_conn_methods.numNames[n] + " Hundred" + string;
	}
	
	public String numbersToChar(final String s) {
		String s2 = "";
		int int1 = 0;
		final int length = s.length();
		String s3 = s.toString();
		final int index = s.indexOf(".");
		if (index != -1) {
			final String substring = s.substring(index + 1, length);
			substring.length();
			s3.substring(0, index).length();
			s3 = s.substring(0, index);
			int1 = Integer.parseInt(substring);
			if (substring.length() == 1) {
				int1 *= 10;
			}
		}
		final long long1 = Long.parseLong(s3);
		if (int1 != 0) {
			s2 = convert(long1) + " and " + convert(int1) + "  cents";
		}
		else if (int1 == 0) {
			s2 = convert(long1);
		}
		return s2;
	}
	
	public Connection met_user_validate(final HttpServletRequest httpServletRequest) {
		try {
			this.username = "Undefined";
			httpServletRequest.getHeader("authorization");
			final byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(httpServletRequest.getHeader("authorization").substring(6, httpServletRequest.getHeader("authorization").length()));
			final String s = new String(decodeBuffer);
			final int index = s.indexOf(":");
			this.username = new String(decodeBuffer).substring(0, index).toUpperCase();
			this.password = new String(decodeBuffer).substring(index + 1, s.length());
			if (this.username == "") {
				this.username = "Undefined";
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			   this.out_conn = DriverManager.getConnection("jdbc:oracle:thin:@DBSVR01:1521:LAKDB", "LAKDL", "snora7623admin");//[LIVE]
			 //this.out_conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB", "LAKDL", "EIGHTITENGPW82");//[DEV]
		}
		catch (Exception ex) {}
		return this.out_conn;
	}
	
	static {
		tensNames = new String[] { "", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety" };
		numNames = new String[] { "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen" };
	}
}
