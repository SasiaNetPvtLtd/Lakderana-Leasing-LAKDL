

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL on 24-05-2007

public class LAKDL_AF_UG_help_left_nw extends javax.servlet.http.HttpServlet { 

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
						
						out.println("	#menu1_2 { display : none } ");
						out.println("	#menu1_3 { display : none } ");
						out.println("	#menu1_4 { display : none } ");
						
						out.println("	#menu1_5 { display : none } ");
						
						out.println("	#menu1_6 { display : none } ");
						
						out.println("	#menu1_7 { display : none } ");
						
						out.println("	#menu1_8 { display : none } ");
						
						out.println("	#menu1_9 { display : none } ");
						out.println("	#menu1_10 { display : none } ");
						out.println("	#menu6_5 { display : none } ");
						out.println("	#menu6_6 { display : none } ");
						out.println("	#menu6_4 { display : none } ");
						out.println("	#menu6_3 { display : none } ");
						out.println("	#menu6_2 { display : none } ");
						out.println("	#menuLetDoc { display : none } ");
						out.println("	#menuLetDoc_1 { display : none } ");
						out.println("	#menuLetDoc_2 { display : none } ");
						
						out.println("	#Maintenancemnu { display : none } ");
						out.println("	#menu2 { display : none } ");
						out.println("	#menu3 { display : none } ");
						
						out.println("	#menu3_2 { display : none } ");
						out.println("	#menu3_3 { display : none } ");
						out.println("	#menu3_4 { display : none } ");
						out.println("	#menu3_5 { display : none } ");
						out.println("	#menu3_6 { display : none } ");
						out.println("	#menu3_7 { display : none } ");

						
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
						out.println("	a:link {color:black; text-decoration:none}");
						out.println("	a:hover {color:blue; text-decoration:underline} ");
						out.println("	a:active {color:blue; text-decoration:none}");
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
						
