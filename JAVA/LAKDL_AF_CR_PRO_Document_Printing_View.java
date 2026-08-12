//Created by Nuwan De Silva on 16/1/2007 at 3.41 pm.
//Modified by Chandana on 27/07/2007
//Document Printing

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Document_Printing_View extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			String m_app_num ="";
			String m_fin_num ="";
			String m_clnt_num="";
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
			m_app_num = req.getParameter("APP_NO");
			m_fin_num = req.getParameter("FIN_NO");
			m_clnt_num= req.getParameter("CLIENT_NO");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("main_page")){
					   
						  String m_sort_column   = "A.ENT_DATE";	
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
				out.println("help_box.innerHTML=\"Credit - Agreement Printing \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Credit - Agreement Printing - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				

			  out.println("function load_data(m_app_no) {");
				out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Printing?chksql=main_page&application_no=\"+m_app_no;"); 
				out.println("   window.open(m_url,'displayWindowap','left=50,top=60,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function load_data_report(m_app_no) {");
				out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=Report&print=TRUE&applicaton_no=\"+m_app_no;"); 
				out.println("   window.open(m_url,'displayWindowap','left=50,top=60,width=750,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				//out.println("   window.open(m_url)");
				
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
	      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Printing_View?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			  out.println(" window.location.href=m_url;"); 
				out.println("}");
			
        out.println("</Script>");
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
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
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Credit - Agreement Printing</td>");
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
				
				  
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
				  out.println("<td colspan=11 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' ></td>");
          out.println("<td colspan=13 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
				//	out.println("</table>");
					
					
				//	out.println("<table class=table border='0' width='100%' >");
          out.println("<tr class=pdn_txtpos2 align='center'>");
					out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO') >Application No</td>");
          out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Facility No  '       onclick=sort_data('FACILITY_NO') >Facility No</td>");
          out.println("<td  width='8%'  style= cursor:hand; title='Click here to sort by - Entered Date  '      onclick=sort_data('ENT_DATE') >Entered Date</td>");
          out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Period  '            onclick=sort_data('PERIOD') >Period(Days)</td>");
					out.println("<td  width='8%' style= cursor:hand; title='Click here to sort by - Marketing Officer  ' onclick=sort_data('MK_NAME') >Marketing Officer</td>");
					out.println("<td  width='7%' style= cursor:hand; title='Click here to sort by - Client Type  ' onclick=sort_data('CLIENT_CATEGORY') >Client Type</td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Client  '            onclick=sort_data('CLIENT') >Client</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Assets  '      onclick=sort_data('ASSET_COUNT') >Total Assets</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Pricing Status  '    onclick=sort_data('PRICING_STS') >Pricing Status</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pricing  '     onclick=sort_data('PRICING_COUNT') >Total Pricing</td>");
					//out.println("<td  width='10%' >Pro Forma Total Amount</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pro Forma  '   onclick=sort_data('PROFORMA_COUNT') >Total Pro Forma</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Valuation  '   onclick=sort_data('VALUATION_COUNT') >Total Valuation</td>");
					//out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Status  '            onclick=sort_data('APP_STS') >Status</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Currency  '          onclick=sort_data('CURRENCY_CODE') >Currency</td>");
					out.println("<td  width='5%'  >&nbsp;</td>");
          out.println("<td  width='5%'  >&nbsp;</td>");
					out.println("</tr>");
					 
           int j = 0;   
						
					 rs = stmt.executeQuery (" SELECT "+
							                        " A.APPLICATION_NO,"+ //1
																			" NVL(A.FACILITY_NO,'-'), "+ //2
																			"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE, "+ //3
																			" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
																			"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
																			"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+ //6
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+ //7
																			" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
																			" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
																			" NVL(A.CURRENCY_CODE,'-'), "+ //12
																			" NVL(A.CLIENT_CODE,'-'), "+ //13
																			"	NVL(INITCAP(X.CLIENT_CATEGORY),'-') CLIENT_CATEGORY  "+ //14
																			"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																			" "+m_schema_name+".AF_CO_MAS_CLIENT X "+
																			//" WHERE APPLICATION_STATUS IN ('VERIFYL','VERIFY','ACTIVATED','RE-APP','APPRO1','APPRO2') "+ //COMMENT BY NUWWAN DE SILVA 22-05-07
																			//" WHERE A.CLIENT_CODE=X.CLIENT_CODE AND A.APPLICATION_STATUS IN ('VERIFYL','ACTIVATED','RE-APP','APPRO1','APPRO2') AND "+ // commented by udara 14-01-2019
																			" WHERE A.CLIENT_CODE=X.CLIENT_CODE AND A.APPLICATION_STATUS IN ('VERIFYL','ACTIVATED','RE-APP','APPRO1','APPRO2','VERIFY-M','VERIFY2') AND "+ // added by udara 14-01-2019
																			" UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_app_num+"') AND "+  
																			" UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_fin_num+"') AND "+
																			" UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_clnt_num+"') "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+"");

              while(rs.next()){
							    
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}
									
									out.println("<td width='12%' align='center' style= cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1) +"</u></td>");
                  out.println("<td width='10%' align='center'>"+rs.getString(2) +"</td>");
                  out.println("<td width='8%' align='center'>"+rs.getString(3) +"</td>");
                  out.println("<td width='5%' align='center'>"+rs.getString(4) +"</td>");
                  out.println("<td width='8%' align='left'>"+rs.getString(5) +"</td>");
									out.println("<td width='7%' align='left'>"+rs.getString(14) +"</td>");
                  out.println("<td width='10%' align='left' style= cursor:hand; onClick=\"show_client('"+rs.getString(13)+"')\" ><u>"+rs.getString(6) +"</u></td>");
                  out.println("<td width='5%'  align='center'>"+rs.getInt(7) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getString(8) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getInt(9) +"</td>");
                  //out.println("<td width='10%' align='center'>"+nf.format(rs.getDouble(8))+"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getInt(10) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getInt(11) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getString(12) +"</td>");
                  out.println("<td width='5%'  align='center'><input class='mainbut1'  type='button' name=\"BUTTON_PO\" value=\"Print\" onclick=load_data('"+rs.getString(1)+"')></td>");
                  out.println("<td width='5%'  align='center'><input class='mainbut1'  type='button' name=\"BUTTON_SANCTION\" value=\"Report\" onclick=load_data_report('"+rs.getString(1)+"')></td>");
         //         out.println("<td width='5%'  align='center'>"+rs.getString(13) +"</td>");
                  //out.println("<td><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"Edit\"); >");
									//out.println("     </td>");
									out.println("</tr>");
                	j=j+1;
              }
							
				//	out.println("</table>");
					
       //   out.println("<table class=table border='0' width='100%' >");

					out.println("<tr class=tr_input>");
				  out.println("<td colspan=11 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td align=right colspan=13><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("</tr></table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

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
