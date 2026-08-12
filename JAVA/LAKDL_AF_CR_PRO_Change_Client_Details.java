import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Change_Client_Details extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt2,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs2,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	String m_return_status="N";
	
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
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
		  String m_username =  con_method.username;
		
			String m_pre_stage;
			String m_pre_stage1;
			String m_app_stage;		
			String m_close;
			String m_new_stage;

							int sel_stage=0;
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			
			//m_chksql = "main_page";
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt1 = conn.createStatement();
			
			
			m_pre_stage=req.getParameter("pre");
				//m_pre_stage = "ENT_CON";
			m_app_stage=req.getParameter("appro");
			   //m_app_stage = "VERIFY1";
			m_pre_stage1=req.getParameter("qry");
			   //m_pre_stage1 = "ENT_CON";
			
			String _m_client_name ="";

			
			m_close=req.getParameter("CLS");
			
			//out.println("udara"+m_chksql);

			if (m_chksql==null) {
					m_chksql = "main_page";
					m_pre_stage = "ENT_CON";
					m_app_stage = "VERIFY1";
					m_pre_stage1 = "ENT_CON";
					//out.println("udara"+m_chksql);
			}
			//else{
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
							//added by nuwan de silva on 23-11-2007------
							else if(m_chksql.trim().equals("main_page")){
		
		
      	out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			// Help Box for main_page section
			
			out.println("function MyDialog(){"); 
			out.println("	this.valout   = new Array(10);"); 
			out.println("}"); 
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_MAS_help_select_client\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); //MAIN
			out.println("						help_update_value_assign_99();"); 
	  		out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"10\"){"); 
			out.println("						help_update_value_assign_10();"); 
	  		out.println("					}"); 
			out.println("				}"); 
			out.println("				else{"); 
			out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 
			out.println("			}"); 
			out.println("			else{	"); 
			out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("			}	"); 
			out.println("	 	}"); 
			out.println("	 	else{	"); 
			out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); //MAIN
			out.println("							document.Form1.TXT_CLIENT_NAME.value='';"); 
			out.println("		               search_client_details('');");
	  		out.println("					}"); 	
			out.println("	 	}	"); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); //MAIN
			out.println("							document.Form1.TXT_CLIENT_NAME.value='';");
			out.println("		               search_client_details('');");
	  		out.println("					}"); 			
			out.println("	}");
			out.println("}"); 
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println("		document.Form1.hid_client_code.value=oBj.valout[2];"); 
			out.println("		document.Form1.hid_client_type.value=oBj.valout[7];");
			out.println("		search_client_details(oBj.valout[2]);"); 
			out.println("}");
			
			out.println("function help_update() {"); 
			out.println(" document.Form1.hid_help_type.value=\"99\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_FACTOR_CLIENT_NAME_CHANGE_CLIENT_sql\";"); 
			//out.println(" m_sql = \"m_help_CHANGE_CLIENT_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_CLIENT_NAME.value+\"@\";"); 
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			// End Help Udara 05-05-2010


			out.println("function befor_end(m_obj) {");
         out.println("   m_obj.focus();");
         out.println("}");
				
			out.println("function load_roll_value(m_val){");
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
			out.println("if(m_val==''){");
			out.println("help_box.innerHTML=\"Credit  - Credit Verifcation \";"); 
			out.println("}else{");
			out.println("help_box.innerHTML=\"Credit  - Change Client Details - \"+m_val;"); 
			out.println("}");
			out.println("}");
			out.println("}");
				
			out.println("function load_roll_value_1(){"); 
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
			out.println("var m_val=\"Verfy\";");
			out.println("help_box.innerHTML=\"Credit  - Change Client Details - \"+m_val;"); 
	
			out.println("}");
			out.println("}");

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			/*out.println("function edit_client(){	"); 
			out.println("	m_url = \"LAKDL_AF_MAS_display_client_creation?m_client_status=A&client_type=\"+document.Form1.hid_client_type.value+\"&screen=G&close_status=Y&client_code=\"+document.Form1.hid_client_code.value;");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}	"); 
			*/
			
			
			out.println("function edit_client_2(code,type){	"); 
			//out.println("	m_url = \"LAKDL_AF_MAS_display_client_creation?m_client_status=A&client_type=\"+type+\"&screen=G&close_status=Y&active_status=Y&client_code=\"+code;");
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_display_client_creation?m_client_status=A&client_type=\"+type+\"&screen=G&close_status=Y&active_status=Y&client_code=\"+code;");//old one ,comment by waruna
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_display_client_creation?m_client_status=A&client_type=\"+type+\"&screen=G&close_status=Y&fscreen=chg_Cln_details&active_status=Y&client_code=\"+code;"); //add by waruna "fscreen"
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
			out.println("}	"); 
			


			out.println("function load_data(m_app_no,m_app_sts) {");
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
	      out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&application_no=\"+m_app_no;"); 
			out.println("   window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("  }");
			out.println("}");
			
			out.println("function load_roll_out_value(m_val){");
			out.println("if('"+m_pre_stage+"'=='ENT_CON'){");
			out.println("help_box.innerHTML=\" Credit  - Change Client Details - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='V-APP'){");
			out.println("help_box.innerHTML=\" Credit  - Credit Approval 1 - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			out.println("else if('"+m_pre_stage+"'=='VERIFY-M'){");
			out.println("help_box.innerHTML=\" Credit  - Credit Approval 2 - \"+document.Form1.hid_status.value;"); 
			out.println("}");
			out.println("}");
			
			out.println("function load_help_msg() {"); 
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_Application_Status_Report_appr1\";"); 
			}
			
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
 			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
					

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"APPROVE\"){");
			out.println("document.Form1.hid_status.value=\"Approve\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"REVERSE\"){");  
			out.println("document.Form1.hid_status.value=\"Reverse\";"); 
			out.println("document.Form1.hid_save.value=\"Reverse\";"); 
			out.println("}else if(m_val==\"CLOSE\"){");  
			out.println("document.Form1.hid_status.value=\"Close\";");  
			out.println("document.Form1.hid_save.value=\"Close\";");  
			out.println("}else if(m_val==\"HELP\"){");  
			out.println("document.Form1.hid_status.value=\"Help\";"); 
			out.println("document.Form1.hid_save.value=\"Help\";");  
			out.println("}else if(m_val==\"VIEW\"){");  
			out.println("document.Form1.hid_status.value=\"ViewLetter\";");  
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("m_scr=m_val");
			out.println("}"); 

			out.println("function search_client_details(m_client_code) {"); 

			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Change_Client_Details?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_prev+\"&division_code=\"+m_division_code+\"&m_client_name=\"+m_client_name+\"&option=\"+m_option;"); 
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Change_Client_Details?chksql=load_data_client&m_client_code=\"+m_client_code;"); 
			out.println("load_interface(m_url,'NORM');");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");

         out.println("</Script>");
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"load_roll_value_1()\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input  type='hidden' value=\"APPROVE\" name=\"SCREEN_NAME\"> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">");
         out.println("<input type=hidden name=\"ROW_ID\" ></td>");
			out.println("<input type=hidden name=\"hid_save\" value=\"Save\" ></td>");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name_verification' VALUE=\"AF_MK_APP_STATUS_APPROVE_1\">");  // added by nuwan de silva on 17-12-2007
			
			
			if(m_pre_stage.equals("ENT_CON")){
			out.println("<input type='Hidden' name='hid_status' value=\"Verify\">");
			}

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

			if(m_pre_stage.equals("ENT_CON")){
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Change Client Details</td>"); 
			}

			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> ");
			
			out.println("<tr>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  name=Close onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='load_screen_status(\"CLOSE\"),close_window();' value=\"Close\"></td>");  

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
								
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			out.println("<tr class=tr_input>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='15' style=\"{width:250px;}\" size='15' >");  //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\"
			//out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value=\"Help\" onClick=\"help_update();\">");
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value=\"Search\" onClick=\"search_client_details(document.Form1.TXT_CLIENT_NAME.value)\"></td>"); //m_help_TXT_APPLICATION_NO
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 

			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>"); 
								
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
      out.println("</body>"); 
			out.println("</html>"); 
       }

			else if(m_chksql.trim().equals("load_data_client")){
			
					String client_code = req.getParameter("m_client_code");
			
					int j = 0;
			
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width=\"10%\" align=\"center\" >Client Code</td>"); 
					out.println("<td width=\"10%\" align=\"center\" >Application No</td>");
					out.println("<td width=\"10%\" align=\"center\" >Finance No</td>"); 
					out.println("<td width=\"10%\" align=\"center\" >Client Type</td>"); 
					out.println("<td width=\"10%\" align=\"center\" >Full Name</td>"); 
					out.println("<td width=\"10%\" align=\"center\" >Active Status</td>"); 
					out.println("<td width=\"10%\" align=\"center\" > </td>"); 
					out.println("</tr>");

					rs = stmt.executeQuery (" SELECT A.CLIENT_CODE,NVL(B.APPLICATION_NO,'-'),NVL(B.FINANCE_NO,'-'),A.CLIENT_TYPE,A.FULL_NAME,A.ACTIVE_STATUS "+
					     " ,DECODE(A.CLIENT_TYPE,'I','Individual','C','Cooparate') "+
			 			   " FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			 				" WHERE 	"+	 
            			" A.CLIENT_CODE = B.CLIENT_CODE "+
									//" AND B.APPLICATION_STATUS = 'ACTIVATED'  "+ // commented by udara 17-10-2017  //--comment by ns 07-06-2010 
									" AND B.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','NORM_TERMI' ) "+   // added by udara 17-10-2017
			 				" AND (UPPER(A.CLIENT_CODE) LIKE UPPER('%"+client_code+"%') OR UPPER(A.FULL_NAME) LIKE UPPER('%"+client_code+"%') OR UPPER(A.NIC_NO) LIKE UPPER('%"+client_code+"%') OR UPPER(A.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+client_code+"%') OR UPPER(B.APPLICATION_NO) LIKE UPPER('%"+client_code+"%') OR UPPER(B.FINANCE_NO) LIKE UPPER('%"+client_code+"%')) "+ // Thamali on 2010.12.27
							" AND A.ACTIVE_STATUS='Y' ");
											
					while(rs.next()){	
					
						if(j>0 && j%2==1){
      					out.println("<tr class=tr_input1 >");
						}
						else{
							out.println("<tr class=tr_input >");
						}
						out.println("<tr>");
						out.println("<td width='5%'  align='left'>"+rs.getString(1) +"</td>"); //
                  out.println("<td width='5%'  align='left'>"+rs.getString(2) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getString(3) +"</td>");
                  out.println("<td width='5%'  align='left'>"+rs.getString(7) +"</td>"); //
                  out.println("<td width='5%'  align='left'>"+rs.getString(5) +"</td>");
						out.println("<td width='5%'  align='left'>"+rs.getString(6) +"</td>");
						//out.println("<td align=\"center\"><input class='but_input' type='button' name=EDIT_CLI_"+j+" value=\"Edit\" onclick=\"edit_client();\" ></td>");
						out.println("<td align=\"center\"><input class='but_input' type='button' name=EDIT_CLI_"+j+" value=\"Edit\" onclick=\"edit_client_2('"+rs.getString(1) +"','"+rs.getString(4) +"');\" ></td>");
						out.println("</tr>");
						
						j=j+1;
					
					}
										
										
					out.println("</table>");
								

			} // else if load_data_client
			
  				
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
		  if(rs1    !=null){try{rs1.close();   }catch(Exception e){}}
		  if(rs2    !=null){try{rs2.close();   }catch(Exception e){}}
			
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(stmt1  !=null){try{stmt1.close(); }catch(Exception e){}}
			if(stmt2  !=null){try{stmt2.close(); }catch(Exception e){}}
			
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
