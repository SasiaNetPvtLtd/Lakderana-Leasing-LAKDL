import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
//import CLAMF_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_PRO_CR_Cheque_details extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
    
  public ResultSet rs,rs1,rs_ind;
	public String m_chksql,m_qua,m_price,m_opt,m_url;
	public String m_st="";
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
			LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url = m_sn_methods.html_client_url;
			String m_class_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();
			String m_sp_name="";
			String m_pricing="";
			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			m_url = m_class_url;

			String m_st="0";
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			
					
	else if (m_chksql.trim().equals("m_cheque")) {
			String m_cheque1 = req.getParameter("data_val").trim();
			
		out.println("<html><head><font 10pt arial><title>Account Details</title></head>");
		out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");

		out.println("<SCRIPT language='JavaScript'>");
	  out.println("function close_1(){");
		out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
		out.println("window.close()");
		out.println("}");
		out.println("}");
		
		out.println("function data(){");
		out.println("alert('"+m_price+"')");
		out.println("}");
		
		out.println("</SCRIPT>");

		out.println("<body leftmargin='0' topmargin='0' class=body >");
		out.println("<br>");
		//out.println("<div align=center><img src='"+m_html_client_url+"/webacc-logo.gif'></div>");
		out.println("<br>");
		out.println("<div align='center' width='180%' class='pdn_mainHD' style='{font: bold;text-align:center;}'>Account Details</div>");
		out.println("<form name='form1'>");
		out.println("<table border='1' width='100%' bgcolor='white' style='{color: black; font: bold 10px;}'>");
		out.println("</table>");			
    out.println("<HR width='100%'>");
    out.println("<br>");
		
		
		rs=stmt.executeQuery("SELECT ACC_NO,BRANCH_CODE,ACC_SYS_REFNO,CURR_CODE,ACC_CODE,ACC_DESC,"+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE)  "+
		"FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
		"WHERE UPPER(ACC_NO) =UPPER('"+m_cheque1+"') AND ACTIVE_STATUS=('Y') ");
		
		
		boolean more=rs.next();	
	
		
		while(more)
		{
		m_price=rs.getString(1);
		m_pricing=rs.getString(3);
	
		out.println("<table border='0' width='100%' class='table'>");
		out.println("<tr><td width='30%' class='pdn_txtpos11'><b>Account No :-</td>");
		out.println("<td width='20%' class='pdn_txtpos11' style='{text-align:left;}'><b>"+rs.getString(1)+"</td>");
		out.println("<td width='50%'></td>");
		out.println("</tr>");
		out.println("</table>");

		out.println("<br>");	
		out.println("<br>");	
		out.println("<table border='0' width='100%' class='table'>");
		out.println("<tr class=\"pdn_txtpos2\"><td width='15%' ><b>Branch Code</td>");
		out.println("<td width='20%'><b>Branch Name</td>");
		out.println("<td width='15%'><b>Acc Ref No</td>");
		out.println("<td width='15%'><b>Curency Code</td>");
		out.println("<td width='15%'><b>Account Code</td>");
		out.println("<td width='20%'><b>Account Description</td>");
		out.println("</tr>");
		out.println("</table>");
		
		while(rs.getString(1).trim().equals(m_price)){
		out.println("<table border='0' width='100%' class='table'>");

		out.println("<tr>");
		out.println("<td width='15%' class='txt-body' style='{text-align:left;}'>"+rs.getString(2)+"</td>");
		out.println("<td width='20%' class='txt-body' style='{text-align:left;}'>"+rs.getString(7)+"</td>");
		out.println("<td width='15%' class='txt-body' style='{text-align:left;}'>"+rs.getString(3)+"</td>");
		out.println("<td width='15%' class='txt-body' style='{text-align:left;}'>"+rs.getString(4)+"</td>");
		out.println("<td width='15%' class='txt-body' style='{text-align:left;}'>"+rs.getString(5)+"</td>");
		out.println("<td width='20%' class='txt-body' style='{text-align:left;}'>"+rs.getString(6)+"</td>");

		out.println("</tr>");
		out.println("</table>");
		out.println("<br>");	
		out.println("<br>");	
		out.println("<table width='100%' cellpadding='1' border=\"0\">");	
    out.println("<tr><td width='20%' align='center'>");

    out.println("<input type='button' class='but_input' name='Exit'  value='Close' OnClick=\"close_1()\";></td></tr>");
    out.println("</table>");		

		more=rs.next();
		if (!more)
		{
		break;
		}
		}

		if (more)
		{
		m_price=rs.getString(1);
		}
		}
		out.println("</form>");
		out.println("</body>");
    out.println("</html>");
	}
	
		
		
		  
//--------------------------------------------------------------------------------------			
						
			else {
			    out.println("Undefined");
			}
			
      out.close();
			conn.close();
			this.destroy();
			
			
			}
			catch (Exception e) {
				try {
						conn.close();
				}catch (Exception eti) {}
			
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintWriter(ostr));
			
					ServletOutputStream out = res.getOutputStream();
					out.println(ostr.toString());
      		out.close();
			
			}
	}
}

