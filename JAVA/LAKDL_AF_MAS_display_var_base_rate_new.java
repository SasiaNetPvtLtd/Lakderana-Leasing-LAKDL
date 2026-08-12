
//Created By Lalanka on 25-01-2010 For OFSCL-SYSTEM ADMINISTATION-PRICING

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_var_base_rate_new extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs;
	Statement stmt ;
	Connection conn;
	String reqstr;
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
		
	//******************************************************************************************
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
			String m_schema_name = m_sn_methods.schema_name;
  //********************************************************************************************
		
			
			 				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Variable Base Rate Approve</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_flag=0");
			out.println("var b_flag1=0");

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
/*
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){");
			
			out.println("if(oBj.valout[2]==' '){");
			out.println("document.Form1.TXT_MAKE_CODE.value=\"\"");
			out.println("document.Form1.TXT_SUB_MODEL_CODE.value=\"\"");
			out.println("document.Form1.TXT_MILEAGE_CODE.value=\"\"");
			out.println(" sub_charge_details.innerHTML = ''; ");
			out.println("}");

			
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			
			out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
			out.println("		help_value_assign_item_category();"); 
	  	out.println("		}"); 
			
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{");
			out.println("	clear_data();");
			out.println("	}");
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 
			
			
				

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
					 
			*/
			
			     						
			
		//***************************************************	
				out.println("function load_lock(){	"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_var_base_rate_new';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_var_base_rate_new';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_maintenance_rate\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Variable Base Rate Approve - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Variable Base Rate Approve - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 			
			out.println("}");
				
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

				
			out.println("function get_vector(data_vec) {");
			out.println("if(data_vec.length >0){");
			out.println("	alert('This record already exists');");
			out.println("			}");		
			out.println("}");
			
			out.println("function makeRequest(obj,day,month,year,code) {");			
			out.println("apply_date=day.value+'-'+month.value+'-'+year.value;");
			out.println("b_code=code.value;");		
			out.println("if(obj.checked==true){");			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_base_code_check_LAKDL_AF_MAS_display_var_base_rate&app_date=\"+apply_date+\"&base_code=\"+b_code;");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
					
		out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:47em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println("");
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function ViewNext(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
		
		
			out.println("function before_submit(){ ");			
			out.println("ckeck_data();");
			out.println("if(val_data()){");
			out.println("if(b_flag==1){");
			out.println("		if(confirm(\"Are you sure you want to save ? \")){ "); 
			out.println("document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_base_rate_new?number='+document.Form1.hid_count.value+'';");  
			out.println("document.Form1.submit();	");
			out.println("}");	
			out.println("}");			
			out.println("}");
			out.println("}");
			
			out.println("function count_select_base(){ ");
			out.println("count=0;");			
			out.println("for(i=1;i<parseInt(document.Form1.hid_count.value);i++){");
			out.println("if(document.Form1.elements[\"chk_update_\"+i].checked==true){");
			out.println("count=count+1;");	
			out.println("}");					
			out.println("}");
			out.println("document.Form1.hid_chk_count.value=count;");
		 	out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			out.println("}"); 
						
			out.println("function ckeck_data(){ ");			
			out.println("if(!count_select_base()){"); 
			out.println("alert('No selected data..!');");
			out.println("b_flag=0;");
			out.println("}else{"); 
			out.println("b_flag=1;}"); 		
			out.println("}");
			
			out.println("function val_data(){");
			out.println("for(i=1;i<parseInt(document.Form1.hid_count.value);i++){");
			out.println("if(document.Form1.elements[\"chk_update_\"+i].checked==true){");
			//out.println("if(document.Form1.elements[\"base_rate_dd_\"+i].value==\"\" ||document.Form1.elements[\"base_rate_mm_\"+i].value==\"\" || document.Form1.elements[\"base_rate_yy_\"+i].value==\"\"){");
			//out.println("alert('Enter valid Apply Date..!');");
			//out.println("return false;");
			out.println("if(document.Form1.elements[\"txt_new_rate_\"+i].value==\"\" ){");
			out.println("alert('Enter new Rate value..!');");
			out.println("return false;");
			out.println("}else{");
			out.println("return true;");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function load_calendar(num,row_no) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println(" document.Form1.hid_row_no.value=row_no;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
			out.println("m_row=document.Form1.hid_row_no.value");
			
			out.println("     document.Form1.elements[\"base_rate_dd_\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"base_rate_mm_\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"base_rate_yy_\"+m_row].value=val;");
					
			out.println("  }");				
			out.println("}");
			out.println("}");
			
			out.println("function chkstartdate(dayobj,monthobj,yearobj) {");
			out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!=''){");
			out.println("	  checkMonthLength(dayobj,monthobj,yearobj);		");
			out.println(" }");
	    out.println("}");
			
							
						 
			
			//***********************************************************************

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_base_code' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_count' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"\">");			
			out.println("<input TYPE='hidden' VALUE='AF_AD_VAR_BASE_RATE' NAME='Hid_scr_name'> ");
			out.println("<INPUT TYPE='Hidden' NAME='SCREEN_NAME' VALUE=\"\">"); 			
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_model_code' VALUE=\"\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Variable Base Rate Approve</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='' value=\"Help\"></td>");  //load_screen_status(\"HELP\")
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
      out.println("<br >"); 
			
			out.println("<table border=0 width='80%' class='table'>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' align='center'>Base </td>"); 
			out.println("<td width='20%' align='center'>Description</td>"); 
			//out.println("<td width='10%' align='center'>Rate</td>"); 
			out.println("<td width='10%' align='center'>Apply Date</td>"); 
			out.println("<td width='10%' align='center'>Rate</td>"); 
			out.println("<td width='5%' align='center'>Update</td>"); 
			out.println("</tr>"); 
		
	
	rs = stmt.executeQuery( " SELECT DISTINCT "+
													" A.BASE_CODE, "+ 
													" B.DESCRIPTION, "+
													" TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY'), "+
													" A.RATE "+
													" FROM "+m_schema_name+".AF_CO_PRO_INTEREST_BASE_RATE A, "+
													"      "+m_schema_name+".AF_CO_MAS_INTEREST_BASE B "+
													" WHERE A.BASE_CODE=B.BASE_CODE AND  A.STATUS = 'N' ");		
		
		
		boolean more = rs.next();	
			int j=1;
			while(more){
			
			if(j>0 && j%2==1){
				out.println("<tr class=tr_input >");
				}
			else{
				out.println("<tr class=tr_input1 >");
			 }
			out.println("<input type='hidden' name='hid_base_code_"+j+"' value="+rs.getString(1)+">");
			out.println("<td width='10%' align='left'><class=div_input>"+rs.getString(1)+"</td>"); 
			out.println("<td width='20%' align='left'><class=div_input>"+rs.getString(2)+"</td>"); 
			out.println("<td width='10%' align='center'><class=div_input>"+rs.getString(3)+"</td>"); 
			//out.println("<td width='10%' align='right'><class=div_input>"+rs.getString(4)+"</td>"); 
			/*out.println("<td width='20%' align='center'><class=div_input>");
			out.println("<input type='text' name='base_rate_dd_"+j+"' class=\"txt_input\" style=\"width: 25px\" maxlength='2' onblur=\"chkstartdate(document.Form1.base_rate_dd_"+j+",document.Form1.base_rate_mm_"+j+",document.Form1.base_rate_yy_"+j+")\" > "); 
			out.println("<input type='text' name='base_rate_mm_"+j+"' class=\"txt_input\" style=\"width: 25px\" maxlength='2' onblur=\"chkstartdate(document.Form1.base_rate_dd_"+j+",document.Form1.base_rate_mm_"+j+",document.Form1.base_rate_yy_"+j+")\" > "); 
			out.println("<input type='text' name='base_rate_yy_"+j+"' class=\"txt_input\" style=\"width: 45px\" maxlength='4' onblur=\"chkstartdate(document.Form1.base_rate_dd_"+j+",document.Form1.base_rate_mm_"+j+",document.Form1.base_rate_yy_"+j+")\" > ");
			out.println("<span style= cursor:hand;cursor-color:blue;color:black onclick=load_calendar('2',"+j+")>Calender</span>"); 
			out.println("</td>");*/ 
			out.println("<td width='10%' align='center'><class=div_input><input type='text' class=\"txt_input\" maxlength='6' onblur=\"format_number3(this,2)\" style=\"width: 45px\" name='txt_new_rate_"+j+"' value='"+rs.getString(4)+"'></td>"); 
			out.println("<td width='5%' align='center'><class=div_input><input type='checkbox' name='chk_update_"+j+"' ></td>"); 
			out.println("</tr>"); 
			out.println("<input type=hidden name='hid_val_date_"+j+"' value="+rs.getString(3)+">");
			more = rs.next();
			j=j+1;
			}
			out.println("<input type=hidden name=hid_count value="+j+">");	
				
			out.println("</table>"); 
			out.println("<br>"); 
						
			out.println("<table align=\"center\" width=\"80%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=sub_charge_details></div></td></tr></table>");

			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			
			
			
			
			out.flush();
		
	//*****************************************************************************************
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
