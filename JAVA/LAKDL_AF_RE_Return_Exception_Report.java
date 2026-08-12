 
//Created by Mahela Wickramasekara on 02-11-2006 at  12.24 P.M.
//Return Exception Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Return_Exception_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			//LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("main_page")){
					   
						  String m_sort_column="RETURN_NO";	
							String m_order_by_type = "ASC";
							
							if(req.getParameter("sort_column")!=null){
			          m_sort_column = req.getParameter("sort_column");
							}
					 		if(req.getParameter("order_by_type")!=null){
								m_order_by_type = req.getParameter("order_by_type");
							}
     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Collection  - Return Exception Report \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Collection -  Return Exception Report - \"+m_val;"); 
			  out.println("}");
				out.println("}");

			  out.println("function load_details_deposit(deposit_no){");
				//out.println("alert('dePOSIT nO ** '+deposit_no)");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_deposit_details&DEP_NO='+deposit_no;"); 
				out.println("window.open(m_url,'displayWindow4','left=150,top=250,width=800,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 		out.println("}");
			
				out.println("function load_details_receipt(receipt_no){");
				//out.println("alert('receipt no ** '+receipt_no)");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_receipt_details&REC_NO='+receipt_no;"); 
				out.println("window.open(m_url,'displayWindow3','left=80,top=200,width=900,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
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

	      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Return_Exception_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 				
			  out.println(" window.location.href=m_url;"); 
				out.println("}");
			
        out.println("</Script>");
				
				
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection - Return Exception Report </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");

        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
		  
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
				  out.println("<td colspan=6 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td colspan=7 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2 align='center'>");
					out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Return No'  onclick=sort_data('A.RETURN_NO') ><u>Return No</u></td>");
          out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Deposit No'  onclick=sort_data('A.DIPOSIT_NO') ><u>Deposit No</u></td>");
          out.println("<td  width='12%'  style= cursor:hand; title='Click here to sort by - Receipt No'  onclick=sort_data('A.RECEIPT_NO') ><u>Receipt No</u></td>");
          out.println("<td  width='8%' style= cursor:hand; title='Click here to sort by - Amount'  onclick=sort_data('A.AMOUNT')><u>Amount </u></td>");
					out.println("<td  width='8%' style= cursor:hand; title='Click here to sort by - Allocated Receipt Status'  onclick=sort_data('A.ALLO_RECEIPT_STATUS')><u>Allocated Receipt Status</u></td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Allocated Amount'  onclick=sort_data('A.ALLOCATED_AMOUNT')><u>Allocated Amount</u></td>");
					out.println("<td  width='8%' style= cursor:hand; title='Click here to sort by - Balance Amount'  onclick=sort_data('A.BAL_AMOUNT')><u>Balance Amount </u></td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Payer Account No ' onclick=sort_data('B.PAYER_ACC_NO')><u>Payer Account No</u></td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Receipt Amount' onclick=sort_data('B.REC_AMOUNT')><u>Receipt Amount </u></td>");
					out.println("<td  width='8%' style= cursor:hand; title='Click here to sort by - Branch Code'  onclick=sort_data('B.BRANCH_CODE')><u>Branch Code </u></td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Branch Name' onclick=sort_data('BRANCH_NAME')><u>Branch Name</u></td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Account No' onclick=sort_data('B.ACC_NO')><u>Account No</u></td>");
					out.println("</tr>");
					 
           int j = 0;      					

					
				 	rs = stmt.executeQuery (" SELECT "+
  				 " A.RETURN_NO,"+
  			   " A.DIPOSIT_NO,"+
  				 " A.RECEIPT_NO,"+
  				 " NVL(A.AMOUNT,0),"+
  				 " NVL(A.ALLO_RECEIPT_STATUS,'-'),"+
  				 " NVL(A.ALLOCATED_AMOUNT,0),"+
  				 " NVL(A.BAL_AMOUNT,0),"+
  				 " NVL(B.PAYER_ACC_NO,'-'),"+
  				 //" B.PAYER_BRANCH_CODE,"+
  				 " NVL(B.REC_AMOUNT,0),"+
  				 " NVL(B.BRANCH_CODE,'-'),"+
					 " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.BRANCH_CODE) BRANCH_NAME,	"+
  				 " NVL(B.ACC_NO,'-') "+
 					 "FROM LAKDL.AF_CO_PRO_RETURN_DETAILS A , LAKDL.AF_CO_PRO_SETTL_RECEIPT B "+
 					 "where BAL_AMOUNT !=0 AND A.RECEIPT_NO=B.REC_NO "+
					 " ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
			

              while(rs.next()){
							    
								if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}

                  out.println("<td width='12%' align='center'>"+rs.getString(1) +"</td>");
									out.println("<td width='12%' align='center' style= cursor:hand; title='Click here to view deposit details ' onclick=\"load_details_deposit('"+rs.getString(2)+"')\"  >"+rs.getString(2) +"</td>");
                  out.println("<td width='12%' align='center' style= cursor:hand; title='Click here to view receipt details ' onclick=\"load_details_receipt('"+rs.getString(3)+"')\"  >"+rs.getString(3) +"</td>");
                  out.println("<td width='8%'  STYLE='{text-align:right;}' >"+nf.format(rs.getDouble(4)) +"</td>");
                  out.println("<td width='8%' align='center'>"+rs.getString(5)+"</td>");
                  out.println("<td width='10%' STYLE='{text-align:right;}'>"+nf.format(rs.getDouble(6))+"</td>");
							    out.println("<td width='8%' STYLE='{text-align:right;}'>"+nf.format(rs.getDouble(7))+"</td>");
									out.println("<td width='10%' align='center'>"+rs.getString(8)+"</td>");
									out.println("<td width='10%'  STYLE='{text-align:right;}'>"+nf.format(rs.getDouble(9))+"</td>");
									out.println("<td width='8%' align='center' >"+rs.getString(10)+"</td>");
									out.println("<td width='10%' align='center' >"+rs.getString(11)+"</td>");
									out.println("<td width='10%' align='center' >"+rs.getString(12)+"</td>");
         					out.println("</tr>");
                	j=j+1;
              } 
          
					
					out.println("<tr class=tr_input>");
				  out.println("<td colspan=6 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td align=right colspan=7><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("</tr></table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
      }
			
			//=========================================================================================================================			
			
  		/*else {
				out.println("Undefined");
			}
			*/
      //out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
