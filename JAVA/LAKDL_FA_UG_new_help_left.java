

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL on 24-05-2007

public class LAKDL_FA_UG_new_help_left extends javax.servlet.http.HttpServlet { 

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
						out.println("	#menu1_1 { display : none } ");
						out.println("	#menu1_2 { display : none } ");
						out.println("	#menu1_3 { display : none } ");
						out.println("	#menu1_4 { display : none } ");
						out.println("	#menu1_5 { display : none } ");
						out.println("	#menu1_6 { display : none } ");
						out.println("	#menu1_7 { display : none } ");
						out.println("	#menu1r { display : none } ");
						
						out.println("	#menu2_2 { display : none } ");
						
						out.println("	#menu3_2 { display : none } ");
						out.println("	#menuLetDoc { display : none } ");
						out.println("	#Maintenancemnu { display : none } ");
						out.println("	#menufinance { display : none } ");
						
						out.println("	#menuinq { display : none } ");
						
						out.println("	#mnuFollowup { display : none } ");
						
						out.println("	#mnuFactoring { display : none } ");
						
						out.println("	#menu2 { display : none } ");
						out.println("	#menu3 { display : none } ");
						out.println("	#menu4 { display : none } ");
						out.println("	#menu5 { display : none } ");
						out.println("	#menu6 { display : none } ");		
						out.println("	#menu6_1 { display : none } ");	
						out.println("	#menu6_2 { display : none } ");
						out.println("	#menu6_3 { display : none } ");
						out.println("	#menu6_4 { display : none } ");
						out.println("	#menu6_5 { display : none } ");
						
						out.println("	#menu3v { display : none } ");
						out.println("	#menu3im { display : none } ");
						out.println("	#menu3sm { display : none } ");						
						out.println(" #menu3ism { display : none } ");
						out.println("	#menu3cd { display : none }");
						out.println("	#menu3sysm { display : none }");
						out.println("	#menu3r { display : none }");
						out.println("	#menu3cb { display : none }");
						
						out.println("	#menuintro { display : none }");
						out.println("	#menuintro_1 { display : none }");
						out.println("	#menuintro_2 { display : none }");
						out.println("	#menuintro_3 { display : none }");
						out.println("	#menuintro_4 { display : none }");
						out.println("	#menuintro_5 { display : none }");
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
						
						out.println("function dualintro_1()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodropintro_1();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else ");
						out.println(" if (plus == false){");
						out.println(" dropintro_1();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");		
						
						out.println("function dualintro_2()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodropintro_2();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else ");
						out.println(" if (plus == false){");
						out.println(" dropintro_2();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");	
						
						out.println("function dualintro_3()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodropintro_3();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else ");
						out.println(" if (plus == false){");
						out.println(" dropintro_3();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");		
						
						out.println("function dualintro_4()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodropintro_4();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else ");
						out.println(" if (plus == false){");
						out.println(" dropintro_4();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");		
						
						out.println("function dualintro_5()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodropintro_5();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else ");
						out.println(" if (plus == false){");
						out.println(" dropintro_5();");
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
						
