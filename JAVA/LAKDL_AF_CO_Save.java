//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Save
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_CO_Save extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
  CallableStatement callstmt1 =null,callstmt2=null;
	BufferedReader input        =null;
  String m_username           =null;
	String m_chksql,m_msg,m_url,m_scr_name,m_schema_name,m_pricing_no=null;
	String reqstr;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	
	//ResultSet rs=null;
	//PreparedStatement pstmt = null;
	//Statement stmt=null;
	//File file1=null;
	//PrintStream out=null;
	//String str_active;
	//int str_sql_opt;
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
		try {
			//stmt = conn.createStatement ();
			//out = new PrintStream(res.getOutputStream());
			
			out    = res.getOutputStream();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn=m_sn_methods.met_user_validate(req);
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
      m_username 						= m_sn_methods.username;
			m_html_client_url 		= m_sn_methods.html_client_url;
			m_servlet_client_url	= m_sn_methods.servlet_client_url;
			m_client_t3_port			= m_sn_methods.client_t3_port; 
			m_schema_name					= m_sn_methods.schema_name.trim();
			String m_client_name  = m_sn_methods.client_name;
			
			input  = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();
			//out.println(reqstr);

			conn.setAutoCommit(false);
			//out.println("t2");
			
			m_scr_name = 	m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
			// Modified by Thamali Jayatunga on 2009.10.19, Added m_msg = "'Information saved successfully'";
			m_msg = "'Information saved successfully'";
			//out.println("sdsddfdf---"+m_scr_name);
			//---------------------------------------------------------------------------------------------------			
			
			if (m_scr_name.trim().equals("AF_RECEIPT_ALLO_UNALLO")){
			//out.println("t3"+conn);
			  
				 synchronized (this){
				 m_url = "AF_CO_ReceiptAllocation?chksql=main_page";
				 callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					           "AF_CO_SAVE_RECEIP_ALL_UNALLO(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END;");

					/*callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					           "AF_RE_SAVE_CONTRACT_REC_BAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
          */
				  //out.println("t&&"+m_sn_methods.met_formdata(reqstr,"OPTION_DESC")+"&&");
			   callstmt1.registerOutParameter(12,java.sql.Types.CHAR);
			   String m_allo_no= "";
				 double m_amount=0;
					
					 
				   for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
			    
					 for(int j=0;j<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_invoice_count_"+(Integer.toString(i))));j++){
					 
					 //out.println(i+"===**"+m_sn_methods.met_formdata(reqstr,"Text_standard"+i+"_"+j)+"**=="+j);
			    
					 if(m_sn_methods.met_formdata(reqstr,"Text_standard"+i+"_"+j).equals("YES")){
						
						/*
						//added by nuwan de silva on 12-06-2008-------------------------------------------
						if(m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase().equals("DELETE")){
					  callstmt2.setString(1 ,m_sn_methods.met_formdata(reqstr,"REC_NO_"+(Integer.toString(i))).toUpperCase());
						callstmt2.setString(2 ,m_sn_methods.met_formdata(reqstr,"HID_FINANCE_NO_"+i).toUpperCase());
						callstmt2.setString(3 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REC_AMOUNT_"+i).toUpperCase()));
						callstmt2.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+i+"_"+j).toUpperCase()));
						callstmt2.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+i+"_"+j).toUpperCase()));
						callstmt2.setString(6 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+i+"_"+j).toUpperCase()));
						callstmt2.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
						callstmt2.setString(8,m_username.toUpperCase());
						callstmt2.setString(9 ,"");
						callstmt2.setString(10,"");
						callstmt2.execute();
						}
            */
						
						callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"REC_NO_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"INV_NO_"+i+"_"+j).toUpperCase());
						callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"V_DATE_"+i+"_"+j).toUpperCase());
						callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REC_AMOUNT_"+i).toUpperCase()));
						callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"INV_AM_"+i+"_"+j).toUpperCase()));
						callstmt1.setString(6 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+i+"_"+j).toUpperCase()));
						callstmt1.setString(7 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+i+"_"+j).toUpperCase()));
						callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
						callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
						callstmt1.setString(10,m_username.toUpperCase());
						callstmt1.setString(11,"");
						
					  if(m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase().equals("DELETE") ) {
						   m_allo_no = m_sn_methods.met_formdata(reqstr,"ALLO_NO_"+i+"_"+j);
						}else if(m_sn_methods.met_formdata(reqstr,"ALLO_NO_"+i+"_"+j)!=null && (!m_sn_methods.met_formdata(reqstr,"ALLO_NO_"+i+"_"+j).equals(""))){
						   m_allo_no = m_sn_methods.met_formdata(reqstr,"ALLO_NO_"+i+"_"+j);
						}
						callstmt1.setString(12,m_allo_no.toUpperCase());
						callstmt1.setString(13 ,m_sn_methods.met_formdata(reqstr,"hid_contract_no_"+i+"_"+j).toUpperCase());
						/*	
					  if(m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase().equals("DELETE") &&
						   m_sn_methods.met_formdata(reqstr,"Text_standard_"+i+"_"+j).equals("NO")) {
						  
					  }else if(m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase().equals("DELETE") && 
						        m_sn_methods.met_formdata(reqstr,"Text_standard_"+i+"_"+j).equals("YES")) {
					    m_allo_no = m_sn_methods.met_formdata(reqstr,"ALLO_NO_"+i+"_"+j);
							//out.println("m_allo_no="+m_allo_no);
					    callstmt1.setString(12,m_allo_no.toUpperCase());
							callstmt1.execute();
					  }else if(!m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase().equals("DELETE")){
						 if(m_allo_no.equals("")){
								callstmt1.registerOutParameter(12,java.sql.Types.CHAR);
						 }else{	
							  callstmt1.setString(12,m_allo_no.toUpperCase());
						 }
					   } 
						*/
						  callstmt1.execute();
							
							if (m_allo_no==null){	
						    m_allo_no = callstmt1.getString(12);
					    }else if(m_allo_no.equals("")){	
						    m_allo_no = callstmt1.getString(12);
					    }
					   	
					 }	
					 	m_msg = "'"+m_allo_no+"- Receipt Allocation Saved Successfully'";
					
					}
				 }
					
					//}	
			    conn.commit();
				}//synchronised
		 }//if
		//------------------added by Sh on 04-08-2008---------------------------- 
		
			else if (m_scr_name.trim().equals("AUTO_ALLOCATION_INV")){
			//out.println("t3"+conn);
			  
				 synchronized (this){
				 m_url = "AF_CO_ReceiptAllocation?chksql=main_page";
				 callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					           "AF_CO_RECEIPT_ALLO_TAKEON_N(:1,:2,:3);END;");

				 //out.println("t&&"+m_sn_methods.met_formdata(reqstr,"OPTION_DESC")+"&&");
			   String m_allo_no= "";
				 double m_amount=0;
				  
				 callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_SCREEN_NAME").toUpperCase());
				 callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
				 callstmt1.setString(3,m_username.toUpperCase());
				 callstmt1.execute();
         
				 m_msg = "'Receipt Allocation Saved Successfully'";
					
				 conn.commit();
				}//synchronised
		 }//if
			
		//-----------------------------end--------------------------------------- 	
		
		//------------------added by Sh on 18-10-2011---------------------------- 
		
			else if (m_scr_name.trim().equals("AUTO_ALLOCATION")){
			//out.println("t3"+conn);
			  
				 synchronized (this){
				 m_url = "AF_CR_TerminationCheck?chksql=main_page";
				 callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					           //"AF_CO_RECEIPT_ALLO_AUTO_FN(:1,:2,:3,:4);END;");  // commented by udara 17-09-2018
								//"AF_CO_RECEIPT_ALLO_AUTO_FN_2(:1,:2,:3,:4);END;"); // added by udara 17-09-2018
								"AF_CO_RECEIPT_ALLO_FIN(:1,:2,:3,:4);END;"); // added by udara 26-03-2019

				 //out.println("t&&"+m_sn_methods.met_formdata(reqstr,"OPTION_DESC")+"&&");
			   String m_allo_no= "";
				 double m_amount=0;
				  
				 callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"hid_fin_no").toUpperCase());
				 callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_SCREEN_NAME").toUpperCase());
				 callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
				 callstmt1.setString(4,m_username.toUpperCase());
				 callstmt1.execute();
         
				 m_msg = "'Receipt Allocation Saved Successfully'";
					
				 conn.commit();
				}//synchronised
		 }//if
			
		//-----------------------------end--------------------------------------- 	
		
      else if (m_scr_name.trim().equals("AF_TERMINATION_ALLO_UNALLO")){
			//out.println("t3"+conn);
			  
				synchronized (this){
					m_url = "AF_CO_Ter_ReceiptAllocation?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_CO_SAVE_TER_RECEIP_ALLO_UN(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END;");
				  //out.println("t&&"+m_sn_methods.met_formdata(reqstr,"OPTION_DESC")+"&&");
			    callstmt1.registerOutParameter(13,java.sql.Types.CHAR);
					String m_allo_no = "";
					String m_invo_no = "";
					
			    for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
					m_allo_no = m_sn_methods.met_formdata(reqstr,"ALLO_NO_"+i);
					
					for(int j=0;j<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_invoice_count_"+(Integer.toString(i))));j++){
					 
					//out.println(i+"===t**"+m_sn_methods.met_formdata(reqstr,"Text_standard"+i+"_"+j)+"**=="+j);
			    
					if(m_sn_methods.met_formdata(reqstr,"Text_standard"+i+"_"+j).equals("YES")){
					callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"REC_NO_"+i+"_"+j).toUpperCase());
					callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"TER_NO_"+i).toUpperCase());
					if(m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase().equals("DELETE")){
					  m_allo_no = m_sn_methods.met_formdata(reqstr,"ALLO_NO_"+i+"_"+j);
						m_invo_no = m_sn_methods.met_formdata(reqstr,"INV_NO_"+i+"_"+j).toUpperCase();
					}else{
					  m_invo_no = m_sn_methods.met_formdata(reqstr,"INV_NO"+i).toUpperCase();
					}
					//out.println("m_invo_no=="+m_invo_no);
			    
					callstmt1.setString(3 ,m_invo_no);
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"V_DATE_"+i).toUpperCase());
					callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REC_AMOUNT_"+i+"_"+j).toUpperCase()));
					callstmt1.setString(6 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"INV_AM_"+i).toUpperCase()));
					callstmt1.setString(7 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"SETT_AMOUN_"+i+"_"+j).toUpperCase()));
					callstmt1.setString(8 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"SETT_AMOUN_"+i+"_"+j).toUpperCase()));
					callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
					callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
					callstmt1.setString(11,m_username.toUpperCase());
					callstmt1.setString(12,"");
					
					callstmt1.setString(13,m_allo_no.toUpperCase());
					//out.println()
					callstmt1.execute();
 							
						if (m_allo_no.equals("")){	
					    m_allo_no = callstmt1.getString(13);
				    }
	
					 	m_msg = "'"+m_allo_no+"- Termination Saved Successfully'";
					}
					}
					}
					
					//}	
			    conn.commit();
				}//synchronised
		 }//if
  		//---------------------------------------------------------------------------------------------------			
			
			if (m_scr_name.trim().equals("AF_CO_DAYEND")){
			out.println("t3"+conn);
			  
				synchronized (this){
					m_msg = "'Day End Routine Saved Successfully'";
					m_url = "AF_CO_DayEnd_Routine?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_CO_DAYEND(:1,:2,:3,:4,:5);END;");
				  out.println("t4");
			
					/*if(m_sn_methods.met_formdata(reqstr,"INQ_NO")==null){
						callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					}else{	
					  callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"INQ_NO").toUpperCase());
					}*/	
					
					String from_date = m_sn_methods.met_formdata(reqstr,"FROM_DAY")+"-"+
					                   m_sn_methods.met_formdata(reqstr,"FROM_MONTH")+"-"+
														 m_sn_methods.met_formdata(reqstr,"FROM_YEAR");
															
															
					callstmt1.setString(1 ,from_date);
					callstmt1.setString(2 ,"");
					callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
					callstmt1.setString(5 ,m_username);
 				  callstmt1.execute();
			    conn.commit();
				}//synchronised
		 }//if
			//------------------------------------------------------------------------------------------------------
			//------------------------------------------------------------------------------------------------------
			
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

