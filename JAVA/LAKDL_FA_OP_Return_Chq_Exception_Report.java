import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_FA_OP_Return_Chq_Exception_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res){
		
		try {
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String m_header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			out = res.getOutputStream();
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			
			String m_sort_column="A.RETURN_NO";	
			String m_order_by_type = "ASC";
			
			if(req.getParameter("sort_column")!=null){
			m_sort_column = req.getParameter("sort_column");
			}
			if(req.getParameter("order_by_type")!=null){
			m_order_by_type = req.getParameter("order_by_type");
			}
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Operation Reports - Return Cheque Exception Report</title>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			out.println("function befor_end(m_obj) {");
			out.println("   m_obj.focus();");
			out.println("}");
			
			out.println("function load_roll_value(m_val){"); 
			out.println("if(m_val==''){");
			out.println("help_box.innerHTML=\"Operation Reports - Return Cheque Exception Report \";"); 
			out.println("}else{");
			out.println("help_box.innerHTML=\"Operation Reports - Return Cheque Exception Report - \"+m_val;"); 
			out.println("}");
			out.println("}");
			
			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_OP_Return_Chq_Exception_Report?sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 				
			out.println(" window.location.href=m_url;"); 
			out.println("}");
			
			out.println("</Script>");
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Operation Reports - Return Cheque Exception Report </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("<tr class=tr_input>");
			out.println("<table class=table border='0' width='100%' >");
			out.println("<tr class=tr_input>");
			out.println("<td colspan=6 align=right><input type=\"button\" name=\"Close\" value=Close class=\"mainbut\" onclick=\"close_window();\" ></td>");
			out.println("<td colspan=7 align=right><input type=\"button\" name=\"end_b\"   value=\"Go to End\" class=\"mainbut\" onclick='befor_end(document.Form1.top_b);' onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			out.println("</tr>");
			
			out.println("<tr class=pdn_txtpos2 align='center'>");
			out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Return No'  onclick=sort_data('A.RETURN_NO') ><u>Return No</u></td>");
			out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Deposit No'  onclick=sort_data('A.DIPOSIT_NO') ><u>Deposit No</u></td>");
			out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Receipt No'  onclick=sort_data('A.RECEIPT_NO') ><u>Receipt No</u></td>");
			out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Deposit Amount'  onclick=sort_data('A.DEPOSIT_AMOUNT')><u>Deposit Amount </u></td>");
			out.println("<td  width='8%' ><u>Cheque No</u></td>");
			out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Realize Date'  onclick=sort_data('A.REALIZE_DATE')><u>Realize Date</u></td>");
			out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Account No'  onclick=sort_data('A.ACCOUNT_NO')><u>Account No</u></td>");
			out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Branch Name'  onclick=sort_data('A.BRANCH_CODE')><u>Branch Name</u></td>");
			out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Receipt date' onclick=sort_data('B.EFF_VALDATE')><u>Receipt Date</u></td>");
			out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Allocated Amount' onclick=sort_data('B.ALLO_AMOUNT')><u>Allocated Amount</u></td>");
			out.println("<td  width='8%' style= cursor:hand; title='Click here to sort by - Client Code'  onclick=sort_data('B.CLIENT_CODE')><u>Client Code </u></td>");
			out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Facility No' onclick=sort_data('B.FACILITY_NO')><u>Facility No</u></td>");
			out.println("</tr>");
			
			int j = 0;      					
	
			rs = stmt.executeQuery (" SELECT "+
			" A.RETURN_NO,"+//1
			" A.DIPOSIT_NO,"+//2
			" A.RECEIPT_NO,"+//3
			" NVL(A.DEPOSIT_AMOUNT,0) DEPOSIT_AMOUNT,"+//4
			" NVL(A.CHEQUE_NO,'-') CHEQUE_NO,"+//5
			" TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY') REALIZE_DATE,"+//6
			" NVL(A.ACCOUNT_NO,0),"+//7
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) BRANCH_NAME,	"+//8
			" TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),"+//9
			" NVL(B.ALLO_AMOUNT,0),"+//10
			" B.CLIENT_CODE, "+//11
			" B.FACILITY_NO "+//12
			" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A ,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
			" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
			
			
			while(rs.next()){
			  
			if(j>0 && j%2==1){
			  	out.println("<tr class=tr_input1 >");
				}
				else{
			  	out.println("<tr class=tr_input >");
				}
			
			  out.println("<td width='12%' align='center' style= cursor:hand; title='click here to view return details ' onclick=\"show_return_details('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u></td>");
				out.println("<td width='12%' align='center' style= cursor:hand; title='click here to view deposit details ' onclick=\"show_deposit_details('"+rs.getString(2)+"')\" ><u>"+rs.getString(2) +"</u></td>");
			  out.println("<td width='12%' align='center' style= cursor:hand; title='click here to view receipt details ' onclick=\"show_receipt_details('"+rs.getString(3)+"')\"><u>"+rs.getString(3) +"</u></td>");
			  out.println("<td width='12%' style='{text-align:right;}'>"+nf.format(rs.getDouble(4))+"</td>");
			  out.println("<td width='8%'  align='center'>"+rs.getString(5)+"</td>");
			  out.println("<td width='12%' style='{text-align:right;}'>"+rs.getString(6)+"</td>");
			  out.println("<td width='10%' style='{text-align:right;}'>"+rs.getString(7)+"</td>");
				out.println("<td width='10%' align='center'>"+rs.getString(8)+"</td>");
				out.println("<td width='10%' style='{text-align:right;}'>"+rs.getString(9)+"</td>");
				out.println("<td width='10%' align='center' >"+nf.format(rs.getDouble(10))+"</td>");
				out.println("<td width='8%' align='center' style= cursor:hand; title='click here to view Client details ' onclick=\"show_client('"+rs.getString(11)+"')\"><u>"+rs.getString(11)+"</u></td>");
				out.println("<td width='10%' align='center' style= cursor:hand; title='click here to view Facility details ' onclick=\"show_facility('"+rs.getString(12)+"')\"><u>"+rs.getString(12)+"</u></td>");
				out.println("</tr>");
				j=j+1;
			} 
			
			out.println("<tr class=tr_input>");
			out.println("<td colspan=6 align=right><input type=\"button\" name=\"Close\" value=Close class=\"mainbut\" onclick=\"close_window();\" ></td>");
			out.println("<td align=right colspan=7><input type=\"button\" name=\"top_b\" value=\"Go to Top\" class=\"mainbut\" onclick='befor_end(document.Form1.end_b)' onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			
			out.println("</tr></table>");
			out.println("</td>");
			
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/factoring_drill_down.js\"></SCRIPT> ");
			out.println("</html>");
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
