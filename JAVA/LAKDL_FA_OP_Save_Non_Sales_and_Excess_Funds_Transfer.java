// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:19-10-2007

    
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_Save_Non_Sales_and_Excess_Funds_Transfer extends HttpServlet {

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
			
			String m_chksql=req.getParameter("chksql");
			String m_facility_code=m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO");
			String m_client_code  =m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE");
			
			if(m_chksql.equals("RECEIPT_DETAILS")){
			
				String m_adjustment_no="";
				m_num=0;
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_OP_SAV_NOSAL_AND_EXFUND_TRN(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
				m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
				m_num=Integer.parseInt(m_scr_num);
			  
				if(m_num>0){
					callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					for(int i=1;i<=m_num;i++){
						String m_chk_status=m_sn_methods.met_formdata(reqstr,"TXT_REC_OK_"+i).trim();
						if(m_chk_status.equals("on")){
						
							m_adjustment_no="";
							callstmt1.setString(2,m_facility_code);
							callstmt1.setString(3,m_client_code);
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_REC_NO_"+i).trim());
							callstmt1.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TRAN_AMOUNT_"+i)));
							callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_TRAN_DATE"+i).trim());
							callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_SOURCE_"+i).trim());
							callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_COMMENT_"+i).trim());
							callstmt1.setString(9,m_username);
							callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"hid_status").trim());
							callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_ADJ_TYPE_"+i).trim());
							callstmt1.execute();
							m_adjustment_no=m_adjustment_no+","+callstmt1.getString(1);
							
						}
					}
				}
			}
			callstmt1.close();
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Information saved successfully');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_non_sales_funds_adjustments';");
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

