//DEVELPED BY : SANDUN 
//ON 11-11-2008

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Val_Vehicle_In_Yard extends javax.servlet.http.HttpServlet {
	
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
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			int m_count_payment_no=0;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("yard_vehicle_detals")){
					  						
				 String  m_yard = req.getParameter("yard").trim();				
				  
				
					out.println("<hr>");
					out.println("<table class=table border='0' width='100%' >");					
					out.println("<tr class=tr_input>");
				  out.println("<td colspan=10 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");					
					
					out.println("<tr class=pdn_txtpos2 >");
					out.println("<td  width='15%' align='left'>Finance No</td>");
					out.println("<td  width='15%' align='left'>Inventry No</td>");
          out.println("<td  width='15%' align='left'>Vehicle No</td>");
          out.println("<td  width='15%' align='left'>Seizer Code</td>");
					out.println("<td  width='25%' align='left'>Seizer Name</td>");
					out.println("<td  width='5%'  align='center'>Valuation</td>");
					out.println("</tr>");
					 
           int j = 0;   
						
					//Commented by Dineth on 2009-01-20
							 /*rs = stmt.executeQuery (" SELECT DISTINCT A.INVENTORY_NO, "+ //1
																		   " A.VEHICLE_NO, "+ //2
																		   " A.SEIZER_CODE, "+//3
																		   " NVL("+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE),'-'), "+//4
																			 " B.ENGINE_NO, "+ //5
																			 " B.CHASSIS_NO, "+ //6
																			 " B.INVOICE_NO "+ //7
																	     " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A ,"+
																			 "      "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
																			 " WHERE  A.VEHICLE_NO = B.REG_NO "+
																			 " AND    A.YARD_CODE  = '"+m_yard+"' ");*/
				//Added by Dineth on 2009-01-20
				rs = stmt.executeQuery(" SELECT DISTINCT NVL(A.INVENTORY_NO,'-'), "+ //1
                               " NVL(A.VEHICLE_NO,'-'), "+ //2
                               " NVL(A.SEIZER_CODE,'-'), "+//3
                               " NVL("+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE),'-'), "+//4
                               " NVL(B.ENGINE_NO,'-'), "+ //5
                               " NVL(B.CHASSIS_NO,'-'), "+ //6
                               " NVL(C.PRO_INVOICE_NO,'-'), "+ //7
															 " NVL(C.FINANCE_NO,'-') "+//8
                               " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A ,"+
                               " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
                               " "+m_schema_name+".AF_RE_PRO_REPOSSESSION C, "+
                               " "+m_schema_name+".AF_CO_PRO_APP_VALUATION D "+
															 " WHERE  A.REPOSSESSION_NO=C.REPOSSESSION_NO "+
															 " AND C.PRO_INVOICE_NO=B.INVOICE_NO(+) "+
															 " AND A.VALUATION_STATUS IS NULL "+
															 " AND A.ACTIVE_STATUS='APP' "+
																" AND C.ACTIVE_STATUS='Y' "+	
                               " AND B.ASSET_ID=D.ASSET_ID "+
                               " AND D.INVENTORY_NO IS NULL "+
                               " AND    A.YARD_CODE  = '"+m_yard+"' "+
                               " UNION ALL "+
                               " SELECT DISTINCT NVL(A.INVENTORY_NO,'-'), "+ //1
                               " NVL(A.VEHICLE_NO,'-'), "+ //2
                               " NVL(A.SEIZER_CODE,'-'), "+//3
                               " NVL("+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE),'-'), "+//4
                               " NVL(B.ENGINE_NO,'-'), "+ //5
                               " NVL(B.CHASSIS_NO,'-'), "+ //6
                               " NVL(C.PRO_INVOICE_NO,'-'), "+ //7
															 " NVL(C.FINANCE_NO,'-') "+//8
                               " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A ,"+
                               " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
                               " "+m_schema_name+".AF_RE_PRO_REPOSSESSION C "+
                               " WHERE  A.REPOSSESSION_NO=C.REPOSSESSION_NO "+
                               " AND C.PRO_INVOICE_NO=B.INVOICE_NO(+) "+
															 " AND A.VALUATION_STATUS IS NULL "+
															 " AND A.ACTIVE_STATUS='APP' "+
							   " AND C.ACTIVE_STATUS='Y' "+								
                               " AND B.ASSET_ID NOT IN (SELECT D.ASSET_ID "+
                               " FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION D "+
                               " WHERE D.INVENTORY_NO IS NULL) "+
                               " AND    A.YARD_CODE  = '"+m_yard+"' ");
				//End by Dineth on 2009-01-20																																	
              while(rs.next()){
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}
									out.println("<td width='15%' align='left' class=div_input style=cursor:hand;cursor-color:blue onclick=\"show_finance_detail_drill('"+rs.getString(8)+"');\" ><u>"+rs.getString(8) +"</u></td>");								
									out.println("<td width='15%' align='left' >"+rs.getString(1) +"</td>");
                  out.println("<td width='15%' align='left' >"+rs.getString(2)+"</td>");
                  out.println("<td width='15%' align='left'>"+rs.getString(3)+"</td>");									
                  out.println("<td width='25%' align='left' >"+rs.getString(4) +"</td>");
									out.println("<TD WIDTH='5%' align=\"center\"><INPUT TYPE=\"button\" NAME=valuation_btt VALUE=\"Valuation\" class='but_input' onclick=\"valuation_vehicle('"+rs.getString(5)+"','"+rs.getString(6)+"','"+rs.getString(1)+"','"+rs.getString(2)+"')\" ></td>");			
									out.println("</tr>");
                	j=j+1;
              }
							
									
					out.println("<tr class=tr_input>");				 
          out.println("<td align=right colspan=10><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr></table>");
          		

      }
				else if(m_chksql.trim().equals("main_page")){
				
				rs = stmt.executeQuery(" SELECT A.YARD_CODE, "+ 
												      " A.NAME "+
															" FROM "+m_schema_name+".AF_CO_MAS_YARD A "+
															" WHERE A.ACTIVE_STATUS = 'Y' ");
				
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Valuation For Vehicle In Yard</title>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				
							
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Valuation For Vehicle In Yard \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Valuation For Vehicle In Yard - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				
				out.println("function valuation_vehicle(en_no,ch_no,inv_no,reg_no){")	;
        out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Vehicle_Inventory_Valuation?inv_no=\"+inv_no+\"&vehicle_no=\"+reg_no+\"&chassis_no=\"+ch_no+\"&engine_no=\"+en_no+\" \";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=900,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}");	
				

				out.println("function validate_data(){"); 
				out.println("return true;"); 
				out.println("}"); 
												
				
			 out.println("function get_vehicle_detail(){");			
			 out.println("m_yard= document.Form1.TXT_YARD.value;");
			 out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Val_Vehicle_In_Yard?chksql=yard_vehicle_detals&yard=\"+m_yard+\"\";");
			 out.println("load_interface(m_url,'NORM');");
			 out.println("}"); 
			
			 out.println("function get_vector_normal(http_response) {");
			 out.println(" m_table.innerHTML = ''; ");
			 out.println(" m_table.innerHTML = http_response; ");
			 out.println("}");
	
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Valuation For Vehicle In Yard - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"\";");  
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
					out.println("function load_screen_status(m_val){"); 					
					out.println("if(m_val==\"HELP\"){"); 
					out.println(" load_help_msg();");
					out.println("}"); 
					out.println("else{");
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("}"); 
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
					out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Val_Vehicle_In_Yard?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 
					
					out.println("</Script>");

				
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">"); //load_lock()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			  out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"CREDIT_NOTE_CANCEL\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");

										
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
					out.println("<td class='border_wht' valign='top'> "); 
					out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
					out.println("<tr> "); 
					out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Valuation For Vehicle In Yard</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='10%'></td>"); 
					out.println("<td width='10%'></td>");
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='6%'></td>");
					out.println("<td width='6%'></td>");
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  
					
					
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<tr class=tr_input>"); 
				out.println("<td width='5%'>&nbsp;</td>");
				out.println("<td width='10%' ><DIV id='DIV_TXT_YARD' class=div_input>Yard Id</DIV></td>"); 
				out.println("<td><select name='TXT_YARD' class=div_input style='width:150' >");
				while(rs.next()){
				out.println("<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>");
				}
				out.println("</select></td>");				
				out.println("<td width='10%'><input type='button' class='but_input' name='MAIN_BBT' value= 'Search' OnClick ='get_vehicle_detail()'></td>");
				out.println("<td width='50%'>&nbsp;</td>");
				out.println("<td width='*%'>&nbsp;</td>");
				out.println("</tr>");										
				out.println("</table>"); 

 			 out.println("<table align='center' width='100%' class='table'>"); 
			 out.println("<tr>");  
		   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
	     out.println("</tr>"); 
	
		   out.println("</table>");
			  
				out.println("</td></tr><tr>");  
			  out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  	
			  out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				
				
			out.println("</td></tr>");  
			out.println("</table>");  	
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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
