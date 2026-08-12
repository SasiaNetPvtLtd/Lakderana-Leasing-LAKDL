//Created by -Nuwan De Silva
//Collection - Assign Coolection Officer
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
//import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Lease_Assign extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_ac_status;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			/*
			//************************************************************	
		    //	LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
		    //	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
           //   String m_username 						= "AA";//m_sn_methods.username;
		
	        String m_username 	= m_sn_methods.username; // added by udara on 03-12-2012
			
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			*/
			
			LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();		
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
            String m_username 						= con_method.username;
			String header_name    = con_method.header_name;
			String m_rep_cur=""; //added by nuwan de silva23-07-07
			
			String m_fschema_name=con_method.client_name.trim();
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 

			out = res.getOutputStream();
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		    nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		    nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			
	   //		m_chksql         = req.getParameter("chksql");
	   //		m_ac_status = req.getParameter("ac_status");
	   //	stmt = conn.createStatement ();
	   ///	stmt1 = conn.createStatement ();
		    //   String m_sort_column   = "ENT_DATE";	
				//	 String m_order_by_type = "ASC";
		
				
		
		// added by udara on 03-12-2012		
		//String m_username = m_sn_methods.username;	
		String user_location = "";
		String user_location_set = "";
		
		try{

			stmt = conn.createStatement ();
			
			rs= stmt.executeQuery("  "+
				" SELECT "+
					" NVL("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"'),'-') "+
					" FROM DUAL "+
				" ");
			
			if(rs.next()){
				user_location = rs.getString(1);
			}
			
		}
		catch (Exception eee){
			out.println(eee.toString());
		}
		
		//out.println(user_location);
		
		if(user_location.equals("HEADOFFICE") || user_location.equals("HO")) // modified by udara on 13-09-2013
			user_location_set = "";
		else
			user_location_set = user_location;
		
		// end by udara on 03-12-2012	

     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("var lineno=0;");
			  out.println("var arr_size=0;");
				out.println("var lineno_finanace=0;");
				out.println("var b_check_help=0;");
				
				out.println("var new_data_vec=new Array();");

				
				
				
			out.println("function get_vector(data_vec) {");
			out.println("m_user_val=\"TXT_USER\"+document.Form1.hid_row_no.value;");
		  out.println("m_finace_val=\"TXT_FINANCE_NO\"+document.Form1.hid_row_no.value;");
			out.println("			if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M2'  && document.Form1.elements[m_user_val].value!=\"\"){");
			out.println("    document.Form1.elements[m_user_val].value=data_vec[0];"); 
			
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.elements[m_user_val].value!=\"\"){");
			out.println("     help_button_user(document.Form1.hid_row_no.value);");
      out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				new_data_vec=data_vec;");
			out.println("				display_data(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				display_message();");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3'){");
			out.println("				new_data_vec=data_vec;");
			out.println("				display_data2(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M4'  && document.Form1.elements[m_finace_val].value!=\"\"){");
			out.println("    document.Form1.elements[m_finace_val].value=data_vec[0];"); 
			out.println("show_data_by_finanace_no(document.Form1.elements[m_finace_val].value,'APPLICATION_NO','ASC');");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.elements[m_finace_val].value!=\"\"){");
			out.println("     help_button_finanace_no(document.Form1.hid_row_no.value);");
      out.println("			}");
			out.println("			else if(data_vec.length==0  && document.Form1.hid_chk_status.value=='G4'  && document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println("   help_button_4()");			
			out.println("			}");
			out.println("			else if(data_vec.length==0  && document.Form1.hid_chk_status.value=='G5'  && document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("   help_button_6()");			
			out.println("			}");			
			
			out.println("		 else if(data_vec.length==0  && document.Form1.hid_chk_status.value=='G2'  && document.Form1.TXT_NEW_COLL_OFFICER.value!=\"\"){");
			out.println("help_button_user_col_new()");
			out.println("			}");
			
			out.println("		 else if(data_vec.length>0  && document.Form1.hid_chk_status.value=='G2'  && document.Form1.TXT_NEW_COLL_OFFICER.value!=\"\"){");
			out.println("		 document.Form1.TXT_NEW_COLL_OFFICER.value=data_vec[0];");
			out.println("			}");
			
			
			out.println("		 else if(data_vec.length==0  && document.Form1.hid_chk_status.value=='G1'  && document.Form1.TXT_COLL_OFFICER.value!=\"\"){");
			out.println("help_button_user_col()");
			out.println("			}");
			
			out.println("		 else if(data_vec.length>0  && document.Form1.hid_chk_status.value=='G1'  && document.Form1.TXT_COLL_OFFICER.value!=\"\"){");
			out.println("		 document.Form1.TXT_COLL_OFFICER.value=data_vec[0];");
			out.println("    get_data_col_officer('APPLICATION_NO','ASC')	");
			out.println("			}");
			
			out.println("		 else if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M_OFFICER'  && document.Form1.TXT_COLL_OFFICER.value!=\"\"){");
			out.println("		 new_data_vec=data_vec;");
			out.println("    display_data_officer(data_vec);");
			out.println("			}");
			
			out.println("			}");
				
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
		
		  out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_user(document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_finanace_no(document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		assign_help_button_user_col();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"5\"){"); 
			out.println("		assign_help_button_user_col_new();"); 
	  	out.println("		}"); 
			
			

			out.println("		if(IfCount==\"6\"){"); 
			out.println("		help_value_assign_6(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		help_value_assign_4(oBj);"); 
	  	out.println("		}"); 
			
			// added by udara on 09-10-2012
			
			out.println("		if(IfCount==\"41\"){"); 
			out.println("		help_value_assign_41(oBj);"); 
	  	    out.println("		}"); 
			
			// end by udara on 09-10-2012

		
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data();");//Added To The Clear The Area Code
			out.println("	}");
			
			
			out.println("	}	"); //
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
			
			
			
			
			out.println("function clear_data() {");
			
		  out.println("if(b_check_help==1){");
			out.println("m_user_clear=\"TXT_USER\"+document.Form1.hid_row_no.value;");
			out.println("document.Form1.elements[m_user_clear].value=\"\";");
			out.println("document.Form1.elements[m_user_clear].focus();");
			out.println("}");
			
			out.println("if(b_check_help==2){");
			out.println("m_user_clear=\"TXT_FINANCE_NO\"+document.Form1.hid_row_no.value;");
			out.println("document.Form1.elements[m_user_clear].value=\"\";");
      out.println("}");
				
			out.println("}");
			
			  out.println("var m_sort_column='APPLICATION_NO';");
				out.println("var m_order_by_type='ASC';");
					
				out.println("function sort_data(m_sort_col) {");
				out.println("	 order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col==m_sort_column){");
				out.println("	   if(m_order_by_type=='DESC'){");
				out.println("	      order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("       order_by_type = 'ASC'; ");
			  out.println("  }");
				
				out.println("m_sort_column=m_sort_col;");
				out.println("m_order_by_type=order_by_type;");
								
				out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"I\"){");
				
				out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			 	out.println("get_Application_numbers(m_sort_col,m_order_by_type);");
				out.println("}");
				
				out.println("else if(document.Form1.SCREEN_NAME.value==\"EDIT\" ){");
				out.println("i=0;");
				//out.println("m_assign_finanace=\"TXT_FINANCE_NO\"+i");
				out.println("show_data_by_finanace_no(m_sort_col,m_order_by_type);"); //document.Form1.elements[m_assign_finanace].value,
				out.println("}");
				out.println("}");
				
				out.println("else {");
				out.println("get_data_col_officer(m_sort_col,m_order_by_type);");
				out.println("}");
				
				out.println("}");
				
				
				out.println("function befor_end(m_obj) {");
				//out.println("alert('value'+m_obj);");
				out.println("if(m_obj==\"GO_TOP\"){");
				out.println("m_go_top=\"top_b\";");
				out.println("document.Form1.elements[m_go_top].focus();}");
				out.println("else if(m_obj==\"GO_END\"){");
				out.println("m_go_end=\"end_b\";");
				out.println("document.Form1.elements[m_go_end].focus();}");

    //    out.println("   m_obj.focus();");
        out.println("}");
				
			
			
			
			
				
				out.println("function display_message(){");
			  
				  out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
					out.println("'<td  width=\"*%\" align=\"center\"><B>No Records Found</td>'+");
          out.println("'</TR></table>';");		
					
      	out.println("}");
				
				
				
					//!--------Display The Header -------------------------------------//
			    out.println("function header(){");
				  out.println("m_table.innerHTML=\"\" ");
					out.println("m_table3.innerHTML=\"\" ");
		      out.println("lineno=0;");
					out.println("arr_size=0;");

					out.println("	if(document.Form1.hid_chk_status.value=='M1'){");		
											
		      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
					out.println("'<td  width=\"15%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Application No  \"    onclick=sort_data(\"APPLICATION_NO\") ><B>Application No</td>'+");
          out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Client Name     \"    onclick=sort_data(\"CLIENT_NAME\") ><B>Client Name</td>'+");
				 //out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - City Code     \"    onclick=sort_data(\"CITY_CODE\") ><B>City Code</td>'+");
          out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Inquiry No  \"        onclick=sort_data(\"INQUARY_NO\") ><B>Inquiry No</td>'+");
          //out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Finance No  \"    onclick=sort_data(\"FINANCE_NO\") ><B>Finance No</td>'+"); // commented by udara 15-07-2015
			out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Finance No  \"    onclick=sort_data(\"FINANCE_NO_SORT\") ><B>Finance No</td>'+"); // added by udara 15-07-2015
				//	out.println("'<td  width=\"10%\" align=\"left\"><B><u>Core Applicant</u></td>'+");
				//out.println("'<td  width=\"10%\" align=\"left\"><B><u>Facility No</u></td>'+");
					out.println("'<td  width=\"12.5%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Total Finance Amount  \"    onclick=sort_data(\"TOTAL_FINANCE_AMOUNT\") ><B>Total Finance Amount</td>'+");
					out.println("'<td  width=\"12.5%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Current Finance Amount  \"    onclick=sort_data(\"CURRENT_FINANCE_AMOUNT\") ><B>Current Finance Amount</td>'+");
					out.println("'<td  width=\"15%\" align=\"left\" style={width:120px;}><B>Collection Officer</td>'+");
					//out.println("'<td  width=\"5%\" align=\"center\"></td>'+");
				  out.println("'<td  width=\"15%\" align=\"center\" style={width:120px;}><B>Remark</td>'+");

			    out.println("'</TR></table>';");		
					
					out.println("}");
					out.println("	else if(document.Form1.hid_chk_status.value=='M3'){");		
		      out.println("m_table3.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
					out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Application No  \"    onclick=sort_data(\"APPLICATION_NO\") ><B>Application No</td>'+");
          out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Client Name     \"    onclick=sort_data(\"CLIENT_NAME\") ><B>Client Name</td>'+");
				 // out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - City Code     \"    onclick=sort_data(\"CITY_CODE\") ><B>City Code</td>'+");
          out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Inquiry No  \"        onclick=sort_data(\"INQUARY_NO\") ><B>Inquiry No</td>'+");
          //out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Finance No  \"    onclick=sort_data(\"FINANCE_NO\") ><B>Finance No</td>'+"); // commented by udara 15-07-2015
			out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Finance No  \"    onclick=sort_data(\"FINANCE_NO_SORT\") ><B>Finance No</td>'+");
				//	out.println("'<td  width=\"10%\" align=\"left\"><B><u>Core Applicant</u></td>'+");
				//out.println("'<td  width=\"10%\" align=\"left\"><B><u>Facility No</u></td>'+");
					out.println("'<td  width=\"10%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Total Finance Amount  \"    onclick=sort_data(\"TOTAL_FINANCE_AMOUNT\") ><B>Total Finance Amount</td>'+");
					out.println("'<td  width=\"10%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Currernt Finance Amount  \"    onclick=sort_data(\"CURRENT_FINANCE_AMOUNT\") ><B>Current Finance Amount</td>'+");
					out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Assigned Officer  \"    onclick=sort_data(\"COLLECTION_OFFICER\")><B>Assigned Officer</td>'+");
					out.println("'<td  width=\"15%\" align=\"left\" style={width:120px;}><B>Collection Officer</td>'+");
					//out.println("'<td  width=\"5%\" align=\"center\"></td>'+");
					out.println("'<td  width=\"15%\" align=\"center\" style={width:120px;}><B>Remark</td>'+");
			    out.println("'</TR></table>';");		
					
					out.println("}");
					out.println("	else if(document.Form1.hid_chk_status.value=='M_OFFICER'){");		
		      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
					out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Application No  \"    onclick=sort_data(\"APPLICATION_NO\") ><B>Application No</td>'+");
          out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Client Name     \"    onclick=sort_data(\"CLIENT_NAME\") ><B>Client Name</td>'+");
				  //out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - City Code     \"    onclick=sort_data(\"CITY_CODE\") ><B>City Code</td>'+");
          out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Inquiry No  \"        onclick=sort_data(\"INQUARY_NO\") ><B>Inquiry No</td>'+");
          //out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Finance No  \"    onclick=sort_data(\"FINANCE_NO\") ><B>Finance No</td>'+"); // commented by udara 15-07-2015
			out.println("'<td  width=\"10%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Finance No  \"    onclick=sort_data(\"FINANCE_NO_SORT\") ><B>Finance No</td>'+"); // added by udara 15-07-2015
					out.println("'<td  width=\"10%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Total Finance Amount  \"    onclick=sort_data(\"TOTAL_FINANCE_AMOUNT\") ><B>Total Finance Amount</td>'+");
					out.println("'<td  width=\"10%\" align=\"right\" style= cursor:hand; title=\"Click here to sort by - Currernt Finance Amount  \"    onclick=sort_data(\"CURRENT_FINANCE_AMOUNT\") ><B>Current Finance Amount</td>'+");
					out.println("'<td  width=\"8%\" align=\"left\" style= cursor:hand; title=\"Click here to sort by - Assigned Officer  \"    onclick=sort_data(\"COLLECTION_OFFICER\") ><B>Assigned Officer</td>'+");
					out.println("'<td  width=\"12%\" align=\"center\" style={width:120px;}><B>Collection Officer</td>'+");
					//out.println("'<td  width=\"5%\" align=\"center\"></td>'+");
						out.println("'<td  width=\"15%\" align=\"center\" style={width:100px;}><B>Remark</td>'+");
					out.println("'<td  width=\"5%\" align=\"left\" style={width:10px;} ><B>Approve</td>'+");
			    out.println("'</TR></table>';");		
					
					out.println("}");


							

     	out.println("}");
				
				
			out.println("function change_val_req(row_no){")	;
			out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_required].checked==true){");
			out.println("document.Form1.elements[m_chk_required].value='on'");
			out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
			out.println("document.Form1.elements[m_chk_required].value='off'");
			out.println("}");	
			out.println("}");	
			
			
			out.println("function help_button_user(row_No) {"); 
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("b_check_help=1;");
			out.println("m_user=\"TXT_USER\"+row_No");
			out.println("    Crit = document.Form1.elements[m_user].value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_USER_ID_sql','1');"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_EMP_CODE_sql','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_user(row_No) {"); 
			out.println("m_user_assign=\"TXT_USER\"+row_No");
			out.println("document.Form1.elements[m_user_assign].value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_button_user_col() {"); 
			out.println("    Crit = document.Form1.TXT_COLL_OFFICER.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_USER_ID_sql','3');"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_EMP_CODE_sql','3');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function assign_help_button_user_col() {"); 
			out.println("    document.Form1.TXT_COLL_OFFICER.value=oBj.valout[2];"); 
			out.println(" view_collection_officer_data()");
			out.println("}"); 
			
			out.println("function help_button_user_col_new() {"); 
			out.println("    Crit =document.Form1.TXT_NEW_COLL_OFFICER.value+\"@\"+document.Form1.TXT_COLL_OFFICER.value+\"@Y@\";"); 
			//out.println("    Crit = document.Form1.TXT_NEW_COLL_OFFICER.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'m_help_collection_officer_new','5');"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_employee_id_new','5');"); 
			
			out.println("}"); 
			out.println(""); 

			out.println("function assign_help_button_user_col_new() {"); 
			out.println("    document.Form1.TXT_NEW_COLL_OFFICER.value=oBj.valout[2];"); 
			out.println("    assign_collection_bulk();"); 
			out.println("}"); 

			
			
			out.println("function help_button_finanace_no(row_No) {"); 
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("b_check_help=2;");
			out.println("m_finance=\"TXT_FINANCE_NO\"+row_No");
			out.println("    Crit = document.Form1.elements[m_finance].value+\"@ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql','2');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_finanace_no(row_No) {"); 
			out.println("m_assign_finanace=\"TXT_FINANCE_NO\"+row_No");
			out.println("    document.Form1.elements[m_assign_finanace].value=oBj.valout[2];"); 
			out.println("show_data_by_finanace_no('APPLICATION_NO','ASC');");//document.Form1.elements[m_assign_finanace].value,
			out.println("}"); 
			
			out.println("function val_finanace_no(row_No){");
			out.println("assignState('M4')");
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("m_finance_no_val=\"TXT_FINANCE_NO\"+row_No");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_validate_finace_no&data_val=\"+document.Form1.elements[m_finance_no_val].value+\"&ac_status=ACTIVATED\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			
			out.println("}"); 
			
			out.println("function show_data_by_finanace_no(m_sort_column,m_order_by_type){"); //val,
			out.println("assignState('M3')");
			
			out.println("var m_user_location_set = '"+user_location_set+"'; "); // added by udara on 13-09-2013
			//modified by delanjali for ref 845 on 2007-09-05
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no&finanace_no=\"+val+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			
			//comment by nuwan de silva on 18-10-07-----------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			
			//added by nuwan de silva on 18-10-07-----------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED&finance_no=\"+document.Form1.TXT_CONTRACT_NO.value;"); // commented by udara on 13-09-2013

			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_finanace_no_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED&finance_no=\"+document.Form1.TXT_CONTRACT_NO.value+\"&user_location_set=\"+m_user_location_set;");  // added by udara on 13-09-2013
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}"); 
			
			
			
			//added by nuwan de silva on 19-10-07-----------------------------------------
			out.println("function get_data_col_officer(m_sort_column,m_order_by_type){"); 
			out.println("assignState('M_OFFICER')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_assign_data&col_officer=\"+document.Form1.TXT_COLL_OFFICER.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);"); 
			out.println("}"); 
			
			//added by nuwan de silva on 19-10-07-----------------------------------------
			out.println("function assign_collection_bulk(){"); 
			out.println("for(i=0;i<arr_size;i++){"); 
			out.println("m_new_officer=\"TXT_USER\"+i");
			out.println("m_chk_req=\"CHK_REQUIRED\"+i");
			out.println("document.Form1.elements[m_new_officer].value=document.Form1.TXT_NEW_COLL_OFFICER.value;"); 
			out.println("document.Form1.elements[m_chk_req].checked=true;"); 
			out.println("document.Form1.elements[m_chk_req].value='on';"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function val_user(row_No){");
			out.println("assignState('M2')");
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("m_user_val=\"TXT_USER\"+row_No");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_User&data_val=\"+document.Form1.elements[m_user_val].value+\"&ac_status=Y\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_Assign_Collection_Officer&data_val=\"+document.Form1.elements[m_user_val].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
		  //out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("  function  display_data_officer(data_vec){");
			out.println("header();	");		
			out.println("var i=0;");
			out.println("var j=0;");
			out.println("var c_client=0;");
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"top_b\"   value=\"Go to End\" class=\"mainbut\" onclick=befor_end(\"GO_END\"); onMouseOver=load_roll_value(\"End\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_top.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			out.println("while(i<data_vec.length){");
			out.println("m_app_no=       '<TD WIDTH=\"10%\"   align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_app_details('+[c_client]+')\" ><u>'+data_vec[i]+'</u></TD>';");
			out.println("m_client_code=  '<TD WIDTH=\"10%\"   align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_client_details('+[c_client+8]+')\"><u>'+data_vec[i+1]+'</u></TD>';");
			//out.println("m_city_code=    '<TD WIDTH=\"10%\"   align=\"left\">'+data_vec[i+2]+'</TD>';");
			out.println("m_inq_no=       '<TD WIDTH=\"10%\"   align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_inq_details('+[c_client+3]+')\"><u>'+data_vec[i+3]+'</u></TD>';");
			out.println("m_fin_no=       '<TD WIDTH=\"10%\"   align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_fin_details('+[c_client+4]+')\"><u>'+data_vec[i+4]+'</u></TD>';");
			out.println("m_total_finance='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
			out.println("m_cur_finance=  '<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+6]+'</TD>';");		
			out.println("m_ass_officer=  '<TD WIDTH=\"8%\"   align=\"left\">'+data_vec[i+9]+'</TD>';");		
			out.println("m_officer=      '<td width=\"15%\"   align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_USER'+lineno+' style={width:70px;} maxlength=\"15\" size=\"15\" onblur=\"val_user('+lineno+')\">&nbsp;<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></TD>';") ;			
			//out.println("m_btn=          '<td width=\"5%\"    align=\"center\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></td>';"); 
			out.println("m_remark=       '<td width=\"15%\"   align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK'+lineno+' style={width:100px;} maxlength=\"500\" size=\"15\"></TD>';") ;		// added by Sandun --- on 28/07/2008	
			out.println("m_officer_chk=  '<TD WIDTH=\"5%\"    align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"off\" onclick=\"change_val_req('+lineno+')\"></td>';");			
			out.println("m_hid_input=    '<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i]+'>';");
		
			out.println("if(j>0 && j%2==1){");
			out.println("m_writedata='<TR class=\"tr_input1\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_ass_officer+m_officer+m_remark+m_officer_chk+'</TR>'+m_hid_input;"); 
			out.println("	}");
			out.println("	else{");
			out.println("m_writedata='<TR class=\"tr_input\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_ass_officer+m_officer+m_remark+m_officer_chk+'</TR>'+m_hid_input;"); 
			out.println("	}");
			
			/*out.println("	if(document.Form1.hid_chk_status.value=='M1'){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			out.println("	else if(document.Form1.hid_chk_status.value=='M3'){");
			out.println("m_table3.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			*/
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			out.println("j=j+1;");
			out.println("i=i+10;");
			out.println("c_client=c_client+10;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");		
			out.println("}"); //End while loop
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"end_b\"   value=\"Go to Top\" class=\"mainbut\" onclick=befor_end(\"GO_TOP\"); onMouseOver=load_roll_value(\"Top\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_end.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			
			out.println("    document.Form1.TXT_NEW_COLL_OFFICER.disabled=false;"); 
			out.println("    document.Form1.BUT_HELP_NEW_COLL_OFFICER.disabled=false;"); 
			
			out.println("}");		
			
			
				
				
			out.println("  function  display_data(data_vec){");
			out.println("header();	");		
			out.println("var i=0;");
			out.println("var j=0;");
			out.println("var c_client=0;");
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"top_b\"   value=\"Go to End\" class=\"mainbut\" onclick=befor_end(\"GO_END\"); onMouseOver=load_roll_value(\"End\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_top.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			out.println("while(i<data_vec.length){");
			out.println("m_app_no='<TD WIDTH=\"15%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_app_details('+[c_client]+')\" ><u>'+data_vec[i]+'</u></TD>';");
			out.println("m_client_code='<TD WIDTH=\"10%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_client_details('+[c_client+7]+')\"><u>'+data_vec[i+1]+'</u></TD>';");
			//out.println("m_city_code='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+2]+'</TD>';");
			out.println("m_inq_no='<TD WIDTH=\"10%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_inq_details('+[c_client+3]+')\"><u>'+data_vec[i+3]+'</u></TD>';");
			out.println("m_fin_no='<TD WIDTH=\"10%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_fin_details('+[c_client+4]+')\"><u>'+data_vec[i+4]+'</u></TD>';");
			//out.println("m_co_applicant='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+4]+'</TD>';");
			//out.println("m_facility_no='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
			out.println("m_total_finance='<TD WIDTH=\"12.5%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
			out.println("m_cur_finance='<TD WIDTH=\"12.5%\" align=\"right\">'+data_vec[i+6]+'</TD>';");		
			//out.println("m_officer='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\"></td>';");			
			out.println("m_officer='<td width=\"15%\" align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_USER'+lineno+' maxlength=\"15\" size=\"15\" onblur=\"val_user('+lineno+')\" style=\"{width:70px;}\" >&nbsp;<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></TD>';") ;
			//out.println("m_btn='<td width=\"5%\" align=\"center\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></td>';"); 
			out.println("m_remark='<td width=\"15%\"   align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK'+lineno+' style={width:120px;} maxlength=\"500\" size=\"15\"></TD>';") ;		// added by Sandun --- on 28/07/2008	
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i]+'>';");
			//out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i+1]+'>';");
			
			out.println("if(j>0 && j%2==1){");
			//       	out.println("<tr class=\"tr_input1\" >");
			//out.println("m_writedata='<TR class=\"tr_input1\">'+m_app_no+m_client_code+m_city_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+m_btn+'</TR>'+m_hid_input;"); 
			
			out.println("m_writedata='<TR class=\"tr_input1\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+m_remark+'</TR>'+m_hid_input;"); 
			out.println("	}");
			out.println("	else{");
			//      	out.println("<tr class=\"tr_input\" >");
			//out.println("m_writedata='<TR class=\"tr_input\">'+m_app_no+m_client_code+m_city_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+m_btn+'</TR>'+m_hid_input;"); 
			out.println("m_writedata='<TR class=\"tr_input\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+m_remark+'</TR>'+m_hid_input;"); 
			out.println("	}");
			//   out.println("m_writedata='<TR>'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+'</TR>'+m_hid_input;"); 
			out.println("	if(document.Form1.hid_chk_status.value=='M1'){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			out.println("	else if(document.Form1.hid_chk_status.value=='M3'){");
			out.println("m_table3.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");		
			out.println("j=j+1;");
			out.println("i=i+8;");
			out.println("c_client=c_client+8;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");		
			out.println("}"); //End while loop
			
			out.println("m_row='<tr class=tr_input>'+");
			out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"end_b\"   value=\"Go to Top\" class=\"mainbut\" onclick=befor_end(\"GO_TOP\"); onMouseOver=load_roll_value(\"Top\"); onmouseout=load_roll_value(\"New\");></td>'+");
			out.println("'</tr>';");
			out.println("m_table_end.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
			
			out.println("}");		
			
			
					out.println("  function  display_data2(data_vec){");
					out.println("header();	");		
					out.println("var i=0;");
					out.println("var j=0;");
					out.println("var c_client=0;");
					out.println("m_row='<tr class=tr_input>'+");
					out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"top_b\"   value=\"Go to End\" class=\"mainbut\" onclick=befor_end(\"GO_END\"); onMouseOver=load_roll_value(\"End\"); onmouseout=load_roll_value(\"New\");></td>'+");
					out.println("'</tr>';");
					out.println("m_table_top_2.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
					out.println("while(i<data_vec.length){");
					
					out.println("m_app_no='<TD WIDTH=\"10%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_app_details('+[c_client]+')\"  ><u>'+data_vec[i]+'</u></TD>';");
					out.println("m_client_code='<TD WIDTH=\"10%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_client_details('+[c_client+8]+')\" ><u>'+data_vec[i+1]+'</u></TD>';");
					//out.println("m_city_code='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+2]+'</TD>';");
					out.println("m_inq_no='<TD WIDTH=\"10%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_inq_details('+[c_client+3]+')\"  ><u>'+data_vec[i+3]+'</u></TD>';");
					out.println("m_fin_no='<TD WIDTH=\"10%\" align=\"left\"      STYLE=\"{cursor:hand;}\"  onclick=\"show_fin_details('+[c_client+4]+')\" ><u>'+data_vec[i+4]+'</u></TD>';");
					//out.println("m_co_applicant='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+4]+'</TD>';");
					//out.println("m_facility_no='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
					out.println("m_total_finance='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+5]+'</TD>';");		
					out.println("m_cur_finance='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+6]+'</TD>';");		
					out.println("m_chk_req='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"hidden\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"on\" ></td>';");		//Uncommented by Jithendra 16-01-2017 and type='hidden'	
					out.println("m_ass_officer='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+9]+'</TD>';");		
					out.println("m_officer='<td width=\"15%\" align=\"center\" ><input class=\"txt_input\" type=\"text\" name=TXT_USER'+lineno+' maxlength=\"15\" size=\"15\" onblur=\"val_user('+lineno+')\"style={width:70px;}>&nbsp;<input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></TD>';") ;
					//out.println("m_btn='<td width=\"5%\" align=\"center\" ><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER'+lineno+' value=\"Help\" onClick=\"help_button_user('+lineno+')\"></td>';"); 
					out.println("m_remark= '<td width=\"15%\"   align=\"center\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK'+lineno+' style={width:120px;} maxlength=\"500\" size=\"15\"></TD>';") ;		// added by Sandun --- on 28/07/2008	
					out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i]+'>';");
					//out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_APP_NO'+lineno+'	VALUE='+data_vec[i+1]+'>';");
					out.println("if(j>0 && j%2==1){");
					//       	out.println("<tr class=\"tr_input1\" >");
					out.println("m_writedata='<TR class=\"tr_input1\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_ass_officer+m_officer+m_remark+'</TR>'+m_hid_input;"); 
					out.println("	}");
					out.println("	else{");
					//      	out.println("<tr class=\"tr_input\" >");
					out.println("m_writedata='<TR class=\"tr_input\">'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_ass_officer+m_officer+m_remark+'</TR>'+m_hid_input+m_chk_req;"); 
					out.println("	}");
					//   out.println("m_writedata='<TR>'+m_app_no+m_client_code+m_inq_no+m_fin_no+m_total_finance+m_cur_finance+m_officer+'</TR>'+m_hid_input;"); 
					
					
					out.println("	if(document.Form1.hid_chk_status.value=='M1'){");
					out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
					out.println("m_writedata+'</table>';");
					out.println("}");		
					out.println("	else if(document.Form1.hid_chk_status.value=='M3'){");
					out.println("m_table3.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
					out.println("m_writedata+'</table>';");
					out.println("}");		
					out.println("j=j+1;");
					out.println("i=i+10");
					out.println("c_client=c_client+10;");
					out.println("lineno=lineno+1;");
					out.println("arr_size=arr_size+1;");		
					out.println("}"); //End while loop
					out.println("m_row='<tr class=tr_input>'+");
					out.println("'<td width=\"5%\" align=\"right\"><input type=\"button\" name=\"end_b\"   value=\"Go to Top\" class=\"mainbut\" onclick=befor_end(\"GO_TOP\"); onMouseOver=load_roll_value(\"Top\"); onmouseout=load_roll_value(\"New\");></td>'+");
					out.println("'</tr>';");
					out.println("m_table_end_2.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+m_row+'</table>';");
					out.println("}");		
				
		//--------------------------------------------------------------------------		
		
		  out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function get_Application_numbers(m_sort_column,m_order_by_type){");
			out.println("assignState('M1')");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease&ac_status=COMPLETED\";");
			
			//---commented by delanjali on 2007-09-05 for ref no 845-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_new&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			//comment by nuwan de silva on 18-10-07---------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			
			//added by nuwan de silva on 18-10-07-------------------
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Assigning_Lease_new&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ACTIVATED&finance_no=\"+document.Form1.TXT_CONTRACT_NO.value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			
			out.println("function validate_data(){"); 
			out.println("return true;"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Lease_Assign';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Lease_Assign';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Lease_Assign';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Lease_Assign\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Assign Collection Officer - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Assign Collection Officer - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
		
   
			out.println("}"); 
			out.println("else{}");
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function edit_data(){");
			
			out.println("m_table.innerHTML=\"\" ");
			out.println("m_table2.innerHTML=\"\" ");
			out.println("m_table3.innerHTML=\"\" ");
			out.println("m_table_top.innerHTML=\"\" ");
			out.println("m_table_end.innerHTML=\"\" ");
			out.println("m_table_top_2.innerHTML=\"\" ");
			out.println("m_table_end_2.innerHTML=\"\" ");
			

			
		//  out.println("lineno_city=0;");
			
			
			out.println("m_finanace_lable='<td  width=\"30%\" align=\"left\" >Finance No</td>';");
			out.println("m_finance='<td width=\"40%\" align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_FINANCE_NO'+lineno_finanace+' maxlength=\"15\" size=\"15\" onblur=\"val_finanace_no('+lineno_finanace+')\">'+") ;
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_FINANCE_NO'+lineno_finanace+' value=\"Help\" onClick=\"help_button_finanace_no('+lineno_finanace+')\"></td><td width=\"*%\"></td>';"); 
			
			
			out.println("m_writedata='<TR >'+m_finanace_lable+m_finance+'</TR>';"); 
			
			out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			out.println("}"); 
			
			
			out.println("function show_client_details(row_No) {"); 
			out.println("show_client(new_data_vec[row_No]);"); 
			out.println("}"); 
			
		
			out.println("function show_fin_details(row_No) {"); 
			out.println("show_finance_detail_drill(new_data_vec[row_No]);"); 
			out.println("}"); 
			
			out.println("function show_inq_details(row_No) {"); 
			out.println("show_inquiry_drill(new_data_vec[row_No]);"); 
			out.println("}"); 
			
			
			out.println("function show_app_details(row_No) {"); 
			out.println("show_application_detail_drill(new_data_vec[row_No]);"); 
			out.println("}"); 
			
			
			//--added by delanjali for ref 845 on 2007-09-05-----------------------------------------------------------------------------------
			
			
			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";");
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){;"); 
			out.println("    m_sql = \"m_help_TXT_FinanceSql_new2\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,m_sql,'6');"); 
			out.println("}");
			out.println("else if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql_new\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,m_sql,'6');"); 
		
			out.println("}");

			out.println("}");	
			
			out.println("function help_value_assign_6(oBj) {"); 
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){;"); 
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}");
			out.println("else if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}");
			out.println("}");
			
			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			//out.println("    m_sql = \"ClientSql1\";"); 
			//out.println("    m_sql = \"ClientSql1_new\";");//Mod by sandun on 25-05-2009 // commented by udara on 05-10-2012
			out.println("    m_sql = \"ClientSql1_new2\";"); // added by udara on 05-10-2012
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			//out.println("    m_sql = \"ClientSql2\";"); 
			//out.println("    m_sql = \"ClientSql_Lease\";"); 
			out.println("    m_sql = \"ClientSql_Lease_new\";");//Mod by sandun on 25-05-2009 // commented by udara on 05-10-2012
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("}");
			out.println("    HelpBox('1','10','0',Crit,m_sql,'4');"); 
	
			out.println("}");
			
			
			// added by udara on 09-10-2012
			
			out.println("function help_button_41() {"); 
			out.println("    document.Form1.hid_help_type.value=\"41\";"); 
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("    var user_location = '"+user_location_set+"'; ");
			out.println("    m_sql = \"ClientSql1_new3\";"); 
			//out.println("    Crit = document.Form1.TXT_CONTRACT_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); // commented by udara on 03-12-2012
			out.println("    Crit = document.Form1.TXT_CONTRACT_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+user_location+\"@\";"); // added by udara on 04-12-2012
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ "); 
			
			out.println("    var user_location = '"+user_location_set+"'; "); // out.println(" alert('"+user_location_set+"'); ");
			
			out.println("    m_sql = \"ClientSql_Lease_new1\";");
			//out.println("    Crit = document.Form1.TXT_CONTRACT_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); // commented by udara on 03-12-2012
			out.println("    Crit = document.Form1.TXT_CONTRACT_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+user_location+\"@\";");  // added by udara on 04-12-2012
			out.println("}");
			out.println("    HelpBox('1','10','0',Crit,m_sql,'41');"); 
			out.println("}");
			
			out.println("function help_value_assign_41(oBj) {"); 
			
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
			out.println("   document.Form1.TXT_CONTRACT_NO.value=oBj.valout[12];");
			out.println("}");
			
			out.println(" else if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("   document.Form1.TXT_CONTRACT_NO.value=oBj.valout[2];");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];");
			out.println("}");
			
			out.println("view()");
			out.println("}");
			
			// end by udara on 09-10-2012
			
			
			out.println("function help_value_assign_4(oBj) {"); 
			
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
			out.println("}");
			
			out.println(" else if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];");
			out.println("}");
			
			out.println("view()"); // commented by udara on 17-12-2012 // released by udara on 13-09-2013
			
			// commented by udara on 13-09-2013
			/*
			out.println("if(document.Form1.TXT_CONTRACT_NO.value!='')");
			out.println("   view()");
			out.println("else");
			out.println("   alert('Enter the contract number');");
			*/
			
			out.println("}");
			
			
			/*out.println("function val_user(row_No){");
			out.println("assignState('M2')");
			out.println("document.Form1.hid_row_no.value=row_No;");
			out.println("m_user_val=\"TXT_USER\"+row_No");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_User&data_val=\"+document.Form1.elements[m_user_val].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
		//	out.println("window.open(m_url);");
			out.println("}"); 
			*/
			
			
			out.println("function makeRequest1(obj) {");
			out.println("if(document.Form1.hid_chk_status.value==\"G4\"){");//CLIENT CODE
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_client&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			
			out.println("else if(document.Form1.hid_chk_status.value==\"G1\"){");//COLLECTION OFFICER
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_User&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_Assign_Collection_Officer&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("}");
			
			out.println("else if(document.Form1.hid_chk_status.value==\"G2\"){");//NEW COLLECTION OFFICER
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_assign_user_valiate&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_COLL_OFFICER.value+\"&ac_status=Y\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_Assign_Collection_Officer_New&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_COLL_OFFICER.value+\"&ac_status=Y\";");
			
			out.println("}");
			
			out.println("if(document.Form1.hid_chk_status.value==\"G5\"){");//FINANCE NO
			out.println(" if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance_new&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");		
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
	
			out.println("function view(){");
			out.println("if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			//out.println("get_Application_numbers('APPLICATION_NO','ASC')	"); // commented by udara on 13-09-2013
			out.println("show_data_by_finanace_no('APPLICATION_NO','ASC');"); // added by udara on 13-09-2013
			out.println("}");
			
			out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
			out.println("show_data_by_finanace_no('APPLICATION_NO','ASC');"); //document.Form1.TXT_FINANCE_NO.value,
			out.println("}");	
			out.println("}");
			
			
			
			out.println("function view_collection_officer_data(){");
			
			//out.println("if (document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println("get_data_col_officer('APPLICATION_NO','ASC')	");
			//out.println("}");
		//	out.println(" if (document.Form1.SCREEN_NAME.value=='EDIT'){ ");
		//	out.println("show_data_by_finanace_no('APPLICATION_NO','ASC');"); //document.Form1.TXT_FINANCE_NO.value,
		//	out.println("}");	
			out.println("}");
			
			
			//-------------------------------------------------------------------------------------------------------------------------------------

   
			out.println("function  change_type(){");
			out.println("m_table.innerHTML=\"\" ");
			out.println("m_table_main.innerHTML=\"\" ");
			
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"I\"){");
			    
			out.println("m_write='<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CLIENT  class=div_input>Client Code</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_CLIENT_CODE maxlength=25 size=15 onblur=assignState(\"G4\"),makeRequest1(this)>&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_CLIENT value=\" ... \" onClick=\"help_button_4()\"></td>'+"); 
			//out.println("        '<td width=\"10%\"><input class=but_input type=button name=BUT_VIEW value=\"View\" onClick=\"view()\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;</td>'+"); 
			out.println("        '</tr>';"); 
			
			// added below by udara on 09-10-2012
			
			out.println("m_write= m_write + '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CONTRACT_NO  class=div_input>Contract No.</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_CONTRACT_NO maxlength=25 size=15 >&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_CONTRACT value=\" ... \" onClick=\"help_button_41()\"></td>'+"); 
			//out.println("        '<td width=\"10%\"><input class=but_input type=button name=BUT_VIEW value=\"View\" onClick=\"view()\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;</td>'+"); 
			out.println("        '</tr>';"); 
			
			// end by udara on 09-10-2012
					
			out.println("m_table_main.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
			out.println("m_write+'</table>';");
			
			out.println("}");
			out.println("else if(document.Form1.TXT_ASSIGN_TYPE.value==\"B\"){");
			
			out.println("m_write='<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_COLL_OFFICER  class=div_input>Collection Officer</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_COLL_OFFICER maxlength=15 size=15 onblur=assignState(\"G1\"),makeRequest1(this) >&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_COLL_OFFICER value=\" ... \" onClick=\"help_button_user_col()\"></td>'+"); 
			//out.println("        '<td width=\"10%\"><input class=but_input type=button name=BUT_VIEW value=\"View\" onClick=\"view_collection_officer_data()\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;</td>'+"); 
			out.println("        '</tr>';"); 
			
			out.println("m_write1='<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_NEW_COLL_OFFICER  class=div_input>New Collection Officer</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_NEW_COLL_OFFICER maxlength=15 size=15 onblur=assignState(\"G2\"),makeRequest1(this) disabled>&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_NEW_COLL_OFFICER value=\" ... \" onClick=\"help_button_user_col_new()\" disabled></td>'+"); 
			out.println("      '<td width=\"*%\">&nbsp;</td>'+"); 
			out.println("        '</tr>';"); 
			
			out.println("m_write2='<tr >'+"); // added by Sandun on28/07/2008			
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_REMARK_NEW class=div_input>Remark</DIV></td>'+"); 
			//out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_REMARK_NEW maxlength=500 size=15>&nbsp;&nbsp;'+"); 
			out.println("        '<td width=\"*%\"><textarea class=txt_input style=\"width:300px; height:50px;\" name=TXT_REMARK_NEW maxlength=500 size=500></textarea>&nbsp;&nbsp;'+");
			out.println("        '<input class=but_input type=button text-align=center name=BUT_APPLY_ALL value=\" Apply All\" onClick=\"but_app_all()\"</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;</td>'+");
			out.println("       '<br>'+");
			out.println("        '</tr>';"); 
			
			
			//out.println("m_table_main.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
			//out.println("m_write+m_write1+'</table>';");
			out.println("m_table_main.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
			out.println("m_write+'</table><br>';");
			
			out.println("m_table_main.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
			out.println("m_write1+'</table>';");
			
			out.println("m_table_main.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
			out.println("m_write2+'</table>';");// added by Sandun on28/07/2008
			
			out.println("}");	
			
			
			out.println("}");
			
			
			out.println("function but_app_all(){");					
			out.println("for(i=0;i<arr_size;i++){"); 
			out.println("txt_remark=\"TXT_REMARK\"+i");
			out.println("document.Form1.elements[txt_remark].value=document.Form1.TXT_REMARK_NEW.value;"); 
			out.println("}"); 
			
			out.println("}");
			
			

      out.println("</Script>");
			
			
				
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"change_type()\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
				  				
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_LEASE_ASSIGN\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
					
					
										
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Assign Collection Officer - New</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//commented by delanjali on 2007-09-05 for ref 845
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\"),edit_data()' value=\"Edit\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
		
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					//out.println("<td width='10%'></td>");  
					//out.println("<td width='10%'></td>");  
					//out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
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
					
					//--added by delanjali for ref 845 on 2007-09-05-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			    
					out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding=0>"); 
									
	        //added by nuwan de silva on 18-10-07--------------				
					out.println("<tr >"); 
					out.println("<td width='20%' >Assign Type</td>"); 
					out.println("<td width='30%' ><select class='txt_input' type='text' name='TXT_ASSIGN_TYPE' onChange=\"change_type()\" maxlength='1' size='1'>");  
					out.println("<option value='I' selected>Individual</option>");			
					out.println("<option value='B' >Bulk</option>");		
					out.println("</select>");
					out.println("</td>");
					out.println("<td width='*%'>&nbsp;</td>"); 
					out.println("</tr>"); 
					out.println("</table>");  
					
					out.println("<br>"); 
					out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding=0 >"); 
					out.println("<tr>");  
			    out.println("<td ><DIV ID='m_table_main'></DIV></td>"); //width=\"100%\"
		      out.println("</tr>"); 
					
					
					/*out.println("<tr >"); 
					out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT'  class=div_input>Client Code</DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='15' size='15' onblur=\"assignState('G4'),makeRequest1(document.Form1.TXT_CLIENT_CODE)\">"); 
					out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_CLIENT' value=\" Help \" onClick=\"help_button_4()\"></td>"); 
					out.println("<td width='10%'><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view()\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					*/
					
		
		/*		out.println("<tr >"); 
					out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No</DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assignState('G5'),makeRequest1(document.Form1.TXT_FINANCE_NO)\">"); 
					out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_FINANCE' value=\" Help \" onClick=\"help_button_6()\"></td>"); 
					out.println("<td width='10%'><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view()\"></td>"); 
					out.println("<td width='*%'></td>"); 				
					out.println("</tr>"); 
	*/				
					out.println("</table>");  
					//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         	out.println("<table align='center' width='100%' class='table'>"); 
			   			   
									
					out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table_top'></DIV></td>");
		     out.println("</tr>"); 

				 out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
					
					out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table_end'></DIV></td>");
		     out.println("</tr>"); 

						  					
				 out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table2'></DIV></td>");
		     out.println("</tr>"); 
					
				 out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table_top_2'></DIV></td>");
		     out.println("</tr>"); 
					
				 out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table3'></DIV></td>");
		     out.println("</tr>"); 
					
					out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table_end_2'></DIV></td>");
		     out.println("</tr>"); 	
	
			
			   out.println("</table>");
					
					
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//commented by delanjali on 2007-09-05 for ref 845
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\"),edit_data()' value=\"Edit\"></td>");  
					
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
		
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					//out.println("<td width='10%'></td>");  
					//out.println("<td width='10%'></td>");  
					//out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					
					out.println("</td></tr>");  
					
				
		
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

				out.println("</html>");
  //    }
			
			//=========================================================================================================================			
  	
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
