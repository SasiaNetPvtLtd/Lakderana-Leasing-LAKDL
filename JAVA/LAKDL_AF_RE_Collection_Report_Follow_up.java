//Created by Nuwan De Silva
//Collection Report Follow_up 05-03-07

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Collection_Report_Follow_up extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_ac_status;
	ServletOutputStream out = null;
	int m_appno_count=0;
	boolean more;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
		  conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
     String m_username 						=m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			String m_chksql=req.getParameter("chksql");
			
	
			if(m_chksql.equals("main_page")){ 
			
			stmt = conn.createStatement ();

			
			String m_finance_no=req.getParameter("finance_no").trim();
			
			
			    		String sql_app_num=" SELECT APPLICATION_NO,FINANCE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
							" WHERE FINANCE_NO=UPPER('"+m_finance_no+"') "; 
				    			
							
							//----Added by Chandana on 02/08/2007 for Ref no.757 -------//
					rs=stmt.executeQuery(sql_app_num);
			    more=rs.next();
					String m_app_num="";
					
					if(more){
					m_app_num=rs.getString(1);
					}
					//-------------- End Ref no.757 ----------------------------// 
			
			 			//Modified by Mahela on 14-09-2007
			   
							String sql_fol=" SELECT "+
							" FOLLOW_UP_NO, "+//1
							" INITCAP(STATUS), "+//2
							" "+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER), "+//3
							" NVL((SELECT CATEGORY_NAME FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY WHERE CATEGORY_CODE=ACTION_TAKEN),'-'), "+//4
							//" NVL(ACTION_TAKEN,'-'), "+//4
							//" (SELECT to_char(SYSDATE,'DD-MM-YYYY') FROM DUAL ), "+//5
							" to_char(eff_val_date,'DD-MM-YYYY') , "+//5
							" NVL((SELECT CATEGORY_NAME FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY WHERE CATEGORY_CODE=ACTION_TOBE_TAKEN),'-'), "+//6
							//" NVL(ACTION_TOBE_TAKEN,'-'), "+//6
							" NVL(ENT_REMARKS,'-') "+ //7
							" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
							" WHERE DIVISION_CODE='AF' AND "+
							" SUB_DIVISION_CODE='RECOVERY' AND "+
							" ID_NO=UPPER('"+m_app_num+"') AND STATUS!='COMPLETED' ";
										
							
				     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
						
		//--------------------------------------------------------------------------		
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no="+m_finance_no+"';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no="+m_finance_no+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
						
			
			
			out.println("function before_submit(){ "); 
						
			out.println("		if(validate_data()){"); 
		
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Report_Follow_up';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter Remarks\");");
			out.println("} "); 
			
			out.println("} "); 
			
			
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_CONDITION.value==\"\" ){  "); 
			//out.println("VDATE.style.color='red';");
			
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_COLLECTION_FOLLOW_UP\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Collection Follow up - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Collection Follow up - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
		
   
			out.println("}"); 
			out.println("else{}");
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"LETTER\"){");  
			out.println("document.Form1.hid_status.value=\"Letter\";");  
			out.println("document.Form1.hid_save_status.value=\"Letter\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			
					
			out.println("function close_screen(){	"); 
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 

			out.println("window.close();");
			out.println("}");
			out.println("}");
			
			
			out.println("function Follow_up(val){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&status=Y&Followu_no='+val;");  //modified by nuwan de silva 06-08-2007
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
      out.println("}");
			
			
			//added by nuwan de silva on 14-09-07----------------------
			out.println("	function chk_comment_length(obj){ ");
			out.println(" var remarks_length=obj.value.toString().length;");
			out.println("if(remarks_length>obj.maxlength) ");
			out.println("		window.event.keyCode=\"\"; ");
			out.println("} ");
			
			//added by nuwan de silva on 14-09-07-----------------------
			out.println("function count_length(obj){ ");
			out.println("var remarks_length=obj.value.toString().length; ");
			out.println("var remarks=obj.value.toString(); ");
			out.println("if(remarks_length>obj.maxlength){ ");
			out.println("obj.value=remarks.substring(0,obj.maxlength); ");
			out.println("} ");
			out.println("} ");
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
							
			out.println("function load_c_date(val) {");
		  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.VAL_DAY.value=v_date;");
			out.println("     document.Form1.VAL_MONTH.value=v_month;");
			out.println("     document.Form1.VAL_YEAR.value=val;");
			//out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			out.println("  }");				
			out.println("}");
			out.println("}");
			
			out.println("function check_Date(val1,val2,val3) {");
			out.println("");
			out.println("}");


			
      out.println("</Script>");
			
			
				
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
				  				
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Cancel\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_print_status' VALUE=\"\">");
					out.println("<input type=hidden name='hid_cal_date' value=\"\">");

					
					
					
										
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Follow up</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//		out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Letter\");'  onclick='close_window()' value=\"Letter\"></td>"); 
					
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					
					out.println("</table>");  
					
					//out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
											
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					
					out.println("</table>");  
					
							
		      rs=stmt.executeQuery(sql_fol);
			     
			    more=rs.next();
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 
					
					if(more){

					out.println("<tr>");
		    	out.println("<td width=\"15%\" ><b>Follow up Number</td>"); 
          out.println("<td width=\"8%\" ><b>Status</td>"); 
					out.println("<td width=\"12%\" ><b>User</td>"); 
					out.println("<td width=\"10%\" ><b>Action</td>"); 
					out.println("<td width=\"10%\" ><b>Next Date</td>"); 
					out.println("<td width=\"10%\" ><b>Next Action</td>"); 
					out.println("<td width=\"*%\" ><b>Remark</td>"); 
					//out.println("<td width=\"*%\" >&nbsp;</td>"); 
					out.println("</tr>");
					
					//out.println("</table>");          
					
					
					}
					
					//out.println("<br>");          
					
			    //out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 
					int j=0;
				
		      while(more){
					//Modified by Mahela on 14-09-2007
					if(j>0 && j%2==1){
					out.println("<tr class=tr_input1 >");
					}
					else{
					out.println("<tr class=tr_input >");
					}
					
					//out.println("<tr >");
		    	out.println("<td width=\"15%\" valign='top' style= cursor:hand; onClick=Follow_up('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>"); 
          out.println("<td width=\"8%\"  valign='top' >"+rs.getString(2)+"</td>"); 
					out.println("<td width=\"12%\" valign='top'  >"+rs.getString(3)+"</td>"); 
					out.println("<td width=\"10%\" valign='top'  >"+rs.getString(4)+"</td>"); 
					out.println("<td width=\"10%\" valign='top'  >"+rs.getString(5)+"</td>"); 
					out.println("<td width=\"10%\" valign='top'  >"+rs.getString(6)+"</td>"); 
					out.println("<td width=\"*%\"   >"+rs.getString(7)+"</td>"); 
					//out.println("<td width=\"*%\" >&nbsp;</td>"); 
					out.println("</tr>");
					
					more=rs.next();
				  j=j+1;
				  }
				  out.println("</table>");    
					
					out.println("<br><br><hr>");    
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='60%'>   "); 
								  
					out.println("<tr >");
		    	out.println("<td width=\"*%\" ><b>Enter New Follow up</b></td>"); 
     			out.println("</tr>");
					
					out.println("</table>");       
					
					out.println("<br>");    
					
					out.println("<table class='table' border='1' cellpadding='0' cellspacing='0' width='100%'>   "); 
								  
					out.println("<tr class=pdn_txtpos2>");
					
		    	out.println("<td width=\"30%\" >Follow up Number</td>"); 
          out.println("<td width=\"30%\" >Remarks</td>"); 
					out.println("<td width=\"*%\" >Next Action Date</td>"); 
					out.println("</tr>");
					
					out.println("<tr>");
		    	out.println("<td width=\"30%\" ><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO disabled></td>"); 
     			//out.println("<td width=\"30%\" ><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION style=\"width:500px; height:50px;\" maxlength=\"200\" size=\"200\" ></td>");
					out.println("<td width='30%' ><TEXTAREA class='txt_input' name=TXT_CONDITION style=\"width:350px; height:70px;\" maxlength=\"200\" size=\"200\"  onkeyPress=\"chk_comment_length(this)\"  onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>");  //modified by nuwan de silva on 14-09-07
					out.println("<input type=hidden name=hid_finance_no value=\""+m_app_num+"\">"); //    m_finance_no
					out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("<input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("<input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a><td> ");
					out.println("</tr>");
					
					out.println("</table>");       
		
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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
