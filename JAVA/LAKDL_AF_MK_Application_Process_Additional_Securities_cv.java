//Created by -Thamali Jayatunga on 2010.02.22
//Collection - Application_Process_Additional_Securities
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MK_Application_Process_Additional_Securities_cv extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1,stmt2,stmt3,stmt4;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3,rs4;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_ac_status;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			String m_sys_dd ="",m_sys_mm ="",m_sys_yy ="";

			out = res.getOutputStream();
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			String m_chksql=req.getParameter("chksql");

			if(m_chksql.equals("main_page")){ 

			
			String m_application_no = req.getParameter("APP_NO");
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL");
			boolean more = rs.next();
			if(more){
					m_sys_dd = rs.getString(1);
					m_sys_mm = rs.getString(2);
					m_sys_yy = rs.getString(3);
			}

     
      out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
				
			out.println("var app_no='"+m_application_no+"';");

				
			out.println("function get_vector(data_vec) {");
			
			out.println(" if(document.Form1.hid_type.value!=\"check_vehicle\"){  "); ///////
			
			out.println("   if(document.Form1.TXT_ASSIGN_TYPE.value==\"V\"){");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_VEHICLE.value!=\"\" ){"); 
			out.println("               help_vehicle();");
			out.println("			}");
			out.println("	}");//end of vehicle
			out.println("   else if(document.Form1.TXT_ASSIGN_TYPE.value==\"L\"){");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_CAV_NO.value!=\"\" ){"); 
			out.println("              help_mortgage();");
			out.println("			}");
			out.println("	}");//end of land
			out.println("   else if(document.Form1.TXT_ASSIGN_TYPE.value==\"F\"){");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_AC_NO.value!=\"\" ){"); 
			out.println("               help_fixed_deposit();");
			out.println("			}");
			out.println("	}");//end of fixed deposit
			
			out.println("}"); ///////
			
			
			out.println("if(document.Form1.hid_type.value==\"vehicle\" && document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"V\"");
  		    out.println("   change_type();");
			out.println("   document.Form1.TXT_VEHICLE.value=data_vec[0];"); 
			out.println("   document.Form1.TXT_CLIENT.value=data_vec[1];"); 
			out.println("   var reg_date=data_vec[2];"); 
			out.println("   document.Form1.TXT_REG_DD.value=reg_date.substring(0,2);");
			out.println("   document.Form1.TXT_REG_MM.value=reg_date.substring(3,5);");
			out.println("   document.Form1.TXT_REG_YY.value=reg_date.substring(6,10);");
			out.println("   document.Form1.TXT_VEHICLE_TYPE.value=data_vec[3];"); 
			out.println("   document.Form1.TXT_VEHICLE_MAKE.value=data_vec[4];"); 
			out.println("   document.Form1.TXT_MANUFAC.value=data_vec[5];"); 
			out.println("   document.Form1.TXT_CAPACITY.value=data_vec[6];"); 
			out.println("   document.Form1.TXT_CHASIS.value=data_vec[7];"); 
			out.println("   document.Form1.TXT_ENGINE.value=data_vec[8];"); 
			out.println("   document.Form1.TXT_EXTEND.value=data_vec[9];"); 
			out.println("   document.Form1.TXT_FUEL.value=data_vec[10];"); 
			out.println("   document.Form1.TXT_ADD.value=data_vec[11];"); 
			out.println("   var cr_date=data_vec[12];"); 
			out.println("   document.Form1.TXT_CR_DD.value=cr_date.substring(0,2);");
			out.println("   document.Form1.TXT_CR_MM.value=cr_date.substring(3,5);");
			out.println("   document.Form1.TXT_CR_YY.value=cr_date.substring(6,10);");
			out.println("   document.Form1.TXT_PROVINCE.value=data_vec[13];"); 
			out.println("   document.Form1.TXT_CR.value=data_vec[14];");
			out.println("   document.Form1.TXT_VALUER.value=data_vec[15];"); 
			out.println("   document.Form1.TXT_VALUER_NAME.value=data_vec[16];"); 
			out.println("   var val_date=data_vec[17];"); 
			out.println("   document.Form1.TXT_VAL_DD.value=val_date.substring(0,2);");
			out.println("   document.Form1.TXT_VAL_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_VAL_YY.value=val_date.substring(6,10);");
			out.println("   document.Form1.TXT_VALUE.value=data_vec[18];"); 
			out.println("   document.Form1.TXT_SALES_VALUE.value=data_vec[19];"); 
			out.println("   document.Form1.TXT_USAGE.value=data_vec[20];"); 
			out.println("   document.Form1.TXT_CONDITION.value=data_vec[21];"); 
			out.println("   document.Form1.TXT_NOTES.value=data_vec[22];");
			out.println("   document.Form1.TXT_REMARKS.value=data_vec[23];"); 
			out.println("   document.Form1.TXT_BODY.value=data_vec[24];");
			out.println("}");
			out.println("else if(document.Form1.hid_type.value==\"land\" && document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"L\"");
  		    out.println("   change_type();");
			out.println("   document.Form1.TXT_CAV_NO.value=data_vec[0];"); 
			out.println("   document.Form1.TXT_MORTGAGE.value=data_vec[1];"); 
			out.println("   document.Form1.TXT_DEED_NO.value=data_vec[2];"); 
			out.println("   document.Form1.TXT_LAND_ADD.value=data_vec[3];"); 
			out.println("   document.Form1.TXT_LAND_VALUE.value=data_vec[4];"); 
			out.println("   document.Form1.TXT_VAL_NAME.value=data_vec[5];"); 
			out.println("   val_date=data_vec[6];"); 
			out.println("   document.Form1.TXT_VAL_DD.value=val_date.substring(0,2);");
  		    out.println("   document.Form1.TXT_VAL_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_VAL_YY.value=val_date.substring(6,10);");
			out.println("   document.Form1.TXT_REMARKS.value=data_vec[7];"); 
			out.println("}");

			out.println("else if(document.Form1.hid_type.value==\"fixed_deposit\" && document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"F\"");
			out.println("   change_type();");
			out.println("   document.Form1.TXT_AC_NO.value=data_vec[0];"); 
			out.println("   document.Form1.TXT_DEP_AMT.value=data_vec[1];"); 
			out.println("   val_date=data_vec[2];"); 
			out.println("   document.Form1.TXT_ST_DD.value=val_date.substring(0,2);");
  		    out.println("   document.Form1.TXT_ST_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_ST_YY.value=val_date.substring(6,10);");
			out.println("   val_date=data_vec[3];"); 
			out.println("   document.Form1.TXT_MATU_DD.value=val_date.substring(0,2);");
  		    out.println("   document.Form1.TXT_MATU_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_MATU_YY.value=val_date.substring(6,10);");
			out.println("   val_date=data_vec[4];"); 
			out.println("   document.Form1.TXT_INTER_DD.value=val_date.substring(0,2);");
  		    out.println("   document.Form1.TXT_INTER_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_INTER_YY.value=val_date.substring(6,10);");
			out.println("   document.Form1.TXT_INTER_PAY.value=data_vec[5];"); 
			out.println("   document.Form1.TXT_PERIOD.value=data_vec[6];"); 
			out.println("   document.Form1.TXT_REMARKS.value=data_vec[7];"); 
			out.println("}");
			
			
			// added by udara 03-04-2014
			out.println("else if(document.Form1.hid_type.value==\"running_contract\" && document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"R\"");
			out.println("   change_type();");
			out.println("   document.Form1.TXT_FIN_NO.value=data_vec[0];"); 
			out.println("}");
			// end by udara 03-04-2014

			//added by kasun on 29-11-2024
			out.println("else if(document.Form1.hid_type.value==\"hadagasma_product\" && document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"H\"");
			out.println("   change_type();");
			out.println("   document.Form1.TXT_ITEM_SUB_CAT.value=data_vec[0];"); 
			out.println("   document.Form1.TXT_REMARKS.value=data_vec[1];"); 
			out.println("}");
			
			// added by udara 21-10-2014
			
			out.println(" if(document.Form1.hid_type.value==\"vehicle\" && document.Form1.SCREEN_NAME.value==\"DELETE\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"V\"");
  		    out.println("   change_type();");
			out.println("   document.Form1.TXT_VEHICLE.value=data_vec[0];"); 
			out.println("   document.Form1.TXT_CLIENT.value=data_vec[1];"); 
			out.println("   var reg_date=data_vec[2];"); 
			out.println("   document.Form1.TXT_REG_DD.value=reg_date.substring(0,2);");
			out.println("   document.Form1.TXT_REG_MM.value=reg_date.substring(3,5);");
			out.println("   document.Form1.TXT_REG_YY.value=reg_date.substring(6,10);");
			out.println("   document.Form1.TXT_VEHICLE_TYPE.value=data_vec[3];"); 
			out.println("   document.Form1.TXT_VEHICLE_MAKE.value=data_vec[4];"); 
			out.println("   document.Form1.TXT_MANUFAC.value=data_vec[5];"); 
			out.println("   document.Form1.TXT_CAPACITY.value=data_vec[6];"); 
			out.println("   document.Form1.TXT_CHASIS.value=data_vec[7];"); 
			out.println("   document.Form1.TXT_ENGINE.value=data_vec[8];"); 
			out.println("   document.Form1.TXT_EXTEND.value=data_vec[9];"); 
			out.println("   document.Form1.TXT_FUEL.value=data_vec[10];"); 
			out.println("   document.Form1.TXT_ADD.value=data_vec[11];"); 
			out.println("   var cr_date=data_vec[12];"); 
			out.println("   document.Form1.TXT_CR_DD.value=cr_date.substring(0,2);");
			out.println("   document.Form1.TXT_CR_MM.value=cr_date.substring(3,5);");
			out.println("   document.Form1.TXT_CR_YY.value=cr_date.substring(6,10);");
			out.println("   document.Form1.TXT_PROVINCE.value=data_vec[13];"); 
			out.println("   document.Form1.TXT_CR.value=data_vec[14];");
			out.println("   document.Form1.TXT_VALUER.value=data_vec[15];"); 
			out.println("   document.Form1.TXT_VALUER_NAME.value=data_vec[16];"); 
			out.println("   var val_date=data_vec[17];"); 
			out.println("   document.Form1.TXT_VAL_DD.value=val_date.substring(0,2);");
			out.println("   document.Form1.TXT_VAL_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_VAL_YY.value=val_date.substring(6,10);");
			out.println("   document.Form1.TXT_VALUE.value=data_vec[18];"); 
			out.println("   document.Form1.TXT_SALES_VALUE.value=data_vec[19];"); 
			out.println("   document.Form1.TXT_USAGE.value=data_vec[20];"); 
			out.println("   document.Form1.TXT_CONDITION.value=data_vec[21];"); 
			out.println("   document.Form1.TXT_NOTES.value=data_vec[22];");
			out.println("   document.Form1.TXT_REMARKS.value=data_vec[23];"); 
			out.println("   document.Form1.TXT_BODY.value=data_vec[24];");
			out.println("}");
			
			out.println("else if(document.Form1.hid_type.value==\"land\" && document.Form1.SCREEN_NAME.value==\"DELETE\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"L\"");
  		    out.println("   change_type();");
			out.println("   document.Form1.TXT_CAV_NO.value=data_vec[0];"); 
			out.println("   document.Form1.TXT_MORTGAGE.value=data_vec[1];"); 
			out.println("   document.Form1.TXT_DEED_NO.value=data_vec[2];"); 
			out.println("   document.Form1.TXT_LAND_ADD.value=data_vec[3];"); 
			out.println("   document.Form1.TXT_LAND_VALUE.value=data_vec[4];"); 
			out.println("   document.Form1.TXT_VAL_NAME.value=data_vec[5];"); 
			out.println("   val_date=data_vec[6];"); 
			out.println("   document.Form1.TXT_VAL_DD.value=val_date.substring(0,2);");
  		    out.println("   document.Form1.TXT_VAL_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_VAL_YY.value=val_date.substring(6,10);");
			out.println("   document.Form1.TXT_REMARKS.value=data_vec[7];"); 
			out.println("}");

			out.println("else if(document.Form1.hid_type.value==\"fixed_deposit\" && document.Form1.SCREEN_NAME.value==\"DELETE\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"F\"");
			out.println("   change_type();");
			out.println("   document.Form1.TXT_AC_NO.value=data_vec[0];"); 
			out.println("   document.Form1.TXT_DEP_AMT.value=data_vec[1];"); 
			out.println("   val_date=data_vec[2];"); 
			out.println("   document.Form1.TXT_ST_DD.value=val_date.substring(0,2);");
  		    out.println("   document.Form1.TXT_ST_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_ST_YY.value=val_date.substring(6,10);");
			out.println("   val_date=data_vec[3];"); 
			out.println("   document.Form1.TXT_MATU_DD.value=val_date.substring(0,2);");
  		    out.println("   document.Form1.TXT_MATU_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_MATU_YY.value=val_date.substring(6,10);");
			out.println("   val_date=data_vec[4];"); 
			out.println("   document.Form1.TXT_INTER_DD.value=val_date.substring(0,2);");
  		    out.println("   document.Form1.TXT_INTER_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_INTER_YY.value=val_date.substring(6,10);");
			out.println("   document.Form1.TXT_INTER_PAY.value=data_vec[5];"); 
			out.println("   document.Form1.TXT_PERIOD.value=data_vec[6];"); 
			out.println("   document.Form1.TXT_REMARKS.value=data_vec[7];"); 
			out.println("}");

			out.println("else if(document.Form1.hid_type.value==\"running_contract\" && document.Form1.SCREEN_NAME.value==\"DELETE\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"R\"");
			out.println("   change_type();");
			out.println("   document.Form1.TXT_FIN_NO.value=data_vec[0];"); 
			out.println("}");
			
			// end by udara 21-10-2014

			//added by kasun on 29-11-2024
			out.println("else if(document.Form1.hid_type.value==\"hadagasma_product\" && document.Form1.SCREEN_NAME.value==\"DELETE\"){");
			out.println("   document.Form1.TXT_ASSIGN_TYPE.value=\"H\"");
			out.println("   change_type();");
			out.println("   document.Form1.TXT_ITEM_SUB_CAT.value=data_vec[0];"); 
			out.println("   document.Form1.TXT_REMARKS.value=data_vec[1];");  
			out.println("}");
			
			
			
			// added by udara on 30-07-2013
			out.println("			else if(data_vec.length>0 && document.Form1.hid_type.value==\"check_vehicle\" && document.Form1.SCREEN_NAME.value!=\"EDIT\"){ ");
			//out.println("			   alert(data_vec[0]); "); 
			out.println("              if(data_vec[0]=='Y' && document.Form1.hid_type_2.value == '1'){ ");
			out.println("			      alert('Chasis number is already exsisting'); "); 
			out.println("			      document.Form1.TXT_CHASIS.value=''; "); 
			out.println("			   }");
			out.println("              else if(data_vec[0]=='Y' && document.Form1.hid_type_2.value == '2'){ ");
			out.println("			      alert('Vehicle number is already exsisting'); ");
			out.println("			      document.Form1.TXT_VEHICLE.value=''; "); 
			out.println("			   }");
			out.println("			}");

			// end by udara on 30-07-2013
			
			

			out.println("			}");
			
			//out.println("function assignState(val){");
			//out.println("document.Form1.hid_chk_status.value=val");
			//out.println("}");

			out.println("function makeRequest(obj) {");
  		out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"V\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_sec_vehicle&data_val=\"+obj.value+\"&data_val2=\"+app_no;");
			out.println("}");
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"L\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_sec_land&data_val=\"+obj.value+\"&data_val2=\"+app_no;");
			out.println("}");
  		out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"F\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_sec_fixed_deposit&data_val=\"+obj.value+\"&data_val2=\"+app_no;");
			out.println("}");

			out.println("load_interface(m_url,'XML');");
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
		
		  out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_vehicle();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_valuer();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_mortgage();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		help_value_assign_fixed_deposit();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"5\"){"); 
			out.println("		help_value_assign_province();"); 
	  	out.println("		}"); 
			
			// added by udara 03-04-2014
			out.println("		if(IfCount==\"6\"){"); 
			out.println("		help_value_assign_fin_no();"); 
	  	    out.println("		}"); 
			
			// end by udara 03-04-2014
		
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
			out.println("	clear_data();");
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
			
			
			// added by udara 03-04-2014
			
			out.println("function help_finance_no() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_FinanceSql_new1\";"); 
			out.println("    m_criteria = document.Form1.TXT_FIN_NO.value+\"@\"+\"ACTIVATED\"+\"@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'6');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_fin_no() {"); 
			out.println("   document.Form1.TXT_FIN_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			// end by udara 03-04-2014
			
			out.println("function help_vehicle() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_VEHICLE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_VEHICLE.value+\"@\"+app_no+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'1');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_valuer() {"); 
      out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_VALUER_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_VALUER.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'2');"); 
			out.println("}"); 
			
			out.println("function help_mortgage() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_MORTGAGE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CAV_NO.value+\"@\"+app_no+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'3');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_fixed_deposit() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_FD_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_AC_NO.value+\"@\"+app_no+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'4');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_province() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_PROVINCE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PROVINCE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'5');"); 
			out.println("}"); 
			out.println(""); 

			
			out.println("function help_value_assign_vehicle() {"); 
			out.println("   document.Form1.TXT_VEHICLE.value=oBj.valout[2];"); 
			out.println("   document.Form1.TXT_CLIENT.value=oBj.valout[3];"); 
			out.println("   reg_date=oBj.valout[4];"); 
			out.println("   document.Form1.TXT_REG_DD.value=reg_date.substring(0,2);");
			out.println("   document.Form1.TXT_REG_MM.value=reg_date.substring(3,5);");
			out.println("   document.Form1.TXT_REG_YY.value=reg_date.substring(6,10);");
			out.println("   document.Form1.TXT_VEHICLE_TYPE.value=oBj.valout[5];"); 
			out.println("   document.Form1.TXT_VEHICLE_MAKE.value=oBj.valout[6];"); 
			out.println("   document.Form1.TXT_MANUFAC.value=oBj.valout[7];"); 
			out.println("   document.Form1.TXT_CAPACITY.value=oBj.valout[8];"); 
			out.println("   document.Form1.TXT_CHASIS.value=oBj.valout[9];"); 
			out.println("   document.Form1.TXT_ENGINE.value=oBj.valout[10];"); 
			out.println("   document.Form1.TXT_EXTEND.value=oBj.valout[11];"); 
			out.println("   document.Form1.TXT_FUEL.value=oBj.valout[12];"); 
			out.println("   document.Form1.TXT_ADD.value=oBj.valout[13];"); 
			out.println("   cr_date=oBj.valout[14];"); 
			out.println("   document.Form1.TXT_CR_DD.value=cr_date.substring(0,2);");
			out.println("   document.Form1.TXT_CR_MM.value=cr_date.substring(3,5);");
			out.println("   document.Form1.TXT_CR_YY.value=cr_date.substring(6,10);");
			out.println("   document.Form1.TXT_PROVINCE.value=oBj.valout[15];"); 
			out.println("   document.Form1.TXT_CR.value=oBj.valout[16];");
			out.println("   document.Form1.TXT_VALUER.value=oBj.valout[17];"); 
			out.println("   document.Form1.TXT_VALUER_NAME.value=oBj.valout[18];"); 
			out.println("   val_date=oBj.valout[19];"); 
			out.println("   document.Form1.TXT_VAL_DD.value=val_date.substring(0,2);");
			out.println("   document.Form1.TXT_VAL_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_VAL_YY.value=val_date.substring(6,10);");
			out.println("   document.Form1.TXT_VALUE.value=oBj.valout[20];"); 
			out.println("   document.Form1.TXT_SALES_VALUE.value=oBj.valout[21];"); 
			out.println("   document.Form1.TXT_USAGE.value=oBj.valout[22];"); 
			out.println("   document.Form1.TXT_CONDITION.value=oBj.valout[23];"); 
			out.println("   document.Form1.TXT_NOTES.value=oBj.valout[24];");
			out.println("   document.Form1.TXT_REMARKS.value=oBj.valout[25];"); 
			out.println("   document.Form1.TXT_BODY.value=oBj.valout[26];");
			out.println("}");

			out.println("function help_value_assign_valuer() {"); 
			out.println("   document.Form1.TXT_VALUER.value=oBj.valout[2];"); 
			out.println("   document.Form1.TXT_VALUER_NAME.value=oBj.valout[12];"); 
			out.println("}");

			out.println("function help_value_assign_mortgage() {"); 
			out.println("   document.Form1.TXT_CAV_NO.value=oBj.valout[2];"); 
			out.println("   document.Form1.TXT_MORTGAGE.value=oBj.valout[3];"); 
			out.println("   document.Form1.TXT_DEED_NO.value=oBj.valout[4];"); 
			out.println("   document.Form1.TXT_LAND_ADD.value=oBj.valout[5];"); 
			out.println("   document.Form1.TXT_LAND_VALUE.value=oBj.valout[6];"); 
			out.println("   document.Form1.TXT_VAL_NAME.value=oBj.valout[7];"); 
			out.println("   val_date=oBj.valout[8];"); 
			out.println("   document.Form1.TXT_VAL_DD.value=val_date.substring(0,2);");
  		out.println("   document.Form1.TXT_VAL_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_VAL_YY.value=val_date.substring(6,10);");
			out.println("   document.Form1.TXT_REMARKS.value=oBj.valout[9];"); 
			out.println("}");

			out.println("function help_value_assign_fixed_deposit() {"); 
			out.println("   document.Form1.TXT_AC_NO.value=oBj.valout[2];"); 
			out.println("   document.Form1.TXT_DEP_AMT.value=oBj.valout[3];"); 
			out.println("   val_date=oBj.valout[4];"); 
			out.println("   document.Form1.TXT_ST_DD.value=val_date.substring(0,2);");
  		out.println("   document.Form1.TXT_ST_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_ST_YY.value=val_date.substring(6,10);");
			out.println("   val_date=oBj.valout[5];"); 
			out.println("   document.Form1.TXT_MATU_DD.value=val_date.substring(0,2);");
  		out.println("   document.Form1.TXT_MATU_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_MATU_YY.value=val_date.substring(6,10);");
			out.println("   val_date=oBj.valout[6];"); 
			out.println("   document.Form1.TXT_INTER_DD.value=val_date.substring(0,2);");
  		out.println("   document.Form1.TXT_INTER_MM.value=val_date.substring(3,5);");
			out.println("   document.Form1.TXT_INTER_YY.value=val_date.substring(6,10);");
			out.println("   document.Form1.TXT_INTER_PAY.value=oBj.valout[7];"); 
			out.println("   document.Form1.TXT_PERIOD.value=oBj.valout[8];"); 
			out.println("   document.Form1.TXT_REMARKS.value=oBj.valout[9];"); 
			out.println("}");

			out.println("function help_value_assign_province() {"); 
			out.println("   document.Form1.TXT_PROVINCE.value=oBj.valout[3];"); 
			out.println("}");

			out.println("function clear_data() {");
			
			out.println("if(document.Form1.hid_help_type.value==\"1\"){");
			out.println("   document.Form1.TXT_VEHICLE.value=\"\";"); 
			out.println("   document.Form1.TXT_CLIENT.value=\"\";"); 
			out.println("   document.Form1.TXT_REG_DD.value='"+m_sys_dd+"';");
			out.println("   document.Form1.TXT_REG_MM.value='"+m_sys_mm+"';");
			out.println("   document.Form1.TXT_REG_YY.value='"+m_sys_yy+"';");
			out.println("   document.Form1.TXT_VEHICLE_TYPE.value=\"\";"); 
			out.println("   document.Form1.TXT_VEHICLE_MAKE.value=\"\";"); 
			out.println("   document.Form1.TXT_MANUFAC.value=\"\";"); 
			out.println("   document.Form1.TXT_CAPACITY.value=\"\";"); 
			out.println("   document.Form1.TXT_CHASIS.value=\"\";"); 
			out.println("   document.Form1.TXT_ENGINE.value=\"\";"); 
			out.println("   document.Form1.TXT_EXTEND.value=\"\";"); 
			out.println("   document.Form1.TXT_FUEL.value=\"\";"); 
			out.println("   document.Form1.TXT_ADD.value=\"\";"); 
			out.println("   document.Form1.TXT_CR_DD.value='"+m_sys_dd+"';");
			out.println("   document.Form1.TXT_CR_MM.value='"+m_sys_mm+"';");
			out.println("   document.Form1.TXT_CR_YY.value='"+m_sys_yy+"';");
			out.println("   document.Form1.TXT_PROVINCE.value=\"\";"); 
			out.println("   document.Form1.TXT_CR.value=\"\";");
			out.println("   document.Form1.TXT_VALUER.value=\"\";"); 
			out.println("   document.Form1.TXT_VALUER_NAME.value=\"\";"); 
			out.println("   document.Form1.TXT_VAL_DD.value='"+m_sys_dd+"';");
			out.println("   document.Form1.TXT_VAL_MM.value='"+m_sys_mm+"';");
			out.println("   document.Form1.TXT_VAL_YY.value='"+m_sys_yy+"';");
			out.println("   document.Form1.TXT_VALUE.value=\"\";"); 
			out.println("   document.Form1.TXT_SALES_VALUE.value=\"\";"); 
			out.println("   document.Form1.TXT_USAGE.value=\"\";"); 
			out.println("   document.Form1.TXT_CONDITION.value=\"N\";"); 
			out.println("   document.Form1.TXT_NOTES.value=\"\";");
			out.println("   document.Form1.TXT_REMARKS.value=\"\";"); 
			out.println("   document.Form1.TXT_BODY.value=\"\";");
			out.println("}");
			
			out.println("if(document.Form1.hid_help_type.value==\"2\"){");
			out.println("   document.Form1.TXT_VALUER.value=\"\";"); 
			out.println("   document.Form1.TXT_VALUER_NAME.value=\"\";"); 
      out.println("}");
				
			out.println("if(document.Form1.hid_help_type.value==\"3\"){");
			out.println("   document.Form1.TXT_CAV_NO.value=\"\";"); 
			out.println("   document.Form1.TXT_MORTGAGE.value=\"P\";"); 
			out.println("   document.Form1.TXT_DEED_NO.value=\"\";"); 
			out.println("   document.Form1.TXT_LAND_ADD.value=\"\";"); 
			out.println("   document.Form1.TXT_LAND_VALUE.value=\"\";"); 
			out.println("   document.Form1.TXT_VAL_NAME.value=\"\";"); 
			out.println("   document.Form1.TXT_VAL_DD.value='"+m_sys_dd+"';");
  		out.println("   document.Form1.TXT_VAL_MM.value='"+m_sys_mm+"';");
			out.println("   document.Form1.TXT_VAL_YY.value='"+m_sys_yy+"';");
			out.println("   document.Form1.TXT_REMARKS.value=\"\";"); 
      out.println("}");

			out.println("if(document.Form1.hid_help_type.value==\"4\"){");
			out.println("   document.Form1.TXT_AC_NO.value=\"\";"); 
			out.println("   document.Form1.TXT_DEP_AMT.value=\"\";"); 
			out.println("   document.Form1.TXT_ST_DD.value='"+m_sys_dd+"';");
  		out.println("   document.Form1.TXT_ST_MM.value='"+m_sys_mm+"';");
			out.println("   document.Form1.TXT_ST_YY.value='"+m_sys_yy+"';");
			out.println("   document.Form1.TXT_MATU_DD.value='"+m_sys_dd+"';");
  		out.println("   document.Form1.TXT_MATU_MM.value='"+m_sys_mm+"';");
			out.println("   document.Form1.TXT_MATU_YY.value='"+m_sys_yy+"';");
			out.println("   document.Form1.TXT_INTER_DD.value='"+m_sys_dd+"';");
  		out.println("   document.Form1.TXT_INTER_MM.value='"+m_sys_mm+"';");
			out.println("   document.Form1.TXT_INTER_YY.value='"+m_sys_yy+"';");
			out.println("   document.Form1.TXT_INTER_PAY.value=\"\";"); 
			out.println("   document.Form1.TXT_PERIOD.value=\"\";"); 
			out.println("   document.Form1.TXT_REMARKS.value=\"\";"); 
      out.println("}");

			out.println("if(document.Form1.hid_help_type.value==\"5\"){");
			out.println("   document.Form1.TXT_PROVINCE.value=\"\";"); 
      out.println("}");
		
		// added by udara 03-04-2014
		out.println("if(document.Form1.hid_help_type.value==\"6\"){");
			out.println("   document.Form1.TXT_FIN_NO.value=\"\";"); 
      out.println("}");
		// end by UDARA 03-04-2014

			out.println("}");
			
			
			out.println("function validate_data(){");
			
			
			// added by udara 03-04-2014
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"R\"){");
			out.println("  if(document.Form1.TXT_FIN_NO.value==\"\"){  "); 
			out.println("     DIV_TXT_FIN_NO.style.color='red';");
			out.println("     return false;"); 
			out.println("  }"); 
			out.println("  else{"); 
    	    out.println("     return true;"); 
			out.println("  }"); 
			out.println("}");
			// end by UDARA 03-04-2014
			

			//added by kasun on 29-11-2024
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"H\"){");
			out.println("  if(document.Form1.TXT_ITEM_SUB_CAT.value==\"\"){  "); 
			out.println("     DIV_TXT_ITEM_SUB_CAT.style.color='red';");
			out.println("     return false;"); 
			out.println("  }"); 
			out.println("  else{"); 
    	    out.println("     return true;"); 
			out.println("  }"); 
			out.println("}");
			
			
			
  		out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"V\"){");
			out.println("if(document.Form1.TXT_VEHICLE.value==\"\"){  "); 
			out.println("DIV_TXT_VEHICLE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_CLIENT.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_REG_DD.value==\"\" || document.Form1.TXT_REG_MM.value==\"\" || document.Form1.TXT_REG_YY.value==\"\"){  "); 
			out.println("DIV_TXT_REG_DATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_VALUE.value==\"\"){  "); 
			out.println("DIV_TXT_VALUE.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			out.println("else{"); 
    	out.println("return true;"); 
			out.println("}"); 
			out.println("}");
  		out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"L\"){");
			out.println("if(document.Form1.TXT_CAV_NO.value==\"\"){  "); 
			out.println("DIV_TXT_CAV_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_LAND_VALUE.value==\"\"){  "); 
			out.println("DIV_TXT_LAND_VALUE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
    	out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
  		out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"F\"){");
			out.println("if(document.Form1.TXT_AC_NO.value==\"\"){  "); 
			out.println("DIV_TXT_AC_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_DEP_AMT.value==\"\"){  "); 
			out.println("DIV_TXT_DEP_AMT.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			out.println("else{"); 
    	out.println("return true;"); 
			out.println("}"); 
			out.println("}");
			out.println("}");
			
			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			
			// commented by udara 22-10-2014
			/*
			
  			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"V\"){");
    		out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Add_Securities?chksql=vehicle&app_no='+app_no;");  
			out.println("		}");
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"L\"){");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Add_Securities?chksql=land&app_no='+app_no;");  
			out.println("		}");
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"F\"){");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Add_Securities?chksql=fixed_deposit&app_no='+app_no;");  
			out.println("		}");
			
			
			// added by udara 03-04-2014
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"R\"){");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Add_Securities?chksql=run_contract&app_no='+app_no;");  
			out.println("		}");
			// end by udara 03-04-2014
			
			*/
			
			// added by udara 21-10-2014
			
			//out.println("  alert(document.Form1.TXT_ASSIGN_TYPE.value);   ");
			//out.println("  alert(document.Form1.SCREEN_NAME.value);   ");
			
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"V\"){");
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"DELETE\"){ ");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Delete_Securities?chksql=vehicle&app_no='+app_no;");   		  
			out.println("    }");
			out.println("    else{");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Add_Securities?chksql=vehicle&app_no='+app_no;");
			out.println("    }");
			
			out.println("}");
			
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"L\"){");
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"DELETE\"){ ");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Delete_Securities?chksql=land&app_no='+app_no;");
			out.println("    }");
			out.println("    else{");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Add_Securities?chksql=land&app_no='+app_no;"); 
			out.println("    }");
			
			out.println("}");
			
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"F\"){");
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"DELETE\"){ ");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Delete_Securities?chksql=fixed_deposit&app_no='+app_no;");
			out.println("    }");
			out.println("    else{");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Add_Securities?chksql=fixed_deposit&app_no='+app_no;");  
			out.println("    }");
			
			out.println("}");
			
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"R\"){");
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"DELETE\"){ ");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Delete_Securities?chksql=run_contract&app_no='+app_no;");
			out.println("    }");
			out.println("    else{");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Add_Securities?chksql=run_contract&app_no='+app_no;");
			out.println("    }");
			
			out.println("}");
			// end by udara 21-10-2014

			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"H\"){"); // added by kasun on 29-11-2024 for JB16102024-25600
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"DELETE\"){ ");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Delete_Securities?chksql=product_hadagasma&app_no='+app_no;");
			out.println("    }");
			out.println("    else{");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_App_Process_Add_Securities?chksql=product_hadagasma&app_no='+app_no;");
			out.println("    }");
			
			out.println("}");
			
			
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Additional_Securities_cv?chksql=main_page&APP_NO='+app_no;"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Additional_Securities_cv?chksql=main_page&APP_NO='+app_no;"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_Application_Process\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Marketing - Additional Collaterals  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Marketing - Additional Collaterals - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("}"); 
			out.println("else{");
  		out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"V\")");
  		out.println("document.Form1.BUT_HELP_VEHICLE.disabled=false;"); 
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"L\")");
  		out.println("document.Form1.BUT_HELP_CAV_NO.disabled=false;"); 
  		out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"F\")");
  		out.println("document.Form1.BUT_HELP_AC_NO.disabled=false;"); 
			
			// added by udara 03-04-2014
		out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"R\")");
  		out.println("   document.Form1.BUT_HELP_FIN_NO.disabled=false;"); 
		// end by udara 03-04-2014
			
			
			
      out.println("}"); 
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
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
      out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("     document.Form1.TXT_REG_DD.value=v_dd;");
			out.println("     document.Form1.TXT_REG_MM.value=v_mm;");
			out.println("     document.Form1.TXT_REG_YY.value=v_yy;");
			out.println("  }");				
			out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("     document.Form1.TXT_CR_DD.value=v_dd;");
			out.println("     document.Form1.TXT_CR_MM.value=v_mm;");
			out.println("     document.Form1.TXT_CR_YY.value=v_yy;");
			out.println("  }");				
      out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("     document.Form1.TXT_VAL_DD.value=v_dd;");
			out.println("     document.Form1.TXT_VAL_MM.value=v_mm;");
			out.println("     document.Form1.TXT_VAL_YY.value=v_yy;");
			out.println("  }");	
			out.println("  if(document.Form1.hid_cal_date.value=='4'){"); 
			out.println("     document.Form1.TXT_ST_DD.value=v_dd;");
			out.println("     document.Form1.TXT_ST_MM.value=v_mm;");
			out.println("     document.Form1.TXT_ST_YY.value=v_yy;");
			out.println("  }");	
			out.println("  if(document.Form1.hid_cal_date.value=='5'){"); 
			out.println("     document.Form1.TXT_MATU_DD.value=v_dd;");
			out.println("     document.Form1.TXT_MATU_MM.value=v_mm;");
			out.println("     document.Form1.TXT_MATU_YY.value=v_yy;");
			out.println("  }");	
			out.println("  if(document.Form1.hid_cal_date.value=='6'){"); 
			out.println("     document.Form1.TXT_INTER_DD.value=v_dd;");
			out.println("     document.Form1.TXT_INTER_MM.value=v_mm;");
			out.println("     document.Form1.TXT_INTER_YY.value=v_yy;");
			out.println("  }");	
			out.println("}");		
			
			out.println("function set_date(){	"); 
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"V\"){");
			out.println("     document.Form1.TXT_REG_DD.value='"+m_sys_dd+"';");
			out.println("     document.Form1.TXT_REG_MM.value='"+m_sys_mm+"';");
			out.println("     document.Form1.TXT_REG_YY.value='"+m_sys_yy+"';");
			out.println("     document.Form1.TXT_CR_DD.value='"+m_sys_dd+"';");
			out.println("     document.Form1.TXT_CR_MM.value='"+m_sys_mm+"';");
			out.println("     document.Form1.TXT_CR_YY.value='"+m_sys_yy+"';");
			out.println("}	"); 
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"V\" || document.Form1.TXT_ASSIGN_TYPE.value==\"L\") {");
			out.println("     document.Form1.TXT_VAL_DD.value='"+m_sys_dd+"';");
			out.println("     document.Form1.TXT_VAL_MM.value='"+m_sys_mm+"';");
			out.println("     document.Form1.TXT_VAL_YY.value='"+m_sys_yy+"';");
			out.println("}	"); 
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"F\"){");
			out.println("     document.Form1.TXT_ST_DD.value='"+m_sys_dd+"';");
			out.println("     document.Form1.TXT_ST_MM.value='"+m_sys_mm+"';");
			out.println("     document.Form1.TXT_ST_YY.value='"+m_sys_yy+"';");
			out.println("     document.Form1.TXT_MATU_DD.value='"+m_sys_dd+"';");
			out.println("     document.Form1.TXT_MATU_MM.value='"+m_sys_mm+"';");
			out.println("     document.Form1.TXT_MATU_YY.value='"+m_sys_yy+"';");
			out.println("     document.Form1.TXT_INTER_DD.value='"+m_sys_dd+"';");
			out.println("     document.Form1.TXT_INTER_MM.value='"+m_sys_mm+"';");
			out.println("     document.Form1.TXT_INTER_YY.value='"+m_sys_yy+"';");
			out.println("}	"); 
			out.println("}	"); 
			
			out.println("function val_number(obj){");
			out.println("   if(isNaN(obj.value) ) { ");
			out.println("   alert('You have typed an incorrect character as a number');");
			out.println("   obj.value='';");
			out.println("   obj.focus();");
			out.println("   }");
			out.println("}");

			out.println("function  change_type(){");
			out.println("m_land.innerHTML=\"\" ");
			out.println("m_vehicle.innerHTML=\"\" ");
			out.println("m_fixed_deposit.innerHTML=\"\" ");
			out.println("m_hadagasma.innerHTML=\"\" "); //added by kasun on 29-11-2024
			
			
			out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"V\"){");
			    
			out.println("m_write='<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_VEHICLE  class=div_input>Vehicle Number*</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_VEHICLE maxlength=20 size=20 onBlur=\"makeRequest(document.Form1.TXT_VEHICLE);\">&nbsp;&nbsp;'+");  //  check_vehicle_no2()
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_VEHICLE value=\" Help \" onClick=\"help_vehicle()\" disabled></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CLIENT  class=div_input>Customer Name*</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_CLIENT maxlength=100 size=100 style=\"{width:200}\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_REG_DATE  class=div_input>First Registration Date*</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input5 type=text name=TXT_REG_DD maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_REG_MM maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_REG_YY maxlength=4 size=4 onblur=\"checkMonthLength(document.Form1.TXT_REG_DD,document.Form1.TXT_REG_MM,document.Form1.TXT_REG_YY)\"><a href onclick=load_calendar(\"1\") style=\"{cursor:hand; }\" >   Calendar</a></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_VEHICLE_TYPE  class=div_input>Vehicle Type</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_VEHICLE_TYPE maxlength=10 size=10 ></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_VEHICLE_MAKE  class=div_input>Make and Model</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_VEHICLE_MAKE maxlength=10 size=10 ></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+");
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_MANUFAC  class=div_input>Year of Manufacture</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_MANUFAC maxlength=4 size=4 onBlur=\"val_number(TXT_MANUFAC)\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CAPACITY  class=div_input>Cubic Capacity</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_CAPACITY maxlength=20 size=20 >&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CHASIS  class=div_input>Chasis Number</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_CHASIS maxlength=50 size=50 ; ></td>'+"); //check_vehicle_no()
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_ENGINE  class=div_input>Engine Number</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_ENGINE maxlength=50 size=50 ></td>'+"); 
			out.println("        '</tr>'+");
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_EXTEND  class=div_input>Extend</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_EXTEND maxlength=50 size=50 >&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_FUEL  class=div_input>Fuel Type</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_FUEL maxlength=20 size=20 >&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_ADD  class=div_input>Customer Address</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_ADD maxlength=200 size=200 style=\"{width:250}\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+");
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CR_DATE  class=div_input>CR Book Date</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input5 type=text name=TXT_CR_DD maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_CR_MM maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_CR_YY maxlength=4 size=4 onblur=\"checkMonthLength(document.Form1.TXT_CR_DD,document.Form1.TXT_CR_MM,document.Form1.TXT_CR_YY)\"><a href onclick=load_calendar(\"2\") style=\"{cursor:hand;  }\" >   Calendar</a></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_PROVINCE  class=div_input>Province</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_PROVINCE maxlength=50 size=50 >&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_PROVINCE value=\" Help \" onClick=\"help_province()\" ></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CR  class=div_input>CR Book Number</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_CR maxlength=15 size=15 >&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("        '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_VALUER  class=div_input>Valuer Code</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_VALUER maxlength=10 size=10 >&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_VALUER value=\" Help \" onClick=\"help_valuer()\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_VALUER_NAME  class=div_input>Valuer Name</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_VALUER_NAME maxlength=25 size=25 disabled style=\"{width:250}\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_VAL_DATE  class=div_input>Valuation Date</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input5 type=text name=TXT_VAL_DD maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_VAL_MM maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_VAL_YY maxlength=4 size=4 onblur=\"checkMonthLength(document.Form1.TXT_VAL_DD,document.Form1.TXT_VAL_MM,document.Form1.TXT_VAL_YY)\"><a href onclick=load_calendar(\"3\") style=\"{cursor:hand;  }\" >   Calendar</a></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_VALUE  class=div_input>Value*</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_VALUE maxlength=25 size=25 onBlur=\"format_number(TXT_VALUE,21)\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_SALES_VALUE  class=div_input>Forsed Slaes Value</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_SALES_VALUE maxlength=25 size=25 onBlur=\"format_number(TXT_SALES_VALUE,23)\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+");
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_USAGE  class=div_input>Odometer Reading/Usage in Units</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_USAGE maxlength=10 size=10 onBlur=\"val_number(TXT_USAGE)\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CONDITION  class=div_input>Condition of Asset</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><select class=txt_input type=text name=TXT_CONDITION maxlength=1 size=1><option value=N selected>New </option><option value=R >Recondition </option></select></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_NOTES  class=div_input>Notes</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_NOTES maxlength=100 size=100 style=\"{width:250}\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_REMARKS  class=div_input>Remarks</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_REMARKS maxlength=100 size=100 style=\"{width:250}\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+");
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_BODY  class=div_input>Type of the Body</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_BODY maxlength=50 size=50 >&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			//out.println("        '</tr>';"); 
			
			out.println("        '</tr>'+");
			
			out.println("'<table>'+ ");  
			out.println("'<tr><td width=\"10%\" ><b>Acknowledge<input  type=\"checkbox\" name=CHK_ACK value=\"N\" unchecked onclick=\"check_change()\" ></td></tr>'+ ");
			out.println("'<tr><td width=\"10%\" ><input type=\"button\" class=\"mainbut\" style=\"width:140px\" name=\"close2\" onMouseout=\"load_roll_out_value();\" onMouseOver=\"load_roll_value(\\'Next\\');\"  onclick=\"close_screen()\" value=\"Proceed to Next Level\"></td></tr>'+ ");  
			out.println("'<tr>'+ ");  
			out.println("'</tr>'+ ");	
			out.println("'<tr>'+ ");  
			out.println("'</tr>'+ ");
			out.println("'<table>';");



					
			out.println("m_vehicle.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\" >'+");
			out.println("m_write+'</table>';");
			
			out.println("}");
			out.println("else if(document.Form1.TXT_ASSIGN_TYPE.value==\"L\"){");
			out.println("m_write='<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_CAV_NO  class=div_input>Mortgage/Caveat Number*</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_CAV_NO maxlength=20 size=20 onBlur=\"makeRequest(document.Form1.TXT_CAV_NO)\">&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_CAV_NO value=\" Help \" onClick=\"help_mortgage()\" disabled></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_MORTGAGE  class=div_input>Mortgage Type</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><select class=txt_input type=text name=TXT_MORTGAGE maxlength=1 size=1><option value=P selected>Primary Mortgage </option><option value=S >Secondary Mortgage </option><option value=C >Caveat </option></select></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_DEED_NO  class=div_input>Deed Number</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_DEED_NO maxlength=20 size=20 ></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_LAND_ADD  class=div_input>Land Situated At (Address)</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_LAND_ADD maxlength=200 size=200 style=\"{width:250}\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+");
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_LAND_VALUE class=div_input>Value of the Land*</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_LAND_VALUE maxlength=25 size=25 onBlur=\"format_number(TXT_LAND_VALUE,21)\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_VAL_NAME  class=div_input>Values Name (If Any)</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_VAL_NAME maxlength=100 size=100 style=\"{width:200}\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_VAL_DATE  class=div_input>Valuation Date</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input5 type=text name=TXT_VAL_DD maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_VAL_MM maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_VAL_YY maxlength=4 size=4 onblur=\"checkMonthLength(document.Form1.TXT_VAL_DD,document.Form1.TXT_VAL_MM,document.Form1.TXT_VAL_YY)\"><a href onclick=load_calendar(\"3\") style=\"{cursor:hand;  }\" >   Calendar</a></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_REMARKS  class=div_input>Special Remarks</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_REMARKS maxlength=100 size=100 style=\"{width:200}\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>';");
			
			out.println("m_land.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\" >'+");
      out.println("m_write+'</table>';");

			out.println("}");	
			
			out.println("else if(document.Form1.TXT_ASSIGN_TYPE.value==\"F\"){");
			out.println("m_write='<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_AC_NO  class=div_input>Fixed Deposit A/C Number*</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_AC_NO maxlength=50 size=50 onBlur=\"makeRequest(document.Form1.TXT_AC_NO)\" >&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_AC_NO value=\" Help \" onClick=\"help_fixed_deposit()\" disabled></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_DEP_AMT  class=div_input>Fixed deposit Amount*</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_DEP_AMT maxlength=25 size=25 onBlur=\"format_number(TXT_DEP_AMT,21)\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_START_DATE  class=div_input>Starting Date</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input5 type=text name=TXT_ST_DD maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_ST_MM maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_ST_YY maxlength=4 size=4 onblur=\"checkMonthLength(document.Form1.TXT_ST_DD,document.Form1.TXT_ST_MM,document.Form1.TXT_ST_YY)\"><a href onclick=load_calendar(\"4\") style=\"{cursor:hand;  }\" >   Calendar</a></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("        '</tr>'+");
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_MATU_DATE class=div_input>Maturity Date</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input5 type=text name=TXT_MATU_DD maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_MATU_MM maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_MATU_YY maxlength=4 size=4 onblur=\"checkMonthLength(document.Form1.TXT_MATU_DD,document.Form1.TXT_MATU_MM,document.Form1.TXT_MATU_YY)\"><a href onclick=load_calendar(\"5\") style=\"{cursor:hand;  }\" >   Calendar</a></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_INTER_DATE  class=div_input>Interest Date</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input5 type=text name=TXT_INTER_DD maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_INTER_MM maxlength=2 size=2 >'+"); 
			out.println("        '<input class=txt_input5 type=text name=TXT_INTER_YY maxlength=4 size=4 onblur=\"checkMonthLength(document.Form1.TXT_INTER_DD,document.Form1.TXT_INTER_MM,document.Form1.TXT_INTER_YY)\"><a href onclick=load_calendar(\"6\") style=\"{cursor:hand;  }\" >   Calendar</a></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+"); 
			out.println("        '</tr>'+"); 
			out.println("				 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_INTER_PAY  class=div_input>Interest Payable</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_INTER_PAY maxlength=20 size=20 ></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+");
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_PERIOD  class=div_input>Period</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_PERIOD maxlength=5 size=5 onBlur=\"val_number(TXT_PERIOD)\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>'+");
			out.println("				 '<tr >'+"); 
    	out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_REMARKS  class=div_input>Special Remarks</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_REMARKS maxlength=100 size=100 style=\"{width:250}\" ></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>';");
			
			out.println("m_fixed_deposit.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\" >'+");
      out.println("m_write+'</table>';");
			out.println("}");	
			
			
			
			// added by udara 03-04-2014
			
			out.println("else if(document.Form1.TXT_ASSIGN_TYPE.value==\"R\"){");
			out.println("m_write='<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_FIN_NO  class=div_input>Finance Number * </DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_FIN_NO maxlength=50 size=50 onBlur=\"\" >&nbsp;&nbsp;'+"); 
			out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_FIN_NO value=\" Help \" onClick=\"help_finance_no()\" ></td>'+"); // disabled
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>';"); 
			
			out.println("m_fixed_deposit.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\" >'+");
      		out.println("m_write+'</table>';");
			out.println("}");
			
			// end by udara 03-04-2014


			// added by kasun on 29-11-2024 for JB16102024-25600 

			out.println("else if(document.Form1.TXT_ASSIGN_TYPE.value==\"H\"){"); 
			out.println("m_write='<tr >'+"); 
			out.println("		 '<td width=\"20%\" ><DIV id=DIV_TXT_ITEM_SUB_CAT  class=div_input>Item Sub Category *</DIV></td>'+");
			out.println("		 '<td width=\"40%\" >'+");
			out.println("		 '<SELECT class=txt_input name=TXT_ITEM_SUB_CAT id=TXT_ITEM_SUB_CAT >'+  ");

			stmt4=conn.createStatement();
			rs4 = stmt4.executeQuery ("SELECT ITEM_SUB_CAT,DESCRIPTION FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY ");
			
			out.println("    '<OPTION value=\"\"></OPTION>'+ ");
			while(rs4.next()) {
				String itemSubCat = rs4.getString("ITEM_SUB_CAT");
				String description = rs4.getString("DESCRIPTION");
				out.println("    '<OPTION value=\"" + itemSubCat + "\">" + description + "</OPTION>'+ ");
			}

			rs4.close();

			out.println("		 '</SELECT>'+");
			out.println("		 '</td>'+ ");
			out.println("		 '</tr>'+");

			out.println("		 '<tr >'+"); 
			out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_REMARKS  class=div_input>Special Remarks</DIV></td>'+"); 
			out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_REMARKS maxlength=100 size=100 style=\"{width:200}\"></td>'+"); 
			out.println("        '<td width=\"*%\">&nbsp;&nbsp;</td>'+"); 
			out.println("        '</tr>';");
			
			out.println("m_hadagasma.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\" >'+");
      		out.println("m_write+'</table>';");

			out.println("}");

			// ended by kasun on 29-11-2024 for JB16102024-25600
			
			
			
			
			out.println("}");
			
			
			out.println("function set_details(){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Additional_Securities_cv?chksql=details&APP_NO=\"+app_no;");
			out.println("load_interface(m_url,'NO');");
			out.println("}");
			
			out.println("function get_vector_normal(m_data) {");
			out.println(" m_details.innerHTML = m_data; ");
			out.println("}");

			out.println("function vehicle_edit(m_vehicle_no,m_applicaton_no) {");
		  out.println("document.Form1.hid_type.value=\"vehicle\"");
			out.println("document.Form1.SCREEN_NAME.value=\"EDIT\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_sec_vehicle_edit&data_val=\"+m_vehicle_no+\"&data_val2=\"+m_applicaton_no;");
			out.println("load_interface(m_url,'XML');");
			
			out.println("}");
			
			out.println("function land_edit(m_land_no,m_applicaton_no) {");
		  out.println("document.Form1.hid_type.value=\"land\"");
			out.println("document.Form1.SCREEN_NAME.value=\"EDIT\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_sec_land_edit&data_val=\"+m_land_no+\"&data_val2=\"+m_applicaton_no;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function fixed_deposit_edit(m_fd_no,m_applicaton_no) {");
		  out.println("document.Form1.hid_type.value=\"fixed_deposit\"");
			out.println("document.Form1.SCREEN_NAME.value=\"EDIT\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_sec_fd_edit&data_val=\"+m_fd_no+\"&data_val2=\"+m_applicaton_no;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			// added by udara 03-04-2014
			
			out.println("function running_contract_edit(m_applicaton_no,m_run_no) {");
		    out.println("document.Form1.hid_type.value=\"running_contract\"");
			out.println("document.Form1.SCREEN_NAME.value=\"EDIT\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_running_contract_edit&data_val=\"+m_applicaton_no+\"&run_no=\"+m_run_no;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			// emnd by udara 03-04-2014

			out.println("function hadagasma_product_edit(m_applicaton_no,m_sub_cat) {");// added by kasun on 2024.11.29
		    out.println("document.Form1.hid_type.value=\"hadagasma_product\"");
			out.println("document.Form1.SCREEN_NAME.value=\"EDIT\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_product_hadagasma_edit&data_val=\"+m_applicaton_no+\"&sub_cat=\"+m_sub_cat;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			// added by udara 21-10-2014
			
			out.println("function vehicle_delete(m_vehicle_no,m_applicaton_no) {");
		    out.println("document.Form1.hid_type.value=\"vehicle\"");
			out.println("document.Form1.SCREEN_NAME.value=\"DELETE\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_sec_vehicle_edit&data_val=\"+m_vehicle_no+\"&data_val2=\"+m_applicaton_no;");
			out.println("load_interface(m_url,'XML');");
			
			out.println("}");
			
			out.println("function land_delete(m_land_no,m_applicaton_no) {");
		    out.println("document.Form1.hid_type.value=\"land\"");
			out.println("document.Form1.SCREEN_NAME.value=\"DELETE\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_sec_land_edit&data_val=\"+m_land_no+\"&data_val2=\"+m_applicaton_no;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function fixed_deposit_delete(m_fd_no,m_applicaton_no) {");
		    out.println("document.Form1.hid_type.value=\"fixed_deposit\"");
			out.println("document.Form1.SCREEN_NAME.value=\"DELETE\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_sec_fd_edit&data_val=\"+m_fd_no+\"&data_val2=\"+m_applicaton_no;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function running_contract_delete(m_applicaton_no,m_run_no) {");
		    out.println("document.Form1.hid_type.value=\"running_contract\"");
			out.println("document.Form1.SCREEN_NAME.value=\"DELETE\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_running_contract_edit&data_val=\"+m_applicaton_no+\"&run_no=\"+m_run_no;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			// end by udara 21-10-2014

			//added by kasun on 2024.11.29
			out.println("function hadagasma_product_delete(m_applicaton_no,m_sub_cat) {");
		    out.println("document.Form1.hid_type.value=\"hadagasma_product\"");
			out.println("document.Form1.SCREEN_NAME.value=\"DELETE\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_product_hadagasma_edit&data_val=\"+m_applicaton_no+\"&sub_cat=\"+m_sub_cat;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function close_screen_2() {");
			
			out.println("	if(document.Form1.close2.value==\"Proceed to Next Level\"){");
			out.println(" if(document.Form1.CHK_ACK.checked==true ){ ");
			out.println("		if(confirm(\"Are you sure you want to Proceed to Next Level?\")){ "); 
			out.println("		window.close();"); 
			out.println("window.opener.document.Form1.elements[\"CHK_SD_\"+m_row_no].checked=true; ");//added by nwuan de silva on 22-10-07
			out.println("window.opener.document.Form1.elements[\"CHK_SD_\"+m_row_no].value='YES'; "); //added by nwuan de silva on 22-10-07
			out.println("window.opener.check_change_D(m_row_no);");
			out.println("		}"); 
			out.println(" }");
			out.println(" else { ");
			out.println(" alert('Please check acknowledge to proceed next level'); ");
			out.println(" } ");
			out.println("		}"); 
			
			out.println("	else if(document.Form1.close2.value==\"Close\"){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("}");
			
			// added by udara on 07-01-2013
			
			out.println("function close_screen() {");
			
			//out.println("	if(document.Form1.close2.value==\"Proceed to Next Level\"){");
			//out.println("m_close_status=\""+m_close+"\"");
			//out.println("alert('ad'+m_close_status);");
			
			out.println("var m_close_status=\"N\"");

            out.println("	if(m_close_status==\"Y\"){");
			out.println(" if(document.Form1.CHK_ACK.checked==true ){ ");

			out.println("		if(confirm(\"Are you sure you want to Proceed to Next Level?\")){ "); 

		    //	out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			
			out.println(" }");
			
			out.println(" else { ");
			out.println(" alert('Please check acknowledge to proceed next level'); ");
			out.println(" } ");
			
			
			out.println("		}"); 
			
			//out.println("	if(document.Form1.close2.value==\"Close\"){");
			out.println("	else if(m_close_status==\"N\"){");
		   //	out.println("		if(confirm(\"Are you sure you want to Proceed to next level?\")){ "); 

			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
				
				
			out.println("}");
			
			
			out.println("function check_change() {");
			out.println("if(document.Form1.CHK_ACK.checked==false){");
			out.println("document.Form1.CHK_ACK.value=\"0\"");
			out.println("window.opener.document.Form1.Btn_approve.disabled=true");
			out.println("}");
			out.println("else if(document.Form1.CHK_ACK.checked==true){");
			out.println("document.Form1.CHK_ACK.value=\"1\"");
			out.println("window.opener.document.Form1.Btn_approve.disabled=false");
			out.println("}");
			out.println("}");
			
			
			
			// end by udara on 07-01-2013
			
			
			// added by udara on 30-07-2013
			
			out.println("function check_vehicle_no() {");
			out.println("    document.Form1.hid_type_2.value=\"1\"");
			out.println("document.Form1.hid_type.value=\"check_vehicle\"");
			out.println("    var vehicle_no = document.Form1.TXT_VEHICLE.value; ");
			out.println("    var chasis_no  = document.Form1.TXT_CHASIS.value; ");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=check_vehicle_no&CHASIS_NO=\"+chasis_no+\"&APPLICATION_NO=\"+app_no+\"&VEHICLE_NO=\"+vehicle_no;");
			out.println("    load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function check_vehicle_no2() {");
			out.println("    document.Form1.hid_type_2.value=\"2\"");
			out.println("document.Form1.hid_type.value=\"check_vehicle\"");
			out.println("    var vehicle_no = document.Form1.TXT_VEHICLE.value; ");
			out.println("    var chasis_no  = document.Form1.TXT_CHASIS.value; ");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=check_vehicle_no2&CHASIS_NO=\"+chasis_no+\"&APPLICATION_NO=\"+app_no+\"&VEHICLE_NO=\"+vehicle_no;");
			out.println("    load_interface(m_url,'XML');");
			out.println("}");
			
			// end by udara on 30-07-2013


		
      out.println("</Script>");
				
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"change_type();set_date();set_details()\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
				  				
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
					//out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_type' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_type_2' VALUE=\"\">");
					
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Marketing - Additional Collaterals - New</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
		
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
					
			    out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>");  
			    out.println("<td width=\"100%\"><DIV ID='m_details'></DIV></td>");
		      out.println("</tr>"); 
					out.println("</table>"); 
					out.println("<br>"); 
					out.println("<hr>"); 
					out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding='2'>"); 
					out.println("<tr >"); 
					out.println("<td width='20%' >Assert Type</td>"); 
					out.println("<td width='30%' ><select class='txt_input' type='text' name='TXT_ASSIGN_TYPE' onChange=\"change_type();set_date()\" maxlength='1' size='1'>");  
					out.println("<option value='V' selected>Motor Vehicle </option>");			
					out.println("<option value='L' >Land</option>");	
					out.println("<option value='F' >Fixed Deposit </option>");	
					out.println("<option value='R' >Running Contract </option>"); // added by udara 03-04-2014
					out.println("<option value='R' >HADAGASMA</option>"); // added by kasun on 29-11-2024
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
					out.println("</table>");  
					//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         	out.println("<table align='center' width='100%' class='table'>"); 
				  out.println("<tr>");  
			    out.println("<td width=\"100%\"><DIV ID='m_vehicle'></DIV></td>");
		      out.println("</tr>"); 
				  out.println("<tr>");  
			    out.println("<td width=\"100%\"><DIV ID='m_land'></DIV></td>");
		      out.println("</tr>"); 	
				  out.println("<tr>");  
			    out.println("<td width=\"100%\"><DIV ID='m_fixed_deposit'></DIV></td>");
		      out.println("</tr>"); 
			    out.println("<tr>");  
			    out.println("<td width=\"100%\"><DIV ID='m_hadagasma'></DIV></td>"); // added by kasun on 29-11-2024 for JB16102024-25600
		      	out.println("</tr>"); 	
			    out.println("</table>");
					
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
		
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
		}// end of main	
		
		if(m_chksql.equals("details")){ 
			String m_application_no = req.getParameter("APP_NO");
			
				out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"2\"> "); 

				stmt = conn.createStatement ();
		
				rs = stmt.executeQuery(	" SELECT "+ 
												        " VEHICLE_NO, "+ //1
												        " CUSTOMER_NAME, "+ //2
												        " NVL(VEHICLE_TYPE,'-') VEHICLE_TYPE, "+ //3
												        " NVL(MODEL_CODE,'-') MODEL_CODE, "+ //4
												        " NVL(YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+ //5
												        " NVL(ENGINE_NO,'-') ENGINE_NO, "+ //6
												        " NVL(VALUE,0) VALUE, "+ //7
																" DECODE(CONDITION_OF_ASSET,'N','New','R','Recondition'), "+ //8
																" NVL(APPLICATION_NO,'-') APPLICATION_NO "+ //9
												        " FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE "+
												        " WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
												        " ORDER BY VEHICLE_NO DESC ");
    
				boolean more = rs.next();		
				int i=0;
				int line_no=0;

			
			  if(more){
				out.println(" <tr > ");
				out.println(" <td > ");
				out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"2\"> "); 
				out.println("<tr ><td width=\"10%\" align='left'><b><u>Vehicle</u></b></td></tr>");
				out.println(" <tr > ");
				out.println("  <td width=\"10%\"  align='left'><b>Vehicle Number</b></td> ");
				out.println("  <td width=\"15%\"  align='left'><b>Customer Name</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Vehicle Type</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Make and Model</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Year of Manufacture</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Engine Number</b></td> ");
				out.println("  <td width=\"10%\"  align='right'><b>Value</b></td> ");
				out.println("  <td width=\"1%\"  align='right'> &nbsp; </td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Condition of Asset</b></td> ");
				out.println("  <td width=\"5%\"  align='left'></td> ");
				out.println("  <td width=\"5%\"  align='left'></td> "); // added by udara 21-10-2014
				out.println(" </tr>");
				}

				while(more){
						if(i>0 && i%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}

						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(1)+"</td> ");
						out.println("  <td width=\"15%\"  align='left'>"+rs.getString(2)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(3)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(4)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getInt(5)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(6)+"</td> ");
						out.println("  <td width=\"10%\"  align='right'>"+nf.format(rs.getDouble(7))+"</td> ");
						out.println("  <td width=\"1%\"  align='right'> &nbsp; </td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(8)+"</td> ");
						out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Edit\" type=\"button\" name=BUT_EDIT"+line_no+"  onClick=\"vehicle_edit('"+rs.getString(1)+"','"+rs.getString(9)+"')\" ></td>");
						out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Delete\" type=\"button\" name=BUT_DELETE"+line_no+"  onClick=\"vehicle_delete('"+rs.getString(1)+"','"+rs.getString(9)+"')\" ></td>"); // added by udara 21-10-2014
						out.println(" </tr>");
						i=i+1;
						line_no=line_no+1;
						more=rs.next();
				}
				//out.println("</table >");
				out.println(" </td > ");
				out.println(" </tr > ");

				rs.close();

				//////////////////////////////////////////////////////////////////////
				stmt1 = conn.createStatement ();
		
				rs1 = stmt1.executeQuery(" SELECT "+ 
												        " MORTGAGE_NO, "+ //1
																" DECODE(MORTGAGE_TYPE,'P','Primary Mortgage','S','Secondary Mortgage','C','Caveat'), "+ //2
												        " NVL(DEED_NO,'-') DEED_NO, "+ //3
												        " NVL(ADDRESS,'-') ADDRESS, "+ //4 
												        " NVL(VALUE,0) VALUE, "+ //5 
												        " NVL(VALUES_NAME,'-') VALUES_NAME, "+ //6
												        " TO_CHAR(VALUATION_DATE,'DD-MM-YYYY') VALUATION_DATE, "+ //7
												        " NVL(REMARKS,'-') REMARKS, "+ //8
																" NVL(APPLICATION_NO,'-') APPLICATION_NO "+ //9
												        " FROM "+m_schema_name+".AF_MK_APP_SECURITY_LAND "+
												        " WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
												        " ORDER BY MORTGAGE_NO DESC ");
    
				boolean more1 = rs1.next();		
				int i1=0;
				int line_no1=0;
				
				if(more1){
				out.println(" <tr > ");
				out.println(" <td > ");
				//out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\" cellpadding=\"0\"> "); 
				out.println("<tr ><td width=\"10%\" align='left'><u><b>Land</b></u></td></tr>");
				out.println(" <tr > ");
				out.println("  <td width=\"10%\"  align='left'><b>Mortgage Number</b></td> ");
				out.println("  <td width=\"15%\"  align='left'><b>Mortgage Type</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Deed Number</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Land Situated At</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Value of the Land</b></td> ");
				out.println("  <td width=\"1%\"  align='right'> &nbsp; </td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Values Name</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Valuation Date</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Special Remarks</b></td> ");
				out.println("  <td width=\"5%\"  align='left'></td> ");
				out.println("  <td width=\"5%\"  align='left'></td> "); // added by udara 21-10-2014
				out.println(" </tr>");

				}
		
				while(more1){
						if(i1>0 && i1%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}

						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(1)+"</td> ");
						out.println("  <td width=\"15%\"  align='left'>"+rs1.getString(2)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(3)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(4)+"</td> ");
						out.println("  <td width=\"10%\"  align='right'>"+nf.format(rs1.getDouble(5))+"</td> ");
						out.println("  <td width=\"1%\"  align='right'> &nbsp; </td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(6)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(7)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(8)+"</td> ");
						out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Edit\" type=\"button\" type=\"button\" name=BUT_EDIT"+line_no1+"  onClick=\"land_edit('"+rs1.getString(1)+"','"+rs1.getString(9)+"')\" ></td>"); 
						out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Delete\" type=\"button\" type=\"button\" name=BUT_DELETE"+line_no1+"  onClick=\"land_delete('"+rs1.getString(1)+"','"+rs1.getString(9)+"')\" ></td>"); // added by udara 21-10-2014
						out.println(" </tr>");
						i1=i1+1;
						line_no1=line_no1+1;
						more1=rs1.next();
				}
				//out.println("</table >");
				out.println(" </td > ");
				out.println(" </tr > ");
				rs1.close();
				
				//////////////////////////////////////////////////////////////
				
				stmt2 = conn.createStatement ();
		
				rs2 = stmt2.executeQuery(" SELECT "+ 
												        " FD_ACC_NO, "+ //1
												        " NVL(AMOUNT,0) AMOUNT, "+ //2
												        " TO_CHAR(STARTING_DATE,'DD-MM-YYYY') STARTING_DATE, "+ //3
												        " TO_CHAR(MATURITY_DATE,'DD-MM-YYYY') MATURITY_DATE, "+ //4
												        " TO_CHAR(INTEREST_DATE,'DD-MM-YYYY') INTEREST_DATE, "+ //5
												        " NVL(INTEREST_PAYABLE,'-') INTEREST_PAYABLE, "+ //6
												        " NVL(PERIOD,0) PERIOD, "+ //7
												        " NVL(REMARKS,'-') REMARKS, "+ //8
																" NVL(APPLICATION_NO,'-') APPLICATION_NO "+ //9
												        " FROM "+m_schema_name+".AF_MK_APP_SECURITY_FIXED_DEP "+
												        " WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
												        " ORDER BY FD_ACC_NO DESC ");
    
				boolean more2 = rs2.next();		
				int i2=0;
				int line_no2=0;

				if(more2){
				out.println(" <tr > ");
				out.println(" <td > ");

				//out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"2\"> "); 
				out.println("<tr ><td width=\"10%\" align='left'><b><u>Fixed Deposit</u></b></td></tr>");
				out.println(" <tr> ");
				out.println("  <td width=\"10%\"  align='left'><b>Fixed Deposit A/C Number</b></td> ");
				out.println("  <td width=\"15%\"  align='left'><b>Amount</b></td> ");
				out.println("  <td width=\"1%\"  align='right'> &nbsp; </td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Starting Date</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Maturity Date</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Interest Date</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Interest Payable</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Period</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Special Remarks</b></td> ");
				out.println("  <td width=\"5%\"  align='left'></td> ");
				out.println("  <td width=\"5%\"  align='left'></td> ");
				out.println(" </tr>");
				}
				

				while(more2){
						if(i2>0 && i2%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}

						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(1)+"</td> ");
						out.println("  <td width=\"15%\"  align='right'>"+nf.format(rs2.getDouble(2))+"</td> ");
						out.println("  <td width=\"1%\"  align='right'> &nbsp; </td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(3)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(4)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(5)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(6)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getInt(7)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(8)+"</td> ");
						out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Edit\" type=\"button\" name=BUT_EDIT"+line_no2+"  onClick=\"fixed_deposit_edit('"+rs2.getString(1)+"','"+rs2.getString(9)+"')\"></td>"); 
						out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Delete\" type=\"button\" name=BUT_DELETE"+line_no2+"  onClick=\"fixed_deposit_delete('"+rs2.getString(1)+"','"+rs2.getString(9)+"')\"></td>"); 
						out.println(" </tr>");
						i2=i2+1;
						line_no2=line_no2+1;
						more2=rs2.next();
				}
				//out.println("</table >"); 
				out.println(" </td > ");
				out.println(" </tr > ");
				
				rs2.close();
				
				
				
				// ====================== added by udara 03-04-2014 ===================================================================
			
				
			    stmt3 = conn.createStatement ();
		
				rs3 = stmt3.executeQuery(" SELECT "+ 
												" ASSIGNED_FINANCE_NO "+ //1
												" FROM "+m_schema_name+".AF_ASSET_RUN_CONTRACTS "+
												" WHERE UPPER(APP_NO) = UPPER('"+m_application_no+"') "+
												" ORDER BY ASSIGNED_FINANCE_NO DESC ");
    
				boolean more3 = rs3.next();		
				int i3=0;
				int line_no3=0;
				
				
				if(more3){
				out.println(" <tr > ");
				out.println(" <td > ");
				//out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\" cellpadding=\"0\"> "); 
				
				out.println("<tr>");
				out.println("  <td width=\"15%\"  align='left'><u><b>Running Contract</b></u></td>");
				out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
				out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
				out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
				out.println("</tr>");
				
				out.println(" <tr> ");
				out.println("  <td width=\"15%\"  align='left'><b>Finance Number</b></td> ");
				out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
				out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
				//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
				//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
				//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
				//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
				//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
				//out.println("  <td width=\"*%\"  align='left'> &nbsp; </td> ");
				out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
				out.println(" </tr>");

				}
		
				
				while(more3){
						
						if(i3>0 && i3%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}
						
						
						out.println("  <td width=\"15%\"  align='left'>"+rs3.getString(1)+"</td> ");
						//out.println("  <td width=\"15%\"  align='center'><input class=\"but_input\" value=\"Edit\" type=\"button\" name=BUT_EDIT"+line_no3+"  onClick=\"running_contract_edit('"+m_application_no+"')\"></td>");
						out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Edit\" type=\"button\" name=BUT_EDIT"+line_no3+"  onClick=\"running_contract_edit('"+m_application_no+"','"+rs3.getString(1)+"')\"></td>");
						out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Delete\" type=\"button\" name=BUT_DELETE"+line_no3+"  onClick=\"running_contract_delete('"+m_application_no+"','"+rs3.getString(1)+"')\"></td>"); // out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
						//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
						//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
						//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
						//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
						//out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
						//out.println("  <td width=\"*%\"  align='left'> &nbsp; </td> ");
						out.println("  <td width=\"*%\"  align='left' colspan=6 > &nbsp; </td> ");
						out.println(" </tr>");
						

						i3=i3+1;
						line_no3=line_no3+1;
						more3=rs3.next();
				}
				
				// out.println("</table >"); // commented by kasun on 29-11-2024
				out.println(" </td > ");
				out.println(" </tr > ");
				rs3.close();
				
			
			
			// ====================== end by udara 03-04-2014 =====================================================================
				
				
			//-------------added by kasun on 29-11-2024 for 	JB16102024-25600

			stmt4 = conn.createStatement ();
		
			rs4 = stmt4.executeQuery(" SELECT "+ 
											" "+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC_2(ITEM_SUB_CAT), "+ //1
											" NVL(REMARKS,'-') REMARKS, "+ //2
											" ITEM_SUB_CAT "+ //3
											" FROM "+m_schema_name+".AF_ASSET_PRO_HADAGASMA "+
											" WHERE UPPER(APP_NO) = UPPER('"+m_application_no+"') "+
											" ORDER BY ITEM_SUB_CAT DESC ");

											

			boolean more4 = rs4.next();		
			int i4=0;
			int line_no4=0;
			
			
			if(more4){
			out.println(" <tr > ");
			out.println(" <td > ");
			out.println("<tr>");
			out.println("  <td width=\"15%\"  align='left'><u><b>HADAGASMA</b></u></td>");
			out.println("</tr>");
			
			out.println(" <tr> ");
			out.println("  <td width=\"15%\"  align='left'><b>Item Sub Category</b></td> ");
			out.println("  <td width=\"15%\"  align='left'><b>Special Remarks</b></td> ");
			out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
			out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
			out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
			out.println(" </tr>");

			}
	
			
			while(more4){
					if(i4>0 && i4%2==1){
							out.println("<tr class=tr_input1 >");
					}
					else{
							out.println("<tr class=tr_input >");
					}
					
					out.println("  <td width=\"15%\"  align='left'>"+rs4.getString(1)+"</td> ");
					out.println("  <td width=\"15%\"  align='left'>"+rs4.getString(2)+"</td> ");
					out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Edit\" type=\"button\" name=BUT_EDIT"+line_no4+"  onClick=\"hadagasma_product_edit('"+m_application_no+"','"+rs4.getString(3)+"')\"></td>");
					out.println("  <td width=\"5%\"  align='center'><input class=\"but_input\" value=\"Delete\" type=\"button\" name=BUT_DELETE"+line_no4+"  onClick=\"hadagasma_product_delete('"+m_application_no+"','"+rs4.getString(3)+"')\"></td>");
					out.println("  <td width=\"*%\"  align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");
					

					i4=i4+1;
					line_no4=line_no4+1;
					more4=rs4.next();
			}
			
			out.println("</table >");
			out.println(" </td > ");
			out.println(" </tr > ");
			rs4.close();
			//-------------end by kasun on 29-11-2024 for 	JB16102024-25600
				
				
				
				
				
				

				out.println("</table >"); 

				

		
		}// end of details
	
			
		}// end of try
		
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
