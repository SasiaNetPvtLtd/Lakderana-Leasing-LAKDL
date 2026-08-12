// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import oracle.jdbc.driver.*;
import java.sql.*;
import sun.misc.BASE64Decoder;
import java.text.DecimalFormat;


public class LAKDL_print_methods{
	
	Connection out_conn;
	
	String html_client_url = "http://www.lakdl-netasset.lk";
	String servlet_client_url = "http://www.lakdl-netasset.lk";
	String client_name="LAKDL_";
	String client_t3_port="/myserver/servlet";
	public String username;
	public String password;
	String schema_name="LAKDL";
	String company_name="";
	String bg_color1="#A3B2CC";
	String bg_color2="white";
	String fo_color1="black";
	String fo_color2="black";
	String header_name="Asset Financing System";
	
	/* Following method will read posted valued from a html form */
	public String met_formdata(String reqstr,String objectname) {
		
		String curr_str = "";
		try {
			if (reqstr.indexOf(objectname)>-1) {
				int m_lenobject=0;
				int m_reqpos=0;			
				int m_curr_pos=0;
				if (reqstr.indexOf(objectname)==0){
					m_lenobject = (objectname.length());
					m_reqpos = reqstr.indexOf(objectname+"=")+m_lenobject+1;
					m_curr_pos = m_reqpos;
				} 
				else{
					m_lenobject = (objectname.length()+1);
					
					if (reqstr.indexOf("&"+objectname+"=")!=-1){
						//m_reqpos = reqstr.indexOf("&"+objectname)+m_lenobject+1; 
						m_reqpos = reqstr.indexOf("&"+objectname+"=")+m_lenobject+1; 
						m_curr_pos = m_reqpos;
					}else{
						m_reqpos=reqstr.length();					
						m_curr_pos = m_reqpos; 	
					}
				}
				
				while (m_curr_pos<=reqstr.length()-1 && (reqstr.substring(m_curr_pos,m_curr_pos+1)).compareTo("&")!=0) {
					curr_str = curr_str + reqstr.substring(m_curr_pos,m_curr_pos+1);
					m_curr_pos = m_curr_pos+1;
				}
				
				/*Replace all + chars with spaces as when spaces are sent it gets convered to +*/
				curr_str = curr_str.replace('+',' ');
				/*Routine to check and replace escape characters with the coresponding correct charachers*/
				Vector escape_str  = new Vector();
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
				//escape_str.addElement("%09");
				Vector correct_str  = new Vector();
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
				char dubchar[] = {'"'};
				String m_dubstr=new String(dubchar);
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
				//correct_str.addElement("");
				int i=0;
				int pos,escape_pos;
				String chk_str,rep_str;
				while (i<curr_str.length()) {
					if ((curr_str.substring(i,i+1)).equals("%")) {
						chk_str=curr_str.substring(i,i+3);
						escape_pos=escape_str.indexOf(chk_str);
						if (escape_pos>=0) {
							rep_str=(String)correct_str.elementAt(escape_pos);
							curr_str=curr_str.substring(0,i)+rep_str+curr_str.substring(i+3);	
						}
					}    
					i=i+1;
				}
			}//------
		}
		
		catch (Exception ex) {
			
			System.out.println(ex.toString());	
		} 
		
		return curr_str;
		
	}
	
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
	
	public String XPosition(int x){
		int i;
		String m_space="";
		for(i=0; i<x; i++){
			m_space=m_space+" ";
		}
		return m_space;
	}	
	
	
	public String YPosition(int y){
		int i;
		String m_space="";
		for(i=0; i<y; i++) {
			m_space=m_space+"\n";
		}
		return m_space;
	}	
	
	public String Add_Space(int count1)	{
		int i;
		String m_space="";
		for(i=0; i<count1; i++)
		{
			m_space=m_space+" ";
		}
		return m_space; 
	}
	
