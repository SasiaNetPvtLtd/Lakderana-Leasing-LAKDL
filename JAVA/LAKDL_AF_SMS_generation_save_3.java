// DEVELOP BY :  DISNAKA FOR OFSCL FACTORING    DATE:2011-11-08


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.regex.*;

public class LAKDL_AF_SMS_generation_save_3 extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
	java.text.NumberFormat nf;
	
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		String m_class_url = "";
		String m_fschema_name = "";
		String m_schema_name = "";
		
		try {
			
			LAKDL_Generate_receipts_sms  receipts_sms  = new LAKDL_Generate_receipts_sms();
			receipts_sms.generateSMS(req,"udara");

		}
		catch (Exception eee) {
			eee.printStackTrace();
		}
		
		
		
		
	}
}