						out.println("function dual1_2()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_2();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_2();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual1_3()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_3();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_3();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						
						out.println("function dual1_4()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_4();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_4();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual1_5()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_5();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_5();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual1_6()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_6();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_6();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual1_7()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_7();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_7();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual1_8()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_8();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_8();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						
						out.println("function dual1_9()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_9();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_9();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual1_10()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_10();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_10();");
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
						
						out.println("function dual3_2()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop3_2();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop3_2();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						
						out.println("function dual3_3()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop3_3();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop3_3();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual3_4()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop3_4();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop3_4();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual3_5()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop3_5();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop3_5();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual3_6()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop3_6();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop3_6();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						
						out.println("function dual3_7()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop3_7();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop3_7();");
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
						
						
						out.println("function dualMaintenance()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodropMaintenanmnu();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	dropMaintainacemnu();");
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
						
						out.println("function dual6_2()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop6_2();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop6_2();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						out.println("function dual6_3()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop6_3();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop6_3();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						
						out.println("function dual6_4()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop6_4();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop6_4();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						
						out.println("function dual6_5()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop6_5();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop6_5();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						
						out.println("function dual6_6()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop6_6();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop6_6();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");





						
						
						
						out.println("function dualLett_doc()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodropLetDoc();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	dropLetDoc();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dualLett_doc_1()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodropLetDoc_1();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	dropLetDoc_1();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						out.println("function dualLett_doc_2()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodropLetDoc_2();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	dropLetDoc_2();");
						out.println("	plus = true;");
						out.println("}");
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
						out.println("document.all.menu1.style.display = 'none';");
						out.println("}");
						out.println("function drop1_2() {");
						out.println("document.all.menu1_2.style.display = 'none';");
						out.println("}");
						
						out.println("function drop1_3() {");
						out.println("document.all.menu1_3.style.display = 'none';");
						out.println("}");
						
						out.println("function drop1_4() {");
						out.println("document.all.menu1_4.style.display = 'none';");
						out.println("}");
						
						out.println("function drop1_5() {");
						out.println("document.all.menu1_5.style.display = 'none';");
						out.println("}");
						
						out.println("function drop1_6() {");
						out.println("document.all.menu1_6.style.display = 'none';");
						out.println("}");
						
						out.println("function drop1_7() {");
						out.println("document.all.menu1_7.style.display = 'none';");
						out.println("}");
						
						out.println("function drop1_8() {");
						out.println("document.all.menu1_8.style.display = 'none';");
						out.println("}");
						
						out.println("function drop1_9() {");
						out.println("document.all.menu1_9.style.display = 'none';");
						out.println("}");

						out.println("function drop1_10() {");
						out.println("document.all.menu1_10.style.display = 'none';");
						out.println("}");




						
						out.println("function nodrop1() {");
						out.println("document.all.menu1.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop1_2() {");
						out.println("document.all.menu1_2.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop1_3() {");
						out.println("document.all.menu1_3.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop1_4() {");
						out.println("document.all.menu1_4.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop1_5() {");
						out.println("document.all.menu1_5.style.display = 'block';");
						out.println("}");
						
						
						out.println("function nodrop1_6() {");
						out.println("document.all.menu1_6.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop1_7() {");
						out.println("document.all.menu1_7.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop1_8() {");
						out.println("document.all.menu1_8.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop1_9() {");
						out.println("document.all.menu1_9.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop1_10() {");
						out.println("document.all.menu1_10.style.display = 'block';");
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
						
						out.println("function drop3_2() {");
						out.println("document.all.menu3_2.style.display = 'none';");
						out.println("}");
						
						out.println("function drop3_3() {");
						out.println("document.all.menu3_3.style.display = 'none';");
						out.println("}");
						
						out.println("function drop3_4() {");
						out.println("document.all.menu3_4.style.display = 'none';");
						out.println("}");
						
						out.println("function drop3_5() {");
						out.println("document.all.menu3_5.style.display = 'none';");
						out.println("}");
						
						out.println("function drop3_6() {");
						out.println("document.all.menu3_6.style.display = 'none';");
						out.println("}");
						
						out.println("function drop3_7() {");
						out.println("document.all.menu3_7.style.display = 'none';");
						out.println("}");






						
						out.println("function nodrop3refinfo() {");
						out.println("document.all.menu3refinfo.style.display = 'block';");
						out.println("}");
						out.println("function drop3refinfo() {");
						out.println("document.all.menu3refinfo.style.display = 'none';");
						out.println("}");
						out.println("function nodrop3() {");
						out.println("document.all.menu3.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop3_2() {");
						out.println("document.all.menu3_2.style.display = 'block';");
						out.println("}");
						
						
						out.println("function nodrop3_3() {");
						out.println("document.all.menu3_3.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop3_4() {");
						out.println("document.all.menu3_4.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop3_5() {");
						out.println("document.all.menu3_5.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop3_6() {");
						out.println("document.all.menu3_6.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop3_7() {");
						out.println("document.all.menu3_7.style.display = 'block';");
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
						
						
						out.println("function nodropMaintenanmnu(){");
						out.println("	document.all.Maintenancemnu.style.display = 'none';");
						out.println("}");

						
						out.println("function nodropLetDoc(){");
						out.println("	document.all.menuLetDoc.style.display = 'none';");
						out.println("}");
						
						out.println("function nodropLetDoc_1(){");
						out.println("	document.all.menuLetDoc_1.style.display = 'none';");
						out.println("}");
						
						out.println("function nodropLetDoc_2(){");
						out.println("	document.all.menuLetDoc_2.style.display = 'none';");
						out.println("}");



						
						out.println("function drop3im(){");
						out.println("	document.all.menu3im.style.display = 'block';");
						out.println("}");
						
						out.println("function dropMaintainacemnu(){");
						out.println("	document.all.Maintenancemnu.style.display = 'block';");
						out.println("}");

						
						out.println("function dropLetDoc(){");
						out.println("	document.all.menuLetDoc.style.display = 'block';");
						out.println("}");
						
						out.println("function dropLetDoc_1(){");
						out.println("	document.all.menuLetDoc_1.style.display = 'block';");
						out.println("}");
						
						out.println("function dropLetDoc_2(){");
						out.println("	document.all.menuLetDoc_2.style.display = 'block';");
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
						
						out.println("function drop6_2() {");
						out.println("document.all.menu6_2.style.display = 'none';");
						out.println("}");
						
						out.println("function drop6_3() {");
						out.println("document.all.menu6_3.style.display = 'none';");
						out.println("}");
						
						out.println("function drop6_4() {");
						out.println("document.all.menu6_4.style.display = 'none';");
						out.println("}");
						
						out.println("function drop6_5() {");
						out.println("document.all.menu6_5.style.display = 'none';");
						out.println("}");
						
						out.println("function drop6_6() {");
						out.println("document.all.menu6_6.style.display = 'none';");
						out.println("}");



						
						out.println("function nodrop6() {");
						out.println("document.all.menu6.style.display = 'block';");
					  out.println("}");
						
						out.println("function nodrop6_2() {");
						out.println("document.all.menu6_2.style.display = 'block';");
					  out.println("}");
						
						out.println("function nodrop6_3() {");
						out.println("document.all.menu6_3.style.display = 'block';");
					  out.println("}");
						
						out.println("function nodrop6_4() {");
						out.println("document.all.menu6_4.style.display = 'block';");
					  out.println("}");
						
						out.println("function nodrop6_5() {");
						out.println("document.all.menu6_5.style.display = 'block';");
					  out.println("}");
						
						out.println("function nodrop6_6() {");
						out.println("document.all.menu6_6.style.display = 'block';");
					  out.println("}");



						
						
						out.println(" </script>");
						out.println("</HEAD>");
						out.println("<BODY bgcolor='#FFFFFF' link='#000000' vlink='#000000' > ");
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
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Marketing_new#Marketing_header' STYLE='{text-decoration:none;}'>Marketing</a></SPAN><BR>");
						out.println("<span ID='menuintro'  >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Marketing_new#Inquiry' STYLE='{text-decoration:none;}'>Inquiry </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Marketing_new#Pricing' STYLE='{text-decoration:none;}'>Pricing </a><BR>");						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Marketing_new#Quotation' STYLE='{text-decoration:none;}'>Quotation </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Marketing_new#Quotation_Approval' STYLE='{text-decoration:none;}'>Quotation Approval </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Marketing_new#Application_Process' STYLE='{text-decoration:none;}'>Application Entry </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Marketing_new#Application_Status_Rep' STYLE='{text-decoration:none;}'>Application Status Report </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Marketing_new#Change_Proforma' STYLE='{text-decoration:none;}'>Change Pro-forma Invoice </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Marketing_new#Client_comments' STYLE='{text-decoration:none;}'>Client Comments</a><BR>");
						out.println("</span>");
						out.println("</TD>");
						out.println("</TR>");
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input  >");
						out.println("<SPAN onClick='dual6();' > ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Credit_header' STYLE='{text-decoration:none;}'>Credit</A></SPAN><BR>");
						out.println("<SPAN ID='menu6' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Credit_Verification' STYLE='{text-decoration:none;}'>Credit Verification</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Credit_Score_Evaluation' STYLE='{text-decoration:none;}'>Credit Score Evaluation</A><BR>");
						
						//menu level 2 
						out.println("<SPAN onClick='dual6_2();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Credit_Approval' STYLE='{text-decoration:none;}'>Credit Approval</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_2' >");
		
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Credit_Approval' STYLE='{text-decoration:none;}'>First Approval</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Credit_Approval2' STYLE='{text-decoration:none;}'>Second Approval</a><BR>");	
						out.println("</SPAN>");
						// end menu level 2
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Initial_Data_specification' STYLE='{text-decoration:none;}'>Initiation Data Specification</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Stipu_loss_valEntry' STYLE='{text-decoration:none;}'>Stipulated Loss Value Entry</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Document_generation' STYLE='{text-decoration:none;}'>Document Generation</a><BR>");
						//menu level 2
						out.println("<SPAN onClick='dual6_3();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Purchase_Order_Gen' STYLE='{text-decoration:none;}'>Purchase Orders</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_3' >");

						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Purchase_Order_Gen' STYLE='{text-decoration:none;}'>Generation</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Purchase_Order_Approval' STYLE='{text-decoration:none;}'>Approval</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Purchase_ord_Printing' STYLE='{text-decoration:none;}'>Printing</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Purchase_ord_Deletion' STYLE='{text-decoration:none;}'>Deletion</a><BR>");
						out.println("</SPAN>");
						//end menu level2
						//menu level 2
						out.println("<SPAN onClick='dual6_4();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Payment_Requisitions_Generation' STYLE='{text-decoration:none;}'>Payment Requisitions</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_4' >");

						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Payment_Requisitions_Generation' STYLE='{text-decoration:none;}'>Generation (Leasing)</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Payment_Requisitions_Generation_loans' STYLE='{text-decoration:none;}'>Generation (Loans)</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Payment_Requisitions_Special_Approval' STYLE='{text-decoration:none;}'>Special Approval </a><BR>");
						out.println("</SPAN>");
						//end menu level2
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Additional_Approval_Remarks' STYLE='{text-decoration:none;}'>Additional Approval Remarks </a><BR>");
						//menu level 2
						
						out.println("<SPAN onClick='dual6_6();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#CRIB_Letter_Generation' STYLE='{text-decoration:none;}'>CRIB</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_6' >");

						
						
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#CRIB_Letter_Generation' STYLE='{text-decoration:none;}'>CRIB - Letter Generation </a><BR>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#CRIB_Data_File_Generation' STYLE='{text-decoration:none;}'>CRIB - Data File Generation</a><BR>");
						out.println("</SPAN>");
						//end menu level 2
						//menu  level 2
						
						out.println("<SPAN onClick='dual6_5();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Deletion_Letter' STYLE='{text-decoration:none;}'>Maintenance</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_5' >");

						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Deletion_Letter' STYLE='{text-decoration:none;}'>Deletion Letter</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Change_Activated_Date' STYLE='{text-decoration:none;}'>Change Activated Date</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Change_Future_Rental_Due_Dates' STYLE='{text-decoration:none;}'>Change Future Rental Due Dates</a><BR>");	
					
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Change_Rental_Amount' STYLE='{text-decoration:none;}'>Change Rental Amount</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Change_Insurance_Date' STYLE='{text-decoration:none;}'>Change Insurance Date</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Facility_Cancellation_after_Purchase_Order' STYLE='{text-decoration:none;}'>Facility Cancellation after Purchase Order</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Add_Guarantor' STYLE='{text-decoration:none;}'>Add Guarantor</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Delete_Guarantor' STYLE='{text-decoration:none;}'>Delete Guarantor</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Standing_Order_Entry' STYLE='{text-decoration:none;}'>Standing Order Entry</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Credit_new#Maintenance_Standing_Order_Approval' STYLE='{text-decoration:none;}'>Standing Order Approval</a><BR>");	
						out.println("</SPAN>");
						
					
						// end menu  level 2
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dual1();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Finance_head' STYLE='{text-decoration:none;}'>Finance</a></SPAN><BR>");
						out.println("<SPAN ID='menu1' >");
						//menu level 2
						out.println("<SPAN onClick='dual1_2();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Payment_Requisitions_Processing' STYLE='{text-decoration:none;}'>Payment Requisitions</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_2' >");
			
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Payment_Requisitions_Processing' STYLE='{text-decoration:none;}'>Processing</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Payment_Requisition_Special_Approval' STYLE='{text-decoration:none;}'>Special Approval</a><BR>");
						out.println("</SPAN>");
						//menu level 2
						
						//menu level 2
						out.println("<SPAN onClick='dual1_3();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Payment_Approval1' STYLE='{text-decoration:none;}'>Payment Approval</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_3' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Payment_Approval1' STYLE='{text-decoration:none;}'>First Approval</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Payment_Approval2' STYLE='{text-decoration:none;}'>Second Approval</a><br>");
						out.println("</SPAN>");
						//menu level 2
						out.println("<SPAN onClick='dual1_4();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Other_Payment_generation' STYLE='{text-decoration:none;}'>Other Payments</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_4' >");

						
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Other_Payment_generation' target='help-right' STYLE='{text-decoration:none;}'>Generation</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Other_Payments_Processing' target='help-right' STYLE='{text-decoration:none;}'>Processing</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Other_Payments_Authorization' target='help-right' STYLE='{text-decoration:none;}'>Authorization</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Other_Payments_Account_Selection' target='help-right' STYLE='{text-decoration:none;}'>Account Selection</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Other_Payments_First_Approval' target='help-right' STYLE='{text-decoration:none;}'>First Approval</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Other_Payments_Second_Approval' target='help-right' STYLE='{text-decoration:none;}'>Second Approval</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Other_Payments_View_Vouchers' target='help-right' STYLE='{text-decoration:none;}'>View Vouchers</a><br>");
						
						out.println("</SPAN>");
						
						out.println("<SPAN onClick='dual1_5();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Cheque_Printing' STYLE='{text-decoration:none;}'>Cheque</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_5' >");

						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Cheque_Printing' target='help-right' STYLE='{text-decoration:none;}'>Printing</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Cheque_Disbursement' target='help-right' STYLE='{text-decoration:none;}'>Disbursement</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Cheque_Cancellation' target='help-right' STYLE='{text-decoration:none;}'>Cancellation</a><br>");
						out.println("</SPAN>");
						
						out.println("<SPAN onClick='dual1_6();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Termination_Termination_Letter' STYLE='{text-decoration:none;}'>Termination</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_6' >");

						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Termination_Termination_Letter' target='help-right' STYLE='{text-decoration:none;}'>Termination Letter</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Termination_First_Termination_Approval' target='help-right' STYLE='{text-decoration:none;}'>First Termination Approval</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Termination_Second_Termination_Approval' target='help-right' STYLE='{text-decoration:none;}'>Second Termination Approval</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Termination_Termination_Calculation' target='help-right' STYLE='{text-decoration:none;}'>Termination Calculation</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Termination_Allocation_of_Receipts' target='help-right' STYLE='{text-decoration:none;}'>Allocation of Receipts</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Termination_Termination_Processing' target='help-right' STYLE='{text-decoration:none;}'>Termination Processing</a><br>");
						
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Legal_Termination_Processing' target='help-right' STYLE='{text-decoration:none;}'>Legal Termination Processing</a><br>");
						//Termination - Legal Termination First Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Termination_Legal_Termination_First_Approval' target='help-right' STYLE='{text-decoration:none;}'>Legal Termination First Approval</a><br>");
						//Termination - Legal Termination Second Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Termination_Legal_Termination_Second_Approval' target='help-right' STYLE='{text-decoration:none;}'>Legal Termination Second Approval</a><br>");
						//Legal Termination Confirmation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Legal_Termination_Confirmation' target='help-right' STYLE='{text-decoration:none;}'>Legal Termination Confirmation</a><br>");
						out.println("</SPAN>");
						
						//menu level 2
						out.println("<SPAN onClick='dual1_7();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Security_File_Movement_Entry' STYLE='{text-decoration:none;}'>Security File</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_7' >");

						
						//Security File - Movement Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Security_File_Movement_Entry' target='help-right' STYLE='{text-decoration:none;}'>Movement Entry</a><br>");
						//Security File - Movement Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Security_File_Movement_Approval' target='help-right' STYLE='{text-decoration:none;}'>Movement Approval</a><br>");
						out.println("</SPAN>");
						//menu level 2
						out.println("<SPAN onClick='dual1_8();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Invoicing_Invoice_Generation' STYLE='{text-decoration:none;}'>Invoicing</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_8' >");
						//Invoicing - Invoice Generation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Invoicing_Invoice_Generation' target='help-right' STYLE='{text-decoration:none;}'>Invoice Generation</a><br>");
						//Invoicing - Invoice Adjustments
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Invoicing_Invoice_Adjustments' target='help-right' STYLE='{text-decoration:none;}'>Invoice Adjustments</a><br>");
						
						//Invoicing - Invoice Printing
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Invoicing_Invoice_Printing' target='help-right' STYLE='{text-decoration:none;}'>Invoice Printing</a><br>");
						//Invoicing - Invoice Reversal
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Invoicing_Invoice_Reversal' target='help-right' STYLE='{text-decoration:none;}'>Invoice Reversal</a><br>");
						out.println("</SPAN>");
						
						
						
						//Finance Activation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Finance_Activation' target='help-right' STYLE='{text-decoration:none;}'>Finance Activation</a><br>");
						
						//Payment Deletion
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Payment_Deletion' target='help-right' STYLE='{text-decoration:none;}'>Payment Deletion</a><br>");
						//Payment Clearing
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Payment_Clearing' target='help-right' STYLE='{text-decoration:none;}'>Payment Clearing</a><br>");
						//Capital Allowance Rate adjustment
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Capital_Allowance_Rate_adjustment' target='help-right' STYLE='{text-decoration:none;}'>Capital Allowance Rate Adjustment</a><br>");
						//Sales Price Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Sales_Price_Entry' target='help-right' STYLE='{text-decoration:none;}'>Sales Price Entry</a><br>");
						
						
						out.println("<SPAN onClick='dual1_9();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Over_Drafts_Adjustments' STYLE='{text-decoration:none;}'>Over Due Interest</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_9' >");

						
						
						//Over Drafts - Adjustments
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Over_Drafts_Adjustments' target='help-right' STYLE='{text-decoration:none;}'>Adjustments</a><br>");
						//Over Drafts - Adjustments First Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Over_Drafts_Adjustments_First_Approval' target='help-right' STYLE='{text-decoration:none;}'>Adjustments First Approval</a><br>");
						//Over Drafts - Adjustments Second Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Over_Drafts_Adjustments_Second_Approval' target='help-right' STYLE='{text-decoration:none;}'>Adjustments Second Approval</a><br>");
						out.println("</SPAN>");
						
						
						out.println("<SPAN onClick='dual1_10();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#DR/CR_Notes_Debit_Note' STYLE='{text-decoration:none;}'>DR/CR Notes </a></SPAN><BR>");
						out.println("<SPAN ID='menu1_10' >");

						
						
						//DR/CR Notes - Debit Note
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#DR/CR_Notes_Debit_Note' target='help-right' STYLE='{text-decoration:none;}'>Debit Note</a><br>");
						
						//DR/CR Notes - Debit Note Cancellation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#DR/CR_Notes_Debit_Note_Cancellation' target='help-right' STYLE='{text-decoration:none;}'>Debit Note Cancellation</a><br>");
						//DR/CR Notes - Credit Note Approval/Cancellation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#DR/CR_Notes_Credit_Note_Approval_Cancellation' target='help-right' STYLE='{text-decoration:none;}'>Credit Note Approval/Cancellation</a><br>");
						out.println("</SPAN>");
						//Change Payee
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Change_Payee' target='help-right' STYLE='{text-decoration:none;}'>Change Payee</a><br>");
						//Income Suspense & Provisioning
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Income_Suspense_Provisioning' target='help-right' STYLE='{text-decoration:none;}'>Income Suspense & Provisioning</a><br>");
						//Legal Provisioning
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_new#Legal_Provisioning' target='help-right' STYLE='{text-decoration:none;}'>Legal Provisioning</a><br>");
						
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#Invoice_Adjustments' target='help-right' STYLE='{text-decoration:none;}'>Invoice Adjustments</a><br>");
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#Termination_Cal' target='help-right' STYLE='{text-decoration:none;}'>Termination-Calculation</a><br>");
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#Termination_Allo_Receipts' target='help-right' STYLE='{text-decoration:none;}'>Termination-Allocation of Receipts</a><br>");
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#Termination_Processing' target='help-right' STYLE='{text-decoration:none;}'>Termination-Processing</a><br>");
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#OD_Interest' target='help-right' STYLE='{text-decoration:none;}'>OD Interest</a><br>");
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#Finance_Activation' target='help-right' STYLE='{text-decoration:none;}'>Finance Activation</a><br>");
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#Change_Cap_Allow' target='help-right' STYLE='{text-decoration:none;}'>Change Capital Allowance Rates</a><br>");
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#Security_File_Movement' target='help-right' STYLE='{text-decoration:none;}'>Security File Movement Entry</a><br>");
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#Security_File_Movement_Approval' target='help-right' STYLE='{text-decoration:none;}'>Security File Movement Approval</a><br>");
						//out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Finance_nw#Day_End_Routine' target='help-right' STYLE='{text-decoration:none;}'>Day End Routine</a><br>");
						
						out.println("</SPAN>");
						out.println("</TD>");
						out.println("</TR>");
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dual2();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_BulkPrinting_new#Bulkprinting_header' STYLE='{text-decoration:none;}'>Bulk Printing</a></SPAN><BR>");
						out.println("<SPAN ID='menu2' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_BulkPrinting_new#Bulk_Printing_Invoices' STYLE='{text-decoration:none;}'>Bulk Printing Invoices</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<A target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_BulkPrinting_new#Bulk_Printing_Monthly_Statements' STYLE='{text-decoration:none;}'>Bulk Printing Monthly Statements</A><BR>	");					
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<A target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_BulkPrinting_new#Bulk_Printing_PDC' STYLE='{text-decoration:none;}'>Bulk Printing PDC</A><BR>	");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<A target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_BulkPrinting_new#Bulk_Printing_Insurance ' STYLE='{text-decoration:none;}'>Bulk Printing Insurance</A><BR>	");					
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");

						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");						
						out.println("<SPAN onClick='dual3();'>");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Collection_header' STYLE='{text-decoration:none;}'>Collection</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menu3' >");
						
						
						//2nd level menu
						out.println("<SPAN onClick='dual3_2();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Temporary_Receipts_Entry' STYLE='{text-decoration:none;}'>Temporary Receipts</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menu3_2' >");

						//Temporary Receipts - Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Temporary_Receipts_Entry' STYLE='{text-decoration:none;}'>Entry</a><BR>");
						//Temporary Receipts - Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Temporary_Receipts_Approval' STYLE='{text-decoration:none;}'>Approval</a><BR>");
						
						
						
						out.println("</SPAN>");
						
						//menu lvl2
						out.println("<SPAN onClick='dual3_3();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Receipts_Entry' STYLE='{text-decoration:none;}'>Receipts</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menu3_3' >");

						//Receipts - Entry
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Receipts_Entry' STYLE='{text-decoration:none;}'>Entry</a><BR>");
						//Receipts - Invoice Allocation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Receipts_Invoice_Allocation' STYLE='{text-decoration:none;}'>Invoice Allocation</a><BR>");
						//Receipts - Balance Allocation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Receipts_Balance_Allocation' STYLE='{text-decoration:none;}'>Balance Allocation</a><BR>");
						//Receipts - Deposit
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Receipts_Deposit' STYLE='{text-decoration:none;}'>Deposit</a><BR>");
						
						
						
						
						//Printing - Receipts
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Printing_Receipts' STYLE='{text-decoration:none;}'>Receipts</a><BR>");
						//Receipts - Deposit Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Receipts_Deposit_Approval' STYLE='{text-decoration:none;}'>Deposit Approval</a><BR>");
						//Receipts - Return & Realization
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Receipts_Return_Realization' STYLE='{text-decoration:none;}'>Return & Realization</a><BR>");
						//Receipts - Cancellation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Receipts_Cancellation' STYLE='{text-decoration:none;}'>Cancellation</a><BR>");
						
						out.println("</SPAN>");
						//end menu level2
						
						//menu 2nlevelGrouped Receipts
						
						out.println("<SPAN onClick='dual3_4();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Grouped_Receipts_Setup' STYLE='{text-decoration:none;}'>Grouped Receipts</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menu3_4' >");

						//Grouped Receipts - Setup 
						
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Grouped_Receipts_Setup' STYLE='{text-decoration:none;}'>Setup</a><BR>");
						//Grouped Receipts - Entry 
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Grouped_Receipts_Entry' STYLE='{text-decoration:none;}'>Entry</a><BR>");
						out.println("</SPAN>");
						//end menu level2 
						
						out.println("<SPAN onClick='dual3_5();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Post_Dated_Cheques_Entry' STYLE='{text-decoration:none;}'>Post Dated Cheques</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menu3_5' >");

						
						
						//Post Dated Cheques - Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Post_Dated_Cheques_Entry' STYLE='{text-decoration:none;}'>Entry</a><BR>");
						//Post Dated Cheques - Receipt Generation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Post_Dated_Cheques_Receipt_Generation' STYLE='{text-decoration:none;}'>Receipt Generation</a><BR>");
						//Post Dated Cheques - Withdrawal
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Post_Dated_Cheques_Withdrawal' STYLE='{text-decoration:none;}'>Withdrawal</a><BR>");
						out.println("</SPAN>");
						//end menu level2
						
						
						//menu level 2
						out.println("<SPAN onClick='dual3_6();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Order_Generation' STYLE='{text-decoration:none;}'>Repossession</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menu3_6' >");

						
						//Repossession - Order Generation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Order_Generation' STYLE='{text-decoration:none;}'>Order Generation</a><BR>");
						//Repossession - Vehicle Inventory - Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Vehicle_Inventory_Entry' STYLE='{text-decoration:none;}'>Vehicle Inventory - Entry</a><BR>");
						//Repossession - Vehicle Inventory - Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Vehicle_Inventory_Approval' STYLE='{text-decoration:none;}'>Vehicle Inventory - Approval</a><BR>");
						//Repossession - Valuation for Vehicle in Yard
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Valuation_for_Vehicle_in_Yard' STYLE='{text-decoration:none;}'>Valuation for Vehicle in Yard</a><BR>");
						//Repossession - Recovery of Financed Amount
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Recovery_of_Financed_Amount' STYLE='{text-decoration:none;}'>Recovery of Financed Amount</a><BR>");
						//Repossession - Advertisement Generation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Advertisement_Generation' STYLE='{text-decoration:none;}'>Advertisement Generation</a><BR>");
						//Repossession - Entry of Offers
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Entry_of_Offers' STYLE='{text-decoration:none;}'>Entry of Offers</a><BR>");
						//Repossession - Acceptance of Offers
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Acceptance_of_Offers' STYLE='{text-decoration:none;}'>Acceptance of Offers</a><BR>");
						//Repossession - Reverse Repossession
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Repossession_Reverse_Repossession' STYLE='{text-decoration:none;}'>Reverse Repossession</a><BR>");
						
						out.println("</SPAN>");
						//end menu level2
						
						// Added by Thamali Jayatunga on 2009.12.04
						//menu level2
						out.println("<SPAN onClick='dualLett_doc_2();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Legal_Actions_Initiation' STYLE='{text-decoration:none;}'>Legal Actions</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menuLetDoc_2' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Legal_Actions_Initiation' STYLE='{text-decoration:none;}'>Initiation</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Legal_Actions_Update' STYLE='{text-decoration:none;}'>Update</A><BR>");
						out.println("</SPAN>");
						// Endded modification by Thamali Jayatunga on 2009.12.04
						
						//menu level 2
						out.println("<SPAN onClick='dual3_7();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Maintenance_Application_Status_Change_Entry' STYLE='{text-decoration:none;}'>Maintenance</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menu3_7' >");

						
						//Maintenance - Application Status Change - Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Maintenance_Application_Status_Change_Entry' STYLE='{text-decoration:none;}'>Application Status Change - Entry</a><BR>");
						//Maintenance - Application Status Change - Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Maintenance_Application_Status_Change_Approval' STYLE='{text-decoration:none;}'>Application Status Change - Approval</a><BR>");
						//Maintenance - Assign Collection Officer
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Maintenance_Assign_Collection_Officer' STYLE='{text-decoration:none;}'>Assign Collection Officer</a><BR>");
						//Maintenance - Collection Officer Target Setup
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Maintenance_Collection_Officer_Target_Setup' STYLE='{text-decoration:none;}'>Collection Officer Target Setup</a><BR>");
						//Maintenance - Collection Reminders
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Maintenance_Collection_Reminders' STYLE='{text-decoration:none;}'>Collection Reminders</a><BR>");
						//Maintenance - Assign Insurance Officer
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Collection_new#Maintenance_Assign_Insurance_Officer' STYLE='{text-decoration:none;}'>Assign Insurance Officer</a><BR>");
						
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						//add by madhawa 2009-11-24
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dualLett_doc();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#LetDocHead' STYLE='{text-decoration:none;}'>Letters & Documents</a></SPAN><BR>");
						out.println("<SPAN ID='menuLetDoc' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Monthly_Statement' STYLE='{text-decoration:none;}'>Monthly Statement</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Cheque_Return_Letter' STYLE='{text-decoration:none;}'>Cheque Return Letter</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Termination_Letter' STYLE='{text-decoration:none;}'>Termination Letter</A><BR>");
						//menu level2
						
						out.println("<SPAN onClick='dualLett_doc_1();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Thanking_Letters_For_Client' STYLE='{text-decoration:none;}'>Thanking Letters</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menuLetDoc_1' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Thanking_Letters_For_Client' STYLE='{text-decoration:none;}'>For Client</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Thanking_Letters_For_Introducer' STYLE='{text-decoration:none;}'>For Introducer</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Thanking_Letters_For_Guarantor' STYLE='{text-decoration:none;}'>For Guarantor</A><BR>");
						out.println("</SPAN>");
						
						// Commented by Thamali Jayatunga on 2009.12.04
						/* 
						//menu level2
						out.println("<SPAN onClick='dualLett_doc_2();'>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Legal_Actions_Initiation' STYLE='{text-decoration:none;}'>Legal Actions</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menuLetDoc_2' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Legal_Actions_Initiation' STYLE='{text-decoration:none;}'>Initiation</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_Letters_Documt_new#Legal_Actions_Update' STYLE='{text-decoration:none;}'>Update</A><BR>");
						out.println("</SPAN>");
						*/
						// Endded Comment by Thamali Jayatunga on 2009.12.04
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						//end add by madhawa 2009-11-24

						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dual3im();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_FollowUp_new#FollowupHeader' STYLE='{text-decoration:none;}'>Follow Up</a></SPAN><BR>");
						out.println("<SPAN ID='menu3im' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_FollowUp_new#Entry' STYLE='{text-decoration:none;}'>Follow Up Entry</a><BR>");
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dualMaintenance();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Maintenance_new#MaintenanceHeader' STYLE='{text-decoration:none;}'>Maintenance</a></SPAN><BR>");
						out.println("<SPAN ID='Maintenancemnu' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Maintenance_new#Day_End_Routine' STYLE='{text-decoration:none;}'>Day End Routine</a><BR>");
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
