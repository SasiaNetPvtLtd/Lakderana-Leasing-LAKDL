//Option Id is 4.2  
//This File was created by SVA on 17-05-2006 
//Collection Invoice
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_InvoiceGeneration extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username			= con_method.username;
			String header_name    = con_method.header_name;
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");

			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
						else if (m_chksql.trim().equals("main_page")) {
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				//Check Values Using AJAX
				out.println("function makeRequest(url,opt,opt1) {");
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
        out.println("http_request.onreadystatechange = function() {alertContents(http_request,opt,opt1); };");
				//alertContents(http_request); };
        //http_request.open('GET', "http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12&type=ADDVANCE&tax=.1", true);
        //http://localhost:/myserver/servlet/CreateFileFormat?chksql=dis_data
				//http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12
				//out.println("url=\"http://localhost:/myserver/servlet/LAKDL_AF_MK_Inquiry?chksql=chkCity&Meth=getCustomerCat&value=Y&value1=TEST\";");
        //out.println("window.open(url);");
				out.println("http_request.open('GET',url, true);");
        //alert('1111');
				out.println("http_request.send(null);");
				out.println("}");

        out.println("function alertContents(http_request,opt,opt1) {");
		//out.println("alert('opt'+opt);");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        out.println("      if(http_request.responseText!=\"\"){");
				out.println("        if(opt==\"EXCEPTION\"){");
				out.println("          window_load(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
				out.println("        }");	
				
				// added by udara 31-08-2016
				out.println("        else if(opt==\"EXCEPTION_THREAD\"){");
				out.println("          window_load(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
				out.println("        }");
				// added by udara 31-08-2016
				
				out.println("        else if(opt==\"EXCEPTION_D\"){");
				out.println("          exc.innerHTML=http_request.responseText; ");
				out.println("          exc_det.innerHTML=\"The system exists rentals due prior to \"+document.Form1.LTO_DAY.value+\"-\"+document.Form1.LTO_MONTH.value+\"-\"+document.Form1.LTO_YEAR.value+\" , which have not been invoiced yet. These have to be invoiced before running the current invoice cycle. Do you wish to run this now? \"; "); // Modified by Thamali Jayatunga on 2009.10.19
				out.println("          exc_F.innerHTML=\"<input type=text name=EFROM_DAY value=\"+document.Form1.E_F_DAY.value+\" class='txt_input' style='width:25px;' DISABLED>\"+");
				out.println("                          \"<input type=text name=EFROM_MON value=\"+document.Form1.E_F_MONTH.value+\" class='txt_input' style='width:25px;' DISABLED>\"+");
				out.println("                          \"<input type=text name=EFROM_YEA value=\"+document.Form1.E_F_YEAR.value+\" class='txt_input' style='width:40px;' DISABLED>\";");
				out.println("          exc_T.innerHTML=\"<input type=text name=ETO_DAY value=\"+document.Form1.E_F_EDAY.value+\" class='txt_input' style='width:25px;' >\"+");
				out.println("                          \"<input type=text name=ETO_MON value=\"+document.Form1.E_F_EMONTH.value+\" class='txt_input' style='width:25px;' >\"+");
				out.println("                          \"<input type=text name=ETO_YEA value=\"+document.Form1.E_F_EYEAR.value+\" class='txt_input' style='width:40px;' >\";");
				out.println("          exc_W.innerHTML=\"To\";");
				out.println("          exc_B.innerHTML=\"<input type=button name=b_submit_e value='Run Invoice Routine ' class=mainbut onclick=chk_vari_rate(document.Form1.EFROM_DAY,document.Form1.EFROM_MON,document.Form1.EFROM_YEA,document.Form1.ETO_DAY,document.Form1.ETO_MON,document.Form1.ETO_YEA); style='width:150px;'>\";");//befor_submit1_e()
				
				out.println("          exc_BB.innerHTML=\"<input type=button name=b_submit_e2 value='Run Inv Route - Test Bottom' class=mainbut onclick=chk_vari_rate_thread(document.Form1.EFROM_DAY,document.Form1.EFROM_MON,document.Form1.EFROM_YEA,document.Form1.ETO_DAY,document.Form1.ETO_MON,document.Form1.ETO_YEA); style='width:150px;'>\";"); // added by udara 11-08-2016 // down button
				
				out.println("        }");	
				
        out.println("        else {");
				out.println("          var xmlbody=http_request.responseXML.documentElement;");
			  out.println("          var vsize=0;");
				out.println("          data_vec = new Array();");

			  out.println("           for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("      			 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("         	  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("         	  vsize++;");
					//alert(data_vec[vsize]);
					
				out.println("            }");
			  out.println("           }");
				//out.println("alert('data_vec[0]'+data_vec[0]);");
				out.println("          if(data_vec.length>0){");
				out.println("          if(opt==\"LOAD_DATE\"){");
				out.println("            assing_date(data_vec);");
				out.println("          }");
				out.println("          else if(opt==\"LOAD_VARI\"){");
				//out.println("alert('data_vec[0]'+data_vec[0]);");
				out.println("            if(data_vec[0]=='NO'){");
				out.println("              alert('Please enter rates for variable bases. ');");
				out.println("              befor_submit1_e();");
				out.println("            }else{");
				out.println("              befor_submit1_e();");
				out.println("            }");
				
				out.println("          }");
				
				// added by udara 31-08-2016
				out.println("          else if(opt==\"LOAD_VARI_THREAD\"){");
				out.println("            if(data_vec[0]=='NO'){");
				out.println("              alert('Please enter rates for variable bases. ');");
				out.println("              befor_submit1_e_thread();");
				out.println("            }else{");
				out.println("              befor_submit1_e_thread();");
				out.println("            }");
				out.println("          }");
				// end by udara 31-08-2016
				
				
				out.println("          else if(opt==\"LOAD_DATE_DIF\"){");
				out.println("            assing_date_dif(data_vec);");
				out.println("          }else if(opt==\"CHK_DATES\"){");
				out.println("            opt1.value=data_vec[0];");
				out.println("          }else if(opt==\"TEAM_CODE\"){");
				out.println("            opt1.value=data_vec[0];");
				out.println("          }");				
				out.println("          }else{");
				out.println("          opt1.value='';");
				out.println("          alert('no data found');");
				out.println("          }");				
        
				out.println("        }");				
        out.println("      }");				
        out.println("      else {");
				out.println("        if(opt==\"EXCEPTION\"){");
				out.println("          befor_submit();");
				//out.println("        }else if(opt==\"INQ_NO\"){");
				//out.println("          opt1.value='';");
				//out.println("        }else if(opt==\"TEAM_CODE\"){");
				//out.println("          opt1.value='';");				
				//out.println("        }else if(opt==\"TRANSACTION_SUB\"){");
				//out.println("          opt1.value='';");
				
				
				out.println("        } ");
				
				// added by udara 31-08-2016
				out.println(" else if(opt==\"EXCEPTION_THREAD\"){");
				out.println("       befor_submit2();");
				out.println(" } ");
				// end by udara 31-08-2016
				
				
				out.println(" else if(opt==\"EXCEPTION_D\"){");
				out.println("        }else{");				
        out.println("          alert('no data found');");
				out.println("        }");
        out.println("      }");
        out.println("    } else {");
        out.println("        alert('There was a problem with the request.');");
        out.println("    }");
        out.println(" }");
        out.println("}");
        
				
				out.println("function assing_date(data){");
				out.println(" document.Form1.FROM_DAY.value   =data[0]");
				out.println(" document.Form1.FROM_MONTH.value =data[1]");
				out.println(" document.Form1.FROM_YEAR.value  =data[2]");
				out.println(" document.Form1.TO_DAY.value     =data[3]");
				out.println(" document.Form1.TO_MONTH.value   =data[4]");
				out.println(" document.Form1.TO_YEAR.value    =data[5]");
				out.println(" document.Form1.LFROM_DAY.value  =data[6]");
				out.println(" document.Form1.LFROM_MONTH.value=data[7]");
				out.println(" document.Form1.LFROM_YEAR.value =data[8]");
				out.println(" document.Form1.LTO_DAY.value    =data[9]");
				out.println(" document.Form1.LTO_MONTH.value  =data[10]");
				out.println(" document.Form1.LTO_YEAR.value   =data[11]");
				out.println(" chk_exception_det(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
				out.println("}");	
				
				out.println("function assing_date_dif(data){");
				out.println(" document.Form1.FROM_DAY.value   =data[0]");
				out.println(" document.Form1.FROM_MONTH.value =data[1]");
				out.println(" document.Form1.FROM_YEAR.value  =data[2]");
				out.println(" document.Form1.TO_DAY.value     =data[3]");
				out.println(" document.Form1.TO_MONTH.value   =data[4]");
				out.println(" document.Form1.TO_YEAR.value    =data[5]");
				out.println("}");	
				
        out.println("function chk_exception(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
				out.println("   url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_InvoiceGeneration?chksql=get_exception&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
				out.println("   makeRequest(url,'EXCEPTION',dayobj);");
        out.println(" }");
	      out.println("}");
			
			// added by udara 31-08-2016
			out.println("function chk_exception_thread(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
			out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
			out.println("   url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_InvoiceGeneration?chksql=get_exception&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
			out.println("   makeRequest(url,'EXCEPTION_THREAD',dayobj);");
        	out.println(" }");
	      	out.println("}");
			// end by udara 31-08-2016
				
				out.println("function chk_exception_det(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
				out.println("   url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_InvoiceGeneration?chksql=get_exception_det&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
				out.println("   makeRequest(url,'EXCEPTION_D',dayobj);");
        out.println(" }");
	      out.println("}");
				    
				out.println("function window_load(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
				out.println("   url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_InvoiceGeneration?chksql=get_exception&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
				out.println("   INV_POP=window.open(url,\"oBj\",\"left=230,top=180,width=320,height=230,scrollBars=1\");");
        out.println(" }");
	      out.println("}");
						
						
				//End Of Checking Values
				
				//change required DIV
				out.println("function change_div(){");
				out.println(" if(document.Form1.CLIENT_TYPE.options[document.Form1.CLIENT_TYPE.selectedIndex].text!='Individual' &&");
				out.println("    document.Form1.CONTACT_PERSON.value==''){ ");
				out.println("   conp.innerHTML=\"Contact Person *\";");
				out.println(" }else{");
				out.println("   conp.innerHTML=\"Contact Person\";");
				out.println(" }");
				out.println("}");
				
				//end of DIV change
				
				
				// added by udara 11-08-2016
				/*
				out.println("function befor_submit2(){"); // refer befor_submit
				
				out.println("   if(confirm(\"Are you sure you want to run the invoice routine thread?\")){  ");
				out.println("      document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Inv_gen_thread_save\";");
				out.println("      document.Form1.submit();");
				out.println("   }");
				
				out.println("}");
				*/
				// end by udara 11-08-2016
				
				// added by udara 31-08-2016
				out.println("function befor_submit2(){");
				out.println(" m_bsubmit='0';");
				out.println(" if(document.Form1.TO_DAY.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.TO_MONTH.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.TO_YEAR.value==''){  ");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.FROM_DAY.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.FROM_MONTH.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.FROM_YEAR.value==''){  ");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");

				out.println(" if(m_bsubmit=='0'){");

				out.println("   if(confirm(\"Are you sure you want to run the invoice routine thread?\")){  ");
				out.println("      document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Inv_gen_thread_save\";");
				out.println("      document.Form1.submit();");
				out.println("   }");

				out.println(" }");
				out.println("}");
				
				out.println("function befor_submit_thread_top(){");
				out.println("  chk_exception_thread(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
				out.println("}");
				
				// added by udara 31-08-2016
				
				
				
				
				
				//Main Button Action
				//Submit
				out.println("function befor_submit1(){");
				out.println("  chk_exception(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
				out.println("  ");
				out.println("}");
				
				out.println("function befor_submit1_e(){");
				out.println(" m_bsubmit='0';");
				out.println(" if(document.Form1.ETO_DAY.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.ETO_MON.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.ETO_YEA.value==''){  ");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.EFROM_DAY.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.EFROM_MON.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.EFROM_YEA.value==''){  ");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				/*
				out.println(" if(document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value=='--'){  ");
				out.println("      fod.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				*/
				out.println(" if(m_bsubmit=='0'){");
				
				out.println("   document.Form1.FROM_YEAR.value =document.Form1.EFROM_YEA.value;");
				out.println("   document.Form1.FROM_MONTH.value=document.Form1.EFROM_MON.value;");
				out.println("   document.Form1.FROM_DAY.value  =document.Form1.EFROM_DAY.value;");
				out.println("   document.Form1.TO_YEAR.value   =document.Form1.ETO_YEA.value;");
				out.println("   document.Form1.TO_MONTH.value  =document.Form1.ETO_MON.value;");
				out.println("   document.Form1.TO_DAY.value    =document.Form1.ETO_DAY.value;");
				out.println("   if(confirm(\"Are you sure you want to run the invoice routine?\")){  ");
				out.println("    document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save\";");
				out.println("    document.Form1.submit();");
				out.println("  }");
				out.println(" }");
				out.println("}");
				
				
				// added by udara 31-08-2016
				out.println("function befor_submit1_e_thread(){");
				out.println(" m_bsubmit='0';");
				out.println(" if(document.Form1.ETO_DAY.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.ETO_MON.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.ETO_YEA.value==''){  ");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.EFROM_DAY.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.EFROM_MON.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.EFROM_YEA.value==''){  ");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");

				out.println(" if(m_bsubmit=='0'){");
				
				out.println("   document.Form1.FROM_YEAR.value =document.Form1.EFROM_YEA.value;");
				out.println("   document.Form1.FROM_MONTH.value=document.Form1.EFROM_MON.value;");
				out.println("   document.Form1.FROM_DAY.value  =document.Form1.EFROM_DAY.value;");
				out.println("   document.Form1.TO_YEAR.value   =document.Form1.ETO_YEA.value;");
				out.println("   document.Form1.TO_MONTH.value  =document.Form1.ETO_MON.value;");
				out.println("   document.Form1.TO_DAY.value    =document.Form1.ETO_DAY.value;");
				
				out.println("   if(confirm(\"Are you sure you want to run the invoice routine thread?\")){  ");
				out.println("      document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Inv_gen_thread_save\";");
				out.println("      document.Form1.submit();");
				out.println("   }");
				
				
				
				out.println(" }");
				out.println("}");
				// end by udara 31-08-2016
				
				
				
				out.println("function befor_submit(){");
				out.println(" m_bsubmit='0';");
				out.println(" if(document.Form1.TO_DAY.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.TO_MONTH.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.TO_YEAR.value==''){  ");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.FROM_DAY.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.FROM_MONTH.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.FROM_YEAR.value==''){  ");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				/*
				out.println(" if(document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value=='--'){  ");
				out.println("      fod.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				*/
				out.println(" if(m_bsubmit=='0'){");
				//out.println("  if(document.Form1.OPTION_NAME.value==\"NEW\" && document.Form1.INQ_NO.value!=\"\"){ ");
				//out.println("   alert();");
				//out.println("  }else{");
				out.println("   if(confirm(\"Are you sure you want to run the invoice routine?\")){  ");
				out.println("    document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save\";");
				out.println("    document.Form1.submit();");
				out.println("   }");
				//out.println("  }");
				out.println(" }");
				out.println("}");
				//end of Submit Function
				
				out.println("function befor_deactive(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println("   close_window(); ");
				//out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				//out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				//out.println("  document.Form1.INQ_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_active(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println("  document.Form1.OPTION_NAME.value=\"ACT\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Re-activate\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_deactive(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
				out.println("  document.Form1.OPTION_DESC.value=\"De-activate\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_reset(){");
				out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
				//out.println("  Form1.reset()   ");
			  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_InvoiceGeneration?chksql=main_page'");
			  out.println(" }  ");
				out.println("}");
				
				
				//end of Main Button Action
				
				//onload Action
				out.println("function befor_onload(){");
				//out.println("  document.Form1.INQ_NO.disabled=true;");
				//out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); 
				out.println("  assign_null();");
				out.println("}");
				//end of onload
				//Mouse movement(MM)
				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Invoicing - Invoices - Generation - \"+m_val;"); 
			  out.println("}");
				//end of MM
				
				out.println("function assign_null(oBj){");
				out.println("chk_load_date();");
				out.println("}");		
				
				out.println("function chk_load_date() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_inv_gen_date\";");
				out.println(" makeRequest(url,'LOAD_DATE',document.Form1.TEAM);");
        out.println("}");
        
				out.println("function chk_vari_rate(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
				//out.println(" alert();");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
				out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_inv_var_rate&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
				//out.println(" window.open(url);");
				out.println(" makeRequest(url,'LOAD_VARI',dayobj);");
        out.println(" }");
	      out.println("}");
			
			// added by udara 31-08-2016
			out.println("function chk_vari_rate_thread(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
			out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
			out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_inv_var_rate&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
			out.println(" makeRequest(url,'LOAD_VARI_THREAD',dayobj);");
        	out.println(" }");
	      	out.println("}");
			// added by udara 31-08-2016
			
				
				out.println("   ");
				out.println("function chkstartdate(dayobj,monthobj,yearobj) {");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!=''){");
				out.println("	  checkMonthLength(dayobj,monthobj,yearobj);		");
				out.println(" }");
	      out.println("}");
				
				out.println("function chkdate_dif(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
				out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_date_dif&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
				out.println(" makeRequest(url,'LOAD_DATE_DIF',dayobj);");
        out.println(" }");
	      out.println("}");
				
				out.println("</Script>");
				out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\" value=\"AF_RE_INVOICE_GEN\">");
				out.println("<input type=hidden name=\"OPTION_NAME\" value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
				//out.println("<input type=hidden name=\"INQ_NO\" value=\"\">");
				
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				
				
				out.println("<table border=\"0\" cellpadding=\"0\" class=table cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" height=\"2%\" id=help_box></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=60%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=befor_new(); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit value=\"Edit\" class=mainbut onclick=befor_modify(); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit1(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        //out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=cal value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
      
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
			
			  out.println("<tr class=tr_input>");
				out.println("<td width =20%>Last Invoice Cycle</td>");
				out.println("<td width =20%><input name=\"LFROM_DAY\"   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.LFROM_DAY,document.Form1.FROM_MONTH,document.Form1.LFROM_YEAR) disabled> ");
				out.println("    <input name=\"LFROM_MONTH\" type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.LFROM_DAY,document.Form1.FROM_MONTH,document.Form1.LFROM_YEAR) disabled> ");
				out.println("    <input name=\"LFROM_YEAR\"  type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:40px;\" onchange=chkstartdate(document.Form1.LFROM_DAY,document.Form1.FROM_MONTH,document.Form1.LFROM_YEAR) disabled> ");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td width =10%>To </td>");
				out.println("<td width =20%><input name=\"LTO_DAY\"   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.LTO_DAY,document.Form1.LTO_MONTH,document.Form1.LTO_YEAR) disabled> ");
				out.println("    <input name=\"LTO_MONTH\" type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.LTO_DAY,document.Form1.LTO_MONTH,document.Form1.LTO_YEAR) disabled> ");
				out.println("    <input name=\"LTO_YEAR\"  type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:40px;\" onchange=chkstartdate(document.Form1.LTO_DAY,document.Form1.LTO_MONTH,document.Form1.LTO_YEAR);chkdate_dif(document.Form1.LFROM_DAY,document.Form1.LFROM_MONTH,document.Form1.LFROM_YEAR,document.Form1.LTO_DAY,document.Form1.LTO_MONTH,document.Form1.LTO_YEAR); disabled> ");
				out.println("</td>");
				out.println("<td ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=fod>Current Invoice Cycle</td>");
				out.println("<td><input name=\"FROM_DAY\"   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("    <input name=\"FROM_MONTH\" type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("    <input name=\"FROM_YEAR\"  type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:40px;\" onchange=chkstartdate(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod>To </td>");
				out.println("<td><input name=\"TO_DAY\"   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR)> ");
				out.println("    <input name=\"TO_MONTH\" type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR)> ");
				out.println("    <input name=\"TO_YEAR\"  type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:40px;\" onchange=chkstartdate(document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);chkdate_dif(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);> ");
				out.println("</td>");
				out.println("<td ><input type=button name=b_submit value=\"Run Invoice Routine \" class=mainbut onclick=befor_submit1(); style=\"width:150px;\"></td>");//onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'
        
				//out.println("<td ><input type=button name=b_submit_2 value=\"Run Inv Route - Test Top\" class=mainbut onclick=\"befor_submit2();\" style=\"width:150px;\"></td>"); // added by udara 11-08-2016 // up button
				out.println("<td ><input type=button name=b_submit_2 value=\"Run Inv Route - Test Top\" class=mainbut onclick=\"befor_submit_thread_top();\" style=\"width:150px;\"></td>"); // added by udara 31-08-2016
				
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\" colspan=5>");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Exceptions</td>");
				out.println("<td colspan=4 id=exc></td>");
			  out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td colspan=5 id=exc_det>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td ></TD>");
				out.println("<td id=exc_F></TD>");
				out.println("<td id=exc_W></TD>");
				out.println("<td id=exc_T></TD>");
				out.println("<TD id=exc_B></td>");
				out.println("<TD id=exc_BB></td>"); // added by udara 11-08-2016 // down button
				out.println("</tr>");
				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr class=tr_input>");
				
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=60%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=new_1 value=\"New\" class=mainbut onclick=befor_new(); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit_1 value=\"Edit\" class=mainbut onclick=befor_modify(); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit1(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        //out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("</tr></table>");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");

				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");    
				out.println("<tr class=tr_input> ");
				out.println("<td valign=\"bottom\" height=\"20\"></td>");
				out.println("</tr>");
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2=\"JavaScript\" src=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT>");
		
				out.println("</html>");
			
			
			}
			else if (m_chksql.trim().equals("get_exception_det")) {
			
			String M_FROM_DATE = req.getParameter("from_date");			
			
						
			  rs = stmt.executeQuery(" SELECT DISTINCT FINANCE_NO, A.GRENTAL_AMOUNT,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+
				                       "        TO_CHAR(A.RENTAL_DATE,'DD'),TO_CHAR(A.RENTAL_DATE,'MM'),"+
															 "        TO_CHAR(A.RENTAL_DATE,'YYYY'),A.CASH_OUT_FLOW, "+
															 "        A.VAT_RENTAL_AMOUNT, A.INVOICE_NO, "+
															 "        "+m_schema_name+".AF_CO_GET_RESIDUAL_VAL_INV(A.APPLICATION_NO,INSTALLMENT_NO),A.RENTAL_DATE "+	
															 " FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
															 "        "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+//Added By Sandun on 19-10-2009
															 " WHERE  A.RENTAL_DATE<=TO_DATE('"+M_FROM_DATE+"','DD-MM-YYYY') AND "+
															 "        A.RENTAL_DATE>=TO_DATE('01-01-2011','DD-MM-YYYY') AND "+ //added by ns on 21-10-2012 temp
															 //"        B.APPLICATION_NO=A.APPLICATION_NO AND B.APPLICATION_STATUS='ACTIVATED' AND "+ // commented by udara 13-08-2015
															 "        B.APPLICATION_NO=A.APPLICATION_NO AND B.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') AND "+ // added by udara 13-08-2015	
															 "        B.APPLICATION_NO   = C.APPLICATION_NO AND "+ //  |
                               "        A.PRO_INVOICE_NO   = C.INVOICE_NO AND "+     //  Added By Sandun on 19-10-2009 
															 "        C.ACTIVE_STATUS    = 'Y' AND "+              //  |
															 //"        RENTAL_DATE<=TO_DATE(M_TO_DATE,'DD-MM-YYYY') AND "+
															 "        A.INVOICE_NO IS NULL "+
																"  AND B.RENTAL_FREEZ = 'N'  "+ // added by udara 03-01-2016 
															 " ORDER BY A.RENTAL_DATE ");
				boolean more = rs.next();
				if(more){
				  String m_day   = rs.getString(4);
					String m_month = rs.getString(5);
					String m_year  = rs.getString(6);
					String m_eday  = rs.getString(4);
					String m_emonth= rs.getString(5);
					String m_eyear = rs.getString(6);
					
				  out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				
					out.println("<tr class=tr_input>");
					out.println("<td>Finance No</td>");
					out.println("<td>Rental Amount</td>");
					out.println("<td>Rental Date</td>");
					out.println("<td>Note</td>");
					//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
					out.println("</tr>");
					
					while(more){
					//   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					out.println("<tr class=tr_input>");
					out.println("<td >"+rs.getString(1)+"</td>");
					out.println("<td >"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td >"+rs.getString(3)+"</td>");
					if(rs.getString(10)==null){
					 out.println("<td ></td>");
					}else{
					if(rs.getString(10).equals("N")){
					 out.println("<td >Residual value Not entered</td>");
					}else{
					 out.println("<td ></td>");
					}
					}
					//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
					out.println("</tr>");
					 m_eday  = rs.getString(4);
					 m_emonth= rs.getString(5);
					 m_eyear = rs.getString(6);
						 more = rs.next();	
					}	 
					out.println("<tr class=tr_input>");
					out.println("<td ><input type=hidden name=E_F_DAY   value="+m_day+"  ><input type=hidden name=E_F_EDAY   value="+m_eday+"  ></td>");
					out.println("<td ><input type=hidden name=E_F_MONTH value="+m_month+"><input type=hidden name=E_F_EMONTH value="+m_emonth+"></td>");
					out.println("<td ><input type=hidden name=E_F_YEAR  value="+m_year+" ><input type=hidden name=E_F_EYEAR  value="+m_eyear+" >");
					out.println("</td>");
					out.println("</table>");
				
				}
			}
			else if (m_chksql.trim().equals("get_exception")) {
			
			String M_FROM_DATE = req.getParameter("from_date");
			
			  rs = stmt.executeQuery(" SELECT FINANCE_NO, A.GRENTAL_AMOUNT,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), A.CASH_OUT_FLOW, "+
															 "        A.VAT_RENTAL_AMOUNT, A.INVOICE_NO "+
															 " FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
															 " WHERE  RENTAL_DATE<TO_DATE('"+M_FROM_DATE+"','DD-MM-YYYY') AND "+
															 //"        B.APPLICATION_NO=A.APPLICATION_NO AND APPLICATION_STATUS='ACTIVATED' AND "+ // commented by udara 
																"        B.APPLICATION_NO=A.APPLICATION_NO AND APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') AND "+ // added by udara 13-08-2015
															 //"        RENTAL_DATE<=TO_DATE(M_TO_DATE,'DD-MM-YYYY') AND "+
															 "        PRO_INVOICE_NO IN (SELECT INVOICE_NO "+
															 "                           FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
															 "                           WHERE  APPLICATION_NO = A.APPLICATION_NO AND "+
															 "                                  ACTIVE_STATUS    = 'Y')	AND "+
															 "        INVOICE_NO IS NULL ");
				boolean more = rs.next();
				if(more){
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				//Check Values Using AJAX
				
				
				//end of Main Button Action
				//onload Action
				out.println("function befor_onload(){");
				//out.println("  document.Form1.INQ_NO.disabled=true;");
				//out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); 
				//out.println("  assign_null();");
				out.println("}");
				//end of onload
				//Mouse movement(MM)
				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Invoicing - Invoices - Generation - Exception Report\";"); 
			  out.println("}");
				//end of MM
				
				
				
				out.println("   ");
				out.println("</Script>");
				out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\" value=\"AF_MK_INQUIRY\">");
				out.println("<input type=hidden name=\"OPTION_NAME\" value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
				//out.println("<input type=hidden name=\"INQ_NO\" value=\"\">");
				
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				
				
				out.println("<table border=\"0\" cellpadding=\"0\" class=table cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" height=\"2%\" id=help_box></td>");
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
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>Finance No</td>");
				out.println("<td>Rental Amount</td>");
				out.println("<td>Rental Date</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				while(more){
				//   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
				out.println("<tr class=tr_input>");
				out.println("<td >"+rs.getString(1)+"</td>");
				out.println("<td >"+nf.format(rs.getDouble(2))+"</td>");
				out.println("<td >"+rs.getString(3)+"");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
					 more = rs.next();	
				}	
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");

				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");    
				out.println("<tr class=tr_input> ");
				out.println("<td valign=\"bottom\" height=\"20\"></td>");
				out.println("</tr>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			  }
				
			
			}
      else if (m_chksql.trim().equals("get_invoices")) {
			
			String M_FROM_DATE = req.getParameter("from_date");
			
			  rs = stmt.executeQuery(" SELECT FINANCE_NO, A.GRENTAL_AMOUNT,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), A.CASH_OUT_FLOW, "+
															 "        A.VAT_RENTAL_AMOUNT, A.INVOICE_NO "+
															 " FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
															 " WHERE  RENTAL_DATE<TO_DATE('"+M_FROM_DATE+"','DD-MM-YYYY') AND "+
															 //"        B.APPLICATION_NO=A.APPLICATION_NO AND APPLICATION_STATUS='ACTIVATED' AND "+ // commented by udara 13-08-2015
																"        B.APPLICATION_NO=A.APPLICATION_NO AND APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') AND "+ // added by udara 13-08-2015
															 "        PRO_INVOICE_NO IN (SELECT INVOICE_NO "+
															 "                           FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
															 "                           WHERE  APPLICATION_NO = A.APPLICATION_NO AND "+
															 "                                  ACTIVE_STATUS    = 'Y')	AND "+
															 //"        RENTAL_DATE<=TO_DATE(M_TO_DATE,'DD-MM-YYYY') AND "+
															 "        INVOICE_NO IS NULL ");
				boolean more = rs.next();
				if(more){
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				//Check Values Using AJAX
				
				
				//end of Main Button Action
				//onload Action
				out.println("function befor_onload(){");
				//out.println("  document.Form1.INQ_NO.disabled=true;");
				//out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); 
				//out.println("  assign_null();");
				out.println("}");
				//end of onload
				//Mouse movement(MM)
				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Collection Invoice - Exception Report\";"); 
			  out.println("}");
				//end of MM
				
				
				
				out.println("   ");
				out.println("</Script>");
				out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\" value=\"AF_MK_INQUIRY\">");
				out.println("<input type=hidden name=\"OPTION_NAME\" value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
				//out.println("<input type=hidden name=\"INQ_NO\" value=\"\">");
				
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				
				
				out.println("<table border=\"0\" cellpadding=\"0\" class=table cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" height=\"2%\" id=help_box></td>");
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
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>Finance No</td>");
				out.println("<td>Rental Amount</td>");
				out.println("<td>Rental Date</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				while(more){
				//   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
				out.println("<tr class=tr_input>");
				out.println("<td >"+rs.getString(1)+"</td>");
				out.println("<td >"+nf.format(rs.getDouble(2))+"</td>");
				out.println("<td >"+rs.getString(3)+"");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
					 more = rs.next();	
				}	
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");

				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");    
				out.println("<tr class=tr_input> ");
				out.println("<td valign=\"bottom\" height=\"20\"></td>");
				out.println("</tr>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			  }
				
			
			}

			
			//=========================================================================================================================			
  		/*else {
				out.println("Undefined");
			}
			*/
      //out.close();
			//conn.close();
			//this.destroy();
			
			
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