						out.println("function dual1_1()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop1_1();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop1_1();");
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
						
						
						out.println("function dual2_2()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop2_2();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop2_2();");
						out.println(" plus = true;");
						out.println(" } ");
						out.println("}");
						
						
						out.println("function dualinq()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodropinq();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" dropinq();");
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
						
						out.println("function dualmnufinac()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodropmnufinac();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	dropmnufinac();");
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
						
						
						out.println("function dualFollowup()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodropFollowup();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	dropFollowup();");
						out.println("	plus = true;");
						out.println("}");
						out.println("}");
						
						
						out.println("function dualFactoring()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("	nodropFactoring();");
						out.println("	plus = false;");
						out.println("}");
						out.println("else if (plus == false){");
						out.println("	dropFactoring();");
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
						
						out.println("function dual6_1()");
						out.println("{");
						out.println("if (plus == true){");
						out.println("nodrop6_1();");
						out.println("plus = false;");
						out.println("} ");
						out.println("else");
						out.println(" if (plus == false){");
						out.println(" drop6_1();");
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

						
						
						out.println("function dropintro() {");
						out.println("document.all.menuintro.style.display = 'none';");
						out.println("}");
						out.println("function dropintro_1() {");
						out.println("document.all.menuintro_1.style.display = 'none';");
						out.println("}");
						out.println("function dropintro_2() {");
						out.println("document.all.menuintro_2.style.display = 'none';");
						out.println("}");
						out.println("function dropintro_3() {");
						out.println("document.all.menuintro_3.style.display = 'none';");
						out.println("}");
						
						out.println("function dropintro_4() {");
						out.println("document.all.menuintro_4.style.display = 'none';");
						out.println("}");
						
						out.println("function dropintro_5() {");
						out.println("document.all.menuintro_5.style.display = 'none';");
						out.println("}");





						
						out.println("function nodropintro() {");
						out.println("document.all.menuintro.style.display = 'block';");
						out.println("}");
						
						out.println("function nodropintro_1() {");
						out.println("document.all.menuintro_1.style.display = 'block';");
						out.println("}");
						
						out.println("function nodropintro_2() {");
						out.println("document.all.menuintro_2.style.display = 'block';");
						out.println("}");
						
						out.println("function nodropintro_3() {");
						out.println("document.all.menuintro_3.style.display = 'block';");
						out.println("}");
						
						out.println("function nodropintro_4() {");
						out.println("document.all.menuintro_4.style.display = 'block';");
						out.println("}");
						
						out.println("function nodropintro_5() {");
						out.println("document.all.menuintro_5.style.display = 'block';");
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
						out.println("function drop1_1() {");
						out.println("document.all.menu1_1.style.display = 'none';");
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







						out.println("function nodrop1() {");
						out.println("document.all.menu1.style.display = 'block';");
						out.println("}");
						out.println("function nodrop1_1() {");
						out.println("document.all.menu1_1.style.display = 'block';");
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






						out.println("function drop2() {");
						out.println("document.all.menu2.style.display = 'none';");
						out.println("}");
						
						out.println("function drop2_2() {");
						out.println("document.all.menu2_2.style.display = 'none';");
						out.println("}");

						
						out.println("function dropinq() {");
						out.println("document.all.menuinq.style.display = 'none';");
						out.println("}");

						
						out.println("function nodrop2() {");
						out.println("document.all.menu2.style.display = 'block';");
						out.println("}");
						
						out.println("function nodrop2_2() {");
						out.println("document.all.menu2_2.style.display = 'block';");
						out.println("}");

						out.println("function nodropinq() {");
						out.println("document.all.menuinq.style.display = 'block';");
						out.println("}");

						out.println("function drop3() {");
						out.println("document.all.menu3.style.display = 'none';");
						out.println("}");
						
						out.println("function drop3_2() {");
						out.println("document.all.menu3_2.style.display = 'none';");
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
						
						//2nd level menu
						out.println("function nodrop3_2() {");
						out.println("document.all.menu3_2.style.display = 'block';");
						out.println("}");

						
						//end 2nd level menu
						
						out.println("function nodrop3v(){");
						out.println("	document.all.menu3v.style.display = 'none';");
						out.println("}");
						out.println("function drop3v(){");
						out.println("	document.all.menu3v.style.display = 'block';");
						out.println("}");
						out.println("function nodrop3im(){");
						out.println("	document.all.menu3im.style.display = 'none';");
						out.println("}");
						
						
						out.println("function nodropmnufinac(){");
						out.println("	document.all.menufinance.style.display = 'none';");
						out.println("}");

						
						out.println("function nodropMaintenanmnu(){");
						out.println("	document.all.Maintenancemnu.style.display = 'none';");
						out.println("}");
							
						out.println("function nodropFollowup(){");
						out.println("	document.all.mnuFollowup.style.display = 'none';");
						out.println("}");
						
						out.println("function nodropFactoring(){");
						out.println("	document.all.mnuFactoring.style.display = 'none';");
						out.println("}");


						
						out.println("function nodropLetDoc(){");
						out.println("	document.all.menuLetDoc.style.display = 'none';");
						out.println("}");

						
						out.println("function drop3im(){");
						out.println("	document.all.menu3im.style.display = 'block';");
						out.println("}");
						
						
						out.println("function dropmnufinac(){");
						out.println("	document.all.menufinance.style.display = 'block';");
						out.println("}");

						
						out.println("function dropMaintainacemnu(){");
						out.println("	document.all.Maintenancemnu.style.display = 'block';");
						out.println("}");
						
						
						out.println("function dropFollowup(){");
						out.println("	document.all.mnuFollowup.style.display = 'block';");
						out.println("}");
						
						
						out.println("function dropFactoring(){");
						out.println("	document.all.mnuFactoring.style.display = 'block';");
						out.println("}");

						
						out.println("function dropLetDoc(){");
						out.println("	document.all.menuLetDoc.style.display = 'block';");
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
						
						out.println("function drop6_1() {");
						out.println("document.all.menu6_1.style.display = 'none';");
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






						out.println("function nodrop6() {");
						out.println("document.all.menu6.style.display = 'block';");
					  out.println("}");
						
						out.println("function nodrop6_1() {");
						out.println("document.all.menu6_1.style.display = 'block';");
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
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing_new#Marketing' STYLE='{text-decoration:none;}'>Marketing</a></SPAN><BR>");
						out.println("<span ID='menuintro'  >");
						
												
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing_new#Inquiry' STYLE='{text-decoration:none;}'>Inquiry</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing_new#Client_Information_Collection' STYLE='{text-decoration:none;}'>Client Information Collection </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing_new#Initial_Credit_Verification' STYLE='{text-decoration:none;}'>Initial Credit Verification</a><BR>");												
						
						
						//menu level2
						out.println("<span onclick='dualintro_2()';>");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing_new#Quotation' STYLE='{text-decoration:none;}'>Quotation</a></SPAN><BR>");
						out.println("<span ID='menuintro_2'  >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing_new#Quotation' STYLE='{text-decoration:none;}'>Quotation </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing_new#Quotation_Approval' STYLE='{text-decoration:none;}'>Quotation Approval</a><BR>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Marketing_new#Quotation_Print' STYLE='{text-decoration:none;}'>Quotation Print</a><BR>");
						out.println("</span>");
						//end menu level 2
												
												
						out.println("</span>");
						out.println("</TD>");
						out.println("</TR>");
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input  >");
						out.println("<SPAN onClick='dual6();' > ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit' STYLE='{text-decoration:none;}'>Credit</A></SPAN><BR>");
						out.println("<SPAN ID='menu6' >");
						
						//menu level2
						out.println("<SPAN onClick='dual6_1();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Client/Debtor_Creation' STYLE='{text-decoration:none;}'>Client/Debtor</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_1' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Client/Debtor_Creation' STYLE='{text-decoration:none;}'>Creation</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Client/Debtor_Creation_Approval' STYLE='{text-decoration:none;}'>Creation Approval</A><BR>");
							
						out.println("</SPAN>");
						//end menu level2
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Create_Client_Facility' STYLE='{text-decoration:none;}'>Create Client Facility</A><BR>");


//---------------------------------------------Credit_Evaluation
out.println("<SPAN onClick='dual6_2();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Evaluation_Entry' STYLE='{text-decoration:none;}'>Credit Evaluation </A></SPAN><BR>");
						out.println("<SPAN ID='menu6_2' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Evaluation_Entry  ' STYLE='{text-decoration:none;}'>Entry </a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Evaluation_Evaluation ' STYLE='{text-decoration:none;}'>Evaluation</a><BR>");	

                        out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Evaluation_Approval ' STYLE='{text-decoration:none;}'>Approval</a><BR>");	

                         
						/* out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Evaluation_Evaluation ' STYLE='{text-decoration:none;}'>Credit Evaluation ? Evaluation</a><BR>");	
                         out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Evaluation_Approval ' STYLE='{text-decoration:none;}'>Credit Evaluation ? Approval</a><BR>");	
*/
						 
						 out.println("</SPAN>");


						
												
						out.println("<SPAN onClick='dual6_3();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Client_Facility_Approval' STYLE='{text-decoration:none;}'>Client Facility</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_3' >");


						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Client_Facility_Approval' STYLE='{text-decoration:none;}'>Approval</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Client_Facility_Confirmation' STYLE='{text-decoration:none;}'>Confirmation</a><BR>");	
												
						out.println("</SPAN>");
						 
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Assign_Client_Debtor' STYLE='{text-decoration:none;}'>Assign Client - Debtor </a><BR>");
							
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Client_Debtor_Document_Update' STYLE='{text-decoration:none;}'>Client - Debtor Document Update</a><BR>");	
                        
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Activate_Client_Debtor' STYLE='{text-decoration:none;}'>Activate Client-Debtor</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Facility_Activation' STYLE='{text-decoration:none;}'>Facility Activation</a><BR>");


						out.println("<SPAN onClick='dual6_4();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Legal_Letters_Agreement_Printing ' STYLE='{text-decoration:none;}'>Legal Letters</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_4' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Legal_Letters_Agreement_Printing' STYLE='{text-decoration:none;}'>Agreement Printing </a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Legal_Letters_Client/Debtor_Assignment_Letters' STYLE='{text-decoration:none;}'>Client/Debtor Assignment Letters</a><BR>");	
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Legal_Letters_Agreement_Enhancement' STYLE='{text-decoration:none;}'>Agreement Enhancement</a><BR>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Legal_Letters_Cancellation_of_Assigned_Letters' STYLE='{text-decoration:none;}'>Cancellation of Assigned Letters</a><BR>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Legal_Letters_to_Clients' STYLE='{text-decoration:none;}'>Legal Letters to Clients</a><BR>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Legal_Letters_to_Debtors' STYLE='{text-decoration:none;}'>Legal Letters to Debtors</a><BR>");
						
						out.println("</SPAN>");
						
						out.println("<SPAN onClick='dual6_5();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Maintenance_Client/Debtor_Maintenance' STYLE='{text-decoration:none;}'>Credit Maintenance</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_5' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Maintenance_Client/Debtor_Maintenance' STYLE='{text-decoration:none;}'>Client/Debtor Maintenance</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Maintenance_Client_Facility_Maintenance' STYLE='{text-decoration:none;}'>Client Facility Maintenance</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Maintenance_Client/Debtor_Relation_Maintenance' STYLE='{text-decoration:none;}'>Client/Debtor Relation Maintenance</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Maintenance_Client/Debtor_Reactivation' STYLE='{text-decoration:none;}'>Client/Debtor Reactivation</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Maintenance_Client/Debtor_Approval_Cheque_Return' STYLE='{text-decoration:none;}'>Client/Debtor Approval - Cheque Return</a><BR>");	
					  out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Maintenance_Debtor_Limit_Adjustments' STYLE='{text-decoration:none;}'>Debtor Limit Adjustments</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Maintenance_Client/Debtor_Relationship_Reactivation' STYLE='{text-decoration:none;}'>Client/Debtor Relationship Reactivation</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Credit_new#Credit_Maintenance_Facility_Extend' STYLE='{text-decoration:none;}'>Facility Extend</a><BR>");	

					


	
						
						out.println("</SPAN>");
						
						
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dual1();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Operations' STYLE='{text-decoration:none;}'>Operations</a></SPAN><BR>");
						out.println("<SPAN ID='menu1' >");
						
						//2nd level menu 
						out.println("<SPAN onClick='dual1_1();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Invoice_Process_Invoice_Entry' STYLE='{text-decoration:none;}'>Invoice Process</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_1' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Invoice_Process_Invoice_Entry' STYLE='{text-decoration:none;}'>Invoice Entry</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Invoice_Process_Invoice_Approval_cash' STYLE='{text-decoration:none;}'>Invoice Approval (Cash)</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Invoice_Process_Invoice_Approval' STYLE='{text-decoration:none;}'>Invoice Approval (Credit)</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Invoice_Process_Invoice_Confirmation' STYLE='{text-decoration:none;}'>Invoice Confirmation</a><br>");
						out.println("</SPAN>");
						
						//end 2nd level menu 
						
						//2nd level menu
						out.println("<SPAN onClick='dual1_2();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Invoice_Adjustments' STYLE='{text-decoration:none;}'>Adjustments - Cr/Dr Notes and Reversals</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_2' >");

						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Invoice_Adjustments' target='help-right' STYLE='{text-decoration:none;}'>Invoice Adjustments</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Invoice_Reassignments' target='help-right' STYLE='{text-decoration:none;}'>Invoice Reassignments</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Client_Adjustments' target='help-right' STYLE='{text-decoration:none;}'>Client Adjustments</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Adjustments_Approval' target='help-right' STYLE='{text-decoration:none;}'>Adjustments - Approval</a><br>");

                        out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Charges_Addition' target='help-right' STYLE='{text-decoration:none;}'>Charges Addition</a><br>");
                         out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Charges_Reversal' target='help-right' STYLE='{text-decoration:none;}'>Charges Reversal</a><br>");

                         out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Non_Sales_and_Excess_Funds_Transfer' target='help-right' STYLE='{text-decoration:none;}'>Non Sales and Excess Funds Transfer</a><br>");

                              out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Cheque_Return_Transfer' target='help-right' STYLE='{text-decoration:none;}'>Cheque Return Transfer</a><br>");
							  out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Adjustments_Cr/Dr_Notes_and_Reversals_Non_Sales_and_Excess_Funds_Transfer_Approval' target='help-right' STYLE='{text-decoration:none;}'>Non Sales and Excess Funds Transfer Approval</a><br>");
				        out.println("</SPAN>");			  
             //end 2nd level menu
			 
			            //2nd level menu
						
						
						out.println("<SPAN onClick='dual1_3();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_PD_Cheque_Entry_and_Allocation' STYLE='{text-decoration:none;}'>P D and Receipt Entry</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_3' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_PD_Cheque_Entry_and_Allocation' target='help-right' STYLE='{text-decoration:none;}'>PD Cheque Entry and Allocation</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_PD_Cheque_Receipt_Generation' target='help-right' STYLE='{text-decoration:none;}'>PD Cheque Receipt Generation</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_Settlement_Receipt_Entry' target='help-right' STYLE='{text-decoration:none;}'>Settlement Receipt Entry</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_Settlement_Receipt_Approval' target='help-right' STYLE='{text-decoration:none;}'>Settlement Receipt Approval</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_Settlement_Deposit' target='help-right' STYLE='{text-decoration:none;}'>Settlement Deposit</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_Return_Realization' target='help-right' STYLE='{text-decoration:none;}'>Return/Realization</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_Cheque_Return_Settlement' target='help-right' STYLE='{text-decoration:none;}'>Cheque Return Settlement</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_Cheque_Re_Banking' target='help-right' STYLE='{text-decoration:none;}'>Cheque Re-Banking</a><br>");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#P_D_and_Receipt_Entry_Invoice_Allocation' target='help-right' STYLE='{text-decoration:none;}'>Invoice Allocation</a><br>");
						out.println("</SPAN>");
						
						
						//end 2nd level menu
						//2nd menu level
						out.println("<SPAN onClick='dual1_4();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Client_Availability' STYLE='{text-decoration:none;}'>Client Payments </a></SPAN><BR>");
						out.println("<SPAN ID='menu1_4' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Client_Availability' target='help-right' STYLE='{text-decoration:none;}'>Client Availability</a><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Client_Payments' target='help-right' STYLE='{text-decoration:none;}'>Client Payments</a><br>");
						
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Client_Payment_Approval' target='help-right' STYLE='{text-decoration:none;}'>Client Payment Approval</a><br>");                     
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Client_Payment_Higher_Approval' target='help-right' STYLE='{text-decoration:none;}'>Client Payment Higher Approval</a><br>"); 
						
						   
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Client_Payment_Confirmation' target='help-right' STYLE='{text-decoration:none;}'>Client Payment Confirmation</a><br>"); 
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Cheque_Print' target='help-right' STYLE='{text-decoration:none;}'>Cheque Print</a><br>"); 
                        
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Cheque_Disbursement' target='help-right' STYLE='{text-decoration:none;}'>Cheque Disbursement</a><br>"); 
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Debtor_Performance' target='help-right' STYLE='{text-decoration:none;}'>Debtor Performance</a><br>"); 
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Client_Payment_Realization' target='help-right' STYLE='{text-decoration:none;}'>Client Payment Realization</a><br>"); 
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Payments_Client_Availability_Performance' target='help-right' STYLE='{text-decoration:none;}'>Client Availability Performance</a><br>"); 


						 
						out.println("</SPAN>");
						
						//end 2nd level menu
						//2nd level menu
						out.println("<SPAN onClick='dual1_5();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Cheque_Return_Reminders_Reminder' STYLE='{text-decoration:none;}'>Cheque Return Reminders</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_5' >");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Cheque_Return_Reminders_Reminder' target='help-right' STYLE='{text-decoration:none;}'>Reminder</a><br>"); 
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Cheque_Return_Reminders_Legal_Letter' target='help-right' STYLE='{text-decoration:none;}'>Legal Letter</a><br>"); 


						
						out.println("</SPAN>");

						
						//end 2nd level menu
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Invoice_Reminders' target='help-right' STYLE='{text-decoration:none;}'>Invoice Reminders</a><br>"); 
						
						//2nd level
						out.println("<SPAN onClick='dual1_6();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Statements_Statement' STYLE='{text-decoration:none;}'>Client Statements</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_6' >");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Statements_Statement' target='help-right' STYLE='{text-decoration:none;}'>Statement</a><br>"); 
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Statements_Generate_Tax_Invoice' target='help-right' STYLE='{text-decoration:none;}'>Generate Tax Invoice</a><br>"); 
            out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Statements_CRIB_Information_Update_Report' target='help-right' STYLE='{text-decoration:none;}'>CRIB Information Update Report</a><br>"); 
						out.println("</SPAN>");
						//end 2nd level
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client_Disputes' target='help-right' STYLE='{text-decoration:none;}'>Client Disputes</a><br>"); 
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Client/Debtor_Credit_Comments' target='help-right' STYLE='{text-decoration:none;}'>Client/Debtor Credit Comments</a><br>"); 

            out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Cancel_Cheques' target='help-right' STYLE='{text-decoration:none;}'>Cancel Cheques</a><br>"); 
						 
						 out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Return_Cheques' target='help-right' STYLE='{text-decoration:none;}'>Return Cheques</a><br>"); 
						 
						 out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Receipt_Bulk_Printing' target='help-right' STYLE='{text-decoration:none;}'>Receipt Bulk Printing</a><br>"); 
						 
						 out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Invoice_Review' target='help-right' STYLE='{text-decoration:none;}'>Invoice Review</a><br>"); 
						 
						 out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Charges_Hold' target='help-right' STYLE='{text-decoration:none;}'>Charges Hold</a><br>");
						  
						 out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_Operations_new#Factoring_Finance_Interest_Rate' target='help-right' STYLE='{text-decoration:none;}'>Factoring Finance Interest Rate</a><br>"); 


						out.println("</SPAN>");
						out.println("</TD>");
						out.println("</TR>");
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dual1_7();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"FA_UG_System_maintainance_new#System_Maintenance' STYLE='{text-decoration:none;}'>System Maintenance</a></SPAN><BR>");
						out.println("<SPAN ID='menu1_7' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_System_maintainance_new#Day_end_Routine' target='help-right' STYLE='{text-decoration:none;}'>Day-end Routine</a><br>"); 
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"FA_UG_System_maintainance_new#Month_end_Routine' target='help-right' STYLE='{text-decoration:none;}'>Month-end Routine</a><br>"); 
						out.println("</SPAN>");
						out.println("</TD>");
						out.println("</TR>");
		
						
						/*
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dual2();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Licensee_Organization_Structure_header' STYLE='{text-decoration:none;}'>Licensee Organization Structure</a></SPAN><BR>");
						out.println("<SPAN ID='menu2' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Divisions' STYLE='{text-decoration:none;}'>Divisions</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Sub_Divisions' STYLE='{text-decoration:none;}'>Sub Divisions</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Locations' STYLE='{text-decoration:none;}'>Locations</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Designations' STYLE='{text-decoration:none;}'>Designations</a><br>");
						//2nd level drop down
						out.println("<SPAN onClick='dual2_2();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Employees_Employee_Entry' STYLE='{text-decoration:none;}'>Employee</a></SPAN><BR>");
						out.println("<SPAN ID='menu2_2' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Employees_Employee_Entry' target='help-right' STYLE='{text-decoration:none;}'>Employee Entry</a><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Employees_Reactivate_Employees' target='help-right' STYLE='{text-decoration:none;}'>Reactivate  Employees</a><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Employees_Approve_Reactivation_of_Employees' target='help-right' STYLE='{text-decoration:none;}'>Approve Reactivation of Employees</a><br>");
						out.println("</SPAN>");
						//2nd level drop down
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Teams' target='help-right' STYLE='{text-decoration:none;}'>Teams</a><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Sub_Teams' target='help-right' STYLE='{text-decoration:none;}'>Sub_Teams</a><br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a HREF='"+m_class_url+"/"+m_fschema_name+"AF_UG_Licensee_Organization_Structure#Assigning_Employees_to_Sub_Teams' target='help-right' STYLE='{text-decoration:none;}'>Assigning Employees to Sub Teams</a><br>");
						
						out.println("</SPAN>");
						out.println("</TD>");
						out.println("</TR>");

						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dualinq();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_inquiry#inquiry_header' STYLE='{text-decoration:none;}'>Inquiry</a></SPAN><BR>");
						out.println("<SPAN ID='menuinq' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_inquiry#Inquiry_Stages' STYLE='{text-decoration:none;}'>Inquiry Stages</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<A target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_inquiry#Customer_Categories' STYLE='{text-decoration:none;}'>Customer Categories</A><BR>	");					
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<A target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_inquiry#Lead_source_Categories' STYLE='{text-decoration:none;}'>Lead source Categories</A><BR>	");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<A target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_inquiry#Lead_Source_Details ' STYLE='{text-decoration:none;}'>Lead Source Details</A><BR>	");					
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<A target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_inquiry#Initiation_Types ' STYLE='{text-decoration:none;}'>Initiation Types</A><BR>	");					
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						

						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");						
						out.println("<SPAN onClick='dual3();'>");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' STYLE='{cursor: hand;}' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Application_Process' STYLE='{text-decoration:none;}'>Application Process</a></SPAN>");
						out.println("<BR>");
						out.println("<SPAN ID='menu3' >");
						
						//Temporary Receipts - Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Transaction_Types' STYLE='{text-decoration:none;}'>Transaction Types</a><BR>");
						//Temporary Receipts - Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Transaction_Sub_Types' STYLE='{text-decoration:none;}'>Transaction Sub Types</a><BR>");
						//Receipts - Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Item_Categories' STYLE='{text-decoration:none;}'>Item Categories</a><BR>");
						//Receipts - Invoice Allocation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Item_Sub_Categories' STYLE='{text-decoration:none;}'>Item Sub Categories</a><BR>");
						//Receipts - Balance Allocation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Makes' STYLE='{text-decoration:none;}'>Makes</a><BR>");
						//Receipts - Deposit
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Models' STYLE='{text-decoration:none;}'>Models</a><BR>");
						//Printing - Receipts
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Application_Status' STYLE='{text-decoration:none;}'>Application Status</a><BR>");
						//Receipts - Deposit Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Clients' STYLE='{text-decoration:none;}'>Clients</a><BR>");
						//Receipts - Return & Realization
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Client_Groups' STYLE='{text-decoration:none;}'>Client Groups</a><BR>");
						//Receipts - Cancellation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Guarantors' STYLE='{text-decoration:none;}'>Guarantors</a><BR>");
						//Grouped Receipts - Setup 
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Engine_Capacities' STYLE='{text-decoration:none;}'>Engine Capacities</a><BR>");
						//Grouped Receipts - Entry 
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Sub_Models' STYLE='{text-decoration:none;}'>Sub Models</a><BR>");
						//Post Dated Cheques - Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Condition_of_Assets' STYLE='{text-decoration:none;}'>Condition of Assets</a><BR>");
						//Post Dated Cheques - Receipt Generation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Fuel_Types' STYLE='{text-decoration:none;}'>Fuel Types</a><BR>");
						//Post Dated Cheques - Withdrawal
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Options' STYLE='{text-decoration:none;}'>Options</a><BR>");
						//Repossession - Order Generation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Mileage' STYLE='{text-decoration:none;}'>Mileage</a><BR>");
						//Repossession - Vehicle Inventory - Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Asset_Usage_Type' STYLE='{text-decoration:none;}'>Asset Usage Type</a><BR>");
						//Repossession - Vehicle Inventory - Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Documents_Required' STYLE='{text-decoration:none;}'>Documents Required</a><BR>");
						//Repossession - Valuation for Vehicle in Yard
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Documents_Applicable_to_Client_Asset' STYLE='{text-decoration:none;}'>Documents Applicable to Client/Asset</a><BR>");
						//Repossession - Recovery of Financed Amount
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Vendors' STYLE='{text-decoration:none;}'>Vendors</a><BR>");
						//Repossession - Advertisement Generation
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Garages' STYLE='{text-decoration:none;}'>Garages</a><BR>");
						//Repossession - Entry of Offers
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Valuation_Fields' STYLE='{text-decoration:none;}'>Valuation Fields</a><BR>");
						//Repossession - Acceptance of Offers
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Applicable_Fields_in_Valuation' STYLE='{text-decoration:none;}'>Applicable Fields in Valuation</a><BR>");
						//Repossession - Reverse Repossession
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Valuers' STYLE='{text-decoration:none;}'>Valuers</a><BR>");
						//Maintenance - Application Status Change - Entry
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Income_Expense_Types' STYLE='{text-decoration:none;}'>Income/Expense Types</a><BR>");
						//Maintenance - Application Status Change - Approval
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#CRIB_Number' STYLE='{text-decoration:none;}'>CRIB Number</a><BR>");
						//Maintenance - Assign Collection Officer
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Screen_Order' STYLE='{text-decoration:none;}'>Screen Order</a><BR>");
						//Maintenance - Collection Officer Target Setup
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Approval_of_Reference_Info' STYLE='{text-decoration:none;}'>Approval of Reference Info</a><BR>");
						//Maintenance - Collection Reminders
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Conditions' STYLE='{text-decoration:none;}'>Conditions</a><BR>");
						//Maintenance - Assign Insurance Officer
						// sub menus
						out.println("<SPAN onClick='dual3_2();'> ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Transactions_Monthly_Targets' STYLE='{text-decoration:none;}'>Transactions</a></SPAN><BR>");
						out.println("<SPAN ID='menu3_2' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Transactions_Monthly_Targets' STYLE='{text-decoration:none;}'>Monthly Targets</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Transactions_Business_Volume_Setup' STYLE='{text-decoration:none;}'>Business Volume Setup</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Transactions_Rental_Target_Setup' STYLE='{text-decoration:none;}'>Rental Target Setup</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Application_Process#Transactions_ODI_Target_Setup' STYLE='{text-decoration:none;}'>ODI Target Setup</a><BR>");
						
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						//add by madhawa 2009-11-24
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dualLett_doc();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Pricing#Pricing' STYLE='{text-decoration:none;}'>Pricing</a></SPAN><BR>");
						out.println("<SPAN ID='menuLetDoc' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Pricing#Pricing_Default_Values' STYLE='{text-decoration:none;}'>Pricing Default Values</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Pricing#Charges' STYLE='{text-decoration:none;}'>Charges</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Pricing#Sub_Charges' STYLE='{text-decoration:none;}'>Sub Charges</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Pricing#VAT_Rates' STYLE='{text-decoration:none;}'>VAT Rates</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Pricing#Assign_Payees_for_Sub_Charges' STYLE='{text-decoration:none;}'>Assign Payees for Sub Charges</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Pricing#Variable_Interest_Rate_Bases' STYLE='{text-decoration:none;}'>Variable Interest Rate Bases</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Pricing#Variable_base_rates' STYLE='{text-decoration:none;}'>Variable base rates</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Pricing#Applicable_Charges_by_Sub_Items' STYLE='{text-decoration:none;}'>Applicable Charges by Sub Items</A><BR>");
						
						
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						//end add by madhawa 2009-11-24

						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dual3im();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_Credit#Credit' STYLE='{text-decoration:none;}'>Credit</a></SPAN><BR>");
						out.println("<SPAN ID='menu3im' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_Credit#Brokers' STYLE='{text-decoration:none;}'>Brokers</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_Credit#Credit_Score_Categories' STYLE='{text-decoration:none;}'>Credit Score Categories</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_Credit#Credit_Score_Sub_categories' STYLE='{text-decoration:none;}'>Credit Score Sub-categories</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_Credit#Credit_Score_Ratings' STYLE='{text-decoration:none;}'>Credit Score Ratings</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_Credit#Credit_Score_Models' STYLE='{text-decoration:none;}'>Credit Score Models</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_Credit#RMV_Agents' STYLE='{text-decoration:none;}'>RMV Agents</a><BR>");
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						
						
											
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dualmnufinac();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_finance#Finance' STYLE='{text-decoration:none;}'>Finance</a></SPAN><BR>");
						out.println("<SPAN ID='menufinance' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_finance#Account_Codes' STYLE='{text-decoration:none;}'>Account Codes</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_finance#Licensee_Bank_Accounts' STYLE='{text-decoration:none;}'>Licensee Bank Accounts</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_finance#Authorization_Limits' STYLE='{text-decoration:none;}'>Authorization Limits</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_finance#Cheque_Return_Narrations' STYLE='{text-decoration:none;}'>Cheque Return Narrations</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_finance#Loan_Facilities' STYLE='{text-decoration:none;}'>Loan Facilities</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_finance#Loan_Facilities_Assign' STYLE='{text-decoration:none;}'>Loan Facilities Assign</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_finance#Loan_Facilities_Assign' STYLE='{text-decoration:none;}'>ODI Alterations</a><BR>");
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
						
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dualMaintenance();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Termination' STYLE='{text-decoration:none;}'>Termination and Exception</a></SPAN><BR>");
						out.println("<SPAN ID='Maintenancemnu' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Seizers' STYLE='{text-decoration:none;}'>Seizers</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Lawyers' STYLE='{text-decoration:none;}'>Lawyers</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Vendor_Blacklisting' STYLE='{text-decoration:none;}'>Vendor Blacklisting</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Termination_Rate' STYLE='{text-decoration:none;}'>Termination Rate</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Client_Blacklisting' STYLE='{text-decoration:none;}'>Client Blacklisting</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Discounting_Rates' STYLE='{text-decoration:none;}'>Discounting Rates</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Early_Termination_Charges' STYLE='{text-decoration:none;}'>Early Termination Charges</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Missing_Assets' STYLE='{text-decoration:none;}'>Missing Assets</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_termination_exception#Yards' STYLE='{text-decoration:none;}'>Yards</a><BR>");
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");
	
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dualFollowup();'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_followup#Follow_Up' STYLE='{text-decoration:none;}'>Follow Up</a></SPAN><BR>");
						out.println("<SPAN ID='mnuFollowup' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_Administration_followup#Follow_Up_Action_Categories' STYLE='{text-decoration:none;}'>Follow Up Action Categories</a><BR>");
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");

						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input >");
						out.println("<SPAN onClick='dualFactoring()'> ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Factoring' STYLE='{text-decoration:none;}'>Factoring</a></SPAN><BR>");
						out.println("<SPAN ID='mnuFactoring' >");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Product_Features' STYLE='{text-decoration:none;}'>Product Features</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Products' STYLE='{text-decoration:none;}'>Products</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Fees' STYLE='{text-decoration:none;}'>Fees</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Fee_Packages' STYLE='{text-decoration:none;}'>Fee Packages</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Product_Category' STYLE='{text-decoration:none;}'>Product Category</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Client_Debtor_Classification' STYLE='{text-decoration:none;}'>Client/Debtor Classification</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Collection_Routes' STYLE='{text-decoration:none;}'>Collection Routes</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Collection_Routes_Assign' STYLE='{text-decoration:none;}'>Collection Routes Assign</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Factoring_Default_Values' STYLE='{text-decoration:none;}'>Factoring Default Values</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"AF_UG_administration_factoring#Remarks' STYLE='{text-decoration:none;}'>Remarks</a><BR>");
						out.println("</SPAN>");
						out.println("</td>");
						out.println("</TR>");

				*/

						
						
						

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
