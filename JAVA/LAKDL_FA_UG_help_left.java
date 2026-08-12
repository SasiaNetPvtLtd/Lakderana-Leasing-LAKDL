

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL on 24-05-2007

public class LAKDL_FA_UG_help_left extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			
						out.println("<HTML>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
						out.println("<HEAD>");
						out.println("	<TITLE>Help</TITLE> ");
						out.println("	<STYLE TYPE='text/css'> ");
						out.println("	#menu1 { display : none } ");
						out.println("	#menu1r { display : none } ");
						out.println("	#menu2 { display : none } ");
						out.println("	#menu3 { display : none } ");
						out.println("	#menu4 { display : none } ");
						out.println("	#menu5 { display : none } ");
						out.println("	#menu6 { display : none } ");							
						out.println("	#menu3v { display : none } ");
						out.println("	#menu3im { display : none } ");
						out.println("	#menu3sm { display : none } ");						
						out.println(" #menu3ism { display : none } ");
						out.println("	#menu3cd { display : none }");
						out.println("	#menu3sysm { display : none }");
						out.println("	#menu3r { display : none }");
						out.println("	#menu3cb { display : none }");
						out.println("	#menuintro { display : none }");						
						out.println("	#menu3refinfo { display : none }");
						out.println(" #menu6sm { display : none } ");
						out.println(" #menu3ir { display : none } ");
						out.println(" #menu3cs { display : none } ");
						out.println("	a:link {color:black; text-decoration:none}");//
						out.println("	a:hover {color:blue; text-decoration:underline} ");
						out.println("	a:active {color:blue; text-decoration:underline}");
						out.println(" </STYLE>");
						
						out.println("<script language='JavaScript1.2'>");
						out.println("plus = true ");
						
						out.println("function dualintro()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodropintro();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else ");
						out.println(" if (plus == false){");
						out.println(" dropintro();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");

						out.println("function dual1r()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1r();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else if (plus == false){");
						out.println(" drop1r();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual1()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual2()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop2();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop2();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual3()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop3();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop3();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual3v()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3v();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3v();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3im()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3im();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3im();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3refinfo()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3refinfo();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3refinfo();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3adjust()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3adjust();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3adjust();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3pd()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3pd();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3pd();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3cp()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3cp();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3cp();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3crr()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3crr();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3crr();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3ir()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3ir();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3ir();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3cs()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3cs();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3cs();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3sm()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3sm();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3sm();");
						out.println("	plus = true; ");
						out.println("}");
						out.println("}");
						
						out.println("function dual3ism()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3ism();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3ism();");
						out.println("	plus = true; ");
						out.println("}");
						out.println("}");
						
						out.println("function dual4sm()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop4sm();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop4sm();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual5sm()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop5sm();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop5sm();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual6sm()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop6sm();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop6sm();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3cd()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3cd();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3cd();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");

						out.println("function dual3sysm()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3sysm();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3sysm();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3r()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3r();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3r();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dual3cb()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodrop3cb();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	drop3cb();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");

						out.println("function dual4()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop4();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop4();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual5()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop5();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop5();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual6()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop6();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop6();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dropintro() {");
						out.println("document.all.menuintro.style.display = 'none';");
						out.println("}");
						out.println("function nodropintro() {");
						out.println("document.all.menuintro.style.display = 'block';");
						out.println("}");						
						out.println("function drop1r() {");
						out.println("document.all.menu1r.style.display = 'none';");
						out.println("}");
						out.println("function nodrop1r() {");
						out.println("document.all.menu1r.style.display = 'block';");
						out.println("}");
						out.println("function drop1() {");
						out.println("document.all.menu1.style.display = 'block';");
						out.println("}");
						out.println("function nodrop1() {");
						out.println("document.all.menu1.style.display = 'none';");
						out.println("}");
						out.println("function drop2() {");
						out.println("document.all.menu2.style.display = 'none';");
						out.println("}");
						out.println("function nodrop2() {");
						out.println("document.all.menu2.style.display = 'block';");
						out.println("}");
						out.println("function drop3() {");
						out.println("document.all.menu3.style.display = 'none';");
						out.println("}");
						out.println("function nodrop3adjust() {");
						out.println("document.all.menu3adjust.style.display = 'none';");
						out.println("}");
						out.println("function drop3adjust() {");
						out.println("document.all.menu3adjust.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3pd() {");
						out.println("document.all.menu3pd.style.display = 'none';");
						out.println("}");
						out.println("function drop3pd() {");
						out.println("document.all.menu3pd.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3cp() {");
						out.println("document.all.menu3cp.style.display = 'none';");
						out.println("}");
						out.println("function drop3cp() {");
						out.println("document.all.menu3cp.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3crr() {");
						out.println("document.all.menu3crr.style.display = 'none';");
						out.println("}");
						out.println("function drop3crr() {");
						out.println("document.all.menu3crr.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3ir() {");
						out.println("document.all.menu3ir.style.display = 'none';");
						out.println("}");
						out.println("function drop3ir() {");
						out.println("document.all.menu3ir.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3cs() {");
						out.println("document.all.menu3cs.style.display = 'none';");
						out.println("}");
						out.println("function drop3cs() {");
						out.println("document.all.menu3cs.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3refinfo() {");
						out.println("document.all.menu3refinfo.style.display = 'none';");
						out.println("}");
						out.println("function drop3refinfo() {");
						out.println("document.all.menu3refinfo.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3() {");
						out.println("document.all.menu3.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3v(){");
						out.println("	document.all.menu3v.style.display = 'none';");
						out.println("}");
						out.println("function drop3v(){");
						out.println("	document.all.menu3v.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3im(){");
						out.println("	document.all.menu3im.style.display = 'none';");
						out.println("}");
						out.println("function drop3im(){");
						out.println("	document.all.menu3im.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3sm(){");
						out.println("	document.all.menu3sm.style.display = 'none';");
						out.println("}");
						out.println("function nodrop3ism(){ ");
						out.println("	document.all.menu3ism.style.display = 'none';");
						out.println("}");
						out.println("function nodrop4sm(){");
						out.println("	document.all.menu4sm.style.display = 'none';");
						out.println("}");
						out.println("function nodrop5sm(){");
						out.println("	document.all.menu5sm.style.display = 'none';");
						out.println("}");
						out.println("function nodrop6sm(){");
						out.println("	document.all.menu6sm.style.display = 'none';");
						out.println("}");
						out.println("function drop4sm(){");
						out.println("	document.all.menu4sm.style.display = 'block';");
						out.println("}");
						out.println("function drop5sm(){");
						out.println("	document.all.menu5sm.style.display = 'block';");
						out.println("}");
						out.println("function drop6sm(){");
						out.println("	document.all.menu6sm.style.display = 'block';");
						out.println("}");
						out.println("function drop3sm(){");
						out.println("	document.all.menu3sm.style.display = 'block';");
						out.println("}");
						out.println("function drop3ism(){");
						out.println("	document.all.menu3ism.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3cd(){");
						out.println("	document.all.menu3cd.style.display = 'none';");
						out.println("}");
						out.println("function drop3cd(){");
						out.println("	document.all.menu3cd.style.display = 'block';");
						out.println("}");						
						out.println("function nodrop3sysm(){");
						out.println("	document.all.menu3sysm.style.display = 'none';");
						out.println("}");
						out.println("function drop3sysm(){");
						out.println("	document.all.menu3sysm.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3r(){");
						out.println("	document.all.menu3r.style.display = 'none';");
						out.println("}");
						out.println("function drop3r(){");
						out.println("	document.all.menu3r.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3cb(){");
						out.println("	document.all.menu3cb.style.display = 'none';");
						out.println("}");
						out.println("function drop3cb(){");
						out.println("	document.all.menu3cb.style.display = 'block';");
						out.println("}");						
						out.println("function drop4() {");
						out.println("document.all.menu4.style.display = 'none';");
						out.println("}");
						out.println("function nodrop4() {");
						out.println("document.all.menu4.style.display = 'block';");
						out.println("}");
						out.println("function drop5() {");
						out.println("document.all.menu5.style.display = 'none';");
						out.println("}");
						out.println("function nodrop5() {");
						out.println("document.all.menu5.style.display = 'block';");
						out.println("}");
						out.println("function drop6() {");
						out.println("document.all.menu6.style.display = 'none';");
						out.println("}");
						out.println("function nodrop6() {");
						out.println("document.all.menu6.style.display = 'block';");
					  out.println("}");
						
						out.println(" </script>");
						out.println("</HEAD>");
						out.println("<BODY bgcolor='#ffffff' link='#000000' vlink='#000000'> ");
						out.println("<br>");
						out.println("<table border='0' cellspacing='0' cellpadding='0' >");
						out.println("<tr>");
						out.println("<td align='left'><img src='"+m_html_client_url+"/images/h-guide.gif' width=160 height=23 border=0></td>");
						out.println("</tr>");
						out.println("</table>");
						
						out.println("<TABLE BORDER='0' ALIGN='left'>");
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<span onclick='dualintro()';>");
						//out.println("<FONT SIZE='-1' FACE='verdana' STYLE='{cursor: hand;}'><b>");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing#Marketing' STYLE='{text-decoration:none;}' >Marketing</a></SPAN><BR>");//</FONT> 
						out.println("<span ID='menuintro' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing#Inquiry' STYLE='{text-decoration:none;}' >Inquiry </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing#Client' STYLE='{text-decoration:none;}' >Client/Debtor Info Collection </a><BR>");						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing#Credit_Verification' STYLE='{text-decoration:none;}' >Initial Credit Verification </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing#Quotation' STYLE='{text-decoration:none;}' >Quotation </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing#Quotation_Approval' STYLE='{text-decoration:none;}' >Quotation Approval </a><BR>");
						out.println("</span>");
						out.println("</TD>");
						out.println("</TR>");
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");						
						out.println("<SPAN onClick='dual3();'>");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Credit' STYLE='{text-decoration:none;}' >Credit</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menu3'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Debtor' STYLE='{text-decoration:none;}'>Client/Debtor Creation</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Debtor_Approval' STYLE='{text-decoration:none;}'>Client/Debtor Creation Approval</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Create_Client_Facility' STYLE='{text-decoration:none;}'>Create Client Facility</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Facility_Approval' STYLE='{text-decoration:none;}'>Client Facility Approval Level 1</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Facility_Confirmation' STYLE='{text-decoration:none;}'>Client Facility Confirmation</a><BR>");
						
						out.println("<SPAN onClick='dual3v();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Credit_Evaluation' STYLE='{text-decoration:none;}'>Credit Evaluation - Factoring</a></SPAN><BR>");
						out.println("<SPAN ID='menu3v' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Credit_Score_Entry' STYLE='{text-decoration:none;}'>Credit Score Entry</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Credit_Score_Approval' STYLE='{text-decoration:none;}'>Credit Score Approval</a><BR>");
						out.println("</SPAN>");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Assign_Client_Debtor' STYLE='{text-decoration:none;}'>Assign Client-Debtor</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Activate_Client_Debtor' STYLE='{text-decoration:none;}'>Activate Client-Debtor</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Activate_Client_Debtor' STYLE='{text-decoration:none;}'>Facility Activation</a><BR>");
						
						out.println("<SPAN onClick='dual3im();' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Legal_Letters' STYLE='{text-decoration:none;}'>Legal Letters</a></SPAN><BR>");
						out.println("<SPAN ID='menu3im'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Agreement_Printing' target='help-right' STYLE='{text-decoration:none;}'>Agreement Printing</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Legal_Letter' STYLE='{text-decoration:none;}'>Legal Letter - Client</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Agreement_Enhance' STYLE='{text-decoration:none;}'>Agreement Enhancement</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Legal_Letter_To_Debtor' STYLE='{text-decoration:none;}'>Legal Letter To Debtor</a><BR>");
						out.println("</SPAN>");
						
						out.println("<SPAN onClick='dual3sm();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Credit_Maintenace' STYLE='{text-decoration:none;}'>Credit Maintenance</a></SPAN><BR>");
						out.println("<SPAN ID='menu3sm'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Facility_Main' STYLE='{text-decoration:none;}'>Client Facility Maintenance</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Debtor_Relation' STYLE='{text-decoration:none;}'>Client/Debtor Relation Maintenance</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Client_Debtor_Main' target='help-right' STYLE='{text-decoration:none;}'>Client/Debtor Maintenance</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit#Debtor_Limit' target='help-right' STYLE='{text-decoration:none;}'>Debtor Limit Adjustments</a><BR>");
						out.println("</SPAN>");
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");

						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dual1();'> ");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Operation' STYLE='{text-decoration:none;}'>Operations</a></SPAN><BR>");
						out.println("<SPAN ID='menu1'>");
						
						out.println("<SPAN onClick='dual3sysm();'>");//menu3adjust  //menu3refinfo
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Process' STYLE='{text-decoration:none;}'>Invoice Process</a></SPAN><BR>");
						out.println("<SPAN ID='menu3sysm'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Enter'  target='help-right' STYLE='{text-decoration:none;}'>Invoice Entry</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Approval' target='help-right' STYLE='{text-decoration:none;}'>Invoice Approval</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Confirmation' target='help-right' STYLE='{text-decoration:none;}'>Invoice Confirmation</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Approval_Credit' target='help-right' STYLE='{text-decoration:none;}'>Invoice Approval-Credit</a><BR>");  
						out.println("</SPAN>");

						out.println("<SPAN onClick='dual3refinfo();'>");//dual3adjust
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Adjustments' STYLE='{text-decoration:none;}'>Adjustments-Cr/Dr Notes </a></SPAN><BR>");
						out.println("<SPAN ID='menu3refinfo'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Adjustments' target='help-right' STYLE='{text-decoration:none;}' >Invoice Adjustments</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Adjustments_Approval' target='help-right' STYLE='{text-decoration:none;}'>Adjustments - Approval</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Adjustments' target='help-right' STYLE='{text-decoration:none;}'>Client Adjustments</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Reassignment' target='help-right' STYLE='{text-decoration:none;}'>Invoice Reassignment</a><BR>");  
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Charges_Reversal' target='help-right' STYLE='{text-decoration:none;}' >Charges Reversal</a><BR>");
						out.println("</SPAN>");

						out.println("<SPAN onClick='dual6sm();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#PD_Receipt' STYLE='{text-decoration:none;}'>PD & Receipts Entry</a></SPAN><BR>");
						out.println("<SPAN ID='menu6sm'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#PD_Enter_Allo' target='help-right' STYLE='{text-decoration:none;}'>PD Cheque Entry & Allocation</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Settle_Receipt_Entry' target='help-right' STYLE='{text-decoration:none;}'>Settlement Receipt Entry</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Settle_Schedule_Entry' target='help-right' STYLE='{text-decoration:none;}'>Settlement Schedule Entry</a><BR>");
						out.println("</SPAN>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#PD_Receipt_Gen' STYLE='{text-decoration:none;}'>PD Cheque Receipt Generation</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Settle_Receipt_Approval' STYLE='{text-decoration:none;}'>Settlement Receipt Approval</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Settle_Deposit' STYLE='{text-decoration:none;}'>Settlements Deposit</a><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Return_Realisation' target='help-right' STYLE='{text-decoration:none;}'>Return/Realisation</a><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Allocation' target='help-right' STYLE='{text-decoration:none;}'>Invoice Allocation</a><br>");
						
						out.println("<SPAN onClick='dual4();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Payments' STYLE='{text-decoration:none;}'>Client Payments</a></SPAN><BR>");
						out.println("<SPAN ID='menu4'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Availability' target='help-right' STYLE='{text-decoration:none;}'>Client Availability</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Payment' target='help-right' STYLE='{text-decoration:none;}'>Client Payments</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Payment_Approval' target='help-right' STYLE='{text-decoration:none;}'>Client Payment Approval</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Payment_Confirmation' target='help-right' STYLE='{text-decoration:none;}'>Client Payment Confirmation</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Payment_Higher_Approval' target='help-right' STYLE='{text-decoration:none;}'>Client Payment Higher Approval</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Cheque_Printing' target='help-right' STYLE='{text-decoration:none;}'>Cheque Print</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Cheque_Disbursement' target='help-right' STYLE='{text-decoration:none;}'>Cheque Disbursement</a><BR>");
						out.println("</SPAN>");
						
						out.println("<SPAN onClick='dual3ism();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Cheque_Return' STYLE='{text-decoration:none;}'>Cheque Return Reminders</a></SPAN><BR>");
						out.println("<SPAN ID='menu3ism'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Initial_Cheque' target='help-right' STYLE='{text-decoration:none;}'>Initial Cheque Return Reminder</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Final_Cheque_Without' target='help-right' STYLE='{text-decoration:none;}'>Final Cheque Return Reminder(Without remark)</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Final_Cheque_With' target='help-right' STYLE='{text-decoration:none;}'>Final Cheque Return Reminder(With remark)</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Cheque_Return_Reminder' target='help-right' STYLE='{text-decoration:none;}'>Cheque Return Reminder</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Legal_Letter' target='help-right' STYLE='{text-decoration:none;}'>Legal Letter - Cheque Return</a><BR>");
						out.println("</SPAN>");
						
						out.println("<SPAN onClick='dual3ir();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Invoice_Remider' STYLE='{text-decoration:none;}'>Invoice Reminders</a></SPAN><BR>");
						out.println("<SPAN ID='menu3ir'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Pre' target='help-right' STYLE='{text-decoration:none;}'>Pre Reminder</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#First' target='help-right' STYLE='{text-decoration:none;}'>1st Reminder</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Second' target='help-right' STYLE='{text-decoration:none;}'>2nd Reminder</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Final' target='help-right' STYLE='{text-decoration:none;}'>Final Reminder</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Final' target='help-right' STYLE='{text-decoration:none;}'>Invoice Reminder</a><BR>");
						out.println("</SPAN>");
						
						out.println("<SPAN onClick='dual3cs();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_Statements' STYLE='{text-decoration:none;}'>Client Statements</a></SPAN><BR>");
						out.println("<SPAN ID='menu3cs'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Client_State' target='help-right' STYLE='{text-decoration:none;}'>Client Statement</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operation#Tax_Invoice' target='help-right' STYLE='{text-decoration:none;}'>Generate Tax Invoice</a><BR>");
						out.println("</SPAN>");
						out.println("</SPAN>");
						out.println("</TD>");
						out.println("</TR>");
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dual2();'> ");
						//out.println("<FONT SIZE='-1' FACE='verdana' STYLE='{cursor: hand;}'><b>");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_System_Maintenance#System_Maintenance' STYLE='{text-decoration:none;}'>System Maintenance</a></SPAN><BR>");
						out.println("<SPAN ID='menu2'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_System_Maintenance#Day_End_Routine' STYLE='{text-decoration:none;}'>Day End Routine</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<A target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_System_Maintenance#Month_End_Routine' STYLE='{text-decoration:none;}'>Month End Routine</A><BR>	");					
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						
						out.println("</table>");
						out.println("</BODY>");
						out.println("</HTML>");
						out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