	public static String get_number_from_percentage(String obj){
		
		Object[] ar_ones  = new Object[10];
		Object[] ar_tens  = new Object[10];
		Object[] ar_teens = new Object[10];
		
		ar_ones[0]        = "Zero";
		ar_ones[1]        = "One";
		ar_ones[2]        = "Two"; 
		ar_ones[3]		  =	"Three";
		ar_ones[4]        = "Four";
		ar_ones[5]        = "Five";
		ar_ones[6]        = "Six"; 
		ar_ones[7]		  =	"Seven";
		ar_ones[8]        = "Eight";
		ar_ones[9]        = "Nine";
		
		ar_tens[0]        = "";
		ar_tens[1]        = "Ten";
		ar_tens[2]        = "Twenty"; 
		ar_tens[3]		  =	"Thirty";
		ar_tens[4]        = "Forty";
		ar_tens[5]        = "Fifty";
		ar_tens[6]        = "Sixty"; 
		ar_tens[7]		  =	"Seventy";
		ar_tens[8]        = "Eighty";
		ar_tens[9]        = "Ninety";
		
		ar_teens[0]        = "";
		ar_teens[1]        = "Eleven";
		ar_teens[2]        = "Twelve"; 
		ar_teens[3]		   = "Thirteen";
		ar_teens[4]        = "Fourteen";
		ar_teens[5]        = "Fifteen";
		ar_teens[6]        = "Sixteen"; 
		ar_teens[7]		   = "Seventeen";
		ar_teens[8]        = "Eighteen";
		ar_teens[9]        = "Nineteen";
		
		String m_value;
		String m_cents="";
		
		String m_full_str="";
		String m_digit="0";
		int m_length=obj.length();
		m_value=obj.toString();
		int m_dot=obj.indexOf(".");
		if (m_dot!= -1 ) {
			m_cents=obj.substring(m_dot+1,m_length);
			int num = m_cents.length();
			if (num==1) {
				m_cents=m_cents;//+"0";
			}
			else if (m_cents.length()==2) {
				m_cents=m_cents;//+"00";
			}
			m_length=m_value.substring(0,m_dot).length();
			m_value=obj.substring(0,m_dot);
		}
		
		
		for (int j=1 ; j<= 15-m_length; j++) {
			m_value="0"+m_value;
		}
		int m_cnt=15-m_length;
		for (int i=m_cnt; i<=14; i++) {
			
			m_digit = m_value.substring(m_cnt,m_cnt+1);
			
			if ((m_cnt)==3) {  // 100,000,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Billion ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if (m_cnt==4) {  // 10,000,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Billion ";
						m_cnt+=1;
						i+=1;
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Billion ";
					}
					
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if (m_cnt==5) {  // 1,000,000,000
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Billion ";
				}
			}
			
			
			else if ((m_cnt)==6) {  // 100,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Million ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if (m_cnt==7) {  // 10,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Million ";
						m_cnt+=1;
						i+=1;
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Million ";
						m_cnt+=1;
						i+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==8) {  // 1,000,000
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Million ";
				}
			}
			
