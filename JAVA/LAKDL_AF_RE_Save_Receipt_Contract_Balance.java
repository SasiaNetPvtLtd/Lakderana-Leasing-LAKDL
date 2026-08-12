//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Save
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_RE_Save_Receipt_Contract_Balance extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
	CallableStatement callstmt1 =null;
	BufferedReader input        =null;
	String m_username           =null;
	String m_chksql,m_msg,m_url,m_scr_name,m_schema_name,m_pricing_no=null;
	String reqstr;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
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
			
			out.println("test 1");
			
			if (m_scr_name.trim().equals("AF_RE_SETTLE_REC_APP_BALANCE")){
				
				out.println("test 2");
				
				synchronized (this){
					
					out.println("test 3");	
					
					m_url = "AF_RE_Settle_Receipt_App_Bal?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_RE_SAVE_CONTRACT_REC_BAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
					
					out.println("test 4");
					
					out.println(m_sn_methods.met_formdata(reqstr,"hid_count"));
					
					out.println("test 5");
					
					
					for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
						for(int j=0;j<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_receipt_contract_"+(Integer.toString(i))));j++){
							if(m_sn_methods.met_formdata(reqstr,"Text_standard"+i+"_"+j).equals("YES")){
								callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"REC_NO_"+(Integer.toString(i))).toUpperCase());
								callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"hid_finance_no"+i+"_"+j).toUpperCase());
								callstmt1.setString(3 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REC_AMOUNT_"+i).toUpperCase()));
								//callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"INV_AM_"+i+"_"+j).toUpperCase()));
								callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+i+"_"+j).toUpperCase()));
								callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+i+"_"+j).toUpperCase()));
								callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
								callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
								callstmt1.setString(8,m_username.toUpperCase());
								callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
								//out.println("fin_no"+m_sn_methods.met_formdata(reqstr,"FINANCE_NO_"+i).toUpperCase());
								if(m_sn_methods.met_formdata(reqstr,"hid_allocate_status").equals("ALLOCATE")){
									callstmt1.setString(10,"");
								}
								else{
									
									callstmt1.setString(10 ,m_sn_methods.met_formdata(reqstr,"FINANCE_NO_"+i).toUpperCase());
									
								}
								
								
								callstmt1.execute();
							}	
							m_msg = "'Receipt Allocation Saved Successfully'";
							
						}
					}
					conn.commit();
				}
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
			}
			
		}
		catch (Exception E) {
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			//out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			try{conn.rollback();}catch(Exception e){}
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

