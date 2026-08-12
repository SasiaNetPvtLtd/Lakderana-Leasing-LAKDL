//--
//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Vehicle_Inventory extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
 ServletOutputStream out = null;


	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
		
			//PrintStream out = new PrintStream(res.getOutputStream());
			out = res.getOutputStream();

	  //  out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();

      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			String m_screen_name="";
			String m_app_no="";
			String m_vendor_code="";
			String m_inventory_no1="";
			String m_status="";
			String m_sus_ref_no="";
	    m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      conn.setAutoCommit(false); //added by nuwan de silva on 06-11-07	

				//	conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_load_status=(String)m_sn_methods.met_formdata(reqstr,"hid_load_status"); //Added BY Sandun on 02-07-2009
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			
					
			
			       	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_VEHICLE_INV0(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39,:40,:41); END;");
								
	            callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_REPOSSESSION_CODE"));
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_SEIZER_CODE"));
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
							callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NAME"));
							callstmt.setString(5,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_ASSET_DESCRIPTION")));
							callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_NO"));
							callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_MILEGE")));
							//callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_ADVERTISMENT_STATUS"));
							//callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_OFFER_STATU"));
							callstmt.setString(8,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS")));
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_KEY_ON");
							if(m_status.equals("on")){
							callstmt.setString(9,"Y");
							}
							else{
							callstmt.setString(9,"");}
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_KEY_OFF");
							if(m_status.equals("on")){
							callstmt.setString(9,"N");
							}
												
												
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LICENSE_ON");
							if(m_status.equals("on")){
							callstmt.setString(10,"Y");
							}
							else{
							callstmt.setString(10,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LICENSE_OFF");
							if(m_status.equals("on")){
							callstmt.setString(10,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_ON");
							if(m_status.equals("on")){
							callstmt.setString(11,"Y");
							}
							else{
							callstmt.setString(11,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_OFF");
							if(m_status.equals("on")){
							callstmt.setString(11,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_ID_CARD_ON");
							if(m_status.equals("on")){
							callstmt.setString(12,"Y");
							}
							else{
							callstmt.setString(12,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_ID_CARD_OFF");
							if(m_status.equals("on")){
							callstmt.setString(12,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_CASSETTE_ON");
							if(m_status.equals("on")){
							callstmt.setString(13,"Y");
							}
							else{
							callstmt.setString(13,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_CASSETTE_OFF");
							if(m_status.equals("on")){
							callstmt.setString(13,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RADIO_ON");
							if(m_status.equals("on")){
							callstmt.setString(14,"Y");
							}
							else{
							callstmt.setString(14,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RADIO_OFF");
							if(m_status.equals("on")){
							callstmt.setString(14,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_CD_PLAYER_ON");
							if(m_status.equals("on")){
							callstmt.setString(15,"Y");
							}
							else{
							callstmt.setString(15,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_CD_PLAYER_OFF");
							if(m_status.equals("on")){
							callstmt.setString(15,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_TOOL_KIT_ON");
							if(m_status.equals("on")){
							callstmt.setString(16,"Y");
							}
							else{
							callstmt.setString(16,"");}

							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_TOOL_KIT_OFF");
							if(m_status.equals("on")){
							callstmt.setString(16,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_SPEAR_WHEEL_ON");
							if(m_status.equals("on")){
							callstmt.setString(17,"Y");
							}
							else{
							callstmt.setString(17,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_SPEAR_WHEEL_OFF");
							if(m_status.equals("on")){
							callstmt.setString(17,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_JACK_ON");
							if(m_status.equals("on")){
							callstmt.setString(18,"Y");
							}
							else{
							callstmt.setString(18,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_JACK_OFF");
							if(m_status.equals("on")){
							callstmt.setString(18,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LIGHTER_ON");
							if(m_status.equals("on")){
							callstmt.setString(19,"Y");
							}
							else{
							callstmt.setString(19,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LIGHTER_OFF");
							if(m_status.equals("on")){
							callstmt.setString(19,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_FUEL_CAP_ON");
							if(m_status.equals("on")){
							callstmt.setString(20,"Y");
							}
							else{
							callstmt.setString(20,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_FUEL_CAP_OFF");
							if(m_status.equals("on")){
							callstmt.setString(20,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_CARPETS_ON");
							if(m_status.equals("on")){
							callstmt.setString(21,"Y");
							}
							else{
							callstmt.setString(21,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_CARPETS_OFF");
							if(m_status.equals("on")){
							callstmt.setString(21,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_WHEEL_ON");
							if(m_status.equals("on")){
							callstmt.setString(22,"Y");
							}
							else{
							callstmt.setString(22,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_WHEEL_OFF");
							if(m_status.equals("on")){
							callstmt.setString(22,"N");
							}
							
							
							
							/*status=(String)m_sn_methods.met_formdata(reqstr,"TXT_BODY_ON");
							if(m_status.equals("on")){
							callstmt.setString(23,"Y");
							}
							else{
							callstmt.setString(23,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_BODY_OFF");
							if(m_status.equals("on")){
							callstmt.setString(23,"N");
							}
							*/
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_BODY_G");
							
							if(m_status.equals("Y")){
							callstmt.setString(23,"G");
							}
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_BODY_S");
							
							if(m_status.equals("Y")){
							callstmt.setString(23,"S");
							}
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_BODY_D");
							
							if(m_status.equals("Y")){
							callstmt.setString(23,"D");
							}
							
													
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_MIRROR_ON");
							if(m_status.equals("on")){
							callstmt.setString(24,"Y");
							}
							else{
							callstmt.setString(24,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_MIRROR_OFF");
							if(m_status.equals("on")){
							callstmt.setString(24,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LEFT_SIDE_MIRROR_ON");
							if(m_status.equals("on")){
							callstmt.setString(25,"Y");
							}
							else{
							callstmt.setString(25,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LEFT_SIDE_MIRROR_OFF");
							if(m_status.equals("on")){
							callstmt.setString(25,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RIGHT_SIDE_MIRROR_ON");
							if(m_status.equals("on")){
							callstmt.setString(26,"Y");
							}
							else{
							callstmt.setString(26,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RIGHT_SIDE_MIRROR_OFF");
							if(m_status.equals("on")){
							callstmt.setString(26,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LEFT_SIGNAL_LIGHT_FRONT_ON");
							if(m_status.equals("on")){
							callstmt.setString(27,"Y");
							}
							else{
							callstmt.setString(27,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LEFT_SIGNAL_LIGHT_FRONT_OFF");
							if(m_status.equals("on")){
							callstmt.setString(27,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RIGHT_SIGNAL_LIGHT_FRONT_ON");
							if(m_status.equals("on")){
							callstmt.setString(28,"Y");
							}
							else{
							callstmt.setString(28,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RIGHT_SIGNAL_LIGHT_FRONT_OFF");
							if(m_status.equals("on")){
							callstmt.setString(28,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LEFT_SIGNAL_LIGHT_REAR_ON");
							if(m_status.equals("on")){
							callstmt.setString(29,"Y");
							}
							else{
							callstmt.setString(29,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_LEFT_SIGNAL_LIGHT_REAR_OFF");
							if(m_status.equals("on")){
							callstmt.setString(29,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RIGHT_SIGNAL_LIGHT_REAR_ON");
							if(m_status.equals("on")){
							callstmt.setString(30,"Y");
							}
							else{
							callstmt.setString(30,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RIGHT_SIGNAL_LIGHT_REAR_OFF");
							if(m_status.equals("on")){
							callstmt.setString(30,"N");
							}
							
							
							/*m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_POLICE_REPORT_ON");
							if(m_status.equals("on")){
							callstmt.setString(31,"Y");
							}
							else{
							callstmt.setString(31,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_POLICE_REPORT_OFF");
							if(m_status.equals("on")){
							callstmt.setString(31,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_CUSTOMERS_SIGNATURE_ON");
							if(m_status.equals("on")){
							callstmt.setString(32,"Y");
							}
							else{
							callstmt.setString(32,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_CUSTOMERS_SIGNATURE_OFF");
							if(m_status.equals("on")){
							callstmt.setString(32,"N");
							}
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_SEIZERS_SIGNATURE_ON");
							if(m_status.equals("on")){
							callstmt.setString(33,"Y");
							}
							else{
							callstmt.setString(33,"");}

							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_SEIZERS_SIGNATURE_OFF");
							if(m_status.equals("on")){
							callstmt.setString(33,"N");
							}
							
							
							
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RECEIVERS_SIGNATURE_ON");
							if(m_status.equals("on")){
							callstmt.setString(34,"Y");
							}
							else{
							callstmt.setString(34,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_RECEIVERS_SIGNATURE_OFF");
							if(m_status.equals("on")){
							callstmt.setString(34,"N");
							}
			*/
							callstmt.setString(31,"");
							callstmt.setString(32,"");
							callstmt.setString(33,"");
							callstmt.setString(34,"");
							
							
							callstmt.setString(35,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
              callstmt.setString(36,m_username);
							callstmt.setString(37,m_scr_name);
							
							String m_inventory_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO");
     
			
			        if(m_inventory_no.equals("")){
                  callstmt.registerOutParameter(38,java.sql.Types.CHAR);	
                }
							else
							{
							callstmt.setString(38,(m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO")).trim());
							}
							
					
							
							callstmt.setString(39,m_sn_methods.met_formdata(reqstr,"TXT_YARD_CODE"));
							
							
							//-----------------------------------------------------------------------------------------------
							//--MODIFIED BY : DELANJALI----------------------------------------------------------------------
							//--DATE				: 2007-02-20---------------------------------------------------------------------
					    callstmt.setString(40 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_AMOUNT").toUpperCase()));
							
														
							//added by nuwan de silva on 06-11-07--------------------------------------------
							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_D_KEY_ON");
							if(m_status.equals("on")){
							callstmt.setString(41,"Y");
							}
							else{
							callstmt.setString(41,"");}

							m_status=(String)m_sn_methods.met_formdata(reqstr,"TXT_D_KEY_OFF");
							if(m_status.equals("on")){
							callstmt.setString(41,"N");
							}
							

							callstmt.execute();
							
							m_screen_name=(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
							
							if(m_screen_name.equals("NEW")){
							//out.println("inventory Number "+m_inventory_no1);
              m_inventory_no1 =callstmt.getString(38);
							m_msg = "'"+m_inventory_no1+ "-" +"Inventory saved successfully.'";
						
						
							}
						
					
				
				////////////////////////////////////////////////////////////////////////////////////////


							
							/*
							
							//-----------------------------------------------------------------------------------------------
							//--MODIFIED BY : DELANJALI----------------------------------------------------------------------
							//--DATE				: 2007-06-12---------------------------------------------------------------------
							//--PURPOSE			: TO INSERT A RECORD TO SUS PAYMENT----------------------------------------------
							*/
							String m_repos=m_sn_methods.met_formdata(reqstr,"TXT_REPOSSESSION_CODE");
							String m_curr_code="";
							String m_val_date="";
							double m_exg_rate=0;
							
					   rs = stmt.executeQuery ("SELECT distinct TRN_CURR_CODE,EXCHANGE_RATE, "+
							"to_char(ENT_DATE,'dd-mm-yyyy') "+
					  	"FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+ //modified by nwuan de silva on 09-10-07
					 		" WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_repos+"')");
	
						boolean more = rs.next();
							if(more){
								m_val_date=rs.getString(3);
								m_exg_rate=rs.getDouble(2);
								m_curr_code=rs.getString(1);
							}			
	
	
	
							/*
							String m_engin=m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO");
							String m_pur="";
							String m_inv="";
							
				
							 rs = stmt.executeQuery ("SELECT "+
			   			 "INVOICE_NO,A.APPLICATION_NO,A.PURCHASE_ORDER_NO "+
			 				 "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER B,  "+
							 ""+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET C "+
			 				 "WHERE A.ENGINE_NO='"+m_engin+"' "+
						   "AND C.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO "+
						   "AND A.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO "+
						   "AND INVOICE_NO=PRO_INVOICE_NO ");
				
							boolean more = rs.next();
							if(more){
								m_pur=rs.getString(3);
								m_inv=rs.getString(1);
							}			
							
							*/
							
							
						  String m_seizer=(String)m_sn_methods.met_formdata(reqstr,"TXT_SEIZER_CODE");
							
							if(!m_seizer.equals("") && !m_seizer.equals("-")){

							callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_SUS_PAYMENT_INSERT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;");
										
													
							
							callstmt.setString(1,"");
              callstmt.setString(2,"E");
	            callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_SEIZER_CODE"));
				      callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
					    callstmt.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_AMOUNT").toUpperCase()));
							callstmt.setInt(6,0);
							callstmt.setString(7 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_AMOUNT").toUpperCase()));
	  					callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						  callstmt.setString(9,m_username);
							callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_REPOSSESSION_CODE"));
							callstmt.setString(11,"AF_RE_COLLECTION_VEHICLE_INVENTORY");


							callstmt.setString(12,m_val_date);
						  callstmt.setDouble(13,m_exg_rate);					
							callstmt.setString(14,m_curr_code);
							callstmt.execute();
							
							}
							
							//-----------------------------------------------------------------------------------------------
							
				
				
				//conn.commit();
				
			
			
		/*
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_DELETE_set(:1); END;");
			callstmt.setString(1,"");
			callstmt.execute();
			callstmt.close();

		 conn.commit();

	*/
			
			
			

		
			//conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			if( m_load_status.equals("APP")){
			out.println("window.close();");
			}else{
		  out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_RE_Vehicle_Inventory';");
	   	}
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		}
		/*catch (Throwable th) {
     	PrintStream out = new PrintStream(res.getOutputStream());
			th.printStackTrace(out);
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}*/
		
		
		
		
			catch (Exception E) {
		  try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
	 }
		finally{
		  try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
	}
}
