//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Save
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_FA_MK_Save extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
  CallableStatement callstmt1 =null;
	BufferedReader input        =null;
  String m_username           =null;
	String m_chksql,m_msg,m_url,m_url1,m_scr_name,m_schema_name,m_pricing_no=null;
	String reqstr;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException	{
		try {
			
			out    = res.getOutputStream();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn=m_sn_methods.met_user_validate(req);
			
      m_username 						= m_sn_methods.username;
			m_html_client_url 		= m_sn_methods.html_client_url;
			m_servlet_client_url	= m_sn_methods.servlet_client_url;
			m_client_t3_port			= m_sn_methods.client_t3_port; 
			m_schema_name					= m_sn_methods.schema_name.trim();
			String m_client_name  = m_sn_methods.client_name;
			
			input  = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();
			
			conn.setAutoCommit(false);
			
			m_scr_name = 	m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
			
			if (m_scr_name.trim().equals("FA_MK_INQUIRY")){
			  
				synchronized (this){
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+".FA_MK_INQUIRY_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29);END;");
			
					if(m_sn_methods.met_formdata(reqstr,"INQ_NO").equals("")){
						callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					}
					else{	
					  callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"INQ_NO").toUpperCase());
					}	
			
					callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"INITIATION_TYPE").toUpperCase());
					callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CATEGORY").toUpperCase());
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"CLIENT_TYPE").toUpperCase());
					callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"LEAD_SOURCE_CATEGORY").toUpperCase());
					callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"LEAD_SOURCE_NAME"));
					callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"CLIENT_NAME"));
					callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"CONTACT_PERSON"));
					callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"MOBILE_NO"));
					callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"TEL_NO").toUpperCase());
					callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"ADDRESS"));
					callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"FAX_NO").toUpperCase());
					callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"ADDRESS1"));
					callstmt1.setString(14,m_sn_methods.met_formdata(reqstr,"EMAIL"));
					callstmt1.setString(15,m_sn_methods.met_formdata(reqstr,"CITY_CODE"));
					callstmt1.setString(16,m_sn_methods.met_formdata(reqstr,"ZIPCODE").toUpperCase());
					callstmt1.setString(17,m_sn_methods.met_formdata(reqstr,"OFFICER_CODE"));
					callstmt1.setString(18,m_sn_methods.met_formdata(reqstr,"SUPERVISOR_CODE").toUpperCase());
					callstmt1.setString(19,m_sn_methods.met_formdata(reqstr,"TEAM"));
					callstmt1.setString(20,m_sn_methods.met_formdata(reqstr,"COUNTRY").toUpperCase());
					callstmt1.setString(21,m_sn_methods.met_formdata(reqstr,"OPTION_NAME"));
					callstmt1.setString(22,m_sn_methods.met_formdata(reqstr,"INTRODUCER").toUpperCase());
					callstmt1.setString(23,m_sn_methods.met_formdata(reqstr,"ID_NO").toUpperCase());
					callstmt1.setString(24,m_sn_methods.met_formdata(reqstr,"TRANSACTION_CODE").toUpperCase());
					callstmt1.setString(25,m_sn_methods.met_formdata(reqstr,"TRANSACTION_SUB").toUpperCase());
					callstmt1.setString(26,m_sn_methods.met_formdata(reqstr,"CLIENT_LAST_NAME").toUpperCase());
					callstmt1.setString(27,m_sn_methods.met_formdata(reqstr,"TITLE").toUpperCase());
					callstmt1.setString(28,m_username);
 				  callstmt1.setString(29,m_scr_name);
					callstmt1.execute();
					
					if(m_sn_methods.met_formdata(reqstr,"INQ_NO").equals("")){
					 // m_msg = "'"+callstmt1.getString(1)+"- Inquiry Saved Successfully'";
						 m_msg = "'Information Saved Successfully Inquery No:"+callstmt1.getString(1)+"'";
					}
					else{	
					  //m_msg = "'"+m_sn_methods.met_formdata(reqstr,"INQ_NO").toUpperCase()+"- Inquiry Saved Successfully'";
						m_msg = "'Information Saved Successfully'";
					} 
					if(m_sn_methods.met_formdata(reqstr,"OPTION_NAME").equals("NEW")){
					  m_url1 = "FA_CO_Followup?chksql=main_page&Inquiry_no="+callstmt1.getString(1)+"";
					} 
					
					m_url = "FA_MK_Inquiry?chksql=main_page";
					

					conn.commit();
				}//synchronised
		 }//if
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
			if (m_scr_name.trim().equals("AF_MK_INQUIRY") && m_sn_methods.met_formdata(reqstr,"OPTION_NAME").equals("NEW")){
			  out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url1+"','inq','left=50,top=280,width=900,height=390');");  
			}
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
		}
		catch (Exception E) {
		  try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			//out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
	 }finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(input     !=null){try{input.close();    }catch(Exception e){}}
			if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
	    if(conn      !=null){try{conn.close();     }catch(Exception e){}}
			if(out       !=null){try{out.close();      }catch(Exception e){}}
			              
		}
	}
}

