//--
//SCREEN NAME :INVENTORY APPROVAL
//CREATED BY	:DELANJALI
//DATE/TIME		:2007-02-27
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_rental_freeze extends javax.servlet.http.HttpServlet { 



	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
			ServletOutputStream out = null;
			ResultSet rs=null;
			Statement stmt=null;
			Connection conn=null;
			String m_chksql="";
			java.text.NumberFormat nf,nf1;
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn=m_sn_methods.met_user_validate(req);
			stmt=conn.createStatement();
			
			m_chksql=req.getParameter("chksql");
			String m_schema_name=m_sn_methods.schema_name;
			
			if(m_chksql.equals("main_page")){

			 String m_sort_column   = "A.INVENTORY_NO";	
			 String m_order_by_type = "ASC";
							
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			       m_sort_column = req.getParameter("sort_column");
			       m_order_by_type = req.getParameter("order_by_type");
				}


			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Repossesion- Rental Freez</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_flag=0");
			
			out.println("function count_docs(){ ");
			out.println("count=0;");
			out.println("for(i=0;i<document.Form1.hid_count_inv.value;i++){");
			out.println("m_chk=\"chk_app_\"+i;");
			out.println("if(document.Form1.elements[m_chk].checked==true){");
			out.println("count=count+1;");
			out.println("}");		
			out.println("}");		
			out.println("if(count>0){");
			out.println("return true;");
			out.println("}"); 
			out.println("else{");
			out.println("return false;");
			out.println("}"); 
			out.println("}");
			
						
			out.println("function check_select(){ "); 
			out.println("if(!count_docs()){"); 
			out.println("alert('Please Select Contract to Freez');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("check_select()");
			out.println("if(b_flag!=1){");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_save_rental_freez';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} ");
			out.println("} "); 


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
	    out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_freeze?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
		  out.println(" window.location.href=m_url;"); 
			out.println("}");


			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_rental_freeze?chksql=main_page&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_rental_freeze?chksql=main_page&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_OFSCL_AF_RE_display_rental_freez\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Repossesion - Rental Freez - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Repossesion - Rental Freez - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_REPOSSESSION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_SEIZER_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_INVENTORY_NO.disabled=true;"); 
			out.println("document.Form1.TXT_YARD_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_INVOICE_AMOUNT.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Servlet?class_in=\"+client_name+\"AF_RE_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
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
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("		help_value_assign_5();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
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
			out.println(""); 



			out.println("function change(row) {");
			out.println("   if(document.Form1.elements[\"chk_app_\"+row].checked==true){"); 
			out.println("document.Form1.elements[\"chk_app_\"+row].value='Y'");
			out.println("}");	
			out.println("  else if(document.Form1.elements[\"chk_app_\"+row].checked==false ){"); 
			out.println("document.Form1.elements[\"chk_app_\"+row].value='N'");
			out.println("}");	
			out.println("}"); 
			
			out.println("function befor_end(m_obj) {");
			out.println("   m_obj.focus();");
			out.println("}");
				
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Repossesion - Rental Freez</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			rs = stmt.executeQuery ("SELECT A.INVENTORY_NO,A.REPOSSESSION_NO,B.FINANCE_NO,"+
			" DECODE(B.REPOSSESS_TYPE,'OFFICER',B.REPOSSESS_OFFICER,'SEIZER',B.SEIZER_CODE,'COMPANY',B.REPOSSESS_OFFICER)  SEIZER_CODE, "+//Sandun on 03-07-2009
			" NVL(DECODE(B.REPOSSESS_TYPE,'OFFICER',"+m_schema_name+".AF_CO_GET_EMP_NAME(B.REPOSSESS_OFFICER),'SEIZER',"+m_schema_name+".AF_CO_GET_SEIZER_NAME(B.SEIZER_CODE),'COMPANY',"+m_schema_name+".AF_CO_GET_EMP_NAME(B.REPOSSESS_OFFICER)),'-') AS SEIZER_NAME , "+ ////Added By Sandun on 02-07-2009
			"	A.CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(A.CLIENT_CODE) AS CLIENT_NAME,A.YARD_CODE, "+
			"(SELECT NAME FROM "+m_schema_name+".AF_CO_MAS_YARD WHERE YARD_CODE=A.YARD_CODE) AS YARD_NAME,TO_CHAR(B.INVOICE_AMOUNT,'9,999,999,999,999,999,999,999,999.99') AS INVOICE_AMOUNT ,NVL("+m_schema_name+".AF_CO_GET_SEIZER_FEE(A.SEIZER_CODE),0) "+
			"FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
			"WHERE A.INVENTORY_NO=B.INVENTORY_NO "+
			"AND A.ACTIVE_STATUS='APP' "+
			"AND B.FINANCE_NO NOT IN ("+
			" SELECT FINANCE_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_RENTAL_FREEZ_DETAILS "+
			")"+
			"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
			
	
				
			
			out.println("<br>");
			
			
			out.println("<table align='center' width='100%' border=\"0\" class='table'>");
			out.println("<tr class=tr_input>");
			out.println("<td align=right colspan=13><input type=button name=top_b     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(\"Top\");'></td>");

			out.println("<tr class=\"pdn_txtpos2\">"); 
			//out.println("<td  width='11%' style= cursor:hand; title='Click here to sort by - Inventory No  '    onclick=sort_data('A.INVENTORY_NO') >Inventory No</td>");
			//out.println("<td  width='11%' style= cursor:hand; title='Click here to sort by - Repossision No  '    onclick=sort_data('A.REPOSSESSION_NO') >Repossision No</td>");
			out.println("<td  width='11%' style= cursor:hand; title='Click here to sort by - Finance No  '    onclick=sort_data('B.FINANCE_NO') >Finance No</td>");
			//out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Seizer Name  '    onclick=sort_data('SEIZER_NAME') >Seizer Name/Officer Name</td>");
			out.println("<td  width='20%' style= cursor:hand; title='Click here to sort by - Client Name  '    onclick=sort_data('CLIENT_NAME') >Client Name</td>");
			out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Yard Name  '    onclick=sort_data('YARD_NAME') >Yard Name</td>");
			//out.println("<td  width='13%' align=right style= cursor:hand; title='Click here to sort by - Invoice Amount  '    onclick=sort_data('INVOICE_AMOUNT') >Invoice Amount</td>");
			out.println("<td  width='30%' style= cursor:hand; title='Click here to sort by - Yard Name  '    onclick=sort_data('YARD_NAME') >Comments</td>");
			out.println("<td width='5%' style='{text-align:center;}' >Approve</td>"); 
			//out.println("<td width='3%' >&nbsp</td>"); 
     
			int j=0;
			while(rs.next()){
			if(j>0 && j%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			//out.println("<td width='11%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u><input class='txt_input' type='hidden' name=TXT_INVENTORY_NO_"+j+" value=\""+rs.getString(1)+"\"></td>"); 
			//out.println("<td width='11%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(2)+"</u><input class='txt_input' type='hidden' name=TXT_REPOSSESSION_NO_"+j+"  value=\""+rs.getString(2)+"\">"); 
			out.println("<td  style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs.getString(3)+"')\" ><u>"+rs.getString(3)+"</u><input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+"  value=\""+rs.getString(3)+"\">"); 
			//out.println("<td  >"+rs.getString(5)+"<input class='txt_input' type='hidden' name=TXT_SEIZER_CODE_"+j+" value=\""+rs.getString(5)+"\">"); 
			out.println("<td  onclick=\"show_client('"+rs.getString(6)+"')\" style='cursor:hand'><u>"+rs.getString(7)+"</u><input class='txt_input' type='hidden' name=TXT_CLIENT_CODE_"+j+"  value=\""+rs.getString(7)+"\">"); 
			out.println("<td  >"+rs.getString(9)+"<input class='txt_input' type='hidden' name=TXT_YARD_CODE_"+j+" value=\""+rs.getString(9)+"\">"); 
			//out.println("<td width='13%' style='{text-align:right;}'>"+rs.getString(10)+"<input class='txt_input' type='hidden' name=TXT_INVOICE_AMOUNT_"+j+"  value=\""+rs.getString(10)+"\"></td>"); 
			
			//out.println("<td width='3%' ><input class='but_input' style=\"width:30px;\" type='button' name=BUT_TXT_INVENTORY_NO_"+j+" value=\"View\" onClick=\"view('"+rs.getString(1)+"')\" ></td>"); 
			//out.println("<td width='5%' ><input class='but_input' style=\"width:80px;\" type='button' name=BUT_VEH_INVENT_"+j+" value=\"Inventory\" onClick=\"load_inventory('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(5)+"','"+rs.getDouble(11)+"')\" ></td>"); //Added By Sandun on 02-07-2009
			
			//out.println("<td width=\"15%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\""+m_val_dd+"\" onblur=\"check_date("+j+")\">");
			//out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\""+m_val_mm+"\" onblur=\"check_date("+j+")\">");
			//out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\""+m_val_yy+"\" onblur=\"check_date("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('2',"+j+")>   Calendar</a> ");	
			//out.println("</td> ");
			out.println("<td  align=left><input class=\"txt_input\" type=\"text\" name=TXT_USER_COMMENTS_"+j+" maxlength=\"250\" style=\"width:250px;\" >");
			out.println("<td  style='{text-align:center;}'><input type='checkbox' name=chk_app_"+j+" value=\"N\" unchecked onClick=\"change("+j+")\"></td>"); 
			out.println("</tr>"); 
			j=j+1;
			}
			out.println("<input type=hidden name=hid_count_inv value="+j+">");
			out.println("<tr class=tr_input>");
      out.println("<td align=right colspan=13><input type=button name=end_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.top_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(\"End\");'></td>");

			out.println("</table>"); 

  		out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  

			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
	}

	}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}