 
//Created by Chandana on 14-03-2007 
//Preliminary Information Letter

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_Preliminary_Information_Letter extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10;
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
			String m_print="";
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("letter")){
					   
						  String m_app_no=req.getParameter("app_no");	
							m_print=req.getParameter("print");
							String m_client_type="";
							String m_client_num ="";
							
				rs4 = stmt.executeQuery("SELECT  A.CLIENT_CODE,B.CLIENT_CATEGORY "+
                                "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
                                "WHERE APPLICATION_NO='"+m_app_no+"' AND "+
                                "A.CLIENT_CODE=B.CLIENT_CODE ");
				boolean more4=rs4.next();	
					if(more4){
					  m_client_num =rs4.getString(1);	
					  m_client_type=rs4.getString(2);					
					}		
				
		//out.println(m_client_num);
				
		if(m_client_type.equals("INDIVIDUAL")||m_client_type.equals("SOLEPROPRI")||m_client_type.equals("PARTNERS")){  //Modified by Chandana on 20/09/2007                    
    
    out.println("<html>");
		out.println("<head>");
		out.println("<title>Asset Financing System</title>    ");
		out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
		out.println("</head>");
		out.println("<Script>");
								
		out.println("function load_roll_value(m_val){"); 
		out.println("help_box.innerHTML=\" Credit - CRIB Letter - \"+m_val;"); 
		out.println("}"); 
		out.println("");
		
		out.println("function load_roll_out_value(){");
		out.println("help_box.innerHTML=\" Credit - CRIB Letter - \"+document.Form1.hid_status.value;"); 
		out.println("}"); 
 	
		out.println("function save_window(){	"); 
		out.println("document.Form1.hid_client_no.value='"+m_client_num+"' ");
		out.println("document.Form1.hid_app_no.value='"+m_app_no+"' ");
		out.println("before_submit();"); 
		out.println("}"); 
		out.println("");
		
		out.println("function before_submit(){"); 
		out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
		//out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Crib_Req_Details';");
		out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Crib_Req_Details?status=individual';");
		out.println("		document.Form1.submit();	"); 
		out.println("} "); 
		out.println("} "); 
		
		
		out.println("function clear_window(){	"); 
		out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
		out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_Preliminary_Information_Letter?chksql=letter&app_no="+m_app_no+"';"); 
		out.println("		}"); 
		out.println("}");
			
	
	out.println("function change_reg_value(){	"); 
	//out.println("alert(document.Form1.CHK_IRREGULAR_VALUE.checked);");
	//elements[m_chk_required].checked
//	out.println("m_chk_required=\"CHK_REGULAR_VALUE\";");	
	out.println("if(document.Form1.CHK_REGULAR_VALUE.checked==true){");
	out.println("document.Form1.CHK_REGULAR_VALUE.value='on'");  
  
	out.println("document.Form1.CHK_IRREGULAR_VALUE.value=\"off\"");
  out.println("document.Form1.CHK_IRREGULAR_VALUE.checked=false");
	out.println("}");
	out.println("}");	


  out.println("function change_ireg_value(){	"); 
  out.println("if(document.Form1.CHK_IRREGULAR_VALUE.checked==true){");
	out.println("document.Form1.CHK_IRREGULAR_VALUE.value='on'");
	
	out.println("document.Form1.CHK_REGULAR_VALUE.value=\"off\"");
	out.println("document.Form1.CHK_REGULAR_VALUE.checked=false");
	out.println("}");	
	out.println("}");
	
	// Added by Thamali Jayatunga on 2009.10.12
	out.println("function check_number(obj,size){");
	out.println("if(obj.value!='')"); 
	out.println("if(isnumberok(obj,size)){"); 
	out.println("format_number(obj,size)"); 
	out.println("}"); 
	out.println("else{");
	out.println("alert('please enter a number');"); 
	out.println("obj.value='';"); 
	out.println("obj.focus();"); 
	out.println("}"); 
	out.println("}"); 			
		
				
			
    out.println("</Script>");
		
		
			rs8 = stmt.executeQuery("SELECT	TO_CHAR(LAST_DAY(ACTIVATED_DATE), 'fmddth'),TO_CHAR(LAST_DAY(ACTIVATED_DATE), 'Month') "+
		                       "	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
														" WHERE APPLICATION_NO='"+m_app_no+"' ");
														
			boolean more8=rs8.next();	
			String m_month_end="";
			String m_month_name="";
			if(more8){
			m_month_end=rs8.getString(1);
			m_month_name=rs8.getString(2);
			}			
				
				
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
			
				
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - CRIB Letter</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Letter\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<br>");
				
				
				
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
      out.println("</table>");
			
			 out.println("<table align='center' width='100%' class='table'>"); 
			 out.println("<tr>");  
			 out.println("<td width=\"100%\"></td>");
		   out.println("</tr>"); 
			
			   out.println("</table>");
			
			
			out.println("<br><br>");
			out.println("<TABLE border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>"); 
			out.println("<td width='*%'class='rep-body' font size=2><B>FROM: CRIB/P</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<TABLE border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=5><B>CREDIT INFORMATION BUREAU OF SRI LANKA</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=2><B>PRELIMINARY INFORMATION ON ADVANCES - PERSONAL BORROWER/S</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=2>REGULAR and IRREGULAR - RS 500,000 AND ABOVE[Please see overleaf]</td></tr>");
	  	out.println("</font></TABLE>");
			
			out.println("<br>");
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='39%'class='rep-body' font size=2>LENDING INSTITUTION&nbsp:<input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_COMP_NAME' value='Orient Financial Services Corporation Limited'></td>");
			out.println("<td width='30%'class='rep-body' font size=2>BRANCH&nbsp:<input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_BRANCH_NAME' value='Head Office'></td>");
			out.println("<td width='30%'class='rep-body' font size=2>MONTH ENDING&nbsp:  ");
			
				out.println("<input class='txt_input' type='text' name='TXT_MONTH_ENDING' maxlength='50' size='50' value=\""+m_month_end+" "+m_month_name+"\"></td>"); 
			
			
			out.println("<td width='*%'class='rep-body' font size=2></td>");
	  	out.println("</tr></font></TABLE>");	
			out.println("<br>");	
			out.println("<font size=2><p style='text-align:justify'>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>A</B></td>");
			out.println("<td width='45%'class='rep-body' font size=2><B>TYPE OF ADVANCE(PLEASE TICK)</B></td>");
			out.println("<td width='50%'class='rep-body' font size=2>");
			  out.println("<table border='0' width='100%' class='table'><tr><td width='5%'></td>");
				 out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr style='text-align:center'><td width='50%'>REGULAR</td>");
			    out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_REGULAR_VALUE onclick=\"change_reg_value()\"></td></TR></TABLE></td>");
				 out.println("<td width='5%'></td>");
				 out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr tr style='text-align:center'><td width='50%'>IRREGULAR</td>");
					out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_IRREGULAR_VALUE onclick=\"change_ireg_value()\"></td></TR></TABLE></td></tr></table>");
			
	  	out.println("</td></tr></font></TABLE>");	
			out.println("<br>");
			out.println("<font size=2><p style='text-align:justify'>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>B</B></td>");
			out.println("<td width='75%'class='rep-body' font size=2><B>INFORMATION ON BORROWERS/JOINT BORROWERS</B></td>");
			out.println("<td width='*%'class='rep-body' font size=2></td>");
			out.println("</tr></font></TABLE>");	
			out.println("<br>");
			
			String m_client_code="";
			String m_co_app_code="";
			
			String m_co_nic    ="";
			String m_co_stname ="";
			String m_co_surnam ="";
			String m_co_disgn  ="";
			String m_co_add1   ="";
			String m_co_add2   ="";
			String m_co_city   ="";
			String m_co_busno  ="";
			String m_co_busname ="";
			String m_co_busadd1 ="";
			String m_co_busadd2 ="";
			
			
			String m_cl_nic    ="";
			String m_cl_stname ="";
			String m_cl_surnam ="";
			String m_cl_disgn  ="";
			String m_cl_add1   ="";
			String m_cl_add2   ="";
			String m_cl_city   ="";
			String m_cl_busno  ="";
			String m_cl_busname ="";
			String m_cl_busadd1 ="";
			String m_cl_busadd2 ="";
			
			
			rs = stmt.executeQuery("SELECT  A.CLIENT_CODE,A.CO_APPLICANT "+
			                       " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
														 " WHERE APPLICATION_NO='"+m_app_no+"' ");
															
			boolean more = rs.next();
		
		if (more) {
		   m_client_code=rs.getString(1);
		   m_co_app_code=rs.getString(2);
		}										
			
		
		
		
		rs1 = stmt.executeQuery("SELECT NVL(NIC_NO,'-'), "+
		                        " NVL(OTHER_NAME,'-'), "+   //FIRST_NAME
														" NVL(SURNAME,'-'), "+
														" NVL(DESIGNATION,'-'), "+
														" NVL(ADDRESS1,'-'), "+
														" NVL(ADDRESS2,'-'), "+
														" NVL(INITCAP(CITY_CODE),'.'), "+
														" NVL(BUSINESS_CERTIFICATE_NO,'-'), "+
														" NVL(BA_NATURE_OF_BUSINESS,'-'), "+ 
														" NVL(REGISTERED_ADDRESS1,'-'), "+
														" NVL(REGISTERED_ADDRESS2,'-') "+
														" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														" WHERE CLIENT_CODE='"+m_client_code+"' ");
                          

        boolean more1 = rs1.next();
				
	if(more1){
	     m_cl_nic    =rs1.getString(1);
	     m_cl_stname =rs1.getString(2);
			 m_cl_surnam =rs1.getString(3);
			 m_cl_disgn  =rs1.getString(4);
			 m_cl_add1   =rs1.getString(5);
			 m_cl_add2   =rs1.getString(6);
			 m_cl_city   =rs1.getString(7);
			 m_cl_busno  =rs1.getString(8);
			 m_cl_busname =rs1.getString(9);
			 m_cl_busadd1 =rs1.getString(10);
			 m_cl_busadd2 =rs1.getString(11);
		}			
 
		
		
		rs2 = stmt.executeQuery("SELECT NVL(NIC_NO,'-'), "+
		                        " NVL(OTHER_NAME,'-'), "+ //FIRST_NAME
														" NVL(SURNAME,'-'), "+
														" NVL(DESIGNATION,'-'), "+
														" NVL(ADDRESS1,'-'), "+
														" NVL(ADDRESS2,'-'), "+
														" NVL(INITCAP(CITY_CODE),'.'), "+
														" NVL(BUSINESS_CERTIFICATE_NO,'-'), "+
														" NVL(BA_NATURE_OF_BUSINESS,'-'), "+ 
														" NVL(REGISTERED_ADDRESS1,'-'), "+
														" NVL(REGISTERED_ADDRESS2,'-') "+
														" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														" WHERE CLIENT_CODE='"+m_co_app_code+"' ");
                          

        boolean more2 = rs2.next();
				
	if(more2){
	     m_co_nic    =rs2.getString(1);
	     m_co_stname =rs2.getString(2);
			 m_co_surnam =rs2.getString(3);
			 m_co_disgn  =rs2.getString(4);
			 m_co_add1   =rs2.getString(5);
			 m_co_add2   =rs2.getString(6);
			 m_co_city   =rs2.getString(7);
			 m_co_busno  =rs2.getString(8);
			 m_co_busname =rs2.getString(9);
			 m_co_busadd1 =rs2.getString(10);
			 m_co_busadd2 =rs2.getString(11);
		}			
	
			
		//out.println(m_client_code+"dddddddddd"+m_co_app_code);	
			
				
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2>1</td>");
			out.println("<td width='35%'class='rep-body' font size=2>NAME/S IN FULL(MR/MRS/MISS) [UNDERLINE SURNAME]</td>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1) First Name<input class='txt_input' type='text'  style=\"width:180px;\" maxlength=\"200\" size=\"100\"    name='TXT_CLNT_NAME' value=\""+m_cl_stname+"\"> Surname <input class='txt_input' type='text'  style=\"width:180px;\" maxlength=\"200\" size=\"100\"    name='TXT_CLNT_SURE_NAME' value=\""+m_cl_surnam+"\"></td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2> &nbsp&nbsp&nbsp&nbsp&nbsp 2) First Name<input class='txt_input' type='text'  style=\"width:180px;\" maxlength=\"200\" size=\"100\"    name='TXT_COAPP_NAME' value=\""+m_co_stname+"\"> Surname <input class='txt_input' type='text'  style=\"width:180px;\" maxlength=\"200\" size=\"100\"    name='TXT_COAPP_SURE_NAME' value=\""+m_co_surnam+"\"> </td>");
			//out.println("<td width='60%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 2) "+m_co_stname+"&nbsp"+m_co_surnam+"</td>");
			out.println("</tr></font></TABLE>");	
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2>2</td>");
			out.println("<td width='35%'class='rep-body' font size=2>NATIONAL IDENTITY CARD NUMBER/S</td>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1) <input class='txt_input' type='text' name='TXT_CLNT_NIC' maxlength='10' size='10' value=\""+m_cl_nic+"\">  2) <input class='txt_input' type='text' name='TXT_COAP_NIC' maxlength='10' size='10' value=\""+m_co_nic+"\"></td>");
			out.println("</tr></font></TABLE>");	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2>3</td>");
			out.println("<td width='35%'class='rep-body' font size=2>OCCUPATION/DESIGNATION</td>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1) <input class='txt_input' type='text' name='TXT_CLNT_DISG' maxlength='10' size='10' value=\""+m_cl_disgn+"\"> 2) <input class='txt_input' type='text' name='TXT_COAP_DISG' maxlength='10' size='10' value=\""+m_co_disgn+"\"></td>");
			out.println("</tr></font></TABLE>");	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2>4</td>");
			out.println("<td width='35%'class='rep-body' font size=2>ADDRESS - RESIDENTIAL</td>");
			//out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1) "+m_cl_add1+"&nbsp"+m_cl_add2+"&nbsp"+m_cl_city+" </td></tr>"); 
			String m_cl_add=m_cl_add1+","+m_cl_add2+","+m_cl_city;
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1) <input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_CLNT_ADD' value=\""+m_cl_add+"\"></td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			String m_co_add=m_co_add1+","+m_co_add2+","+m_co_city;
			//out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 2) "+m_co_add1+"&nbsp"+m_co_add2+"&nbsp"+m_co_city+"</td></tr>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 2) <input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_COAP_ADD' value=\""+m_co_add+"\"></td></tr>");
			out.println("</font></TABLE>");	
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2 valign='top'>5</td>");
			out.println("<td width='35%'class='rep-body' font size=2>NAME OF BUSINESS (Where the above borrower/s is/are the Sole proprietor or a partner)</td>");
			
			if(m_client_type.equals("INDIVIDUAL")){
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>:&nbsp&nbsp&nbsp&nbsp 1) <input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_CLNT_BUS_NAME' value=''></td></tr>");
			}else{
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>:&nbsp&nbsp&nbsp&nbsp 1) <input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_CLNT_BUS_NAME' value=\""+m_cl_busname+"\"></td></tr>");
			}			
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			if(m_client_type.equals("INDIVIDUAL")){
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>:&nbsp&nbsp&nbsp&nbsp 2) <input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_COAP_BUS_NAME' value=''> </td></tr>");
			}else{
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>:&nbsp&nbsp&nbsp&nbsp 2) <input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_COAP_BUS_NAME' value=\""+m_co_busname+"\"> </td></tr>");
			}
			out.println("</font></TABLE>");	
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2>BUSINESS REGISTRATION NUMBER</td>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1) <input class='txt_input' type='text' name='TXT_CLNT_BUS_NO' maxlength='10' size='10' value=\""+m_cl_busno+"\"> 2) <input class='txt_input' type='text' name='TXT_COAP_BUS_NO' maxlength='10' size='10' value=\""+m_co_busno+"\"></td>");
			out.println("</tr></font></TABLE>");
			
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2 style='valign:up'></td>");
			out.println("<td width='35%'class='rep-body' font size=2>BUSINESS ADDRESS</td>");
			String m_cl_busadd=m_cl_busadd1+","+m_cl_busadd2;
			out.println("<td width='55%'class='rep-body' font size=2>&nbsp&nbsp&nbsp&nbsp 1) <input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_CLNT_BUS_ADD' value=\""+m_cl_busadd+"\"></td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			String m_co_busadd=m_co_busadd1+", "+m_co_busadd2;
			out.println("<td width='55%'class='rep-body' font size=2>&nbsp&nbsp&nbsp&nbsp 2) <input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_COAP_BUS_ADD' value=\""+m_co_busadd+"\"></td></tr>");
			out.println("</font></TABLE>");	
			out.println("</br>");
				
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>C</B></td>");
			out.println("<td width='95%'class='rep-body' font size=2><B>DETAILS OF ADVANCES</B></td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='95%'class='rep-body' font size=2><table border='1' cellspacing='0' border color='black' width='100%' class='table'> ");
			out.println("<tr style='text-align:center'>");
			out.println("<td width='60%'class='rep-body' font size=2></td>");
			out.println("<td width='20%'class='rep-body' font size=2>ADVANCE1</td>");
			out.println("<td width='20%'class='rep-body' font size=2>ADVANCE2</td>");
			out.println("</tr>");
			
			
			rs7 = stmt.executeQuery("SELECT A.FINANCE_NO,TO_CHAR(A.ACTIVATED_DATE,'DD-MON-YYYY'), "+
		    " D.DESCRIPTION,B.ITEM_SUB_CAT_CODE,C.NET_AMOUNT+C.NET_AMOUNT*((C.VAT_PERCENTAGE - C.VAT_APP)/100) AMOUNT,NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(E.SECTOR_CODE),'-') "+
			  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+m_schema_name+".AF_MK_PRO_PRICING C, "+
				" "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE D, "+
				" "+m_schema_name+".AF_CO_MAS_CLIENT E "+
			  " WHERE /*B.PRICING_STATUS='Y' AND */ "+ //Comment by Chandana on 22/10/2007
			  " A.APPLICATION_NO=B.APPLICATION_NO AND "+
        " B.PRICING_NO=C.PRICING_NO AND "+
				" B.TRANSACION_TYPE = D.TRAN_CODE AND "+
				" A.CLIENT_CODE = E.CLIENT_CODE AND "+
			  " A.FINANCE_NO IS NOT NULL AND "+ 
				" A.APPLICATION_NO='"+m_app_no+"' ");
				//" A.APPLICATION_NO='AP20061003-0029' ");
			
		
		boolean more7=rs7.next();
		
		String m_fin_num="";
		String m_gr_date="";
		String m_tr_type="";
		String m_security="";
		double m_amount =0.00;
		String m_secter="";
		
		
		if(more7){
		 m_fin_num=rs7.getString(1);
		 m_gr_date=rs7.getString(2);
		 m_tr_type=rs7.getString(3);
		 m_security=rs7.getString(4);	
		 m_amount =rs7.getDouble(5);
		 m_secter =rs7.getString(6); 	
		}
			
			
			
					
		rs10 = stmt.executeQuery(	" SELECT MODEL_DESC,PRICING_NO,ASSET_ID,ITEM_CAT "+
	     " FROM "+
				" (SELECT "+
				" DISTINCT nvl(B.CHASSIS_NO,'-'), "+
				" I.PRICING_NO, "+
				" I.GROSS_AMOUNT, "+
				//" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||DECODE(H.DESCRIPTION,'Not Applicable',' ')) MODEL_DESC, "+ //comment by nuwan de silva on 12-12-2007 at ofscl
				" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION ||' '||DECODE(H.DESCRIPTION,'Not Applicable',' ')) MODEL_DESC, "+ //added by nuwan de silva on 12-12-2007 at ofscl
				" A.ASSET_ID ,"+
				" LOWER(J.DESCRIPTION) ITEM_CAT "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
				" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
				" "+m_schema_name+".AF_CO_MAS_MAKE C, "+
				" "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
				" "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
				" "+m_schema_name+".AF_CO_MAS_MODEL F, "+
				" "+m_schema_name+".AF_CO_MAS_VENDORS G, "+
				" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H, "+
				" "+m_schema_name+".AF_CO_PRO_APP_PRICING I ,"+
				" "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY J "+
				" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
				" A.ACTIVE_STATUS='Y' AND "+
				" B.ACTIVE_STATUS='Y' AND "+
				" B.PRICING_NO =I.PRICING_NO AND "+
				" A.APPLICATION_NO=UPPER('"+m_app_no+"') AND "+
				" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(B.MODEL_CODE)=J.ITEM_CAT_CODE AND "+ 
				" A.ASSET_ID=B.ASSET_ID AND "+
				" C.MAKE_CODE=(SELECT "+
				" MAKE_CODE "+
				" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
				" WHERE MODEL_CODE IN ( SELECT "+
				" MODEL_CODE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
				" WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
				" )) AND "+
				" D.SUB_CODE=B.SUB_MODEL_CODE AND "+
				" UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
				" UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
				" UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
				" UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
				" B.MODEL_CODE=F.MODEL_CODE) "+
				" GROUP BY  PRICING_NO,MODEL_DESC,ASSET_ID,GROSS_AMOUNT,ITEM_CAT ");
		
		boolean more10=rs10.next();
		String m_security_des = "";
		
		
		if(more10){		
		m_security_des = rs10.getString(1);		
		}	
			
			
			
			
			
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 1. Loan/Facility Account Number</td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_FIN_NO' value=\""+m_fin_num+"\"></td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_ADV2_FIN_NO' value=''></td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 2. Date of granting the Advance</td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_GR_DATE' value=\""+m_gr_date+"\"></td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_ADV2_GR_DATE' value=''></td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 3. Nature of the Advance : Eg. Overdraft, Letter of Credit, Finance Lease etc</td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_TYPE' value=\""+m_tr_type+"\"></td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_ADV2_TYPE' value=''></td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 4. Sector - Manufacturing, Construction, Agriculture, Transport etc</td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_SECTOR' value=\""+m_secter+"\"></td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_ADV2_SECTOR' value=''></td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 5. Whether the advance is Direct or Indirect</td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_ADVANCE' value='Direct'></td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_ADV2_ADVANCE' value=''></td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			// Modified Thamali Jayatunga on 2009.10.12, Replace maxlength to 15 and added onBlur event in TXT_AMT_LIMT and TXT_ADV2_AMT_LIMT 
			out.println("<td width='60%'class='rep-body' font size=2> 6. Amount granted/limit (Rs'000)</td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"15\" size=\"100\"    onBlur='check_number(this,13)' name='TXT_AMT_LIMT' value="+nf.format(m_amount)+"></td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"15\" size=\"100\"    onBlur='check_number(this,13)' name='TXT_ADV2_AMT_LIMT' value='' ></td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 7. Security offered</td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_SECURITY' value=\""+m_security_des+"\"></td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_ADV2_SECURITY' value=''></td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			// Modified Thamali Jayatunga on 2009.10.12, Replace maxlength to 15 and added onBlur event in TXT_BALANCET and TXT_ADV2_BALANCE 
			out.println("<td width='60%'class='rep-body' font size=2> 8. Balance outstanding (Rs'000)</td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"15\" size=\"100\"    onBlur='check_number(this,13)' name='TXT_BALANCE' value="+nf.format(m_amount)+" ></td>");
			out.println("<td width='20%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"15\" size=\"100\"    onBlur='check_number(this,13)' name='TXT_ADV2_BALANCE' value='' ></td>");
			out.println("</tr>");
			out.println("</TABLE></td></tr>");
			
			
			
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2></td></tr>");
			out.println("</font></TABLE>");	
			out.println("</br>");
			
			
			//out.println(m_app_no);
			
			String m_guarantor1=" ";
			String m_guarantor2=" ";			
			
	
	
			rs9 = stmt.executeQuery("SELECT APPLICATION_NO, "+
			                        " GUARANTOR_CODE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
															" where APPLICATION_NO='"+m_app_no+"' ");
								
			boolean more9=rs9.next();				
			int i=1;
					
			while(more9){
				if(i==1){
			  m_guarantor1=rs9.getString(2);
				//out.println("m_guarantor1"+rs9.getString(2));
				}
				else if(i==2){
				m_guarantor2=rs9.getString(2);
				//out.println("m_guarantor2"+rs9.getString(2));
				}
			i=i+1;	
			more9=rs9.next();
			} 
			
			
			String m_guar1_name="";
			String m_guar1_nic="";
			String m_guar1_add1="";
			String m_guar1_add2="";
			String m_guar1_city="";
			String m_guar1_dsg="";
			String m_guar2_name="";
			String m_guar2_nic="";
			String m_guar2_add1="";
			String m_guar2_add2="";
			String m_guar2_city="";
			String m_guar2_dsg="";
			
			
			
	//	out.println("m_guarantor1111"+m_guarantor1);	
	//	out.println("m_guarantor2222"+m_guarantor2);	
			
			if(!m_guarantor1.equals("")){
			rs5 = stmt.executeQuery("SELECT  NVL(FULL_NAME,'-'),NVL(NIC_NO,'-'),NVL(DESIGNATION,'-'),NVL(ADDRESS1,'-'),NVL(ADDRESS2,'-'),NVL(INITCAP(CITY_CODE),'.') "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" where CLIENT_CODE='"+m_guarantor1+"' ");
			
			boolean more5=rs5.next();
			
			if(more5){
			   m_guar1_name=rs5.getString(1);
			   m_guar1_nic =rs5.getString(2);
					m_guar1_dsg=rs5.getString(3);
				 m_guar1_add1=rs5.getString(4);
				 m_guar1_add2=rs5.getString(5);
				 m_guar1_city=rs5.getString(6);
			}
			}
			
			if(!m_guarantor2.equals("")){
			rs6 =  stmt.executeQuery("SELECT  NVL(FULL_NAME,'-'),NVL(NIC_NO,'-'),NVL(DESIGNATION,'-'),NVL(ADDRESS1,'-'),NVL(ADDRESS2,'-'),NVL(INITCAP(CITY_CODE),'.') "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" where CLIENT_CODE='"+m_guarantor2+"' ");
			
			boolean more6=rs6.next();
			
			if(more6){
			   m_guar2_name=rs6.getString(1);
			   m_guar2_nic =rs6.getString(2);
				 m_guar2_dsg =rs6.getString(3);
				 m_guar2_add1=rs6.getString(4);
				 m_guar2_add2=rs6.getString(5);
				 m_guar2_city=rs6.getString(6);
			}
			 
			}
			
			
		  //out.println("<tr style='text-align:left'>");	
		  out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>D</B></td>");
			out.println("<td width='95%'class='rep-body' font size=2><B>DETAILS OF GUARANTORS</B></td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='95%'class='rep-body' font size=2>");
		
		out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' border color='black' class='table' >");
    out.println("<tr style='text-align:center'>");
    out.println("<td width='30%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("<td width='35%' class='rep-body' font size=2><B>ADVANCE 1 </B></td>");
    out.println("<td width='35%' class='rep-body' font size=2><B>ADVANCE II </B></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0' class='table'>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2><B>&nbsp;&nbsp;1 GUARANTOR - I</B></td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; a) Name in full </td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; b) NIC Number </td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; c) Occupation/Designation</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; d) Address - Residential</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_GR1_NAME' value=\""+m_guar1_name+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_GR1_NIC' value=\""+m_guar1_nic+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_GR1_DSG' value=\""+m_guar1_dsg+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
		String m_guar1_add=m_guar1_add1+","+m_guar1_add2+","+m_guar1_city;
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_GR1_ADD' value=\""+m_guar1_add+"\"></td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_ADD2_GR1_NAME' value=''></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_ADD2_GR1_NIC' value=''></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_ADD2_GR1_DSG' value=''></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_ADD2_GR1_ADD' value=''></td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><B>&nbsp;&nbsp;2 GUARANTOR - II</B></td>"); 
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; a) Name in full </td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; b) NIC Number </td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; c) Occupation/Designation </td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; d) Address - Residential </td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_GR2_NAME' value=\""+m_guar2_name+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_GR2_NIC' value=\""+m_guar2_nic+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_GR2_DSG' value=\""+m_guar2_dsg+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
		String m_guar2_add=m_guar2_add1+","+m_guar2_add2+","+m_guar2_city;
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_GR1_ADD' value=\""+m_guar2_add+"\"></td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_ADD2_GR2_NAME' value=''></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_ADD2_GR2_NIC' value=''></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_ADD2_GR2_DSG' value=''></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:325px;\" maxlength=\"200\" size=\"150\"    name='TXT_ADD2_GR1_ADD' value=''></td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("</tr>");
    out.println("</table>");

			
	 out.println("</td>");
	 out.println("</tr>");
   out.println("</table>");		
			
		out.println("<br>");	
			
		
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'><B>.................................................</B></td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'><B>..................................</B></td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'><B>............................................</B></td>");
		out.println("</tr>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'>NAME OF AUTHORIZED OFFICER</td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'>SIGNATURE</td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'>TELEPHONE NUMBER&nbsp&nbsp&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("<hr color='black'>");
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'>For completion by the Bureau only</td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'></td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'></td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp; 1) Initial Code No: </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp;&nbsp;&nbsp;2) Borrower ID No:</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp; 3) Entered by </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp;&nbsp;&nbsp;4) Validated by </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("<br><br>");
		
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("</tr>");
		out.println("</font></TABLE>");			
				
				
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</html>");
				
				
 //=============================================================================================================================     
			}else{
			
			
			//out.println("m_client_num"+m_client_num+"m_app_no"+m_app_no);
			
			
			String m_borro_name="";
			String m_borro_add1="";
			String m_borro_add2="";
			String m_borro_city="";
			String m_borro_busno="";
		
			
			
			
			rs = stmt.executeQuery("SELECT  NVL(FULL_NAME,'-'), "+
			                       " NVL(REGISTERED_ADDRESS1,'-'), "+
														 " NVL(REGISTERED_ADDRESS2,'-'), "+
														 " NVL(REGISTERED_CITY_CODE,'.'), "+
														 " NVL(BUSINESS_CERTIFICATE_NO,'-') "+
														 " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														 " where CLIENT_CODE='"+m_client_num+"' ");
			
			boolean more=rs.next();
			
			if(more){
			   m_borro_name=rs.getString(1);
			   m_borro_add1=rs.getString(2);
				 m_borro_add2=rs.getString(3);
				 m_borro_city=rs.getString(4);
				 m_borro_busno=rs.getString(5);
			
			}
			
			
			
			
		rs8 = stmt.executeQuery("SELECT	TO_CHAR(LAST_DAY(ACTIVATED_DATE), 'fmddth') || ' ' || TO_CHAR(LAST_DAY(ACTIVATED_DATE), 'Month') "+
		                       "	FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS "+
														" WHERE APPLICATION_NO='"+m_app_no+"' ");
														
			boolean more8=rs8.next();	
			String m_month_end="";
			String m_month_name="";
			if(more8){
			m_month_end=rs8.getString(1);
			}										
			
			
			
			
			
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Asset Financing System</title>    ");
		out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
		out.println("</head>");
		out.println("<Script>");
								
		out.println("function load_roll_value(m_val){"); 
		out.println("help_box.innerHTML=\" Credit - CRIB Letter - \"+m_val;"); 
		out.println("}"); 
		out.println("");
		
		out.println("function load_roll_out_value(){");
		out.println("help_box.innerHTML=\" Credit - CRIB Letter - \"+document.Form1.hid_status.value;"); 
		out.println("}"); 
 	
		out.println("function save_window(){	"); 
		
		//out.println("alert("+m_client_num+");");
		out.println("document.Form1.hid_client_no.value='"+m_client_num+"' ");
		out.println("document.Form1.hid_app_no.value='"+m_app_no+"' ");
		out.println("before_submit();"); 
		out.println("}"); 
		out.println("");
		
		out.println("function before_submit(){"); 
		out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
		out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Crib_Req_Details?status=corperate';");   
		out.println("		document.Form1.submit();	"); 
		out.println("} "); 
		out.println("} "); 
		
		
		out.println("function clear_window(){	"); 
		out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
		out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_Preliminary_Information_Letter?chksql=letter&app_no="+m_app_no+"';"); 
		out.println("		}"); 
		out.println("}");
			
	
	 out.println("function change_value(){	"); 
	 out.println("if(document.Form1.CHK_REGULAR_VALUE.checked==true){");
	 out.println("document.Form1.CHK_REGULAR_VALUE.value='on'");
	
	out.println("}else if(document.Form1.CHK_REGULAR_VALUE.checked==false){");
	out.println("document.Form1.CHK_REGULAR_VALUE.value='off'");
	out.println("}");	
	
	//out.println("alert(document.Form1.CHK_REGULAR_VALUE.value);");
	out.println("}");

			
	 out.println("function change_reg_value(){	"); 
	//out.println("alert(document.Form1.CHK_IRREGULAR_VALUE.checked);");
	//elements[m_chk_required].checked
//	out.println("m_chk_required=\"CHK_REGULAR_VALUE\";");	
	out.println("if(document.Form1.CHK_REGULAR_VALUE.checked==true){");
	out.println("document.Form1.CHK_REGULAR_VALUE.value='on'");  
  
	out.println("document.Form1.CHK_IRREGULAR_VALUE.value=\"off\"");
  out.println("document.Form1.CHK_IRREGULAR_VALUE.checked=false");
	out.println("}");
	out.println("}");	


  out.println("function change_ireg_value(){	"); 
  out.println("if(document.Form1.CHK_IRREGULAR_VALUE.checked==true){");
	out.println("document.Form1.CHK_IRREGULAR_VALUE.value='on'");
	
	out.println("document.Form1.CHK_REGULAR_VALUE.value=\"off\"");
	out.println("document.Form1.CHK_REGULAR_VALUE.checked=false");
	out.println("}");	
	out.println("}");
		
			
			
			
			
			
			
			
			
    out.println("</Script>");
				
				
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - CRIB Letter</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Letter\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<br>");
				
				
				
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
      out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"></td>");
		  out.println("</tr>"); 
			out.println("</table>");
		
				
				
				
				
				
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
      out.println("</table>");
			
			out.println("<br><br>");
			out.println("<TABLE border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>"); 
			out.println("<td width='*%'class='rep-body' font size=2><B>FROM: CRIB/C</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<TABLE border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=5><B>CREDIT INFORMATION BUREAU OF SRI LANKA</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=2><B>PRELIMINARY INFORMATION ON ADVANCES - CORPORATE BORROWERS</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=2>REGULAR and IRREGULAR - RS 500,000 AND ABOVE[Please see overleaf]</td></tr>");
	  	out.println("</font></TABLE>");
			
			out.println("<br>");
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='39%'class='rep-body' font size=2>LENDING INSTITUTION &nbsp: <input class='txt_input' type='text'  style=\"width:235px;\" maxlength=\"200\" size=\"100\"    name='TXT_COMP_NAME' value='Lakderana  Investments  Limited'></td>");
			out.println("<td width='30%'class='rep-body' font size=2>BRANCH &nbsp:<input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_BRANCH_NAME' value='Head Office'></td>");
			out.println("<td width='30%'class='rep-body' font size=2>MONTH ENDING &nbsp:");
			out.println("<input class='txt_input' type='text' name='TXT_MONTH_ENDING' maxlength='50' size='50' value=\""+m_month_end+"\"></td>");
			out.println("<td width='*%'class='rep-body' font size=2></td>");
	  	out.println("</tr></font></TABLE>");	
			out.println("<br>");	
			out.println("<font size=2><p style='text-align:justify'>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>A</B></td>");
			out.println("<td width='45%'class='rep-body' font size=2><B>TYPE OF ADVANCE(PLEASE TICK)</B></td>");
			out.println("<td width='50%'class='rep-body' font size=2>");
			out.println("<table border='0' width='100%' class='table'><tr><td width='5%'></td>");
			out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr style='text-align:center'><td width='50%'>REGULAR</td><td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_REGULAR_VALUE onclick=\"change_reg_value()\"></td></TR></TABLE></td>");
			out.println("<td width='5%'></td>");
			out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr tr style='text-align:center'><td width='50%'>IRREGULAR</td><td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_IRREGULAR_VALUE onclick=\"change_ireg_value()\"></td></TR></TABLE></td></tr></table>");
			out.println("</td></tr></font></TABLE>");	
		
			out.println("<font size=2><p style='text-align:justify'>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>B</B></td>");
			out.println("<td width='75%'class='rep-body' font size=2><B>INFORMATION ON CORPORATE BORROWER</B></td>");
			out.println("<td width='*%'class='rep-body' font size=2></td>");
			out.println("</tr></font></TABLE>");
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' valign='top' font size=2>1</td>");
			out.println("<td width='30%'class='rep-body' font size=2 valign='top'>NAME OF THE CORPORATE BORROWER</td>");
			out.println("<td width='60%'class='rep-body' font size=2>:&nbsp<input class='txt_input' type='text'  style=\"width:285px;\" maxlength=\"200\" size=\"100\"    name='TXT_BORR_NAME' value=\""+m_borro_name+"\"></td>");
			out.println("</tr>");
		  out.println("</font></TABLE>");	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' valign='top' font size=2>2</td>");
			out.println("<td width='30%'class='rep-body' font size=2 valign='top'>REGISTERED ADDRESS</td>");
			String m_brrow_add=m_borro_add1+","+m_borro_add2+","+m_borro_city;
			out.println("<td width='60%'class='rep-body' font size=2>:&nbsp<input class='txt_input' type='text'  style=\"width:285px;\" maxlength=\"200\" size=\"100\"    name='TXT_BORR_ADD' value=\""+m_brrow_add+"\"></td>");
			out.println("</tr>");
		  out.println("</font></TABLE>");	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' valign='top' font size=2>3</td>");
			out.println("<td width='30%'class='rep-body' font size=2 valign='top'>COMPANY/BUSINESS REGISTRATION NUMBER</td>");
			out.println("<td width='60%'class='rep-body' font size=2>:&nbsp<input class='txt_input' type='text'  style=\"width:200px;\" maxlength=\"200\" size=\"100\"    name='TXT_BORR_BUS_NO' value=\""+m_borro_busno+"\"></td>");
			out.println("</tr>");
		  out.println("</font></TABLE>");	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' valign='top' font size=2>4</td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>NAME & ADDRESS OF THE PROPRIETORS/PARTNERS</td>");
			out.println("<td width='35%'class='rep-body' font size=2>NIC NUMBERS</td>");
			out.println("</tr>");
			
			
			/* //Comment by Chandana on 29/10/2007
			rs1 = stmt.executeQuery("SELECT CLIENT_CODE,NAME,NIC_NO "+
			      " FROM LAKDL.AF_CO_MAS_COMPANY_DIRECTORS "+
						" WHERE CLIENT_CODE='"+m_client_num+"'"); */
						
						
			rs1 = stmt.executeQuery(" SELECT A.CLIENT_CODE,NAME,A.NIC_NO , B.ADDRESS1||' '||B.ADDRESS2||' '||INITCAP(CITY_CODE) "+
			      " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
						" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
						" A.CLIENT_CODE='"+m_client_num+"' ");
						
						
			boolean more1=rs1.next();	
			int i=1;
			

			while(more1){			
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>"+i+"&nbsp;NAME&nbsp;&nbsp;&nbsp;&nbsp;:<input class='txt_input' type='text'  style=\"width:280px;\" maxlength=\"200\" size=\"100\"    name='TXT_BORR_NAME"+i+"' value=\""+rs1.getString(2)+"\"></td>");
			out.println("<td width='35%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:150px;\" maxlength=\"10\" size=\"100\"    name='TXT_PART_NIC"+i+"' value=\""+rs1.getString(3)+"\"></td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>&nbsp;&nbsp;ADDRESS:<input class='txt_input' type='text'  style=\"width:280px;\" maxlength=\"200\" size=\"100\"    name='TXT_PART_ADD"+i+"' value=\""+rs1.getString(4)+"\"></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			out.println("</tr>");
			i=i+1;
			more1=rs1.next();
			}
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_max' VALUE="+i+">");
			
			
			out.println("</font></TABLE>");	
			
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2>5</td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>NAMES OF SUBSIDIARIES/ASSOCIATE COMPANIES</td>");
			out.println("<td width='35%'class='rep-body' font size=2>COMPANY REGISTRATION NUMBERS</td>");
			out.println("</tr>");
			
			
			rs2 = stmt.executeQuery("SELECT NAME "+
			      " FROM "+m_schema_name+".AF_CO_MAS_SUBSIDIARIES "+
						" WHERE CLIENT_CODE='"+m_client_num+"'");
						
		/*				
			rs2 = stmt.executeQuery("SELECT A.NAME, NVL(BUSINESS_CERTIFICATE_NO, NIC_NO) "+
			      " FROM "+m_schema_name+".AF_CO_MAS_SUBSIDIARIES A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
						" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
						" A.CLIENT_CODE='"+m_client_num+"' ");*/
						
						
			
			boolean more2=rs2.next();
			int j=1;
			while(more2){
			
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>"+j+"&nbsp;<input class='txt_input' type='text'  style=\"width:280px;\" maxlength=\"200\" size=\"100\"    name='TXT_SUBSIDERY_NAME"+j+"' value=\""+rs2.getString(1)+"\"></td>");
			out.println("<td width='35%'class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:150px;\" maxlength=\"100\" size=\"100\"    name='TXT_SUBSIDERY_REG_NO"+j+"' value=\"-\"></td>");
			out.println("</tr>");
			j=j+1;
			more2=rs2.next();
			}
					
			out.println("</font></TABLE>");	
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>C</B></td>");
			out.println("<td width='95%'class='rep-body' font size=2><B>DETAILS OF ADVANCES</B></td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='95%'class='rep-body' font size=2>");
		
		out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' border color='black' class='table' >");
    out.println("<tr style='text-align:center'>");
    out.println("<td width='30%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2><B>ADVANCE 1 </B></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2><B>ADVANCE 2 </B></td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><B>ADVANCE 3 </B></td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><B>ADVANCE 4 </B></td>");
    out.println("</tr>");
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		rs7 = stmt.executeQuery("SELECT A.FINANCE_NO,TO_CHAR(A.ACTIVATED_DATE,'DD-MON-YYYY'), "+
		    " D.DESCRIPTION,B.ITEM_SUB_CAT_CODE,C.NET_AMOUNT+C.NET_AMOUNT*((C.VAT_PERCENTAGE - C.VAT_APP)/100) AMOUNT, NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(E.SECTOR_CODE),'-') "+
			  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+m_schema_name+".AF_MK_PRO_PRICING C, "+
				" "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE D, "+ 
				" "+m_schema_name+".AF_CO_MAS_CLIENT E "+
			  " WHERE /*B.PRICING_STATUS='Y' AND*/ "+
			  " A.APPLICATION_NO=B.APPLICATION_NO AND "+
        " B.PRICING_NO=C.PRICING_NO AND "+
				" B.TRANSACION_TYPE = D.TRAN_CODE AND "+
				" A.CLIENT_CODE = E.CLIENT_CODE AND "+
			  " A.FINANCE_NO IS NOT NULL AND "+ 
				" A.APPLICATION_NO='"+m_app_no+"' ");
				//" A.APPLICATION_NO='AP20061003-0029' ");
			
		
		boolean more7=rs7.next();
		
		String m_fin_num="";
		String m_gr_date="";
		String m_tr_type="";
		String m_security="";
		double m_amount =0.00;
		String m_sector="";
		
		
		if(more7){
		 m_fin_num=rs7.getString(1);
		 m_gr_date=rs7.getString(2);
		 m_tr_type=rs7.getString(3);
		 m_security=rs7.getString(4);	
		 m_amount =rs7.getDouble(5);
		 m_sector =rs7.getString(6);	
		}
		
		
		
		
		
		rs10 = stmt.executeQuery(	" SELECT MODEL_DESC,PRICING_NO,ASSET_ID,ITEM_CAT "+
	     " FROM "+
				" (SELECT "+
				" DISTINCT nvl(B.CHASSIS_NO,'-'), "+
				" I.PRICING_NO, "+
				" I.GROSS_AMOUNT, "+
				" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||DECODE(H.DESCRIPTION,'Not Applicable',' ')) MODEL_DESC, "+
				" A.ASSET_ID ,"+
				" LOWER(J.DESCRIPTION) ITEM_CAT "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
				" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
				" "+m_schema_name+".AF_CO_MAS_MAKE C, "+
				" "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
				" "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
				" "+m_schema_name+".AF_CO_MAS_MODEL F, "+
				" "+m_schema_name+".AF_CO_MAS_VENDORS G, "+
				" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H, "+
				" "+m_schema_name+".AF_CO_PRO_APP_PRICING I ,"+
				" "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY J "+
				" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
				" A.ACTIVE_STATUS='Y' AND "+
				" B.ACTIVE_STATUS='Y' AND "+
				" B.PRICING_NO =I.PRICING_NO AND "+
				" A.APPLICATION_NO=UPPER('"+m_app_no+"') AND "+
				" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(B.MODEL_CODE)=J.ITEM_CAT_CODE AND "+ 
				" A.ASSET_ID=B.ASSET_ID AND "+
				" C.MAKE_CODE=(SELECT "+
				" MAKE_CODE "+
				" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
				" WHERE MODEL_CODE IN ( SELECT "+
				" MODEL_CODE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
				" WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
				" )) AND "+
				" D.SUB_CODE=B.SUB_MODEL_CODE AND "+
				" UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
				" UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
				" UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
				" UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
				" B.MODEL_CODE=F.MODEL_CODE) "+
				" GROUP BY  PRICING_NO,MODEL_DESC,ASSET_ID,GROSS_AMOUNT,ITEM_CAT ");
		
		boolean more10=rs10.next();
		String m_security_des = "";
		
		
		if(more10){		
		m_security_des = rs10.getString(1);		
		}	
		
		
		out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;1&nbsp; Loan/Facility Account Number</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;1</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Loan/Facility Account Number</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_FINANCE_NO' value="+m_fin_num+"></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;2&nbsp; Date of granting the Advance</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;2</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Date of granting the Advance</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_GRANT_DATE' value="+m_gr_date+"></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;3&nbsp; Nature of the Advance: Eg. Overdraft Letter of Credit, Finance Lease etc.</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;3</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Nature of the Advance: Eg. Overdraft Letter of Credit, Finance Lease etc.</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_TRN_TYPE' value="+m_tr_type+"></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;4&nbsp; Sector - Manufacturing, Construction, Agriculture, Transport etc.</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;4</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Sector - Manufacturing, Construction, Agriculture, Transport etc.</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_SECTOR' value=\""+m_sector+"\"></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
  //  out.println("<td width='30%' class='rep-body' font size=2>&nbsp;5&nbsp; Where the advance is Direct or Indirect</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;5</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Where the advance is Direct or Indirect</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_ADVANCE' value='Direct'></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
  //  out.println("<td width='30%' class='rep-body' font size=2>&nbsp;6&nbsp; Amount granted/limit(Rs'000).</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;6</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Amount granted/limit(Rs'000).</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_AMOUNT' value="+nf.format(m_amount)+"></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;7&nbsp; Security offered</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;7</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Security offered</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_SECURITY' value=\""+m_security_des+"\"></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
  //  out.println("<td width='30%' class='rep-body' font size=2>&nbsp;8&nbsp; Balance outstanding(Rs'000)</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;8</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Balance outstanding(Rs'000)</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_BALANCE' value="+nf.format(m_amount)+"></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		String m_guarantor1="";
		String m_guarantor2="";			
			
			rs3 = stmt.executeQuery("SELECT "+
			       " APPLICATION_NO, "+
						 " GUARANTOR_CODE "+
						 " FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
						 " where APPLICATION_NO='"+m_app_no+"' ");
							
			boolean more3=rs3.next();				
			int k=1;
			
			
			while(more3){
			if(k==1){
			  m_guarantor1=rs3.getString(2);
				}
				if(k==2){
				m_guarantor2=rs3.getString(2);
				
				}
			k=k+1;	
			more3=rs3.next();
			}
			
			
			String m_guar1_name="";
			String m_guar1_nic="";
			String m_guar1_add1="";
			String m_guar1_add2="";
			String m_guar1_city="";
			String m_guar1_dsg="";
			String m_guar2_name="";
			String m_guar2_nic="";
			String m_guar2_add1="";
			String m_guar2_add2="";
			String m_guar2_city="";
			String m_guar2_dsg="";
			
			
			rs5 = stmt.executeQuery("SELECT  NVL(FULL_NAME,'-'),NVL(NIC_NO,'-'),NVL(DESIGNATION,'-'),NVL(ADDRESS1,'-'),NVL(ADDRESS2,'-'),NVL(INITCAP(CITY_CODE),'.') "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" where CLIENT_CODE='"+m_guarantor1+"' ");
			
			boolean more5=rs5.next();
			
			if(more5){
			   m_guar1_name=rs5.getString(1);
			   m_guar1_nic =rs5.getString(2);
				 m_guar1_dsg=rs5.getString(3);
				 m_guar1_add1=rs5.getString(4);
				 m_guar1_add2=rs5.getString(5);
				 m_guar1_city=rs5.getString(6);
			}
			
		
			rs6 =  stmt.executeQuery("SELECT  NVL(FULL_NAME,'-'),NVL(NIC_NO,'-'),NVL(DESIGNATION,'-'),NVL(ADDRESS1,'-'),NVL(ADDRESS2,'-'),NVL(INITCAP(CITY_CODE),'.') "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" where CLIENT_CODE='"+m_guarantor2+"' ");
			
			boolean more6=rs6.next();
			
			if(more6){
			   m_guar2_name=rs6.getString(1);
			   m_guar2_nic =rs6.getString(2);
				 m_guar2_dsg =rs6.getString(3);
				 m_guar2_add1=rs6.getString(4);
				 m_guar2_add2=rs6.getString(5);
				 m_guar2_city=rs6.getString(6);
			}
			
		
		
		
		
		
		
    out.println("<tr>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0' class='table'>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2><B>&nbsp;&nbsp;1 GUARANTOR - I</B></td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; a) Name in full </td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; b) NIC Number </td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; c) Occupation/Designation</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; d) Address - Residential</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_GR1_NAME' value=\""+m_guar1_name+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_GR1_NIC' value=\""+m_guar1_nic+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_GR1_DESG' value=\""+m_guar1_dsg+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
		String m_guar1_add=m_guar1_add1+" "+m_guar1_add2+" "+m_guar1_city;
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_GR1_ADD' value=\""+m_guar1_add+"\"></td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
		out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
		out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><B>&nbsp;&nbsp;2 GUARANTOR - II</B></td>"); 
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; a) Name in full </td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; b) NIC Number </td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; c) Occupation/Designation </td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; d) Address - Residential &nbsp;&nbsp;&nbsp;&nbsp;(If space is insufficient, please use a separate Paper)      </td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_GR2_NAME' value=\""+m_guar2_name+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_GR2_NIC' value=\""+m_guar2_nic+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_GR2_DESG' value=\""+m_guar2_dsg+"\"></td>");
    out.println("</tr>");
    out.println("<tr>");
		String m_guar2_add=m_guar2_add1+" "+m_guar2_add2+" "+m_guar2_city;
    out.println("<td class='rep-body' font size=2><input class='txt_input' type='text'  style=\"width:160px;\" maxlength=\"100\" size=\"100\"    name='TXT_GR2_ADD' value=\""+m_guar2_add+"\"></td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
		
		out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
    
		
    out.println("</tr>");
    out.println("</table>");

			
	 out.println("</td>");
	 out.println("</tr>");
   out.println("</table>");		
			
		out.println("<br>");	
			
		
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'><B>.................................................</B></td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'><B>..................................</B></td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'><B>............................................</B></td>");
		out.println("</tr>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'>NAME OF AUTHORIZED OFFICER</td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'>SIGNATURE</td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'>TELEPHONE NUMBER&nbsp&nbsp&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("<hr color='black'>");
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'>For completion by the Bureau only</td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'></td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'></td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp; 1) Initial Code No: </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp;&nbsp;&nbsp;2) Borrower ID No:</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp; 3) Entered by </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp;&nbsp;&nbsp;4) Validated by </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("<br><br>");
		
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("</tr>");
		out.println("</font></TABLE>");			
				
				
		out.println("</form>");
		out.println("</body>");
		out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
		out.println("</html>");

			
			
			
			
			}
			
			}
			
			//=========================================================================================================================			
			
			else if(m_chksql.trim().equals("print_letter")){
			
			String m_crib_ref_no=req.getParameter("crib_ref_no");
			
			
			rs = stmt.executeQuery("SELECT CLIENT_CODE, "+
			                       "NVL(INSTITUTION_NAME,'-'), "+
														 "NVL(BRANCH_NAME,'-'), "+
														 "NVL(MONTH_END_DATE,'-'), "+
														 "NVL(TYPE_OF_ADVANCE,'-'), "+
														 "NVL(CLIENT_NAME,'-'), "+    //6
														 "NVL(CLIENT_SURNAME,'-'), "+ //7
														 "NVL(CO_APP_SURNAME,'-'), "+ //8
														 "NVL(CO_APP_NAME,'-'), "+   //9
														 "NVL(CLIENT_NIC,'-'), "+   //10
														 "NVL(CO_APP_NIC,'-'), "+   //11
														 "NVL(CLIENT_DISG,'-'), "+ //12
														 "NVL(CO_APP_DISG,'-'), "+ //13
														 "NVL(CLIENT_ADD,'-'), "+  //14
														 "NVL(CO_APP_ADD,'-'), "+   //15
														 "NVL(CLIENT_BUS_NAME,'-'), "+ //16
														 "NVL(CO_APP_BUS_NAME,'-'), "+  //17
														 "NVL(CLIENT_BUS_NO,'-'), "+
															"NVL(CO_APP_BUS_NO,'-'), "+
															"NVL(CLIENT_BUS_ADD,'-'), "+
															"NVL(CO_APP_BUS_ADD,'-'), "+
															"NVL(FINANCE_NO,'-'), "+  //22  
															"NVL(DATE_OF_GRNT,'-'), "+ //23
															"NVL(NATURE_OF_ADV,'-'), "+ //24
															"NVL(SECTOR,'-'), "+  //25
															"NVL(ADVANCE,'-'), "+
															"AMOUNT, "+
															"NVL(SECURITY,'-'), "+
															"BALANCE, "+ //29
															"NVL(GUAR1_NAME,'-'), "+ //30
															"NVL(GUAR1_NIC,'-'), "+
															"NVL(GUAR1_DESIG,'-'), "+
															"NVL(GUAR1_ADD,'-'), "+
															"NVL(GUAR2_NAME,'-'), "+
															"NVL(GUAR2_NIC,'-'), "+
															"NVL(GUAR2_DESIG,'-'), "+
															"NVL(GUAR2_ADD,'-') "+
															"FROM "+m_schema_name+".AF_CR_PRO_CRIB_REQ_DETAILS "+
															"WHERE CREIB_REF_NO='"+m_crib_ref_no+"' ");
															
															
	   boolean more=rs.next();
			
			
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Asset Financing System</title>    ");
		out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
		out.println("</head>");
		out.println("<Script>");
		
		
		 out.println("function add_button(){");
			
		  out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
	
						
			out.println("}");
			
			
			
			out.println("function save_data(){");
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
		  out.println("}");
		
		
		
		
		
		 out.println("</Script>");
		
		
			
				
				
			out.println("<body onload=\"add_button()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
			
			
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" ></table>");
	
		
			
			
			if(more){
			
				
			out.println("<br><br>");
			out.println("<TABLE border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>"); 
			out.println("<td width='*%'class='rep-body' font size=2><B>FROM: CRIB/P</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<TABLE border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=5><B>CREDIT INFORMATION BUREAU OF SRI LANKA</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=2><B>PRELIMINARY INFORMATION ON ADVANCES - PERSONAL BORROWER/S</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=2>REGULAR and IRREGULAR - RS 500,000 AND ABOVE[Please see overleaf]</td></tr>");
	  	out.println("</font></TABLE>");
			
			out.println("<br>");
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='39%'class='rep-body' font size=2>LENDING INSTITUTION&nbsp:"+rs.getString(2)+"</td>");
			out.println("<td width='30%'class='rep-body' font size=2>BRANCH&nbsp:"+rs.getString(3)+"</td>");
			out.println("<td width='30%'class='rep-body' font size=2>MONTH ENDING&nbsp:"+rs.getString(4)+"</td>"); 
			out.println("<td width='*%'class='rep-body' font size=2></td>");
	  	out.println("</tr></font></TABLE>");	
			
			out.println("<br>");	
			out.println("<font size=2><p style='text-align:justify'>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>A</B></td>");
			out.println("<td width='45%'class='rep-body' font size=2><B>TYPE OF ADVANCE(PLEASE TICK)</B></td>");
			out.println("<td width='50%'class='rep-body' font size=2>");
			  out.println("<table border='0' width='100%' class='table'><tr><td width='5%'></td>");
				 out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr style='text-align:center'><td width='50%'>REGULAR</td>");
					if(rs.getString(5).equals("REGULAR")){
			    out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_REGULAR_VALUE onclick=\"\" checked  disabled></td></TR></TABLE></td>");
					}else{
					out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_REGULAR_VALUE onclick=\"\" disabled ></td></TR></TABLE></td>");
					}
										
				 out.println("<td width='5%'></td>");
				 out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr tr style='text-align:center'><td width='50%'>IRREGULAR</td>");
					if(rs.getString(5).equals("IRREGULAR")){
					out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_IRREGULAR_VALUE=\"\" onclick=\"\" checked disabled ></td></TR></TABLE></td></tr></table>");
					}else{
					out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_IRREGULAR_VALUE=\"\" onclick=\"\" disabled></td></TR></TABLE></td></tr></table>");
					}
			
	  	out.println("</td></tr></font></TABLE>");	
			out.println("<br>");
			out.println("<font size=2><p style='text-align:justify'>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>B</B></td>");
			out.println("<td width='75%'class='rep-body' font size=2><B>INFORMATION ON BORROWERS/JOINT BORROWERS</B></td>");
			out.println("<td width='*%'class='rep-body' font size=2></td>");
			out.println("</tr></font></TABLE>");	
			out.println("<br>");
			
				out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2 valign='top'>1</td>");
			out.println("<td width='35%'class='rep-body' font size=2>NAME/S IN FULL(MR/MRS/MISS) [UNDERLINE SURNAME]</td>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1)&nbsp "+rs.getString(6)+"&nbsp<U>"+rs.getString(7)+"</U> </td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2> &nbsp&nbsp&nbsp&nbsp&nbsp 2)&nbsp "+rs.getString(9)+"&nbsp<U>"+rs.getString(8)+"</U>  </td>");
			//out.println("<td width='60%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 2) "+m_co_stname+"&nbsp"+m_co_surnam+"</td>");
			out.println("</tr></font></TABLE>");	
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2>2</td>");
			out.println("<td width='35%'class='rep-body' font size=2>NATIONAL IDENTITY CARD NUMBER/S</td>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1)&nbsp"+rs.getString(10)+" &nbsp 02)&nbsp"+rs.getString(11)+"  </td>");
			out.println("</tr></font></TABLE>");	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2>3</td>");
			out.println("<td width='35%'class='rep-body' font size=2>OCCUPATION/DESIGNATION</td>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1)&nbsp"+rs.getString(12)+"  2)&nbsp"+rs.getString(13)+" </td>");
			out.println("</tr></font></TABLE>");	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2>4</td>");
			out.println("<td width='35%'class='rep-body' font size=2>ADDRESS - RESIDENTIAL</td>");
			//out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1) "+m_cl_add1+"&nbsp"+m_cl_add2+"&nbsp"+m_cl_city+" </td></tr>"); 
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1)&nbsp"+rs.getString(14)+"</td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			
			//out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 2) "+m_co_add1+"&nbsp"+m_co_add2+"&nbsp"+m_co_city+"</td></tr>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 2)&nbsp"+rs.getString(15)+"</td></tr>");
			out.println("</font></TABLE>");	
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2 valign='top'>5</td>");
			out.println("<td width='35%'class='rep-body' font size=2>NAME OF BUSINESS (Where the above borrower/s is/are the Sole proprietor or a partner)</td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>:&nbsp&nbsp&nbsp&nbsp 1)&nbsp"+rs.getString(16)+" </td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>:&nbsp&nbsp&nbsp&nbsp 2)&nbsp"+rs.getString(17)+" </td></tr>");
			out.println("</font></TABLE>");	
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2>BUSINESS REGISTRATION NUMBER</td>");
			out.println("<td width='55%'class='rep-body' font size=2>:&nbsp&nbsp&nbsp&nbsp 1)&nbsp"+rs.getString(18)+"  2)&nbsp"+rs.getString(19)+" </td>");
			out.println("</tr></font></TABLE>");
			
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 	
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2 style='valign:up'></td>");
			out.println("<td width='35%'class='rep-body' font size=2>BUSINESS ADDRESS</td>");
			
			out.println("<td width='55%'class='rep-body' font size=2>&nbsp&nbsp&nbsp&nbsp&nbsp 1)&nbsp"+rs.getString(20)+" </td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			
			out.println("<td width='55%'class='rep-body' font size=2>&nbsp&nbsp&nbsp&nbsp&nbsp 2)&nbsp"+rs.getString(21)+" </td></tr>");
			out.println("</font></TABLE>");	
			out.println("</br>");
		  
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>C</B></td>");
			out.println("<td width='95%'class='rep-body' font size=2><B>DETAILS OF ADVANCES</B></td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='95%'class='rep-body' font size=2><table border='1' cellspacing='0' border color='black' width='100%' class='table'> ");
			out.println("<tr style='text-align:center'>");
			out.println("<td width='60%'class='rep-body' font size=2></td>");
			out.println("<td width='20%'class='rep-body' font size=2>ADVANCE1</td>");
			out.println("<td width='20%'class='rep-body' font size=2>ADVANCE2</td>");
			out.println("</tr>");
				out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 1. Loan/Facility Account Number</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;"+rs.getString(22)+"</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 2. Date of granting the Advance</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;"+rs.getString(23)+"</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 3. Nature of the Advance : Eg. Overdraft, Letter of Credit, Finance Lease etc</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;"+rs.getString(24)+"</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 4. Sector - Manufacturing, Construction, Agriculture, Transport etc</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;"+rs.getString(25)+"</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 5. Whether the advance is Direct or Indirect</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;"+rs.getString(26)+"</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 6. Amount granted/limit (Rs'000)</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;"+nf.format(rs.getDouble(27))+"</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 7. Security offered</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;"+rs.getString(28)+"</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='60%'class='rep-body' font size=2> 8. Balance outstanding (Rs'000)</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;"+nf.format(rs.getDouble(29))+"</td>");
			out.println("<td width='20%'class='rep-body' font size=2>&nbsp;</td>");
			out.println("</tr>");
			out.println("</TABLE></td></tr>");
			
			
			
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2></td></tr>");
			out.println("</font></TABLE>");	
			out.println("</br>");
		
	   
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>D</B></td>");
			out.println("<td width='95%'class='rep-body' font size=2><B>DETAILS OF GUARANTORS</B></td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='95%'class='rep-body' font size=2>");
		
		out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' border color='black' class='table' >");
    out.println("<tr style='text-align:center'>");
    out.println("<td width='30%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("<td width='35%' class='rep-body' font size=2><B>ADVANCE 1 </B></td>");
    out.println("<td width='35%' class='rep-body' font size=2><B>ADVANCE II </B></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0' class='table'>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2><B>&nbsp;&nbsp;1 GUARANTOR - I</B></td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; a) Name in full </td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; b) NIC Number </td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; c) Occupation/Designation</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; d) Address - Residential</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+rs.getString(30)+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+rs.getString(31)+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+rs.getString(32)+"</td>");
    out.println("</tr>");
    out.println("<tr>");
		out.println("<td class='rep-body' font size=2>&nbsp;"+rs.getString(33)+"</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><B>&nbsp;&nbsp;2 GUARANTOR - II</B></td>"); 
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; a) Name in full </td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; b) NIC Number </td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; c) Occupation/Designation </td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;&nbsp;&nbsp; d) Address - Residential </td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+rs.getString(34)+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+rs.getString(35)+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+rs.getString(36)+"</td>");
    out.println("</tr>");
    out.println("<tr>");
		out.println("<td class='rep-body' font size=2>&nbsp;"+rs.getString(37)+"</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("</tr>");
    out.println("</table>");

			
	 out.println("</td>");
	 out.println("</tr>");
   out.println("</table>");		
			
		out.println("<br>");	
			
		
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'><B>.................................................</B></td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'><B>..................................</B></td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'><B>............................................</B></td>");
		out.println("</tr>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'>NAME OF AUTHORIZED OFFICER</td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'>SIGNATURE</td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'>TELEPHONE NUMBER&nbsp&nbsp&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("<hr color='black'>");
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'>For completion by the Bureau only</td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'></td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'></td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp; 1) Initial Code No: </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp;&nbsp;&nbsp;2) Borrower ID No:</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp; 3) Entered by </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp;&nbsp;&nbsp;4) Validated by </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("<br><br>");
		
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("</tr>");
		out.println("</font></TABLE>");			
     
     }
 
 

		out.println("</form>");
		out.println("</body>");
		out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
		out.println("</html>");
			
			
				
			
			
			}
			
			
			else if(m_chksql.trim().equals("print_letter_corp")){
			
			String m_crib_ref_no=req.getParameter("crib_ref_no");
			
			rs = stmt.executeQuery("SELECT CLIENT_CODE, "+
			                       "INSTITUTION_NAME, "+
														 "BRANCH_NAME, "+
														 "MONTH_END_DATE, "+
														 "TYPE_OF_ADVANCE, "+
														 "NVL(CLIENT_NAME,'-'), "+    //6
														 "NVL(CLIENT_ADD,'-'), "+ //7
														 "NVL(CLIENT_BUS_NO,'-'), "+ //8
														 "NVL(FINANCE_NO,'-'), "+  //9
														 "NVL(DATE_OF_GRNT,'-'), "+
														 "NVL(NATURE_OF_ADV,'-'), "+	
														 "NVL(SECTOR,'-'), "+	//12
														 "NVL(ADVANCE,'-'), "+	//13
														 "AMOUNT, "+
														 "NVL(SECURITY,'-'), "+	
														 "BALANCE, "+
														 "NVL(GUAR1_NAME,'-'), "+ //17
														 "NVL(GUAR1_NIC,'-'), "+
														 "NVL(GUAR1_DESIG,'-'), "+
														 "NVL(GUAR1_ADD,'-'), "+	
														 "NVL(GUAR2_NAME,'-'), "+
														 "NVL(GUAR2_NIC,'-'), "+
														 "NVL(GUAR2_DESIG,'-'), "+
														 "NVL(GUAR2_ADD,'-') "+
							               "FROM "+m_schema_name+".AF_CR_PRO_CRIB_REQ_DETAILS "+
														 "WHERE CREIB_REF_NO='"+m_crib_ref_no+"' ");
															
			boolean more=rs.next();												
															
															
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Asset Financing System</title>    ");
		out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
		out.println("</head>");
		out.println("<Script>");
		
		
		 out.println("function add_button(){");
			
		  out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
	
						
			out.println("}");
			
			
			
			out.println("function save_data(){");
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
		  out.println("}");
		
		
		
		
		
		 out.println("</Script>");
		
		
			
				
				
			out.println("<body onload=\"add_button()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
			
			
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" ></table>");
	
					
			if(more){
			
			
			
			String m_fin_num = rs.getString(9);
			String m_gr_date = rs.getString(10);
			String m_tr_type = rs.getString(11);
			String m_sector = rs.getString(12);
			String m_advance = rs.getString(13);
			double m_amount = rs.getDouble(14);
			String m_security = rs.getString(15);
			double m_balance = rs.getDouble(16);
			
			String m_guar1_name = rs.getString(17);
			String m_guar1_nic = rs.getString(18);
			String m_guar1_dsg = rs.getString(19);
			String m_guar1_add = rs.getString(20);
			String m_guar2_name = rs.getString(21);
			String m_guar2_nic = rs.getString(22);
			String m_guar2_dsg = rs.getString(23);
			String m_guar2_add = rs.getString(24);
			
			
			
			
			
			
			
			
			
			
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
      out.println("</table>");
			
			out.println("<br><br>");
			out.println("<TABLE border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>"); 
			out.println("<td width='*%'class='rep-body' font size=2><B>FROM: CRIB/C</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<TABLE border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=5><B>CREDIT INFORMATION BUREAU OF SRI LANKA</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=2><B>PRELIMINARY INFORMATION ON ADVANCES - CORPORATE BORROWERS</B></td></tr>");
	  	out.println("</font></TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:center'><td width='*%'class='rep-body' font size=2>REGULAR and IRREGULAR - RS 500,000 AND ABOVE[Please see overleaf]</td></tr>");
	  	out.println("</font></TABLE>");
			
			out.println("<br>");
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='39%'class='rep-body' font size=2>LENDING INSTITUTION &nbsp:"+rs.getString(2)+" </td>");
			out.println("<td width='30%'class='rep-body' font size=2>BRANCH &nbsp:"+rs.getString(3)+"</td>");
			out.println("<td width='30%'class='rep-body' font size=2>MONTH ENDING &nbsp:"+rs.getString(4)+"</td>");
			//out.println("<input class='txt_input' type='text' name='TXT_MONTH_ENDING' maxlength='50' size='50' value="+m_month_end+"></td>");
			out.println("<td width='*%'class='rep-body' font size=2></td>");
	  	out.println("</tr></font></TABLE>");	
			out.println("<br>");	
			out.println("<font size=2><p style='text-align:justify'>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>A</B></td>");
			out.println("<td width='45%'class='rep-body' font size=2><B>TYPE OF ADVANCE(PLEASE TICK)</B></td>");
			out.println("<td width='50%'class='rep-body' font size=2>");
			out.println("<table border='0' width='100%' class='table'><tr><td width='5%'></td>");
			out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr style='text-align:center'><td width='50%'>REGULAR</td>");
			
								if(rs.getString(5).equals("REGULAR")){
			    out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_REGULAR_VALUE onclick=\"\" checked  disabled></td></TR></TABLE></td>");
					}else{
					out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_REGULAR_VALUE onclick=\"\" disabled ></td></TR></TABLE></td>");
					}
										
				 out.println("<td width='5%'></td>");
				 out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr tr style='text-align:center'><td width='50%'>IRREGULAR</td>");
					if(rs.getString(5).equals("IRREGULAR")){
					out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_IRREGULAR_VALUE=\"\" onclick=\"\" checked disabled ></td></TR></TABLE></td></tr></table>");
					}else{
					out.println("<td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_IRREGULAR_VALUE=\"\" onclick=\"\" disabled></td></TR></TABLE></td></tr></table>");
					}
			
			
			
			
		//	out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr style='text-align:center'><td width='50%'>REGULAR</td><td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_REGULAR_VALUE onclick=\"change_value()\"></td></TR></TABLE></td>");
		//	out.println("<td width='5%'></td>");
		//	out.println("<td width='45%'><table border='1' cellspacing='0' border color='black' width='100%' class='table'><tr tr style='text-align:center'><td width='50%'>IRREGULAR</td><td width='50%'><INPUT TYPE=\"checkbox\" NAME=CHK_IRREGULAR_VALUE onclick=\"\"></td></TR></TABLE></td></tr></table>");
			out.println("</td></tr></font></TABLE>");	
		
			out.println("<font size=2><p style='text-align:justify'>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>B</B></td>");
			out.println("<td width='75%'class='rep-body' font size=2><B>INFORMATION ON CORPORATE BORROWER</B></td>");
			out.println("<td width='*%'class='rep-body' font size=2></td>");
			out.println("</tr></font></TABLE>");
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' valign='top' font size=2>1</td>");
			out.println("<td width='30%'class='rep-body' font size=2 valign='top'>NAME OF THE CORPORATE BORROWER</td>");
			out.println("<td width='60%'class='rep-body' font size=2>:&nbsp"+rs.getString(6)+"</td>");
			out.println("</tr>");
		  out.println("</font></TABLE>");	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' valign='top' font size=2>2</td>");
			out.println("<td width='30%'class='rep-body' font size=2 valign='top'>REGISTERED ADDRESS</td>");
		//	String m_brrow_add=m_borro_add1+","+m_borro_add2+","+m_borro_city;
			out.println("<td width='60%'class='rep-body' font size=2>:&nbsp"+rs.getString(7)+"</td>");
			out.println("</tr>");
		  out.println("</font></TABLE>");	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' valign='top' font size=2>3</td>");
			out.println("<td width='30%'class='rep-body' font size=2 valign='top'>COMPANY/BUSINESS REGISTRATION NUMBER</td>");
			out.println("<td width='60%'class='rep-body' font size=2>:&nbsp"+rs.getString(8)+"</td>");
			out.println("</tr>");
		  out.println("</font></TABLE>");	
			
			
			//====================PROPRIETORS/PARTNERS==============================//
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' valign='top' font size=2>4</td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>NAME & ADDRESS OF THE PROPRIETORS/PARTNERS</td>");
			out.println("<td width='35%'class='rep-body' font size=2>NIC NUMBERS</td>");
			out.println("</tr>");
			
			
			
						rs1 = stmt.executeQuery("SELECT CREIB_REF_NO, "+
			                       "CLIENT_CODE, "+
														 "PARTNER_NAME, "+
														 "PARTNER_ADD, "+
														 "PARTNER_NIC "+
														 "FROM "+m_schema_name+".AF_CR_PRO_CRIB_PARTNERS_DET "+
														  "WHERE CREIB_REF_NO='"+m_crib_ref_no+"' ");
						
						
			boolean more1=rs1.next();	
			int i=1;
			

			while(more1){			
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>"+i+"&nbsp;NAME&nbsp;&nbsp;&nbsp;&nbsp;:"+rs1.getString(3)+"</td>");
			out.println("<td width='35%'class='rep-body' font size=2>"+rs1.getString(5)+"</td>");
			out.println("</tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>&nbsp;&nbsp;ADDRESS:"+rs1.getString(4)+"</td>");
			out.println("<td width='35%'class='rep-body' font size=2></td>");
			out.println("</tr>");
			i=i+1;
			more1=rs1.next();
			}
			
				
			
			out.println("</font></TABLE>");	

			
			
			
		
			
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2>5</td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>NAMES OF SUBSIDIARIES/ASSOCIATE COMPANIES</td>");
			out.println("<td width='35%'class='rep-body' font size=2>COMPANY REGISTRATION NUMBERS</td>");
			out.println("</tr>");
			
			
								
						
			rs2 = stmt.executeQuery("SELECT CREIB_REF_NO, "+
			                        "CLIENT_CODE, "+
															"SUBSIDERY_NAME, "+
															"SUBSIDERY_REG_NO "+
															"FROM LAKDL.AF_CR_PRO_CRIB_SUBSIDERY_DET "+
															 "WHERE CREIB_REF_NO='"+m_crib_ref_no+"' ");
																			
			
			boolean more2=rs2.next();
			int j=1;
			while(more2){
			
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='55%'class='rep-body' font size=2 valign='top'>"+j+"&nbsp;"+rs2.getString(3)+"</td>");
			out.println("<td width='35%'class='rep-body' font size=2>"+rs2.getString(4)+"</td>");
			out.println("</tr>");
			j=j+1;
			more2=rs2.next();
			}
					
			out.println("</font></TABLE>");	
			
			
	//===============================	DETAILS OF ADVANCES===================================//	
			
			out.println("<font size=2><p style='text-align:justify'>");	
			out.println("<table border='0' width='100%' class='table'>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2><B>C</B></td>");
			out.println("<td width='95%'class='rep-body' font size=2><B>DETAILS OF ADVANCES</B></td></tr>");
			out.println("<tr style='text-align:left'>");
			out.println("<td width='5%'class='rep-body' font size=2></td>");
			out.println("<td width='95%'class='rep-body' font size=2>");
		
		out.println("<table width='100%'  border='1' cellspacing='0' cellpadding='0' border color='black' class='table' >");
    out.println("<tr style='text-align:center'>");
    out.println("<td width='30%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2><B>ADVANCE 1 </B></td>");
    out.println("<td width='17.5%' class='rep-body' font size=2><B>ADVANCE 2 </B></td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><B>ADVANCE 3 </B></td>");
		out.println("<td width='17.5%' class='rep-body' font size=2><B>ADVANCE 4 </B></td>");
    out.println("</tr>");
			
			
		
		
			out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;1&nbsp; Loan/Facility Account Number</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;1</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Loan/Facility Account Number</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;"+m_fin_num+"</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;2&nbsp; Date of granting the Advance</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;2</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Date of granting the Advance</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;"+m_gr_date+"</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;3&nbsp; Nature of the Advance: Eg. Overdraft Letter of Credit, Finance Lease etc.</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;3</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Nature of the Advance: Eg. Overdraft Letter of Credit, Finance Lease etc.</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;"+m_tr_type+"</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;4&nbsp; Sector - Manufacturing, Construction, Agriculture, Transport etc.</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;4</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Sector - Manufacturing, Construction, Agriculture, Transport etc.</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;"+m_sector+"</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
  //  out.println("<td width='30%' class='rep-body' font size=2>&nbsp;5&nbsp; Where the advance is Direct or Indirect</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;5</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Where the advance is Direct or Indirect</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;"+m_advance+"</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
  //  out.println("<td width='30%' class='rep-body' font size=2>&nbsp;6&nbsp; Amount granted/limit(Rs'000).</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;6</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Amount granted/limit(Rs'000).</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;"+nf.format(m_amount)+"</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
   // out.println("<td width='30%' class='rep-body' font size=2>&nbsp;7&nbsp; Security offered</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;7</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Security offered</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;"+m_security+"</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
  //  out.println("<td width='30%' class='rep-body' font size=2>&nbsp;8&nbsp; Balance outstanding(Rs'000)</td>");
		out.println("<td width='30%' class='rep-body' font size=2><table border='0' width='100%' class='table'>");
		out.println("<tr style='text-align:left'>");
		out.println("<td width='5%' class='rep-body' valign='top' font size=2>&nbsp;8</td>");
		out.println("<td width='95%' class='rep-body' font size=2>Balance outstanding(Rs'000)</td>");
		out.println("</tr></table>");
		out.println("</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;"+nf.format(m_balance)+"</td>");
    out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
		out.println("<td width='17.5%' class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
	
			
		  out.println("<tr>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0' class='table'>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2><B>&nbsp;&nbsp;1 GUARANTOR - I</B></td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; a) Name in full </td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; b) NIC Number </td>");
    out.println("</tr>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; c) Occupation/Designation</td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; d) Address - Residential</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+m_guar1_name+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+m_guar1_nic+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+m_guar1_dsg+"</td>");
    out.println("</tr>");
    out.println("<tr>");
		//String m_guar1_add=m_guar1_add1+","+m_guar1_add2+","+m_guar1_city;
    out.println("<td class='rep-body' font size=2>&nbsp;"+m_guar1_add+"</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
		out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
		out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2><B>&nbsp;&nbsp;2 GUARANTOR - II</B></td>"); 
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; a) Name in full </td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; b) NIC Number </td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; c) Occupation/Designation </td>");
    out.println("</tr>");
		out.println("<tr style='text-align:left'>");
    out.println("<td class='rep-body' valign='top' font size=2>&nbsp;&nbsp;&nbsp; d) Address - Residential &nbsp;&nbsp;&nbsp;&nbsp;(If space is insufficient, please use a separate Paper)      </td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+m_guar2_name+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+m_guar2_nic+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;"+m_guar2_dsg+"</td>");
    out.println("</tr>");
    out.println("<tr>");
		//String m_guar2_add=m_guar2_add1+","+m_guar2_add2+","+m_guar2_city;
    out.println("<td class='rep-body' font size=2>&nbsp;"+m_guar2_add+"</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
		
		out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
    out.println("<td><table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td class='rep-body' font size=2>&nbsp;</td>");
    out.println("</tr>");
    out.println("</table></td>");
    
		
    out.println("</tr>");
    out.println("</table>");

			
	 out.println("</td>");
	 out.println("</tr>");
   out.println("</table>");		
			
		out.println("<br>");	
			
		
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'><B>.................................................</B></td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'><B>..................................</B></td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'><B>............................................</B></td>");
		out.println("</tr>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'>NAME OF AUTHORIZED OFFICER</td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'>SIGNATURE</td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'>TELEPHONE NUMBER&nbsp&nbsp&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("<hr color='black'>");
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:left'>For completion by the Bureau only</td>");
		out.println("<td width='30%'class='rep-body' font size=2 style='text-align:center'></td>");
		out.println("<td width='35%'class='rep-body' font size=2 style='text-align:right'></td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		
		out.println("<font size=2><p style='text-align:justify'>");
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp; 1) Initial Code No: </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp;&nbsp;&nbsp;2) Borrower ID No:</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp; 3) Entered by </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>&nbsp;&nbsp;&nbsp;4) Validated by </td>");
		out.println("<td width='25%'class='rep-body' font size=2 style='text-align:left'>");
		out.println("<table border='1' cellspacing='0' width='100%' class='table'>");
		out.println("<tr style='text-align:center'>");
		out.println("<td width='*%'class='rep-body' font size=2 style='text-align:left'>&nbsp</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("</td>");
		out.println("</tr>");
		out.println("</font></TABLE>");	
		out.println("<br><br>");
		
		out.println("<table border='0' width='100%' class='table'>"); 		
		out.println("<tr style='text-align:center'>");
		out.println("</tr>");
		out.println("</font></TABLE>");			
				
				
		out.println("</form>");
		out.println("</body>");
		out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
		out.println("</html>");

	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
															
			
			}
			
			}
			
			
			
			
			
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
