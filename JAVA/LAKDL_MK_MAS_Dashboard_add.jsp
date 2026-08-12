<%@page import="java.sql.Connection" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.Date" %>
<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="javax.servlet.http.*" %>
<%@page import="oracle.jdbc.driver.*" %>
<%@page import="java.sql.*" %>
<%@page import="java.text.DecimalFormat" %>
<HTML> 
	<HEAD> 
		<TITLE>System Administration - Locations</TITLE> 
	</HEAD> 
	
	<%
		LAKDL_AF_CO_conn_methods m_sn_methods1 = new LAKDL_AF_CO_conn_methods();
		//String m_html_client_url = m_sn_methods1.html_client_url;
		//LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(session);
		String m_jsp_client_url=m_sn_methods.jsp_client_url;
		m_schema_name = m_sn_methods.schema_name.trim();
		
		//out.println(m_jsp_client_url);
		
	%>
	<!--<link REL='STYLESHEET' HREF='../../css/Asset_Financing_System.css' TYPE="text/css">
	<link REL='STYLESHEET' HREF='../../css/Asset_Financing_System.css' TYPE="text/css"> 
	<link REL="STYLESHEET" HREF="../../css/report/jquery-ui.css"  TYPE="text/css"/>
	<link REL="STYLESHEET" HREF="../../css/report/jquery.ui.button.css"  TYPE="text/css"/>
	<link REL="stylesheet" type="text/css" href="../../css/report/ui.jqgrid.css"/>
	<link REL="stylesheet" type="text/css" href="../../css/report/jquery.ui.datepicker.css"/>
	
	<script type="text/javascript" src="../../jquery/report/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src= "../../js/report/jquery.min.js"></script> 
	<script type="text/javascript" src= "../../js/report/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../../js/report/json2.js"></script>
	<script type="text/javascript" src="../../js/report/popup.js"></script>
	<script type="text/javascript" src="../../jquery/grid/4.1/js/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="../../jquery/grid/4.1/js/jquery.jqGrid.min.js"></script>-->
	
	<SCRIPT language="JavaScript">
		
		//document.Form1.username.value	 	 = session.getAttribute("USERNAME");
		function init_assign_val(){
			//document.Form1.current_url.value	 = document.URL;
			
		}
		//alert(document.Form1.current_url.value);
		<%
		try{
			db db =new db();
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			conn = db.GetORACon();
			stmt=conn.createStatement();
			String schema   	 	= db.getSchemaName();
			String header_name   	= "System Administration - Locations"; 
			String save_class_url	= db.getSaveClassUrl(); 
			String header_id = "";	
			
			
			rs=stmt.executeQuery(" SELECT HEDER_ID, "+
				"DESC_1, "+
					"DESC_2, "+
					"HEIGHT_1 "+
					"FROM LAKDL.DH_DASH_HEADER ");
			if(rs.next()){
				
				header_id = rs.getString(1);
			}
			%>
			var lineno=0;
			var arr_size=0;
			
			var array_location=new Array();
			var array_contact_name=new Array();
			var array_contact_tel=new Array();
			var array_contact_fax=new Array();
			
			// m_writedata='<TR>' +
			//'<TD><B>Contact Person</B></TD><TD><B>Telephone Number</B></TD><TD><B>Fax Number</B></TD>' +
			//'</TR>';
			//array_location[lineno]=m_writedata;		 
			//lineno=lineno+1;
			//arr_size=arr_size+1;
			
			function load_grid_app_details(){
				
				//*************  USE JQUERY GRIDS  ****************//
				//m_url="../../validations/PW_MK_sql_validate_duplication.jsp?chksql=get_sub_sections";
				
				jQuery("#verification_app_details").jqGrid(
					{
						loadonce: 1,
						url:m_url,
						datatype: "xml", 
						colNames:['Sub Section ID','Description 1','Description 2', 'Height', 'Add Widget', 'Edit', 'Remove' ],
						colModel:[ 
							{name:'SUB_SEC_ID' 			,index:'SUB_SEC_ID' 	  	    , hidden:false	,width:100,align:"center" 	,formatter:application_no_fmatter},
							{name:'DESCRIPTION_1' 		,index:'DESCRIPTION_1' 		    , hidden:false	,width:100,align:"center" 	,formatter:application_description1},
							{name:'DESCRIPTION_2' 		,index:'DESCRIPTION_2' 			, hidden:false	,width:100,align:"center" 	,formatter:application_description2},
							{name:'HEIGHT' 				,index:'HEIGHT' 				, hidden:false	,width:100,align:"center" 	,formatter:application_height},
							{name:'DETAILS' 			,index:'DETAILS' 				, hidden:false	,width:200,align:"center" 	,formatter:detail_fmatter},
							{name:'EDIT' 				,index:'EDIT' 					, hidden:true	,width:200,align:"center" 	,formatter:edit_btn_fmatter},
							{name:'REMOVE' 				,index:'REMOVE' 				, hidden:false	,width:200,align:"center" 	,formatter:delete_btn_fmatter},
							
						],
						viewrecords: true, 
						hidegrid: false, 
						rownumbers:true,
						gridview: true,
						caption:"Sub Sections",
						height: 'auto',
						gridComplete: function () { 
							//alert(document.Form1.elements["ROW_COUNT"].value);
							var records_count = $("#verification_app_details").jqGrid('getGridParam',"records");
							document.Form1.elements["row_count"].value = records_count;
						},
						beforeShowForm :false
						
					}
				);
			}	
			/*
			function delete_btn_fmatter (cellvalue, options, rowObject)
			{
				var rowid = options['rowId'];
				var link ='<input type="button" style="width: 80px;" onclick="delete_article_data('+rowid+');"  value="Delete" name="BUT_DELETE_DETAIL_'+rowid+'" class="but_input">'; 
				return link;	
			}
			*/
			
			function application_description1(cellvalue, options, rowObject){
				var rowid = options['rowId'];
				var link ='<input type="hidden" name="HID_APP_DESC1_'+rowid+'" value='+cellvalue+' > '+cellvalue; 
				return link;
				
			}
			
			function application_description2(cellvalue, options, rowObject){
				var rowid = options['rowId'];
				var link ='<input type="hidden" name="HID_APP_DESC2_'+rowid+'" value='+cellvalue+' > '+cellvalue; 
				return link;
				
			}
			
			function application_height(cellvalue, options, rowObject){
				var rowid = options['rowId'];
				var link ='<input type="hidden" name="HID_APP_HEIGHT_'+rowid+'" value='+cellvalue+' > '+cellvalue; 
				return link;
				
			}
			
			
			function edit_btn_fmatter (cellvalue, options, rowObject)
			{
				var rowid = options['rowId'];
				var link ='<input type="button" style="width: 80px;" onclick="edit_article_data('+rowid+');" value="Edit" name="BUT_EDIT_DETAIL_'+rowid+'" class="but_input">'; 
				return link;	
			}
			
			
			function edit_article_data(row_id){
				if(confirm("Are you sure you want to edit ? ")){ 
					document.Form1.TXT_SUB_SEC_ID.value 	= document.Form1.elements["HID_APP_NO_"+row_id].value;
					document.Form1.TXT_SUB_DESC1.value		= document.Form1.elements["HID_APP_DESC1_"+row_id].value;
					document.Form1.TXT_SUB_DESC2.value 	= document.Form1.elements["HID_APP_DESC2_"+row_id].value;
					document.Form1.TXT_SUB_HEIGHT.value		= document.Form1.elements["HID_APP_HEIGHT_"+row_id].value;
					
					//remove edit record from grid
					jQuery('#verification_app_details').jqGrid('delRowData', row_id);
					//document.Form1.elements["row_count"].value =  parseInt(document.Form1.elements["row_count"].value) - 1;
					//cal_total_amount_existing_article();
				}
			}
			
			
			function detail_fmatter (cellvalue, options, rowObject)
			{
				var rowid  = options['rowId'];
				var link ='<input type="button" style="width: 80px;" onclick="cli_info('+rowid+');"  value="Add" class="but_input">'; 
				return link;	
			}
			
			
			function application_no_fmatter (cellvalue, options, rowObject)
			{
				var rowid = options['rowId'];
				var link ='<input type="hidden" name="HID_APP_NO_'+rowid+'" value='+cellvalue+' > '+cellvalue; 
				return link;	
			}
			
			function cli_info(row_id){
				m_url='add_graph_tables.jsp?APP_NO='+document.Form1.elements["HID_APP_NO_"+row_id].value;
				window.open(m_url,'displayWindow3','left=50,top=60,width=1100,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1'); 
				
			}
			
			function add_row(){
				
				
				var m_status= "ADD";
				if(lineno == 0){
					load_grid_app_details();
				}
				more_records();
				clear_top_feilds();
				
			}
			
			
			function more_records(){
				var sub_sec_id 	= document.Form1.elements["TXT_SUB_SEC_ID"].value;
				var sub_des1 	= document.Form1.elements["TXT_SUB_DESC1"].value;
				var sub_des2 	= document.Form1.elements["TXT_SUB_DESC2"].value;
				var sub_height 	= document.Form1.elements["TXT_SUB_HEIGHT"].value;
				//temp
				//new_item_value = "25.000524";
				//new_loan_value = "30.255787";
				
				var data={"SUB_SEC_ID":""+sub_sec_id+"",
					"DESCRIPTION_1":""+sub_des1+"",
					"DESCRIPTION_2":""+sub_des2+"",
					"HEIGHT":""+sub_height+"",
					"ADD_ROW":" ","EDIT_ROW":" ","REMOVE_ROW":" "
				}
				
				var lineno = parseInt(document.Form1.elements["row_count"].value);
				jQuery("#verification_app_details").jqGrid('addRowData',lineno,data,'first'); 
				//document.Form1.elements["row_count"].value = lineno + 1;
			}
			
			
			function delete_btn_fmatter (cellvalue, options, rowObject)
			{
				var rowid = options['rowId'];
				var link ='<input type="button" style="width: 80px;" onclick="delete_article_data('+rowid+');"  value="Delete" name="BUT_DELETE_DETAIL_'+rowid+'" class="but_input">'; 
				return link;	
			}
			
			
			function delete_article_data(row_id){
				//delete record from jq grid
				//jQuery('#ticket_details').jqGrid('delGridRow', row_id,{dataType: 'local',msg: 'Are you sure want to Delete this entry?',reloadAfterSubmit: false,url:"../../validations/PW_MK_sql_validations.jsp?chksql=get_article_record&mode=DELETE"});
				if(confirm("Are you sure you want to delete ? ")){ 
					jQuery('#verification_app_details').jqGrid('delRowData', row_id);
					//document.Form1.elements["row_count"].value =  parseInt(document.Form1.elements["row_count"].value) - 1;
					
				}
			}
			
			
			
			function save_window(){
				document.Form1.action="save/add_sub_sections_save.jsp";
				document.Form1.submit();	 
			}
			
			
	</script> 
	<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onload="" > <!--load_grid_app_details();-->
		<FORM NAME='Form1' id="Form1" method='post'> 
			<input  type='hidden' value='NEW' name='SCREEN_NAME'>  
			<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=""> 
			<INPUT TYPE='Hidden' NAME='hid_status' VALUE="New"> 
			<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE="">
			<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=""> 
			<INPUT TYPE='Hidden' NAME='row_count' value=0>
			<INPUT TYPE='Hidden' NAME='HID_HEADER_ID' value="<%=header_id%>">
			
			
			<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'> 
				<tr> 
					<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td> 
					<td class='border_wht' valign='top'>  
						<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'> 
							<tr>  
								<td height='30' id='help_box' class='pdn_txtpos2'><%=header_name%></td> 
							</tr> 
							<tr>  
								<td height='1'><img src='spacer.gif' width='1' height='1'></td> 
							</tr> 
							<tr> 
								
								<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>    
									
									<tr> 
										<td  height='10px' class='pdn_txtpos'> 
											<table class='table' cellpadding='2' cellspacing='2' border='0'>  
												<tr>
													<!--									
													<td width='10%' align='center'><input type="button" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value("New");' onclick='load_screen_status("NEW")' value="New"></td>  
													<td width='10%' align='center'><input type="button" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value("Edit");' onClick='load_screen_status("EDIT")' value="Edit"></td>  
													<td width='10%' align='center'><input type="button" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value("Deactivate");' onClick='load_screen_status("DACT")' value="Deactivate"></td>  
													<td width='10%' align='center'><input type="button" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value("Reactivate");' onClick='load_screen_status("RACT")' value="Reactivate"></td> 
													<td width='10%' align='center'><input type="button" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value("View All");'  onclick='help_view_all(0)' value="View All"></td>
													<td width='6%'></td>  -->
													<td width='10%' align='center'><input type="button" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value("Save");'  onClick='save_window()' value="Save"></td>  
													<!--<td width='10%' align='center'><input type="button" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value("Help");' onClick='load_screen_status("HELP")' value="Help"></td>-->  
													<td width='10%' align='center'><input type="button" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value("Cancel");'  onclick='clear_window()' value="Cancel"></td>  
													<td width='10%' align='center'><input type=button name=back value="Close" class=mainbut onclick=close_window(); onMouseOver='load_roll_value("Close");' onmouseout='load_roll_value("Close'></td>
													<td width='*%' align='right' class='div_input'></td></tr>  
											</table>  
										</td></tr><tr>  
										<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>  
									</tr><tr>  
										<td class='pdn_txtpos' height='150' valign='top'>  
											
											
											<table align='center' width='100%' class='table'> 
												
												<tr>	
													<td width='10%' ><b>Header Details</b></td> 
												</tr>												
												<!--<tr > 
												
												<td width='30%' >Header ID *</DIV></td> 
												<td width='40%' ><input class='txt_input' type='text' name='TXT_HEADER_ID' maxlength='10' size='10' onblur="assignState('M1'),makeRequest(document.Form1.TXT_LOCATION_CODE)"> 
												
												<td width='*%'></td> 
												</tr> -->
												<%
			rs=stmt.executeQuery(" SELECT HEDER_ID, "+
				"DESC_1, "+
					"DESC_2, "+
					"HEIGHT_1 "+
					"FROM LAKDL.DH_DASH_HEADER ");
			if(rs.next()){
				
				
												%>
												
												
												<tr > 
													
													<td width='30%' >Main Description *</DIV></td> 
												<td width='40%' ><input class='txt_input' value="<%=rs.getString(2)%>" type='text' name='TXT_HEADER_DESC' maxlength='100' size='150' onblur="assignState('M6'),makeRequest(document.Form1.TXT_LOCATION_DESC)"></td> 
												<td width='*%'></td> 
											</tr> 
											
											<tr > 
												
												<td width='30%' >Company Name *</DIV></td> 
											<td width='40%' ><input class='txt_input' value="<%=rs.getString(3)%>" type='text' name='TXT_HEADER_COM_NAME' maxlength='100' size='150' onblur="assignState('M6'),makeRequest(document.Form1.TXT_LOCATION_DESC)"></td> 
											<td width='*%'></td> 
										</tr> 													
										
										<tr > 
											
											<td width='30%' >Heading Height</DIV></td> 
										<td width='40%' ><input class='txt_input' value="<%=rs.getString(4)%>" type='text' name='TXT_HEADER_HEIGHT' maxlength='100' size='150'></td> 
										<td width='*%'></td> 
									</tr> 
									
									<%
			}
									%>
									
								</table>
								<br><br>
								
								
								<table align='center' width='100%' class='table'> 
									
									<tr>	
										<td width='10%' ><b>Add Sub Sections</b></td> 
									</tr>												
									<!--<tr > 
									
									<td width='30%' >Header ID *</DIV></td> 
									<td width='40%' ><input class='txt_input' type='text' name='TXT_HEADER_ID' maxlength='10' size='10' onblur="assignState('M1'),makeRequest(document.Form1.TXT_LOCATION_CODE)"> 
									
									<td width='*%'></td> 
									</tr> -->
									<tr > 
										
										<td width='30%' >Sub Section ID *</DIV></td> 
									<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_SEC_ID' maxlength='100' size='100' onblur="assignState('M6'),makeRequest(document.Form1.TXT_LOCATION_DESC)"></td> 
									<td width='*%'></td> 
								</tr> 
								
								<tr > 
									
									<td width='30%' >Description 1 *</DIV></td> 
								<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_DESC1' maxlength='100' size='100' onblur="assignState('M6'),makeRequest(document.Form1.TXT_LOCATION_DESC)"></td> 
								<td width='*%'></td> 
							</tr> 													
							
							<tr > 
								
								<td width='30%' >Description 2</DIV></td> 
							<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_DESC2' maxlength='100' size='50'></td> 
							<td width='*%'></td> 
						</tr> 
						
						<tr > 
							
							<td width='30%' >Height</DIV></td> 
						<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_HEIGHT' maxlength='100' size='50'>
							<input type="button" class='mainbut' onclick='add_row();' value="Add"></td>   <!-- add_row();  more_str_help();--></td> 
					<td width='*%'></td> 
				</tr> 
				
				
				
				<tr>
					<div ALIGN='LEFT'><table id="verification_app_details" ALIGN='CENTER'></table></DIV>		
				</tr>
				
			</table>
			
			
			<br> 
			<table align='center' width='100%'> 
				<tr> 
					<td width='100%' class='note'></td> 
				</tr> 
			</table> 
		</form> 
		<SCRIPT language1.2='JavaScript' src='../../js/validate.js'></SCRIPT> 
		<SCRIPT language1.2='JavaScript' src='../../js/ajax_data_gateway.js'></SCRIPT> 
	</body> 
</html> 
<%						}catch(Exception e){
	
	
}%>

