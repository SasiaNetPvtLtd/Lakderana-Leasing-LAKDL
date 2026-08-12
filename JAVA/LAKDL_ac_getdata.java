import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder; 
import java.lang.*;
import java.sql.*;
//import LAKDAC_wbacc_methods;
import oracle.jdbc.driver.*;

public class LAKDL_ac_getdata extends HttpServlet {
	
	
	Connection conn;
	Statement stmt;
	public String m_bankcode;
	public ResultSet rs,rs1;
	public String m_data1,m_data2,m_data3,m_data4,m_data5,m_chksql;
	public String m_html_client_url,m_servlet_client_url;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse
		res)
		throws IOException
	{
		
		try {
			
			String m_country,m_state,m_city; 
			String password="";
			String rights="norights";
			int count=0;
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_LAKDAC_wbacc_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_LAKDAC_wbacc_methods.met_user_validate(req);
			String m_username = m_LAKDAC_wbacc_methods.username;
			String m_schema_name = m_LAKDAC_wbacc_methods.schema_name;
			//************************************************************
			stmt = conn.createStatement ();
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			ServletOutputStream out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			
			//ADDED MILINDA 2014-04-29 FOR PRINTER USER
			else if (m_chksql.trim().equals("printuser")) {
				//System.out.println("OK");
				//m_data1 = req.getParameter("data1");
				//m_data2 = req.getParameter("data2");
				rs = stmt.executeQuery("SELECT P_NAME, "+
					" NORMAL_SHARED_NAME, "+
					" SERVER_SHARED_NAME, "+
					//" ACTIVE_STATUS, "+
					" P_TYPE "+
					" FROM "+m_schema_name+".AF_REF_PRINTER  "+
					" WHERE ACTIVE_STATUS='Y' group by P_NAME,NORMAL_SHARED_NAME,SERVER_SHARED_NAME,P_TYPE " );	
				boolean more = rs.next();
				
				while (more) {
					
					out.println(rs.getString(1));
					out.println(rs.getString(2));
					out.println(rs.getString(3));
					out.println(rs.getString(4));
					more = rs.next();	
				} 
				rs.close();
				stmt.close();
			}
			
			else if(m_chksql.equals("LOAD_USER_APPROVAL_2")){
				
				String m_string="";				
				
				
				rs1= stmt.executeQuery("SELECT P_NAME, "+
					" NORMAL_SHARED_NAME, "+
					" SERVER_SHARED_NAME, "+
					//" ACTIVE_STATUS, "+
					" P_TYPE "+
					" FROM "+m_schema_name+".AF_REF_PRINTER  "+
					" WHERE ACTIVE_STATUS='Y' group by P_NAME,NORMAL_SHARED_NAME,SERVER_SHARED_NAME,P_TYPE " );	
				
				
				
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=0>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><b>Printer Name</b></DIV></td>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><B>Printer Path</b></DIV></td>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><b>Server Name</b></DIV></td>";
				/*m_string=m_string+"<td width='10%' ><DIV class=div_input><b>User Type</b></DIV></td>"; 
				//modified by madhawa 2009-10-15 change emp id to Emp Name
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Emp Name</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Division</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Designation</b></DIV></td>";
				m_string=m_string+"<td width='6%' class=div_inputj>Approve</td>";*/
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='8%' class=div_input onClick=\"edit_user('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_USER_ID_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='8%' class=div_input onClick=\"\" >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='8%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_location_drill('"+rs1.getString(1)+"')\"><U>"+rs1.getString(3)+"</td>";
					/*m_string=m_string+"<td width='10%' class=div_input onClick=\"\" >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_employee_drill('"+rs1.getString(5)+"')\"><U>"+rs1.getString(11)+"</td><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_EMP_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\">";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(10)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_designation_drill('"+rs1.getString(9)+"')\"><U>"+rs1.getString(7)+"</td>"; 
					m_string=m_string+"<td width='6%' class=div_input align=center><INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" ></td>";*/
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
			else if(m_chksql.equals("LOAD_USER_PRINTER")){
				
				String m_string="";				
				
				
				rs1= stmt.executeQuery("SELECT P_NAME, "+
					" NORMAL_SHARED_NAME, "+
					" SERVER_SHARED_NAME, "+
					//" ACTIVE_STATUS, "+
					" P_TYPE "+
					" FROM "+m_schema_name+".AF_REF_PRINTER  "+
					" WHERE ACTIVE_STATUS='Y' group by P_NAME,NORMAL_SHARED_NAME,SERVER_SHARED_NAME,P_TYPE " );	
				
				
				
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=0>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><b>Printer Name</b></DIV></td>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><B>Printer Path</b></DIV></td>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><b>Server Name</b></DIV></td>";
				/*m_string=m_string+"<td width='10%' ><DIV class=div_input><b>User Type</b></DIV></td>"; 
				//modified by madhawa 2009-10-15 change emp id to Emp Name
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Emp Name</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Division</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Designation</b></DIV></td>";*/
				m_string=m_string+"<td width='6%' class=div_inputj>Save</td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='8%' class=div_input onClick=\"\"  ><INPUT TYPE='HIDDEN' class='txt_input' NAME='HID_PRINTER"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\">"+rs1.getString(1)+"</td>";
					m_string=m_string+"<td width='8%' class=div_input onClick=\"\" ><INPUT TYPE='HIDDEN' class='txt_input' NAME='HID_PINTPATH"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='8%' class=div_input onClick=\"\" ><INPUT TYPE='HIDDEN' class='txt_input' NAME='HID_SHARE"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\">"+rs1.getString(3)+"</td>";
					/*m_string=m_string+"<td width='10%' class=div_input onClick=\"\" >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_employee_drill('"+rs1.getString(5)+"')\"><U>"+rs1.getString(11)+"</td><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_EMP_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\">";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(10)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_designation_drill('"+rs1.getString(9)+"')\"><U>"+rs1.getString(7)+"</td>"; 
					*/
					m_string=m_string+"<td width='6%' class=div_input align=center><INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" ></td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("print_info")){
				String m_user = req.getParameter("user_id");
				String m_string="";				
				
				
				rs = stmt.executeQuery ("SELECT NVL(PRINTER,' '),SEL_TYPE  "+
					"FROM "+m_schema_name+".REF_USER_PRINTER where user_name='"+m_user+"' and SEL_TYPE='Y' group by PRINTER,SEL_TYPE   ");	
				
				boolean more = rs.next();
				while (more) {
					
					out.println(rs.getString(1));
					out.println(rs.getString(2));
					//out.println(rs.getString(3));
					//out.println(rs.getString(4));
					//out.println(rs.getString(5));
					more = rs.next();
				}
				
			}
			else {
				out.println("Undefined");
			}
			
			out.close();
			conn.close();
			
			this.destroy();
			//}
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.close();
			}
			catch (Exception eti) {}
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			
			out.println(ostr.toString());
			out.close();
			
		}
	}
}
