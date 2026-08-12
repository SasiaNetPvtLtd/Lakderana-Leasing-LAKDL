//CREATED BY DINETH MEEMANAGE
//DATE 2008-10-02
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Invoice_Reversal extends HttpServlet {

	ResultSet rs1;
	Statement stmt;
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
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
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			stmt=conn.createStatement();
      //String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			int count=0;
			String m_value_date="";
			
			String m_value_dd=m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_DD");
			String m_value_mm=m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_MM");
			String m_value_yy=m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_YY");
			
			m_value_date=m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_DD")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_MM")+'-'+m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_YY");
			if(m_value_dd.equals("") || m_value_mm.equals("") || m_value_yy.equals("") ){
			m_value_date="";
			}
			rs1=stmt.executeQuery("SELECT COUNT(*) FROM DUAL WHERE ADD_MONTHS(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),-1)<TO_DATE('"+m_value_date+"','DD-MM-YYYY')");
			boolean more=rs1.next();
			if(more){
				count=rs1.getInt(1);
			}
			
			
			//if(count>0){
			int row_count = Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"INV_COUNT"));
			String m_chk_type = "off"; 
			 //out.println("row_count"+row_count);
		//	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_INV_REVERSAL(:1,:2,:3,:4,:5); END;");
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_INV_REVERSAL_1(:1,:2,:3,:4,:5,:6); END;");//Mod By Sandun  on 11-03-2009
			for(int k=0;k<row_count;k++){
			m_chk_type = (String)m_sn_methods.met_formdata(reqstr,"CHK_"+(Integer.toString(k)));
		//	out.println("m_chk_type---"+m_chk_type);
			
			if(m_chk_type.equals("on")){			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setString(2,m_value_date);
			callstmt.setString(3,m_username);
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"HID_SCREEN"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"hid_status"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"HID_INV_NO_"+(Integer.toString(k))));//Added By Sandun  on 11-03-2009
			callstmt.execute();
			}
			}
			callstmt.close();
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_Invoice_Reversal_Option?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
		//	}
		/*	else{
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Date must be on or after one month');");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_Invoice_Reversal_Option?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			}*/
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			//out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
		finally{
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		
