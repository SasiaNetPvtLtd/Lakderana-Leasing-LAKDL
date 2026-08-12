//--
//SCREEN NAME	:SYSTEM ADMINISTRATION - MAINTENANCE RATE
//MODIFED BY	:DELANJALI
//DATE/TIME		:16-01-2007
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_authorization_limits_details extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1,rs2;
	Statement stmt,stmt1;
	Connection conn;
	String reqstr;
	String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			String m_schema_name = m_sn_methods.schema_name;

			m_chksql=req.getParameter("chksql");
			
	
	
		if(m_chksql.equals("auth_limit_details")){		
			int i=0;		
			int x=0;	
			
		
			
			String m_user_id= req.getParameter("user_id");	
					
			
			
	rs = stmt.executeQuery(""+
		 "SELECT  TO_NUMBER(AUTHORIZATION_LEVEL), "+
		 "(SELECT INITCAP(DISPLAY_NAME) FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN WHERE POSITION=AUTHORIZATION_LEVEL), "+
		 "NVL(LIMIT,0), "+ //TO_CHAR(NVL(LIMIT,0),'999,999,999,999,999.99')
		 "A.ACTIVE_STATUS "+
		 "FROM "+m_schema_name+".AF_CO_MAS_AUTHORIZATION_LIMITS A "+//,"+m_schema_name+".CO_CO_MAS_USER_SCREEN b "+
		 "WHERE UPPER(USER_ID)=UPPER('"+m_user_id+"') "+
			
		 "UNION  "+
			
		 "SELECT POSITION,INITCAP(DISPLAY_NAME),0,'N' "+
		 "FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
		 "WHERE DISPLAY_STATUS='Y' AND POSITION IS NOT NULL  "+
		 "AND POSITION NOT IN (SELECT AUTHORIZATION_LEVEL "+
		 "FROM "+m_schema_name+".AF_CO_MAS_AUTHORIZATION_LIMITS A "+
		 "WHERE UPPER(USER_ID)=UPPER('"+m_user_id+"')) "+
		  "	");

	  
	
		boolean more = rs.next();

			out.println("<br>");			
			out.println("<table align=\"LEFT\" width=\"50%\" border=\"0\" class=\"table\">");
		
			out.println("<br>");			
	

			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='25%' >Authorization Level</td>"); 
			out.println("<td width='20%' align='right'>Authorization Limit</td>"); 
			//out.println("<td width='20%' align='center'>Default Value </td>");
			out.println("<td width='5%' align='center'>Status</td>");
			

			out.println("</tr >"); 
			/*
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("</table>");
			out.println("<table align=\"LEFT\" width=\"50%\" border=\"1\" class=\"table\">");
*/
		  	int j = 0; 
			while(more){
			String m_sub=rs.getString(2);
			
			if(m_sub==null){
			m_sub="";
			}
			out.println("<tr >"); 
			out.println("<td width='25%' >"+m_sub+"<input class='txt_input' style='{text-align:right}' type='hidden' name=TXT_TYPE_"+j+" maxlength='22'  value=\""+rs.getString(1).trim()+"\" ></td>");
			
			if(rs.getString(3)!=null){
			out.println("<td width='20%' align='right'><input class='txt_input' style='{text-align:right}' type='text' name=TXT_AMOUNT_"+j+" maxlength='25' value=\""+rs.getString(3).trim()+"\" onblur=\"check_amt("+j+")\"></td>"); 
			}
			else if(rs.getString(3)==null){
			out.println("<td width='20%' align='right'><input class='txt_input' style='{text-align:right}' type='text' name=TXT_AMOUNT_"+j+" maxlength='25'  value=\"\" onblur=\"check_amt("+j+")\"></td>"); 
			}
			
			/*out.println("<td width='20%' align='center'><select class='txt_input' type='text' name=TXT_DEFAULT_VALUE_"+j+" maxlength='1' size='1'>"); 
			
			String m_default=rs.getString(4);
			
			if(m_default==null){
			m_default="N";
			}
			if(m_default.equals("Y")){
			out.println("<option value='N' > No </option>");
			out.println("<option value='Y' selected> Yes </option>");
			}
			
			else if(m_default.equals("N")){
			out.println("<option value='N' selected> No </option>");
			out.println("<option value='Y' > Yes </option>");
			}
		
			else {
			out.println("<option value='N' selected> No </option>");
			out.println("<option value='Y' > Yes </option>");
			}
		    out.println("</select>");

			out.println("</td>");*/
			
			
			//if(rs.getString(3)==null){
				//out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=CHK_STATUS_"+j+" value=\"off\" unchecked onclick=\"change("+j+")\"></td>");
			//}
			//else {
				if(rs.getString(4).equals("Y")){
					out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=CHK_STATUS_"+j+" value=\"on\" checked onclick=\"change("+j+")\"></td>");
				}
				else{
					out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=CHK_STATUS_"+j+" value=\"on\" unchecked onclick=\"change("+j+")\"></td>");
				}
			//}
			out.println("<input class='txt_input' type='hidden' name='TXT_SUB_TYPE_CODE_"+j+"' maxlength='10' size='10'  value=\""+rs.getString(1)+"\">");

			out.println("</tr >"); 
			more=rs.next();
			j=j+1;
			
			}	
			//out.println("</table>");
			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");
		

}


		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			
			
			
			
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
