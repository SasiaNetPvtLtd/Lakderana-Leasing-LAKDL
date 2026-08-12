

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//DEVELOPED BY MAHELA FOR OFSCL on 24-05-2007

public class LAKDL_MISF_UG_new_help_left extends javax.servlet.http.HttpServlet { 

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
						out.println("function nodrop1() {");
						out.println("document.all.menu1.style.display = 'block';");
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
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Leasing_and_Loans' STYLE='{text-decoration:none;}'>Leasing and Loans</a></SPAN><BR>");
						out.println("<span ID='menuintro'  >");
						//menu level2
						out.println("<span onclick='dualintro_1()';>");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Follow_Up_Report' STYLE='{text-decoration:none;}'>Follow Up</a></SPAN><BR>");
						out.println("<span ID='menuintro_1'  >");
						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Follow_Up_Report' STYLE='{text-decoration:none;}'>Report</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Follow_Up_History_Report' STYLE='{text-decoration:none;}'>History Report </a><BR>");						
						out.println("</span>");
						//end menu level 2
						//menu level2
						out.println("<span onclick='dualintro_2()';>");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Invoicing_Adjustment_Report' STYLE='{text-decoration:none;}'>Invoicing</a></SPAN><BR>");
						out.println("<span ID='menuintro_2'  >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Invoicing_Adjustment_Report' STYLE='{text-decoration:none;}'>Adjustment Report </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Invoicing_Details_Report' STYLE='{text-decoration:none;}'>Details Report</a><BR>");
						out.println("</span>");
						//end menu level 2
						//menu level2
						out.println("<span onclick='dualintro_3()';>");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Senior_Management_Sales_Report' STYLE='{text-decoration:none;}'>Senior Management</a></SPAN><BR>");
						out.println("<span ID='menuintro_3'  >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Senior_Management_Sales_Report' STYLE='{text-decoration:none;}'>Sales Report</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Senior_Management_Second_Level_Report' STYLE='{text-decoration:none;}'>Second Level Report </a><BR>");
						out.println("</span>");
						//end menu level2
						
						//menu level2
						out.println("<span onclick='dualintro_4()';>");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Agreement_Register' STYLE='{text-decoration:none;}'>Credit</a></SPAN><BR>");
						out.println("<span ID='menuintro_4'  >");

						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Agreement_Register' STYLE='{text-decoration:none;}'>Agreement Register </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Application_Process_Report' STYLE='{text-decoration:none;}'>Application Process Report </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Finance_Status_Report' STYLE='{text-decoration:none;}'>Finance Status Report </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Rental_Details_Report' STYLE='{text-decoration:none;}'>Rental Details Report </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Contract_Details_Report' STYLE='{text-decoration:none;}'>Contract Details Report </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Contract_Balance_Report' STYLE='{text-decoration:none;}'>Contract Balance Report</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Activation_Error_Log' STYLE='{text-decoration:none;}'>Activation Error Log</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Termination_Report' STYLE='{text-decoration:none;}'>Termination Report </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Exposure_And_Arrears_Statement' STYLE='{text-decoration:none;}'>Exposure And Arrears Statement </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_ODI_Waved_Off_Report' STYLE='{text-decoration:none;}'>ODI Waved Off Report</a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Old_Repayment_Schedule' STYLE='{text-decoration:none;}'>Old Repayment Schedule </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Audit_Reports_Administration ' STYLE='{text-decoration:none;}'>Audit Reports - Administration  </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Credit_Audit_Reports_Transactions' STYLE='{text-decoration:none;}'>Audit Reports - Transactions </a><BR>");
						out.println("</span>");
						//end menu level2
						out.println("<span onclick='dualintro_5()';>");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Collection_Asset_Insurance_Details' STYLE='{text-decoration:none;}'>Collection</a></SPAN><BR>");
						out.println("<span ID='menuintro_5'  >");

						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Collection_Asset_Insurance_Details' STYLE='{text-decoration:none;}'>Asset Insurance Details </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Collection_Collection_Report_With_Age' STYLE='{text-decoration:none;}'>Collection Report With Age </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Collection_Collection_Report' STYLE='{text-decoration:none;}'>Collection Report </a><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Leasing_and_Loans#Collection_P_D_Cheque_Report' STYLE='{text-decoration:none;}'>P D Cheque Report </a><BR>");
						out.println("</span>");
						
						out.println("</span>");
						out.println("</TD>");
						out.println("</TR>");
						
						out.println("<TR>");
						out.println("<TD VALIGN='top' WIDTH='350' class=div_input  >");
						out.println("<SPAN onClick='dual6();' > ");
						out.println("");
						out.println("<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Factoring' STYLE='{text-decoration:none;}'>Factoring</A></SPAN><BR>");
						out.println("<SPAN ID='menu6' >");
						
