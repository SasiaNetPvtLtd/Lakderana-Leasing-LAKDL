//Option Id is 6.0  
//This File was created by SVA on 13-07-2006 
//FoolowUp Alert Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CO_Followup extends javax.servlet.http.HttpServlet {
    
	/*
    Connection conn;
    Statement stmt;
    java.text.NumberFormat nf,nf1;
    public ResultSet rs;
    public String m_chksql;
    ServletOutputStream out = null;
	*/
    
    public  void service(HttpServletRequest req, HttpServletResponse res) // synchronized
    {
		
		Connection conn=null;
	    Statement stmt=null;
	    java.text.NumberFormat nf=null,nf1=null;
	    ResultSet rs=null;
	    String m_chksql=null;
	    ServletOutputStream out = null;
        
        try {
            
            //************************************************************	
            LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();
            
            LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
            conn = con_method.met_user_validate(req); 
            String m_html_client_url = con_method.html_client_url;
            String m_schema_name = con_method.schema_name;
            String m_servlet_client_url=con_method.servlet_client_url;
            String m_client_name=con_method.client_name;
            String m_client_t3_port=con_method.client_t3_port;
            String m_username 						=  con_method.username;
            String header_name    = con_method.header_name;
            out = res.getOutputStream();
            //out.println("conn="+conn);
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
            CallableStatement callstmt1 =null;
            
            
            //************************************************************
            
            nf = java.text.NumberFormat.getInstance(Locale.US);   
            nf.setMinimumFractionDigits(0);
            
            nf1 = java.text.NumberFormat.getInstance(Locale.US);   
            nf1.setMinimumFractionDigits(4);
            
            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("text/html");
            
            m_chksql = req.getParameter("chksql");
            stmt = conn.createStatement ();
            if (m_chksql.trim().equals("idle")) {
                out.println("idle");
            }
            
            else if(m_chksql.trim().equals("main_page")){
                String m_cat_string = "";
                String m_Followu_no   = req.getParameter("Followu_no");
                String m_Inquiry_no   = req.getParameter("Inquiry_no");
                
                //added by nuwan de silva 08-06-2007-----------------------
                String m_status="N";
                if(req.getParameter("status")!=null){
                    m_status=req.getParameter("status");
                }
                String m_scr_name="N";
                if(req.getParameter("scr_name")!=null){
                    m_scr_name=req.getParameter("scr_name");
                }
                
                //----------------------------------------------------------
                
                String m_sysdate      = "";
                boolean	more = false;
                rs = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
                    "FROM   DUAL ");
                more = rs.next();
                if(more){
                    m_sysdate = rs.getString(1);
                }
                
                
                if(m_Inquiry_no != null){
                    rs = stmt.executeQuery("SELECT FOLLOW_UP_NO,ID_NO "+
                        "FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
                        "WHERE  ID_NO = '"+m_Inquiry_no+"'");
                    more = rs.next();
                    if(more){
                        m_Followu_no = rs.getString(1);
                    }
                }
                
                out.println("<HTML>"); 
                out.println("<HEAD>"); 
                out.println("<TITLE>Follow Up</TITLE>"); 
                out.println("</HEAD>"); 
                out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
                out.println("<SCRIPT language=\"JavaScript\">"); 
                
                
                rs = stmt.executeQuery(CO_methods.getFollowupCat(m_schema_name,"Y",""));
                more = rs.next();
                while(more){
                    m_cat_string = m_cat_string +"<OPTION value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>";
                    more = rs.next();	
                }	
                
                //Check Values Using AJAX
                out.println("function makeRequest(url,opt) {");
                out.println("var http_request = false;");
                out.println("if (window.XMLHttpRequest) {"); // Mozilla, Safari,...
                out.println("    http_request = new XMLHttpRequest();");
                out.println("    if (http_request.overrideMimeType) {");
                out.println("        http_request.overrideMimeType('text/xml');");
                out.println("    }");
                out.println("} else if (window.ActiveXObject) { ");// IE
                out.println("    try {");
                out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
                out.println("    } catch (e) {");
                out.println("        try {");
                out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
                out.println("        } catch (e) {}");
                out.println("    }");
                out.println("}");
                out.println("if (!http_request) {");
                out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
                out.println("    return false;");
                out.println("}");
                out.println(" if(opt=='1'){");
                out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
                out.println("  http_request.open('POST',url, true);");
                out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
                out.println("	 m_send_val = \"chksql=get_advance_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+opt+");
                out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
                
                out.println("  http_request.send(m_send_val);");
                
                out.println(" }else{");
                
                out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt); };");
                out.println("  http_request.open('GET',url, true);");
                //out.println("  window.open(url);");
                out.println("  http_request.send(null);");
                out.println(" }");
                out.println("}");
                
                out.println("function alertContents(http_request) {");
                //out.println(" alert('test');");
                out.println(" if (http_request.readyState == 4) {");
                out.println("    if (http_request.status == 200) {");
                out.println("         price_cal.innerHTML=http_request.responseText; ");
                out.println("    } else {");
                out.println("        alert('There was a problem with the request.');");
                out.println("    }");
                out.println(" }");
                out.println("}");
                
                out.println("function alertGetContents(http_request,opt) {");
                //out.println(" alert('test--'+opt);");
                out.println(" if (http_request.readyState == 4) {");
                out.println("    if (http_request.status == 200) {");
                out.println("      if(opt==\"2\"){");
                //out.println("         alert(http_request.responseText);");
                out.println("         load_followup.innerHTML=http_request.responseText; ");
                out.println("      }else if(opt==\"3\"){");
                out.println("   				data_vec= new Array(); ");
                
                out.println("           var xmlbody=http_request.responseXML.documentElement;");
                out.println("           var vsize=0;");
                
                out.println("  				  for(var i=0;i<xmlbody.childNodes.length;i++){");
                out.println("    				 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
                out.println("      			  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
                out.println("       			vsize++;");
                //alert(data_vec[vsize]);
                out.println("    				}");
                out.println("   		   }");
                
                //out.println("          alert(data_vec);");
                out.println("          addrow(data_vec);");
                
                out.println("      }else if(opt==\"4\"){");
                out.println("   				data_vec= new Array(); ");
                
                out.println("           var xmlbody=http_request.responseXML.documentElement;");
                out.println("           var vsize=0;");
                
                out.println("  				  for(var i=0;i<xmlbody.childNodes.length;i++){");
                out.println("    				 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
                out.println("      			  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
                out.println("       			vsize++;");
                //alert(data_vec[vsize]);
                out.println("    				}");
                out.println("   		   }");
                out.println("          if(data_vec.length>0){");
                out.println("           if(document.Form1.hid_req.value==\"Product\"){");
                out.println("             document.Form1.TXT_PRO_CODE.value=data_vec[0];"); 
                out.println("             document.Form1.TXT_PRO_NAME.value=data_vec[1];"); 
                out.println("           }else if(document.Form1.hid_req.value==\"Division\"){");
                out.println("             document.Form1.TXT_DIVISION.value=data_vec[0];"); 
                out.println("             document.Form1.TXT_DIVISION_NAME.value=data_vec[1];"); 
                out.println("           }else if(document.Form1.hid_req.value==\"SubDivision\"){");
                out.println("             document.Form1.TXT_SUB_DIVISION.value=data_vec[0];"); 
                out.println("             document.Form1.TXT_SUB_DIVISION_NAME.value=data_vec[1];");
                out.println("             check_Foll('4');");
                out.println("           }else if(document.Form1.hid_req.value==\"Foll_Cat\"){");
                out.println("            Curr_no_opts=document.Form1.TXT_ACTION_TOBE_TAKEN.options;");
                out.println("            Curr_no_opts1=document.Form1.TXT_ACTION_TOOK.options;");
                out.println("            Curr_no_opts.length=0;");
                out.println("            Curr_no_opts1.length=0;");
                out.println("            x=0;y=0;");
                out.println("            alert(y+'<'+(data_vec.length/2));");
                out.println("            while(y<(data_vec.length/2)){");
                out.println("              Curr_no_opts[y]= new Option(data_vec[x+1],data_vec[x+0]);");
                out.println("              Curr_no_opts1[y]= new Option(data_vec[x+1],data_vec[x+0]);");
                out.println("              x=x+2;");
                out.println("              y=y+1;");
                out.println("            }");
                out.println("           }else{");
                out.println("             if(document.Form1.hid_cal_date.value=='2'){");
                out.println("              document.Form1.TXT_ACTION_TOOK_DATE.value = data_vec[0];");
                out.println("             }else{");
                out.println("              document.Form1.TXT_next_day.value = data_vec[0];");
                out.println("             } ");
                out.println("           }");
                out.println("          }else{");
                out.println("            if(document.Form1.hid_req.value==\"Product\"){");
                out.println("            }else if(document.Form1.hid_req.value==\"Division\"){");
                out.println("            }else if(document.Form1.hid_req.value==\"SubDivision\"){");
                out.println("            }else if(document.Form1.hid_req.value==\"Foll_Cat\"){");
                out.println("            }else{ ");
                out.println("             alert('Please select a valid date.'); ");
                out.println("            }"); 
                out.println("          }");
                
                out.println("      }");
                out.println("    } else {");
                out.println("        load_followup.innerHTML='';");
                out.println("    }");
                out.println(" }");
                out.println("}");
                
                
                out.println("function addrow( data) {");
                out.println(" str=\"\";");
                out.println(" i=0;");
                out.println(" if(data.length>0){");
                //out.println("	while(i<data.length){");
                out.println("    document.Form1.TXT_FOLLOW_UP_NO.value      =data[0];"); 
                out.println("    document.Form1.TXT_ID_NO.value             =data[1];"); 
                //out.println("    document.Form1.TXT_ACTION_TOBE_TAKEN.value =data[4];"); 
                out.println("    document.Form1.TXT_EFF_VAL_DATE.value      =data[3];"); 
                //out.println("    document.Form1.TXT_ACTION_TOOK.value       =data[6];"); 
                //out.println("    document.Form1.TXT_ACTION_TOOK_DATE.value  =data[7];"); 
                out.println("    document.Form1.TXT_ACTION_SET_FOR.value		 =data[5];"); 
                out.println("    document.Form1.TXT_ACTION_ASS_TO.value		 =data[10];"); 	
                //out.println("    document.Form1.TXT_SCREEN_NAME.value       =data[9];"); 
                //out.println("    document.Form1.TXT_DIVISION_CODE.value     =data[10];"); 
                out.println("    document.Form1.TXT_ENT_REMARKS.value       =data[6];"); 
                out.println("    document.Form1.TXT_REMARKS.value           =data[9];"); 
                out.println("    document.Form1.TXT_ACTION_TOOK_DATE.value  =data[11];"); 
                out.println("    document.Form1.TXT_SUB_DIVISION.value      =data[13];"); 
                out.println("    document.Form1.TXT_DIVISION.value          =data[12];"); 
                out.println("    document.Form1.TXT_PRO_CODE.value				   =data[14];"); 
                out.println("    document.Form1.TXT_DIVISION_NAME.value     =data[15];"); 
                out.println("    document.Form1.TXT_SUB_DIVISION_NAME.value =data[16];"); 
                out.println("    document.Form1.TXT_PRO_NAME.value          =data[17];"); 
                
                //out.println("    document.Form1.TXT_ACTION_ENT_DATE.value   =oBj.valout[13];"); 
                //out.println("	i=i+4;");
                //out.println("	}");
                //out.println("  test1.innerHTML=str;");
                out.println(" }");
                out.println("}");
                //End Of Checking Values
                
                out.println("function check_division(opt,val,act) {");
                out.println("   document.Form1.hid_req.value=\"Division\";");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup_XML?chksql=Division&DIVISION=\"+document.Form1.TXT_DIVISION.value+\"\";");
                //out.println("   window.open(m_url);");
                out.println("   makeRequest(m_url,opt);");
                out.println("}");	
                
                out.println("function check_subdivision(opt,val,act) {");
                out.println("   document.Form1.hid_req.value=\"SubDivision\";");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup_XML?chksql=SubDivision&SUB_DIVISION=\"+document.Form1.TXT_SUB_DIVISION.value+\"\";");
                out.println("   window.open(m_url);");
                out.println("   makeRequest(m_url,opt);");
                out.println("}");	
                
                out.println("function check_Foll(opt,val,act) {");
                out.println("   document.Form1.hid_req.value=\"Foll_Cat\";");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup_XML?chksql=Foll_Cat&SUB_DIVISION=\"+document.Form1.TXT_SUB_DIVISION.value+\"\";");
                //out.println("   window.open(m_url);");
                out.println("   makeRequest(m_url,opt);");
                out.println("}");	
                
                out.println("function check_product(opt,val,act) {");
                out.println("   document.Form1.hid_req.value=\"Product\";");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup_XML?chksql=Product&PROCUCT=\"+document.Form1.TXT_PRO_CODE.value+\"\";");
                //out.println("   window.open(m_url);");
                out.println("   makeRequest(m_url,opt);");
                out.println("}");	
                
                out.println("function check_date(opt,val,act) {");
                out.println("   document.Form1.hid_req.value=\"Date\";");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup_XML?chksql=chk_next_followup&actual=\"+act+\"&next=\"+val+\"\";");
                //out.println("   window.open(m_url);");
                out.println("   makeRequest(m_url,opt);");
                out.println("}");	
                
                out.println("function load_foll(m_stat,opt) {");
                if (m_Followu_no!=null){
                    //out.println("   document.Form1.TXT_FOLLOW_UP_NO.value
                    out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup_XML?chksql=get_followup&fno="+m_Followu_no+"\";");
                    //out.println("   window.open(m_url);");
                    out.println("   makeRequest(m_url,opt);");
                }
                out.println("}");	
                
                out.println("function edit_foll(opt) {");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup_XML?chksql=get_followup&fno=\"+document.Form1.TXT_FOLLOW_UP_NO.value+\"\";");
                //out.println("   window.open(m_url);");
                out.println("   makeRequest(m_url,opt);");
                out.println("}");
                
                out.println("function load_all_foll(m_stat,opt) {");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup&fno="+m_Followu_no+"\";");
                //out.println("   window.open(m_url);");
                out.println("   makeRequest(m_url,opt);");
                out.println("}");
                
                out.println("function validate_data(){"); 
                out.println("m_sub=0;"); 
                out.println("if(document.Form1.hid_option.value==\"NEW\"){"); 
                out.println("if(document.Form1.TXT_ENT_REMARKS.value==\"\"){  "); 
                out.println("EUR.style.color='red';");
                out.println("m_sub = 1;;"); 
                out.println("}"); 
                out.println("if(document.Form1.TXT_ACTION_TOBE_TAKEN.value==\"\"){  "); 
                out.println("ATBT.style.color='red';");
                out.println("m_sub = 1;;"); 
                out.println("}"); 
                out.println("if(document.Form1.TXT_EFF_VAL_DATE.value==\"\"){  "); 
                out.println("TDA.style.color='red';");
                out.println("m_sub = 1;;"); 
                out.println("}");
                out.println("if(document.Form1.TXT_DIVISION.value==\"\"){  "); 
                out.println("DCO.style.color='red';");
                out.println("m_sub = 1;;"); 
                out.println("}"); 
                out.println("if(document.Form1.TXT_SUB_DIVISION.value==\"\"){  "); 
                out.println("SDC.style.color='red';");
                out.println("m_sub = 1;;"); 
                out.println("}"); 
                out.println("if(document.Form1.TXT_PRO_CODE.value==\"\"){  "); 
                out.println("PCO.style.color='red';");
                out.println("m_sub = 1;"); 
                out.println("}"); 
                out.println("if(document.Form1.TXT_ACTION_ASS_TO.value==\"\"){  "); 
                out.println("AAT.style.color='red';");
                out.println("m_sub = 1;"); 
                out.println("}"); 
                
                
                out.println("}else if(document.Form1.hid_option.value==\"EDIT\"){"); 
                
                out.println("if(document.Form1.TXT_STATUS.value!=\"--\"){");
                out.println("if(document.Form1.TXT_ACTION_TOOK_DATE.value==\"\"){  "); 
                out.println("AAD.style.color='red';");
                out.println("m_sub = 1;;"); 
                out.println("}"); 
                out.println("if(document.Form1.TXT_ACTION_TOOK.value==\"\"){  "); 
                out.println("AT.style.color='red';");
                out.println("m_sub = 1;;"); 
                out.println("}"); 
                out.println("}"); 
                
                out.println("}");
                
                out.println("if(m_sub=='1'){");
                out.println("return false;"); 
                out.println("}"); 
                out.println("else{"); 
                out.println("return true;"); 
                out.println("}"); 
                out.println("}"); 
                
                out.println("function before_submit(){ "); 
                out.println("	 if(validate_data()){"); 
                out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
                out.println("    for (var i=0; i < document.Form1.elements.length; i++ ) {");
                out.println("     document.Form1.elements[i].disabled=false;");
                out.println("    }");
                out.println("		 document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Save_Followup';");  
                out.println("		 document.Form1.submit();	"); 
                out.println("		}"); 
                out.println("	 }"); 
                out.println("} "); 
                
                out.println("function load_lock(){	"); 
                //out.println("document.oncontextmenu=new Function(\"return false\");"); 
                if (m_Followu_no!=null){
                    out.println("  dis_text();	"); 
                }
                out.println("}	"); 
                
                out.println("function clear_window(){	"); 
                out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
                if(m_Followu_no==null){
                    out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=main_page';"); 
                }
                out.println("		}"); 
                out.println("}"); 
                
                out.println("function close_window1(){	"); 
                out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
                if(m_Followu_no==null){
                    out.println("		close_window();"); 
                }else{
                    out.println("		window.close();");
                }
                out.println("		}"); 
                out.println("}");
                
                out.println("function new_window(){	"); 
                out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup';"); 
                out.println("}"); 
                out.println(""); 
                out.println(""); 
                
                out.println("function save_window(){	"); 
                out.println("before_submit();"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function load_help_msg() {"); 
                out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_Followup\";"); 
                out.println("    HelpBox_msg(m_help_message);"); 
                out.println("}"); 	
                out.println("function HelpBox_msg(m_help_message) {"); 
                out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
                out.println("  \"&help_message_in=\"+m_help_message);"); 
                out.println("}"); 
                
                out.println("function load_roll_value(m_val){"); 
                out.println("help_box.innerHTML=\" Follow Up - \"+m_val;"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function load_roll_out_value(){");
                out.println("help_box.innerHTML=\" Follow Up - \"+document.Form1.hid_status.value;"); 
                out.println("document.Form1.hid_status_close.value='"+m_status+"'");  //added by nuwan de silva 06-08-2007
                out.println("document.Form1.hid_my_scr_name.value='"+m_scr_name+"'");  //added by nuwan de silva 17-10-2007
                out.println("}"); 
                
                out.println("function load_screen_status(m_val){"); 
                out.println("    document.Form1.hid_option.value    =m_val;"); 
                out.println("if(m_val==\"NEW\"){"); 
                //out.println("new_window();");
                //out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
                out.println("document.Form1.TXT_ID_NO.disabled=false;"); 
                out.println("document.Form1.TXT_ACTION_TOBE_TAKEN.disabled=false;"); 
                out.println("document.Form1.TXT_EFF_VAL_DATE.disabled=false;"); 
                out.println("document.Form1.TXT_ACTION_SET_FOR.disabled=false;"); 
                out.println("document.Form1.TXT_ENT_REMARKS.disabled=false;"); 
                
                out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
                out.println("else if(m_val==\"HELP\"){"); 
                out.println("load_help_msg();"); 
                out.println("}"); 
                out.println("else if(m_val!=\"EDIT\"){"); 
                out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
                out.println("document.Form1.TXT_ID_NO.disabled=true;"); 
                out.println("document.Form1.TXT_ACTION_TOBE_TAKEN.disabled=true;"); 
                out.println("document.Form1.TXT_EFF_VAL_DATE.disabled=true;"); 
                //out.println("document.Form1.TXT_ACTION_TOOK.disabled=true;"); 
                //out.println("document.Form1.TXT_ACTION_TOOK_DATE.disabled=true;"); 
                out.println("document.Form1.TXT_ACTION_SET_FOR.disabled=true;"); 
                //out.println("document.Form1.TXT_SCREEN_NAME.disabled=true;"); 
                //out.println("document.Form1.TXT_DIVISION_CODE.disabled=true;"); 
                out.println("document.Form1.TXT_ENT_REMARKS.disabled=true;"); 
                //out.println("document.Form1.TXT_REMARKS.disabled=true;"); 
                //out.println("document.Form1.TXT_ACTION_ENT_DATE.disabled=true;"); 
                
                out.println("}"); 
                out.println("else{");
                out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
                out.println("document.Form1.OPTION_DESC.value=m_val;"); 
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
                
                out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
                out.println("    oBj = new MyDialog();"); 
                out.println("    oBj.valout[1]  = \" \";"); 
                out.println("    oBj.valout[2]  = \" \";"); 
                out.println("    oBj.valout[3]  = \" \";"); 
                out.println("	"); 
                /*out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_Help_Servlet?class_in=\"+client_name+\"AF_CO_help_select\"+"); 
                out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
                out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
                out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
                */
                //out.println("alert('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
                out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                
                out.println("	"); 
                out.println("	if(oBj.valout[1] !=\" \"){"); 
                out.println("	if(oBj.valout[1] !=\"Close\"){"); 
                out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
                out.println("	if(oBj.valout[1]!=\"Next\"){"); 
                out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
                out.println("		 help_update_value_assign_99();"); 
                out.println("		}"); 
                out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
                out.println("		 help_value_assign_1();"); 
                out.println("		}"); 
                out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
                out.println("		 help_value_assign_2();"); 
                out.println("		}"); 
                out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
                out.println("		 help_division();"); 
                out.println("		}"); 
                out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
                out.println("		 help_sub_division();"); 
                out.println("		}"); 
                out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
                out.println("		 help_product();"); 
                out.println("		}"); 
                
                out.println("	}"); 
                out.println("	else{"); 
                out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
                out.println("		return false;"); 
                out.println("	} "); 
                out.println("	}"); 
                out.println("	else{	"); 
                out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
                out.println("	}	"); 
                out.println("	}		"); 
                out.println("	}	"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
                out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
                out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
                out.println("}"); 
                out.println(""); 
                out.println("function help_button_3() {"); 
                out.println("    document.Form1.hid_help_type.value=\"1\";"); 
                out.println("    m_sql = \"FollowupSql\";"); 
                out.println("    m_criteria = document.Form1.TXT_FOLLOW_UP_NO.value+\"@Y@\";"); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,'');"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function help_button_3() {"); 
                out.println("    document.Form1.TXT_FOLLOW_UP_NO.value=oBj.valout[2];"); 
                out.println("}"); 
                
                out.println("function help_button_1() {"); 
                out.println("    document.Form1.hid_help_type.value=\"1\";"); 
                out.println("    m_sql = \"FollowIDSql\";"); 
                out.println("    m_criteria = document.Form1.TXT_ID_NO.value+\"@Y@\";"); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,'');"); 
                out.println("}"); 
                out.println(""); 
                
                
                out.println("function help_division() {"); 
                out.println("    document.Form1.TXT_DIVISION.value=oBj.valout[2];"); 
                out.println("    document.Form1.TXT_DIVISION_NAME.value=oBj.valout[3];"); 
                out.println("}"); 
                
                out.println("function help_but_division() {"); 
                out.println("    document.Form1.hid_help_type.value=\"3\";"); 
                out.println("    m_sql = \"DivisionSql\";"); 
                out.println("    m_criteria = document.Form1.TXT_DIVISION.value+\"@Y@\";"); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,'');"); 
                out.println("}"); 
                out.println("");
                
                
                out.println("function help_sub_division() {"); 
                out.println("    document.Form1.TXT_SUB_DIVISION.value=oBj.valout[2];"); 
                out.println("    document.Form1.TXT_SUB_DIVISION_NAME.value=oBj.valout[3];"); 
                out.println("    check_Foll('4');");
                out.println("}"); 
                
                out.println("function help_but_sub_div() {");
                out.println(" if(document.Form1.TXT_DIVISION.value!=\"\"){");
                out.println("    document.Form1.hid_help_type.value=\"4\";"); 
                out.println("    m_sql = \"SubDivisionSql\";"); 
                out.println("    m_criteria = document.Form1.TXT_SUB_DIVISION.value+\"@\"+document.Form1.TXT_DIVISION.value+\"@Y@\";"); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,'');"); 
                out.println(" }else{");
                out.println("    alert('Please select a division code and continue.');");
                out.println(" }");
                out.println("}"); 
                
                out.println("function help_product() {"); 
                out.println("    document.Form1.TXT_PRO_CODE.value=oBj.valout[2];"); 
                out.println("    document.Form1.TXT_PRO_NAME.value=oBj.valout[3];"); 
                out.println("}"); 
                
                out.println("function help_but_pro() {"); 
                out.println("    document.Form1.hid_help_type.value=\"5\";"); 
                out.println("    m_sql = \"ProductSql\";"); 
                out.println("    m_criteria = document.Form1.TXT_PRO_CODE.value+\"@Y@\";"); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,'');"); 
                out.println("}"); 
                out.println("");
                
                out.println("function help_value_assign_1() {"); 
                out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[2];"); 
                out.println("}"); 
                
                out.println("function help_button_2() {"); 
                out.println("    document.Form1.hid_help_type.value=\"2\";"); 
                out.println("    m_sql = \"UserSql\";"); 
                out.println("    m_criteria = document.Form1.TXT_ACTION_ASS_TO.value+\"@Y@\";"); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,'');"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function help_value_assign_2() {"); 
                out.println("    document.Form1.TXT_ACTION_ASS_TO.value=oBj.valout[2];"); 
                out.println("}"); 
                
                out.println("function help_update() {"); 
                out.println("    document.Form1.hid_help_type.value=\"99\";"); 
                out.println("    m_sql = \"FollowupSql\";"); 
                out.println("    if(document.Form1.OPTION_DESC.value==\"EDIT\" || document.Form1.OPTION_DESC.value==\"DACT\"){ ");
                out.println("    m_criteria = document.Form1.TXT_FOLLOW_UP_NO.value+\"@\"+\"Y@\";"); 
                out.println("    } ");
                out.println("    else{");
                out.println("    m_criteria = document.Form1.TXT_FOLLOW_UP_NO.value+\"@\"+\"N@\";}"); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,'');"); 
                out.println("}"); 
                
                out.println("function help_update_value_assign_99() {"); 
                out.println("    document.Form1.TXT_FOLLOW_UP_NO.value=oBj.valout[2];"); 
                out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[3];"); 
                out.println("    document.Form1.TXT_ACTION_TOBE_TAKEN.value=oBj.valout[4];"); 
                out.println("    document.Form1.TXT_EFF_VAL_DATE.value=oBj.valout[5];"); 
                out.println("    document.Form1.TXT_ACTION_ASS_TO.value=oBj.valout[6];"); 
                //out.println("    document.Form1.TXT_ACTION_TOOK.value=oBj.valout[6];"); 
                //out.println("    document.Form1.TXT_ACTION_TOOK_DATE.value=oBj.valout[7];"); 
                out.println("    document.Form1.TXT_ACTION_SET_FOR.value=oBj.valout[8];"); 
                //out.println("    document.Form1.TXT_SCREEN_NAME.value=oBj.valout[9];"); 
                //out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[10];"); 
                out.println("    document.Form1.TXT_ENT_REMARKS.value=oBj.valout[7];"); 
                out.println("    document.Form1.TXT_SUB_DIVISION.value        =oBj.valout[9];"); 
                out.println("    document.Form1.TXT_DIVISION.value            =oBj.valout[10];"); 
                out.println("    document.Form1.TXT_PRO_CODE.value						=oBj.valout[11];"); 
                //out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[12];"); 
                //out.println("    document.Form1.TXT_ACTION_ENT_DATE.value=oBj.valout[13];"); 
                
                out.println("}"); 
                
                out.println("function load_edit_window(i,foll_no,type) {");
                out.println("   ");
                out.println("   document.Form1.hid_option.value      =type;"); 
                out.println("   if(type=='EDIT'){"); 
                out.println("   document.Form1.hid_status.value      ='Edit';"); 
                out.println("    document.Form1.OPTION_DESC.value    ='Edit';"); 
                out.println("   }"); 
                out.println("   load_roll_out_value();"); 
                
                out.println("    document.Form1.TXT_FOLLOW_UP_NO.value     =foll_no"); 
                out.println("    document.Form1.TXT_ID_NO.value            =document.Form1.elements[\"ID_NO\"+i].value;"); 
                out.println("    for(j=0;j<document.Form1.TXT_ACTION_TOBE_TAKEN.length;j++){");
                //out.println("     alert(document.Form1.elements[\"ACTION_TOBE_TAKEN\"+i].value+'=='+document.Form1.TXT_ACTION_TOBE_TAKEN.options[j].text);");
                out.println("     if(document.Form1.elements[\"ACTION_TOBE_TAKEN\"+i].value==document.Form1.TXT_ACTION_TOBE_TAKEN.options[j].text){"); 
                out.println("      document.Form1.TXT_ACTION_TOBE_TAKEN.selectedIndex = j;"); 
                out.println("      document.Form1.TXT_ACTION_TOOK.selectedIndex       = j;"); 
                out.println("      break;");
                out.println("     }");
                out.println("    }");
                out.println("    document.Form1.TXT_EFF_VAL_DATE.value     =document.Form1.elements[\"EFF_VAL_DATE\"+i].value;"); 
                //out.println("    document.Form1.TXT_ACTION_TOOK.value      =document.Form1.elements[\"ACTION_TOOK\"+i].value;"); 
                //out.println("    document.Form1.TXT_ACTION_TOOK_DATE.value =document.Form1.elements[\"ACTION_TOOK_DATE\"+i].value;"); 
                out.println("    document.Form1.TXT_ACTION_SET_FOR.value   =document.Form1.elements[\"Ent_User_\"+i].value;"); 
                out.println("    document.Form1.TXT_ACTION_ASS_TO.value    =document.Form1.elements[\"ACTION_SET_FOR\"+i].value;"); 
                //out.println("    document.Form1.TXT_SCREEN_NAME.value      =document.Form1.elements[\"SCREEN_NAME\"+i].value;"); 
                //out.println("    document.Form1.TXT_DIVISION_CODE.value    =document.Form1.elements[\"DIVISION_CODE\"+i].value;"); 
                out.println("    document.Form1.TXT_ENT_REMARKS.value      =document.Form1.elements[\"ENT_REMARKS\"+i].value;"); 
                //out.println("    document.Form1.TXT_REMARKS.value          =document.Form1.elements[\"REMARKS\"+i].value;"); 
                //out.println("    document.Form1.TXT_ACTION_ENT_DATE.value  =document.Form1.elements[\"ACTION_ENT_DATE\"+i].value;"); 
                out.println("    document.Form1.TXT_STATUS.selectedIndex  =1;"); 
                out.println("    load_div();");
                out.println("    edit_foll('3');");
                out.println("    dis_text();");
                out.println("}"); 
                
                out.println("function dis_text(){"); 
                out.println("    document.Form1.TXT_FOLLOW_UP_NO.disabled        =false;"); 
                out.println("    document.Form1.TXT_ID_NO.disabled               =true;"); 
                out.println("    document.Form1.TXT_ACTION_TOBE_TAKEN.disabled   =true;"); 
                out.println("    document.Form1.TXT_EFF_VAL_DATE.disabled        =true;"); 
                out.println("    document.Form1.TXT_ACTION_SET_FOR.disabled      =true;"); 
                out.println("    document.Form1.TXT_ENT_REMARKS.disabled         =true;"); 
                out.println("    document.Form1.TXT_ACTION_ASS_TO.disabled       =false;");
                out.println("    document.Form1.BUT_TXT_ID_NO.disabled           =true;");
                out.println("    document.Form1.BUT_TXT_USER_NO.disabled         =false;");
                out.println("    document.Form1.TXT_ACTION_TOOK.disabled         =false;"); 
                out.println("    document.Form1.TXT_ACTION_TOOK_DATE.disabled    =false;"); 
                out.println("    document.Form1.TXT_REMARKS.disabled             =false;"); 
                out.println("    document.Form1.TXT_STATUS.disabled              =false;"); 
                out.println("    document.Form1.TXT_SUB_DIVISION.disabled        =true;"); 
                out.println("    document.Form1.TXT_DIVISION.disabled            =true;"); 
                out.println("    document.Form1.TXT_PRO_CODE.disabled						 =true;"); 
                out.println("    document.Form1.BUT_PRO_MAIN.disabled            =true;"); 
                out.println("    document.Form1.BUT_SUB_DIV.disabled             =true;"); 
                out.println("    document.Form1.BUT_DIV_MAIN.disabled						 =true;"); 
                out.println("}"); 
                
                out.println("function ena_text(){"); 
                out.println("    document.Form1.TXT_FOLLOW_UP_NO.disabled        =true;"); 
                out.println("    document.Form1.TXT_ID_NO.disabled               =false;"); 
                out.println("    document.Form1.TXT_ACTION_TOBE_TAKEN.disabled   =false;"); 
                out.println("    document.Form1.TXT_EFF_VAL_DATE.disabled        =false;"); 
                out.println("    document.Form1.TXT_ACTION_SET_FOR.disabled      =false;"); 
                out.println("    document.Form1.TXT_ENT_REMARKS.disabled         =false;"); 
                out.println("    document.Form1.TXT_ACTION_ASS_TO.disabled       =false;");
                out.println("    document.Form1.BUT_TXT_ID_NO.disabled           =false;");
                out.println("    document.Form1.BUT_TXT_USER_NO.disabled         =false;");
                out.println("    document.Form1.TXT_ACTION_TOOK.disabled         =true;"); 
                out.println("    document.Form1.TXT_ACTION_TOOK_DATE.disabled    =true;"); 
                out.println("    document.Form1.TXT_REMARKS.disabled             =true;"); 
                out.println("    document.Form1.TXT_STATUS.disabled              =true;"); 
                out.println("    document.Form1.TXT_SUB_DIVISION.disabled        =false;"); 
                out.println("    document.Form1.TXT_DIVISION.disabled            =false;"); 
                out.println("    document.Form1.TXT_PRO_CODE.disabled						 =false;"); 
                out.println("    document.Form1.BUT_PRO_MAIN.disabled            =false;"); 
                out.println("    document.Form1.BUT_SUB_DIV.disabled             =false;"); 
                out.println("    document.Form1.BUT_DIV_MAIN.disabled						 =false;"); 
                out.println("    document.Form1.TXT_STATUS.selectedIndex         =0;"); 
                out.println("    load_div();");
                out.println("}"); 
                
                out.println("function load_div(){"); 
                out.println(" if(document.Form1.TXT_STATUS.value==\"INPROGRESS\"){");
                out.println("   tnextd.innerHTML='Next Date'; "); 
                out.println("   bnextd.innerHTML=\"<input class='txt_input' type='text' name='TXT_next_day' maxlength='10' value="+m_sysdate+"><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar    </a>\"+ "); 
                
                out.println("\"<br>HH: <select name='NEXT_HOURS' class='txt_input' style='width:40px;'>\"+");
                out.println("\"<option value='07'>07</option><option value='08'>08</option>\"+");
                out.println("\"<option value='09'>09</option><option value='10'>10</option>\"+");
                out.println("\"<option value='11'>11</option><option value='12'>12</option>\"+");
                out.println("\"<option value='13'>13</option><option value='14'>14</option>\"+");
                out.println("\"<option value='15'>15</option><option value='16'>16</option>\"+");
                out.println("\"<option value='17'>17</option><option value='18'>18</option>\"+");
                out.println("\"<option value='19'>19</option><option value='20'>20</option>\"+");
                out.println("\"</select>\"+");
                out.println("\"MI: <select name='NEXT_MIN' class='txt_input' style='width:40px;'>\"+");
                out.println("\"<option value='00'>00</option><option value='15'>15</option>\"+");
                out.println("\"<option value='30'>30</option><option value='45'>45</option>\"+");
                out.println("\"</select>\"+");
                out.println("\"\";"); 
                
                
                out.println("   bnexta.innerHTML='Next Action To Be Taken'; "); 
                out.println("   tnexta.innerHTML=\"<SELECT class='txt_input' name='TXT_NEXT_ACTION_TOOK'>"+m_cat_string+"</SELECT>\"; "); 
                
                out.println(" }else{"); 
                out.println("   tnextd.innerHTML=''; "); 
                out.println("   bnextd.innerHTML=\"\"; ");
                out.println("   tnexta.innerHTML=''; "); 
                out.println("   bnexta.innerHTML=\"\"; ");
                
                out.println(" }"); 
                out.println("}"); 
                
                out.println("function befor_end(m_obj) {");
                out.println("   m_obj.focus();");
                out.println("}");
                
                out.println("function load_calendar(num) {");
                out.println(" document.Form1.hid_cal_date.value=num;"); 
                out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
                //out.println(" load_c_date(document.Form1.hid_cal_date.value);");
                out.println("}");
                
                out.println("function load_c_date(val) {");
                out.println("  if(document.Form1.hid_cal_date.value=='1'){");
                out.println("     document.Form1.TXT_EFF_VAL_DATE.value=val;");
                out.println("  }else if(document.Form1.hid_cal_date.value=='2'){"); 
                out.println("     check_date('4',val,document.Form1.TXT_EFF_VAL_DATE.value);");// document.Form1.TXT_next_day.value=val;");
                //out.println("     document.Form1.TXT_ACTION_TOOK_DATE.value=val;");
                out.println("  }else if(document.Form1.hid_cal_date.value=='3'){"); 
                out.println("     check_date('4',val,document.Form1.TXT_ACTION_TOOK_DATE.value);");// document.Form1.TXT_next_day.value=val;");
                out.println("  }");				
                out.println("}");				
                
                out.println("function load_data(num) {");
                out.println(" if(num!=\"\"){");	
                out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\"+document.Form1.TXT_FOLLOW_UP_NO.value+\"\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
                //out.println(" load_c_date(document.Form1.hid_cal_date.value);");
                out.println(" }else{");
                out.println("   alert('Please enter ID Number and continue!');");
                out.println(" }");
                out.println("}");
                
                  out.println("function load_data1(val1,val2) {");
                out.println("show_transaction_history_new(val1,val2) "); 
                out.println("}");
                
                
                out.println("function show_transaction_history_new(val1,val2){ "); 
                out.println("m_url='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val1+'&client_code='+val2;"); 
                out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
                out.println("}");
                
                out.println("function load_history(num) {");
                out.println("	if(num!=''){ ");
                out.println("	  popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_History&deal_no=\"+num+\"\", \"oBj\",\"left=100,top=200,width=650,height=400\");"); 
                out.println("	}else{");
                out.println("	  alert('Please enter Followup Number and continue!');");
                out.println("	}");
                //out.println(" load_c_date(document.Form1.hid_cal_date.value);");
                out.println("}");
                
                out.println("</script>"); 
                if(m_Followu_no==null){
                    out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();load_lock();load_foll('','3');load_all_foll('','2');ena_text();\">"); 
                }else{
                    out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();load_lock();load_foll('','3');load_all_foll('','2');dis_text();\">"); 
                }
                out.println("<FORM NAME='Form1' method='post'>"); 
                out.println("<input type='hidden' name='Hid_scr_name' value='AF_FOLLOWUP_ENTRY' > ");
                out.println("<input type='hidden' name='TXT_SCREEN_NAME' value='AF_FOLLOWUP_ENTRY' > ");
                out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
                out.println("<INPUT TYPE='Hidden' NAME='hid_req' VALUE=\"\">");
                out.println("<INPUT TYPE='Hidden' NAME='hid_status_close' VALUE=\"\">"); // added by nuwan de silva 06-08-2007
                out.println("<INPUT TYPE='Hidden' NAME='hid_my_scr_name' VALUE=\"\">"); // added by nuwan de silva 17-10-2007
                
                
                out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
                if(m_Followu_no==null){
                    out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
                    out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
                    out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
                    out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
                    
                }else{
                    out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Edit\">");
                    out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"Edit\">");
                    out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Window\">"); 
                    out.println("<input type=hidden name=\"OPTION_DESC\" value=\"EDIT\">");
                }
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
                out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
                out.println("<tr>"); 
                out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
                out.println("</tr>"); 
                out.println("<tr>"); 
                out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Follow Up</td>"); 
                out.println("</tr>"); 
                out.println("<tr>"); 
                out.println("<td  height='10px' class='pdn_txtpos'>"); 
                out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
                if(m_Followu_no==null){
                    out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\");ena_text()' value=\"New\"></td>");  
                    out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\");dis_text()' value=\"Edit\"></td>");  
                }else{
                    out.println("<tr><td width='10%' align='center'></td>");  
                    out.println("<td align='center'>&nbsp;</td>");  
                    
                }
                //out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
                //out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
                out.println("<td width='10%' >&nbsp;</td>");  
                //out.println("<td width='10%' >&nbsp;</td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");'  value=\"Help\" class=mainbut onclick=HelpBox_msg();></td>");
                //out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
                if(m_Followu_no==null){
                    out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
                }
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window1()' value=\"Close\"></td>"); 
                
                //out.println("<td >&nbsp;</td>");  
                
                out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
                out.println("</table>");  
                out.println("</td></tr><tr>");  
                out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
                out.println("</tr><tr>");  
                out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
                out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
                out.println("<tr class='tr_input'>");  
                out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
                out.println("</tr>");  
                out.println("</table>");  
                
                out.println("<table align='center' width='100%' class='table'>"); 
                
                out.println("<tr >"); 
                //out.println("<td width='1%'></td>"); 
                out.println("<td width='20%' ID=FNO>Follow Up No *</td>"); 
                out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FOLLOW_UP_NO' maxlength='16' size='16' onblur=\"makeRequest(document.Form1.TXT_FOLLOW_UP_NO)\" >"); 
                out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update()\" disabled>  <input class='but_input' type='button' name='BUT_HELP_HIS' value=\"History\" onClick=\"load_history(document.Form1.TXT_FOLLOW_UP_NO.value)\" ></td>"); 
                //out.println("<td width='*%'></td>"); 
                /*out.println("</tr>"); 
                out.println("<tr>"); 
                //out.println("<td width='1%'></td>"); */
                out.println("<td width='20%' ID=INO>ID No</td>"); 
                out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ID_NO' maxlength='15' size='15'>"); 
                out.println("<input class='but_input' type='button' name='BUT_TXT_ID_NO' value=\"...\" onClick=\"help_button_1()\">  <input class='but_input' type='button' name='BUT_HELP_DET' value=\"Detail\" onClick=\"load_data(document.Form1.TXT_ID_NO.value)\" ></td>"); 
                //out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                
                out.println("<tr >"); 
                //out.println("<td width='1%'></td>"); 
                out.println("<td width='20%' ID=DCO>Division Code*</td>"); 
                out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DIVISION' maxlength='10' size='16' onblur=\"check_division('4')\" disabled>"); 
                out.println("<input class='but_input' type='button' name='BUT_DIV_MAIN' value=\"...\" onClick=\"help_but_division()\" > </td>"); 
                //out.println("<td width='*%'></td>"); 
                /*out.println("</tr>"); 
                out.println("<tr>"); 
                //out.println("<td width='1%'></td>"); */
                out.println("<td width='20%' >Division Name</td>"); 
                out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DIVISION_NAME' maxlength='10' size='15' style=\"width:200px;\" disabled>"); 
                out.println("</td>"); 
                //out.println("<td width='*%'></td>"); 
                out.println("</tr>");
                
                out.println("<tr >"); 
                //out.println("<td width='1%'></td>"); 
                out.println("<td width='20%' ID=SDC>Sub Division Code*</td>"); 
                out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_DIVISION' maxlength='10' size='16' onblur=\"check_subdivision('4')\" disabled>"); 
                out.println("<input class='but_input' type='button' name='BUT_SUB_DIV' value=\"...\" onClick=\"help_but_sub_div()\" > </td>"); 
                //out.println("<td width='*%'></td>"); 
                /*out.println("</tr>"); 
                out.println("<tr>"); 
                //out.println("<td width='1%'></td>"); */
                out.println("<td width='20%' >Sub Division Name</td>"); 
                out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_DIVISION_NAME' maxlength='10' size='15' style=\"width:200px;\" disabled>"); 
                out.println("</td>"); 
                //out.println("<td width='*%'></td>"); 
                out.println("</tr>");
                
                
                out.println("<tr >"); 
                //out.println("<td width='1%'></td>"); 
                out.println("<td width='20%' ID=PCO>Product Code*</td>"); 
                out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PRO_CODE' maxlength='10' size='16' onblur=\"check_product('4')\" disabled>"); 
                out.println("<input class='but_input' type='button' name='BUT_PRO_MAIN' value=\"...\" onClick=\"help_but_pro()\" ></td>"); 
                //out.println("<td width='*%'></td>"); 
                /*out.println("</tr>"); 
                out.println("<tr>"); 
                //out.println("<td width='1%'></td>"); */
                out.println("<td width='20%' >Product Name</td>"); 
                out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PRO_NAME' maxlength='15' size='15' style=\"width:200px;\" disabled>"); 
                out.println("</td>"); 
                //out.println("<td width='*%'></td>"); 
                out.println("</tr>");
                
                
                
                out.println("<tr >"); 
                //out.println("<td width='1%'></td>"); 
                out.println("<td >Assigned By </td>"); 
                out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_SET_FOR' maxlength='10' size='10' value=\""+m_username+"\" disabled></td>"); 
                out.println("<td id=AAT>Action Assign To*</td>"); 
                out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_ASS_TO' maxlength='10' size='10'>"); 
                out.println("<input class='but_input' type='button' name='BUT_TXT_USER_NO' value=\"...\" onClick=\"help_button_2()\"></td>"); 
                //out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                out.println("<tr >"); 
                //out.println("<td width='1%'></td>"); 
                //out.println("<td width='*%'></td>"); 
                /*out.println("</tr>"); 
                out.println("<tr >"); 
                out.println("<td width='1%'></td>"); */
                out.println("<td ID=TDA>Action Target Date *</td>"); 
                if(m_Followu_no==null){
                    out.println("<td ><input class='txt_input' type='text' name='TXT_EFF_VAL_DATE' maxlength='10' size='10'><a href style='{cursor:hand; }' onclick=\"load_calendar('1')\">   Calendar   </a>");
                    out.println("<br>HH: <select name=\"HOURS\" class='txt_input' style=\"width:40px;\">");
                    out.println("<option value=\"07\">07</option><option value=\"08\">08</option>");
                    out.println("<option value=\"09\">09</option><option value=\"10\">10</option>");
                    out.println("<option value=\"11\">11</option><option value=\"12\">12</option>");
                    out.println("<option value=\"13\">13</option><option value=\"14\">14</option>");
                    out.println("<option value=\"15\">15</option><option value=\"16\">16</option>");
                    out.println("<option value=\"17\">17</option><option value=\"18\">18</option>");
                    out.println("<option value=\"19\">19</option><option value=\"20\">20</option>");
                    out.println("</select>");
                    out.println("MI: <select name=\"MIN\" class='txt_input' style=\"width:40px;\">");
                    out.println("<option value=\"00\">00</option><option value=\"15\">15</option>");
                    out.println("<option value=\"30\">30</option><option value=\"45\">45</option>");
                    out.println("</select>");
                    out.println("");
                    out.println("</td>"); 
                }else{
                    out.println("<td ><input class='txt_input' type='text' name='TXT_EFF_VAL_DATE' maxlength='10' size='10'></td>"); 
                }
                out.println("<td ID=AAD>Action Actual Date *</td>"); 
                out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_TOOK_DATE' maxlength='10' size='10'><a href style='{cursor:hand; }' onclick=\"load_calendar('2')\">   Calendar   </a></td>"); 
                //out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                out.println("<tr >"); 
                //out.println("<td width='1%'></td>"); 
                out.println("<td ><DIV id='ATBT'  class=div_input>Action To Be Taken *</DIV></td>"); 
                out.println("<td ><SELECT class='txt_input' name='TXT_ACTION_TOBE_TAKEN'>"); 
                /*rs = stmt.executeQuery(CO_methods.getFollowupCat(m_schema_name,"Y",""));
            boolean	more = rs.next();
                while(more){
                out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
                        more = rs.next();	
                }	*/
                out.println(""+m_cat_string+"");
                out.println("</SELECT>");
                out.println("</td>");
                //out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_TOBE_TAKEN' maxlength='10' size='10'></td>"); 
                //out.println("<td width='*%'></td>"); 
                /*out.println("</tr>"); 
                out.println("<tr >"); 
                out.println("<td width='1%'></td>"); */
                out.println("<td ID=AT>Action Taken *</td>"); 
                out.println("<td ><SELECT class='txt_input' name='TXT_ACTION_TOOK'>"); 
                /*rs = stmt.executeQuery(CO_methods.getFollowupCat(m_schema_name,"Y",""));
                more = rs.next();
                while(more){
                out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
                        more = rs.next();	
                }	*/
                out.println(""+m_cat_string+"");
                
                out.println("</SELECT>");
                out.println("</td>");//out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_TOOK' maxlength='10' size='10'></td>"); 
                //out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                out.println("<tr >"); 
                //out.println("<td width='1%'></td>"); 
                //out.println("<td width='*%'></td>"); 
                out.println("<td ID=EUR>Entered User Remarks *</td>"); 
                out.println("<td ><input class='txt_input' type='text' name='TXT_ENT_REMARKS' maxlength='200' size='200'></td>"); 
                out.println("<td ID=UR>Remarks *</td>"); 
                out.println("<td ><input class='txt_input' type='text' name='TXT_REMARKS' maxlength='200' size='200'></td>"); 
                //out.println("<td width='*%'></td>");
                //out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                
                if(m_Followu_no!=null){
                    
                    out.println("<tr >"); 
                    out.println("<td >Status</td>"); 
                    out.println("<td ><select name='TXT_STATUS' class='txt_input' onchange=load_div()>");
                    out.println("<option value=\"--\">--</option>");
                    out.println("<option value=INPROGRESS selected>In progress</option>");
                    out.println("<option value=COMPLETED >Completed  </option>");
                    out.println("</select>");
                    out.println("</td>"); //<input class='txt_input' type='text' name='TXT_STATUS' >
                    out.println("<td id=tnextd>Next Date</td>"); 
                    out.println("<td id=bnextd><input class='txt_input' type='text' name='TXT_next_day' maxlength='10' value="+m_sysdate+"><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar   </a>");
                    out.println("<br>HH: <select name=\"NEXT_HOURS\" class='txt_input' style=\"width:40px;\">");
                    out.println("<option value=\"07\">07</option><option value=\"08\">08</option>");
                    out.println("<option value=\"09\">09</option><option value=\"10\">10</option>");
                    out.println("<option value=\"11\">11</option><option value=\"12\">12</option>");
                    out.println("<option value=\"13\">13</option><option value=\"14\">14</option>");
                    out.println("<option value=\"15\">15</option><option value=\"16\">16</option>");
                    out.println("<option value=\"17\">17</option><option value=\"18\">18</option>");
                    out.println("<option value=\"19\">19</option><option value=\"20\">20</option>");
                    out.println("</select>");
                    out.println("MI: <select name=\"NEXT_MIN\" class='txt_input' style=\"width:40px;\">");
                    out.println("<option value=\"00\">00</option><option value=\"15\">15</option>");
                    out.println("<option value=\"30\">30</option><option value=\"45\">45</option>");
                    out.println("</select>");
                    out.println("");
                    out.println("</td>"); 
                    out.println("</tr>");
                    out.println("<tr >"); 
                    out.println("<td ></td>"); 
                    out.println("<td ></td>"); 
                    out.println("<td id=bnexta>Next Action To Be Taken</td>"); 
                    out.println("<td id=tnexta><SELECT class='txt_input' name='TXT_NEXT_ACTION_TOOK'>"+m_cat_string+"</SELECT>");
                    out.println("</td>"); //<input class='txt_input' type='text' name='TXT_STATUS' >
                    out.println("</tr>");
                }else{
                    
                    out.println("<tr >"); 
                    out.println("<td >Status</td>"); 
                    out.println("<td ><select name='TXT_STATUS' class='txt_input' onchange=load_div()>");
                    out.println("<option value=\"--\">  --  </option>");
                    out.println("<option value=INPROGRESS>In progress</option>");
                    out.println("<option value=COMPLETED >Completed  </option>");
                    out.println("</select>");
                    out.println("</td>"); //<input class='txt_input' type='text' name='TXT_STATUS' >
                    out.println("<td id=tnextd></td>"); 
                    out.println("<td id=bnextd></td>");
                    out.println("</tr>");
                    out.println("<tr >"); 
                    out.println("<td ></td>"); 
                    out.println("<td ></td>"); 
                    out.println("<td id=bnexta></td>"); 
                    out.println("<td id=tnexta>");
                    out.println("</td>"); //<input class='txt_input' type='text' name='TXT_STATUS' >
                    out.println("</tr>");
                }
                //out.println("</td>")
                //out.println("<tr >"); 
                //out.println("<td width='1%'></td>"); 
                //out.println("<td >Next  Action Date *</td>"); 
                //out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_SET_FOR' maxlength='10' size='10'></td>"); 
                
                //out.println("<td ></td>"); 
                //out.println("<td ></td>"); 
                //out.println("<td width='*%'></td>"); 
                
                //out.println("</tr>");
                
                /*out.println("</tr>"); 
                
                out.println("<tr >"); 
                out.println("<td width='1%'></td>"); 
                out.println("<td >SCREEN_NAME *</td>"); 
                out.println("<td ><input class='txt_input' type='text' name='TXT_SCREEN_NAME' maxlength='50' size='50'></td>"); 
                out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                
                out.println("<tr >"); 
                out.println("<td width='1%'></td>"); 
                out.println("<td >DIVISION_CODE *</td>"); 
                out.println("<td ><input class='txt_input' type='text' name='TXT_DIVISION_CODE' maxlength='10' size='10'></td>"); 
                out.println("<td width='*%'></td>"); 
                out.println("</tr>");
                
                out.println("<tr >"); 
                out.println("<td width='1%'></td>"); */
                
                /*
                out.println("<tr >"); 
                out.println("<td width='1%'></td>"); 
                out.println("<td >ACTION_ENT_DATE *</td>"); 
                out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_ENT_DATE' maxlength='7' size='7'></td>"); 
                out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                */
                out.println("</table>"); 
                out.println("<br>"); 
                
                out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
                out.println("<tr class='tr_input'>");  
                out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
                out.println("</tr>"); 
                out.println("<tr class='tr_input'>");  
                out.println("<td width='100%' id=load_followup></td>");  
                out.println("</tr>"); 
                out.println("</table>");  
                
                
                out.println("<table align='center' width='100%'>"); 
                out.println("<tr>"); 
                out.println("<td width='100%' class='note'></td>"); 
                out.println("</tr>"); 
                out.println("</table>"); 
                out.println("</form>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
                out.println("</body>"); 
                out.println("</html>"); 
                
            } 
            
            else if(m_chksql.trim().equals("get_followup")){
                
                String m_rate		    = req.getParameter("rate");
                String m_value      = req.getParameter("value");
                
                out.println("<table class=table border='0' width='100%' >");
                out.println("<tr class=tr_input>");
                //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                out.println("</tr>");
                
                out.println("<tr class=pdn_txtpos2>");
                out.println("<td  width='15%' >Follow Up No</td>");
                out.println("<td  width='10%' >Category</td>");
                out.println("<td  width='15%' >ID No</td>");
                out.println("<td  width='15%' >Action To Be Taken</td>");
                out.println("<td  width='10%' >Effective Date</td>");
                out.println("<td  width='5%'  >Time</td>");
                out.println("<td  width='15%' >Remarks</td>");
                out.println("<td  width='10%' ></td>");
                out.println("</tr>");
                
                /////////////THIS SECTION COMMENT BY NS ON 23/05/2017, DUE TO NOT USING THIS FEATURE
				
				
				int j = 0;      	
				

                
                rs = stmt.executeQuery (" SELECT A.FOLLOW_UP_NO, NVL(A.ID_NO,'-'), CATEGORY_NAME, "+
                    "        TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),  "+
                    "        A.ENT_USER,A.ENT_REMARKS,A.ACTION_TAKEN,  "+
                    "        A.ACTION_DATE,A.REMARKS,  "+
                    "        "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,SCREEN_NAME),   "+
                    "        FOLLOWUP_TIME ,NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)) , "+ //Modified by Disnaka on 2010-10-13
                    "        NVL("+m_schema_name+".FA_OP_GET_CLINET_COMMENT ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO),NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)) ),A.ENT_REMARKS)"+
                    " FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A,  "+
                    "        "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B  "+
                    " WHERE  STATUS = 'PENDING' AND ACTION_SET_FOR='"+m_username+"' AND  "+
                    "        CATEGORY_CODE=ACTION_TOBE_TAKEN  "+
                     " AND TO_DATE(TO_CHAR(EFF_VAL_DATE,'DD-MON-YYYY'),'DD-MON-YYYY') <= ( "+
                    " SELECT  MIN(EFF_VAL_DATE) "+
                    " FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A,  "+
                    "        "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B  "+
                    " WHERE  STATUS = 'PENDING' AND ACTION_SET_FOR='"+m_username+"' AND  "+
                    " CATEGORY_CODE=ACTION_TOBE_TAKEN "+
                    " AND EFF_VAL_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY')  "+
                    " ) "+
                    " ORDER  BY PRIORITY  ");
                
                while(rs.next()){
                    //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                    out.println("<tr class=tr_input alt=\"Click Here to get Details\" style=\"cursor:hand\">");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(1) +"<input type=hidden name=\"FOLLOW_UP_NO_"+j+"\"     value=\""+rs.getString(1)+"\"></td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(11)+"</td>");
                    out.println("<td onclick=\"load_data1('"+rs.getString(13)+"','"+rs.getString(14)+"')\";>"+rs.getString(13) +"<input type=hidden name=\"ID_NO"+j+"\"             value=\""+rs.getString(2)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(3) +"<input type=hidden name=\"ACTION_TOBE_TAKEN"+j+"\" value=\""+rs.getString(3)+"\"></td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(4) +"<input type=hidden name=\"Ent_User_"+j+"\"         value=\""+rs.getString(6)+"\"></td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(12) +"<input type=hidden name=\"VAL_TIME"+j+"\"         value=\""+rs.getString(12)+"\"></td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(15) +"<input type=hidden name=\"EFF_VAL_DATE"+j+"\"      value=\""+rs.getString(4)+"\">");
                    out.println("     <input type=hidden name=\"ACTION_TOOK"+j+"\"      value=\""+rs.getString(8)+"\">");
                    out.println("     <input type=hidden name=\"ACTION_SET_FOR"+j+"\"   value=\""+m_username+"\">");
                    out.println("     <input type=hidden name=\"ENT_REMARKS"+j+"\"      value=\""+rs.getString(7)+"\">");
                    out.println("     <input type=hidden name=\"REMARKS"+j+"\"          value=\""+rs.getString(10)+"\">");
                    out.println("     <input type=hidden name=\"ACTION_TOOK_DATE"+j+"\" value=\""+rs.getString(9)+"\"></td>");
                    out.println("<td ><input type=button name=\"Edit_"+j+"\"            value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
                    out.println("     </td>");
                    out.println("</tr>");
                    j=j+1;
                    
                    if(rs.next()){
                        //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                        out.println("<tr class=tr_input1 alt=\"Click Here to get Details\" style=\"cursor:hand\">");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(1) +"<input type=hidden name=\"FOLLOW_UP_NO_"+j+"\"     value=\""+rs.getString(1)+"\"></td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(11)+"</td>");
                        out.println("<td onclick=\"load_data1('"+rs.getString(13)+"','"+rs.getString(14)+"')\";>"+rs.getString(13) +"<input type=hidden name=\"ID_NO"+j+"\"             value=\""+rs.getString(2)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(3) +"<input type=hidden name=\"ACTION_TOBE_TAKEN"+j+"\" value=\""+rs.getString(3)+"\"></td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(4) +"<input type=hidden name=\"Ent_User_"+j+"\"         value=\""+rs.getString(6)+"\"></td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(12) +"<input type=hidden name=\"VAL_TIME"+j+"\"         value=\""+rs.getString(12)+"\"></td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(15) +"<input type=hidden name=\"EFF_VAL_DATE"+j+"\"      value=\""+rs.getString(4)+"\">");
                        out.println("     <input type=hidden name=\"ACTION_TOOK"+j+"\"      value=\""+rs.getString(8)+"\">");
                        out.println("     <input type=hidden name=\"ACTION_SET_FOR"+j+"\"   value=\""+m_username+"\">");
                        out.println("     <input type=hidden name=\"ENT_REMARKS"+j+"\"      value=\""+rs.getString(7)+"\">");
                        out.println("     <input type=hidden name=\"REMARKS"+j+"\"          value=\""+rs.getString(10)+"\">");
                        out.println("     <input type=hidden name=\"ACTION_TOOK_DATE"+j+"\" value=\""+rs.getString(9)+"\"></td>");
                        out.println("<td ><input type=button name=\"Edit_"+j+"\"            value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
                        out.println("     </td>");
                        out.println("</tr>");
                        j=j+1;
                        
                    }
                    
                  
                }
                
			
			
				/////////////THIS SECTION COMMENT BY NS ON 23/05/2017, DUE TO NOT USING THIS FEATURE
				
                
                out.println("<tr class=tr_input>");
                out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                
                out.println("</tr></table>");
                
                
            } 	
            else if(m_chksql.trim().equals("get_History")){
                
                String m_deal_no	    = req.getParameter("deal_no");
                
                
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Asset Financing System</title>    ");
                out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
                out.println("</head>");
                out.println("<Script>");
                out.println("var m_bsubmit = '0';");
                out.println("var arr_assign= new Array();");
                out.println("var m_send_val= '';");
                
                out.println("function load_all_foll(m_stat,opt) {");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup\";");
                //out.println("   window.open(m_url);");
                out.println("  setInterval('makeRequest(m_url,\"2\")',36000);");
                out.println("}");
                
                out.println("</Script>");
                out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
                out.println("<form name=\"Form1\" method=post>");
                out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
                out.println("<input type=hidden name=\"ROW_ID\" ></td>");
                
                out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
                out.println("<tr>");
                
                out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
                out.println("<td class=\"border_wht\" valign=\"top\"> ");
                out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
                out.println("<tr> ");
                out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
                out.println("</tr>");
                out.println("<tr> ");
                out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td style=\"height: 327px\">");
                
                out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
                out.println("<tr>");
                out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>History</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
                out.println("</td>	");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
                
                out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
                out.println("<tr class=tr_input>");
                out.println("<td valign=top  width=100% Id=Follow_up> ");
                
                out.println("<table class=table border='0' width='100%' >");
                //out.println("<tr class=tr_input>");
                //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                //out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                //out.println("</tr>");
                
                out.println("<tr class=pdn_txtpos2>");
                out.println("<td  width='15%' >Follow Up No</td>");
                out.println("<td  width='15%' >Category</td>");
                out.println("<td  width='15%' >ID No</td>");
                out.println("<td  width='15%' >Action To Be Taken</td>");
                out.println("<td  width='10%' >Effective Date</td>");
                out.println("<td  width='10%' >Remarks</td>");
                out.println("<td  width='10%' >Action Taken</td>");
                out.println("<td  width='10%' >Action Date</td>");
                out.println("</tr>");
                
                int j = 0;      					
                rs = stmt.executeQuery (" SELECT A.FOLLOW_UP_NO, NVL(A.ID_NO,'-'), NVL(CATEGORY_NAME,'-'),"+
                    "	       TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),NVL(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'-'), "+
                    "	       A.ENT_USER,NVL(A.ENT_REMARKS,'-'),NVL(A.ACTION_TAKEN,'-'), "+
                    "	       NVL(TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY'),'-'),NVL(A.REMARKS,'-'),"+
                    "        "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,SCREEN_NAME)  "+
                    " FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
                    "		     "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
                    "	WHERE  ACTION_SET_FOR='"+m_username+"' AND "+
                    "        ORG_FOLLOWUP_NO = (SELECT ORG_FOLLOWUP_NO "+
                    "													  FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
                    "														WHERE  FOLLOW_UP_NO = UPPER('"+m_deal_no+"')) AND"+
                    "        CATEGORY_CODE=ACTION_TOBE_TAKEN AND "+
                    "        EFF_VAL_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') "+
                    "	ORDER  BY PRIORITY ");
                
                while(rs.next()){
                    //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                    out.println("<tr class=tr_input alt=\"Click Here to get Details\" >");
                    out.println("<td >"+rs.getString(1) +"</td>");
                    out.println("<td >"+rs.getString(11)+"</td>");
                    out.println("<td >"+rs.getString(2) +"</td>");
                    out.println("<td >"+rs.getString(3) +"</td>");
                    out.println("<td >"+rs.getString(4) +"</td>");
                    out.println("<td >"+rs.getString(7) +"</td>");
                    out.println("<td >"+rs.getString(8) +"</td>");
                    out.println("<td >"+rs.getString(9) +"</td>");
                    
                    out.println("</tr>     ");
                    
                    if(rs.next()){
                        out.println("<tr class=tr_input1 alt=\"Click Here to get Details\" >");
                        out.println("<td >"+rs.getString(1) +"</td>");
                        out.println("<td >"+rs.getString(11)+"</td>");
                        out.println("<td >"+rs.getString(2) +"</td>");
                        out.println("<td >"+rs.getString(3) +"</td>");
                        out.println("<td >"+rs.getString(4) +"</td>");
                        out.println("<td >"+rs.getString(7) +"</td>");
                        out.println("<td >"+rs.getString(8) +"</td>");
                        out.println("<td >"+rs.getString(9) +"</td>");
                        
                        out.println("</tr>     ");
                    }
                    //out.println("</tr>");
                    j=j+1;
                    
                    //if(rs.getString(6).equals(m_username)){
                    //  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
                    //	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
                    //}else{
                    //}                  
                }
                //}
                
                
                //out.println("<tr class=tr_input>");
                //out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                
                out.println("</table>");
                
                
                
                out.println("</td>");
                
                out.println("</tr>");
                out.println("</table>");
                out.println("</form>");
                out.println("</body>");
                out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
                out.println("</html>");
                
                
                
                
                
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
