<%-- 
CREATED BY KANISHKA DILSHAN ON 16-09-2013
<%@page import="net.sf.json.*"%>
--%>

<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="oracle.jdbc.driver.*"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="pawning.connection.db"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<title>PopUp </title>
		<script  type="text/javascript"  src="report/jquery.min.js"></script>
		<script type="text/javascript"  src="report/jquery-ui.min.js"></script>
		<script type="text/javascript" src="report/jquery.tablesorter.js"></script> 
		
		<link rel="stylesheet" HREF="../css/report/style.css" type="text/css" />		
		<link rel="stylesheet" href="../css/report/blue/style.css" type="text/css" />
		<link rel="stylesheet" href="../css/report/redmond/jquery-ui.css" type="text/css" />
		<style type="text/css" media="screen">
			
		</style>
		<script type="text/javascript">
			
		
			// DIALOG Required Code
			var prntWindow = getParentWindowWithDialog(); //$(top)[0];
			var $dlg = prntWindow && prntWindow.$dialog;
			
			function getParentWindowWithDialog() {
				var p = window.parent;
				var previousParent = p;
				while (p != null) {
					if ($(p.document).find('#iframeDialog').length) return p;
					
					p = p.parent;
					
					if (previousParent == p) return null;
					
					// save previous parent
					
					previousParent = p;
				}
				return null;
			}
			
			function setWindowReturnValue(value) {
				if ($dlg) $dlg.returnValue = value;
				window.returnValue = value; // in case popup is called using showModalDialog
				
			}
			
			function getWindowReturnValue() {
				// in case popup is called using showModalDialog
				
				if (!$dlg && window.returnValue != null)
					return window.returnValue;
				
				return $dlg && $dlg.returnValue;
			}
			
			if ($dlg) window.dialogArguments = $dlg.dialogArguments;
			if ($dlg) window.close = function() { if ($dlg) $dlg.dialogWindow.dialog('close'); 
				
			};
			// END of dialog Required Code
			
			function closeMe() {
				//setWindowReturnValue('The Return Value. And Passed Dialog Arguments: ' + window.dialogArguments);
				/*var oMyObject = window.dialogArguments;
					var sFirstName = oMyObject.firstName;
					var sLastName = oMyObject.lastName;
				
				setWindowReturnValue('The Return Value. And Passed Dialog Arguments: ' + sFirstName);*/
				setWindowReturnValue(null);
				
				var m_cl_fn_name=gup('close_fun');
				if(m_cl_fn_name!=null && m_cl_fn_name!=''){
					invoke_fun_again2(m_cl_fn_name);
					
				}
				window.close();
			}
			
			
			
			function close_onclick(obj) {
				//setWindowReturnValue('The Return Value. And Passed Dialog Arguments: ' + window.dialogArguments);
				
				setWindowReturnValue(obj);
				window.close();
			}
			//--------------------------------------	
			$(document).ready(function() 
				{ 
					$("#myTable").tablesorter(); 
				} 
			); 
			//--------------------------------------
			
			
			onload = function() {
				if (!document.getElementsByTagName || !document.createTextNode) return;
				var rows = document.getElementById('myTable').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
				
				for (i = 0; i < rows.length; i++) {
					rows[i].onclick = function() {
						var row_index=this.rowIndex + 1;
						//var row_length = document.getElementById('myTable').rows.length;
						
						var row_length =document.getElementById('myTable').getElementsByTagName("td").length/rows.length
						//alert(row_index);
						//alert(row_length);
						// alert( document.getElementById('myTable').rows[row_index-1].cells[0].childNodes[0].data);
						//var oMyObjecty = window.dialogArguments;
						var theStatus=new Array(); 
						for (j = 0; j < row_length; j++) {
							
							//alert(""+row_index-1+"**"+j+"**"+row_length);
							// alert( document.getElementById('myTable').rows[row_index-1].cells[j].childNodes[0].data);
							
							theStatus[j] = document.getElementById('myTable').rows[row_index-1].cells[j].childNodes[0].data;
						} 
						close_onclick(theStatus);
						
					}
				}
			}
			/*
			function Next(m_next){
			window.close();
			m_next=document.getElementById('oShowStrat').value;//ovaride value
			window.parent.test(m_next);
			
			}  
			function Pre(m_pre){
			window.close();
			window.parent.test(m_pre);
			
			} */	
			
			
			
			function Next(m_next){
				window.close();
				m_next=document.getElementById('oShowStrat').value;//ovaride value
				
				var m_ex_fn_name=gup('fn_name');
				//invoke_fun_again(m_ex_fn_name,m_next);
				//alert('test1'+m_ex_fn_name);	
				invoke_fun_again(m_ex_fn_name,m_next);	
				//window.parent.test(m_next);
				//alert('test4');
			}  
			function Pre(m_pre){
				window.close();
				var m_ex_fn_name=gup('fn_name');
				invoke_fun_again(m_ex_fn_name,m_pre);
				//window.parent.test(m_pre);
				
			}
			
			function invoke_fun_again(fn_name,val){
				//alert('test2'+fn_name);
				//window.parent.callback_fun(fn_name,val);
				parent.callback_fun(fn_name,val);
				//alert('test3');
			}
			function invoke_fun_again2(fn_name,val){
				window.parent.callback_fun2(fn_name);
			}
			
			function gup( name )
			{
				
				name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
				var regexS = "[\\?&]"+name+"=([^&#]*)";
				var regex = new RegExp( regexS );
				var results = regex.exec( window.location.href );
				//alert(window.location.href );
				if( results == null )
					return "";
				else
					return results[1];
			}
			
		</script>
	</head>
	<body>
		
		<%
			//System.out.println("***HELP POPUP START HERE***");
			session = request.getSession(true);
			/*
			Object done = session.getValue("logon.isDone");  // marker object
			if (done == null) {
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				//out.println("window.location.href='"+html_client_home_url+"/login.htm' ;");
				out.println("alert('Login has Expired');");
				out.println("window.close();");
				out.println("</SCRIPT></HEAD>");
				out.println("<body onload=''></body>");
				out.println("</html>");
				out.flush();
				return;
			}
			*/
		
			db oDB =new db();
			Connection con = oDB.GetORACon();
			String m_schema_name  = oDB.getSchemaName();
			String m_screen_name = request.getParameter("screen_name");
			String m_url = null;
			String m_display_name = null;
			String urlRedirect = null;
			if (request.getParameter("mode")==null || (String)request.getParameter("mode")=="help") {    //help popup
				
				int m_show_limit=0; 
				int m_show_strat =0; 
				int m_show_end =0;
				int m_pre_strat=0;
				
				String json_string_par=request.getParameter("par");
				//System.out.println("kanishka"+json_string_par);//kanishka
				String json_string_type=request.getParameter("type");
				int m_quary_id=Integer.parseInt(request.getParameter("qid"));
				int m_hide=0;
				
				if(request.getParameter("hid")!=null && (String)request.getParameter("hid").trim()!="") {
					m_hide=Integer.parseInt(request.getParameter("hid"));
				}
				
				//JSONArray jsonArray3 = JSONArray.fromObject( "['json','is','easy']" );   	
				
				JSONArray m_json_par_array = JSONArray.fromObject(json_string_par);
				JSONArray m_json_type_array = JSONArray.fromObject(json_string_type);
				
				Object[] m_pra_object=m_json_par_array.toArray();
				Object[] m_type_object=m_json_type_array.toArray();
				
				m_show_limit=Integer.parseInt(request.getParameter("show")); 
				
				String m_str_limit=" limit 0 ,"+m_show_limit;
				
				if (request.getParameter("start")!=null){
					m_show_strat=Integer.parseInt(request.getParameter("start"));
					m_show_end=m_show_strat+m_show_limit;
					m_str_limit=" limit "+m_show_strat+" , "+m_show_limit;
					//m_str_limit="WHERE P.NO>= "+ m_show_strat+" AND P.NO<= "+m_show_end+" ";	
				}
				
				//m_pre_strat=(m_show_strat-m_show_limit>=0?m_show_strat-m_show_limit:0);  
				
				if (m_show_strat-m_show_limit>=0) {
					m_pre_strat=m_show_strat-m_show_limit;
				}
				else {
					m_pre_strat=0;
				}
				
				//db_connection m_con_obj=new db_connection("root","root");
				//Connection con=m_con_obj.GetORACon();
				
				String   selectStatement = "";//"SELECT * FROM ugc_data  where steam=? "+m_str_limit;
				//String   quary_select_statement ="SELECT * FROM HELP_QUERY  where steam=? "+m_str_limit;
				String   quary_select_statement = "SELECT QUARY FROM " + m_schema_name + "HELP_QUERY  WHERE QUARY_ID = ? ";
				
				PreparedStatement prepStmtQ = con.prepareStatement(quary_select_statement);
				prepStmtQ.setInt(1, m_quary_id);
				ResultSet rs_quary = prepStmtQ.executeQuery();
								
				if (rs_quary.next()) {
					selectStatement = rs_quary.getString(1);
					selectStatement = selectStatement.replace("@@",m_schema_name); // TO REPLACE SCHEMA NAME
					//System.out.println("TESTTING HELP QUERY ==> "+selectStatement);//TEMP
				}
				
				String selectStatement_2 = selectStatement;
				
				if (prepStmtQ!=null) {
					prepStmtQ.close();
				}
				
				
				//selectStatement = "SELECT * FROM ( SELECT ROWNUM NO, A.* FROM ("+selectStatement +") A ) P "+m_str_limit;//+m_str_limit
				PreparedStatement prepStmt = con.prepareStatement(selectStatement); 
				for (int j=1,i=1;j<= m_pra_object.length;j++){
					System.out.println(m_type_object[j-1]);
					
					if(m_type_object[j-1]!=null && !m_type_object[j-1].toString().equals("null")){ 
						
						//out.println(m_type_object[j-1]);
						//System.out.println("kanishka ************"+m_type_object[j-1].toString()+"*"+m_type_object[j-1].toString());     
						if(m_type_object[j-1].toString().equals("String")){  
							//System.out.println("kanishka ###"+m_pra_object[j-1].toString());  
							prepStmt.setString(j,m_pra_object[j-1].toString().trim());//
							
						}else if(m_type_object[j-1].toString().equals("StringL")){  
							prepStmt.setString(j,"%"+m_pra_object[j-1].toString().trim()+"%" );
							
						}else if(m_type_object[j-1].toString().equals("Int")){
							
							prepStmt.setInt(j, Integer.parseInt(m_pra_object[j-1].toString()));    
						}else if(m_type_object[j-1].toString().equals("Float")){
							
							prepStmt.setFloat(j, Float.parseFloat(m_pra_object[j-1].toString()));  
						} else if(m_type_object[j-1].toString().equals("Double")){
							prepStmt.setDouble(j, Double.parseDouble(m_pra_object[j-1].toString()));
							
						}else if(m_type_object[j-1].toString().equals("Long")){
							
							prepStmt.setLong(j, Long.parseLong(m_pra_object[j-1].toString()));  
						}else if(m_type_object[j-1].toString().equals("Date")){
							
							int m_year   = Integer.parseInt(m_pra_object[j-1].toString().substring(6,10));
							int m_month  = Integer.parseInt(m_pra_object[j-1].toString().substring(3,5));
							int m_day    = Integer.parseInt(m_pra_object[j-1].toString().substring(0,2));
							prepStmt.setDate(j, new java.sql.Date(m_year,m_month,m_day));    
						}
					} //if !=null 
				}
				
				ResultSet rs = prepStmt.executeQuery();
				
				out.print("<table id=\"myTable\" class=\"tablesorter\">");
				out.print(" <thead> ");  
				out.print("<tr>");   
				int m_max_col_count=rs.getMetaData().getColumnCount();
				// out.print("   <th></th> ");
				out.print("<th>No.</th>");
				for(int m_col_count=1;m_col_count<=m_max_col_count;m_col_count++){
					
					String m_column_name = rs.getMetaData().getColumnName(m_col_count);
					// if (m_column_name.equals("NO")) {
					// m_column_name = "No.";
					// }
					if(m_col_count<=m_max_col_count-m_hide){
						out.print("<th>"+ m_column_name+"</th>");
					}else{
						out.print("<th style=\"display: none;\" >"+ m_column_name+"</th>");
					}
					
				}out.print("</tr></thead><tbody>");
				// boolean m_more_result=rs.next();
				int rowCount = 0;
				// if(m_more_result){
				int rec_no=0;
				while(rs.next()){
					rec_no= rec_no + 1;
					
					if ((rs.getRow() > m_show_strat) && (rs.getRow() <= m_show_end)) {
						
						out.print("<tr>"); 
						out.print("<td style=\"cursor: pointer;\">"+rs.getRow()+"</td>"); 
						for(int j=1;j<=m_max_col_count;j++){ 
							
							if(j<=m_max_col_count-m_hide){
								out.print("<td style=\"cursor: pointer;\">"+rs.getString(j)+"</td>");
							}else{
								out.print("<td style=\"display: none;\" >"+rs.getString(j)+"</td>");
							}	
							
						}
						out.print("</tr>");
						
					}
					// rec_no++;
					// m_more_result=rs.next();  
				}
				
				rowCount = rec_no;
				
				//}
				out.print("</tbody> </table> ");
				
				
				if(rs!=null){rs.close();}
				if(prepStmt!=null){prepStmt.close();}
				if(prepStmtQ!=null){prepStmtQ.close();}
				if(con!=null) {con.close();}   
				
				
				out.print("<input type=\"button\" class=\"but_input\" onClick=\"closeMe();\" value=\"Close\" />");
				out.print("<input type=\"button\" class=\"but_input\" onClick=\"Pre('"+m_pre_strat+"');\" value=\"<<\" />");
				out.print("<INPUT TYPE=\"text\"   class=\"txt_input\"   NAME=\"oShowStrat\"  ID=\"oShowStrat\" VALUE=\""+(m_show_end)+"\" SIZE="+(m_show_end+"").length()+"  align=\"right\">"); //
				out.print("<input type=\"button\" class=\"but_input\" onClick=\"Next('"+(m_show_end)+"');\" value=\">>\" />");
				out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print("<span class=\"div_input\">Showing " + Integer.toString(m_show_strat + 1) + " - " + Integer.toString(m_show_end) + " out of " + Integer.toString(rowCount) + " records</span>");
			}//end if help popup
			%>
			</body>
				</html>