			else if ((m_cnt)==9) {  // 100,000
				if (!m_digit.equals("0")) {
					if ((Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (Integer.parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Thousand ";
						m_cnt+=2;
						i+=2;
					}
					else {						
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if ((m_cnt)==10) {  // 10,000
				if (!m_digit.equals("0")) {
					if (Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Thousand ";
						m_cnt+=1;
						i+=1;
						//m_cnt+=2;
					}
					else if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Thousand ";
						m_cnt+=1;
						i+=1;
					}
					else {						
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==11) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Thousand ";
				}
			}
			
			else if ((m_cnt)==12) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
				}
			}
			
			else if ((m_cnt)==13) {  
				if (!m_digit.equals("0")) {
					if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) {
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" ";
						m_cnt+=1;
						i+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==14) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" ";
				}
			}
			
			m_cnt++;
		}
		
		
		m_cnt=0;
		
		
		//System.out.println(m_cnt+" "+m_cents);
		if (!m_cents.equals("")){
			
			if(m_full_str.equals("")){
				m_full_str=m_full_str+"Zero point ";
			}
			else{
				m_full_str=m_full_str+" point ";
			}
			
			for (int i=m_cnt; i<=1; i++) {			
				m_digit = m_cents.substring(m_cnt,m_cnt+1);
				//System.out.println("m_digit="+m_digit+" i="+i+" m_cnt="+m_cnt );
				if ((m_cnt)==0) {  // 1
					System.out.println("ar_ones[Integer.parseInt("+m_digit+")]="+ar_ones[Integer.parseInt(m_digit)]);
					m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)];
					/*if (!m_digit.equals("0")) {
					if ((m_digit.equals("1")) && (!m_cents.substring(m_cnt+1,m_cnt+2).equals("0"))) {
					if(!m_full_str.toString().equals(""))	{
					if(!m_full_str.equals("")){
					m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+" ";
					}
					else{
					m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+" ";
					}
					}
					else{
					m_full_str=ar_ones[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+" ";
					}
					m_cnt+=1;
					}
					else {
					if(!m_full_str.equals("")){
					m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)]+" ";
					}
					else{
					m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)]+" ";
					}
					}
					}*/
				}
				else if ((m_cnt)==1) {  // 2
					if (!m_digit.equals("0")) {
						if (m_cents.substring(m_cnt-1,m_cnt).equals("0")) {
							if(!m_full_str.toString().equals("")){
								m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)]+" ";						
							}
							else{
								m_full_str=ar_ones[Integer.parseInt(m_digit)]+" "+ar_ones[Integer.parseInt(m_digit)]+" ";				
							}
						}
						else {
							m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)]+" ";	
						}
					}
				}
				
				m_cnt++;
				
				if (m_cnt>1){
					m_full_str=m_full_str +" ";
				}
				
			}
		}
		else{
			m_full_str=m_full_str+" point zero ";
		}
		
		return m_full_str.toString();
		
	}
	
	
	/* Added by cs on 16/09/2011*/
	
	
	private static final String[] tensNames = {
		"",
		" Ten",
		" Twenty",
		" Thirty",
		" Forty",
		" Fifty",
		" Sixty",
		" Seventy",
		" Eighty",
		" Ninety"
	};
	private static final String[] numNames = {
		"",
		" One",
		" Two",
		" Three",
		" Four",
		" Five",
		" Six",
		" Seven",
		" Eight",
		" Nine",
		" Ten",
		" Eleven",
		" Twelve",
		" Thirteen",
		" Fourteen",
		" Fifteen",
		" Sixteen",
		" Seventeen",
		" Eighteen",
		" Nineteen"
	};
	
	public static String convert(long number) {
		
		
		// 0 to 999 999 999 999
		if (number == 0) {
			return "Zero";
		}
		
		String snumber = Long.toString(number);
		
		
		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);
		
		// XXXnnnnnnnnn
		int billions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnnnnn
		int millions = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXXnnn
		int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
		// nnnnnnnnnXXX
		int thousands = Integer.parseInt(snumber.substring(9, 12));
		
		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(billions) + " Billion ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(billions) + " Billion ";
		}
		String result = tradBillions;
		
		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + " Million ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + " Million ";
		}
		result = result + tradMillions;
		
		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "One Thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " Thousand ";
		}
		result = result + tradHundredThousands;
		
		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;
		
		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}
	
	private static String convertLessThanOneThousand(int number) {
		String soFar;
		
		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {
			soFar = numNames[number % 10];
			number /= 10;
			
			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0) {
			return soFar;
		}
		return numNames[number] + " Hundred" + soFar;
	}
	
	public String numbersToChar(String obj) {
		
		
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
		
		
		if (m_dot != -1) {
			
			m_cents = obj.substring(m_dot + 1, m_length);            //m_cents = obj.substring(m_dot + 1);
			num = m_cents.length();
			m_length = m_value.substring(0, m_dot).length();
			m_value = obj.substring(0, m_dot);
			cent = Integer.parseInt(m_cents);
			
			
			
		}
		
		
		decimal_amount = Long.parseLong(m_value);
		
		
		if (cent != 0) {
			numberinWords = LAKDL_print_methods.convert(decimal_amount) + " and " + LAKDL_print_methods.convert(cent) + "  cents";
			
			
		} else if (cent == 0) {
			numberinWords = LAKDL_print_methods.convert(decimal_amount);
		}
		
		return numberinWords;
	}
	
	
	/*End  16/09/2011*/
	
	public String numbersToChar_1(String obj){
		
		Object[] ar_ones  = new Object[10];
		Object[] ar_tens  = new Object[10];
		Object[] ar_teens = new Object[10];
		
		ar_ones[0]        = "";
		ar_ones[1]        = "One";
		ar_ones[2]        = "Two"; 
		ar_ones[3]		  =	"Three";
		ar_ones[4]        = "Four";
		ar_ones[5]        = "Five";
		ar_ones[6]        = "Six"; 
		ar_ones[7]		  =	"Seven";
		ar_ones[8]        = "Eight";
		ar_ones[9]        = "Nine";
		
		ar_tens[0]        = "";
		ar_tens[1]        = "Ten";
		ar_tens[2]        = "Twenty"; 
		ar_tens[3]		  =	"Thirty";
		ar_tens[4]        = "Forty";
		ar_tens[5]        = "Fifty";
		ar_tens[6]        = "Sixty"; 
		ar_tens[7]		  =	"Seventy";
		ar_tens[8]        = "Eighty";
		ar_tens[9]        = "Ninety";
		
		ar_teens[0]        = "";
		ar_teens[1]        = "Eleven";
		ar_teens[2]        = "Twelve"; 
		ar_teens[3]		   = "Thirteen";
		ar_teens[4]        = "Fourteen";
		ar_teens[5]        = "Fifteen";
		ar_teens[6]        = "Sixteen"; 
		ar_teens[7]		   = "Seventeen";
		ar_teens[8]        = "Eighteen";
		ar_teens[9]        = "Nineteen";
		
		String m_value;
		String m_cents="";
		
		String m_full_str="";
		String m_digit="0";
		int m_length=obj.length();
		m_value=obj.toString();
		int m_dot=obj.indexOf(".");
		if (m_dot!= -1 ) {
			m_cents=obj.substring(m_dot+1,m_length);
			int num = m_cents.length();
			if (num==1) {
				m_cents=m_cents+"0";
			}
			else if (m_cents.length()==2) {
				m_cents=m_cents+"00";
			}
			m_length=m_value.substring(0,m_dot).length();
			m_value=obj.substring(0,m_dot);
		}
		
		
		for (int j=1 ; j<= 15-m_length; j++) {
			m_value="0"+m_value;
		}
		int m_cnt=15-m_length;
		for (int i=m_cnt; i<=14; i++) {
			
			m_digit = m_value.substring(m_cnt,m_cnt+1);
			//System.out.println("m_digit="+m_digit);
			if ((m_cnt)==3) {  // 100,000,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Billion ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if (m_cnt==4) {  // 10,000,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Billion ";
						m_cnt+=1;
						i+=1;
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Billion ";
					}
					
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if (m_cnt==5) {  // 1,000,000,000
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Billion ";
				}
			}
			
			
			else if ((m_cnt)==6) {  // 100,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Million ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if (m_cnt==7) {  // 10,000,000
				//System.out.println("m_value="+m_value+" m_value.substring(m_cnt,m_cnt+1)="+m_value.substring(m_cnt,m_cnt+1)+"  m_value.substring(m_cnt+1,m_cnt+2)="+m_value.substring(m_cnt+1,m_cnt+2));
				if (!m_digit.equals("0")) {
					//if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Million ";
						m_cnt+=1;
						i+=1;
						//System.out.println("m_full_str="+m_full_str);
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Million ";
						m_cnt+=1;
						i+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==8) {  // 1,000,000
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Million ";
				}
			}
			
			else if ((m_cnt)==9) {  // 100,000
				if (!m_digit.equals("0")) {
					if ((Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (Integer.parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Thousand ";
						m_cnt+=2;
						i+=2;
					}
					else {						
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if ((m_cnt)==10) {  // 10,000
				if (!m_digit.equals("0")) {
					if (Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Thousand ";
						m_cnt+=1;
						i+=1;
						//m_cnt+=2;
					}
					else if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Thousand ";
						m_cnt+=1;
						i+=1;
					}
					else {						
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==11) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Thousand ";
				}
			}
			
			else if ((m_cnt)==12) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
				}
			}
			
			else if ((m_cnt)==13) {  
				if (!m_digit.equals("0")) {
					if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) {
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" ";
						m_cnt+=1;
						i+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==14) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" ";
				}
			}
			
			m_cnt++;
		}
		
		
		m_cnt=0;
		if (!m_cents.equals("")){
			for (int i=m_cnt; i<=1; i++) {			
				m_digit = m_cents.substring(m_cnt,m_cnt+1);
				if ((m_cnt)==0) {  // 10
					if (!m_digit.equals("0")) {
						if ((m_digit.equals("1")) && (!m_cents.substring(m_cnt+1,m_cnt+2).equals("0"))) {
							if(!m_full_str.toString().equals(""))
							{
								m_full_str=m_full_str+"and "+ar_teens[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+"";
							}
							else 
							{
								m_full_str=ar_teens[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+"";
							}
							m_cnt+=1;
						}
						else {
							m_full_str=m_full_str+"and "+ar_tens[Integer.parseInt(m_digit)]+"";
						}
					}
				}
				
				else if ((m_cnt)==1) {  // 1
					if (!m_digit.equals("0")) {
						if (m_cents.substring(m_cnt-1,m_cnt).equals("0")) {
							if(!m_full_str.toString().equals(""))
							{
								m_full_str=m_full_str+"and "+ar_ones[Integer.parseInt(m_digit)]+" cents ";						
							}
							else
							{
								m_full_str=ar_ones[Integer.parseInt(m_digit)]+" cents ";		
							}
						}
						else {
							m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" cents ";	
						}
					}
				}
				
				
				m_cnt++;
				if (m_cnt>1){
					//System.out.println("m_cnt+"+m_cnt);
					//m_full_str=m_full_str +" cents ";
				}
				
			}
		}
		
		return m_full_str.toString();
	}
	
	
	
	
	
	
	
	
	
	public Connection get_print_connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//out_conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.100.23:1521:OFSCL","OFSCL", "OFSCL123");
			//out_conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.200.10:1521:DEMODB","OFSCLALL","ALL123");
			//out_conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:DEALNET","OFSCLALL","ALL123"); 
			//out_conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB","LAKDL","EIGHTITENGPW82"); //divelopment
			
			//out_conn = DriverManager.getConnection("jdbc:oracle:thin:@172.20.1.117:1521:LAKDB", "LAKDL", "SNORA7623ADMIN"); // commented by udara 06-11-2015
			out_conn = DriverManager.getConnection("jdbc:oracle:thin:@DBSVR01:1521:LAKDB", "LAKDL", "SNORA7623ADMIN"); // added by udara 06-11-2015
			
			
			//out_conn = DriverManager.getConnection("jdbc:oracle:thin:@NetAsset:1521:LAKTESTDB", "LAKDL", "EIGHTITENGPW82"); //client test
			
		}	
		catch (Exception e) {
			try {
				System.out.println(e.toString());
			}
			catch (Exception e1) {
				System.out.println(e1.toString());
			}
		}
		return out_conn;		
	}
	
}