						//menu level2
						out.println("<SPAN onClick='dual6_1();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Marketing_Inquiry_Details' STYLE='{text-decoration:none;}'>Marketing</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_1' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Marketing_Inquiry_Details' STYLE='{text-decoration:none;}'>Inquiry Details (User)</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Marketing_Inquiry_Details_all' STYLE='{text-decoration:none;}'>Inquiry Details (All)</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Marketing_Quotation_Details_Report' STYLE='{text-decoration:none;}'>Quotation Details Report</a><BR>");	
						out.println("</SPAN>");
						//end menu level2
						
						out.println("<SPAN onClick='dual6_2();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Credit_Client_Detail_Report' STYLE='{text-decoration:none;}'>Credit</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_2' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Credit_Client_Detail_Report' STYLE='{text-decoration:none;}'>Client Detail Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Credit_Active_Debtor_List_Report' STYLE='{text-decoration:none;}'>Active Debtor List Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Credit_Invoice_Verification_Report' STYLE='{text-decoration:none;}'>Invoice Verification Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Credit_Credit_Limit_Zero_Clients' STYLE='{text-decoration:none;}'>Credit Limit Zero Clients</a><BR>");
						out.println("</SPAN>");
						//end menu level2
						out.println("<SPAN onClick='dual6_3();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Non_Sales_and_Excess_Funds_Transfer_Report' STYLE='{text-decoration:none;}'>Operations</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_3' >");


						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Non_Sales_and_Excess_Funds_Transfer_Report' STYLE='{text-decoration:none;}'>Non Sales and Excess Funds Transfer Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Exception_Report' STYLE='{text-decoration:none;}'>Exception Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Operation_Details' STYLE='{text-decoration:none;}'>Operation Details</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_PD_Cheque_Report' STYLE='{text-decoration:none;}'>PD Cheque Report</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Realized_Settlement_Report' STYLE='{text-decoration:none;}'>Realized Settlement Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Invoice_Portfolio_Report' STYLE='{text-decoration:none;}'>Invoice Portfolio Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Client_Charges_Report' STYLE='{text-decoration:none;}'>Client Charges Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_PD_Age_Analysis_Report' STYLE='{text-decoration:none;}'>PD Age Analysis Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Reserve_Account_Summary' STYLE='{text-decoration:none;}'>Reserve Account Summary</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Return_Settlement_Report' STYLE='{text-decoration:none;}'>Return Settlement Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Return_Cheque_Exception_Report' STYLE='{text-decoration:none;}'>Return Cheque Exception Report</a><BR>");	

						
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Unallocated_Funds_Detail_Report' STYLE='{text-decoration:none;}'>Unallocated Funds Detail Report</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Cancel_P_D_Cheque_Report' STYLE='{text-decoration:none;}'>Cancel P D Cheque Report</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Unbanked_Cheque_Report' STYLE='{text-decoration:none;}'>Unbanked Cheque Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_PD_Cheque_and_Settlement_Receipt_Report' STYLE='{text-decoration:none;}'>PD Cheque and Settlement Receipt Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Invoice_Age_Analysis_Debtor_Wise_Report' STYLE='{text-decoration:none;}'>Invoice Age Analysis Debtor-wise Reports</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Global_Availability' STYLE='{text-decoration:none;}'>Global Availability</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Operations_Client_Daily_Statement' STYLE='{text-decoration:none;}'>Client Daily Statement</a><BR>");	
						
						out.println("</SPAN>");
						out.println("<SPAN onClick='dual6_4();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Collections_Collection_Report' STYLE='{text-decoration:none;}'>Collections</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_4' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Collections_Collection_Report' STYLE='{text-decoration:none;}'>Collection Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Collections_Remarks_Report' STYLE='{text-decoration:none;}'>Remarks Report</a><BR>");	
						out.println("</SPAN>");
						
						out.println("<SPAN onClick='dual6_5();' > ");
						out.println("");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littlex.gif' width='21' height='19'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Accounting_Factoring_Finance_Interest' STYLE='{text-decoration:none;}'>Accounting</A></SPAN><BR>");
						out.println("<SPAN ID='menu6_5' >");

						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Accounting_Factoring_Finance_Interest' STYLE='{text-decoration:none;}'>Factoring Finance Interest</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Accounting_Factoring_Actual_Yield' STYLE='{text-decoration:none;}'>Factoring Actual Yield</A><BR>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Accounting_Account_Report' STYLE='{text-decoration:none;}'>Account Report</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Accounting_Income_Suspense_Generation' STYLE='{text-decoration:none;}'>Income Suspense Generation</a><BR>");	
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<IMG SRC='"+m_html_client_url+"/images/littledash.gif' width='20' height='18'>&nbsp;<a target='help-right' href='"+m_class_url+"/"+m_fschema_name+"MISF_UG_Factoring#Accounting_Income_Suspense_and_Provision_Processing' STYLE='{text-decoration:none;}'>Income Suspense and Provision Processing</a><BR>");	
						out.println("</SPAN>");
						
						
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
