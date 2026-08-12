// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
    
       
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Save_Settlement_deposit extends HttpServlet {

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
			int m_num=0;
			int m_tnum=0;
			String m_scr_num="0";
			String m_deposit_refno="";
			
			m_msg = "'Information saved successfully for Settlement Deposit";
			//------------------------------------------------------------------------------------------
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			m_num=Integer.parseInt(m_scr_num);
			
			if(m_num>0){
			
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_SETTLE_DEPOSIT(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;"); 
			
				m_deposit_refno=m_sn_methods.met_formdata(reqstr,"TXT_DEPOSIT_REF_NO");
	
				if (m_deposit_refno.equals("")){
					callstmt.registerOutParameter(1,java.sql.Types.CHAR);
				}
				else {
					callstmt.setString(1,m_deposit_refno);
	      }   

				callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"));
				callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ACCOUNT_CODE"));
				m_date=m_sn_methods.met_formdata(reqstr,"TXT_DEPOSIT_DD")+"-"+
							 m_sn_methods.met_formdata(reqstr,"TXT_DEPOSIT_MM")+"-"+
							 m_sn_methods.met_formdata(reqstr,"TXT_DEPOSIT_YY");
				callstmt.setString(4,m_date);
				callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DEPOSIT_AMOUNT")));
				callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_DEPOSIT_COMMENTS"));
				callstmt.setString(7,m_username);
				callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_SETTLEMENT_MODE").trim());
				callstmt.execute();
				
				if (m_deposit_refno.equals("")){
					m_deposit_refno=callstmt.getString(1);
				}
			
				callstmt.close();
				//------------------------------------------------------------------
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_SETTLE_DEPOSIT_ALLO(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
				
				int tnum=0;
				
				if(m_num>0){
					for(int i=1;i<=m_num;i++){
						
						String m_option=m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_CHECK_"+i).trim();
						
						if(m_option.equals("on")){
							tnum++;
							callstmt1.setString(1,m_deposit_refno);
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_RECEIPT_NO_"+i).trim());
							callstmt1.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT_"+i)));
							callstmt1.setString(4,m_date);
							callstmt1.setString(5,m_username);
							callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setInt(7,tnum);
							callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_REF_NO_"+i).trim());
							callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_SETTLEMENT_MODE").trim());
							callstmt1.execute();
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
			if(m_deposit_refno.equals("")){
			m_msg = "'Information saved successfully ";
			out.println("alert("+m_msg+" ');");
			}
			else{
			m_msg = "'Information saved successfully for Settlement Deposit Reference No: ";
			out.println("alert("+m_msg+" "+m_deposit_refno+"');");
			}
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Settlement_Deposit';");
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

