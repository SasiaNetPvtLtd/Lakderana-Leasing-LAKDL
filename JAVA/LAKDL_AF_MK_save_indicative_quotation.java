//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - INDICATIVE QUOTATION
//CREATED BY :DELANJALI
//DATE/TIME  :
//NOTES      :

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_save_indicative_quotation extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	
	
	Statement stmt,stmt1;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
	
	public ResultSet rs;
	
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods   m_sn_methods = new LAKDL_AF_CO_conn_methods  (); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			int m_con_number;
			String m_rate,m_quot,m_option;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			String m_no="";
			stmt = conn.createStatement();
			
			conn.setAutoCommit(false);
			String m_pstatus="";
			
			String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_fuel=req.getParameter("fuel"); 
			
			String m_status=m_sn_methods.met_formdata(reqstr,"TXT_STATUS");
			String m_inq=m_sn_methods.met_formdata(reqstr,"TXT_INQUIRY_NO");
			int g=0;
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			m_url = m_class_url;
			int m_chksql = Integer.parseInt(req.getParameter("number"));
			
			m_con_number = Integer.parseInt(req.getParameter("con_number"));
			
			
			int m_row = Integer.parseInt(req.getParameter("rowno"));
			
			
			
			m_option = req.getParameter("option");
			String new_screen_type=req.getParameter("screen_type");
			
			String m_my_screen="";
			
			m_my_screen =  req.getParameter("my_screen_name");
			
			if(m_my_screen==null){
				m_my_screen="";
				
			}
			
			
			m_quot=(m_sn_methods.met_formdata(reqstr,"TXT_QUOTATION_NO")).trim();
			
			
			int xx=0;
			String m_mk_off="";
			String m_auth_sig="";
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_IND_QUOTATION(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			//m_mk_off=m_sn_methods.met_formdata(reqstr,"TXT_MK_OFFICER");
			//if (m_mk_off.equals("")){
			m_mk_off=m_sn_methods.met_formdata(reqstr,"TXT_MK_NAME");
			//}
			//m_auth_sig=m_sn_methods.met_formdata(reqstr,"TXT_AUTH_SIGNA");
			//if (m_auth_sig.equals("")){
			m_auth_sig=m_sn_methods.met_formdata(reqstr,"TXT_AUTH_SIGNA_NAME");
			//}
			
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_INQUIRY_NO")).trim());
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_STATUS"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(5,m_username);
			callstmt.setString(6,Integer.toString(xx));
			callstmt.setString(7,m_auth_sig);
			
			//		callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_AUTH_SIGNA"));
			callstmt.setString(8,m_mk_off);
			//callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_MK_OFFICER"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO"));
			callstmt.execute();	
			
			m_no = callstmt.getString(1);
			
			
			for (int k=0; k<m_chksql; k++) 
			{
				
				
				
				for (int x=0; x<m_row; x++) 
				{					
					
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_IND_QUOTATION1(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");
					
					String m_price=m_sn_methods.met_formdata(reqstr,"text_price_"+k+"_"+x);
					
					callstmt.setString(1,m_no);
					
					
					callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"text_price_"+k+"_"+x)).trim());
					
					callstmt.setString(3,Integer.toString(k+1));
					
					
					callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"text_qty_"+k+"_"+x));
					callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"text_initpay_"+k+"_"+x)));
					callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"text_ipay_"+k+"_"+x)));
					callstmt.setString(7,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"text_monrent_"+k+"_"+x)));
					callstmt.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"text_monvat_"+k+"_"+x)));
					callstmt.setString(9,(m_sn_methods.met_formdata(reqstr,"text_conasst_"+k+"_"+x)).trim());
					callstmt.setString(10,(m_sn_methods.met_formdata(reqstr,"text_make_"+k+"_"+x)).trim());
					callstmt.setString(11,(m_sn_methods.met_formdata(reqstr,"text_model_"+k+"_"+x)).trim());
					callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"text_period_"+k+"_"+x));
					callstmt.setString(13,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"text_netamt_"+k+"_"+x)));
					callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt.setString(15,m_username);
					callstmt.setString(16,Integer.toString(k));
					callstmt.setString(17,(m_sn_methods.met_formdata(reqstr,"text_make_desc_"+k+"_"+x)).trim());
					callstmt.setString(18,(m_sn_methods.met_formdata(reqstr,"text_model_desc_"+k+"_"+x)).trim());
					callstmt.setString(19,(m_fuel).trim());
					callstmt.setString(20,(m_sn_methods.met_formdata(reqstr,"TXT_INQUIRY_NO")).trim());
					
					
					
					callstmt.registerOutParameter(21,java.sql.Types.CHAR);
					if  (!m_no.trim().equals("") ) {
						callstmt.execute();
					}
					
					
					m_pstatus=callstmt.getString(21);
					
				}
			}	
			
			
			
			for (int j=0; j<m_con_number; j++) 
			{
				String m_app=m_sn_methods.met_formdata(reqstr,"chk_app_"+j);
				
				if(m_app.equals("")){
					m_app="N";
				}
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_CONDITIONS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
				
				
				if(m_app.equals("Y")){
					
					callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_CODE_"+j));
					callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_DESC_"+j));
					callstmt.setString(3,"");
					callstmt.setString(4,"AF_AD_INDICATIVE_QUOTATION");
					callstmt.setString(5,m_app);
					callstmt.setString(6,"");
					callstmt.setString(7,"NEW");
					callstmt.setString(8,m_username);	
					callstmt.setString(9,"");			
					callstmt.setString(10,Integer.toString(j));
					callstmt.setString(11,m_no);
					callstmt.execute();
					g=g+1;
					
				}
				
				else if(m_app.equals("N") && g==0){
					
					callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_CODE_"+j));
					callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_DESC_"+j));
					callstmt.setString(3,"");
					callstmt.setString(4,"AF_AD_INDICATIVE_QUOTATION");
					
					callstmt.setString(5,m_app);
					callstmt.setString(6,"");
					callstmt.setString(7,"DELETE");
					callstmt.setString(8,m_username);	
					callstmt.setString(9,"");			
					callstmt.setString(10,Integer.toString(j));
					callstmt.setString(11,"");
					callstmt.execute();
					
				}
				
				
				
			}
			
			
			conn.commit();
			
			
			
			
			m_msg="'"+m_no+"-Quotation saved successfully'";
			
			callstmt.close();
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			
			if(m_pstatus.equals("Y"))
			{
				out.println("m_url=\""+m_url+"/"+m_fschema_name+"AF_MK_Indicative_Quatation_Letter?chksql=view_letter&data_val="+m_no+"&opt="+m_option+"\";");//
				out.println("popupwin=window.open(m_url,'displayWindow1','left=160,top=90,width=680,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			}
			//Added By Nuwan De Silva
			out.println("if('"+m_my_screen+"'=='' && '"+m_inq+"' ==\"\"){");
			out.println("window.opener.load_pricing()");
			out.println("}");
			out.println("else {");
			if(new_screen_type.equals("WIN")){
				out.println("		window.location.href='"+m_url+"/"+m_schema_name+"_AF_MK_display_indicative_quotation?screen_type="+new_screen_type+"';"); 
			}
			if(!new_screen_type.equals("WIN")){
				out.println("		window.location.href='"+m_url+"/"+m_schema_name+"_AF_MK_display_indicative_quotation';"); 
			}
			out.println("}");
			
			
			
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
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
		}
		finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
}

