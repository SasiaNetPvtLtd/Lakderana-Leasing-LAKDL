// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE: 29-02-2008
    
       
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_Save_unbank_Cheque extends HttpServlet {

	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
  String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************		
			//out.println("reqstr="+reqstr);
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();


			ServletOutputStream out = res.getOutputStream();

			int m_num=0;
			String m_scr_num="0";
			String m_client_no="";
			String m_facility_no="";

				
			m_msg = "'Information saved successfully'";
			//------------------------------------------------------------------------------------------
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			m_num=Integer.parseInt(m_scr_num);
			
			m_facility_no=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			m_client_no=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			String m_rec_num="";//Added by Dineth on 2009-02-19
			int count_check=0;  //Added by Dineth on 2009-02-19
			if(m_num>0){
			
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_UNBANK_CHEQUES(:1,:2,:3,:4,:5); END;");
				callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_RETURN_CHQ_LETTER(:1,:2,:3); END;");//Added by Dineth on 2009-02-19
				if(m_num>0){
				count_check=0;//Added by Dineth on 2009-02-19
					for(int i=1;i<=m_num;i++){
						if(m_sn_methods.met_formdata(reqstr,"CANCEL_CHK_"+i).trim().equals("on")){

							callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_REC_NO_"+i).trim());
							//callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO"));
							//callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
							//edit and add by malik on 17/2/2009
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"HID_FACILTY_NO_"+i).trim());
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"HID_CLIENT_CODE_"+i).trim());
              m_facility_no=m_sn_methods.met_formdata(reqstr,"HID_FACILTY_NO_"+i);
			        m_client_no=m_sn_methods.met_formdata(reqstr,"HID_CLIENT_CODE_"+i);
							//end
  						callstmt1.setString(4,m_username);
							callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE_"+i).trim());
							callstmt1.execute();
							
							//Added by Dineth on 2009-02-19
							if(count_check==0){
							callstmt2.registerOutParameter(1,java.sql.Types.CHAR);
							}	else{
							callstmt2.setString(1,m_rec_num);
							}

							callstmt2.registerOutParameter(1,java.sql.Types.CHAR);
							callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_REC_NO_"+i).trim());
							callstmt2.setInt(3,count_check);
							callstmt2.execute();
							
							if(count_check==0){
			        m_rec_num=callstmt2.getString(1);
			        }
							count_check+=1;
							
							//End by Dineth on 2009-02-19
							}
						}
					}
				}
				callstmt1.close();


            conn.commit();

	   	    out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			// Commented below line by Udara Somathilake on 13-10-2010
			//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_Unbank_cheque_letter?finance_no="+m_facility_no+"&client="+m_client_no+"&ref_no="+m_rec_num+"&chksql=main_page\";");//Modified by Dineth on 2009-02-19  
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MISF_Return_cheque_sent_letter?finance_no="+m_facility_no+"&client="+m_client_no+"&ref_no="+m_rec_num+"&chksql=main_page\";"); // Added by Udara Somathilake on 13-11-2010
			
			
			//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PD_Cancel_cheque_letter?finance_no="+m_facility_no+"&client="+m_client_no+"&chksql=main_page\";"); 
			//out.println("alert(m_url);");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println("window.location.href='"+m_class_url+"/"+m_client_name+"FA_OP_Unbank_Cheques';");
			//out.println("window.close();");
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");			
			
			

			out.flush();
      out.close();
		}
		catch (Exception ex) {
			try{conn.rollback();}catch(Exception e){}
			out.println("Error:"+ex.toString());
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
		finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}

