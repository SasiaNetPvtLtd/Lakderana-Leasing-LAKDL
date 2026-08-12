//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Collection_Advertistment_Generation extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	Statement stmt;
	public ResultSet rs;
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		synchronized(this){ 
			
			try {
				
				BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
				reqstr = input.readLine();   	
				
				PrintStream out = new PrintStream(res.getOutputStream());
				// out.println(reqstr);
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				//************************************************************	
				conn =m_sn_methods.met_user_validate(req);
				//	out.println("conn"+conn);
				//**************************************************************		
				String m_schema_name = m_sn_methods.schema_name.trim();
				String m_client_name = m_sn_methods.client_name.trim();
				String m_username = m_sn_methods.username;
				String m_html_client_url;
				String m_class_url;
				String m_screen_name="";
				String m_app_no="";
				String m_vendor_code="";
				String m_status="";
				String m_val_date="";
				String m_scr_name="";
				String m_adtistement_no="";
				String m_scr="";
				String m_adv="";
				m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
				
				//	out.println("conn"+conn);
				//out.println(reqstr);
				
				m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
				m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
				m_msg = "'Information saved successfully'";
				m_url = m_class_url;
				String m_fschema_name=m_sn_methods.client_name.trim();
				
				stmt=conn.createStatement();
				
				
				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ADVERTIS_GEN(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
				
				String m_adset_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_ADVETST_NO");
				
				
				if(m_adset_no.equals("")){
					callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
				}
				else
				{
					callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_ADVETST_NO")).trim());
				}
				
				
				
				
				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO"));
				callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_NO"));
				
				String m_val_day =(String)m_sn_methods.met_formdata(reqstr,"TXT_ADVTEST_DATE_DD");
				String m_val_month =(String)m_sn_methods.met_formdata(reqstr,"TXT_ADVTEST_DATE_MM");
				String m_val_year =(String)m_sn_methods.met_formdata(reqstr,"TXT_ADVTEST_DATE_YY");
				
				
				m_val_date=m_val_day+"-"+m_val_month+"-"+m_val_year;
				
				callstmt.setString(4,m_val_date);
				
				callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT")));
				callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VAT_AMOUNT")));
				callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_AMOUNT")));
				//callstmt.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OFFER")));
				callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt.setString(9,m_username);
				callstmt.setString(10,m_scr_name);
				
				//---modified by :delanjali------------------------------------------------------------------------------------
				//---date				 :2007-06-12-----------------------------------------------------------------------------------
				
				callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_PAID_TO"));
				
				
				//-------------------------------------------------------------------------------------------------------------
				
				callstmt.execute();
				
				
				if(m_screen_name.equals("NEW")){
					m_adtistement_no =callstmt.getString(1);
					m_msg = "'"+m_adtistement_no+ "-" +"Advertisement saved successfully.'";
					
				}
				
				
				//			}
				
				//	}
				
				
				
				
				////////////////////////////////////////////////////////////////////////////////////////
				
				//-----------------------------------------------------------------------------------------------
				//--MODIFIED BY : DELANJALI----------------------------------------------------------------------
				//--DATE				: 2007-06-12---------------------------------------------------------------------
				//--PURPOSE			: TO INSERT A RECORD TO SUS PAYMENT----------------------------------------------
				String m_ent_date="";
				String m_inv_no=m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO");
				if(m_screen_name.equals("NEW")){
					m_adv=m_adtistement_no;
				}
				else{
					m_adv=m_adset_no;
				}
				rs = stmt.executeQuery (
					"SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY') "+
					"FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL "+
					"WHERE UPPER(ADVER_NO)=UPPER('"+m_adv+"')");
				
				boolean more = rs.next();
				if(more){
					m_ent_date=rs.getString(1);
				}			
				
				
				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_SUS_PAYMENT_INSERT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14); END;");
				
				
				
				callstmt.setString(1,"");
				callstmt.setString(2,"A");//Addvertistment
				
				
				//----modified by : delanjali---------------------------------
				//----date			  : 2007-07-20--------------------------------
				
				//callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_client_code"));
				//callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PAID_TO"));
				
				callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PAID_TO"));
				callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"hid_client_code"));
				
				
				callstmt.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_AMOUNT").toUpperCase()));
				callstmt.setInt(6,0);
				callstmt.setString(7 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_AMOUNT").toUpperCase()));
				//callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt.setString(8,m_screen_name);
				callstmt.setString(9,m_username);
				callstmt.setString(10,m_adv);
				callstmt.setString(11,"AF_RE_COLLECTION_ADVEST_GEN");
				callstmt.setString(12,m_ent_date);
				callstmt.setInt(13,1);
				callstmt.setString(14,"SLR");
				callstmt.execute();
				//-----------------------------------------------------------------------------------------------
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				conn.close();
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				out.println("m_scr_name='"+m_screen_name+"'");
				out.println("m_status='"+m_status+"'");
				out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Collection_Advertistment_Generation';");
				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				
				out.flush();
				out.close();
			}
			catch (Throwable th) {
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
			}
		}
	}
}
