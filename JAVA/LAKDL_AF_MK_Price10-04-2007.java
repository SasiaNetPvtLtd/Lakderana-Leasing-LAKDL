//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Pricing Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_MK_Price extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
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
      String m_username 						= con_method.username;
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
		  nf1.setMinimumFractionDigits(6);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if (m_chksql.trim().equals("main_page")) {
			  String m_app_no = req.getParameter("app_no");
				String m_inq_no = req.getParameter("inquiry_no");
				String m_pri_no = req.getParameter("pricing_no");
				String m_screen = req.getParameter("screen_type");
				String amount   = req.getParameter("amount");
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			
				
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				out.println("var arr_assign= new Array();");
				out.println("var m_send_val= '';");
				
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
        //out.println("alert('opt='+opt+'--opt1='+opt1); ");
        //http_request.open('GET', "http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12&type=ADDVANCE&tax=.1", true);
        //http://localhost:/myserver/servlet/CreateFileFormat?chksql=dis_data
				//http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12
				//"http://localhost:/myserver/servlet/LAKDL_AF_Inquiry?chksql=get_Method&Meth=getCustomerCat&value=Y&value1=TEST"
        
				out.println(" if(opt1=='YES'){");
			 //window.open("http://www.ofscl-leasing.lk:/myserver/servlet/LAKDL_AF_MK_Price?"+m_send_val);
				//out.println("  window.open(url+'?'+m_send_val);");
				out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
				out.println("  http_request.open('POST',url, true);");
				out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
				//out.println("  alert(construct_URL(\"get_advance_price_cal\",opt));");
				//out.println("  window.open(url+'?'+construct_URL(\"get_advance_price_cal\",opt));");
			  out.println("	 m_send_val = \"chksql=get_advance_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&rental=\"+unformat_noobject(document.Form1.RENTAL_AMOUNT.value)+\"&1rental=\"+unformat_noobject(document.Form1.RENTAL1_AMOUNT.value)+");				
				out.println("	              \"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+opt+");
				out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
				out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&tax_rent=\"+document.Form1.VAT_PER_RENT.value+\"\";");
									
				out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"&installments=\";");						
				out.println("	 }else{");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=&installments=\";");						
				out.println("	 }");
				out.println("	 m_fact_val = \"\";"); 
				out.println("	 m_prac_val = \"\";"); 
				out.println("	 m_vat_val = \"\";"); 
				out.println("	 m_gro_val = \"\";"); 
				out.println("  m_percentage = \"\";"); 
				out.println("  m_char_val = \"0\";"); 
				out.println("  m_main_val = \"0\";"); 
				out.println("  m_mainten  = \"\";"); 
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
			  out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
				out.println("    m_fact_val   = m_fact_val+/*unformat_noobject*/(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
			  out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
				out.println("    m_vat_val    = m_vat_val+unformat_noobject(document.Form1.elements[\"VATAMT\"+i].value)+\"@\";");
				out.println("    m_gro_val    = m_gro_val+unformat_noobject(document.Form1.elements[\"GROSS\"+i].value)+\"@\";");
				
				out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
				//out.println("    alert('m_send_val'+m_send_val);");
			  out.println("	 }");
				out.println("  for(i=0;i<parseFloat(document.Form1.c_c_count.value);i++){");
				out.println("   if(document.Form1.elements[\"CHARGE_TYPE_\"+i].value==\"AMO\"){");
			  out.println("    m_char_val   = parseFloat(m_char_val)+parseFloat(unformat_noobject(document.Form1.elements[\"CHARGE_\"+i].value));");
				out.println("	  }");
				out.println("	 }");
				
				out.println("  for(j=0;j<=parseFloat(document.Form1.hid_y_count.value);j++){");
				out.println("      m_main_val   =0;");
			  out.println("    for(i=0;i<parseFloat(document.Form1.hid_m_count.value);i++){");
			  out.println("      m_main_val   = parseFloat(m_main_val)+parseFloat(unformat_noobject(document.Form1.elements[\"MAINTANENCE_\"+i+\"_\"+j].value));");
				out.println("	   }");
				out.println("    m_mainten   = m_mainten+m_main_val+\"@\";");
				out.println("	 }");
				
				out.println("    m_send_val = m_send_val+\"&maintan=\"+m_mainten+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage+");
				out.println("                 \"&vat_val=\"+m_vat_val+\"&gro_val=\"+m_gro_val;");
				
				out.println("    document.Form1.hid_count.value=0;");
				//out.println("  window.open(url+'?'+m_send_val);");
				//out.println(" if(parseFloat(document.Form1.hid_count.value)>0){");
				//out.println("  plsw.innerHTML='Please Wait';");
				//out.println(" }");
				out.println("  http_request.send(m_send_val);");
				
			  out.println(" }else{");
				
				out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt); };");
				out.println("  http_request.open('GET',url, true);");
        //out.println("  window.open(url);");
				//out.println("  alert('opt-'+opt);");
				out.println("  http_request.send(null);");
				out.println(" }");
        out.println("}");
        
        out.println("function alertContents(http_request) {");
				//out.println(" alert('test');");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        //out.println("      alert(http_request.responseText);");
				out.println("         price_cal.innerHTML=http_request.responseText; ");
				out.println("         for(i=0;i<10000;i++){o=i;}");
				out.println("         asign_div();");
        out.println("    } else {");
        out.println("        alert('There was a problem with the request.');");
        out.println("    }");
        out.println(" }");
        out.println("}");

        out.println("function alertGetContents(http_request,opt) {");
				//out.println(" alert('test--'+opt);");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        out.println("      if(opt==\"Main\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         main_id.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"Main_Price\"){");
				out.println("         price_cal.innerHTML=http_request.responseText; ");
				//out.println("         plsw.innerHTML='';");
				out.println("         document.Form1.hid_count.value=document.Form1.hid_cou.value;");
				out.println("         getVatNetGro('GRO');");
				//out.println("         loadcharges();");
				out.println("      }else if(opt==\"SCHAR\"){");
				out.println("         schar.innerHTML=http_request.responseText; ");
				//out.println("         alert('');");
				//out.println("         document.Form1.hid_count.value=document.Form1.hid_cou.value;");
				//schar
				out.println("            cal_VatGross();");
				out.println("      }else if(opt==\"SubType\"){");
				//out.println("         main_id.innerHTML=http_request.responseText; ");
				out.println("         Curr_no_opts=document.Form1.TRANSACTION_SUB.options;");
				out.println("         Curr_no_opts.length=0;");
				out.println("         m_string=http_request.responseText; ");
				out.println("         m_index =m_string.indexOf(\"~#@\");"); 
				out.println("         z=0;");
				out.println("         while(m_index!=-1){");
				out.println("           arr_assign[z]  = m_string.substring(0,m_string.indexOf(\"~#@\")); ");
				out.println("           m_string       = m_string.substring(m_string.indexOf(\"~#@\")+3); ");
				out.println("           z              = z+1;");
				out.println("           m_index =m_string.indexOf(\"~#@\");"); 
				out.println("         }");
				out.println("         for(x=0; x<arr_assign.length; x++){");
			  out.println("           m_string1      = arr_assign[x].substring(0,arr_assign[x].indexOf(\"@#\")); ");
				out.println("           m_string2      = arr_assign[x].substring(arr_assign[x].indexOf(\"@#\")+2); ");
				out.println("           m_string       = m_string2.substring(0,m_string2.indexOf(\"@#\")); ");
				//out.println("           alert('m_string1='+m_string1+'--m_string='+m_string+'');");
				out.println("           Curr_no_opts[x]= new Option(m_string,m_string1);");
			  out.println("         }");
				out.println("      }else {");
				out.println("        var xmlbody=http_request.responseXML.documentElement;");
			  out.println("        var vsize=0;");
        out.println("        data_vec = new Array();");

			  out.println("           for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("      			 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("         	  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("         	  vsize++;");
					//alert(data_vec[vsize]);
				out.println("            }");
			  out.println("           }");
				out.println("          if(data_vec.length>0){");
				
				out.println("          if(opt==\"PRICENO\"){");
				out.println("             assing_pval(data_vec);");
				out.println("          }else if(opt==\"INQ_NO\"){");
				out.println("            document.Form1.INQ_NO.value=data_vec[0];");
				out.println("          }else if(opt==\"VENDOR\"){");
				out.println("            document.Form1.VENDOR.value=data_vec[0];");
				out.println("          }else if(opt==\"ITEM_CAT\"){");
				out.println("            if(document.Form1.TRANSACTION_TYPE.value!='HIREPURCH'){");
				out.println("            document.Form1.VAT_PERCENTAGE.value=data_vec[0];");
				out.println("            document.Form1.VAT_PER_APP.value=data_vec[1];");
				out.println("            document.Form1.VAT_PER_RENT.value=data_vec[1];");
				
				out.println("            getVatNetGro()");
				out.println("            }else{");
				out.println("            document.Form1.VAT_PERCENTAGE.value='0';");
				out.println("            document.Form1.VAT_PER_APP.value='0';");
				out.println("            document.Form1.VAT_PER_RENT.value='0';");
				out.println("            getVatNetGro()");
				out.println("            }");
				
				out.println("            chk_sub_item();");
				out.println("          }else if(opt==\"ITEM_SUB_VAT\"){");
				out.println("            if(document.Form1.TRANSACTION_TYPE.value!='HIREPURCH'){");
				out.println("            document.Form1.VAT_PERCENTAGE.value=data_vec[0];");
				out.println("            document.Form1.VAT_PER_APP.value=data_vec[1];");
				out.println("            document.Form1.VAT_PER_RENT.value=data_vec[1];");
				out.println("            getVatNetGro()");
				out.println("            }else{");
				out.println("            document.Form1.VAT_PERCENTAGE.value='0';");
				out.println("            document.Form1.VAT_PER_APP.value='0';");
				out.println("            document.Form1.VAT_PER_RENT.value='0';");
				out.println("            getVatNetGro()");
				out.println("            }");
				out.println("            chk_make();");				
				out.println("          }else if(opt==\"SUB_MODEL\"){");
				out.println("            document.Form1.SUB_MODEL.value=data_vec[0];");
				out.println("            if(document.Form1.TRANSACTION_TYPE.value!='HIREPURCH'){");
				out.println("            document.Form1.VAT_PERCENTAGE.value=data_vec[2];");
				out.println("            document.Form1.VAT_PER_APP.value=data_vec[3];");
				out.println("            document.Form1.VAT_PER_RENT.value=data_vec[3];");
				out.println("            }else{");
				out.println("            document.Form1.VAT_PERCENTAGE.value='0';");
				out.println("            document.Form1.VAT_PER_APP.value='0';");
				out.println("            document.Form1.VAT_PER_RENT.value='0';");
				//out.println("            getVatNetGro()");
				out.println("            }");
				out.println("            document.Form1.FUEL_TYPE.value=data_vec[4];");
				//out.println("            document.Form1.ENGIN_CAPACITY.value=data_vec[5];");
				//out.println("            alert('TEST1');");
				out.println("            getVatNetGro()");
				out.println("          }else if(opt==\"AMT\"){");
				out.println("            document.Form1.NET_AMOUNT.value  =data_vec[0];");
				out.println("            document.Form1.VAT_AMOUNT.value  =data_vec[1];");
				out.println("            document.Form1.GROSS_AMOUNT.value=data_vec[2];");
				out.println("            document.Form1.FIN_AMOUNT.value=data_vec[3];");
				//out.println("            alert('TEST2');");
				out.println("            cal_VatGross();");
				out.println("          }else if(opt==\"ITEM_SUB_CAT\"){");
				out.println("            Curr_no_opts=document.Form1.ITEM_SUB_CAT.options;");
				out.println("            Curr_no_opts.length=0;");
				out.println("            x=0;y=0;");
				out.println("            while(y<(data_vec.length/2)){");
			  out.println("              Curr_no_opts[y]= new Option(data_vec[x+1],data_vec[x+0]);");
				out.println("              x=x+2;");
				out.println("              y=y+1;");
				out.println("            }"); 
				out.println("            chk_make();");
			  out.println("          }else if(opt==\"ASSET_MAKE\"){");
				out.println("            Curr_no_opts=document.Form1.ASSET_MAKE.options;");
				out.println("            Curr_no_opts.length=0;");
				out.println("            x=0;y=0;");
				out.println("            while(y<(data_vec.length/2)){");
			  out.println("              Curr_no_opts[y]= new Option(data_vec[x+1],data_vec[x+0]);");
				out.println("              x=x+2;");
				out.println("              y=y+1;");
				out.println("            }");
				out.println("            loadcharges();");				
				out.println("          }");	
				out.println("          } else {");
        out.println("          if(opt==\"INQ_NO\"){");
				//out.println("            alert('No data found');");
				//out.println("            document.Form1.INQ_NO.value='';");
				out.println("            inq_help('1','10','13','InquirySql','7');");
				out.println("          }else if(opt==\"VENDOR\"){");
				//out.println("            alert('No data found');");
				out.println("            document.Form1.VENDOR.value='';");
				out.println("          }else if(opt==\"SUB_MODEL\"){");
				//out.println("            alert('No data found');");
				out.println("            document.Form1.SUB_MODEL.value='';");
				out.println("            getVatNetGro()");
				out.println("          }else if(opt==\"ASSET_MAKE\"){");
				out.println("            Curr_no_opts=document.Form1.ASSET_MAKE.options;");
				out.println("            Curr_no_opts.length=0;");
				out.println("            loadcharges();");
				out.println("          }else if(opt==\"ITEM_SUB_CAT\"){");
				out.println("            Curr_no_opts=document.Form1.ITEM_SUB_CAT.options;");
				out.println("            Curr_no_opts.length=0;");
				out.println("            loadcharges();");
				out.println("          }else if(opt==\"AMT\"){");
				out.println("            cal_VatGross();");
				out.println("          }else if (opt==\"Main\"){");			
				out.println("            main_id.innerHTML='<input type=hidden name=hid_m_count value=0><input type=hidden name=hid_y_count value=0>';");
        out.println("          }");
        out.println("          }");
    
				
				//out.println("          }");
				
				out.println("      }");
				out.println("    } else {");
        out.println("          if(opt==\"INQ_NO\"){");
				out.println("            alert('No data found');");
				out.println("            document.Form1.INQ_NO.value='';");
				out.println("          }else if(opt==\"VENDOR\"){");
				out.println("            alert('No data found');");
				out.println("            document.Form1.VENDOR.value='';");
				out.println("          }else if(opt==\"ASSET_MAKE\"){");
				out.println("            loadcharges();");
				out.println("          }else if(opt==\"ITEM_SUB_CAT\"){");
				out.println("            loadcharges();");
				out.println("          }else if(opt==\"SUB_MODEL\"){");
				out.println("            alert('No data found');");
				out.println("            document.Form1.SUB_MODEL.value='';");
				out.println("          }else if (opt==\"Main\"){");			
				out.println("            main_id.innerHTML='<input type=hidden name=hid_m_count value=0><input type=hidden name=hid_y_count value=0>';");
        out.println("          }");
        out.println("    }");
        out.println(" }");
        out.println("}");
				
				out.println("function getVatNetGro(type) {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_vat_net_gros&net_amt=\"+unformat_noobject(document.Form1.NET_AMOUNT.value)+\"&vat_prt=\"+unformat_noobject(document.Form1.VAT_PERCENTAGE.value)+\"&gro_amt=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&vat_app=\"+unformat_noobject(document.Form1.VAT_PER_APP.value)+\"&type=\"+type;");
				out.println(" makeRequest(url,'AMT','NO');");
        //out.println(" window.open(url)");
				out.println("}");
        
				out.println("function loadcharges(type) {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=loadcharges&price_no=\"+document.Form1.PRICE_NO.value+\"&item_sub_cat=\"+document.Form1.ITEM_SUB_CAT.value+\"\";");
				//out.println(" window.open(url);");
				out.println(" makeRequest(url,'SCHAR','NO');");
        out.println("}");
    				
				out.println("function chk_charge(i) {");
        out.println(" if(document.Form1.elements['CHARGE_'+i].value==''){");
				out.println("    document.Form1.elements['CHARGE_'+i].value='0.00';");
    		out.println(" }else{");
    		out.println("    document.Form1.elements['CHARGE_'+i].value=format_noobject(document.Form1.elements['CHARGE_'+i].value);");
    		out.println(" }");
				out.println(" cal_char_sum();");
    		out.println("}");
				
				out.println("function cal_char_sum() {");
        out.println("  M_CHR_SUM=0;");
				out.println("  for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
				out.println("     M_CHR_SUM = parseFloat(M_CHR_SUM)+parseFloat(unformat_noobject(document.Form1.elements['CHARGE_'+j].value));");
				out.println("   }");
				out.println("  if(parseFloat(document.Form1.c_c_count.value)>0){");
				out.println("    document.Form1.elements['Total_CHARGE'].value=format_noobject(M_CHR_SUM);");
    		out.println("  }");  
    		out.println("}");
    				
				out.println("function chk_inqno() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_inq_det&inqno=\"+document.Form1.INQ_NO.value+\"\";");
				out.println(" makeRequest(url,'INQ_NO','NO');");
        out.println("}");
        
				out.println("function chk_priceno() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_price_det&pno=\"+document.Form1.PRICE_NO.value+\"\";");
				out.println(" makeRequest(url,'PRICENO','NO');");
        //out.println(" window.open(url);");
        out.println("}");
            
				out.println("function chk_vendor() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_vendor&vendor=\"+document.Form1.VENDOR.value+\"\";");
				out.println(" makeRequest(url,'VENDOR','NO');");
        out.println("}");
        
				out.println("function chk_sub_model() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_sub_model&sub_model=\"+document.Form1.SUB_MODEL.value+\"\";");
				out.println(" makeRequest(url,'SUB_MODEL','NO');");
        out.println("}");
        
				out.println("function chk_sub_item() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_sub_item&code=\"+document.Form1.ITEM_CAT.value+\"\";");
				out.println(" makeRequest(url,'ITEM_SUB_CAT','NO');");
        out.println("}");
        
				out.println("function chk_item() {");
				out.println(" if(document.Form1.SUB_MODEL.value==\"\" && document.Form1.ITEM_SUB_CAT.value==\"0\"){");
        out.println("  url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_item&code=\"+document.Form1.ITEM_CAT.value+\"\";");
				out.println("  makeRequest(url,'ITEM_CAT','NO');");
        out.println(" }else{");
				out.println("  chk_sub_item();");
				out.println(" }");
        out.println("}");
        
				out.println("function get_sub_item_vat() {");
				out.println(" if(document.Form1.SUB_MODEL.value==\"\"){");
        out.println("   url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_sub_item_vat&code=\"+document.Form1.ITEM_SUB_CAT.value+\"\";");
				out.println("   makeRequest(url,'ITEM_SUB_VAT','NO');");
				out.println(" }else{");
				out.println("   chk_make();");
				out.println(" }");
        out.println("}");
        
				out.println("function chk_make() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_make&code=\"+document.Form1.ITEM_SUB_CAT.value+\"\";");
				out.println(" makeRequest(url,'ASSET_MAKE','NO');");
        out.println("}");
        
				//End Of Checking Values
				
				out.println("function asign_div(){");
				//out.println("alert(document.Form1.sun_fact.value);");
				//out.println("alert(document.Form1.sun_p_nt.value);");
				//out.println("format_num(document.Form1.sun_g_nt,0)");
				//out.println("format_num(document.Form1.sun_rent0)");
				//out.println("format_num(document.Form1.sun_p_nt,0)");
				//out.println("format_num(document.Form1.sun_fact,4)");
				out.println("g_re.innerHTML   =document.Form1.sun_g_nt.value");
				out.println("n_re.innerHTML   =document.Form1.sun_rent.value");
				out.println("p_va.innerHTML   =document.Form1.sun_p_nt.value");
				out.println("m_fact.innerHTML =document.Form1.sun_fact.value");
				out.println("document.Form1.hid_count.value=document.Form1.hid_cou.value;");
				out.println("document.Form1.GROSS_AMOUNT.value=document.Form1.hid_am.value;");
				out.println("document.Form1.RATE.value=document.Form1.hid_ra.value;");
				out.println("document.Form1.PERIOD.value=document.Form1.hid_pe.value;");
				out.println("getVatNetGro('GRO');");
				out.println("}");
			
				//Help Function
				out.println("function MyDialog(){");
				out.println("this.valout   = new Array(10);");
				out.println("}");	
				
				out.println("function get_help(Start,End,Hid_No,Crit,Sql,IfCount) {");			
				
				out.println("oBj = new MyDialog();");
				out.println("oBj.valout[3]  = \" \";");
				out.println("oBj.valout[4]  = \" \";");
				out.println("oBj.valout[5]  = \" \";");
				//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				//out.println("alert('333333--'+oBj.valout[4]+'--'+oBj.valout[1]+'--');");
				
				out.println("if(oBj.valout[1]=='Next')  {");
				out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[1]=='Prev') {");
				out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[1] == 'Close'){");
				out.println("if(IfCount=='7'){"); 
				out.println("document.Form1.INQ_NO.value ='';");
				out.println("}");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
				//out.println("if(oBj.valout[1] == null){");
				//out.println("alert('3333333333');");
				//out.println("}else{");
				//out.println("alert('44444444444');");
				out.println("if(IfCount=='1'){"); 
				out.println("client_assign(oBj);");
				//out.println("load_data(document.Form1.txt_aff_code.value);");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("modle_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("");
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("vendor_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='5'){"); 
				out.println("mk_trn_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='6'){"); 
				out.println("mk_sub_trn_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='7'){"); 
				out.println("inq_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='8'){"); 
				out.println("price_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='9'){"); 
				out.println("int_base_assign(oBj);");
				out.println("}");
				//out.println("}");
				
				
				out.println("}");
				out.println("else if(oBj.valout[4] != \" \"){ ");
				//out.println("alert('5555555555');");
				out.println("Crit = oBj.valout[4];");
				out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
				out.println("}	");
				out.println("}");	
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount)");
				out.println("}");
				
				out.println("function Next(Start,End,Hid_No,Crit,Sql,IfCount){");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount)");
				out.println("}");
				
				out.println("function criteria_1(Start,End,Hid_No,Crit,Sql,IfCount){");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				//client Help
				out.println("function model_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.ASSET_MAKE.value+\"@\"+document.Form1.SUB_MODEL.value+\"@Y@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function modle_assign(oBj){");
				//out.println(" alert('oBj.valout[2]='+oBj.valout[2]);");
				out.println(" document.Form1.SUB_MODEL.value      =oBj.valout[2]");
				out.println(" document.Form1.VAT_PERCENTAGE.value =oBj.valout[4]");
				out.println(" document.Form1.VAT_PER_APP.value =oBj.valout[5]");
				out.println(" document.Form1.MODEL_CODE.value =oBj.valout[6]");
				out.println(" document.Form1.FUEL_TYPE.value =oBj.valout[8]");
				out.println(" document.Form1.ENGIN_CAPACITY.value =oBj.valout[9]");
				out.println("}");		
				//Marketing Officer
				out.println("function vendor_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.VENDOR.value+\"@Y@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function vendor_assign(oBj){");
				out.println(" document.Form1.VENDOR.value =oBj.valout[2]");
				out.println(" document.Form1.VENDOR_CODE.value =oBj.valout[1]");
				out.println("}");				
				
				out.println("function trn_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TRANSACTION_CODE.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_trn_assign(oBj){");
				out.println(" document.Form1.TRANSACTION_CODE.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				out.println("function sub_trn_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TRANSACTION_SUB.value+\"@\"+document.Form1.TRANSACTION_TYPE.value+\"@A@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_sub_trn_assign(oBj){");
				out.println(" document.Form1.TRANSACTION_SUB.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				out.println("function inq_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.INQ_NO.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function inq_assign(oBj){");
				out.println(" document.Form1.INQ_NO.value =oBj.valout[2]");
				out.println("}");		
				
				
				out.println("function int_base_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.INTEREST_BASE.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function int_base_assign(oBj){");
				out.println(" document.Form1.INTEREST_BASE.value =oBj.valout[2]");
				out.println(" document.Form1.BASE_INT_RATE.value =oBj.valout[4]");
				out.println(" cal_int_rate();");
				out.println("}");		
								
				out.println("function price_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.PRICE_NO.value+\"@\"+document.Form1.INQ_NO.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				
				out.println("function price_assign(oBj){");
				out.println(" document.Form1.PRICE_NO.value =oBj.valout[2]");
				out.println(" chk_priceno();");
				out.println("}");		
				
				//End of Help Function
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
				
				out.println("function change_repay(m_type){");
				out.println(" if(m_type=='TYPE'){");
				out.println("    document.Form1.REPAYMENT_INTERVAL.selectedIndex=document.Form1.REPAYMENT_TYPE.selectedIndex ");
				out.println(" }else{");
				out.println("    document.Form1.REPAYMENT_TYPE.selectedIndex=document.Form1.REPAYMENT_INTERVAL.selectedIndex ");
				out.println(" }");
				out.println("}");
				
				//Main Button Action
				//Submit
				out.println("function befor_submit(){");
				out.println(" m_bsubmit='0';");
				out.println(" if(document.Form1.TRANSACTION_TYPE.value==''){ ");
				out.println("   trntype.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.TRANSACTION_SUB.value=='' ){ ");
				out.println("   trnsub.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.INTEREST_TYPE.value==''){  ");
				out.println("      inttype.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.ASSET_CONDITION.value==''){  ");
				out.println("      conass.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.PERIOD.value==''){  ");
				out.println("      peri.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.RATE.value==''){  ");
				out.println("      ra.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");				//document.Form1..value
				out.println(" if(document.Form1.VAT_PER_APP.value==''){  ");
				out.println("      vatapp.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.VAT_PERCENTAGE.value==''){  ");
				out.println("      vat.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.GROSS_AMOUNT.value==''){  ");
				out.println("      GAM.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.NET_AMOUNT.value==''){  ");
				out.println("      NAM.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.VAT_AMOUNT.value==''){  ");
				out.println("      VAM.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.RESIDUAL_VALUE.value==''){  ");
				out.println("      RES.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.NIBSM.value==''){  ");
				out.println("      NIB.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.AMI.value==''){  ");
				out.println("      ADV.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				
				out.println(" if(m_bsubmit=='0' && parseFloat(document.Form1.CAL_COUNT.value)>0){  ");
				out.println("      alert('Please calculate values before save.');");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				
				out.println(" if(m_bsubmit=='0'){");
				out.println("  if(document.Form1.OPTION_NAME.value==\"NEW\" && document.Form1.PRICE_NO.value!=\"\"){ ");
				//out.println("   alert();");
				out.println("  }else{");
				
				out.println("   document.Form1.VAT_AMOUNT.value=unformat_noobject(document.Form1.VAT_AMOUNT.value);");
				out.println("   document.Form1.NET_AMOUNT.value=unformat_noobject(document.Form1.NET_AMOUNT.value);");
				out.println("   document.Form1.GROSS_AMOUNT.value=unformat_noobject(document.Form1.GROSS_AMOUNT.value);");
				out.println("   for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
				out.println("     document.Form1.elements[\"PRACENT\"+i].value = unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value) ");// unformat_noobject(
				out.println("     document.Form1.elements[\"NETAMT\"+i].value = unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value) ");//
				out.println("     document.Form1.elements[\"GROSS\"+i].value = unformat_noobject(document.Form1.elements[\"GROSS\"+i].value) ");//");//unformat_noobject(
				out.println("     document.Form1.elements[\"FACTOR\"+i].value = unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value) ");//");//unformat_noobject(
				
				out.println("     document.Form1.elements[\"PRACENT\"+i].disabled = false; ");// unformat_noobject(
				out.println("     document.Form1.elements[\"NETAMT\"+i].disabled  = false; ");//
				out.println("     document.Form1.elements[\"GROSS\"+i].disabled   = false; ");//");//unformat_noobject(
				out.println("     document.Form1.elements[\"FACTOR\"+i].disabled  = false; ");//");//unformat_noobject(
				
				out.println("   }");
				
				out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
				out.println("     document.Form1.elements['CHARGE_'+j].value=unformat_noobject(document.Form1.elements['CHARGE_'+j].value);");
				out.println("     document.Form1.elements['CHARGE_PER_'+j].value=unformat_noobject(document.Form1.elements['CHARGE_PER_'+j].value);");
				out.println("   }");
				//out.println("alert(document.Form1.OUT_COUNT.value);");
				out.println("    if(parseFloat(document.Form1.OUT_COUNT.value)==0){");
				out.println("     outflow_id.innerHTML = '<input type=hidden name=\"month0\"       value='+document.Form1.SUPPLIER_CREDIT.value+'>'+ ");
				out.println("                            '<input type=hidden name=\"cash_amount0\" value='+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+'>';");
				out.println("     document.Form1.OUT_COUNT.value = '1'; ");
				//out.println("alert(document.Form1.month0.value);");
				
				out.println("   }else{");
				out.println("    for(i=0;i<parseFloat(document.Form1.OUT_COUNT.value);i++){");
				out.println("     document.Form1.elements[\"month\"+i].value           = unformat_noobject(document.Form1.elements[\"month\"+i].value) ");//");//unformat_noobject(
				out.println("     document.Form1.elements[\"cash_amount\"+i].value     = unformat_noobject(document.Form1.elements[\"cash_amount\"+i].value) ");//");//unformat_noobject(
				//out.println("     alert(document.Form1.elements[\"month\"+i].value); ");// unformat_noobject(
				//out.println("     alert(document.Form1.elements[\"cash_amount\"+i].value); ");// unformat_noobject(
				out.println("     document.Form1.elements[\"month\"+i].disabled        = false; ");// unformat_noobject(
				out.println("     document.Form1.elements[\"cash_amount\"+i].disabled  = false; ");//
				out.println("    }");
				out.println("   }");
				
				/*
				out.println("   
				out.println("	window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
				out.println(" m_str = m_str+'</table>'; ");
				out.println(" window.opener.outflow_id.innerHTML = m_str; ");
				out.println(" window.opener.document.Form1.CAL_COUNT.value = '1'; ");
				out.println(" window.opener.document.Form1.OUT_COUNT.value = m_cashcount; ");
				*/
				out.println("   document.Form1.RESIDUAL_VALUE.disabled        = false; ");// unformat_noobject(
				out.println("   if(document.Form1.OPTION_NAME.value==\"NEW\"){m_val='Save'}else{m_val='Modify' }  ");
				out.println("   if(confirm(\"Are you sure you want to \"+m_val+\" ?\")){  ");
				out.println("     document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Save\";");
				out.println("     document.Form1.submit();");
				out.println("  }");
				
				out.println("  }");
				out.println(" }else { ");
		    out.println("   alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
		    out.println(" }");
				out.println("}");
				//end of Submit Function
				
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
				//out.println("  Form1.reset() ;  ");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				if(m_pri_no==null && m_screen==null){//
				out.println("   close_window(); ");
				}else{
				out.println("  if(confirm(\"Are you sure you want to close the screen?\")){ ");
		    out.println("    window.close(); ");
				out.println("  }");
				}
				//out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  top.frames[1].location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";");
				//out.println("  Form1.reset() ;  ");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				//out.println("  document.Form1.OPTION_DESC.value=\"B\";");
				//out.println("  document.Form1.PRICE_NO.disabled=true;");
				//out.println("  document.Form1.inqu_help.disabled=true;");
				//out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are you sure you want to modify record?\")){  ");
				//out.println("  Form1.reset();   ");
				out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
				//out.println("  alert(document.Form1.OPTION_DESC.value);");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_active(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset() ;  ");
				out.println("  document.Form1.OPTION_NAME.value=\"ACT\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Re-activate\";");
				//out.println("  alert(document.Form1.OPTION_DESC.value);");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_deactive(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset();   ");
				out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
				out.println("  document.Form1.OPTION_DESC.value=\"De-activate\";");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=fales;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_reset(){");
				out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
				//out.println("  Form1.reset()   ");
			  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=main_page'");
			  out.println(" }  ");
				out.println("}");
				
				out.println("function befor_cal(m_stat,opt) {");
				out.println("   document.Form1.cal_charge.value=\"NO\";");
        out.println("   befor_target();");
				out.println(" if((document.Form1.TRARGET.value==\"RENTAL\" && (document.Form1.RATE.value!=\"\" && parseFloat(unformat_noobject(document.Form1.RATE.value))!=\"0\")     && (document.Form1.GROSS_AMOUNT.value!=\"\" && parseFloat(unformat_noobject(document.Form1.GROSS_AMOUNT.value))!=\"0\") && (document.Form1.PERIOD.value!=\"\"        && parseFloat(unformat_noobject(document.Form1.PERIOD.value))!=\"0\")) || ");
				out.println("    (document.Form1.TRARGET.value==\"PERIOD\" && (document.Form1.RATE.value!=\"\" && parseFloat(unformat_noobject(document.Form1.RATE.value))!=\"0\")     && (document.Form1.GROSS_AMOUNT.value!=\"\" && parseFloat(unformat_noobject(document.Form1.GROSS_AMOUNT.value))!=\"0\") && (document.Form1.RENTAL_AMOUNT.value!=\"\" && parseFloat(unformat_noobject(document.Form1.RENTAL_AMOUNT.value))!=\"0\")) ||");
				out.println("    (document.Form1.TRARGET.value==\"RATE\"   && (document.Form1.PERIOD.value!=\"\" && parseFloat(unformat_noobject(document.Form1.PERIOD.value))!=\"0\") && (document.Form1.GROSS_AMOUNT.value!=\"\" && parseFloat(unformat_noobject(document.Form1.GROSS_AMOUNT.value))!=\"0\") && (document.Form1.RENTAL_AMOUNT.value!=\"\" && parseFloat(unformat_noobject(document.Form1.RENTAL_AMOUNT.value))!=\"0\")) ||");
				out.println("    (document.Form1.TRARGET.value==\"AMOUNT\" && (document.Form1.RATE.value!=\"\" && parseFloat(unformat_noobject(document.Form1.RATE.value))!=\"0\")     && (document.Form1.PERIOD.value!=\"\"       && parseFloat(unformat_noobject(document.Form1.PERIOD.value))!=\"0\")       && (document.Form1.RENTAL_AMOUNT.value!=\"\" && parseFloat(unformat_noobject(document.Form1.RENTAL_AMOUNT.value))!=\"0\")) ) {");
				
        out.println(" if(document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.VAT_PER_APP.value !=\"\" ){");//document.Form1.RATE.value!=\"\" && document.Form1.NET_AMOUNT.value !=\"\" && document.Form1.GROSS_AMOUNT.value!=\"\" && 
				out.println("  document.Form1.CAL_COUNT.value=0;");
        out.println("  if(opt==\"YES\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price\";");
        //out.println(" }else{");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=get_basic_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        out.println(" }");
        out.println("   m_opt=document.Form1.CACULATED.value;");
        out.println("   document.Form1.CACULATED.value=\"YES\";");
        out.println("   makeRequest(m_url,m_stat,opt);");
        out.println(" }else if(parseFloat(document.Form1.hid_count.value)>0 && (document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.VAT_PER_APP.value !=\"\")){");//document.Form1.RATE.value!=\"\" || document.Form1.NET_AMOUNT.value !=\"\" || document.Form1.VAT_PERCENTAGE.value !=\"\" || document.Form1.PERIOD.value !=\"\" || document.Form1.GROSS_AMOUNT.value!=\"\"
				out.println("  document.Form1.CAL_COUNT.value=0;");
        out.println("  if(opt==\"YES\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price\";");
        //out.println("  }else{");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=get_basic_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        out.println("  }");
        out.println("   m_opt=document.Form1.CACULATED.value;");
        out.println("   document.Form1.CACULATED.value=\"YES\";");
        out.println("   makeRequest(m_url,m_stat,opt);");
        out.println(" }else{");
				out.println("   alert('Please Enter ');");
        out.println(" }");
        out.println(" }else{");//main if
				out.println("   alert('Please enter correct amounts');");
        out.println(" }");
        
        out.println("}");
				
				out.println("function load_flow() {");
        out.println(" if(document.Form1.RATE.value!=\"\" && document.Form1.NET_AMOUNT.value !=\"\" && document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.PERIOD.value !=\"\" && document.Form1.GROSS_AMOUNT.value!=\"\"){");
				out.println("	 m_send_val = \"chksql=get_outflow_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&rental=\"+unformat_noobject(document.Form1.RENTAL_AMOUNT.value)+\"&1rental=\"+unformat_noobject(document.Form1.RENTAL1_AMOUNT.value)+");				
				out.println("	              \"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
				out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
				out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&tax_rent=\"+document.Form1.VAT_PER_RENT.value+\"&installments=\";");
									
				out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
				out.println("	 }else{");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
				out.println("	 }");
				out.println("	 m_fact_val = \"\";"); 
				out.println("	 m_prac_val = \"\";"); 
				out.println("  m_percentage = \"\";"); 
				out.println("  m_char_val = \"0\";"); 
				out.println("  m_main_val = \"0\";"); 
				out.println("  m_mainten  = \"\";"); 
				out.println("	 m_vat_val = \"\";"); 
				out.println("	 m_gro_val = \"\";"); 
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
			  out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
				out.println("    m_fact_val   = m_fact_val+/*unformat_noobject*/(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
			  out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
				out.println("    m_vat_val    = m_vat_val+unformat_noobject(document.Form1.elements[\"VATAMT\"+i].value)+\"@\";");
				out.println("    m_gro_val    = m_gro_val+unformat_noobject(document.Form1.elements[\"GROSS\"+i].value)+\"@\";");
				
				out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
				//out.println("    alert('m_percentage'+m_percentage);");
			  out.println("	 }");
				out.println("  for(i=0;i<parseFloat(document.Form1.c_c_count.value);i++){");
			  out.println("   if(document.Form1.elements[\"CHARGE_TYPE_\"+i].value==\"AMO\"){");
			  out.println("    m_char_val   = parseFloat(m_char_val)+parseFloat(unformat_noobject(document.Form1.elements[\"CHARGE_\"+i].value));");
				out.println("	  }");
				out.println("	 }");
				
				out.println("  for(j=0;j<=parseFloat(document.Form1.hid_y_count.value);j++){");
				out.println("      m_main_val   =0;");
			  out.println("    for(i=0;i<parseFloat(document.Form1.hid_m_count.value);i++){");
			  out.println("      m_main_val   = parseFloat(m_main_val)+parseFloat(unformat_noobject(document.Form1.elements[\"MAINTANENCE_\"+i+\"_\"+j].value));");
				out.println("	   }");
				out.println("    m_mainten   = m_mainten+m_main_val+\"@\";");
				out.println("	 }");
				
				out.println("    m_send_val = m_send_val+\"&maintan=\"+m_mainten+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage+");
				out.println("                 \"&vat_val=\"+m_vat_val+\"&gro_val=\"+m_gro_val;");
				//out.println("   m_str=construct_URL(\"get_outflow_cal\",\"\");");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?\"+m_send_val;");
				//out.println("   alert(m_url);");
				out.println("   popupwin = window.open(m_url,'displayWindow1','left=50,top=300,width=900,height=340,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
        out.println(" }else{");
				out.println("   alert('Please Enter ');");
        out.println(" }");
        
        out.println("}");
        
				out.println("function befor_main(m_stat,opt) {");
        //out.println("   alert('func ');");
        out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
        //out.println("   alert('if ');");
				out.println(" if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=loadMaintenance&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_rent=\"+document.Form1.VAT_PER_RENT.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&make=\"+document.Form1.ITEM_SUB_CAT.value+\"&model=\"+document.Form1.SUB_MODEL.value+\"&sub_type=\";");
        out.println(" }else{");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=loadMaintenance&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_rent=\"+document.Form1.VAT_PER_RENT.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&make=\"+document.Form1.ITEM_SUB_CAT.value+\"&model=\"+document.Form1.SUB_MODEL.value+\"&sub_type=\";");
        out.println(" }");  
				
				out.println("   makeRequest(m_url,\"Main\",'NO');");
        out.println(" }else{");
				out.println("   main_id.innerHTML='<input type=hidden name=hid_m_count value=0><input type=hidden name=hid_y_count value=0>';");
        out.println(" }");
        out.println("}");
				
				
				
				out.println("function cal_int_rate() {");
        //out.println("   alert('func ');");
        out.println(" if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				out.println("  if(document.Form1.INTEREST_MARGIN.value!=\"\" && document.Form1.BASE_INT_RATE.value!=\"\"){");
				out.println("   document.Form1.RATE.value=format_noobject(parseFloat(document.Form1.BASE_INT_RATE.value)+parseFloat(document.Form1.INTEREST_MARGIN.value));");
        out.println("  }");  
				out.println(" }else{");
				out.println("   ");
        out.println(" }");  
				out.println("}");
				
				out.println("function befor_subtype(m_stat,opt) {");
        out.println("            if(document.Form1.TRANSACTION_TYPE.value=='HIREPURCH'){");
				out.println("            document.Form1.VAT_PERCENTAGE.value='0';");
				out.println("            document.Form1.VAT_PER_APP.value='0';");
				out.println("            getVatNetGro()");
				out.println("            }");
				//out.println("   alert('func ');");
        //out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
        //out.println("   alert('if ');");
        out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=chkSubType&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        out.println("   makeRequest(m_url,\"SubType\",'NO');");
        //out.println(" }else{");
				//out.println("   main_id.innerHTML='';;");
        //out.println(" }");
        
        out.println("}");
				
				out.println("function befor_target(m_stat,opt) {");
        out.println(" if(document.Form1.TRARGET.value==\"RENTAL\"){");
        out.println("      ra.style.color=\"black\";");
				out.println("      GAM.style.color=\"black\";");
				out.println("      NAM.style.color=\"black\";");
				out.println("      VAM.style.color=\"black\";");
				out.println("      peri.style.color=\"black\";");
				out.println("      document.Form1.RENTAL_AMOUNT.value='0';");
				out.println(" }else if(document.Form1.TRARGET.value==\"AMOUNT\"){");
        out.println("      GAM.style.color=\"red\";");
				out.println("      NAM.style.color=\"red\";");
				out.println("      VAM.style.color=\"red\";");
				out.println("      ra.style.color=\"black\";");
				out.println("      peri.style.color=\"black\";");
				out.println("      document.Form1.GROSS_AMOUNT.value='0';");
				out.println("      document.Form1.NET_AMOUNT.value='0';");
				out.println("      document.Form1.VAT_AMOUNT.value='0';");
				out.println(" }else if(document.Form1.TRARGET.value==\"RATE\"){");
        out.println("      ra.style.color=\"red\";");
				out.println("      GAM.style.color=\"black\";");
				out.println("      NAM.style.color=\"black\";");
				out.println("      VAM.style.color=\"black\";");
				out.println("      peri.style.color=\"black\";");
				out.println("      document.Form1.RATE.value='0';");
				out.println(" }else if(document.Form1.TRARGET.value==\"PERIOD\"){");
        out.println("      ra.style.color=\"black\";");
				out.println("      GAM.style.color=\"black\";");
				out.println("      NAM.style.color=\"black\";");
				out.println("      VAM.style.color=\"black\";");
				out.println("      peri.style.color=\"red\";");
				out.println("      document.Form1.PERIOD.value='0';");
				out.println(" }");
        out.println(" befor_subtype();");
        out.println("}");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				//end of Main Button Action
				//onload Action
				
				out.println("function befor_onload(){");
				out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); 
				out.println("}");
				//end of onload
				
				out.println("function  cal_VatNet(){");
				out.println("  if(document.Form1.GROSS_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
				//out.println("   document.Form1.NET_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.GROSS_AMOUNT.value))/((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
				//out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
				
				out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
				out.println("    if(document.Form1.elements['CHARGE_'+j].value=='0' || document.Form1.elements['CHARGE_'+j].value==null){");
				out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value);");
				out.println("    }");
				out.println("   }");
				
				//out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.GROSS_AMOUNT.value));");
				out.println("  }");
				out.println("}");
				
				out.println("function cal_VatGross(){");
				out.println(" if(document.Form1.cal_charge.value==\"YES\"){");
        out.println("  if(document.Form1.NET_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
				//out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
				//out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
				//out.println("   alert(document.Form1.c_c_count.value);");
				out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
				out.println("    if(document.Form1.elements['CHARGE_PER_'+j].value!='0' && document.Form1.elements['CHARGE_PER_'+j].value!=null){");
				out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject0(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value,0);");
				out.println("    }");
				out.println("   }");
				//out.println("   document.Form1.NET_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.NET_AMOUNT.value));");
				out.println("  }");
				out.println("  cal_char_sum();");
				out.println(" }");
				out.println(" document.Form1.cal_charge.value=\"NO\";");
        //out.println("  befor_main();");
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Marketing - Pricing - \"+m_val;"); 
			  out.println("}");
				
				out.println("   ");
				out.println("function chan_residual(){"); 
			  out.println(" if(parseFloat(unformat_noobject(document.Form1.NIBSM.value))>0){"); 
			  out.println("  document.Form1.RESIDUAL_VALUE.value=format_noobject(document.Form1.NIBSM.value);"); 
			  out.println("  document.Form1.NIBSM.value=format_noobject(document.Form1.NIBSM.value);"); 
				out.println("  document.Form1.RESIDUAL_VALUE.disabled=true;");
			  out.println(" }else{"); 
			  out.println("  document.Form1.RESIDUAL_VALUE.value='0';");
			  out.println("  document.Form1.RESIDUAL_VALUE.disabled=false;");
			  out.println(" }"); 
			  out.println("}");
				
				out.println("function format_text(obj){"); 
			  out.println("  obj.value=format_noobject(obj.value);"); 
			  out.println("}");
				
				out.println("function format_num(obj,i){");
				//out.println("  alert((obj.value));");
				out.println("  if(isNaN(unformat_noobject(obj.value))){");
				out.println("   alert('Please Enter Number!');");
				out.println("   obj.value='0';");
				out.println("  }else{");
				//out.println("   alert('www'+obj.value);");
				out.println("   if(i!='6'){");
				out.println("    m_val_o=unformat_noobject(obj.value)");
				out.println("    obj.value=format_noobject(m_val_o);"); 
			  out.println("   }");
				out.println("  }");
				out.println("}");
				
				out.println("function checkfor_num(obj){");
				out.println("  if(isNaN(obj)){");
				out.println("   alert('Please Enter Number!');");
				out.println("   obj.value='0';");
				out.println("  }");
				out.println("}");
				
				out.println("function cha_val(count){"); 
				out.println(" if(document.Form1.TRANSACTION_SUB.value==\"STEPUP\" || document.Form1.TRANSACTION_SUB.value==\"STEPDOWN\"){ ");
			  out.println("  if(confirm(\"Do you want apply this change to all the below installments?\")){"); 
			  out.println("    for(i=parseFloat(count)+1;i<parseFloat(document.Form1.hid_count.value);i++){"); 
			  out.println("     document.Form1.elements[\"PERCENTAGE\"+i].value = unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+count].value) ");// unformat_noobject(
				out.println("    }");
				out.println("  }");
				out.println(" }");
				out.println("}");
				
				out.println("function cal_count(){"); 
			  out.println("  document.Form1.CAL_COUNT.value=parseFloat(document.Form1.CAL_COUNT.value)+1;");
				//out.println("      cal.style.color=\"red\";");
				out.println("}");
				
				out.println("function load_fild(){"); 
			  out.println(" if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				/*
				out.println("   int_base.innerHTML='Variable Interest Base'; ");
				out.println("   int_base_txt.innerHTML='<input name=\"INTEREST_BASE\" type=\"text\" maxlength=\"50\" class=\"txt_input\" >';; ");
				out.println("   int_mar.innerHTML='Variable Interest Margin'; ");
				out.println("   int_mar_txt.innerHTML='<input name=\"INTEREST_MARGIN\" type=\"text\" maxlength=\"50\" class=\"txt_input\" >';; ");
				*/
				out.println("   int_type.innerHTML='<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >'+");
				out.println("                      '<tr class=tr_input><td width=25%>Variable Interest Base</td>'+");
				out.println("                      '<td  ><input name=\"INTEREST_BASE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=\"cal_int_rate()\" disabled>'+");
				out.println("                      '<input type=button name=intbase_help value=Help class=\"but_input\" onclick=int_base_help(\"1\",\"10\",\"0\",\"IntBaseSql\",\"9\")></td></tr>'+");
        out.println("                      '<tr class=tr_input><td >Variable Interest Margin</td>'+");
				out.println("                      '<td ><input name=\"INTEREST_MARGIN\" type=\"text\" maxlength=\"6\" class=\"txt_input\" onchange=\"cal_int_rate()\"></td></tr></table>';");
				out.println(" }else{");
				out.println("   int_type.innerHTML='';");
				out.println(" }");
				out.println("}");
				
				out.println("function assing_pval(data){");
				out.println(" document.Form1.PRICE_NO.value =data[0]");
				out.println(" document.Form1.RATE.value =data[6]");
				out.println(" document.Form1.VAT_PERCENTAGE.value =data[19]");
				out.println(" document.Form1.VAT_PER_APP.value =data[36]");
				out.println(" document.Form1.PERIOD.value =data[4]");
				out.println(" document.Form1.GROSS_AMOUNT.value =data[7]");
				out.println(" document.Form1.VAT_AMOUNT.value =data[8]");
				out.println(" document.Form1.NET_AMOUNT.value =data[9]");
				out.println(" document.Form1.NIBSM.value =data[10]");
				out.println(" document.Form1.AMI.value =data[11]");
				out.println(" document.Form1.RESIDUAL_VALUE.value =data[12]");
				out.println(" document.Form1.SUPPLIER_CREDIT.value =data[25]");
				out.println(" document.Form1.INTEREST_TYPE.value =data[32]");
				out.println(" document.Form1.PAYMENT_MODE.value =data[26]");
				out.println(" document.Form1.REPAYMENT_INTERVAL.value =data[5]");
				out.println(" document.Form1.INQ_NO.value =data[1]");
				out.println(" document.Form1.TRANSACTION_TYPE.value =data[2]");
				out.println(" document.Form1.TRANSACTION_SUB.value =data[3]");
				out.println(" document.Form1.ASSET_CONDITION.value =data[35]");
				out.println(" document.Form1.ITEM_CAT.value =data[13]");
				out.println(" document.Form1.ITEM_SUB_CAT.value =data[14]");
				out.println(" document.Form1.ASSET_MAKE.value =data[15]");
				out.println(" document.Form1.SUB_MODEL.value =data[17]");
				
				out.println(" document.Form1.ENGIN_CAPACITY.value =data[20]");
				out.println(" document.Form1.FUEL_TYPE.value =data[21]");
				out.println(" document.Form1.BUY_BACK.value =data[31]");
				out.println(" document.Form1.VENDOR.value =data[33]");
				out.println(" document.Form1.ASSET_USAGE.value =data[18]");
				out.println(" document.Form1.MAINTENANCE_APP.value =data[23]");
				//out.println(" document.Form1.SUB_MODEL.value =data[2]");
				//out.println(" document.Form1.SUB_MODEL.value =data[2]");
				//out.println(" document.Form1.SUB_MODEL.value =data[2]");
				//out.println(" document.Form1.SUB_MODEL.value =data[2]");
				//out.println(" document.Form1.SUB_MODEL.value =data[2]");
				out.println("befor_loadPrice();");
				out.println("}");	
				
				out.println("function befor_loadPrice(m_stat,opt) {");
        //out.println("   alert('func ');");
        out.println(" if(document.Form1.PRICE_NO.value!=''){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=loadPrice&price_no=\"+document.Form1.PRICE_NO.value+\"&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+opt+");
				out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
				out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&tax_rent=\"+document.Form1.VAT_PER_RENT.value+\"\";");
        
				out.println("   makeRequest(m_url,\"Main_Price\",'NO');");
        out.println(" }else{");
				out.println("   main_id.innerHTML='<input type=hidden name=hid_m_count value=0><input type=hidden name=hid_y_count value=0>';");
        out.println(" }");
        out.println("}");
				
				out.println("function assing_default(){");
				rs = stmt.executeQuery(" SELECT A.INTEREST_RATE, A.VAT_PER, A.VAT_APP, "+
				                       "        A.PERIOD, NVL(A.AMOUNT,0) "+
                               " FROM   "+m_schema_name+".AF_CO_MAS_PRICING_DEFAULT_VAL A");
				boolean more = rs.next();
				if(more){
				  out.println(" document.Form1.RATE.value ="+rs.getString(1)+";");
					out.println(" document.Form1.VAT_PERCENTAGE.value ="+rs.getString(2)+"");
					out.println(" document.Form1.VAT_PER_APP.value ="+rs.getString(3)+"");
					out.println(" document.Form1.VAT_PER_RENT.value ="+rs.getString(3)+"");
					out.println(" document.Form1.PERIOD.value ="+rs.getString(4)+"");
					if(amount==null){
		    		out.println(" document.Form1.GROSS_AMOUNT.value ="+rs.getString(5)+"");
					}else{
					  out.println(" document.Form1.GROSS_AMOUNT.value ="+amount+"");
					}
				  out.println(" getVatNetGro('GRO');");
					out.println(" cal_count();");
				}else{
				  out.println(" document.Form1.GROSS_AMOUNT.value ="+amount+"");
					out.println(" getVatNetGro('GRO');");
					out.println(" cal_count();");
				}
				if(m_pri_no=="" || m_pri_no==null){
				}else{
				  out.println(" chk_priceno();");
				}
				out.println("}");
				
				out.println("function HelpBox_msg() {");
	      out.println("  popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+\"&help_message_in=pricing_help\"); ");
        out.println("}");
				
				
				out.println("</Script>");
				out.println("<body onload=\"befor_onload();assing_default();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\"  value=\"AF_MK_PRICE\">");
				out.println("<input type=hidden name=\"OPTION_NAME\"   value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\"   value=\"New\">");
				out.println("<input type=hidden name=\"CURRENCY_CODE\" value=\"SLR\">");
				out.println("<input type=hidden name=\"EXCHANGE_RATE\" value=\"0\">");
				out.println("<input type=hidden name=\"CACULATED\"     value=\"NO\">");
				out.println("<input type=hidden name=\"CAL_COUNT\"     value=\"0\">");
				out.println("<input type=hidden name=\"OUT_COUNT\"     value=\"0\">");
				out.println("<input type=hidden name=\"CASH_OUTFLOW\"  value=\"\">");
				out.println("<input type=hidden name=\"BASE_INT_RATE\" value=\"0\">");
				out.println("<input type=hidden name=\"cal_charge\"    value=\"YES\">");
				out.println("<input type=hidden name=\"WINDOW_STATUS\" value=\""+m_screen+"\">");
				
				if(m_app_no=="" || m_app_no==null ){ 
				  out.println("<input type=hidden name=\"APP_NO\"  value=\"\">");
				}else{
				  out.println("<input type=hidden name=\"APP_NO\"  value=\""+m_app_no+"\">");
				}
				
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr>");
				/*out.println("<td style=\"width: 6px\"><img src=\""+m_html_client_url+"/images/btnback.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><img src=\""+m_html_client_url+"/images/btndelete.gif\" ></td>");
				//out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_deactive();><img src=\""+m_html_client_url+"/images/btndelete.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_modify();><img src=\""+m_html_client_url+"/images/btnedit.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_active();><img src=\""+m_html_client_url+"/images/btncancel.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_reset();><img src=\""+m_html_client_url+"/images/btnreset.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_submit();><img src=\""+m_html_client_url+"/images/btnsubmit.gif\" ></td>");*/
				
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=new      value=\"New\"       class=mainbut onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit     value=\"Edit\"      class=mainbut onclick=befor_modify();   onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete   value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel   value=\"Re-active\" class=mainbut onclick=befor_active();   onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_submit value=\"Save\"      class=mainbut onclick=befor_submit();   onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=delete value=\"Help\" class=mainbut onclick=HelpBox_msg(); onMouseOver='load_roll_value(\"Help\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_   value=\"Cancel\"     class=mainbut onclick=befor_reset();    onMouseOver='load_roll_value(\"Cancel\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td id=cal_but><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td ><input type=button name=val     value=\"Valuation\" class=mainbut onclick=view_report(document.Form1.SUB_MODEL.value,document.Form1.MODEL_CODE.value);      onMouseOver='load_roll_value(\"Valuation\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        //out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=recal     value=\"Re-Cal\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");     onMouseOver='load_roll_value(\"Re-Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=end_b     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\" align=center><b>");
				//out.println("<div id=plsw> </div></b></td>");
				out.println("</tr>");
					
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				/*
				out.println("<tr class=tr_input>");
				out.println("<td width=\"15%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"120\" ></td>");
				out.println("<td width=\"35%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"180\" ></td>");
				out.println("<td width=\"15%\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				out.println("<td>");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				out.println("</tr>");
				*/
				out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=45%>");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//sub table start(data entry)
				
				out.println("<tr class=tr_input>");
				out.println("<td><div id=targ class=div_input>Target Filed *</div></td>");
				out.println("<td>");
				out.println("<select name=\"TRARGET\" class=\"txt_input\" onchange=\"befor_target()\" >");
				out.println("<OPTION value=\"RENTAL\">Rental</option>");
				out.println("<OPTION value=\"AMOUNT\">Amount</option>");
				out.println("<OPTION value=\"RATE\">Interest Rate</option>");
				out.println("<OPTION value=\"PERIOD\">Period</option>");
				out.println("</SELECT>");
				
				out.println("</td>");
				out.println("</tr>");


				
				out.println("<tr class=tr_input>");
				out.println("<td><div id=trntype class=div_input>Transaction Type *</div></td>");
				out.println("<td>");
				out.println("<select name=\"TRANSACTION_TYPE\" class=\"txt_input\" onchange=\"befor_subtype()\" >");
				
				rs = stmt.executeQuery(CO_methods.getTransaction(m_schema_name,"Y",""));
				       	more = rs.next();
								while(more){
				           out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
									 more = rs.next();	
								}	
				out.println("</SELECT>");
				
				out.println("</td>");
				out.println("</tr>");


				out.println("<tr class=tr_input>");
				out.println("<td ><div id=trnsub class=div_input>Transaction Sub Type *</div></td>");
				out.println("<td>");
				out.println("<select name=\"TRANSACTION_SUB\" class=\"txt_input\" >");
				rs = stmt.executeQuery(CO_methods.getTrnSubType(m_schema_name,"Y","",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT></td>");
				out.println("</tr>");

				out.println("<tr class=tr_input>");
				out.println("<td >Item Category</td>");
				out.println("<td>");
				out.println("<select name=\"ITEM_CAT\" class=\"txt_input\" onChange=\"chk_item()\">");//onChange=\"chk_sub_item()\"
				rs = stmt.executeQuery(CO_methods.getItemCat(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Item Sub Category </td>");
				out.println("<td>");
				out.println("<select name=\"ITEM_SUB_CAT\" class=\"txt_input\" onChange=\"get_sub_item_vat()\">");
				rs = stmt.executeQuery(CO_methods.getItemSubCat(m_schema_name,"Y","",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td >Make of Asset </td>");
				out.println("<td>");
				out.println("<select name=\"ASSET_MAKE\" class=\"txt_input\" onChange=\"\">");
				rs = stmt.executeQuery(CO_methods.getMake(m_schema_name,"Y","",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				/*out.println("<tr class=tr_input>");
				out.println("<td >Model of Asset</td>");
				out.println("<td><input name=\"MODEL\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=chk_model()><INPUT TYPE=HIDDEN NAME=MODEL_CODE>");
				out.println("<input type=button name=mode_help value=Help class=\"but_input\" onclick=\"model_help('1','10','0','ModelSql','2')\"></td>");
				out.println("</tr>");
				*/
				out.println("<tr class=tr_input>");
				out.println("<td >Sub Model of Asset</td>");
				out.println("<td><input name=\"SUB_MODEL\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=chk_sub_model()><INPUT TYPE=HIDDEN NAME=MODEL_CODE>");
				out.println("<input type=button name=mode_help value=Help class=\"but_input\" onclick=\"model_help('1','10','3','SubModelSql','2')\"></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Engine Capacity </td>");
				out.println("<td>");
				out.println("<select name=\"ENGIN_CAPACITY\" class=\"txt_input\" onChange=\"\">");
				rs = stmt.executeQuery(CO_methods.getEngineCapacity(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Fuel Type</td>");
				out.println("<td>");
				 out.println("<select name=\"FUEL_TYPE\" class=\"txt_input\" onChange=\"loadcharges();\">");
				rs = stmt.executeQuery(CO_methods.getFuelType(m_schema_name,"Y","",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width=25% id=ra>Interest Rate *</td>");
				out.println("<td >");
				out.println("<input name=\"RATE\" type=\"text\" maxlength=\6\" class=\"txt_input\" value=18 onchange=cal_count()>");//format_num(document.Form1.RATE,0);
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=vat>VAT % On Purchase *</td>");//Percentage
				out.println("<td>");
				out.println("<input name=\"VAT_PERCENTAGE\" type=\"text\" maxlength=\"5\" class=\"txt_input\" onchange=format_num(document.Form1.VAT_PERCENTAGE,0);getVatNetGro('GRO');cal_count() value=20>");
				out.println("<!--input type=button name=vat_help value=Help class=\"but_input\" onclick=\"model_help('1','10','3','SubModelSql','2')\"--></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=vatapp>VAT % Applicable for Financing *</td>");
				out.println("<td>");
				out.println("<input name=\"VAT_PER_APP\" type=\"text\" maxlength=\"5\" class=\"txt_input\" onchange=format_num(document.Form1.VAT_PER_APP,0);getVatNetGro('GRO');cal_count() value=15>");
				out.println("<!--input type=button name=vat_help value=Help class=\"but_input\" onclick=\"model_help('1','10','3','SubModelSql','2')\"--></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=vatrent>VAT % on Rental *</td>");
				out.println("<td>");
				out.println("<input name=\"VAT_PER_RENT\" type=\"text\" maxlength=\"5\" class=\"txt_input\" onchange=format_num(document.Form1.VAT_PER_RENT,0);cal_count() value=15>");
				out.println("<!--input type=button name=vat_help value=Help class=\"but_input\" onclick=\"model_help('1','10','3','SubModelSql','2')\"--></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><DIV id=peri  class=div_input>Period of Financing *</div></td>");
				out.println("<td>");
				out.println("<input name=\"PERIOD\" type=\"text\" maxlength=\"4\" class=\"txt_input\" value=48 onchange=format_num(document.Form1.PERIOD,0);cal_count()>");
				out.println("<select name=\"REPAYMENT_TYPE\" class=\"txt_input\" onChange=\"change_repay('TYPE')\" style=\"width:70px;\">");
				rs = stmt.executeQuery(CO_methods.getRepaymentInt(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(3)+"</option>");
					 more = rs.next();	
				}
				out.println("</SELECT>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=GAM>Amount Gross *</td>");
				out.println("<td>");
				if(amount==null){
		      out.println("<input name=\"GROSS_AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=format_num(document.Form1.GROSS_AMOUNT,0);getVatNetGro('GRO');cal_count() STYLE=\"{text-align:right;}\">");
				}else{
				  out.println("<input name=\"GROSS_AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=format_num(document.Form1.GROSS_AMOUNT,0);getVatNetGro('GRO');cal_count() STYLE=\"{text-align:right;}\" value=\""+amount+"\">");
				}
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=VAM>Amount VAT *</td>");
				out.println("<td>");
				out.println("<input name=\"VAT_AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" STYLE=\"{text-align:right;}\" onchange=format_num(document.Form1.VAT_AMOUNT,0);cal_count()>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=NAM>Amount Net *</td>");
				out.println("<td>");
		    out.println("<input name=\"NET_AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" STYLE=\"{text-align:right;}\" onchange=format_num(document.Form1.NET_AMOUNT,0);getVatNetGro('NET');cal_count()>");
			
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Amount Financed </b></td>");
				out.println("<td>");
		    out.println("<input name=\"FIN_AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" STYLE=\"{text-align:right;}\" DISABLED>");
			
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=NIB>NIBSM *</td>");//Non Interest Bearing Security Margin
				out.println("<td>");
				out.println("<input name=\"NIBSM\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 STYLE=\"{text-align:right;}\" onchange=format_num(document.Form1.NIBSM,0);chan_residual();cal_count()>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=ADV>AMI *</td>");//Advance Monthly Installment
				out.println("<td>");
				out.println("<input name=\"AMI\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 STYLE=\"{text-align:right;}\" onchange=format_num(document.Form1.AMI,0);cal_count()>");
				out.println("</td>");
				out.println("</tr>");
				/*
				out.println("<tr class=tr_input>");
				out.println("<td >Last Rental *</td>");
				out.println("<td>");
				out.println("<input name=\"LAST_RENTAL\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("</tr>");
        */ 
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=RES>Residual Value *</td>");
				out.println("<td>");
				out.println("<input name=\"RESIDUAL_VALUE\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 STYLE=\"{text-align:right;}\" onchange=format_num(document.Form1.RESIDUAL_VALUE,0);cal_count()>");
				out.println("</td>");
				out.println("</tr>");
				/*
				out.println("<tr class=tr_input>");
				out.println("<td >Cash Outflow Pattern *</td>");
				out.println("<td>");
				out.println("<input name=\"CASH_OUTFLOW\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 onchange=cal_count()>");
				out.println("</td>");
				out.println("</tr>");
				*/
				out.println("<tr class=tr_input>");
				out.println("<td >Supplier Credit  </td>");
				out.println("<td>");
				out.println("<input name=\"SUPPLIER_CREDIT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 onchange=format_num(document.Form1.SUPPLIER_CREDIT,0);cal_count()>");
				out.println("</td>");
				out.println("</tr>");
				
        out.println("<tr class=tr_input>");
				out.println("<td >Rental Amount</td>");
				out.println("<td>");
				out.println("<input name=\"RENTAL_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" value=0 onchange=format_num(document.Form1.RENTAL_AMOUNT,0);cal_count() STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("</tr>");
				 
				out.println("<tr class=tr_input>");
				out.println("<td >First Rental Amount</td>");
				out.println("<td>");
				out.println("<input name=\"RENTAL1_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" value=0 onchange=format_num(document.Form1.RENTAL1_AMOUNT,0);cal_count() STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("</tr>");
					
					
				out.println("<tr class=tr_input>");
				out.println("<td ><div id=inttype class=div_input>Interest Type *</div></td>");
				out.println("<td>");
				out.println("<select name=\"INTEREST_TYPE\" class=\"txt_input\" onchange=load_fild() >");
				rs = stmt.executeQuery(CO_methods.getInterestType(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				
				out.println("</tr>");
				/*
				out.println("<tr class=tr_input>");
				out.println("<td >Variable Interest Base and Margin</td>");
				out.println("<td>");
				out.println("<input name=\"INTEREST_BASE_MARGIN\" type=\"text\" maxlength=\"50\" class=\"txt_input\" ></td>");
				out.println("</tr>");
				*/
				out.println("</table>");//sub table start(data entry)
				/*
        out.println("<tr class=tr_input>");
				out.println("<td id=int_base></td>");
				out.println("<td id=int_base_txt></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td id=int_mar></td>");
				out.println("<td id=int_mar_txt></td>");
				out.println("</tr>");
				*/
				out.println("<div id=int_type></div>");//sub table start(data entry)
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//sub table start(data entry)
				
				out.println("<tr class=tr_input>");
				out.println("<td >Repayment Mode</td>");
				out.println("<td>");
				out.println("<select name=\"PAYMENT_MODE\" class=\"txt_input\" onChange=\"\">");
		    rs = stmt.executeQuery(CO_methods.getRepaymentMeth(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				
				/*
				out.println("<tr class=tr_input>");
				out.println("<td >Cash Inflow Pattern *</td>");
				out.println("<td>");
				out.println("<input name=\"CASH_INFLOW\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=load_flow() >");
				out.println("</td>");
				out.println("</tr>");
				*/
				
				out.println("<tr class=tr_input>");
				out.println("<td><div id=inqno class=div_input>Inquiry No </div></td>");
				if(m_inq_no=="" || m_inq_no==null){ 
				  out.println("<td><input name=\"INQ_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=chk_inqno()>");
				}else{
				  out.println("<td><input name=\"INQ_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=chk_inqno() value=\""+m_inq_no+"\">");
				}
				out.println("<input type=button name=inqui_help value=Help class=\"but_input\" onclick=\"inq_help('1','10','13','InquirySql','7')\"></td>");
				
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width=25%><div id=prino class=div_input>Pricing No </div></td>");
				
				if(m_pri_no=="" || m_pri_no==null){ 
				  out.println("<td><input name=\"PRICE_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=chk_priceno()>");
				}else{
				  out.println("<td><input name=\"PRICE_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" value=\""+m_pri_no+"\" onchange=chk_priceno()>");
				}
				
				out.println("<input type=button name=inqu_help value=Help class=\"but_input\" onclick=\"price_help('1','10','23','PriceSql','8')\"></td>");
				
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Repayment Interval *</td>");
				out.println("<td>");
				out.println("<select name=\"REPAYMENT_INTERVAL\" class=\"txt_input\" onChange=\"change_repay('INTERVAL')\">");
				rs = stmt.executeQuery(CO_methods.getRepaymentInt(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
								
        out.println("<tr class=tr_input>");
				out.println("<td ><div id=conass class=div_input>Condition of Asset *</div></td>");
				out.println("<td >");
				out.println("<select name=\"ASSET_CONDITION\" class=\"txt_input\" onChange=\"\">");
				rs = stmt.executeQuery(CO_methods.getConditionOfAsset(m_schema_name,"Y","",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td >Buy Back </td>");
				out.println("<td>");
				out.println("<select name=\"BUY_BACK\" class=\"txt_input\" onChange=\"\">");
		    out.println("<OPTION value=\"YES\">Yes</option>");
			  out.println("<OPTION value=\"NO\" SELECTED>No </option>");
			  out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<input name=\"VENDOR\" type=\"hidden\" maxlength=\"20\" class=\"txt_input\"  ></td>");
				out.println("</tr>");
				
				/*out.println("<tr class=tr_input>");
				out.println("<td >Vendor</td>");
				out.println("<td>");
				out.println("<input name=\"VENDOR\" type=\"text\" maxlength=\"20\" class=\"txt_input\"  onchange=chk_vendor()><INPUT TYPE=HIDDEN NAME=VENDOR_CODE>");
				out.println("<input type=button name=ven_help value=Help class=\"but_input\" onclick=\"vendor_help('1','10','0','VendorSql','4')\"></td>");
				out.println("</td>");
				out.println("</tr>");*/
				
				out.println("<tr class=tr_input>");
				out.println("<td >Asset Usage Type</td>");
				out.println("<td>");
				out.println("<select name=\"ASSET_USAGE\" class=\"txt_input\" onChange=\"\">");
				//out.println("----"+CO_methods.getAssetUsageType(m_schema_name,"Y",""));
				rs = stmt.executeQuery(CO_methods.getAssetUsageType(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td></td>");
				out.println("</tr>");
				
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>Standard Charges and Costs</td>");
				out.println("<td></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td colspan=2><div id=schar><table>");
				
				
				int i = 0;
				rs = stmt.executeQuery(CO_methods.getChargesApp(m_schema_name,"Y","","N//A","N//A"));
				more = rs.next();
				if(more){
				
				
				while(more){
				  out.println("<tr class=tr_input>");
					out.println("<td width=65% >"+rs.getString(1)+"<input type=hidden name=\"CHARGE_NAME_"+i+"\" value="+rs.getString(2)+"></td>");
					out.println("<td><input type=hidden name=\"CHARGE_PER_"+i+"\" value="+rs.getString(4)+">");
					out.println("<select name=\"CHARGE_TYPE_"+i+"\" class=\"txt_input1\" >");
				  out.println("<OPTION value=\"AMO\">Amortised</option>");
					out.println("<OPTION value=\"INV\">Upfront</option>");
					out.println("</SELECT>");
					//out.println("");
					out.println("<input name=\"CHARGE_"+i+"\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\""+rs.getString(3)+"\" STYLE=\"{text-align:right;}\" >");
					out.println("</td>");
					out.println("</tr>");
					i = i+1;
				  more = rs.next();	
				}
				out.println("<input type=hidden name=\"c_c_count\" value=\""+i+"\"></table></div></td>");
				out.println("</tr>");
				
				  //out.println("<script>");
					//out.println("document.Form1.c_c_count.value=\""+i+"\";");
					//out.println("</script>");
					
				}else{
				out.println("<input type=hidden name=\"c_c_count\" value=\"0\"></table></div></td>");
				out.println("</tr>");
				
				}
				/*
				out.println("<tr class=tr_input>");
				out.println("<td >Revenue License</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Taxes</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Insurance</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Document</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td></td>");
				out.println("</tr>");
				 */
				
				
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=hidden name=m_count value=0></td>");
				out.println("</tr>");
				 
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>Maintenance</td>");
				out.println("<td></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Applicability</td>");
				out.println("<td>");
				out.println("<select name=\"MAINTENANCE_APP\" class=\"txt_input\" onChange=\"befor_main()\">");
		    out.println("<OPTION value=\"N\" SELECTED>No </option>");
			  out.println("<OPTION value=\"Y\">Yes</option>");
			  out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=2 id=main_id>");
				out.println("<input type=hidden name=hid_m_count value=0>");
				out.println("<input type=hidden name=hid_y_count value=0>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=2 id=outflow_id>");
				out.println("</td>");
				out.println("</tr>");
				/*
				i = 0;
				rs = stmt.executeQuery(CO_methods.getChargesApp(m_schema_name,"Y","","",""));
				more = rs.next();
				if(more){
				
			   while(more){
				  out.println("<tr class=tr_input>");
					out.println("<td >"+rs.getString(1)+"<input type=hidden name=\"MAINTE_NAME_"+i+"\" value="+rs.getString(1)+"></td>");
					out.println("<td>");
					out.println("<input name=\"MAINTANENCE_"+i+"\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=\"0\">");
					out.println("</td>");
					out.println("</tr>");
					i = i+1;
				  more = rs.next();	
				}
				  out.println("<script>");
					out.println("document.Form1.c_c_count.value=\""+i+"\";");
					out.println("</script>");
					
				}
				*/
				/*
				out.println("<tr class=tr_input>");
				out.println("<td >Garage</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Mileage</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Document</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");*/
				out.println("</table>");//sub table close(data entry)
				out.println("</td>");
				
				out.println("<td valign=top><div id=price_cal></div>");
				out.println("<INPUT TYPE=HIDDEN NAME=hid_count VALUE=0></td>");
				out.println("</tr>");
				
				
				out.println("</table>");//main table
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr class=tr_input>");
				/*
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Back\" class=mainbut onclick=befor_back();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=delete value=\"Delete\" class=mainbut onclick=befor_deactive();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit value=\"Edit\" class=mainbut onclick=befor_modify();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=cancel value=\"Cancel\" class=mainbut onclick=befor_active();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Reset\" class=mainbut onclick=befor_reset();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Submit\" class=mainbut onclick=befor_submit();></td>");
        */
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=new1      value=\"New\"       class=mainbut onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit1     value=\"Edit\"      class=mainbut onclick=befor_modify();   onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete   value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel   value=\"Re-active\" class=mainbut onclick=befor_active();   onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_submit1 value=\"Save\"      class=mainbut onclick=befor_submit();   onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=delete_1 value=\"Help\" class=mainbut onclick=HelpBox_msg(); onMouseOver='load_roll_value(\"Help\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back1     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1   value=\"Cancel\"     class=mainbut onclick=befor_reset();    onMouseOver='load_roll_value(\"Cancel\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td id=cal_but><input type=button name=cal1     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        //out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=recal     value=\"Re-Cal\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");     onMouseOver='load_roll_value(\"Re-Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td ><input type=button name=val1     value=\"Valuation\" class=mainbut onclick=view_report(\"NO\");     onMouseOver='load_roll_value(\"Valuation\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        //out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=recal     value=\"Re-Cal\" class=mainbut onclick=befor_cal(\"YES\");     onMouseOver='load_roll_value(\"Re-Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
				out.println("</tr></table>");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");
				
				/*out.println("<!--tr>");
				out.println("<td class="pdn_txtpos1 & txt-bodyRed" height="15">");
				out.println("<div id="ValidationSummary1" style="color:Red;display:none;">");
				
				out.println("</div>");
				out.println(" <span id="lblError" style="color:Red;"></span></td>");
				out.println("</tr-->");
				*/
				
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
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/valuation.js\"></SCRIPT> ");
				out.println("</html>");
			
			
			}
			else if(m_chksql.trim().equals("get_advance_price_cal")){
			
			    String m_rate		    = req.getParameter("rate");
          String m_value      = req.getParameter("value");
          String m_terms	    = req.getParameter("terms");
          String m_freq		    = req.getParameter("freq");
          String m_type       = req.getParameter("type");
					String m_vat_per    = req.getParameter("tax");
          String m_install    = req.getParameter("installments");
					String m_factor     = req.getParameter("factor");
					String m_pracent    = req.getParameter("pracent");
					String m_percentage = req.getParameter("percentage");
					String m_ami        = req.getParameter("ami");
					String m_vat_app    = req.getParameter("tax_app");
					String m_tax_rent   = req.getParameter("tax_rent");
          String m_sup_cr_per = req.getParameter("supcrper");
					String m_nibsm      = req.getParameter("nibsm");
          String m_residual   = req.getParameter("residual");
					String m_sup_credit = req.getParameter("sup_cr");
          String m_other_cha  = req.getParameter("other_cha");
					String m_maintenance= req.getParameter("maintan");
        	String m_int_base_m = req.getParameter("nitbasemar");
          //String m_last_rent  = req.getParameter("last_rent");
          String m_cash_out   = req.getParameter("cashout");
          String m_trn_sub    = req.getParameter("trnsub");
          String m_int_type   = req.getParameter("nittype");
          String m_trn_type   = req.getParameter("trn_type");
          String m_option     = req.getParameter("option");
          String m_nitmar     = req.getParameter("nitmar");
					String m_vat_val    = req.getParameter("vat_val");
					String m_gro_val    = req.getParameter("gro_val");
					String m_rental     = req.getParameter("rental");
					String m_1rental    = req.getParameter("1rental");
					
					
								
					//out.println("m_percentage="+m_percentage);											
          //out.println("m_install="+m_install);											
          callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_TEMP_PRICE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30);END;");
				  callstmt1.setString(1 ,m_terms);
          //out.println("m_terms="+m_terms);											
					callstmt1.setString(2 ,m_freq);
          //out.println("m_freq="+m_freq);											
					callstmt1.setString(3 ,m_install);
					//out.println("m_install="+m_install);											
					callstmt1.setString(4 ,m_pracent);
					//out.println("m_pracent="+m_pracent);											
					callstmt1.setString(5 ,m_factor);
          //out.println("m_factor="+m_factor);											
					callstmt1.setString(6 ,m_type);
 				  //out.println("m_type="+m_type);											
					callstmt1.setString(7 ,m_username);
 				  //out.println("m_username="+m_username);											
					callstmt1.setString(8 ,m_rate);
 				  //out.println("m_rate="+m_rate);											
					callstmt1.setString(9 ,m_value);
 				  //out.println("m_value="+m_value);											
					callstmt1.setString(10,m_vat_per);
					//out.println("m_vat_per="+m_vat_per);											
					callstmt1.setString(11,m_percentage);	
 				  //out.println("m_percentage="+m_percentage);											
					callstmt1.setString(12,m_vat_app);	
					//out.println("m_vat_app="+m_vat_app);											
					callstmt1.setString(13,m_ami);	
					//out.println("m_ami="+m_ami);											
					callstmt1.setString(14,m_sup_credit);	
					//out.println("m_sup_credit="+m_sup_credit);											
					callstmt1.setString(15,m_sup_cr_per);	
					//out.println("m_sup_cr_per="+m_sup_cr_per);											
					callstmt1.setString(16,m_nibsm);	
					//out.println("m_nibsm="+m_nibsm);											
					callstmt1.setString(17,m_residual);	
					//out.println("m_residual="+m_residual);											
					callstmt1.setString(18,m_other_cha);	
					//out.println("m_other_cha="+m_other_cha);											
					callstmt1.setString(19,m_maintenance);	
					//out.println("m_maintenance="+m_maintenance);											
					callstmt1.setString(20,m_option);	
					//out.println("m_option="+m_option);											
					callstmt1.setString(21,m_trn_type);
					//out.println("m_trn_type="+m_trn_type);											
					callstmt1.setString(22,m_trn_sub);
					//out.println("m_trn_sub="+m_trn_sub);											
					callstmt1.setString(23,m_int_type);
					//out.println("m_int_type="+m_int_type);											
					callstmt1.setString(24,m_nitmar);
					//out.println("m_nitmar="+m_nitmar);											
					callstmt1.setString(25,m_cash_out);
					//out.println("m_cash_out="+m_cash_out);											
					callstmt1.setString(26,m_vat_val);
					//out.println("m_vat_val="+m_vat_val);											
					callstmt1.setString(27,m_gro_val);
					//out.println("m_gro_val="+m_gro_val);											
					callstmt1.setString(28,m_rental);
					//out.println("m_rental="+m_rental);											
					callstmt1.setString(29,m_1rental);
					//out.println("m_1rental="+m_1rental);											
					callstmt1.setString(30,m_tax_rent);
						
 				  //out.println("t5");
			    callstmt1.execute();
					//out.println("t6");
			    
			    /*     
          rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
					                        "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
																	"       "+m_vat_per+",/*"+m_freq+"*****"+m_terms+",'"+m_ami+"' "+
																	" FROM DUAL");
																	
          /*
					out.println("<html><head>");
          out.println("<title>PMT Value - Formulation</title></head>");
          out.println("<body bgcolor='white'>");
          out.println("<form name='Form1'>");
          out.println("<br>");
          */
					
          double mm_tot_fact 				= 0;
					double mm_tot_term 				= 0;
					out.println("<table class=table border='1' width='100%' >");
          out.println("<tr class=tr_input><td  width='100%' >");
          //out.println("<div id=plsw> </div>");
					
					/*
          if(rs.next()){
              
              mm_tot_fact				= rs.getDouble(7);
						  mm_tot_term				= rs.getInt(5);
              
					}	*/	
					/*		        
              out.println("<table class=table border='1' width='100%' >");
              out.println("<tr class=tr_input>");
							out.println("<td  width='20%' >Total Factor</td>");
              out.println("<td width='30%' >"+nf.format(rs.getDouble(7))+"</td>");
							out.println("<td  width='20%' >Total Gross Rental</td>");
              out.println("<td width='30%' ID=GRENT></td>");
							out.println("</tr>");
              out.println("<tr class=tr_input>");
							out.println("<td  width='20%' >Total Present Value</td>");
              out.println("<td width='30%' ID=PVAL></td>");
							out.println("<td  width='20%' >Avg. Gross Rental</td>");
              out.println("<td width='30%' ID=A_GRENT></td>");
							out.println("</tr>");
							out.println("</table>");
							
          }
          
          out.println("<br>");		
					*/
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          out.println("<td colspan=6 align=right><input type=button name=cash_f  value=\"Cash Flow\" class=mainbut onclick=load_flow();             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("<td colspan=2 id=plsw> </td>");
          //out.println("<td colspan=2 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=tr_input><td  width='8%' >Inst</td>");
          out.println("<td  width='8%' > </td>");
          //out.println("<td  width='20%' >Rate per Month(RM)</td>");
          //out.println("<td  width='30%' >(RM+1)=X</td>");
          out.println("<td  width='10%' align=right>Factor</td>");
					out.println("<td  width='10%' align=right>Rental Factor</td>");
					out.println("<td  width='20%' align=right>Net Rental</td>");
					out.println("<td  width='20%' align=right>VAT</td>");
					out.println("<td  width='20%' align=right>Gross Rental</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					//out.println("<td  width='20%' >AMI</td>");
					
					
          out.println("</tr>");
					out.println("<tr class=tr_input><td  width='8%' ></td><td  width='8%' ></td>");
          out.println("<td id=m_fact align=right></td>");
          out.println("<td ></td>");
          out.println("<td id=n_re align=right></td>");
          out.println("<td id=v_re align=right></td>");
          out.println("<td id=g_re align=right></td>");
          out.println("<td id=p_va align=right></td>");
          //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
          out.println("</tr>");
          
          double sum_rate=0;
          double sum_rent=0;
					double sum_p_re=0;
					double sum_g_re=0;
					double sum_v_re=0;
					int m_start=0;
					/*int m_end  =mm_term;
					
					if(m_type.equals("ARREASE")){
					  m_start=1;
						m_end  =mm_term+1;
					}
					*/
					int j=0;
          //for(j=m_start;j<m_end;j++){
              //out.println("J="+j);
             //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
 
							rs = stmt.executeQuery (" SELECT A.INSTALLMENT, A.FACTOR, A.NEW_NET, A.NEW_GROSS,A.NEW_PRACENT,  "+
																			"        A.PERCENTAGE, A.AMI,AMOUNT,PERIOD,INT_RATE,TERM, "+
																			"        A.NEW_GROSS-A.NEW_NET,INTEREST_AMOUNT,CAPITAL_AMOUNT "+
																			"  FROM  "+m_schema_name+".AF_MK_TBD_PRICE_CAL A "+
													 						"	WHERE  ENT_USER  = '"+m_username+"'"+
																			" ORDER BY INSTALLMENT");
							
							boolean more = rs.next();
							if(more){
 							   m_value      = rs.getString(8);
			           m_freq		    = rs.getString(9);
							   m_rate		    = rs.getString(10);
			           m_terms	    = rs.getString(11);
			           while(more){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input><td>"+(j+1)+"</td>");
                  out.println("<td>"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\"  onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(12))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.GROSS"+j+",'4');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled> ");
									out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(14))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(13))+"\"></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
									sum_g_re=sum_g_re+(rs.getDouble(4));
									sum_v_re=sum_v_re+(rs.getDouble(12));
									j=j+1;
									more=rs.next();
									if(more){
									out.println("<tr class=tr_input1><td>"+(j+1)+"</td>");
                  out.println("<td>"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(12))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.GROSS"+j+",'4');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
									out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(14))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(13))+"\"></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
									sum_g_re=sum_g_re+(rs.getDouble(4));
									sum_v_re=sum_v_re+(rs.getDouble(12));
									j=j+1;
									more=rs.next();
									}
                }
						  }		
          //}
          out.println("<tr class=tr_input><td  ></td><td  ></td>");
          //out.println("<td  width='20%' ></td>");
          out.println("<td  align=right><input type=hidden name=hid_cou value="+j+">"+nf1.format(sum_rate)+"<input type=hidden name=sun_fact value="+nf1.format(sum_rate)+"></td>");
          out.println("<td><input type=hidden name=hid_am value="+m_value+"><input type=hidden name=hid_ra value="+m_rate+"><input type=hidden name=hid_pe value="+m_terms+"></td>");
          out.println("<td align=right>"+nf.format(sum_rent)+"<input type=hidden name=sun_rent value="+nf.format(sum_rent)+"></td>");
          out.println("<td align=right>"+nf.format(sum_v_re)+"<input type=hidden name=sun_v_nt value="+nf.format(sum_v_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_g_re)+"<input type=hidden name=sun_g_nt value="+nf.format(sum_g_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_p_re)+"<input type=hidden name=sun_p_nt value="+nf.format(sum_p_re)+"></td>");
          //out.println("<td></td>");
					
					out.println("</tr>");					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><!--input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'--></td>");
 
          out.println("</tr></table>");
          
      
			}
			
			
			
				else if(m_chksql.trim().equals("loadPrice")){
			
			    String m_rate		    = req.getParameter("rate");
          String m_value      = req.getParameter("value");
          String m_terms	    = req.getParameter("terms");
          String m_freq		    = req.getParameter("freq");
          String m_type       = req.getParameter("type");
					String m_vat_per    = req.getParameter("tax");
          String m_install    = req.getParameter("installments");
					String m_factor     = req.getParameter("factor");
					String m_pracent    = req.getParameter("pracent");
					String m_percentage = req.getParameter("percentage");
					String m_ami        = req.getParameter("ami");
					String m_vat_app    = req.getParameter("tax_app");
					String m_tax_rent   = req.getParameter("tax_rent");
					String m_sup_cr_per = req.getParameter("supcrper");
					String m_nibsm      = req.getParameter("nibsm");
          String m_residual   = req.getParameter("residual");
					String m_sup_credit = req.getParameter("sup_cr");
          String m_other_cha  = req.getParameter("other_cha");
					String m_maintenance= req.getParameter("maintan");
        	String m_int_base_m = req.getParameter("nitbasemar");
          //String m_last_rent  = req.getParameter("last_rent");
          String m_cash_out   = req.getParameter("cashout");
          String m_trn_sub    = req.getParameter("trnsub");
          String m_int_type   = req.getParameter("nittype");
          String m_trn_type   = req.getParameter("trn_type");
          String m_option     = req.getParameter("option");
          String m_price_no   = req.getParameter("price_no");
          			
					  
			    /*     
          rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
					                        "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
																	"       "+m_vat_per+",/*"+m_freq+"*****"+m_terms+",'"+m_ami+"' "+
																	" FROM DUAL");
																	
          /*
					out.println("<html><head>");
          out.println("<title>PMT Value - Formulation</title></head>");
          out.println("<body bgcolor='white'>");
          out.println("<form name='Form1'>");
          out.println("<br>");
          */
					
          double mm_tot_fact 				= 0;
					double mm_tot_term 				= 0;
					out.println("<table class=table border='1' width='100%' >");
          out.println("<tr class=tr_input><td  width='100%' >");
          /*
          if(rs.next()){
              
              mm_tot_fact				= rs.getDouble(7);
						  mm_tot_term				= rs.getInt(5);
              
					}	*/	
					/*		        
              out.println("<table class=table border='1' width='100%' >");
              out.println("<tr class=tr_input>");
							out.println("<td  width='20%' >Total Factor</td>");
              out.println("<td width='30%' >"+nf.format(rs.getDouble(7))+"</td>");
							out.println("<td  width='20%' >Total Gross Rental</td>");
              out.println("<td width='30%' ID=GRENT></td>");
							out.println("</tr>");
              out.println("<tr class=tr_input>");
							out.println("<td  width='20%' >Total Present Value</td>");
              out.println("<td width='30%' ID=PVAL></td>");
							out.println("<td  width='20%' >Avg. Gross Rental</td>");
              out.println("<td width='30%' ID=A_GRENT></td>");
							out.println("</tr>");
							out.println("</table>");
							
          }
          
          out.println("<br>");		
					*/
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow\" class=mainbut onclick=load_flow();             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=2 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=tr_input><td  width='8%' >Inst</td>");
          out.println("<td  width='8%' > </td>");
          //out.println("<td  width='20%' >Rate per Month(RM)</td>");
          //out.println("<td  width='30%' >(RM+1)=X</td>");
          out.println("<td  width='10%' align=right>Factor</td>");
					out.println("<td  width='10%' align=right>Rental Factor</td>");
					out.println("<td  width='20%' align=right>Net Rental</td>");
					out.println("<td  width='20%' align=right>VAT</td>");
					out.println("<td  width='20%' align=right>Gross Rental</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					//out.println("<td  width='20%' >AMI</td>");
					
					
          out.println("</tr>");
					out.println("<tr class=tr_input><td  width='8%' ></td><td  ></td>");
          out.println("<td id=m_fact align=right></td>");
          out.println("<td ></td>");
          out.println("<td id=n_re align=right></td>");
          out.println("<td id=v_re align=right></td>");
          out.println("<td id=g_re align=right></td>");
          out.println("<td id=p_va align=right></td>");
          //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
          out.println("</tr>");
          
          double sum_rate=0;
          double sum_rent=0;
					double sum_p_re=0;
					double sum_g_re=0;
					double sum_v_re=0;
					int m_start=0;
					/*int m_end  =mm_term;
					
					if(m_type.equals("ARREASE")){
					  m_start=1;
						m_end  =mm_term+1;
					}
					*/
					int j=0;
          //for(j=m_start;j<m_end;j++){
              //out.println("J="+j);
             //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
 
							rs = stmt.executeQuery (" SELECT A.INSTALLMENT_NO, "+
							                        "        PERCENTAGE / POWER((('"+m_rate+"' / 100) / '"+m_freq+"' +1), INSTALLMENT_NO), "+
																			"        NET_RENTAL_AMOUNT,GRENTAL_AMOUNT,A.PRACENT_AMOUNT,  "+
																			"        PERCENTAGE, '"+m_ami+"','"+m_value+"','"+m_freq+"',"+
																			"        '"+m_rate+"','"+m_terms+"',GRENTAL_AMOUNT-NET_RENTAL_AMOUNT, "+
																			"        INTEREST_AMOUNT,CAPITAL_AMOUNT "+
																			"  FROM  "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT A "+
													 						"	WHERE  PRICING_NO  = '"+m_price_no+"'"+
																			" ORDER BY TO_NUMBER(INSTALLMENT_NO)");
							
							
							
							boolean more = rs.next();
							if(more){
 							   m_value      = rs.getString(8);
			           m_freq		    = rs.getString(9);
							   m_rate		    = rs.getString(10);
			           m_terms	    = rs.getString(11);
			           
                 while(more){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input><td>"+(j+1)+"</td>");
                  out.println("<td>"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(12))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\"  onchange=format_num(document.Form1.GROSS"+j+",'4');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
									out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(14))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(13))+"\"></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
									sum_g_re=sum_g_re+(rs.getDouble(4));
									sum_v_re=sum_v_re+(rs.getDouble(12));
									j=j+1;
									more=rs.next();
									if(more){
								  out.println("<tr class=tr_input1><td>"+(j+1)+"</td>");
                  out.println("<td>"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(12))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\"  onchange=format_num(document.Form1.GROSS"+j+",'4');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
									out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(14))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(13))+"\"></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
									sum_g_re=sum_g_re+(rs.getDouble(4));
									sum_v_re=sum_v_re+(rs.getDouble(12));
									j=j+1;
									more=rs.next();
									}
                }
						  }		
          //}
          out.println("<tr class=tr_input><td  ></td><td  ></td>");
          //out.println("<td  width='20%' ></td>");
          out.println("<td  align=right><input type=hidden name=hid_cou value="+j+">"+nf1.format(sum_rate)+"<input type=hidden name=sun_fact value="+nf1.format(sum_rate)+"></td>");
          out.println("<td><input type=hidden name=hid_am value="+m_value+"><input type=hidden name=hid_ra value="+m_rate+"><input type=hidden name=hid_pe value="+m_terms+"></td>");
          out.println("<td align=right>"+nf.format(sum_rent)+"<input type=hidden name=sun_rent value="+nf.format(sum_rent)+"></td>");
          out.println("<td align=right>"+nf.format(sum_v_re)+"<input type=hidden name=sun_v_nt value="+nf.format(sum_v_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_g_re)+"<input type=hidden name=sun_g_nt value="+nf.format(sum_g_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_p_re)+"<input type=hidden name=sun_p_nt value="+nf.format(sum_p_re)+"></td>");
          //out.println("<td></td>");
					
					out.println("</tr>");					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("</tr></table>");
          
      
			}
			
			else if(m_chksql.trim().equals("chkCity")){
			 rs = stmt.executeQuery("SELECT A.CITY_CODE, A.CITY_DESC, B.DESCRIPTION "+
															 "FROM   "+m_schema_name+".AF_CO_MAS_CITY A,"+
															 "       "+m_schema_name+".AF_CO_MAS_POSTAL_CODES B"+
															 "WHERE  A.CITY_CODE = B.CITY_CODE AND A.ACTIVE_STATUS='Y' AND "+
                               "       B.ACTIVE_STATUS='Y'");
				boolean more = rs.next();
				if(more){
				   out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#"+rs.getString(3)+"@##@");
				}	    
						
			}
			
			else if(m_chksql.trim().equals("chkSubType")){
			
			 String type = req.getParameter("type");
				//out.println(CO_methods.getTrnSubType(m_schema_name,"Y",type,""));
			 rs = stmt.executeQuery(CO_methods.getTrnSubType(m_schema_name,"Y",type,""));
				boolean more = rs.next();
				while(more){
				   out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#~#@");
					  more = rs.next();	
				}	    
						
			}
			else if(m_chksql.trim().equals("loadMaintenance")){
			
			  String terms = req.getParameter("terms");
				String freq  = req.getParameter("freq");
				String make  = req.getParameter("make");
				String model  = req.getParameter("model");
				String sub_type  = req.getParameter("sub_type");
				
				
				/*
				out.println(" SELECT DISTINCT B.DESCRIPTION, A.charge_sub_code	,NVL(A.AMOUNT,'0'),NVL(A.INCREASE_DECREASE*100,'0'),   "+
		       "        D.ITEM_SUB_CAT,D.ITEM_SUB_CAT,A.MILEAGE_CODE,A.ACTIVE_STATUS "+//A.MAKE_CODE,A.SUB_MODEL_CODE
 					 " FROM  "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B, "+
           "       "+m_schema_name+".AF_CO_MAS_SUB_MODLE C,"+m_schema_name+".AF_CO_MAS_MODEL D "+
					 " WHERE A.CHARGE_SUB_CODE=B.SUB_TYPE_CODE AND D.ITEM_SUB_CAT LIKE '"+make+"%' AND "+
					 "       A.CHARGE_SUB_CODE LIKE '"+sub_type+"%' AND "+//A.SUB_MODEL_CODE LIKE '"+model+"%' AND "+
					 "       TYPE_CODE='MAINTENANC'  AND A.SUB_MODEL_CODE = C.SUB_CODE AND C.MODEL_CODE = D.MODEL_CODE"+
					 " ORDER BY	B.DESCRIPTION DESC");
				*/		
				rs = stmt.executeQuery(" SELECT DISTINCT B.DESCRIPTION, A.charge_sub_code	,NVL(A.AMOUNT,'0'),NVL(A.INCREASE_DECREASE*100,'0'),   "+
		       "        D.ITEM_SUB_CAT,D.ITEM_SUB_CAT,A.MILEAGE_CODE,A.ACTIVE_STATUS "+//A.MAKE_CODE,A.SUB_MODEL_CODE
 					 " FROM  "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B, "+
           "       "+m_schema_name+".AF_CO_MAS_SUB_MODLE C,"+m_schema_name+".AF_CO_MAS_MODEL D "+
					 " WHERE A.CHARGE_SUB_CODE=B.SUB_TYPE_CODE AND D.ITEM_SUB_CAT LIKE '"+make+"%' AND "+
					 "       A.CHARGE_SUB_CODE LIKE '"+sub_type+"%' AND "+//A.SUB_MODEL_CODE LIKE '"+model+"%' AND "+
					 "       TYPE_CODE='MAINTENANC'  AND A.SUB_MODEL_CODE = C.SUB_CODE AND C.MODEL_CODE = D.MODEL_CODE"+
					 " ORDER BY	B.DESCRIPTION DESC");
				 
				double years    = (Double.parseDouble(terms)/Integer.parseInt(freq))-1;
				//out.println(CO_methods.getMaintenancApp(m_schema_name,"Y","","","","MAINTENANC"));
			  //rs = stmt.executeQuery(CO_methods.getMaintenancApp(m_schema_name,"Y",make,model,sub_type,"MAINTENANC"));
				boolean more = rs.next();
				if(more){
				 int t=0;
				 out.println("<table>");
				 out.println("<tr class=tr_input >");
					out.println("<td >Type</td>");
          out.println("<td >Amount</td>");
          out.println("<td >%</td>");
          for(int j=0;j<=years;j++){ 					
						out.println("<td >");
						out.println("Year"+j+"");
						out.println("</td>");
					}	
					out.println("</tr>");	
					
			   while(more){
				  out.println("<tr class=tr_input>");
					out.println("<td >"+rs.getString(1)+"<input type=hidden name=\"MAINTE_NAME_"+t+"\" value="+rs.getString(2)+"></td>");
          out.println("<td >"+nf.format(rs.getDouble(3))+" <input type=hidden name=\"AMOUNT_"+t+"\"      value="+rs.getString(3)+"></td>");
          out.println("<td >"+nf1.format(rs.getDouble(4))+"<input type=hidden name=\"AMOUNT_PER_"+t+"\"  value="+rs.getString(4)+"></td>");
          for(int j=0;j<=years;j++){ 					
						out.println("<td>");
						out.println("<input name=\"MAINTANENCE_"+t+"_"+j+"\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\"0\" onchange=format_num(document.Form1.MAINTANENCE_"+t+"_"+j+",'4');cal_count()>");
						out.println("</td>");
					}	
					out.println("</tr>");
					t = t+1;
				  more = rs.next();	
				}
				out.println("<input type=hidden name=hid_m_count value="+t+">");
				out.println("<input type=hidden name=hid_y_count value="+years+">");
				out.println("<table>");
			 }else{
				out.println("<table>");
				 out.println("<input type=hidden name=hid_m_count value=0>");
				out.println("<input type=hidden name=hid_y_count value=0>");
				out.println("<table>");
			 }	
			}
			
			else if(m_chksql.trim().equals("loadcharges")){
			
			  String SubItemCat  = req.getParameter("item_sub_cat");
				String m_pr        = req.getParameter("price_no");
				//out.println("SubItemCat="+SubItemCat+"---m_pr="+m_pr);
	      if(m_pr!=null && !m_pr.equals("")){ 
				 
				 rs = stmt.executeQuery("SELECT DESCRIPTION,SUB_CHAGE_CODE,ROUND(AMOUNT),PERCENT_AMOUNT "+
					                      "FROM   "+m_schema_name+".AF_MK_PRO_PRICING_CHARGES A,"+
																"       "+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
																"WHERE  PRICING_NO = '"+m_pr+"' AND "+
																"       B.SUB_TYPE_CODE=A.SUB_CHAGE_CODE ");
        }else{
																	
				 rs = stmt.executeQuery( " SELECT B.DESCRIPTION, A.SUB_TYPE_CODE,NVL(ROUND(A.AMOUNT),'0'),NVL(A.PERCENTAGE,'0')/100,   "+
													       "        A.ITEM_SUB_CAT,A.FUAL_TYPE_CODE,A.FROM_DATE,A.TO_DATE,A.ACTIVE_STATUS "+
											 					 " FROM  "+m_schema_name+".AF_CO_MAS_CHARGES_APPLICABLE A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
																 " WHERE A.SUB_TYPE_CODE=B.SUB_TYPE_CODE AND A.ITEM_SUB_CAT LIKE '"+SubItemCat+"' AND "+
																 //"       A.SUB_TYPE_CODE LIKE '"+SubChargType+"%' AND A.SUB_TYPE_CODE LIKE '"+Fual+"%' AND "+
																 " FROM_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') AND "+
																 " TO_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') "+
																 " ORDER BY	A.DEFAULT_VALUE DESC");																	
				}
				
				boolean more = rs.next();
				if(more){
				 int i=0;
				 out.println("<table>");
				 
			  while(more){
				  out.println("<tr class=tr_input>");
					out.println("<td width=65% >"+rs.getString(1)+"<input type=hidden name=\"CHARGE_NAME_"+i+"\" value="+rs.getString(2)+"></td>");
					out.println("<td><input type=hidden name=\"CHARGE_PER_"+i+"\" value="+rs.getString(4)+">");
					out.println("<select name=\"CHARGE_TYPE_"+i+"\" class=\"txt_input1\" >");
				  out.println("<OPTION value=\"AMO\">Amortised</option>");
					out.println("<OPTION value=\"INV\">Upfront</option>");
					out.println("</SELECT>");
					out.println("<input name=\"CHARGE_"+i+"\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\""+nf.format(rs.getDouble(3))+"\" STYLE=\"{text-align:right;}\" onchange=chk_charge('"+i+"')>");
					out.println("</td>");
					out.println("</tr>");
					i = i+1;
				  more = rs.next();	
				}
				
				  out.println("<tr class=tr_input>");
					out.println("<td width=65% >Total</td>");
					out.println("<td>");
					out.println("<input name=\"Total_CHARGE\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\""+nf.format(0)+"\" STYLE=\"{text-align:right;}\" >");
					out.println("</td>");
					out.println("</tr>");
				out.println("<input type=hidden name=\"c_c_count\" value=\""+i+"\"><table>");
			 	
			}else{
			  out.println("<table><tr class=tr_input><td></td><td></td></tr>");
				out.println("<input type=hidden name=\"c_c_count\" value=\"0\"><table>");
			 	 
			}
			}
			else if(m_chksql.trim().equals("chkClient")){
			
			  String clientName = req.getParameter("clientName");
				String addrees1   = req.getParameter("addrees1");
				String city_code 	= req.getParameter("city_code");
				String tel_no 		= req.getParameter("tel_no");
				String email 			= req.getParameter("email");
				String nic_no 		= req.getParameter("nic_no");
				String bc_no		  = req.getParameter("bc_no");
				
			  rs = stmt.executeQuery( " SELECT FULL_NAME, ADDRESS1, CITY_CODE,TEL_NO,EMAIL,NIC_NO "+
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
																" WHERE FULL_NAME = UPPER('"+clientName+"') OR "+
																"       (ADDRESS1 = UPPER('"+addrees1+"') AND "+
																"	      CITY_CODE = UPPER('"+city_code+"')) OR "+
																"       TEL_NO    = UPPER('"+tel_no+"') OR "+
																"	      EMAIL     = UPPER('"+email+"') OR "+
																"       NIC_NO    = UPPER('"+nic_no+"') OR "+
																"	      BUSINESS_CERTIFICATE_NO = UPPER('"+bc_no+"') AND "+
		                            "       ACTIVE_STATUS='Y'");
				boolean more = rs.next();
				if(more){
				   out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#"+rs.getString(3)+"@#"+rs.getString(4)
						           +"@#"+rs.getString(5)+"@#"+rs.getString(6)+"@##@");
				}	    
						
			}
			
	    	else if(m_chksql.trim().equals("get_outflow_cal")){
			
			    String m_rate		    = req.getParameter("rate");
          String m_value      = req.getParameter("value");
          String m_terms	    = req.getParameter("terms");
          String m_freq		    = req.getParameter("freq");
          String m_type       = req.getParameter("type");
					String m_vat_per    = req.getParameter("tax");
          String m_install    = req.getParameter("installments");
					String m_factor     = req.getParameter("factor");
					String m_pracent    = req.getParameter("pracent");
					String m_percentage = req.getParameter("percentage");
					String m_ami        = req.getParameter("ami");
					String m_vat_app    = req.getParameter("tax_app");
					String m_tax_rent   = req.getParameter("tax_rent");
					
          String m_sup_cr_per = req.getParameter("supcrper");
					String m_nibsm      = req.getParameter("nibsm");
          String m_residual   = req.getParameter("residual");
					String m_sup_credit = req.getParameter("sup_cr");
          String m_other_cha  = req.getParameter("other_cha");
					String m_maintenance= req.getParameter("maintan");
        	String m_int_base_m = req.getParameter("nitbasemar");
					String m_nitmar     = req.getParameter("nitmar");
					 
          //String m_last_rent  = req.getParameter("last_rent");
          String m_cash_out   = req.getParameter("cashout");
          String m_trn_sub    = req.getParameter("trnsub");
          String m_int_type   = req.getParameter("nittype");
          String m_trn_type   = req.getParameter("trn_type");
          String m_option     = req.getParameter("option");
          String m_vat_val    = req.getParameter("vat_val");
					String m_gro_val    = req.getParameter("gro_val");
					String m_rental     = req.getParameter("rental");
					String m_1rental    = req.getParameter("1rental");
								
					//out.println("m_install="+m_install);											
          callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_TEMP_PRICE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30);END;");
				  callstmt1.setString(1 ,m_terms);
          callstmt1.setString(2 ,m_freq);
          callstmt1.setString(3 ,m_install);
					callstmt1.setString(4 ,m_pracent);
					callstmt1.setString(5 ,m_factor);
          callstmt1.setString(6 ,m_type);
 				  callstmt1.setString(7 ,m_username);
 				  callstmt1.setString(8 ,m_rate);
 				  callstmt1.setString(9 ,m_value);
 				  callstmt1.setString(10,m_vat_per);
					callstmt1.setString(11,m_percentage);	
 				  callstmt1.setString(12,m_vat_app);	
					callstmt1.setString(13,m_ami);	
					callstmt1.setString(14,m_sup_credit);	
					callstmt1.setString(15,m_sup_cr_per);	
					callstmt1.setString(16,m_nibsm);	
					callstmt1.setString(17,m_residual);	
					callstmt1.setString(18,m_other_cha);	
					callstmt1.setString(19,m_maintenance);	
					callstmt1.setString(20,m_option);	
					callstmt1.setString(21,m_trn_type);
					callstmt1.setString(22,m_trn_sub);
					callstmt1.setString(23,m_int_type);
					callstmt1.setString(24,m_nitmar);
					callstmt1.setString(25,m_cash_out);
					callstmt1.setString(26,m_vat_val);
					callstmt1.setString(27,m_gro_val);
					callstmt1.setString(28,m_rental);
					callstmt1.setString(29,m_1rental);	
 				  callstmt1.setString(30,m_tax_rent);
					//out.println("t5");
			    callstmt1.execute();
					//out.println("t6");
			    
			         
          rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
					                        "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
																	"       "+m_vat_per+",/*"+m_freq+"**/"+m_terms+",'"+m_ami+"' "+
																	" FROM DUAL");
																	
          /*
					out.println("<html><head>");
          out.println("<title>PMT Value - Formulation</title></head>");
          out.println("<body bgcolor='white'>");
          out.println("<form name='Form1'>");
          out.println("<br>");
          */
					
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				out.println("var arr_assign= new Array();");
				out.println("var m_send_val= '';");
				
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
        
				out.println(" if(opt1=='YES'){");
			  out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
				out.println("  http_request.open('POST',url, true);");
				out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
				//out.println("  alert(construct_URL(\"get_advance_price_cal\",opt));");
				//out.println("  window.open(url+'?'+construct_URL(\"get_advance_price_cal\",opt));");
			  out.println("	 m_fact_val = \"\";"); 
				out.println("	 m_prac_val = \"\";"); 
				out.println("  m_percentage = \"\";"); 
				out.println("  m_char_val = \"0\";"); 
				out.println("  m_main_val = \"0\";"); 
				out.println("  m_mainten  = \"\";"); 
				out.println("  m_cash_out = \"\";"); 
				out.println("  m_cashOAmo = \"0\";"); 
				out.println("  m_vat_val  = \"0\";"); 
				out.println("  m_gro_val  = \"0\";"); 
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
			  out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
				out.println("    m_fact_val   = m_fact_val+/*unformat_noobject*/(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
			  out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
				out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
				out.println("    m_cashOAmo   = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
				out.println("    m_vat_val    = m_vat_val+unformat_noobject(document.Form1.elements[\"VATAMT\"+i].value)+\"@\";");
				out.println("    m_gro_val    = m_gro_val+unformat_noobject(document.Form1.elements[\"GROSS\"+i].value)+\"@\";");
				 
				out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
				//out.println("    alert('m_percentage'+m_percentage);");
			  out.println("	 }");
				out.println("	 document.Form1.CASH_OUTFLOW.value=m_cash_out;");
				out.println("	 window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
				//out.println("	 alert(m_cashOAmo+'!='+document.Form1.GROSS_AMOUNT.value);");
				out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
				out.println("	   alert('Please Check the Cash Outflow Amounts.');");
				out.println("	   return false;");
				out.println("	 }");
				
				out.println("	 m_send_val = \"chksql=get_outflow_recal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
				out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
				out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&tax_rent=\"+document.Form1.VAT_PER_RENT.value+\"&installments=\";");
				
				out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
				out.println("	 }else{");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
				out.println("	 }");
			
				
				out.println("    m_send_val = m_send_val+\"&maintan=\"+document.Form1.MAINTANENCE.value+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+document.Form1.CHARGE.value+\"&percentage=\"+m_percentage+");
				out.println("                 \"&vat_val=\"+m_vat_val+\"&gro_val=\"+m_gro_val;");
				//out.println("  window.open(url+'?'+m_send_val);");
				out.println("  http_request.send(m_send_val);");
			  out.println(" }else{");
				
				out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt); };");
				out.println("  http_request.open('GET',url, true);");
        //out.println("  window.open(url);");
				//out.println("  alert('opt-'+opt);");
				out.println("  http_request.send(null);");
				out.println(" }");
        out.println("}");
        
				
				out.println("function construct_URL(chk_sql,opt) {");
        out.println("	 m_send_val = \"chksql=\"+chk_sql+\"&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+opt+");
				out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
				out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
									
				out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
				out.println("	 }else{");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
				out.println("	 }");
				out.println("	 m_fact_val = \"\";"); 
				out.println("	 m_prac_val = \"\";"); 
				out.println("  m_percentage = \"\";"); 
				out.println("  m_char_val = \"0\";"); 
				out.println("  m_main_val = \"0\";"); 
				out.println("  m_mainten  = \"\";"); 
				out.println("	 m_vat_val = \"\";"); 
				out.println("	 m_gro_val = \"\";"); 
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
			  out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
				out.println("    m_fact_val   = m_fact_val+/*unformat_noobject*/(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
			  out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
				out.println("    m_vat_val    = m_vat_val+unformat_noobject(document.Form1.elements[\"VATAMT\"+i].value)+\"@\";");
				out.println("    m_gro_val    = m_gro_val+unformat_noobject(document.Form1.elements[\"GROSS\"+i].value)+\"@\";");
				
				out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
				//out.println("    alert('m_percentage'+m_percentage);");
			  out.println("	 }");
				
				out.println("    m_send_val = m_send_val+\"&maintan=\"+document.Form1.MAINTANENCE.value+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage+");
				out.println("                 \"&vat_val=\"+m_vat_val+\"&gro_val=\"+m_gro_val;");
				out.println("    return m_send_val;");
				out.println("	 }");
				
				
        out.println("function alertContents(http_request) {");
				//out.println(" alert('test');");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        //out.println("      alert(http_request.responseText);");
				out.println("         price_cal.innerHTML=http_request.responseText; ");
				out.println("         for(i=0;i<100000000;i++){o=i;}");
				out.println("         asign_div();");
        out.println("    } else {");
        out.println("        alert('There was a problem with the request.');");
        out.println("    }");
        out.println(" }");
        out.println("}");

        out.println("function alertGetContents(http_request,opt) {");
				//out.println(" alert('test--'+opt);");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        out.println("      if(opt==\"Main\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         main_id.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"SubType\"){");
				//out.println("         main_id.innerHTML=http_request.responseText; ");
				out.println("         Curr_no_opts=document.Form1.TRANSACTION_SUB.options;");
				out.println("         Curr_no_opts.length=0;");
				out.println("         m_string=http_request.responseText; ");
				out.println("         m_index =m_string.indexOf(\"~#@\");"); 
				out.println("         z=0;");
				out.println("         while(m_index!=-1){");
				out.println("           arr_assign[z]  = m_string.substring(0,m_string.indexOf(\"~#@\")); ");
				out.println("           m_string       = m_string.substring(m_string.indexOf(\"~#@\")+3); ");
				out.println("           z              = z+1;");
				out.println("           m_index =m_string.indexOf(\"~#@\");"); 
				out.println("         }");
				out.println("         for(x=0; x<arr_assign.length; x++){");
			  out.println("           m_string1      = arr_assign[x].substring(0,arr_assign[x].indexOf(\"@#\")); ");
				out.println("           m_string2      = arr_assign[x].substring(arr_assign[x].indexOf(\"@#\")+2); ");
				out.println("           m_string       = m_string2.substring(0,m_string2.indexOf(\"@#\")); ");
				//out.println("           alert('m_string1='+m_string1+'--m_string='+m_string+'');");
				out.println("           Curr_no_opts[x]= new Option(m_string,m_string1);");
			  out.println("         }");
				
				out.println("      }");
				out.println("    } else {");
        out.println("        main_id.innerHTML='';");
        out.println("    }");
        out.println(" }");
        out.println("}");
				//End Of Checking Values
				
				out.println("function asign_div(){");
				out.println("format_num(document.Form1.sun_g_nt,0)");
				out.println("format_num(document.Form1.sun_rent,0)");
				out.println("format_num(document.Form1.sun_p_nt,0)");
				out.println("format_num(document.Form1.sun_fact,4)");
				out.println("g_re.innerHTML   =document.Form1.sun_g_nt.value");
				out.println("n_re.innerHTML   =document.Form1.sun_rent.value");
				out.println("p_va.innerHTML   =document.Form1.sun_p_nt.value");
				out.println("m_fact.innerHTML =document.Form1.sun_fact.value");
				out.println("document.Form1.hid_count.value=document.Form1.hid_cou.value;");
				out.println("}");
			
				//Help Function
				out.println("function MyDialog(){");
				out.println("this.valout   = new Array(10);");
				out.println("}");	
				/*
				out.println("function get_help(Start,End,Hid_No,Crit,Sql,IfCount) {");			
				
				out.println("oBj = new MyDialog();");
				out.println("oBj.valout[3]  = \" \";");
				out.println("oBj.valout[4]  = \" \";");
				out.println("oBj.valout[5]  = \" \";");
				//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				//out.println("alert('333333--'+oBj.valout[0]+'--'+oBj.valout[1]+'--');");
				
				out.println("if(oBj.valout[0]=='Next')  {");
				out.println("Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[0]=='Prev') {");
				out.println("Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[0] == 'Exit'){");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("if(IfCount=='1'){"); 
				out.println("client_assign(oBj);");
				//out.println("load_data(document.Form1.txt_aff_code.value);");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("modle_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("");
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("vendor_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='5'){"); 
				out.println("mk_trn_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='6'){"); 
				out.println("mk_sub_trn_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='7'){"); 
				out.println("inq_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='7'){"); 
				out.println("price_assign(oBj);");
				out.println("}");
				
				
				out.println("}");
				out.println("else if(oBj.valout[4] != \" \"){ ");
				out.println("Crit = oBj.valout[4];");
				out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
				out.println("}	");
				out.println("}");	
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount)");
				out.println("}");
				
				out.println("function Next(Start,End,Hid_No,Crit,Sql,IfCount){");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount)");
				out.println("}");
				
				out.println("function criteria_1(Start,End,Hid_No,Crit,Sql,IfCount){");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				//client Help
				out.println("function model_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.ASSET_MAKE.value+\"@\"+document.Form1.SUB_MODEL.value+\"@Y@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function modle_assign(oBj){");
				out.println(" document.Form1.SUB_MODEL.value      =oBj.valout[2]");
				out.println(" document.Form1.VAT_PERCENTAGE.value =oBj.valout[4]");
				out.println(" document.Form1.MODEL_CODE.value =oBj.valout[4]");
				out.println("}");		
				//Marketing Officer
				out.println("function vendor_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.VENDOR.value+\"@Y@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function vendor_assign(oBj){");
				out.println(" document.Form1.VENDOR.value =oBj.valout[2]");
				out.println(" document.Form1.VENDOR_CODE.value =oBj.valout[1]");
				out.println("}");				
				
				out.println("function trn_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TRANSACTION_CODE.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_trn_assign(oBj){");
				out.println(" document.Form1.TRANSACTION_CODE.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				out.println("function sub_trn_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TRANSACTION_SUB.value+\"@\"+document.Form1.TRANSACTION_TYPE.value+\"@A@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_sub_trn_assign(oBj){");
				out.println(" document.Form1.TRANSACTION_SUB.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				out.println("function inq_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.INQ_NO.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function inq_assign(oBj){");
				out.println(" document.Form1.INQ_NO.value =oBj.valout[2]");
				out.println("}");		
				
				out.println("function price_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.PRICE_NO.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function price_assign(oBj){");
				out.println(" document.Form1.PRICE_NO.value =oBj.valout[2]");
				out.println("}");		
				*/
				
				//End of Help Function
				//change required DIV
				/*
				out.println("function change_div(){");
				out.println(" if(document.Form1.CLIENT_TYPE.options[document.Form1.CLIENT_TYPE.selectedIndex].text!='Individual' &&");
				out.println("    document.Form1.CONTACT_PERSON.value==''){ ");
				out.println("   conp.innerHTML=\"Contact Person *\";");
				out.println(" }else{");
				out.println("   conp.innerHTML=\"Contact Person\";");
				out.println(" }");
				out.println("}");
				
				
				//end of DIV change
				
				out.println("function change_repay(m_type){");
				out.println(" if(m_type=='TYPE'){");
				out.println("    document.Form1.REPAYMENT_INTERVAL.selectedIndex=document.Form1.REPAYMENT_TYPE.selectedIndex ");
				out.println(" }else{");
				out.println("    document.Form1.REPAYMENT_TYPE.selectedIndex=document.Form1.REPAYMENT_INTERVAL.selectedIndex ");
				out.println(" }");
				out.println("}");
				*/
				//Main Button Action
				//Submit
				out.println("function befor_submit(){");
				out.println(" m_str='<table><tr><td colspan=2>Cash Outflow Details</td></tr> <tr><td>Month</td><td>Amount</td></tr>'; ");
				out.println(" m_cashOAmo ='0'; ");
				out.println(" m_cash_out =''; ");
				out.println(" m_cashcount='0'; ");
				
				out.println("   for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
				out.println("    if(parseFloat(document.Form1.elements[\"CASHOUT\"+i].value)!=0){");
				out.println("     m_str      = m_str+'<tr><td><input type=text name=\"month'+m_cashcount+'\"       value='+document.Form1.elements[\"INSTALLMENT\"+i].value+' class=\"txt_input2\" disabled></td>';");
				out.println("     m_str      = m_str+'    <td><input type=text name=\"cash_amount'+m_cashcount+'\" value='+document.Form1.elements[\"CASHOUT\"+i].value+'     class=\"txt_input2\" disabled></td></tr>';");
				out.println("     m_cashOAmo = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
				out.println("     m_cashcount= parseFloat(m_cashcount)+1");
				out.println("    }");
				out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
				out.println("   }");
				out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
				out.println("	   alert('Please Check the Cash Outflow Amounts.');");
				out.println("	   return false;");
				out.println("	 }");
				out.println("	window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
				out.println(" m_str = m_str+'</table>'; ");
				out.println(" window.opener.outflow_id.innerHTML = m_str; ");
				out.println(" window.opener.document.Form1.CAL_COUNT.value = '1'; ");
				out.println(" window.opener.document.Form1.OUT_COUNT.value = m_cashcount; ");
				out.println(" window.opener.befor_cal(\"NO\",\"YES\"); ");
				out.println(" window.close();");
				
				out.println("}");
				//end of Submit Function
				
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
				//out.println("  Form1.reset() ;  ");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				//out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("   close_window(); ");
				//out.println("  top.frames[1].location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";");
				//out.println("  Form1.reset() ;  ");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				//out.println("  document.Form1.OPTION_DESC.value=\"B\";");
				//out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  window.close()");
				//out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are you sure you want to modify record?\")){  ");
				//out.println("  Form1.reset();   ");
				out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
				//out.println("  alert(document.Form1.OPTION_DESC.value);");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_active(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset() ;  ");
				out.println("  document.Form1.OPTION_NAME.value=\"ACT\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Re-activate\";");
				//out.println("  alert(document.Form1.OPTION_DESC.value);");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_deactive(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset();   ");
				out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
				out.println("  document.Form1.OPTION_DESC.value=\"De-activate\";");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=fales;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_cal(m_stat,opt) {");
        //out.println(" if(document.Form1.RATE.value!=\"\" && document.Form1.NET_AMOUNT.value !=\"\" && document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.PERIOD.value !=\"\" && document.Form1.GROSS_AMOUNT.value!=\"\"){");
				out.println("  document.Form1.CAL_COUNT.value=0;");
        out.println("  if(opt==\"YES\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price\";");
        out.println(" }else{");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=get_basic_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        out.println(" }");
        out.println("   m_opt=document.Form1.CACULATED.value;");
        out.println("   document.Form1.CACULATED.value=\"YES\";");
        out.println("   makeRequest(m_url,m_stat,opt);");
        //out.println(" }else{");
				//out.println("   alert('Please Enter ');");
        //out.println(" }");
        
        out.println("}");
				/*
				out.println("function load_flow() {");
        out.println(" if(document.Form1.RATE.value!=\"\" && document.Form1.NET_AMOUNT.value !=\"\" && document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.PERIOD.value !=\"\" && document.Form1.GROSS_AMOUNT.value!=\"\"){");
				
				out.println("	 m_fact_val = \"\";"); 
				out.println("	 m_prac_val = \"\";"); 
				out.println("  m_percentage = \"\";"); 
				out.println("  m_char_val = \"0\";"); 
				out.println("  m_main_val = \"0\";"); 
				out.println("  m_mainten  = \"\";"); 
				out.println("  m_cash_out = \"\";"); 
				out.println("  m_cashOAmo = \"0\";"); 
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
			  out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
				out.println("    m_fact_val   = m_fact_val+unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
			  out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
				out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
				out.println("    m_cashOAmo   = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
				 
				out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
				//out.println("    alert('m_percentage'+m_percentage);");
			  out.println("	 }");
				out.println("	 document.Form1.CASH_OUTFLOW.value=m_cash_out;");
				out.println("	 window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
				//out.println("	 alert(m_cashOAmo+'!='+document.Form1.GROSS_AMOUNT.value);");
				
				out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
				out.println("	   alert('Please Check the Cash Outflow Amounts.');");
				out.println("	   return false;");
				out.println("	 }");
				
				out.println("	 m_send_val = \"chksql=get_outflow_recal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
				out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
				out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
									
				out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
				out.println("	 }else{");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
				out.println("	 }");
				
				//out.println("  for(i=0;i<parseFloat(document.Form1.c_c_count.value);i++){");
			  //out.println("    m_char_val   = parseFloat(m_char_val)+parseFloat(unformat_noobject(document.Form1.elements[\"CHARGE_\"+i].value));");
				//out.println("	 }");
				
				//out.println("  for(j=0;j<=parseFloat(document.Form1.hid_y_count.value);j++){");
				//out.println("      m_main_val   =0;");
			  //out.println("    for(i=0;i<parseFloat(document.Form1.hid_m_count.value);i++){");
			  //out.println("      m_main_val   = parseFloat(m_main_val)+parseFloat(unformat_noobject(document.Form1.elements[\"MAINTANENCE_\"+i+\"_\"+j].value));");
				//out.println("	   }");
				//out.println("    m_mainten   = m_mainten+m_main_val+\"@\";");
				//out.println("	 }");
				
				out.println("    m_send_val = m_send_val+\"&maintan=\"+m_mainten+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage;");
				//out.println("   m_str=construct_URL(\"get_outflow_cal\",\"\");");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?\"+m_send_val;");
				out.println("   popupwin = window.open(m_url,'displayWindow1','left=50,top=280,width=900,height=390,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
        out.println(" }else{");
				out.println("   alert('Please Enter ');");
        out.println(" }");
        
        out.println("}");
        
				out.println("function befor_main(m_stat,opt) {");
        //out.println("   alert('func ');");
        out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
        //out.println("   alert('if ');");
        out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=loadMaintenance&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        out.println("   makeRequest(m_url,\"Main\",'NO');");
        out.println(" }else{");
				out.println("   main_id.innerHTML='';;");
        out.println(" }");
        out.println("}");
				
				
				out.println("function befor_subtype(m_stat,opt) {");
        //out.println("   alert('func ');");
        //out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
        //out.println("   alert('if ');");
        out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=chkSubType&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        out.println("   makeRequest(m_url,\"SubType\",'NO');");
        //out.println(" }else{");
				//out.println("   main_id.innerHTML='';;");
        //out.println(" }");
        
        out.println("}");
				*/
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				//end of Main Button Action
				//onload Action
				
				out.println("function befor_onload(){");
				/*out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); */
				out.println("}");
				//end of onload
				/*
				out.println("function cal_VatNet(){");
				out.println("  if(document.Form1.GROSS_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
				out.println("   document.Form1.NET_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.GROSS_AMOUNT.value))/((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
				out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
				
				//out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
				//out.println("    if(document.Form1.elements['CHARGE_'+j].value=='0' || document.Form1.elements['CHARGE_'+j].value==null){");
				//out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value);");
				//out.println("    }");
				//out.println("   }");
				
				out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.GROSS_AMOUNT.value));");
				out.println("  }");
				out.println("}");
				
				 out.println("function cal_VatGross(){");
				out.println("  if(document.Form1.NET_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
				out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
				out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
				
				//out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
				//out.println("    if(document.Form1.elements['CHARGE_'+j].value=='0' || document.Form1.elements['CHARGE_'+j].value==null){");
				//out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value);");
				//out.println("    }");
				//out.println("   }");
				
				out.println("   document.Form1.NET_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.NET_AMOUNT.value));");
				out.println("  }");
				out.println("}");
				*/
				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Marketing - Pricing - \"+m_val;"); 
			  out.println("}");
				
				out.println("   ");
				/*
				out.println("function chan_residual(){"); 
			  out.println(" if(parseFloat(unformat_noobject(document.Form1.NIBSM.value))>0){"); 
			  out.println("  document.Form1.RESIDUAL_VALUE.value=format_noobject(document.Form1.NIBSM.value);"); 
			  out.println("  document.Form1.NIBSM.value=format_noobject(document.Form1.NIBSM.value);"); 
				out.println("  document.Form1.RESIDUAL_VALUE.disabled=true;");
			  out.println(" }else{"); 
			  out.println("  document.Form1.RESIDUAL_VALUE.value='0';");
			  out.println("  document.Form1.RESIDUAL_VALUE.disabled=false;");
			  out.println(" }"); 
			  out.println("}");
				*/
				out.println("function format_text(obj){"); 
			  out.println("  obj.value=format_noobject(obj.value);"); 
			  out.println("}");
				
				out.println("function format_num(obj,i){");
				out.println("  if(!isNaN(obj)){");
				out.println("   alert('Please Enter Number!');");
				out.println("   obj='0';");
				out.println("  }else{");
			  out.println("   if(i!='6'){");
				out.println("    obj.value=unformat_noobject(obj.value)");
				out.println("    obj.value=format_noobject0(obj.value,i);");
				out.println("   }");
				
			  out.println("  }");
				out.println("}");
				
				
				out.println("function cha_val(count){"); 
				out.println(" if(document.Form1.TRANSACTION_SUB.value==\"STEP-UP\" || document.Form1.TRANSACTION_SUB.value==\"STEP-DOWN\"){ ");
			  out.println("  if(confirm(\"Do you want apply this change to all the below installments?\")){"); 
			  out.println("    for(i=parseFloat(count)+1;i<parseFloat(document.Form1.hid_count.value);i++){"); 
			  out.println("     document.Form1.elements[\"PERCENTAGE\"+i].value = unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+count].value) ");// unformat_noobject(
				out.println("    }");
				out.println("  }");
				out.println(" }");
				out.println("}");
				
				out.println("function cal_count(){"); 
			  out.println("  document.Form1.CAL_COUNT.value=parseFloat(document.Form1.CAL_COUNT.value)+1;"); 
			  out.println("}");
				/*
				out.println("function load_fild(){"); 
			  out.println(" if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
			
				//out.println("   int_base.innerHTML='Variable Interest Base'; ");
				//out.println("   int_base_txt.innerHTML='<input name=\"INTEREST_BASE\" type=\"text\" maxlength=\"50\" class=\"txt_input\" >';; ");
				//out.println("   int_mar.innerHTML='Variable Interest Margin'; ");
				//out.println("   int_mar_txt.innerHTML='<input name=\"INTEREST_MARGIN\" type=\"text\" maxlength=\"50\" class=\"txt_input\" >';; ");
				
				out.println("   int_type.innerHTML='<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >'+");
				out.println("                      '<tr class=tr_input><td width=25%>Variable Interest Base</td>'+");
				out.println("                      '<td  ><input name=\"INTEREST_BASE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" >'+");
				out.println("                      '<input type=button name=intbase_help value=Help class=\"but_input\" onclick=\"int_base_help('1','10','0','IntBaseSql','9')\"></td></tr>'+");
        out.println("                      '<tr class=tr_input><td >Variable Interest Margin</td>'+");
				out.println("                      '<td ><input name=\"INTEREST_MARGIN\" type=\"text\" maxlength=\"6\" class=\"txt_input\" ></td></tr></table>';");
				out.println(" }else{");
				out.println("   int_type.innerHTML='';");
				out.println(" }");
				out.println("}");
				*/
				

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
        
				out.println(" if(opt1=='YES'){");
			 //window.open("http://www.ofscl-leasing.lk:/myserver/servlet/LAKDL_AF_MK_Price?"+m_send_val);
				out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
				out.println("  http_request.open('POST',url, true);");
				out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
				out.println("	 m_fact_val = \"\";"); 
				out.println("	 m_prac_val = \"\";"); 
				out.println("  m_percentage = \"\";"); 
				out.println("  m_char_val = \"0\";"); 
				out.println("  m_main_val = \"0\";"); 
				out.println("  m_mainten  = \"\";"); 
				out.println("  m_cash_out = \"\";"); 
				out.println("  m_cashOAmo = \"0\";"); 
				out.println("	 m_vat_val = \"\";"); 
				out.println("	 m_gro_val = \"\";"); 
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
			  out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
				out.println("    m_fact_val   = m_fact_val+/*unformat_noobject*/(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
			  out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
				out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
				out.println("    m_cashOAmo   = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
				out.println("    m_vat_val    = m_vat_val+unformat_noobject(document.Form1.elements[\"VATAMT\"+i].value)+\"@\";");
				out.println("    m_gro_val    = m_gro_val+unformat_noobject(document.Form1.elements[\"GROSS\"+i].value)+\"@\";");
				  
				out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
				//out.println("    alert('m_percentage'+m_percentage);");
			  out.println("	 }");
				out.println("	 document.Form1.CASH_OUTFLOW.value=m_cash_out;");
				out.println("	 window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
				//out.println("	 alert(m_cashOAmo+'!='+document.Form1.GROSS_AMOUNT.value);");
				
				out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
				out.println("	   alert('Please Check the Cash Outflow Amounts.');");
				out.println("	   return false;");
				out.println("	 }");
				
				out.println("	 m_send_val = \"chksql=get_outflow_recal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&rental=\"+unformat_noobject(document.Form1.RENTAL_AMOUNT.value)+\"&1rental=\"+unformat_noobject(document.Form1.RENTAL1_AMOUNT.value)+");				
				out.println("	              \"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
				out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
				out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&tax_rent=\"+document.Form1.VAT_PER_RENT.value+\"&installments=\";");
									
				out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
				out.println("	 }else{");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
				out.println("	 }");
				
				
				out.println("    m_send_val = m_send_val+\"&maintan=\"+m_mainten+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage+");
				out.println("                 \"&vat_val=\"+m_vat_val+\"&gro_val=\"+m_gro_val;");
				
				//out.println("  construct_URL(\"get_advance_price_cal\",opt);");
			  //out.println("  window.open(url+'?'+m_send_val);");
				out.println("  http_request.send(m_send_val);");
			  out.println(" }else{");
				
				out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt); };");
				out.println("  http_request.open('GET',url, true);");
        //out.println("  window.open(url);");
				//out.println("  alert('opt-'+opt);");
				out.println("  http_request.send(null);");
				out.println(" }");
        out.println("}");
        
				/*
				out.println("function construct_URL(chk_sql,opt) {");
        out.println("	 m_send_val = \"chksql=\"+chk_sql+\"&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+opt+");
				out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
				out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
									
				out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
				out.println("	 }else{");
				out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
				out.println("	 }");
				out.println("	 m_fact_val = \"\";"); 
				out.println("	 m_prac_val = \"\";"); 
				out.println("	 m_outf_val = \"\";"); 
				out.println("  m_percentage = \"\";"); 
				out.println("  m_char_val = \"0\";"); 
				out.println("  m_main_val = \"0\";"); 
				out.println("  m_mainten  = \"\";"); 
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
			  out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
				out.println("    m_fact_val   = m_fact_val+unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
			  out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
				out.println("    m_outf_val   = m_outf_val+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
				
				out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
				//out.println("    alert('m_percentage'+m_percentage);");
			  out.println("	 }");
				out.println("  for(i=0;i<parseFloat(document.Form1.c_c_count.value);i++){");
			  out.println("    m_char_val   = parseFloat(m_char_val)+parseFloat(unformat_noobject(document.Form1.elements[\"CHARGE_\"+i].value));");
				out.println("	 }");
				
				out.println("  for(j=0;j<=parseFloat(document.Form1.hid_y_count.value);j++){");
				out.println("      m_main_val   =0;");
			  out.println("    for(i=0;i<parseFloat(document.Form1.hid_m_count.value);i++){");
			  out.println("      m_main_val   = parseFloat(m_main_val)+parseFloat(unformat_noobject(document.Form1.elements[\"MAINTANENCE_\"+i+\"_\"+j].value));");
				out.println("	   }");
				out.println("    m_mainten   = m_mainten+m_main_val+\"@\";");
				out.println("	 }");
				
				out.println("    m_send_val = m_send_val+\"&maintan=\"+m_mainten+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage;");
				out.println("    return m_send_val;");
				out.println("	 }");
				*/
				
        out.println("function alertContents(http_request) {");
				//out.println(" alert('test');");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        //out.println("      alert(http_request.responseText);");
				out.println("         price_cal.innerHTML=http_request.responseText; ");
				//out.println("         for(i=0;i<10000;i++){o=i;}");
				//out.println("         asign_div();");
        out.println("    } else {");
        out.println("        alert('There was a problem with the request.');");
        out.println("    }");
        out.println(" }");
        out.println("}");
        /*
        out.println("function alertGetContents(http_request,opt) {");
				//out.println(" alert('test--'+opt);");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        out.println("      if(opt==\"Main\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         main_id.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"SubType\"){");
				//out.println("         main_id.innerHTML=http_request.responseText; ");
				out.println("         Curr_no_opts=document.Form1.TRANSACTION_SUB.options;");
				out.println("         Curr_no_opts.length=0;");
				out.println("         m_string=http_request.responseText; ");
				out.println("         m_index =m_string.indexOf(\"~#@\");"); 
				out.println("         z=0;");
				out.println("         while(m_index!=-1){");
				out.println("           arr_assign[z]  = m_string.substring(0,m_string.indexOf(\"~#@\")); ");
				out.println("           m_string       = m_string.substring(m_string.indexOf(\"~#@\")+3); ");
				out.println("           z              = z+1;");
				out.println("           m_index =m_string.indexOf(\"~#@\");"); 
				out.println("         }");
				out.println("         for(x=0; x<arr_assign.length; x++){");
			  out.println("           m_string1      = arr_assign[x].substring(0,arr_assign[x].indexOf(\"@#\")); ");
				out.println("           m_string2      = arr_assign[x].substring(arr_assign[x].indexOf(\"@#\")+2); ");
				out.println("           m_string       = m_string2.substring(0,m_string2.indexOf(\"@#\")); ");
				out.println("           alert('m_string1='+m_string1+'--m_string='+m_string+'');");
				out.println("           Curr_no_opts[x]= new Option(m_string,m_string1);");
			  out.println("         }");
				
				out.println("      }");
				out.println("    } else {");
        out.println("        main_id.innerHTML='';");
        out.println("    }");
        out.println(" }");
        out.println("}");
				*/
				//End Of Checking Values
				
				out.println("function asign_div(){");
				out.println("format_num(unformat_noobject(document.Form1.sun_g_nt.value),0)");
				out.println("format_num(unformat_noobject(document.Form1.sun_rent.value),0)");
				out.println("format_num(unformat_noobject(document.Form1.sun_p_nt.value),0)");
				out.println("format_num(unformat_noobject(document.Form1.sun_fact.value),4)");
				out.println("g_re.innerHTML   =document.Form1.sun_g_nt.value");
				out.println("n_re.innerHTML   =document.Form1.sun_rent.value");
				out.println("p_va.innerHTML   =document.Form1.sun_p_nt.value");
				out.println("m_fact.innerHTML =document.Form1.sun_fact.value");
				out.println("document.Form1.hid_count.value=document.Form1.hid_cou.value;");
				out.println("}");
				
				out.println("function format_text(obj){"); 
			  out.println("  obj.value=format_noobject(obj.value);"); 
			  out.println("}");
				
				out.println("function format_num(obj,i){");
				out.println("  if(isNaN(unformat_noobject(obj.value))){");
				out.println("   alert('Please Enter Number!');");
				out.println("  }else{");
			  out.println("   obj.value=unformat_noobject(obj.value)");
				out.println("   obj.value=format_noobject0(obj.value,i);"); 
			  out.println("  }");
				out.println("}");
				
        out.println("</Script>");
				out.println("<body onload=\"befor_onload()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				
				out.println("<INPUT TYPE=HIDDEN NAME=RATE 							VALUE="+m_rate+">");
				out.println("<INPUT TYPE=HIDDEN NAME=GROSS_AMOUNT 			VALUE="+m_value+">");
				out.println("<INPUT TYPE=HIDDEN NAME=PERIOD 						VALUE="+m_terms+">");
				out.println("<INPUT TYPE=HIDDEN NAME=REPAYMENT_INTERVAL VALUE="+m_freq+">");
				out.println("<INPUT TYPE=HIDDEN NAME=PAYMENT_MODE 			VALUE="+m_type+">");
				out.println("<INPUT TYPE=HIDDEN NAME=SUPPLIER_CREDIT 		VALUE="+m_sup_credit+">");
				out.println("<INPUT TYPE=HIDDEN NAME=RESIDUAL_VALUE 		VALUE="+m_residual+">");
				out.println("<INPUT TYPE=HIDDEN NAME=CASH_OUTFLOW 			VALUE="+m_cash_out+">");
				out.println("<INPUT TYPE=HIDDEN NAME=TRANSACTION_SUB 		VALUE="+m_trn_sub+">");
				out.println("<INPUT TYPE=HIDDEN NAME=INTEREST_TYPE 			VALUE="+m_int_type+">");
				out.println("<INPUT TYPE=HIDDEN NAME=NIBSM 							VALUE="+m_nibsm+">");
				out.println("<INPUT TYPE=HIDDEN NAME=TRANSACTION_TYPE 	VALUE="+m_trn_type+">");
				out.println("<INPUT TYPE=HIDDEN NAME=VAT_PERCENTAGE 		VALUE="+m_vat_per+">");
				out.println("<INPUT TYPE=HIDDEN NAME=VAT_PER_APP 				VALUE="+m_vat_app+">");
				out.println("<INPUT TYPE=HIDDEN NAME=VAT_PER_RENT				VALUE="+m_tax_rent+">");
					
				out.println("<INPUT TYPE=HIDDEN NAME=AMI 								VALUE="+m_ami+">");
				out.println("<INPUT TYPE=HIDDEN NAME=CHARGE 						VALUE="+m_other_cha+">");
				out.println("<INPUT TYPE=HIDDEN NAME=MAINTANENCE 				VALUE="+m_maintenance+">");
				out.println("<INPUT TYPE=HIDDEN NAME=INTEREST_MARGIN 		VALUE="+m_nitmar+">");
				out.println("<INPUT TYPE=HIDDEN NAME=INTEREST_BASE 			VALUE="+m_int_base_m+">");
				out.println("<INPUT TYPE=HIDDEN NAME=RENTAL_AMOUNT 			VALUE="+m_rental+">");
				out.println("<INPUT TYPE=HIDDEN NAME=OPTION_DESC 			  VALUE=\"\">");
				out.println("<INPUT TYPE=HIDDEN NAME=CAL_COUNT 			    VALUE=\"\">");
				out.println("<input type=hidden name=\"CACULATED\"      value=\"NO\">");
				
				
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box></td>");
				out.println("</tr>");	
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr>");
				/*out.println("<td style=\"width: 6px\"><img src=\""+m_html_client_url+"/images/btnback.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><img src=\""+m_html_client_url+"/images/btndelete.gif\" ></td>");
				//out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_deactive();><img src=\""+m_html_client_url+"/images/btndelete.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_modify();><img src=\""+m_html_client_url+"/images/btnedit.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_active();><img src=\""+m_html_client_url+"/images/btncancel.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_reset();><img src=\""+m_html_client_url+"/images/btnreset.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_submit();><img src=\""+m_html_client_url+"/images/btnsubmit.gif\" ></td>");*/
				
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=new      value=\"New\"       class=mainbut onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit     value=\"Edit\"      class=mainbut onclick=befor_modify();   onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete   value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel   value=\"Re-active\" class=mainbut onclick=befor_active();   onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_submit value=\"Save\"      class=mainbut onclick=befor_submit();   onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=reset_   value=\"Reset\"     class=mainbut onclick=befor_reset();    onMouseOver='load_roll_value(\"Reset\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        //out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=recal     value=\"Re-Cal\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");     onMouseOver='load_roll_value(\"Re-Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        //out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=end_b     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				//out.println("<tr>");
				//out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				//out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				//out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				//out.println("<tr class=tr_input>");
				//out.println("<td width=\"15%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"120\" ></td>");
				//out.println("<td width=\"35%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"180\" ></td>");
				//out.println("<td width=\"15%\">");
				//out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				//out.println("<td>");
				//out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				//out.println("</tr>");
				//out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=40%>");
	double mm_tot_fact 				= 0;
					double mm_tot_term 				= 0;
					out.println("<table class=table border='1' width='100%' >");
          out.println("<tr class=tr_input><td  width='100%' Id=price_cal>");
          
          if(rs.next()){
              
              mm_tot_fact				= rs.getDouble(7);
						  mm_tot_term				= rs.getInt(5);
              
					}		
					/*		        
              out.println("<table class=table border='1' width='100%' >");
              out.println("<tr class=tr_input>");
							out.println("<td  width='20%' >Total Factor</td>");
              out.println("<td width='30%' >"+nf.format(rs.getDouble(7))+"</td>");
							out.println("<td  width='20%' >Total Gross Rental</td>");
              out.println("<td width='30%' ID=GRENT></td>");
							out.println("</tr>");
              out.println("<tr class=tr_input>");
							out.println("<td  width='20%' >Total Present Value</td>");
              out.println("<td width='30%' ID=PVAL></td>");
							out.println("<td  width='20%' >Avg. Gross Rental</td>");
              out.println("<td width='30%' ID=A_GRENT></td>");
							out.println("</tr>");
							out.println("</table>");
							
          }
          
          out.println("<br>");		
					*/
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=9 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=tr_input><td  width='5%' >Inst</td>");
          out.println("<td  width='5%' ></td>");
          //out.println("<td  width='20%' >Rate per Month(RM)</td>");
          //out.println("<td  width='30%' >(RM+1)=X</td>");
          out.println("<td  width='5%' align=right>Factor</td>");
					out.println("<td  width='10%' align=right>Rental Factor</td>");
					out.println("<td  width='15%' align=right>Net Rental</td>");
					out.println("<td  width='15%' align=right>VAT </td>");
					out.println("<td  width='15%' align=right>Gross Rental</td>");
					out.println("<td  width='15%' align=right>P.V. Inflow</td>");
					out.println("<td  width='20%' align=right>Cash Outflow</td>");
					out.println("<td  width='15%' align=right>P.V. Outflow</td>");
					//out.println("<td  width='20%' >AMI</td>");
					
					
          out.println("</tr>");
					out.println("<tr class=tr_input><td  width='8%' ></td><td  ></td>");
          out.println("<td id=m_fact align=right></td>");
          out.println("<td ></td>");
          out.println("<td id=n_re align=right></td>");
          out.println("<td id=v_re align=right></td>");
          out.println("<td id=g_re align=right></td>");
          out.println("<td id=p_va align=right></td>");
          out.println("<td id=o_va align=right></td>");
          out.println("<td id=op_va align=right></td>");
          //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
          out.println("</tr>");
          
          double sum_rate =0;
          double sum_rent =0;
					double sum_p_re =0;
					double sum_g_re =0;
					double sum_v_re =0;
					double sum_o_re =0;
					double sum_op_re=0;
					int m_start=0;
					/*int m_end  =mm_term;
					
					if(m_type.equals("ARREASE")){
					  m_start=1;
						m_end  =mm_term+1;
					}
					*/
					int j=0;
          //for(j=m_start;j<m_end;j++){
              //out.println("J="+j);
             //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
              					
							rs = stmt.executeQuery (" SELECT A.INSTALLMENT, A.FACTOR, A.NEW_NET, A.NEW_GROSS, "+
																			"        A.NEW_PRACENT, A.PERCENTAGE, A.AMI,CASH_OUT, "+
																			"        CASH_OUT*FACTOR,(A.NEW_GROSS-A.NEW_NET),"+
																			"        INTEREST_AMOUNT,CAPITAL_AMOUNT  "+
																			"  FROM  "+m_schema_name+".AF_MK_TBD_PRICE_CAL A "+
													 						"	WHERE  ENT_USER  = '"+m_username+"'"+
																			" ORDER BY INSTALLMENT");
																			
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input><td>"+(j+1)+"</td>");
                  out.println("<td  width='8%' >"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" disabled onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(10))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"CASHOUT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(8))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.CASHOUT"+j+",'0')></td>");
                  out.println("<td align=right><input name=\"OUTPVT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(9))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
									out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(12))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(11))+"\"></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
									sum_g_re=sum_g_re+(rs.getDouble(4));
									sum_o_re=sum_o_re+(rs.getDouble(8));
									sum_op_re=sum_op_re+(rs.getDouble(9));
									sum_v_re =sum_v_re+(rs.getDouble(10));
									j=j+1;
									if(rs.next()){
									out.println("<tr class=tr_input1><td>"+(j+1)+"</td>");
                   out.println("<td  width='8%' >"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" disabled onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(10))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"CASHOUT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(8))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.CASHOUT"+j+",'0')></td>");
                  out.println("<td align=right><input name=\"OUTPVT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(9))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
									out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(12))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(11))+"\"></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
									sum_g_re=sum_g_re+(rs.getDouble(4));
									sum_o_re=sum_o_re+(rs.getDouble(8));
									sum_op_re=sum_op_re+(rs.getDouble(9));
									sum_v_re =sum_v_re+(rs.getDouble(10));
									j=j+1;
									}
									
              }
          //}
          out.println("<tr class=tr_input><td  ></td><td  ></td>");
          //out.println("<td  width='20%' ></td>");
          out.println("<td  align=right><input type=hidden name=hid_count value="+j+">"+nf1.format(sum_rate)+"<input type=hidden name=sun_fact value="+nf.format(sum_rate)+"></td>");
          out.println("<td></td>");
          out.println("<td align=right>"+nf.format(sum_rent)+"<input type=hidden name=sun_rent value="+nf.format(sum_rent)+"></td>");
          out.println("<td align=right>"+nf.format(sum_v_re)+"<input type=hidden name=sun_v_nt value="+nf.format(sum_v_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_g_re)+"<input type=hidden name=sun_g_nt value="+nf.format(sum_g_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_p_re)+"<input type=hidden name=sun_p_nt value="+nf.format(sum_p_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_o_re)+"<input type=hidden name=sun_o_nt value="+nf.format(sum_o_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_op_re)+"<input type=hidden name=sun_op_nt value="+nf.format(sum_op_re)+"></td>");
          
					out.println("</tr>");
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=9><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("</tr></table>");
          out.println("</td>");
			
				out.println("</table>");    
				out.println("<tr class=tr_input> ");
				out.println("<td valign=\"bottom\" height=\"20\"></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
      } 
			
	    	else if(m_chksql.trim().equals("get_outflow_recal")){
			
			    String m_rate		    = req.getParameter("rate");
          String m_value      = req.getParameter("value");
          String m_terms	    = req.getParameter("terms");
          String m_freq		    = req.getParameter("freq");
          String m_type       = req.getParameter("type");
					String m_vat_per    = req.getParameter("tax");
          String m_install    = req.getParameter("installments");
					String m_factor     = req.getParameter("factor");
					String m_pracent    = req.getParameter("pracent");
					String m_percentage = req.getParameter("percentage");
					String m_ami        = req.getParameter("ami");
					String m_vat_app    = req.getParameter("tax_app");
					String m_tax_rent   = req.getParameter("tax_rent");
					
          String m_sup_cr_per = req.getParameter("supcrper");
					String m_nibsm      = req.getParameter("nibsm");
          String m_residual   = req.getParameter("residual");
					String m_sup_credit = req.getParameter("sup_cr");
          String m_other_cha  = req.getParameter("other_cha");
					String m_maintenance= req.getParameter("maintan");
        	String m_int_base_m = req.getParameter("nitbasemar");
          //String m_last_rent  = req.getParameter("last_rent");nitmar
          String m_cash_out   = req.getParameter("cashout");
          String m_trn_sub    = req.getParameter("trnsub");
          String m_int_type   = req.getParameter("nittype");
          String m_trn_type   = req.getParameter("trn_type");
          String m_option     = req.getParameter("option");
          String m_nitmar     = req.getParameter("nitmar");
					String m_vat_val    = req.getParameter("vat_val");
					String m_gro_val    = req.getParameter("gro_val");
					String m_rental     = req.getParameter("rental");
					String m_1rental    = req.getParameter("1rental");
								
					//out.println("m_percentage="+m_percentage);											
          callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_TEMP_PRICE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30);END;");
				  callstmt1.setString(1 ,m_terms);
          callstmt1.setString(2 ,m_freq);
          callstmt1.setString(3 ,m_install);
					callstmt1.setString(4 ,m_pracent);
					callstmt1.setString(5 ,m_factor);
          callstmt1.setString(6 ,m_type);
 				  callstmt1.setString(7 ,m_username);
 				  callstmt1.setString(8 ,m_rate);
 				  callstmt1.setString(9 ,m_value);
 				  callstmt1.setString(10,m_vat_per);
					callstmt1.setString(11,m_percentage);	
 				  callstmt1.setString(12,m_vat_app);	
					callstmt1.setString(13,m_ami);	
					callstmt1.setString(14,m_sup_credit);	
					callstmt1.setString(15,m_sup_cr_per);	
					callstmt1.setString(16,m_nibsm);	
					callstmt1.setString(17,m_residual);	
					callstmt1.setString(18,m_other_cha);	
					callstmt1.setString(19,m_maintenance);	
					callstmt1.setString(20,m_option);	
					callstmt1.setString(21,m_trn_type);
					callstmt1.setString(22,m_trn_sub);
					callstmt1.setString(23,m_int_type);
					callstmt1.setString(24,m_nitmar);
					callstmt1.setString(25,m_cash_out);
					callstmt1.setString(26,m_vat_val);
					callstmt1.setString(27,m_gro_val);
					callstmt1.setString(28,m_rental);
					callstmt1.setString(29,m_1rental);
					callstmt1.setString(30,m_tax_rent);
					
 				  //out.println("t5");
			    callstmt1.execute();
					//out.println("t6");
			    
			         
          rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
					                        "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
																	"       "+m_vat_per+",/*"+m_freq+"**/"+m_terms+",'"+m_ami+"' "+
																	" FROM DUAL");
																	

				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=40%>");
	        double mm_tot_fact 				= 0;
					double mm_tot_term 				= 0;
					out.println("<table class=table border='1' width='100%' >");
          out.println("<tr class=tr_input><td  width='100%' >");
          
          if(rs.next()){
              
              mm_tot_fact				= rs.getDouble(7);
						  mm_tot_term				= rs.getInt(5);
              
					}	
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=load_flow();             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=tr_input><td  width='5%' >Inst</td>");
          out.println("<td  width='5%' ></td>");
          //out.println("<td  width='20%' >Rate per Month(RM)</td>");
          //out.println("<td  width='30%' >(RM+1)=X</td>");
          out.println("<td  width='5%' align=right>Factor</td>");
					out.println("<td  width='10%' align=right>Rental Factor</td>");
					out.println("<td  width='15%' align=right>Net Rental</td>");
					out.println("<td  width='15%' align=right>VAT</td>");
					out.println("<td  width='15%' align=right>Gross Rental</td>");
					out.println("<td  width='15%' align=right>P.V Inflow</td>");
					out.println("<td  width='20%' align=right>Cash Outflow</td>");
					out.println("<td  width='15%' align=right>P.V. Outflow</td>");
					//out.println("<td  width='20%' >AMI</td>");
					
					
          out.println("</tr>");
					out.println("<tr class=tr_input><td  width='8%' ></td><td  ></td>");
          out.println("<td id=m_fact align=right></td>");
          out.println("<td ></td>");
          out.println("<td id=n_re align=right></td>");
          out.println("<td id=v_re align=right></td>");
          out.println("<td id=g_re align=right></td>");
          out.println("<td id=p_va align=right></td>");
          out.println("<td id=o_va align=right></td>");
          out.println("<td id=op_va align=right></td>");
          //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
          out.println("</tr>");
          
          double sum_rate=0;
          double sum_rent=0;
					double sum_p_re=0;
					double sum_g_re=0;
					double sum_v_re=0;
					double sum_o_re=0;
					double sum_op_re=0;
					int m_start=0;
					/*int m_end  =mm_term;
					
					if(m_type.equals("ARREASE")){
					  m_start=1;
						m_end  =mm_term+1;
					}
					*/
					int j=0;
          //for(j=m_start;j<m_end;j++){
              //out.println("J="+j);
             //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
              					
							rs = stmt.executeQuery (" SELECT A.INSTALLMENT, A.FACTOR, A.NEW_NET, A.NEW_GROSS, "+
																			"        A.NEW_PRACENT, A.PERCENTAGE, A.AMI,CASH_OUT,CASH_OUT*FACTOR, "+
																			"        A.NEW_GROSS-A.NEW_NET,INTEREST_AMOUNT,CAPITAL_AMOUNT "+ 
																			"  FROM  "+m_schema_name+".AF_MK_TBD_PRICE_CAL A "+
													 						"	WHERE  ENT_USER  = '"+m_username+"'"+
																			" ORDER BY INSTALLMENT");
																			
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input><td>"+(j+1)+"</td>");
                  out.println("<td  width='8%' >"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td ><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td ><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" disabled onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(10))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"CASHOUT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(8))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.CASHOUT"+j+",'0')></td>");
                  out.println("<td align=right><input name=\"OUTPVT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(9))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
									out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(12))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(11))+"\"></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
									sum_g_re=sum_g_re+(rs.getDouble(4));
									sum_v_re=sum_v_re+(rs.getDouble(10));
									sum_o_re=sum_o_re+(rs.getDouble(8));
									sum_op_re=sum_op_re+(rs.getDouble(9));
									j=j+1;
									
									if(rs.next()){
									out.println("<tr class=tr_input1><td>"+(j+1)+"</td>");
                  out.println("<td  width='8%' >"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td ><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td ><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" disabled onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                  out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(10))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right><input name=\"CASHOUT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(8))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.CASHOUT"+j+",'0')></td>");
                  out.println("<td align=right><input name=\"OUTPVT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(9))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
									out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(12))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(11))+"\"></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
									sum_g_re=sum_g_re+(rs.getDouble(4));
									sum_v_re=sum_v_re+(rs.getDouble(10));
									sum_o_re=sum_o_re+(rs.getDouble(8));
									sum_op_re=sum_op_re+(rs.getDouble(9));
									j=j+1;
									}
              }
          //}
          out.println("<tr class=tr_input><td  ></td><td  ></td>");
          //out.println("<td  width='20%' ></td>");
          out.println("<td align=right><input type=hidden name=hid_count value="+j+">"+nf1.format(sum_rate)+"<input type=hidden name=sun_fact value="+nf.format(sum_rate)+"></td>");
          out.println("<td></td>");
          out.println("<td align=right>"+nf.format(sum_rent)+"<input type=hidden name=sun_rent value="+nf.format(sum_rent)+"></td>");
          out.println("<td align=right>"+nf.format(sum_g_re)+"<input type=hidden name=sun_g_nt value="+nf.format(sum_g_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_p_re)+"<input type=hidden name=sun_p_nt value="+nf.format(sum_p_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_o_re)+"<input type=hidden name=sun_o_nt value="+nf.format(sum_o_re)+"></td>");
          out.println("<td align=right>"+nf.format(sum_op_re)+"<input type=hidden name=sun_op_nt value="+nf.format(sum_op_re)+"></td>");
          //out.println("<td></td>");
					
					out.println("</tr>");
					out.println("</table>");
					
				
      } 	
			 else if(m_chksql.trim().equals("get_basic_price_cal")){
          String m_rate		    = req.getParameter("rate");
          String m_value      = req.getParameter("value");
          String m_terms	    = req.getParameter("terms");
          String m_freq		    = req.getParameter("freq");
          String m_type       = req.getParameter("type");
					String m_vat_per    = req.getParameter("tax");
          String m_install    = req.getParameter("installments");
					String m_factor     = req.getParameter("factor");
					String m_pracent    = req.getParameter("pracent");
					String m_percentage = req.getParameter("percentage");
					String m_ami        = req.getParameter("ami");
					String m_vat_app    = req.getParameter("tax_app");
					String m_tax_rent   = req.getParameter("tax_rent");
					         
          String m_sup_cr_per = req.getParameter("supcrper");
					String m_nibsm      = req.getParameter("nibsm");
          String m_residual   = req.getParameter("residual");
					String m_sup_credit = req.getParameter("sup_cr");
          String m_other_cha  = req.getParameter("other_cha");
					String m_maintanance= req.getParameter("maintan");
        	String m_int_base_m = req.getParameter("nitbasemar");
          String m_last_rent  = req.getParameter("last_rent");
          String m_cash_out   = req.getParameter("cashout");
          String m_trn_sub    = req.getParameter("trnsub");
          String m_int_type   = req.getParameter("nittype");
          String m_trn_type   = req.getParameter("trn_type");
          											
          
          rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
					                        "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
																	"       "+m_vat_per+",/*"+m_freq+"**/"+m_terms+",'"+m_ami+"' "+
																	" FROM DUAL");
																	
          /*
					out.println("<html><head>");
          out.println("<title>PMT Value - Formulation</title></head>");
          out.println("<body bgcolor='white'>");
          out.println("<form name='Form1'>");
          out.println("<br>");
          */
          int 	 mm_freq					  = 0;
          int 	 mm_terms						= 0;
          int    mm_term            = 0;
					double mm_value						= 0;
          double mm_rate_per_month  = 0;
					double mm_tot_fact 				= 0;
					double mm_vat_per 				= 0;
					int mm_ami             = 0;
					
          if(rs.next()){
              
              mm_rate_per_month = rs.getDouble(3);
              mm_value					= rs.getDouble(6);
              mm_terms					= rs.getInt(5);
              mm_term 					= rs.getInt(9);
              mm_freq						= rs.getInt(4);
							mm_tot_fact				= rs.getDouble(7);
							mm_vat_per				= rs.getDouble(8);
              mm_ami     				= rs.getInt(10);
               
              out.println("<table class=table border='1' width='100%' >");
              out.println("<tr class=tr_input><td  width='40%' >Total Factor</td>");
              out.println("<td width='60%' >"+nf.format(rs.getDouble(7))+"</td></tr>");
              /*out.println("<tr><td  width='40%' >Rate (%)</td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(1))+"</td></tr>");
              out.println("<tr><td  width='40%' >Rate </td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(2))+"</td></tr>");
              out.println("<tr><td  width='40%' >Rate per Month</td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(3))+"</td></tr>");
              out.println("<tr><td  width='40%' >Payment Frequency</td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(4))+"</td></tr>");
              out.println("<tr><td  width='40%' >No of Installaments</td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(5))+"</td></tr>");
              */
							out.println("</table>");
							
          }
          
          out.println("<br>");
          
          out.println("<table class=table border='0' width='100%' class=table>");
          out.println("<tr class=tr_input><td  width='10%' >Installment No(Y)</td>");
          //out.println("<td  width='20%' >Rate per Month(RM)</td>");
          //out.println("<td  width='30%' >(RM+1)=X</td>");
          out.println("<td  width='10%' >Factor</td>");
					out.println("<td  width='10%' >Percentage</td>");
					out.println("<td  width='20%' >Net Rental</td>");
					out.println("<td  width='20%' >VAT</td>");
					out.println("<td  width='20%' >Gross Rental</td>");
					out.println("<td  width='20%' >Pracent Value</td>");
					//out.println("<td  width='20%' >AMI</td>");
					
					
          out.println("</tr>");
          double sum_rate=0;
          double sum_rent=0;
					double sum_p_re=0;
					int m_start=0;
					int m_end  =mm_term;
					
					if(m_type.equals("ARREASE")){
					  m_start=1;
						m_end  =mm_term+1;
					}
					int j=0;
          for(j=m_start;j<m_end;j++){
              //out.println("J="+j);
              out.println("<tr class=tr_input><td  width='8%' >"+j+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
              //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
              										
							rs = stmt.executeQuery ("SELECT "+(mm_rate_per_month+1)+",1/POWER("+(mm_rate_per_month+1)+","+j+"),"+
							                                ""+m_value+"/"+mm_tot_fact+"/(1+("+m_vat_app+"/100)),"+m_value+"/"+mm_tot_fact+","+//"+mm_vat_per+"
																							""+m_value+"/"+mm_tot_fact+"/1/POWER("+(mm_rate_per_month+1)+","+j+") "+
																			"FROM   DUAL ");

              if(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<td ><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td ><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\"1\" maxlength=\"5\" class=\"txt_input1\" ></td>");
                  out.println("<td ><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("<td ><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("<td ><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
              }
          }
          out.println("<tr class=tr_input><td  ></td>");
          //out.println("<td  width='20%' ></td>");
          out.println("<td  align=right><input type=hidden name=hid_cou value="+j+">"+nf.format(sum_rate)+"</td>");
          out.println("<td></td>");
          out.println("<td align=right>"+nf.format(sum_rent)+"</td>");
          out.println("<td></td>");
          out.println("<td align=right>"+nf.format(sum_p_re)+"</td>");
          //out.println("<td></td>");
          out.println("</tr></table>");
          
        
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
