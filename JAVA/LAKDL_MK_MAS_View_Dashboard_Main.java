

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
import java.io.IOException;


public class LAKDL_MK_MAS_View_Dashboard_Main extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	String header_id = "";	
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9,stmt10,stmt11,stmt12,stmt13,stmt14,stmt15,stmt16;
	public ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10,rs11,rs12,rs13,rs14,rs15,rs16;
	java.text.NumberFormat nf;
	//nf=null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws ServletException,IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			Connection conn = m_sn_methods.met_user_validate(req);
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			//req.setCharacterEncoding("ISO-8859-1");
			//res.setCharacterEncoding("UTF-8"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			/*********************************************************/
			String TXT_APPLICATION_NO 	= req.getParameter("APP_NO");
			String TXT_APPLICANT_CODE   = req.getParameter("APPLICANT_CODE");
			String TXT_APPLICANT_NAME   = req.getParameter("APPLICANT_NAME");
			String status               = req.getParameter("STATUS");
			String m_schema_name = m_sn_methods.schema_name;
			stmt = conn.createStatement ();
			stmt1= conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			
			stmt6=conn.createStatement();
			stmt7=conn.createStatement();
			stmt8=conn.createStatement();
			stmt9=conn.createStatement();
			
			stmt10=conn.createStatement();
			stmt11=conn.createStatement();
			stmt12=conn.createStatement();
			stmt13=conn.createStatement();
			stmt14=conn.createStatement();
			stmt15=conn.createStatement();
			stmt16=conn.createStatement();
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Dashboard</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<link REL=\"STYLESHEET\" HREF='"+m_html_client_url+"/csss/jquery-ui.css'  TYPE=\"text/css\">");
			out.println("<link REL=\"STYLESHEET\" HREF='"+m_html_client_url+"/csss/jquery.ui.button.css'  TYPE=\"text/css\" >");
			out.println("<link REL=\"stylesheet\" type=\"text/css\" href='"+m_html_client_url+"/csss/ui.jqgrid.css'>");
			out.println("<link REL=\"stylesheet\" type=\"text/css\" href='"+m_html_client_url+"/csss/jquery.ui.datepicker.css'>");
			
			//out.println("<script type=\"text/javascript\" src=\"" + m_jsp_client_url + "/js/jquery.min.js\"></script>");
			//out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/jquery.jqGrid.min.js\"></script>");
			out.println("<script type=\"text/javascript\" src= \""+m_html_client_url+"/newdash/jquery.min.js\"></script> ");
			out.println("<script type=\"text/javascript\" src= \""+m_html_client_url+"/newdash/jquery-ui.min.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/json2.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/popup.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/grid.locale-en.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/jquery.jqGrid.min.js\"></script>");
			out.println("<script type=\"text/javascript\" src='"+m_html_client_url+"/FusionCharts/FusionCharts.js'></script>");
			//out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/CLC/FusionCharts/FusionCharts.js\"></script>");//location of charts swf
			/***********AJAX*************/
			out.println("<script>");
			out.println("	function loadXMLDoc(m_div,url,isasyn)");
			out.println("{");
			out.println("alert(m_div.value);");
			out.println("	var xmlhttp;");
			out.println("	if (window.XMLHttpRequest)");
			out.println("	{");// code for IE7+, Firefox, Chrome, Opera, Safari");
			out.println("		xmlhttp=new XMLHttpRequest();");
			out.println("	}");
			out.println("	else");
			out.println("	{");// code for IE6, IE5
			out.println("		xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");");
			out.println("	}");
			out.println("	xmlhttp.onreadystatechange=function()");
			out.println("	{");
			
			out.println("		if (xmlhttp.readyState==4 && xmlhttp.status==200)");
			out.println("		{");
			out.println("			document.getElementById(m_div).innerHTML=xmlhttp.responseText;");
			
			out.println("		}");
			out.println("	}");
			
			out.println("	xmlhttp.open(\"POST\",url,isasyn);");
			out.println("	xmlhttp.send();");
			out.println("}");
			out.println("</script>");
			
			
			/***********************/
			/************CSS**************/
			out.println("<style TYPE=\"text/css\" >");
			
			out.println("	.header_table_class tr:first-child td{");
			out.println("	background:-o-linear-gradient(bottom, #454c54 5%, #eaebed 100%);");	
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #454c54), color-stop(1, #eaebed) );");
			out.println("	background:-moz-linear-gradient( center top, #454c54 5%, #eaebed 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#454c54\", endColorstr=\"#eaebed\");");
			out.println("	background: -o-linear-gradient(top,#454c54,eaebed);");
			
			out.println("	background-color:#454c54;");
			out.println("	border:0px solid #000000;");
			out.println("	text-align:center;");
			out.println("	border-width:0px 0px 1px 1px;");
			out.println("	font-size:12px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:bold;");
			out.println("	color:#ffffff;");
			out.println("}");
			
			
			//---------------------------------------------------------------------------------------------------------------------------------------------
			
			
			out.println(".sub_table_class {");
			out.println("	margin:0px;padding:0px;");
			out.println("	width:100%;");
			out.println("	box-shadow: 10px 10px 5px #888888;");
			out.println("	border:1px solid #d1d1f9;");
			out.println("	-moz-border-radius-bottomleft:0px;");
			out.println("	-webkit-border-bottom-left-radius:0px;");
			out.println("	border-bottom-left-radius:0px;");
			out.println("	-moz-border-radius-bottomright:0px;");
			out.println("	-webkit-border-bottom-right-radius:0px;");
			out.println("	border-bottom-right-radius:0px;");
			out.println("	-moz-border-radius-topright:0px;");
			out.println("	-webkit-border-top-right-radius:0px;");
			out.println("	border-top-right-radius:0px;");
			
			out.println("	-moz-border-radius-topleft:0px;");
			out.println("	-webkit-border-top-left-radius:0px;");
			out.println("	border-top-left-radius:0px;");
			out.println("}");
			out.println(".sub_table_class table{");
			out.println("	width:100%;");
			out.println("	height:100%;");
			out.println("	margin:0px;padding:0px;");
			out.println("}");
			out.println(".sub_table_class tr:last-child td:last-child {");
			out.println("	-moz-border-radius-bottomright:0px;");
			out.println("	-webkit-border-bottom-right-radius:0px;");
			out.println("	border-bottom-right-radius:0px;");
			out.println("	}");
			out.println(".sub_table_class table tr:first-child td:first-child {");
			out.println("	-moz-border-radius-topleft:0px;");
			out.println("	-webkit-border-top-left-radius:0px;");
			out.println("	border-top-left-radius:0px;");
			out.println("	}");
			out.println(".sub_table_class table tr:first-child td:last-child {");
			out.println("	-moz-border-radius-topright:0px;");
			out.println("	-webkit-border-top-right-radius:0px;");
			out.println("	border-top-right-radius:0px;");
			out.println("}.sub_table_class tr:last-child td:first-child{");
			out.println("-moz-border-radius-bottomleft:0px;");
			out.println("-webkit-border-bottom-left-radius:0px;");
			out.println("	border-bottom-left-radius:0px;");
			out.println("}.sub_table_class tr:hover td{");
			//background-color:#f9f4fc;
			
			
			out.println("}");
			out.println(".sub_table_class td{");
			out.println("vertical-align:middle;");
			//background:-o-linear-gradient(bottom, #ffffff 5%, #f9f4fc 100%);
			out.println("background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #ffffff), color-stop(1, #f9f4fc) ); ");
			out.println("background:-moz-linear-gradient( center top, #ffffff 5%, #f9f4fc 100% );");
			out.println("filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#ffffff\", endColorstr=\"#f9f4fc\");	");
			out.println("	background: -o-linear-gradient(top,#ffffff,f9f4fc);");
			
			//background-color:#ffffff;
			
			out.println("border:1px solid #d1d1f9;");
			out.println("	border-width:0px 1px 1px 0px;");
			out.println("	text-align:left;");
			out.println("padding:2px;");
			out.println("font-size:10px;");
			out.println("font-family:Arial;");
			out.println("font-weight:normal;");
			out.println("color:#000000;");
			out.println("}");
			out.println(".sub_table_class tr:last-child td{");
			out.println("	border-width:0px 1px 0px 0px;");
			out.println("	}.sub_table_class tr td:last-child{");
			out.println("	border-width:0px 0px 1px 0px;");
			out.println("}.sub_table_class tr:last-child td:last-child{");
			out.println("	border-width:0px 0px 0px 0px;");
			out.println("}");
			out.println(".sub_table_class tr:first-child td{");
			out.println("	background:-o-linear-gradient(bottom, #56256b 5%, #303030 100%);	");
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #56256b), color-stop(1, #303030) );");
			out.println("	background:-moz-linear-gradient( center top, #56256b 5%, #303030 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#56256b\", endColorstr=\"#303030\");");
			out.println("	background: -o-linear-gradient(top,#56256b,303030);");
			
			out.println("	background-color:#56256b;");
			out.println("	border:0px solid #d1d1f9;");
			out.println("	text-align:center;");
			out.println("	border-width:0px 0px 1px 1px;");
			out.println("	font-size:10px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:bold;");
			out.println("	color:#f7f7f7;");
			out.println("}");
			
			
			
			out.println(".sub_table_class tr:first-child td:first-child{");
			out.println("	border-width:0px 0px 1px 0px;");
			out.println("}");
			out.println(".sub_table_class tr:first-child td:last-child{");
			out.println("	border-width:0px 0px 1px 1px;");
			out.println("}");
			
			//-------------------------------------------------------------------------------------------------------------------
			
			out.println(".button-wraper {visibility:hidden;}");
			out.println(".button-wraper {display:none;}");
			
			out.println(".gridTable {");
			out.println("	margin:0px;padding:0px;");
			out.println("	width:100%;");
			out.println("	box-shadow: 10px 10px 5px #888888;");
			out.println("	border:1px solid #000000;");
			out.println("-moz-border-radius-bottomleft:0px;");
			out.println("-webkit-border-bottom-left-radius:0px;");
			out.println("border-bottom-left-radius:0px;");
			out.println("-moz-border-radius-bottomright:0px;");
			out.println("-webkit-border-bottom-right-radius:0px;");
			out.println("border-bottom-right-radius:0px;");
			out.println("-moz-border-radius-topright:0px;");
			out.println("-webkit-border-top-right-radius:0px;");
			out.println("	border-top-right-radius:0px;");
			out.println("	-moz-border-radius-topleft:0px;");
			out.println("	-webkit-border-top-left-radius:0px;");
			out.println("	border-top-left-radius:0px;");
			out.println("	}.gridTable table{");
			out.println("	width:100%;");
			out.println("	height:100%;");
			out.println("	margin:0px;padding:0px;");
			out.println("}.gridTable tr:last-child td:last-child {");
			out.println("	-moz-border-radius-bottomright:0px;");
			out.println("	-webkit-border-bottom-right-radius:0px;");
			out.println("	border-bottom-right-radius:0px;");
			out.println("}");
			out.println(".gridTable table tr:first-child td:first-child {");
			out.println("	-moz-border-radius-topleft:0px;");
			out.println("	-webkit-border-top-left-radius:0px;");
			out.println("	border-top-left-radius:0px;");
			out.println("	}");
			out.println(".gridTable table tr:first-child td:last-child {");
			out.println("	-moz-border-radius-topright:0px;");
			out.println("	-webkit-border-top-right-radius:0px;");
			out.println("		border-top-right-radius:0px;");
			out.println("	}.gridTable tr:last-child td:first-child{");
			out.println("	-moz-border-radius-bottomleft:0px;");
			out.println("	-webkit-border-bottom-left-radius:0px;");
			out.println("	border-bottom-left-radius:0px;");
			out.println("	}");
			//.gridTable tr:nth-child(odd){ background-color:#88a7f7; }
			//.gridTable tr:nth-child(even){ background-color:#b8e0e0; }
			
			out.println(".gridTable tr:nth-child(odd) td{");
			out.println("	background:-o-linear-gradient(bottom, #88a7f7 5%, #88a7f7 100%);");	
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #88a7f7), color-stop(1, #88a7f7) );");
			out.println("	background:-moz-linear-gradient( center top, #88a7f7 5%, #88a7f7 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#88a7f7\", endColorstr=\"#88a7f7\");");	
			out.println("	background: -o-linear-gradient(top,#88a7f7,88a7f7);");
			
			out.println("	background-color:#88a7f7;");
			out.println("	border:0px solid #000000;");
			out.println("	border-width:0px 1px 0px 0px;");
			out.println("	font-size:10px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:bold;");
			out.println("	color:#000000;");
			out.println("}");
			
			out.println(".gridTable tr:nth-child(even) td{");
			out.println("	background:-o-linear-gradient(bottom, #b8e0e0 5%, #b8e0e0 100%);");	
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #b8e0e0), color-stop(1, #b8e0e0) );");
			out.println("	background:-moz-linear-gradient( center top, #b8e0e0 5%, #b8e0e0 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#b8e0e0\", endColorstr=\"#b8e0e0\");	");
			out.println("	background: -o-linear-gradient(top,#b8e0e0,b8e0e0);");
			
			out.println("	background-color:#b8e0e0;");
			out.println("	border:0px solid #000000;");
			out.println("	border-width:0px 1px 0px 0px;");
			out.println("	font-size:10px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:bold;");
			out.println("	color:#000000;");
			out.println("}	");
			
			out.println(".gridTable td{");
			out.println("	vertical-align:middle;");
			out.println("	border:1px solid #000000;");
			out.println("	border-width:0px 1px 1px 0px;");
			out.println("	text-align:left;");
			out.println("	padding:5px;");
			out.println("	font-size:10px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:normal;");
			out.println("	color:#000000;");
			out.println("}.gridTable tr:last-child td{");
			out.println("		border-width:0px 1px 0px 0px;");
			out.println("	}.gridTable tr td:last-child{");
			out.println("	border-width:0px 0px 1px 0px;");
			out.println("	}.gridTable tr:last-child td:last-child{");
			out.println("	border-width:0px 0px 0px 0px;");
			//out.println("	}");
			
			out.println("}.gridTable tr:first-child th{");
			out.println("	background:-o-linear-gradient(bottom, #003366 5%, #003f7f 100%);");	
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #003366), color-stop(1, #003f7f) );");
			out.println("	background:-moz-linear-gradient( center top, #003366 5%, #003f7f 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#003366\", endColorstr=\"#003f7f\");	");
			out.println("	background: -o-linear-gradient(top,#003366,003f7f);");
			
			out.println("	background-color:#003366;");
			out.println("	border:0px solid #000000;");
			out.println("	text-align:center;");
			out.println("	border-width:0px 0px 1px 1px;");
			out.println("	font-size:10px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:bold;");
			out.println("	color:#ffffff;");
			out.println("	}	");
			
			out.println("	.gridTable tr:first-child td{");
			out.println("	background:-o-linear-gradient(bottom, #003366 5%, #003f7f 100%);");	
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #003366), color-stop(1, #003f7f) );");
			out.println("	background:-moz-linear-gradient( center top, #003366 5%, #003f7f 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#003366\", endColorstr=\"#003f7f\");	");
			out.println("	background: -o-linear-gradient(top,#003366,003f7f);");
			
			out.println("	background-color:#003366;");
			out.println("	border:0px solid #000000;");
			out.println("	text-align:center;");
			out.println("		border-width:0px 0px 1px 1px;");
			out.println("		font-size:10px;");
			out.println("		font-family:Arial;");
			out.println("		font-weight:bold;");
			out.println("		color:#ffffff;");
			out.println("	}");
			
			out.println("	.gridTable tr:first-child td:first-child{");
			out.println("		border-width:0px 0px 1px 0px;");
			out.println("	}");
			out.println("	.gridTable tr:first-child td:last-child{");
			out.println("		border-width:0px 0px 1px 1px;");
			out.println("	}");
			
			out.println("	.gridTable caption {");
			out.println("	background: #dbb768;");
			out.println("	font-weight: bold;");
			out.println("		font-size: 1.1em;");
			out.println("	}");
			
			//--------------------------------------------------------------------------------------------------------------
			
			
			
			out.println(".doubleheadgridTable {");
			out.println("	margin:0px;padding:0px; ");
			out.println("	width:100%;");
			out.println("	box-shadow: 10px 10px 5px #888888;");
			out.println("	border:1px solid #000000;");
			out.println("		-moz-border-radius-bottomleft:0px;");
			out.println("		-webkit-border-bottom-left-radius:0px;");
			out.println("		border-bottom-left-radius:0px;");
			out.println("		-moz-border-radius-bottomright:0px;");
			out.println("	-webkit-border-bottom-right-radius:0px;");
			out.println("	border-bottom-right-radius:0px;");
			out.println("		-moz-border-radius-topright:0px;");
			out.println("		-webkit-border-top-right-radius:0px;");
			out.println("		border-top-right-radius:0px;");
			out.println("		-moz-border-radius-topleft:0px;");
			out.println("		-webkit-border-top-left-radius:0px;");
			out.println("		border-top-left-radius:0px;");
			out.println("	}.doubleheadgridTable table{");
			out.println("		width:100%;");
			out.println("		height:100%;");
			out.println("		margin:0px;padding:0px;");
			out.println("	}.doubleheadgridTable tr:last-child td:last-child {");
			out.println("		-moz-border-radius-bottomright:0px;");
			out.println("		-webkit-border-bottom-right-radius:0px;");
			out.println("		border-bottom-right-radius:0px;");
			out.println("	}");
			out.println("	.doubleheadgridTable table tr:first-child td:first-child {");
			out.println("		-moz-border-radius-topleft:0px;");
			out.println("		-webkit-border-top-left-radius:0px;");
			out.println("		border-top-left-radius:0px;");
			out.println("	}");
			out.println("	.doubleheadgridTable table tr:first-child td:last-child {");
			out.println("		-moz-border-radius-topright:0px;");
			out.println("		-webkit-border-top-right-radius:0px;");
			out.println("		border-top-right-radius:0px;");
			out.println("	}.doubleheadgridTable tr:last-child td:first-child{");
			out.println("		-moz-border-radius-bottomleft:0px;");
			out.println("		-webkit-border-bottom-left-radius:0px;");
			out.println("		border-bottom-left-radius:0px;");
			out.println("	}");
			
			out.println(".doubleheadgridTable td{");
			out.println("	vertical-align:middle;");
			out.println("	border:1px solid #000000;");
			out.println("	border-width:0px 1px 1px 0px;");
			out.println("	text-align:left;");
			out.println("	padding:5px;");
			out.println("	font-size:10px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:normal;");
			out.println("	color:#000000;");
			out.println("	}.doubleheadgridTable tr:last-child td{");
			out.println("		border-width:0px 1px 0px 0px;");
			out.println("}.doubleheadgridTable tr td:last-child{");
			out.println("	border-width:0px 0px 1px 0px;");
			out.println("}.doubleheadgridTable tr:last-child td:last-child{");
			out.println("	border-width:0px 0px 0px 0px;");
			out.println("}");
			
			
			
			
			out.println(".doubleheadgridTable tr:nth-child(even) td{");
			out.println("	background:-o-linear-gradient(bottom, #88a7f7 5%, #88a7f7 100%);	");
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #88a7f7), color-stop(1, #88a7f7) );");
			out.println("	background:-moz-linear-gradient( center top, #88a7f7 5%, #88a7f7 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#88a7f7\", endColorstr=\"#88a7f7\");	");
			out.println("	background: -o-linear-gradient(top,#88a7f7,88a7f7);");
			
			out.println("	background-color:#88a7f7;");
			out.println("	border:0px solid #000000;");
			out.println("	border-width:0px 1px 0px 0px;");
			out.println("	font-size:10px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:bold;");
			out.println("	color:#000000;");
			out.println("	}");
			
			out.println(".doubleheadgridTable tr:nth-child(odd) td{");
			out.println("	background:-o-linear-gradient(bottom, #b8e0e0 5%, #b8e0e0 100%);	");
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #b8e0e0), color-stop(1, #b8e0e0) );");
			out.println("	background:-moz-linear-gradient( center top, #b8e0e0 5%, #b8e0e0 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#b8e0e0\", endColorstr=\"#b8e0e0\");	");
			out.println("	background: -o-linear-gradient(top,#b8e0e0,b8e0e0);");
			
			out.println("	background-color:#b8e0e0;");
			out.println("	border:0px solid #000000;");
			out.println("	border-width:0px 1px 0px 0px;");
			out.println("	font-size:10px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:bold;");
			out.println("	color:#000000;");
			out.println("}	");
			
			
			
			//.doubleheadgridTable tr:nth-child(even) td{ background-color:#88a7f7; }
			//.doubleheadgridTable tr:nth-child(odd) td{ background-color:#b8e0e0; }
			out.println(".doubleheadgridTable tr:first-child td{");
			out.println("	background:-o-linear-gradient(bottom, #003366 5%, #003f7f 100%);");	
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #003366), color-stop(1, #003f7f) );");
			out.println("	background:-moz-linear-gradient( center top, #003366 5%, #003f7f 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#003366\", endColorstr=\"#003f7f\");	");
			out.println("	background: -o-linear-gradient(top,#003366,003f7f);");
			
			out.println("	background-color:#003366;");
			out.println("	border:0px solid #000000;");
			out.println("	text-align:center;");
			out.println("	border-width:0px 0px 1px 1px;");
			out.println("	font-size:10px;");
			out.println("		font-family:Arial;");
			out.println("		font-weight:bold;");
			out.println("		color:#ffffff;");
			out.println("	}	");
			out.println("	.doubleheadgridTable tr:nth-child(2) td{");
			out.println("		background:-o-linear-gradient(bottom, #003366 5%, #003f7f 100%);");	
			out.println("		background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #003366), color-stop(1, #003f7f) );");
			out.println("		background:-moz-linear-gradient( center top, #003366 5%, #003f7f 100% );");
			out.println("		filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#003366\", endColorstr=\"#003f7f\");	");
			out.println("		background: -o-linear-gradient(top,#003366,003f7f);");
			
			out.println("	background-color:#003366;");
			out.println("		border:0px solid #000000;");
			out.println("	text-align:center;");
			out.println("	border-width:0px 0px 1px 1px;");
			out.println("		font-size:10px;");
			out.println("		font-family:Arial;");
			out.println("	font-weight:bold;");
			out.println("	color:#ffffff;");
			out.println("	}	");
			
			
			
			out.println("	.doubleheadgridTable tr:first-child td:first-child{");
			out.println("		border-width:0px 0px 1px 0px;");
			out.println("	}");
			out.println("	.doubleheadgridTable tr:first-child td:last-child{");
			out.println("	border-width:0px 0px 1px 1px;");
			out.println("}");
			
			out.println("	.doubleheadgridTable caption {");
			out.println("		background: #dbb768;");
			out.println("		font-weight: bold;");
			out.println("		font-size: 1.1em;");
			out.println("		padding:1px;		");
			out.println("	}");
			//--------------------------------------------------------------------------------------------------
			out.println("	.gridRSS {");
			out.println("		margin:0px;padding:0px;");
			out.println("		width:100%;");
			out.println("		box-shadow: 10px 10px 5px #888888;");
			out.println("		border:1px solid #000000;");
			
			out.println("		-moz-border-radius-bottomleft:0px;");
			out.println("		-webkit-border-bottom-left-radius:0px;");
			out.println("		border-bottom-left-radius:0px;");
			
			out.println("		-moz-border-radius-bottomright:0px;");
			out.println("		-webkit-border-bottom-right-radius:0px;");
			out.println("		border-bottom-right-radius:0px;");
			
			out.println("		-moz-border-radius-topright:0px;");
			out.println("		-webkit-border-top-right-radius:0px;");
			out.println("		border-top-right-radius:0px;");
			
			out.println("		-moz-border-radius-topleft:0px;");
			out.println("		-webkit-border-top-left-radius:0px;");
			out.println("		border-top-left-radius:0px;");
			out.println("	}.gridRSS table{");
			out.println("	width:100%;");
			out.println("	height:100%;");
			out.println("	margin:0px;padding:0px;");
			
			out.println("	}.gridRSS tr:last-child td:last-child {");
			out.println("	-moz-border-radius-bottomright:0px;");
			out.println("	-webkit-border-bottom-right-radius:0px;");
			out.println("	border-bottom-right-radius:0px;");
			out.println("	}");
			out.println("	.gridRSS table tr:first-child td:first-child {");
			out.println("		-moz-border-radius-topleft:0px;");
			out.println("		-webkit-border-top-left-radius:0px;");
			out.println("		border-top-left-radius:0px;");
			out.println("	}");
			out.println("	.gridRSS table tr:first-child td:last-child {");
			out.println("		-moz-border-radius-topright:0px;");
			out.println("		-webkit-border-top-right-radius:0px;");
			out.println("		border-top-right-radius:0px;");
			out.println("	}.gridRSS tr:last-child td:first-child{");
			out.println("		-moz-border-radius-bottomleft:0px;");
			out.println("		-webkit-border-bottom-left-radius:0px;");
			out.println("		border-bottom-left-radius:0px;");
			out.println("	}.gridRSS tr:hover td{");
			out.println("	background-color:#ebf4f4;");
			
			
			out.println("}");
			out.println(".gridRSS td{");
			out.println("	vertical-align:middle;");
			out.println("	background:-o-linear-gradient(bottom, #edeff4 5%, #ebf4f4 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #edeff4), color-stop(1, #ebf4f4) ); ");
			out.println("	background:-moz-linear-gradient( center top, #edeff4 5%, #ebf4f4 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#edeff4\", endColorstr=\"#ebf4f4\");	background: -o-linear-gradient(top,#edeff4,ebf4f4);");
			
			out.println("	background-color:#edeff4;");
			
			out.println("	border:1px solid #000000;");
			out.println("	border-width:0px 1px 1px 0px;");
			out.println("	text-align:left;");
			out.println("	padding:7px;");
			out.println("	font-size:10px;");
			out.println("	font-family:Arial;");
			out.println("	font-weight:normal;");
			out.println("	color:#000000;");
			out.println("}.gridRSS tr:last-child td{");
			out.println("	border-width:0px 1px 0px 0px;");
			out.println("	}.gridRSS tr td:last-child{");
			out.println("	border-width:0px 0px 1px 0px;");
			out.println("}.gridRSS tr:last-child td:last-child{");
			out.println("	border-width:0px 0px 0px 0px;");
			//out.println("}");
			out.println("}.gridRSS tr:first-child td{");
			out.println("	background:-o-linear-gradient(bottom, #9e0606 5%, #890101 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #9e0606), color-stop(1, #890101) );");
			out.println("	background:-moz-linear-gradient( center top, #9e0606 5%, #890101 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#9e0606\", endColorstr=\"#890101\");	background: -o-linear-gradient(top,#9e0606,890101);");
			
			out.println("	background-color:#9e0606;");
			out.println("	border:0px solid #000000;");
			out.println("	text-align:center;");
			out.println("	border-width:0px 0px 1px 1px;");
			out.println("		font-size:12px;");
			out.println("		font-family:Arial;");
			out.println("		font-weight:bold;");
			out.println("		color:#ffffff;");
			out.println("	}");
			out.println("	.gridRSS tr:first-child:hover td{");
			out.println("		background:-o-linear-gradient(bottom, #9e0606 5%, #890101 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #9e0606), color-stop(1, #890101) );");
			out.println("		background:-moz-linear-gradient( center top, #9e0606 5%, #890101 100% );");
			out.println("		filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#9e0606\", endColorstr=\"#890101\");	background: -o-linear-gradient(top,#9e0606,890101);");
			
			out.println("		background-color:#9e0606;");
			out.println("	}");
			out.println("	.gridRSS tr:first-child td:first-child{");
			out.println("		border-width:0px 0px 1px 0px;");
			out.println("	}");
			out.println("	.gridRSS tr:first-child td:last-child{");
			out.println("		border-width:0px 0px 1px 1px;");
			out.println("	}");
			
			out.println(".gridRSS  a:link { color:#FFFFFF; text-decoration: none; }");
			out.println(".gridRSS  a:visited { color:#ccb7b7; text-decoration: none; }");
			out.println(".gridRSS  a:hover { color:#33348e; text-decoration: none; }");
			out.println(".gridRSS  a:active { color:#ccb7b7; text-decoration: underline;}");
			
			out.println(".gridRSS caption {");
			out.println("	background: #dbb768;");
			out.println("	font-weight: bold;");
			out.println("	font-size: 1.1em;");
			out.println("	padding:1px;	");
			out.println("	}	");
			
			out.println(".rssdate {");
			//vertical-align:right;
			out.println("	background:-o-linear-gradient(bottom, #edeff4 5%, #ebf4f4 100%);");
			out.println("	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #edeff4), color-stop(1, #ebf4f4) ); ");
			out.println("	background:-moz-linear-gradient( center top, #edeff4 5%, #ebf4f4 100% );");
			out.println("	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#edeff4\", endColorstr=\"#ebf4f4\");");
			out.println("	background: -o-linear-gradient(top,#edeff4,ebf4f4);");
			out.println("	background-color:#FFFFF;");
			out.println("	border:0px solid #000000;");
			out.println("	border-width:0px 0px 0px 0px;");
			out.println("	text-align:right;");
			out.println("	padding:1px;");
			out.println("	font-size:9px;");
			out.println("font-family:Arial;");
			out.println("	font-weight:normal;");
			out.println("	color:#FFFFF;");
			
			out.println("	}");
			
			
			
			//-------------------------------------------------------------------------------------
			
			out.println("	.div2 {width:350px;height:70px;padding:10px;border:1px solid #aaaaaa;}");
			
			out.println("</STYLE>");
			/**************************/
			
			out.println("<SCRIPT language=\"JavaScript\">");
			
			
			
			
			
			
			out.println("function get_vector(data_vec) {");
			
			
			out.println("if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("	alert('Record already exist');");
			out.println("	new_window();");
			out.println(" }");
			out.println("else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_SUB_TYPE_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("help_update()");
			out.println("}");
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_SUB_TYPE_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("document.Form1.TXT_SUB_TYPE_CODE.value=data_vec[0];"); 
			out.println("document.Form1.TXT_TYPE_CODE.value=data_vec[1];"); 
			out.println("document.Form1.TXT_DESCRIPTION.value=data_vec[2];"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value=data_vec[3];"); 
			out.println("document.Form1.TXT_MAINTENANCE_STATUS.value=data_vec[4];"); 
			out.println("document.Form1.TXT_CHARGE_TYPE.value=data_vec[5];"); 
			out.println("document.Form1.TXT_ACC_TYPE.value=data_vec[6];");
			out.println("}");
			
			out.println("if(data_vec.length==0 && document.Form1.hid_val.value=='t2' && document.Form1.TXT_TYPE_CODE.value!=''){");
			out.println("   help_button_2();");
			out.println("	}");
			out.println("}");
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");
			
			out.println("}");
			
			out.println("function clear_data() {");
			out.println("	if(document.Form1.hid_help_type.value==\"99\"){"); 
			//out.println("document.Form1.TXT_SUB_TYPE_CODE.value='';"); 
			//out.println("document.Form1.TXT_SUB_TYPE_CODE.focus();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			//out.println("document.Form1.TXT_TYPE_CODE.value='';"); 
			//out.println("document.Form1.TXT_TYPE_CODE.focus();"); 
			out.println("		}"); 
			out.println("		}"); 
			
			out.println("function makeRequest(obj) {");
			out.println("	if(document.Form1.SCREEN_NAME.value!=\"RACT\" && document.Form1.SCREEN_NAME.value==\"NEW\"  )");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_sub_charge&data_val=\"+obj.value;");
			out.println(" else");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_sub_charge1&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println(" load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest1(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_charges&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_SUB_TYPE_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SUB_TYPE_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PAYEE_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_PAYEE_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PAYEE_ADD1.value==\"\"){  "); 
			out.println("DIV_TXT_PAYEE_ADD1.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PAYEE_ADD2.value==\"\"){  "); 
			out.println("DIV_TXT_PAYEE_ADD2.style.color='red';");
			
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			out.println(" m_status = document.Form1.hid_status.value ");
			out.println(" m_save_msg='Are you sure you want to Save ? ';");
			out.println(" if(m_status == \"New\"){ ");
			out.println(" m_save_msg = 'Are you sure you want to Save ? '");
			out.println(" }"); 
			out.println(" else if(m_status == \"Edit\"){ ");
			out.println(" m_save_msg = 'Are you sure you want to Modify ? '");
			out.println(" }"); 
			
			out.println("else if(m_status == \"DACT\"){");
			out.println(" m_save_msg = 'Are you sure you want to Deactivate ? '");
			out.println(" }"); 
			
			out.println("else if(m_status == \"RACT\"){");
			out.println(" m_save_msg = 'Are you sure you want to Reactivate ? '");
			out.println(" }"); 
			
			
			//out.println("		if(validate_data()){"); 
			out.println("		if(confirm(m_save_msg)){ "); 
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"MK_MAS_Add_Sql_Property_Save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			//out.println("		}"); 
			//out.println("		else { "); 
			//out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			//out.println("		}");
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"MK_MAS_dashboard_add_sql_property';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"MK_MAS_dashboard_add_sql_property';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_sub_charge\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Dashboard - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Dashboard - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println("document.Form1.TXT_PAYEE_CODE.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_PAYEE_CODE.disabled=false;"); 
			out.println("document.Form1.TXT_SUB_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_PAYEE_ADD1.disabled=true;"); 
			out.println("document.Form1.TXT_PAYEE_ADD2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_NO.disabled=true;");
			out.println("document.Form1.TXT_WHT.disabled=true;");
			out.println("document.Form1.TXT_PAYEE_NAME.disabled=true;");
			
			
			out.println("}"); 
			
			
			
			
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
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
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		assign_help_dasge();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		assign_help_city_code();"); 
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
			out.println(""); 
			
			
			
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
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
			
			
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_view_TXT_SUB_TYPE_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_SUB_TYPE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			/**********************************/
			
			out.println("	function getInfo(){ ");
			out.println("	alert(\"working\"); ");
			out.println("	document.getElementById(\"container\").style.display = \"none\";   ");
			out.println("} ");
			
			/*out.println("function ViewChart(){");
			out.println("kkkkkkkkkkkkkkkkkkkkkkkkk");
			out.println("if(document.Form1.TXT_TYPE.value == \"AREA2D\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Area2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("	popupwin=window.open(m_url,'displayWindow1','left=10,top=110,width=975,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("} ");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_Bar2D\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Bar2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("} ");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_Candlestick\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Candlestick.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_Column2D\"){");     
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Column2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_Column3D\"){    "); 
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Column3D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_Doughnut2D\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Doughnut2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_Funnel\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Funnel.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("	}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_Gantt\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Gantt.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("}");
			
			out.println("	else if(document.Form1.TXT_TYPE.value == \"FCF_Line\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Line.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("	}");
			
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_Pie2D\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Pie2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_Pie3D\"){    "); 
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_Pie3D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=CHART2\";");
			out.println("}");
			
			//Multi Dimensions Grapsh-----------------------------------------------------------------------------------
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_MSArea2D\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_MSArea2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("	}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_MSBar2D\"){ ");    
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_MSBar2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("	}");
			
			out.println("	else if(document.Form1.TXT_TYPE.value == \"FCF_MSColumn2D\"){    "); 
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_MSColumn2D.swf\", myChartId, 600, 500);");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("	}");
			
			out.println("	else if(document.Form1.TXT_TYPE.value == \"FCF_MSColumn2DLineDY\"){   ");  
			out.println("		var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_MSColumn2DLineDY.swf\", myChartId, 600, 500);");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("	}");
			
			out.println("	else if(document.Form1.TXT_TYPE.value == \"FCF_MSColumn3D\"){     ");
			out.println("		var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_MSColumn3D.swf\", myChartId, 600, 500);");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("	}");
			
			out.println("	else if(document.Form1.TXT_TYPE.value == \"FCF_MSColumn3DLineDY\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_MSColumn3DLineDY.swf\", myChartId, 600, 500);");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("	}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_MSLine\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_MSLine.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("	}");
			
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_StackedArea2D\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_StackedArea2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("	}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_StackedBar2D\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_StackedBar2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_StackedColumn2D\"){     ");
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_StackedColumn2D.swf\", myChartId, 600, 500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("}");
			
			out.println("else if(document.Form1.TXT_TYPE.value == \"FCF_StackedColumn3D\"){    "); 
			out.println("	var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/CLC/FusionCharts/Charts/FCF_StackedColumn3D.swf\", myChartId, 600,500);");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?chksql=MULTI_CHARTS\";");
			out.println("}");
			*/
			
			
			
			// 	myChart.setDataURL(""+m_html_client_url+"/FusionCharts/Data.xml
			//out.println("	myChart.setDataURL(m_url);");
			//out.println("	myChart.render(\"chartdiv\");");
			//out.println("}");
			/************************************/
			/**************************************************************************************************/
			/*rs = stmt.executeQuery(
				//System.out.println(
				" SELECT DASH_ID,GRAPH_SWF_LOCATION,GRAPH_HEIGHT,GRAPH_WIDHT,DRAW_EXC_SECTION "+
				" FROM  "+m_schema_name+".DH_DASH_GRAPH_MAIN A,"+
				"       "+m_schema_name+".DH_REF_GRAPH_TYPES B "+
				" WHERE  ACTIVE_STATUS='Y' "+
				" AND    A.GRAPH_TYPE_ID = B.GRAPH_TYPE_ID ");
			
			while(rs.next()){
				
				String m_graph_swf_location = rs.getString(2);
				double m_graph_height = rs.getDouble(3);
				double m_graph_widht = rs.getDouble(3);
				
				if( rs.getString(5) != null && ( rs.getString(5).equals("MULTI_CHARTS") || rs.getString(5).equals("CHART2") )){
					
					out.println("function "+rs.getString(1)+"(){");
					//out.println("var myChart = new FusionCharts(\""+m_graph_swf_location+"\", \"myChartId\", \""+m_graph_height+"\", \""+m_graph_widht+"\");");
					out.println("var myChart = new FusionCharts(\""+m_html_client_url+"/newdash/"+m_graph_swf_location+",myChartId,'"+m_graph_height+"','"+m_graph_widht+"'\" );");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?dash_id="+rs.getString(1)+"\";");//LAKDL_MK_MAS_dash
						//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_dash\";");
					//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"PW_MK_sql_validate_duplication?chksql=dash_get_sql&dash_id=\"+document.Form1.SUB_SEC_NUM.value;	");
					out.println("window.open(m_url);");	
					out.println("myChart.setDataURL(m_url);");
					out.println("myChart.render(\"div_chart_"+rs.getString(1)+"\");");
					out.println("}");
				}else if(  rs.getString(5)!=null  && rs.getString(5).equals("CHART3") ){
					
					out.println("function "+rs.getString(1)+"(){");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?dash_id="+rs.getString(1)+"\";");
					
					//out.println("window.open(m_url);");	
					//out.println("alert('HI')");	
					out.println("loadXMLDoc(\"div_chart_"+rs.getString(1)+"\",m_url,true);");
					out.println("}");
				}else if(  rs.getString(5)!=null  && rs.getString(5).equals("RSS") ){
					
					
					
					out.println("function "+rs.getString(1)+"(){");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?dash_id="+rs.getString(1)+"\";");
					//out.println("window.open(m_url);");	
					out.println("loadXMLDoc(\"div_chart_"+rs.getString(1)+"\",m_url,true);");
					out.println("}");
				}else if(  rs.getString(5)!=null  && rs.getString(5).equals("CSE") ){
					
					
					
					out.println("function "+rs.getString(1)+"(){");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_FA_CHART_xml_data_Test1?dash_id="+rs.getString(1)+"\";");
					//out.println("window.open(m_url);");	
					out.println("loadXMLDoc(\"div_chart_"+rs.getString(1)+"\",m_url,true);");
					out.println("}");
				}				
				
				
				
				
			}*/
			
			/****************************************************************************************************/
			
			
			/***************************************************************************/
			out.println("function w_load_graps(){");
			//overdue();
			rs = stmt.executeQuery(" SELECT DASH_ID FROM "+m_schema_name+".DH_DASH_GRAPH_MAIN A WHERE ACTIVE_STATUS='Y' ");
			while(rs.next()){
				
				out.println(rs.getString(1)+"();");
				out.println("var intervalID = setInterval('"+rs.getString(1)+"()',90000)");
			}
			
			out.println("}");
			/*
			out.println("function allowDrop(ev)");
			out.println("{");
			out.println("	ev.preventDefault();");
			out.println("}");
			
			out.println("function drag(ev)");
			out.println("{");
			out.println("	ev.dataTransfer.setData(\"DIV\",ev.target.id);");
			out.println("}");
			
			out.println("function drop(ev)");
			out.println("{");
			out.println("	ev.preventDefault();");
			out.println("	var data=ev.dataTransfer.getData(\"DIV\");");
			out.println("	ev.target.appendChild(document.getElementById(data));");
			out.println("}");*/
			/***************************************/
			out.println("function ViewChart(){");
			out.println(" 	var myChart = new FusionCharts(\""+m_html_client_url+"/FusionCharts/Charts/FCF_Column3D.swf\", \"myChartId\", \"600\", \"500\")");
			out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CHART_xml_data?chksql=CHART1\" ;");
			//out.println(" 	myChart.setDataURL(\""+m_html_client_url+"/FusionCharts/Data.xml\");");
			out.println(" 	myChart.setDataURL(m_url);");
			out.println(" 	myChart.render(\"chartdiv\");");
			out.println("}"); 
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"ViewChart();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='row_count' value=0>");
			out.println("<INPUT TYPE='hidden' NAME='HID_APPNO' VALUE="+TXT_APPLICATION_NO+" >");
			out.println("<INPUT TYPE='hidden' NAME='HID_APPLICANT_CODE' VALUE="+TXT_APPLICANT_CODE+"> ");
			out.println("<INPUT TYPE='hidden' NAME='HID_APPLICANT_NAME' VALUE="+TXT_APPLICANT_NAME+"> ");
			/************************/
			
			
			
			out.println("<TABLE >	");
			out.println("<TR bgcolor='#C8C8C8'>	");
			out.println("<TD colspan='1'>");
			out.println("<TD><b>Actual Month</b></TD>");
			out.println("<TD><b>Actual Ytd</b></TD>");
			out.println("<TD><b>Target Month</b></TD>");
			out.println("<TD><b>Target Ytd</b></TD>");
			out.println("<TD><b>Varience Month</b></TD>");
			out.println("<TD><b>Varience Ytd</b></TD>");
			out.println("<TD><b>Last Month Actual</b></TD>");
			out.println("<TD><b>Varience %</b></TD>");
			out.println("</TR>	");
			out.println("<TR>	");
			
			String m_current_month="";
			String m_lastmon      ="";
			String first_dayte_of_month="";
			String last_date_of_month ="";
			String sys_date="";
			String first_month_of_year="";
			String new_format_last_month="";
			String new_format_last_month_YYY="";
			String finacial_year="";
			int varience_month=0;
			String previos_month_first_date="";
			String previos_month_last_date  ="";
			int varience=0;
			double val_varience_month=0;
			double val_varience=0;
			int bus_varius_month=0;
			int bus_varience=0;
			double average_actual_month=0;
			double average_actual_ytd=0;
			double average_actual_targetmonth=0;
			double average_VARIENCE=0;
			double average_lastmonth=0;
			double average_varience=0;
			double average_VARIENCE_ytd=0;
			
			rs2 = stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'MONTH'),"+//1
				" TO_CHAR(ADD_MONTHS(SYSDATE,-1),'MONTH'),"+//2
				" TO_CHAR(TRUNC(SYSDATE,'MM'),'DD-MM-YYYY'),"+//3
				" TO_CHAR(LAST_DAY(SYSDATE),'DD-MM-YYYY'),"+//4
				" TO_CHAR(SYSDATE,'dd-mm-YYYY'),"+//5
				" TO_CHAR(TRUNC(SYSDATE,'YEAR'),'DD-MM-YYYY') AS FIRST_MONTH_OF_YEAR, "+//6
				" TO_CHAR(LAST_DAY(SYSDATE),'MON'), "+//7
				" TO_CHAR(LAST_DAY(SYSDATE),'YYYY'), "+//8
				" TO_CHAR(LAST_DAY(ADD_MONTHS (sysdate, -11)), 'DD-MM-YYYY')     AS PREV_YEAR, "+//9
				" TO_CHAR(trunc(ADD_MONTHS(SYSDATE,-1),'MON'),'dd-mm-yyyy') AS PREVIOS_MONTH_FIRST_DATE, "+//11
				" TO_CHAR(trunc(SYSDATE, 'MM')-1,'DD-MM-YYYY') AS PREVIOS_MONTH_LAST_DATE "+//11
				" FROM   DUAL "+
				" ");
			if(rs2.next()){
				m_current_month        =rs2.getString(1);
				m_lastmon              =rs2.getString(2);
				first_dayte_of_month   =rs2.getString(3);
				last_date_of_month     =rs2.getString(4);
				sys_date               =rs2.getString(5);
				first_month_of_year    =rs2.getString(6);
				new_format_last_month  =rs2.getString(7);
				new_format_last_month_YYY=rs2.getString(8);
				finacial_year          =rs2.getString(9);
				previos_month_first_date=rs2.getString(10);
				previos_month_last_date  =rs2.getString(11);
				//}
				//rs2.close();
				out.println("<TD bgcolor='00CCFF'><b>1)Lendng (Mn)</b></TD>");
				out.println("<TD bgcolor='00CCFF'><b>"+m_current_month+"</b></TD>");
				out.println("<TD bgcolor='00CCFF'>&nbsp</TD>");
				out.println("<TD bgcolor='00CCFF'><b>"+m_current_month+"</b></TD>");
				
				out.println("<TD bgcolor='00CCFF'>&nbsp</TD>");
				out.println("<TD bgcolor='00CCFF'>&nbsp</TD>");
				out.println("<TD bgcolor='00CCFF'>&nbsp</TD>");
				out.println("<TD bgcolor='00CCFF'><b>"+m_lastmon+"</b></TD>");
				//}
				out.println("<TD bgcolor='00CCFF'>&nbsp</TD>");
				out.println("</TR>	");
				//=================================Cases-ACTUAL MONTH==========================================
				//out.println("last_date_of_month"+last_date_of_month);
				out.println("<TR>	");
				rs=stmt.executeQuery
					//out.print
					("  SELECT COUNT(A.APPLICATION_NO) "+
					" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY A  ,"+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_DET B  "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+
					" AND A.ENT_USER='"+m_username+"' "+
					" AND B.ENT_USER='"+m_username+"' "+
					" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+first_dayte_of_month+"','DD-MM-YYYY') "+
					" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+last_date_of_month+"','DD-MM-YYYY') "+
					" ");
				
				if(rs.next()){
					out.println("<TD><b>No of Cases</b></TD>");
					//out.println("<TD bgcolor='00CCFF'><b>1)Lendng (Mn)</b></TD>");
					out.println("<TD BGCOLOR='FFCCFF'><b>"+rs.getInt(1)+"</b></TD>");
					
					//============================END ACTUAL MONTH============================================
					//============================Cases-ACTUAL YTD==================================================
					//out.println("sys_date**************"+first_month_of_year);
					rs4=stmt4.executeQuery
						//out.print
						("  SELECT COUNT(A.APPLICATION_NO) "+
						" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY A  ,"+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_DET B  "+ 
						" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+
						" AND A.ENT_USER='"+m_username+"' "+
						" AND B.ENT_USER='"+m_username+"' "+
						" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+finacial_year+"','DD-MM-YYYY') "+
						" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+sys_date+"','DD-MM-YYYY') "+
						" ");
					
					if(rs4.next()){
						out.println("<TD BGCOLOR='FFCCFF'><b>"+rs4.getInt(1)+"</b></TD>");
						
						//=================================END ACTUAL YTD===========================================
						
						//===============================Cases-TARGET MONTH===========================================
						rs3=stmt3.executeQuery
							//out.print
							("   SELECT  	 NVL(SUM(A.TARGET_CASES),0), NVL(SUM(A.TARGET_VALUE),0) "+
							" FROM  "+m_schema_name+".CO_MAS_LEND_TARGETS A  "+
							" WHERE  A.PERIOD_MONTH = '"+new_format_last_month+"' AND A.PERIOD_YEAR = '"+new_format_last_month_YYY+"' "+
							// " AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('01-03-2013','DD-MM-YYYY') "+
							//" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+last_date_of_month+"','DD-MM-YYYY') "+
							" ");
						
						if(rs3.next()){
							
							
							out.println("<TD BGCOLOR='FFCCFF'><b>"+rs3.getInt(1)+"</b></TD>");
							
							
							//===========================END TARGET MONTH========================================
							
							out.println("<TD BGCOLOR='FFCCFF'>no</TD>");
							
							//===================================Cases-VATIENCE MONTH============================
							//out.println("-------------------"+rs.getInt(1));
							if(rs3.getInt(1)==0){
								
							}else{
								varience_month=(rs.getInt(1)-rs3.getInt(1))/rs3.getInt(1);
							}
							out.println("<TD BGCOLOR='FFCCFF'><b>"+varience_month+" %</b></TD>");
							//===================================end Cases-VATIENCE MONTH========================
							
							
							
							out.println("<TD BGCOLOR='FFCCFF'>no</TD>");
							
							//====================================Cases-LAST_MONTH_ACTUAL=======================
							rs5=stmt5.executeQuery
								//out.print
								("  SELECT COUNT(A.APPLICATION_NO) "+
								" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY A  ,"+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_DET B  "+ 
								" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+
								" AND A.ENT_USER='"+m_username+"' "+
								" AND B.ENT_USER='"+m_username+"' "+
								" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+previos_month_first_date+"','DD-MM-YYYY') "+
								" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+previos_month_last_date+"','DD-MM-YYYY') "+
								" ");
							
							if(rs5.next()){
								
								out.println("<TD BGCOLOR='FFCCFF'><b>"+rs5.getInt(1)+"</b></TD>");
								//================================end Cases-LAST_MONTH_ACTUAL=======================
								varience=(rs.getInt(1)-rs5.getInt(1))/rs5.getInt(1);
								out.println("<TD BGCOLOR='FFCCFF'><b>"+varience+" %</b></TD>");
								
								
								//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
								out.println("</TR>	");
								out.println("<TR>	");
								out.println("<TD><b>Value</b></TD>");
								//=================================VALUE-VALUE======================================
								rs1=stmt1.executeQuery
									//out.print
									("  SELECT NVL(SUM(A.facility_amount),0) "+
									" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY A  ,"+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_DET B  "+ 
									" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+
									" AND A.ENT_USER='"+m_username+"' "+
									" AND B.ENT_USER='"+m_username+"' "+
									" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+first_dayte_of_month+"','DD-MM-YYYY') "+
									" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+last_date_of_month+"','DD-MM-YYYY') "+
									" ");
								
								if(rs1.next()){
									out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(rs1.getDouble(1))+"</b></TD>");
									
									//===========================END VALUE===================================================
									
									//=============================VALUE-YTD==========================================================
									rs6=stmt6.executeQuery
										//out.print
										("  SELECT NVL(SUM(A.facility_amount),0) "+
										" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY A  ,"+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_DET B  "+ 
										" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+
										" AND A.ENT_USER='"+m_username+"' "+
										" AND B.ENT_USER='"+m_username+"' "+
										" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+finacial_year+"','DD-MM-YYYY') "+
										" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+sys_date+"','DD-MM-YYYY') "+
										" ");
									
									if(rs6.next()){
										out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(rs6.getDouble(1))+"</b></TD>");
										
										//=============================END YTD==========================================================
										//===============================VALUE-TRAGET MONTH===================================================
										//rs=stmt.executeQuery
										//out.print
										rs7=stmt7.executeQuery
											//out.print
											("   SELECT  	 NVL(SUM(A.TARGET_CASES),0), NVL(SUM(A.TARGET_VALUE),0) "+
											" FROM  "+m_schema_name+".CO_MAS_LEND_TARGETS A  "+
											" WHERE  A.PERIOD_MONTH = '"+new_format_last_month+"' AND A.PERIOD_YEAR = '"+new_format_last_month_YYY+"' "+
											// " AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('01-03-2013','DD-MM-YYYY') "+
											//" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+last_date_of_month+"','DD-MM-YYYY') "+
											" ");
										
										
										
										if(rs7.next()){
											out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(rs7.getDouble(2))+"</b></TD>");
											
											//================================END TRAGET MONTH===============================================
											
											
											//================================VALUE-TRAGET YTD==================================
											out.println("<TD BGCOLOR='FFCCFF'><b>no</b></TD>");
											//=================================END VALUE-TRAGET YTD=================================
											
											//=================================VALUE-VARIENCEMONTH============================================
											if(rs7.getDouble(2)!=0){
												//out.println(rs1.getDouble(1)+"**********"+rs3.getDouble(2));
												val_varience_month=(rs1.getDouble(1)-rs7.getDouble(2))/rs7.getDouble(2);
											}else{
											}
											out.println("<TD BGCOLOR='FFCCFF'><b>"+val_varience_month+" % <img src= \""+m_html_client_url+"/Home/img/sad.png\"></b></TD>");
											//=================================END-VALUE-VARIENCEMONTH ============
											
											
											//===============================VALUE-VARIECE-YTD=========================================
											out.println("<TD BGCOLOR='FFCCFF'><b>no</b></TD>");
											//=====================================END YTD====================================
											
											//==================================VALUE -LAST_MONTH ACTUAL=======================================
											rs8=stmt8.executeQuery
												//out.print
												("  SELECT NVL(SUM(A.facility_amount),0) "+
												" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY A  ,"+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_DET B  "+ 
												" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+
												" AND A.ENT_USER='"+m_username+"' "+
												" AND B.ENT_USER='"+m_username+"' "+
												" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+previos_month_first_date+"','DD-MM-YYYY') "+
												" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+previos_month_last_date+"','DD-MM-YYYY') "+
												" ");
											
											
											
											if(rs8.next()){
												out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(rs8.getDouble(1))+"</b></TD>");
												
												//=====================================VALUE-VARIENCE=============================================
												val_varience=(rs1.getDouble(1)-rs8.getDouble(1))/rs8.getDouble(1);
												out.println("<TD BGCOLOR='FFCCFF'><b>"+val_varience+" %</b></TD>");
												//========================================END-VARIENCE============================================
												
												
												//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
												out.println("</TR>	");
												out.println("<TR>	");
												out.println("<TD><b>Direct Business</b></TD>");
												//==============================Business-actual month============================================
												rs9=stmt9.executeQuery
													("  SELECT count(A.APPLICATION_NO) "+
													" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+ 
													" WHERE LEAD_SOURCE_CATEGORY NOT IN('EXISTING')  "+
													" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+first_dayte_of_month+"','DD-MM-YYYY') "+
													" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+last_date_of_month+"','DD-MM-YYYY') "+
													
													" ");
												
												if(rs9.next()){
													out.println("<TD BGCOLOR='FFCCFF'><b>"+rs9.getInt(1)+" %</b></TD>");
													
													//==================================Business-actual YTD==========================================
													rs10=stmt10.executeQuery
														("  SELECT count(A.APPLICATION_NO) "+
														" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+ 
														" WHERE LEAD_SOURCE_CATEGORY NOT IN('EXISTING')  "+
														" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+finacial_year+"','DD-MM-YYYY') "+
														" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+sys_date+"','DD-MM-YYYY') "+
														
														
														" ");
													
													if(rs10.next()){
														out.println("<TD BGCOLOR='FFCCFF'><b>"+rs10.getInt(1)+" %</b></TD>");
														//==================================END-Business-actual YTD==========================================
														
														//==================================Business-TARGET MONTH=========================================
														rs11=stmt11.executeQuery
															("  SELECT count(A.APPLICATION_NO) "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+ 
															" WHERE LEAD_SOURCE_CATEGORY NOT IN('EXISTING')  "+
															" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+first_dayte_of_month+"','DD-MM-YYYY') "+
															" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+last_date_of_month+"','DD-MM-YYYY') "+
															
															" ");
														if(rs11.next()){
															out.println("<TD BGCOLOR='FFCCFF'><b>"+rs11.getInt(1)+" %</b></TD>");
															//==================================END-Business-TARGET MONTH=========================================
															
															//===============================Business-TARGET MONTH====================
															out.println("<TD BGCOLOR='FFCCFF'><b>no</b></TD>");
															//===============================END-Business-TARGET MONTH====================
															
															//===================================Business-varience month====================================
															if(rs11.getInt(1)!=0){
																bus_varius_month=(rs9.getInt(1)-rs11.getInt(1))/rs11.getInt(1);
															}else{
															}
															out.println("<TD BGCOLOR='FFCCFF'><b>"+bus_varius_month+" %</b></TD>");
															
															//===================================end-Business-varience month====================================
															
															//=====================================Business-varience ytd==================================================
															out.println("<TD BGCOLOR='FFCCFF'><b>no</b></TD>");
															//=====================================end-Business-varience ytd==================================================
															//===========================================Business-last month actual===========================================
															rs12=stmt12.executeQuery
																("  SELECT count(A.APPLICATION_NO) "+
																" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+ 
																" WHERE LEAD_SOURCE_CATEGORY NOT IN('EXISTING')  "+
																" AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+previos_month_first_date+"','DD-MM-YYYY') "+
																" AND TRUNC(A.ACTIVATED_DATE) <=  TO_DATE('"+previos_month_last_date+"','DD-MM-YYYY') "+
																
																
																" ");
															if(rs12.next()){
																out.println("<TD BGCOLOR='FFCCFF'><b>"+rs12.getInt(1)+" %</b></TD>");
																//===========================================end-Business-last month actual===========================================
																
																//============================================Business-varience===============================
																bus_varience=(rs9.getInt(1)-rs12.getInt(1))/rs12.getInt(1);
																out.println("<TD BGCOLOR='FFCCFF'><b>"+bus_varience+" %</b></TD>");
																//============================================end-Business-varience===============================
																
																
																//==============================end-Business-actual month============================================
																//-----------------------------------------------------------------------------------------------------------------------
																out.println("</TR>	");
																out.println("<TR>	");
																
																out.println("<TD><b>Average Contact Value</b></TD>");
																//out.print(rs1.getDouble(1)+"-------"+rs.getInt(1));
																if(rs.getInt(1) !=0){
																	average_actual_month=rs1.getDouble(1)/rs.getInt(1)*1000000;
																}else{}
																out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(average_actual_month)+" </b></TD>");
																
																//=======================================Average Contact ACTUAL YTD===================================================
																average_actual_ytd=rs6.getDouble(1)/rs4.getInt(1)*1000000;
																out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(average_actual_ytd)+" </b></TD>");
																//=======================================END Average Contact ACTUAL YTD===================================================
																
																
																//=======================================Average Target Month===================================================
																if(rs3.getInt(1)!=0){
																	average_actual_targetmonth=rs7.getDouble(2)/rs3.getInt(1)*1000000;
																}else{
																}
																out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(average_actual_targetmonth)+" </b></TD>");
																//out.println("<TD ><b>no</b></TD>");
																//=======================================END Average Target Month===================================================
																
																//=======================================Average Target YTD===================================================
																if(rs3.getInt(1)!=0){
																	average_actual_targetmonth=rs7.getDouble(2)/rs3.getInt(1)*1000000;
																}else{
																}
																out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(average_actual_targetmonth)+" </b></TD>");
																//out.println("<TD ><b>no</b></TD>");
																//=======================================END Average Target YTD===================================================
																
																//=======================================Average VARIECE===================================================
																if(average_actual_targetmonth !=0){
																	average_VARIENCE=(average_actual_month-average_actual_targetmonth)/average_actual_targetmonth;
																}else{
																}
																out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(average_VARIENCE)+" % </b></TD>");
																//out.println("<TD ><b>no</b></TD>");
																//=======================================END Average VARIECE===================================================
																
																
																//=======================================Average VARIECE YTD===================================================
																if(average_actual_targetmonth !=0){
																	average_VARIENCE_ytd=(average_actual_month-average_actual_targetmonth)/average_actual_targetmonth;
																}else{
																}
																out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(average_VARIENCE_ytd)+" </b></TD>");
																//out.println("<TD ><b>no</b></TD>");
																//=======================================END Average VARIECE YTD===================================================
																
																
																//=======================================Average LAST MONTH===================================================
																average_lastmonth=rs8.getDouble(1)/rs5.getInt(1)*1000000;
																out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(average_lastmonth)+" </b></TD>");
																//out.println("<TD ><b>no</b></TD>");
																//=======================================END Average LAST MONTH===================================================
																
																
																//=======================================Average varience===================================================
																average_varience=(average_actual_month-average_lastmonth)/average_lastmonth;
																out.println("<TD BGCOLOR='FFCCFF'><b>"+nf.format(average_varience)+" %</b></TD>");
																//out.println("<TD ><b>no</b></TD>");
																//=======================================END Average varience===================================================
																
																
															}//Business-last month actual
														}//Business-TARGET MONTH		
													}//Business-actual YTD		
												}//Business-actual month
												
											}//end MONTH_ACTUAL
										}//end val
									}//end ytd
								}//end target month
								
								
							}//END Cases-LAST_MONTH_ACTUAL
							
						}//END_OF NO_OF_CASE
					}//END YTD
				} //END TRAGET MONTH 
				
				
				out.println("</TR>	");
				out.println("</TABLE>	");
				
				out.println("<br>"); 
				out.println("<br>"); 
				out.println("<br>"); 
				/****************************/
				out.println("<table>"); 
				out.println("	<thead>"); 
				out.println("	<tr bgcolor='00CC00'>"); 
				out.println("	<th class=2) Collections (Mn)-cell>2) Collections (Mn)</th>"); 
				out.println("<th class=\"October-cell\">October</th>"); 
				out.println("<th class=\"-cell\">&nbsp</th>"); 
				out.println("<th class=\"October-cell\">October</th>"); 
				out.println("<th class=\"-cell\">&nbsp</th>"); 
				out.println("<th class=\"-cell\">&nbsp</th>"); 
				out.println("<th class=\"-cell\">&nbsp</th>"); 
				out.println("<th class=\"September-cell\">September</th>"); 
				out.println("<th class=\"-cell\">&nbsp</th>"); 
				out.println("</tr>"); 
				out.println("</thead>"); 
				out.println("<tbody>"); 
				out.println("<tr class=\"firstRow\">"); 
				out.println("<td class=2) Collections (Mn)-cell>Rentals (with excess)</td>"); 
				out.println("<td class=October-cell>27.76</td>"); 
				out.println("<td class=\"-cell\">217.77</td>"); 
				out.println("<td class=\"October-cell\">35.18</td>"); 
				out.println("<td class=\"-cell\">286.81</td>"); 
				out.println("<td class=\"-cell\">-21%</td>"); 
				out.println("<td class=\"-cell\">-24%</td>"); 
				out.println("<td class=\"September-cell\">25.26</td>"); 
				out.println("<td class=\"-cell\">10%</td>"); 
				out.println("</tr>"); 
				out.println("<tr class=\"lastRow\">"); 
				out.println("<td class=2) Collections (Mn)-cell>Arrears</td>"); 
				out.println("<td class=\"October-cell\">11.19</td>"); 
				out.println("<td class=\"-cell\">86.28</td>"); 
				out.println("<td class=\"October-cell\">15.67</td>"); 
				out.println("<td class=\"-cell\">148.34</td>"); 
				out.println("<td class=\"-cell\">-29%</td>"); 
				out.println("<td class=\"-cell\">-42%</td>"); 
				out.println("<td class=\"September-cell\">9.48</td>"); 
				out.println("<td class=\"-cell\">18%</td>"); 
				out.println("</tr>"); 
				out.println("</tbody>"); 
				out.println("</table>"); 
				
				
				/*************************/ 
				out.println("<br>"); 
				out.println("<br>"); 
				out.println("<br>"); 
				
				out.println("<table>"); 
				out.println("<thead>"); 
				out.println("<tr bgcolor='CC0000'>"); 
				out.println("<th class=3) Arrears (as at 31 Oct 2013)-cell>3) Arrears (as at 31 Oct 2013)</th>"); 
				out.println("<th class=\"Total-cell\">Total</th>"); 
				out.println("<th class=\"< 1-cell\">< 1</th>"); 
				out.println("<th class=\"0-1-cell\">0-1</th>"); 
				out.println("<th class=\"1-2-cell\">1-2</th>"); 
				out.println("<th class=\"2-3-cell\">2-3</th>"); 
				out.println("<th class=\"3-4-cell\">3-4</th>"); 
				out.println("<th class=\"4-5-cell\">4-5</th>"); 
				out.println("<th class=\"5-6-cell\">5-6</th>"); 
				out.println("<th class=\"> 6-cell\">> 6</th>"); 
				out.println("<th class=\"Mature-cell\">Mature</th>"); 
				out.println("</tr>"); 
				out.println("</thead>"); 
				out.println("<tbody>"); 
				out.println("<tr class=\"firstRow\">"); 
				out.println("<td class=3) Arrears (as at 31 Oct 2013)-cell>No. of cases</td>"); 
				out.println("<td class=\"Total-cell\">1557</td>"); 
				out.println("<td class=< 1-cell>586</td>"); 
				out.println("<td class=\"0-1-cell\">559</td>"); 
				out.println("<td class=\"1-2-cell\">198</td>"); 
				out.println("<td class=\"2-3-cell\">59</td>"); 
				out.println("<td class=\"3-4-cell\">27</td>"); 
				out.println("<td class=\"4-5-cell\">15</td>"); 
				out.println("<td class=\"5-6-cell\">13</td>"); 
				out.println("<td class=\"> 6-cell\">43</td>"); 
				out.println("<td class=\"Mature-cell\">57</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td class=3) Arrears (as at 31 Oct 2013)-cell>Value</td>"); 
				out.println("<td class=\"Total-cell\">17,275,091 </td>"); 
				out.println("<td class=< 1-cell>709.947 </td>"); 
				out.println("<td class=\"0-1-cell\">4,309,980 </td>"); 
				out.println("<td class=\"1-2-cell\">3,228,483 </td>"); 
				out.println("<td class=\"2-3-cell\">1,496,387 </td>"); 
				out.println("<td class=\"3-4-cell\">966.625 </td>"); 
				out.println("<td class=\"4-5-cell\">597.537 </td>"); 
				out.println("<td class=\"5-6-cell\">684.696 </td>"); 
				out.println("<td class=\"> 6-cell\">3,887,624 </td>"); 
				out.println("<td class=\"Mature-cell\">1,393,796 </td>"); 
				out.println("</tr>"); 
				out.println("<tr bgcolor='339900'>"); 
				out.println("<td class=3) Arrears (as at 31 Oct 2013)-cell>Actual (based on Value)</td>"); 
				out.println("<td class=\"Total-cell\">100%</td>"); 
				out.println("<td class=< 1-cell>4%</td>"); 
				out.println("<td class=\"0-1-cell\">25%</td>"); 
				out.println("<td class=\"1-2-cell\">19%</td>"); 
				out.println("<td class=\"2-3-cell\">9%</td>"); 
				out.println("<td class=\"3-4-cell\">6%</td>"); 
				out.println("<td class=\"4-5-cell\">3%</td>"); 
				out.println("<td class=\"5-6-cell\">4%</td>"); 
				out.println("<td class=\"> 6-cell\">23%</td>"); 
				out.println("<td class=\"Mature-cell\">8%</td>"); 
				out.println("</tr>"); 
				out.println("<tr class=\"lastRow\">"); 
				out.println("<td class=3) Arrears (as at 31 Oct 2013)-cell>Target</td>"); 
				out.println("<td class=\"Total-cell\">100%</td>"); 
				out.println("<td class=< 1-cell>10%</td>"); 
				out.println("<td class=\"0-1-cell\">30%</td>"); 
				out.println("<td class=\"1-2-cell\">30%</td>"); 
				out.println("<td class=\"2-3-cell\">20%</td>"); 
				out.println("<td class=\"3-4-cell\">10%</td>"); 
				out.println("<td class=\"4-5-cell\">0%</td>"); 
				out.println("<td class=\"5-6-cell\">0%</td>"); 
				out.println("<td class=\"> 6-cell\">0%</td>"); 
				out.println("<td class=\"Mature-cell\">0%</td>"); 
				out.println("</tr>"); 
				out.println("</tbody>"); 
				out.println("</table>"); 
				/*************************/
				out.println("<br>"); 
				out.println("<br>"); 
				out.println("<br>"); 
				out.println("<table>"); 
				out.println("	<thead>"); 
				out.println("	<tr>"); 
				out.println("	<th class=\"Arrears (as at 30 Sep 2013)-cell\">Arrears (as at 30 Sep 2013)</th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	<th class=\"-cell\"></th>"); 
				out.println("	</tr>"); 
				out.println("	</thead>"); 
				out.println("	<tbody>"); 
				out.println("	<tr class=\"firstRow\">"); 
				out.println("	<td class=\"Arrears (as at 30 Sep 2013)-cell\">No. of cases</td>"); 
				out.println("	<td class=\"-cell\">1539</td>"); 
				out.println("	<td class=\"-cell\">490</td>"); 
				out.println("	<td class=\"-cell\">625</td>"); 
				out.println("	<td class=\"-cell\">174</td>"); 
				out.println("	<td class=\"-cell\">84</td>"); 
				out.println("	<td class=\"-cell\">23</td>"); 
				out.println("	<td class=\"-cell\">22</td>"); 
				out.println("	<td class=\"-cell\">12</td>"); 
				out.println("	<td class=\"-cell\">42</td>"); 
				out.println("	<td class=\"-cell\">68</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td class=\"Arrears (as at 30 Sep 2013)-cell\">Value</td>"); 
				out.println("	<td class=\"-cell\">17,728,672 </td>"); 
				out.println("<td class=\"-cell\">661.351 </td>"); 
				out.println("<td class=\"-cell\">4,824,054 </td>"); 
				out.println("<td class=\"-cell\">2,835,663 </td>"); 
				out.println("<td class=\"-cell\">2,008,807 </td>"); 
				out.println("<td class=\"-cell\">874.485 </td>"); 
				out.println("<td class=\"-cell\">845.078 </td>"); 
				out.println("<td class=\"-cell\">642.717 </td>"); 
				out.println("<td class=\"-cell\">3,469,722 </td>"); 
				out.println("<td class=\"-cell\">1,566,781 </td>"); 
				out.println("</tr>"); 
				out.println("<tr bgcolor='339900'>"); 
				out.println("<td class=\"Arrears (as at 30 Sep 2013)-cell\">Actual (based on Value)</td>"); 
				out.println("<td class=\"-cell\">100%</td>"); 
				out.println("<td class=\"-cell\">4%</td>"); 
				out.println("<td class=\"-cell\">27%</td>"); 
				out.println("<td class=\"-cell\">16%</td>"); 
				out.println("<td class=\"-cell\">11%</td>"); 
				out.println("<td class=\"-cell\">5%</td>"); 
				out.println("<td class=\"-cell\">5%</td>"); 
				out.println("<td class=\"-cell\">4%</td>"); 
				out.println("<td class=\"-cell\">20%</td>"); 
				out.println("<td class=\"-cell\">9%</td>"); 
				out.println("</tr>"); 
				out.println("<tr class=\"lastRow\">"); 
				out.println("<td class=\"Arrears (as at 30 Sep 2013)-cell\">Target</td>"); 
				out.println("<td class=\"-cell\">100%</td>"); 
				out.println("<td class=\"-cell\">10%</td>"); 
				out.println("<td class=\"-cell\">30%</td>"); 
				out.println("<td class=\"-cell\">30%</td>"); 
				out.println("<td class=\"-cell\">20%</td>"); 
				out.println("<td class=\"-cell\">10%</td>"); 
				out.println("<td class=\"-cell\">0%</td>"); 
				out.println("<td class=\"-cell\">0%</td>"); 
				out.println("<td class=\"-cell\">0%</td>"); 
				out.println("<td class=\"-cell\">0%</td>"); 
				out.println("</tr>"); 
				out.println("</tbody>"); 
				out.println("</table>"); 
				/*******************************/
				out.println("<br>"); 
				out.println("<br>"); 
				out.println("<br>");
				out.println("<table>");
				out.println("<thead>");
				out.println("<tr >");
				out.println("	<th class=4 bgcolor='FF9933') Arrears (Current month)-cell>4 ) Arrears (Current month)</th>");
				out.println("<th class=\"17,370,000-cell\">17,370,000</th>");
				out.println("<th class=\"-cell\"></th>");
				out.println("<th class=\"Arrears (Last month)-cell\">Arrears (Last month)</th>");
				out.println("<th class=\"-cell\"></th>");
				out.println("<th class=\"18,037,589-cell\">18,037,589</th>");
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				out.println("<tr class=\"firstRow\">");
				out.println("<td class=\"4) Arrears (Current month)-cell\">Up to 4 months Target</td>");
				out.println("<td class=\"17,370,000-cell\">              100%</td>");
				out.println("	<td class=\"-cell\"></td>");
				out.println("<td class=\"Arrears (Last month)-cell\">Total up to 4 months</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"18,037,589-cell\">       62%</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=4) Arrears (Current month)-cell>Up to 4 months Actual</td>");
				out.println("<td class=\"17,370,000-cell\">               62%</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"Arrears (Last month)-cell\">Previous month</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"18,037,589-cell\">               61%</td>");
				out.println("</tr>");
				out.println("<tr class=\"lastRow\">");
				out.println("<td class=4) Arrears (Current month)-cell>Variance</td>");
				out.println("<td class=\"17,370,000-cell\">                      -38%</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"Arrears (Last month)-cell\">Variance</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"18,037,589-cell\">                1%</td>");
				out.println("</tr>");
				out.println("</tbody>");
				out.println("</table>");
				out.println("<br>"); 
				out.println("<br>"); 
				out.println("<br>"); 
				/*******************************/
				out.println("<table>");
				out.println("	<thead>");
				out.println("	<tr>");
				out.println("	<th class=5) Current month-cell>5) Current month</th>");
				out.println("<th class=\"-cell\"></th>");
				out.println("<th class=\"-cell\"></th>");
				out.println("<th class=                             7) Last month-cell>                             7) Last month</th>");
				out.println("<th class=\"-cell\"></th>");
				out.println("<th class=\"-cell\"></th>");
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				out.println("<tr class=\"firstRow\">");
				out.println("<td class=\"5) Current month-cell\">Gross rentals receivable (Portfolio)</td>");
				out.println("<td class=\"-cell\"> 880,187,362 </td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"                             7) Last month-cell\">     Gross rentals receivable (portfolio)</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"-cell\">823,698,350 </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"5) Current month-cell\">Arrears as a %</td>");
				out.println("<td class=\"-cell\">                               1.96%</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"                             7) Last month-cell\">     Arrears as a %</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"-cell\">                              2.19%</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"5) Current month-cell\">Target</td>");
				out.println("<td class=\"-cell\">                                        1.5%</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"                             7) Last month-cell\">     Target</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"-cell\">                                       1.5%</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"5) Current month-cell\"></td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"                             7) Last month-cell\"></td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("</tr>");
				out.println("<tr class=lastRow>");
				out.println("<td class=5) Current month-cell>Variance</td>");
				out.println("<td class=\"-cell\">                             -10.37%</td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"                             7) Last month-cell\"></td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("<td class=\"-cell\"></td>");
				out.println("</tr>");
			}
			out.println("</tbody>");
			out.println("</table>");
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
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