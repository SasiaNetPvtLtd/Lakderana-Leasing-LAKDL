// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
    
       
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Save_Realisation_Return extends HttpServlet {

	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
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

			String m_date="";
			String m_date_company="";
			int m_num=0;
			int m_tnum=0;
			String m_scr_num="0";
			String m_return_refno="";
			String m_return_realize_no="";
				
			m_msg = "'Information saved successfully for Return Reference No: ";
			//------------------------------------------------------------------------------------------
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			m_num=Integer.parseInt(m_scr_num);
			
			if(m_num>0){

				//------------------------------------------------------------------
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_REALIZE_RETURN(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");
				callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
				
				int tnum=0;
				
				if(m_num>0){
					for(int i=1;i<=m_num;i++){
						
						String m_option3=m_sn_methods.met_formdata(reqstr,"TXT_RSTATUS_"+i).trim();
						String m_rec_status="";
						
						if(m_option3.equals("R")){
							m_rec_status="REA";
						}
						else if(m_option3.equals("T")){
							m_rec_status="RET";
						}
						else{
							m_rec_status="";
						}

						
						if(!m_rec_status.equals("")){
							
							m_return_refno="";
							if(m_rec_status.equals("REA")){
								m_return_refno="REA";
							}
							else{
								m_return_refno="";
							}
							
							/*if (m_return_refno.equals("")){
							callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
							}
							else {*/
							callstmt1.setString(1,m_return_refno);
							//}   
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_DEPOSIT_NO_"+i).trim());
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_RECEIPT_NO_"+i).trim());
							callstmt1.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DEPOSIT_AMT_"+i)));
							callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REF_NO_"+i).trim());
							m_date=m_sn_methods.met_formdata(reqstr,"TXT_REALIZE_DD_"+i)+"-"+m_sn_methods.met_formdata(reqstr,"TXT_REALIZE_MM_"+i)+"-"+m_sn_methods.met_formdata(reqstr,"TXT_REALIZE_YY_"+i);
							m_date_company=m_sn_methods.met_formdata(reqstr,"TXT_REALIZE_DD_CO_"+i)+"-"+m_sn_methods.met_formdata(reqstr,"TXT_REALIZE_MM_CO_"+i)+"-"+m_sn_methods.met_formdata(reqstr,"TXT_REALIZE_YY_CO_"+i);
							callstmt1.setString(6,m_date);
							callstmt1.setString(7,m_rec_status);
							callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_ACCOUNT_CODE"));
							callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"));
							callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(11,m_username);
							callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS_"+i).trim());
							callstmt1.setString(13,m_date_company);
							callstmt1.execute();
							
							if(m_return_refno.equals("")){
								m_return_realize_no=m_return_realize_no+","+callstmt1.getString(1);
							}

						}
					}
				}
				callstmt1.close();
			}
			//-------------------------------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+" "+m_return_realize_no+"');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Realisation_Return';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

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

