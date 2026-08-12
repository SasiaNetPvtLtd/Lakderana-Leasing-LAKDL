 
//Created by Nuwan De Silva
//Collection - Repossession Exception Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Collection_Repossession_Exception_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql,m_no_of_due_days,m_sys_date;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username = con_method.username;
			String m_fschema_name=con_method.client_name.trim();
   //   String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			String m_val="";
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql         = req.getParameter("chksql");
		//	m_no_of_due_days = req.getParameter("no_of_days");
		//	m_val=m_no_of_due_days+"days Collection due report";
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
		//	if (m_chksql.trim().equals("idle")) {
		//		out.println("idle");
	//		}
					 if(m_chksql.trim().equals("main_page")){
					   
						  String m_sort_column   = "REPOSSESSION_NO";	
							String m_order_by_type = "ASC";
							
							if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			          m_sort_column = req.getParameter("sort_column");
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
				out.println("help_box.innerHTML=\"Collection - Repossession Exception Report \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Collection - Repossession Exception Report - \"+m_val;"); 
			  out.println("}");
			
							
				out.println("}");
				

			  out.println("function load_data(m_finance_no) {");
				//out.println(" if(m_app_sts=='IP') { ");
	      out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Return_Exception_Report_Drill?chksql=drill&finance_no=\"+m_finance_no;"); 
			  out.println("   window.open(m_url,'displayWindowap','left=110,top=110,width=900,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			//	out.println("  }");
			//	out.println(" else { ");
			//  out.println("   alert(m_app_no+'  is complete.');"); 
		//		out.println("  }");
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
				//out.println("alert(m_sort_col);");
	      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Repossession_Exception_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			  out.println(" window.location.href=m_url;"); 
				out.println("}");
				
							
			
        out.println("</Script>");
				out.println("<body onload=\"load_roll_value('"+m_val+"')\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" >");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
				
                  
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
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" >   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection - Repossession Exception Report</td>");
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
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				 				 
           int j = 0;   
						
					
			
			out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
				//	out.println("<td colspan=6 align=right><input type=button name=Generate_Letter value=Generate Letter class=mainbut onclick=Generate_letter(); ></td>");
				  out.println("<td colspan=6 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td colspan=7 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
		
		
          out.println("<tr class=pdn_txtpos2 align='left'>");
					out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Repossession No'    onclick=sort_data('REPOSSESSION_NO') >Repossession No</td>");
					out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Finance No'   onclick=sort_data('FINANCE_NO') >Finance No</td>");
          out.println("<td  width='10%'  style= cursor:hand; title='Click here to sort by -Sizer Code'    onclick=sort_data('SEIZER_CODE') >Sizer Code</td>");
          out.println("<td  width='20%'  style= cursor:hand; title='Click here to sort by -Sizer Name'  onclick=sort_data('SEIZER_NAME') >Sizer Name</td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Letter Validity Period' onclick=sort_data('LETTER_VALIDITY_PERIOD') >Letter Validity Period</td>");
					out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Ent Date'        onclick=sort_data('ENT_DATE') >Ent Date</td>");
					out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Effective Value Date'  onclick=sort_data('EFF_VAL_DATE') >Effective Value Date</td>");
					
					out.println("</tr>");
			
		
		 rs = stmt.executeQuery (" SELECT "+
    " A.REPOSSESSION_NO REPOSSESSION_NO, "+
    " A.FINANCE_NO FINANCE_NO, "+
    " A.SEIZER_CODE SEIZER_CODE, "+
		"(B.FIRST_NAME || ' ' || B.LAST_NAME) SEIZER_NAME,"+
		" A.LETTER_VALIDITY_PERIOD LETTER_VALIDITY_PERIOD, "+
    //VEHICLE_INVENTORY_STATUS,
    " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE, "+
    //" TRN_DATE, "+
    //ACTIVE_STATUS,
    " NVL((TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY')),'-') EFF_VAL_DATE "+
 		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A,"+m_schema_name+".AF_CO_MAS_SEIZER B "+
 		" WHERE A.ACTIVE_STATUS='Y' AND "+
 		" (A.ENT_DATE+A.LETTER_VALIDITY_PERIOD)<SYSDATE AND "+
 		" A.VEHICLE_INVENTORY_STATUS='N' AND "+
		" A.SEIZER_CODE=B.SEIZER_CODE "+	
		" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
                             

		
		
		  while(rs.next()){
							    
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}
									
									//out.println("<td width='12%' align='center' style= cursor:hand;cursor-color:blue onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') >"+rs.getString(1) +"</td>");
                   out.println("<td width='15%'align='left'>"+rs.getString(1) +"</td> ");
									out.println("<td width='15%' align='left' style= cursor:hand;cursor-color:blue onclick=load_data('"+rs.getString(2)+"') >"+rs.getString(2) +"</td>");
                  out.println("<td width='10%' align='left'>"+rs.getString(3) +"</td>");
                  out.println("<td width='20%' align='left'>"+rs.getString(4)+"</td>");
                  out.println("<td width='10%' align='left'>"+rs.getInt(5) +"</td>");
                  out.println("<td width='15%' align='left'>"+rs.getString(6) +"</td>");
									out.println("<td width='15%' align='left'>"+rs.getString(7) +"</td>");
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
