
/*
	*	DEVELOPED BY : INESH  NUwan
	*	2018-01-08
	*	JB02012018-02276 NetAsset system documents upload facility
*/
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MK_Document_Upload_New extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;  
	ResultSet rs 			= null;
	Statement stmt			= null;
	CallableStatement callstmt;
	Connection conn;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username 	 = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			conn = m_sn_methods.met_user_validate(req);   
            stmt = conn.createStatement();
			
			String m_screenDisplayName = "Document Upload";
			
			out.println("<!DOCTYPE html>"); 
			out.println("<HTML>");
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - "+m_screenDisplayName+"</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			//out.println("<SCRIPT type='text/javascript' src='"+m_html_client_url+"/test.js'></SCRIPT>"); //Testing purpose only comment this when you transfer 
			//out.println("<SCRIPT type='text/javascript' src='"+m_html_client_url+"/jquery-3.3.1.min.js'></SCRIPT>"); //Testing purpose only comment this when you transfer  
			out.println("<SCRIPT language=\"JavaScript\">"); 
			//=========================================================================================================================
			out.println(" var allowTypes = new Array();");
			
			String m_allowedTypesQ = " SELECT UPPER(TYPE_NAME) DOC_EXT "+
									 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_ALLOW_TYPE "+
									 " WHERE ACTIVE_STATUS ='Y' "+
									 " UNION ALL "+
									 " SELECT LOWER(TYPE_NAME) DOC_EXT "+
									 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_ALLOW_TYPE "+
									 " WHERE ACTIVE_STATUS ='Y' ";
			rs = stmt.executeQuery (m_allowedTypesQ);
			while(rs.next()){
				out.println(" allowTypes.push('"+rs.getString("DOC_EXT")+"');"); 
			}
			
			out.println(" var allowImgTypes = new Array();");
			String m_allowedTypesImgQ = " SELECT UPPER(TYPE_NAME) DOC_EXT "+
									 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_ALLOW_TYPE "+
									 " WHERE ACTIVE_STATUS ='Y' "+
									 " AND TYPE_OF_IMG ='Y' "+
									 " UNION ALL "+
									 " SELECT LOWER(TYPE_NAME) DOC_EXT "+
									 " FROM "+m_schema_name+".AF_MK_DOC_UPLOAD_ALLOW_TYPE "+
									 " WHERE ACTIVE_STATUS ='Y' "+
									 " AND TYPE_OF_IMG ='Y' ";
			rs = stmt.executeQuery (m_allowedTypesImgQ);
			while(rs.next()){
				out.println(" allowImgTypes.push('"+rs.getString("DOC_EXT")+"');"); 
			}
			//=========================================================================================================================
			out.println("function load_roll_out_value(){");
			out.println(" 		help_box.innerHTML=\" System Administration - "+m_screenDisplayName+" \";"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println(" 		help_box.innerHTML=\" System Administration - "+m_screenDisplayName+" - \"+m_val;"); 
			out.println("}"); 
						
			out.println("function new_window(){	"); 
			out.println(" 	window.location.href=window.location.href;"); 
			out.println("}"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("			window.location.href=window.location.href;"); 
			out.println("		}"); 
			out.println("}");
			//=========================================================================================================================
			out.println("function load_screen_status(m_val){");
			out.println("    if(m_val==\"NEW\"){");
			out.println("        new_window();");
			out.println("    }");
			out.println("}");
			out.println("function save_window(){    ");
			out.println("    var msg ='Are you sure you want to Save?';");
			out.println("    if(document.Form1.SCREEN_NAME.value == \"NEW\"){");
			out.println("        msg ='Are you sure you want to Save?';");
			out.println("    }else if(document.Form1.SCREEN_NAME.value == \"EDIT\"){");
			out.println("        msg ='Are you sure you want to Modify?';");
			out.println("    }");
			out.println("    if(confirm(msg)){");
			out.println("        if(validateForm()){");
			out.println("            if(isDocumentUploaded()){");
			out.println("                beforeSubmit();                ");
			out.println("            }else{");
			out.println("                alert('Please upload a document before save');");
			out.println("            }");
			out.println("        }else{");
			out.println("            alert('Please upload valid document');");
			out.println("        }        ");
			out.println("    }");
			out.println("}");
			out.println("");
			out.println("function help_update() {");
			out.println("    document.Form1.HID_HELP_TYPE.value=\"FINANCE_NO\";");
			out.println("    m_sql = \"m_help_TXT_DOCUMENT_UPLOAD_FIN_NO_sql\";    ");
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";        ");
			out.println("    HelpBox('1','10','0');");
			out.println("}");
			out.println("function help_update_finance_no(){            ");
			out.println("    document.Form1.TXT_FINANCE_NO.value = oBj.valout[2];    ");
			out.println("    loadDocumentUpdateTable(oBj.valout[2]);");
			out.println("}");
			out.println("function clear_data(){");
			out.println("    document.Form1.TXT_FINANCE_NO.value = '';    ");
			out.println("    document.getElementById('DIV_DOCUMENT_GRID').innerHTML = \"\";    ");
			out.println("}");
			out.println("function Close(){    ");
			out.println("    clear_data();");
			out.println("}");
			out.println("function makeRequest(url,opt,type) {");
			out.println("    var http_request = false;");
			out.println("    if (window.XMLHttpRequest) {    //Mozilla, Safari,..");
			out.println("        http_request = new XMLHttpRequest();");
			out.println("        if (http_request.overrideMimeType) {");
			out.println("            http_request.overrideMimeType('text/xml');");
			out.println("        }");
			out.println("    }else if (window.ActiveXObject) { // IE");
			out.println("        try {");
			out.println("            http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");            ");
			out.println("        } catch (e) {");
			out.println("            try {");
			out.println("                http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
			out.println("            }catch (e) {}");
			out.println("        }");
			out.println("    }");
			out.println("    if (!http_request) {");
			out.println("        alert('Giving up :( Cannot create an XMLHTTP instance');");
			out.println("        return false;");
			out.println("    }");
			out.println("    if(opt=='GET_DOC_UP_TABLE'){");
			out.println("        http_request.onreadystatechange = function() { receiveAjaxData(http_request,opt,type); };");
			out.println("        http_request.open('GET',url, true);");
			out.println("        http_request.send(null);");
			out.println("    }");
			out.println("}");
			out.println("function receiveAjaxData(http_request,opt,type){");
			out.println("    if (http_request.readyState == 4) {");
			out.println("        if (http_request.status == 200) {");
			out.println("            if(opt=='GET_DOC_UP_TABLE'){");
			out.println("                DIV_DOCUMENT_GRID.innerHTML=http_request.responseText;                 ");
			out.println("            }");
			out.println("        }");
			out.println("    }");
			out.println("}");
			out.println("function loadDocumentUpdateTable(fin_no){");
			out.println("    if(fin_no!=''){");
			
			out.println("        m_url = servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_sql_validations_2?chksql=m_getDocumentUploadTable&fin_no=\"+fin_no;");
			out.println("        makeRequest(m_url,'GET_DOC_UP_TABLE','DOC_UPLOAD');");
			out.println("    }");
			out.println("}");
			out.println("function viewDocument(docId){");
			out.println("    m_url = servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+ \"AF_MK_Document_Upload_New_View?chksql=view_document&doc_id=\"+docId;");
			out.println("    window.open(m_url,'slab','width=600,height=600,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');    ");
			out.println("}");
			out.println("function viewAllUploads(){");
			out.println("    if(document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("        m_url = servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Document_Upload_New_View?chksql=viewAllDocuments&finance_no=\"+document.Form1.TXT_FINANCE_NO.value;");
			out.println("        window.open(m_url);");
			out.println("    }else{");
			out.println("        alert('Please enter a finance number.');");
			out.println("    }");
			out.println("}");
			
			out.println("function clearFileUpload(num){\r\n");
			out.println("    document.Form1.elements['HID_UPLOAD_STATUS_'+num].value ='N';    ");
			out.println("    document.getElementById('FILE_DOCUMENT_'+num).parentNode.innerHTML = document.getElementById('FILE_DOCUMENT_'+num).parentNode.innerHTML;    ");
			out.println("}    ");
			out.println("function upload_file(row){    ");
			out.println("    var isSizeOk = false;     ");
			out.println("    var fObj = document.getElementById(\"FILE_DOCUMENT_\"+row);    ");
			out.println("    fObj.style.color='black';    ");
			out.println("    var mfileName = fObj.value.split(/(\\\\|\\/)/g).pop();    ");
			out.println("    var indexFileMin = 1;    ");
			out.println("    var extention = mfileName.split('.').pop();     ");
			out.println("    if(!isValidExtenstion(extention)){    ");
			out.println("        alert('Please upload valid file');    ");
			out.println("        fObj.value ='';    ");
			out.println("        document.Form1.elements['HID_UPLOAD_STATUS_'+row].value ='N';    ");
			//out.println("        document.Form1.elements['FILE_DOCUMENT_'+row].style.color='red';    ");
			out.println("                    clearFileUpload(row);    ");
			out.println("        return false;    ");
			out.println("    }else{    ");
			out.println("        if(indexFileMin == -1 && isValidExtenstionImg(extention)){    ");
			out.println("            alert('Please reduce the image size before upload');    ");
			out.println("            fObj.value ='';    ");
			out.println("            document.Form1.elements['HID_UPLOAD_STATUS_'+row].value ='N'    ");
			//out.println("            document.Form1.elements['FILE_DOCUMENT_'+row].style.color='red';    ");
			out.println("                    clearFileUpload(row);    ");
			out.println("        }else{    ");
			//out.println("            //-----------------------------------------------------------------    ");
			out.println("            try{    ");
			out.println("                var ua = window.navigator.userAgent;    ");
			out.println("                var sPath  = fObj.value;                        ");
			out.println("                var msie = ua.indexOf(\"MSIE \");    ");
			out.println("                if(msie > 0){    ");
			out.println("                    var objFSO = new ActiveXObject(\"Scripting.FileSystemObject\");                        ");
			out.println("                    var objFile = objFSO.getFile(sPath);    ");
			out.println("                    var iSize = objFile.size;    ");
			out.println("                    iSize = iSize/ 1024;    ");
			out.println("                }else{    ");
			out.println("                    var idName =\"FILE_DOCUMENT_\"+row;    ");
			out.println("                    iSize = (document.getElementById(\"FILE_DOCUMENT_\"+row).files[0].size / 1024);     ");
			out.println("                }    ");
			out.println("                if (iSize / 1024 > 1){    ");
			out.println("                    isSizeOk = false;        ");
			out.println("                    alert('Document size is greater than 1Mb');    ");
			out.println("                    clearFileUpload(row);    ");
			out.println("                }else{    ");
			out.println("                    isSizeOk = true;        ");
			out.println("                }    ");
			out.println("                    ");
			out.println("            }catch(err){    ");
			out.println("                alert('File size can not be validate please contact IT support.');    ");
			out.println("                isSizeOk = true;    ");
			out.println("            }    ");
			//out.println("            //-----------------------------------------------------------------    ");
			out.println("            if(isSizeOk){    ");
			out.println("                document.Form1.elements['HID_UPLOAD_STATUS_'+row].value ='Y'    ");
			out.println("                return true;    ");
			out.println("            }                ");
			out.println("        }    ");
			out.println("    }            ");
			out.println("}    ");

			//out.println("    var mfileName = fObj.value.split(/(\\\\|\\/)/g).pop();    ");
			/*out.println("function upload_file(row){    ");
			out.println("    var fObj = document.getElementById(\"FILE_DOCUMENT_\"+row);");
			out.println("    fObj.style.color='black';");
			out.println("    var mfileName = fObj.value.split(/(\\\\|\\/)/g).pop();    ");
			//out.println("    var indexFileMin = mfileName.indexOf(\"FILEminimizer\");");
			out.println("    var indexFileMin = 1;");
			out.println("    var extention = mfileName.split('.').pop();    ");
			out.println("    if(!isValidExtenstion(extention)){");
			out.println("        alert('Please upload valid file');");
			out.println("        fObj.value ='';");
			out.println("        document.Form1.elements['HID_UPLOAD_STATUS_'+row].value ='N'");
			out.println("        document.Form1.elements['FILE_DOCUMENT_'+row].style.color='red';");
			out.println("        return false;");
			out.println("    }else{");
			out.println("        if(indexFileMin == -1 && isValidExtenstionImg(extention)){");
			out.println("            alert('Please reduce the image size before upload');");
			out.println("            fObj.value ='';");
			out.println("            document.Form1.elements['HID_UPLOAD_STATUS_'+row].value ='N'");
			out.println("            document.Form1.elements['FILE_DOCUMENT_'+row].style.color='red';");
			out.println("        }else{");
			out.println("            document.Form1.elements['HID_UPLOAD_STATUS_'+row].value ='Y'");
			out.println("            return true;");
			out.println("        }");
			out.println("    }    ");
			out.println("}");
			*/
			out.println("function isValidExtenstion(extention){    ");
			out.println("    for(i=0;i<allowTypes.length;i++){");
			out.println("        if(extention == allowTypes[i]){");
			out.println("            return true;");
			out.println("        }");
			out.println("    }");
			out.println("    return false;");
			out.println("}");
			out.println("function isValidExtenstionImg(extention){");
			out.println("    for(i=0;i<allowImgTypes.length;i++){");
			out.println("        if(extention == allowImgTypes[i]){");
			out.println("            return true;");
			out.println("        }");
			out.println("    }");
			out.println("    return false;");
			out.println("}");
			out.println("function validateForm(){");
			out.println("    DIV_TXT_DOCUMENT_CODE.style.color='black';");
			out.println("    ");
			out.println("    var isFormValid= true;");
			out.println("    if(document.Form1.TXT_FINANCE_NO.value ==''){");
			out.println("        DIV_TXT_DOCUMENT_CODE.style.color='red';");
			out.println("        alert('Please enter Finance No');    ");
			out.println("        isFormValid = false;");
			out.println("        return false;");
			out.println("    }");
			out.println("    ");
			out.println("    var numElement = document.Form1.HID_NUM_OF_REC;");
			out.println("    if(typeof(numElement) != 'undefined' && numElement != null ){        ");
			out.println("        var numOfRec= parseInt(document.Form1.HID_NUM_OF_REC.value);");
			out.println("        for(x=1;x<=numOfRec;x++){");
			out.println("            ");
			out.println("            if(document.Form1.elements['HID_UPLOAD_ENABLE_'+x].value=='Y'){ //SELECT ENABLED ONE");
			out.println("                document.Form1.elements['FILE_DOCUMENT_'+x].style.color='black'; //RESET THE COLOR                ");
			out.println("                if(document.Form1.elements['HID_UPLOAD_STATUS_'+x].value=='Y'){ //SELECT THE UPLOADED ONE");
			out.println("                    if(document.Form1.elements['FILE_DOCUMENT_'+x].value ==''){ //check the uploaded file name is empty");
			out.println("                        document.Form1.elements['FILE_DOCUMENT_'+x].style.color='red';");
			out.println("                        isFormValid = false;");
			out.println("                    }");
			out.println("                }");
			out.println("                if(document.Form1.elements['HID_UPLOAD_STATUS_'+x].value=='N'){ //SELECT NOT UPLOADED ONE");
			out.println("                    if(document.Form1.elements['FILE_DOCUMENT_'+x].value !=''){                        ");
			out.println("                        document.Form1.elements['FILE_DOCUMENT_'+x].style.color='red';");
			out.println("                        isFormValid = false;");
			out.println("                    }");
			out.println("                }");
			out.println("            }");
			out.println("            //validate the every attached file.");
			out.println("        }");
			out.println("    }else{");
			out.println("        isFormValid = false;");
			out.println("    }");
			out.println("    return isFormValid;");
			out.println("    ");
			out.println("}");
			out.println("function isDocumentUploaded(){");
			out.println("    var numElement = document.Form1.HID_NUM_OF_REC;");
			out.println("    if(typeof(numElement) != 'undefined' && numElement != null ){");
			out.println("        var numOfRec= parseInt(document.Form1.HID_NUM_OF_REC.value);");
			out.println("        for(x=1;x<=numOfRec;x++){");
			out.println("            if(document.Form1.elements['HID_UPLOAD_ENABLE_'+x].value=='Y'){ //SELECT ENABLED ONE");
			out.println("                if(document.Form1.elements['HID_UPLOAD_STATUS_'+x].value=='Y'){");
			out.println("                    return true;");
			out.println("                }");
			out.println("            }");
			out.println("        }");
			out.println("    }");
			out.println("    return false;");
			out.println("}");
			out.println("function viewPreviousUploads(){");
			out.println("    if(document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("        m_url = servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Document_upload?chksql=view_documents&finance_no=\"+document.Form1.TXT_FINANCE_NO.value;");
			out.println("        window.open(m_url);");
			out.println("    }else{");
			out.println("        alert('Please enter a finance number.');");
			out.println("    }");
			out.println("    ");
			out.println("}");
			//=========================================================================================================================
			out.println("function beforeSubmit(){		");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Document_Upload_New_Save';");
		    out.println(" 		document.getElementById('BTN_DOC_SAVE').disabled = true; ");
			out.println("		document.Form1.submit();	"); 
			out.println("}		");
					 	
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select2\"+");
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			
			out.println("					if(document.Form1.HID_HELP_TYPE.value==\"FINANCE_NO\"){"); 
			out.println("						help_update_finance_no();"); 
	  		out.println("					}"); 
			
			out.println("				}else{"); 
			out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 			
			out.println("			}else{	"); 
			out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("				}	"); 			
			out.println("		}else{");
			out.println("			clear_data();");
			out.println("	}");
			out.println("	}	"); 
			out.println(" 	if(oBj.valout[2]==' '){");//**
			out.println(" 		Close();"); 
			out.println("	}	"); 
			out.println("}"); 
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");  

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}");
		
			//=========================================================================================================================
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post' enctype='multipart/form-data'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> ");			
			out.println("<input  type='hidden' value='NEW' name='HID_HELP_TYPE'> ");
			

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
			//=====================================================================================================================================================
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - "+m_screenDisplayName+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' name='BTN_DOC_SAVE' id='BTN_DOC_SAVE'  onClick='save_window()' value=\"Save\"></td>");  			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
      		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			//=====================================================================================================================================================
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' border='0' class='table'>"); 

			out.println("<tr>"); 
			out.println(" 	<td width='20%'></td>");
			out.println(" 	<td width='30%'></td>");
			out.println(" 	<td width='50%'></td>");
			out.println("</tr>");			
			
			out.println("<tr>"); 
			out.println(" 	<td><div id='DIV_TXT_DOCUMENT_CODE'>Finance No *</div></td>");
			out.println(" 	<td><input class='txt_input' type='text' name='TXT_FINANCE_NO' onBlur =\"help_update();\" maxlength='20' size='20' onblur=\"\">");
			out.println("		<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>");
			out.println(" 	<td></td>");
			out.println("</tr>");
			out.println("</table>"); 
			out.println("</br>");
			out.println("<table align='center' width='100%' border='0' class='table'>");
			out.println(" 	<tr>"); 
			out.println(" 		<td width='100%'>");
			out.println(" 			<div id='DIV_DOCUMENT_GRID'></div>");
			out.println(" 		</td>"); 
			out.println(" 	</tr>"); 
			out.println("</table>"); 
			out.println("<br>"); 
			//---------------------------------------------------------------------**
			out.println("<br>"); 
			out.println("<table align='center' width='100%' border='0' class='table'>");
			out.println(" 	<tr>"); 
			out.println(" 		<td width='100%'>");
			out.println(" 			<div id='DIV_PREVIOUS_DOCUMENT_GRID'></div>");
			out.println(" 		</td>"); 
			out.println(" 	</tr>"); 
			out.println("</table>"); 
			//---------------------------------------------------------------------**
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>");

			//--------------------------------------------------***/
			out.println("</table>"); 
			out.println("</form>"); 
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