import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:23-03-2007

public class LAKDL_AF_RE_PRO_drill_downs extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	public ServletOutputStream out = null; 
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		
		Connection conn =null;
		Statement stmt=null,stmt1=null;
		CallableStatement callstmt=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
		 ServletOutputStream out = null; 
		 ResultSet rs=null,rs1=null,rs2=null;
		 String m_chksql=null;
		
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			//**************************************************************					
			//--MODIFIED By :delanjali----------------------------------------
			//--DATE				: 2007-07-27--------------------------------------
			
			
			
			String m_html_client_url1=m_sn_methods.html_client_url.trim(); 
			
			String url = "";
			if(req.getParameter("url")!=null){
				url=req.getParameter("url");
			}
			
			if(url.equals("http://www.lakdac.lk")){
				m_html_client_url="http://www.lakdac.lk"; 
				m_class_url="http://www.lakdac.lk:/myserver/servlet"; 
			}
			
			else{
				
				m_html_client_url=m_sn_methods.html_client_url.trim(); 
			}
			//---------------------------------------------------------------
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			out = res.getOutputStream();
			
			m_chksql=req.getParameter("chksql");
			
			
			
			stmt1=conn.createStatement();
			stmt=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			//-------------------##############--- Client Details Drill Start --###############----------------------------------------------------
			
			else if(m_chksql.equals("SHOW_CLIENT_INFORMATION")){
				
				String m_string="";				
				String m_client_code=req.getParameter("client_code");
				
				//out.println(" SELECT "+ //prabash
				rs= stmt1.executeQuery(" SELECT "+
					"NVL(A.CLIENT_CODE,'-'), "+//1
					"DECODE(A.CLIENT_TYPE,'I','Individual','Corporate'), "+//2
					"A.TITLE, "+//30
					"NVL(A.FIRST_NAME,'-'), "+//4
					"NVL(A.SURNAME,'-'), "+//5
					"NVL(A.NIC_NO,'-'), "+//6
					"NVL(A.TEL_NO,'-'), "+//7
					"NVL(A.MOBILE_NO,'-'), "+//8
					"NVL(A.ADDRESS1,'-'), "+//9
					"NVL(A.INITIALS,'-'), "+//10
					"NVL(A.FULL_NAME,'-'), "+//11
					"NVL(A.OTHER_NAME,'-'), "+//12
					"NVL(A.RESIDENTIAL_STATUS,'-'),"+//13
					"NVL(A.ADDRESS2,'-'), "+//14
					"NVL(A.OFFICE_TEL_NO,'-'), "+//15
					"NVL(A.FAX_NO,'-'), "+//16
					"NVL(A.EMAIL,'-'), "+//17
					"NVL(A.DURATION_AT_YEARS,0), "+//18
					"NVL(A.DURATION_AT_MONTHS,0), "+//19
					"NVL(A.EMP_NAME,'-'), "+//20
					"NVL(A.EMP_ADDRESS1,'-'), "+//21
					"NVL(A.EMP_ADDRESS2,'-'), "+//22
					"NVL(A.EMP_REFERENCE,'-'), "+//23
					"NVL(A.EMP_RDESIGNATION,'-'), "+//24
					"NVL(A.EMP_TEL_NO,'-'), "+//25
					"NVL(A.EMP_FAX_NO,'-'), "+//26
					"NVL(B.NAME,'-'), "+//27
					"NVL(B.ADDRESS1,'-') REL_ADD1, "+//28
					"NVL(B.ADDRESS2,'-') REL_ADD2,"+//29
					"NVL(B.RELATIONSHIP,'-'), "+//30
					"NVL(B.HOME_TEL_NO,'-'), "+//31
					"NVL(B.OFFICE_TEL_NO,'-') REL_OFF_TEL, "+//32
					"NVL(B.MOBILE_NO,'-') REL_MOB, "+//33
					"NVL(TO_CHAR(A.DATE_OF_BIRTH,'DD-MM-YYYY'),'-'), "+//34
					"NVL(A.PASSPORT_NO,'-'), "+//35
					"NVL(A.NATIONALITY,'-'), "+//36
					"NVL(A.MARITAL_STATUS,'-'), "+//37
					"DECODE(A.GENDER,'M','Male','Female'), "+//38
					"NVL(A.BA_NATURE_OF_BUSINESS,'-'), "+//39
					"NVL(A.BA_PROFESSION,'-'), "+//40
					"NVL(A.BA_QUALIFICATIONS,'-'), "+//41
					"NVL(A.BA_DESIGNATION,'-'), "+//42
					"NVL(A.NO_OF_CHILDREN,0), "+//43
					"NVL(A.DEPENDENTS,0), "+//44
					"NVL(A.CITY_CODE,'-'), "+//45
					"NVL(A.VAT_REG_NO,'-'), "+//46
					"NVL(A.DRIVING_LICENSE_NO,'-'), "+//47
					"NVL(A.POSTALCODE,'-') POSTALCODE, "+//48
					"NVL(A.GRIB_NO,'-'),  "+//49
					"DECODE(A.ACTIVE_STATUS,'Y','Active','N','Deactive','E','Initial Credit Approval', "+
					"'I','Waiting for Credit Approval','T','Terminated','B','Black Listed','Other'), "+//50
					" "+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE), "+//51
					"NVL(A.REGISTERED_ADDRESS1,'-'), "+//52
					"NVL(A.REGISTERED_ADDRESS2,'-'), "+//53
					"NVL(A.REGISTERED_CITY_CODE,'-'), "+//54
					" "+m_schema_name+".AF_CO_GET_CITY_NAME(A.REGISTERED_CITY_CODE), "+//55
					"NVL(A.KEY_DECISION_MAKER,'-'), "+//56
					"NVL(A.CONTACT_FOR_PAYMENT,'-'), "+//57
					"NVL(A.DESIGNATION,'-'), "+//58
					""+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(A.CLIENT_CODE), "+	//59
					"NVL(SECTOR_CODE,'-'), "+//60
					"NVL(BUSINESS_SUB_SECTOR,'-'), "+//61
					"nvl("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(SECTOR_CODE),'-'), "+					
					"nvl("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(BUSINESS_SUB_SECTOR),'-'), "+	
					"NVL("+m_schema_name+".AF_CO_GET_EXPOSURE_AS_GUAR(A.CLIENT_CODE),0) "+//64 Added by Dineth on 2008-12-02
					//"(SELECT NVL(SUM(C.CREDIT_LIMIT),0) FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY C WHERE C.CLIENT_CODE=A.CLIENT_CODE AND C.FACILITY_STATUS='Y') "+//65 Added by Dineth on 2008-12-31
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A , "+m_schema_name+".AF_CO_MAS_CLIENT_RELATIVE B "+
					" WHERE A.CLIENT_CODE=UPPER('"+m_client_code+"') AND A.CLIENT_CODE=B.CLIENT_CODE(+) ");
				
				
				
				out.println("<HTML><HEAD><TITLE>Client Information  - "+m_client_code+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("	function assign_type(){");
				out.println("document.Form1.hid_client_type.value='Corporate' ");
				out.println("	}");
				
				out.println("	function show_other_cont_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"other_client_info\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_OTHER_CLIENT_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_employment_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"guarantor\"; ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_EMPLOYMENT_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_director_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"director\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_DIRECTOR_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_business_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"business\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_BUSINESS_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_subsidiary_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"subsidiary\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_SUBSIDIARY_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_bank_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"bank\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_BANK_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_auditor_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"auditor\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_AUDITOR_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_credit_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"credit\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_CREDIT_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_non_related_ref_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"non_related_ref\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_NON_RELATED_REF_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_income_expense_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"income_expense\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_INCOME_EXPENSE_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_family_member_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"family_member\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_FAMILY_MEMBER_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_prop_security_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"security\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_PROP_SECURITY_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_product_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"product\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_PRODUCT_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_supplier_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"supplier\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_SUPPLIER_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_top_debtor_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"debtor\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_TOP_DEBTOR_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	function show_transaction_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"transaction\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_TRANSACTION_HISTORY_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				
				out.println("	function show_realisation_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"realisation\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_REALISATION_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				
				out.println("	function show_pod_cheque_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"post_dated_cheque_in_hand\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_POD_CHEQUE_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				
				out.println("	function show_std_order_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"show_std_order_info\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_STD_ORDER_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				
				out.println("function show_std_order_drill(m_std_order_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_STD_ORDER_DRILL&url="+url+"&std_order_no='+m_std_order_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=250,width=500,resizable=1');"); 
				out.println("}");
				
				
				/*out.println("function show_settle_receipt_drill(m_receipt_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_SETTLE_RECEIPT_DRILL&receipt_no='+m_receipt_no+'';"); 
			  out.println("window.open(m_url,'displayWindow3','status=0,menubar=0,scrollbars=1,height=450,width=700');"); 
				out.println("}");*/
				
				out.println("function show_settle_receipt_drill(m_receipt_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_SETTLE_RECEIPT_DRILL&url="+url+"&receipt_no='+m_receipt_no+'';"); 
				out.println("window.open(m_url,'displayWindow3','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("	function show_contract_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"contract\";  ");
				//out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_CONTRACT_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Sql_Querys?chksql=SHOW_CONTRACT_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				//out.println("  window.open(m_url);"); //TEST MCP
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				
				out.println("function show_application_drill(m_application_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_APPLICATION_DETAIL_DRILL&url="+url+"&application_no='+m_application_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("function show_finance_drill(m_finance_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_FINANCE_DETAIL_DRILL&url="+url+"&finance_no='+m_finance_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("	function show_asset_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"asset\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_ASSET_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				
				out.println("function show_asset_drill(m_asset_id){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_ASSET_DETAIL_DRILL&url="+url+"&asset_id='+m_asset_id+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("	function show_rental_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"rental\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_RENTAL_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				
				out.println("function show_rent_invoiced_drill(m_application_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_RENT_DETAIL_INVOICED_DRILL&url="+url+"&application_no='+m_application_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("function show_rent_settled_drill(m_application_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_RENT_DETAIL_SETTLED_DRILL&url="+url+"&application_no='+m_application_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("function show_rent_balance_to_be_received_drill(m_application_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_RENT_DETAIL_BAL_TO_BE_RECEIVED_DRILL&url="+url+"&application_no='+m_application_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("function show_balance_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"balances\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_BALANCES_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("}");
				
				//=====================================================================================//			
				out.println("function show_odi_cal_drill(m_application_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&url="+url+"&application_no='+m_application_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("function show_odi_bal_drill(m_application_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&url="+url+"&application_no='+m_application_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("function show_odi_set_drill(m_application_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&url="+url+"&application_no='+m_application_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");			
				//=====================================================================================//
				out.println("	function show_invoice_info(m_invoice_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_INVOICE_DRILL&url="+url+"&invoice_no='+m_invoice_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("	}");
				
				out.println("function show_cheque_return_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"cheque_return\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_RETURN_CHEQUE_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("}");
				
				out.println("function show_deposit_drill(m_deposit_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_DEPOSIT_DETAIL_DRILL&url="+url+"&deposit_no='+m_deposit_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				
				
				/*out.println("function show_POD_drill(m_pod_ref_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_POD_CHEQUE_DRILL&pod_ref_no='+m_pod_ref_no+'';"); 
			  out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700');"); 
				out.println("}");
				*/
				
				out.println("function show_POD_drill(m_pod_ref_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_POD_CHEQUE_DRILL&url="+url+"&pod_ref_no='+m_pod_ref_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				
				out.println("function show_return_drill(m_return_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_RETURN_DETAIL_DRILL&url="+url+"&return_no='+m_return_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				out.println("function show_payment(m_payment_no){");
				//	out.println("alert('payment no'+m_payment_no);");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_PAYMENT_DRILL&url="+url+"&payment_no='+m_payment_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				//=======================================================================================
				out.println("	function show_guarantor_info(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"guarantor_info\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_GUARANTOR_INFO&url="+url+"&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");//Added By Sandun on 30-09-2008
				//=========================================================================================
				//Added by Dineth on 2008-11-27
				out.println("function show_total_client_exposure(m_client_code){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_TOTAL_CLIENT_EXPOSURE&url="+url+"&client_code=\"+m_client_code+\"\";"); 
				out.println("window.open(m_url,'displayWindow4','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");				
				//End by Dineth on 2008-11-27
				
				// Added below by Udara on 07-10-2011
				out.println("	function show_loan_facility_assign(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"loan_facility\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_client_loan_facility_drill_down?chksql=LOAN_FACILITY_DETAILS&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				// End by udara on 07-10-2011
				
				// added by udara on 15-11-2013
				out.println("	function show_client_guar_details(m_client_code){");
				out.println("	 document.Form1.hid_link_type.value = \"client_guar_details\";  ");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_client_guar_details_drill_down?chksql=CLIENT_GUAR_DETAILS&client_code=\"+m_client_code+\"\";");
				out.println("  load_interface(m_url,'NORM');");
				out.println("	}");
				// added by udara on 15-11-2013
				
				// added by udara 12-12-2013
				out.println("function show_client_drill(m_client_code){");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs?chksql=SHOW_CLIENT_INFORMATION&client_code=\"+m_client_code+\"\";");
				out.println("  window.open(m_url,'displayWindow4','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				// end by udara 12-12-2013
				
				
				out.println("function clear_inner(){");
				out.println("      other_cont_info.innerHTML = \"\";");
				out.println("      third_party_guarantor_info.innerHTML = \"\";");
				out.println("      subsidiary_info.innerHTML = \"\";");
				out.println("      bank_info.innerHTML = \"\";");
				out.println("      auditor_info.innerHTML = \"\";");
				out.println("      credit_info.innerHTML = \"\";");
				out.println("      security_info.innerHTML = \"\";");
				out.println("      product_info.innerHTML = \"\";");
				out.println("      supplier_info.innerHTML = \"\";");
				out.println("      top_debtor_info.innerHTML = \"\";");
				out.println("}");
				
				out.println("	function get_vector_normal(http_response){");
				out.println("  if(document.Form1.hid_link_type.value == \"guarantor_info\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Guarantor Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");	
				out.println("	 }");
				
				// Added below by Udara on 07-10-2011
				out.println("  else if(document.Form1.hid_link_type.value == \"loan_facility\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Loan Facility Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				// End by udara on 07-10-2011
				
				// Added below by Udara on 15-11-2013
				out.println("  else if(document.Form1.hid_link_type.value == \"client_guar_details\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Client Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				// End by udara on 15-11-2013
				
				out.println(" else if(document.Form1.hid_link_type.value == \"other_client_info\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Other Client Information </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");	
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"guarantor\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Employment Details</u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"director\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Directors/Partners/Shareholders Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				//out.println("      director_info.innerHTML = http_response;");
				
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"subsidiary\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Subsidiaries & Associated Companies Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"bank\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Accounts & Banking Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"auditor\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Auditors Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"credit\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Credit Facilities Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"security\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Proposed Security Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"product\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Product Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"supplier\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Suppliers/Customers Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"debtor\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Top Debtors/Clients Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"non_related_ref\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Non Related Referee Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"income_expense\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Income Expense Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"family_member\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Family Member Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"business\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Business Activity Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				out.println("  else if(document.Form1.hid_link_type.value == \"transaction\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Transaction History Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				
				out.println("  else if(document.Form1.hid_link_type.value == \"realisation\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Receipt Pending Realisation Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				
				out.println("  else if(document.Form1.hid_link_type.value == \"contract\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Contract Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				
				out.println("  else if(document.Form1.hid_link_type.value == \"asset\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Asset Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				
				out.println("  else if(document.Form1.hid_link_type.value == \"rental\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width=140%' class=div_input><b><u>Rental and Balance Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				
				out.println("  else if(document.Form1.hid_link_type.value == \"balances\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Receipts Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				
				out.println("  else if(document.Form1.hid_link_type.value == \"cheque_return\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Cheque Return History Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				
				out.println("  else if(document.Form1.hid_link_type.value == \"post_dated_cheque_in_hand\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Post Dated Cheque Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				
				out.println("  else if(document.Form1.hid_link_type.value == \"show_std_order_info\" ) {");
				out.println("			 label_all.innerHTML =\"<table width='100%' ><tr class=pdn_txtpos2><td width='1%'></td><td width='*%' class=div_input><b><u>Standing Order Details </u></b></td></tr></table>\" ");
				out.println("      all_info.innerHTML = http_response;");
				out.println("	 }");
				
				
				out.println("	}");
				
				
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_director_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_guarantor_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_subsidiary_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_bank_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_auditor_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_credit_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_security_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_product_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_supplier_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_debtor_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_other_client_info_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_all_info_visibily' VALUE=\"hidden\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_link_type' VALUE=\"\">"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Client Information  - "+m_client_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Status</td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(50)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Code</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Type</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Client Exposure</td>");
					//out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(59))+"</td>");//Commented by Dineth on 2008-11-27
					//out.println("<td width='50%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_total_client_exposure('"+m_client_code+"')\"><U>"+nf.format(rs.getDouble(59)+rs.getDouble(64)+rs.getDouble(65))+"</U></td>");//Added by Dineth on 2008-11-27
					out.println("<td width='50%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_total_client_exposure('"+m_client_code+"')\"><U>"+nf.format(rs.getDouble(59)+rs.getDouble(64))+"</U></td>");//Added by Dineth on 2008-11-27

					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					//------------------------------------------------------------------------------------
					//----modified by : delanjali--------------------------------------------------------------------------------
					//----date				: 2007-06-20--------------------------------------------------------------------------------
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><u><b>Bussiness Information</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Business Sector</td>");
					out.println("<td width='50%' class=div_input  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_buss_sectors('"+rs.getString(60)+"')\"><U>"+rs.getString(62)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Business Sub Sector</td>");
					out.println("<td width='50%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_buss_sub_sectors('"+rs.getString(61)+"')\"><U>"+rs.getString(63)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					
					out.println("</table>");
					out.println("<br>");
					
					//------------------------------------------------------------------------------------
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><u><b>Contact Information</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					if(rs.getString(2).equals("Individual")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>Address </td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(14)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(51)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					else{
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>Address </td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(52)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(53)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input></td>");
						out.println("<td width='50%' class=div_input>"+rs.getString(55)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>General Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>General FAX No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Office Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Mobile Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Email Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Key Decision Maker</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(56)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(57)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Designation Payment</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(58)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_other_cont_info('"+m_client_code+"')\"><u><b>More Client Information &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"other_cont_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("</table>");
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_transaction_info('"+m_client_code+"')\"><u><b>Transaction History &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"transaction_info\" >");
					out.println("</div>");
					out.println("<br>");
					
					
					//	out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_realisation_info('"+m_client_code+"')\"><u><b>Receipt Pending Realisation &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"realisation_info\" >");
					out.println("</div>");
					out.println("<br>");
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_contract_info('"+m_client_code+"')\"><u><b>Contract Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"contract_info\" >");
					out.println("</div>");
					out.println("<br>");
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_asset_info('"+m_client_code+"')\"><u><b>Asset Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"asset_info\" >");
					out.println("</div>");
					out.println("<br>");
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_rental_info('"+m_client_code+"')\"><u><b>Rental and Balance Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"rental_info\" >");
					out.println("</div>");
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_balance_info('"+m_client_code+"')\"><u><b>Receipts Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"balance_info\" >");
					out.println("</div>");
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_cheque_return_info('"+m_client_code+"')\"><u><b>Cheque Return History Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"cheque_return_info\" >");
					out.println("</div>");
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_pod_cheque_info('"+m_client_code+"')\"><u><b>Post Dated Cheque Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_std_order_info('"+m_client_code+"')\"><u><b>Standing Order Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					//out.println("<div id=\"standing_order\" >");
					//out.println("</div>");
					out.println("<br>");
					
					
					
					
					
					if(rs.getString(2).equals("Corporate")){
						//out.println("assign_type();");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_director_info('"+m_client_code+"')\"><u><b>Directors/Partners/Shareholders Details &raquo;</b></u></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<div id=\"director_info\" >");
						out.println("</div>");
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_business_info('"+m_client_code+"')\"><u><b>Business Activity Details &raquo;</b></u></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<div id=\"director_info\" >");
						out.println("</div>");
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_subsidiary_info('"+m_client_code+"')\"><u><b>Subsidiaries & Associated Companies Details &raquo;</b></u></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<div id=\"subsidiary_info\" >");
						out.println("</div>");
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_auditor_info('"+m_client_code+"')\"><u><b>Auditors Details &raquo;</b></u></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<div id=\"auditor_info\" >");
						out.println("</div>");
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_supplier_info('"+m_client_code+"')\"><u><b>Suppliers/Customers Details &raquo;</b></u></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<div id=\"supplier_info\" >");
						out.println("</div>");
					}
					if(rs.getString(2).equals("Individual")){
						//out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_employment_info('"+m_client_code+"')\"><u><b>Employment Details &raquo;</b></u></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<div id=\"third_party_guarantor_info\" >");
						out.println("</div>");
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_income_expense_info('"+m_client_code+"')\"><u><b>Income Expense Details &raquo;</b></u></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<div id=\"credit_info\" >");
						out.println("</div>");
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_family_member_info('"+m_client_code+"')\"><u><b>Family Member Details &raquo;</b></u></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<div id=\"credit_info\" >");
						out.println("</div>");
					}
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_bank_info('"+m_client_code+"')\"><u><b>Accounts & Banking Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"bank_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_credit_info('"+m_client_code+"')\"><u><b>Credit Facilities Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"credit_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_non_related_ref_info('"+m_client_code+"')\"><u><b>Non Related Referee Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<div id=\"credit_info\" >");
					out.println("</div>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_guarantor_info('"+m_client_code+"')\"><u><b>Guarantor Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					// Added by udara on 07-10-2011
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_loan_facility_assign('"+m_client_code+"')\"><u><b>Loan Facility Assign Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					// end by udara on 07-10-2011
					
					// added by udara 15-11-2013
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='40%' class=div_input style=\"cursor:hand\" onclick=\"show_client_guar_details('"+m_client_code+"')\"><u><b>Client Details &raquo;</b></u></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					// end by udara 15-11-2013
					
					out.println("<div id=\"credit_info\" >");
					out.println("</div>");
					
					
					
					out.println("<br><br>");
					out.println("<div id=\"label_all\" >");
					out.println("</div>");
					out.println("<div id=\"all_info\" >");
					out.println("</div>");
					
				}
				else{
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				//---MODIFIED BY : DELANJALI---------------------------------------------------------------------------------
				//---DATE				 : 2007-07-27--------------------------------------------------------------------------------
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url1+"/ajax_data_gateway.js'></SCRIPT>"); 
				//-----------------------------------------------------------------------------------------------------------
				out.println("</BODY></HTML>");
			}
			/*
			else if(m_chksql.equals("SHOW_OTHER_CLIENT_INFO")){
			
				String m_string="";				
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT "+
	  		  "NVL(A.CLIENT_CODE,'-'), "+//1
				  "DECODE(A.CLIENT_TYPE,'I','Individual','Corporate'), "+//2
				  "A.TITLE, "+//3
					"NVL(A.FIRST_NAME,'-'), "+//4
					"NVL(A.SURNAME,'-'), "+//5
					"NVL(A.NIC_NO,'-'), "+//6
					"NVL(A.TEL_NO,'-'), "+//7
					"NVL(A.MOBILE_NO,'-'), "+//8
					"NVL(A.ADDRESS1,'-'), "+//9
					"NVL(A.INITIALS,'-'), "+//10
					"NVL(A.FULL_NAME,'-'), "+//11
					"NVL(A.OTHER_NAME,'-'), "+//12
					"NVL(A.RESIDENTIAL_STATUS,'-'),"+//13
  		    "NVL(A.ADDRESS2,'-'), "+//14
					"NVL(A.OFFICE_TEL_NO,'-'), "+//15
					"NVL(A.FAX_NO,'-'), "+//16
					"NVL(A.EMAIL,'-'), "+//17
					"NVL(A.DURATION_AT_YEARS,0), "+//18
					"NVL(A.DURATION_AT_MONTHS,0), "+//19
					"NVL(A.EMP_NAME,'-'), "+//20
  		    "NVL(A.EMP_ADDRESS1,'-'), "+//21
					"NVL(A.EMP_ADDRESS2,'-'), "+//22
					"NVL(A.EMP_REFERENCE,'-'), "+//23
					"NVL(A.EMP_RDESIGNATION,'-'), "+//24
					"NVL(A.EMP_TEL_NO,'-'), "+//25
					"NVL(A.EMP_FAX_NO,'-'), "+//26
					"NVL(B.NAME,'-'), "+//27
					"NVL(B.ADDRESS1,'-') REL_ADD1, "+//28
					"NVL(B.ADDRESS2,'-') REL_ADD2,"+//29
  		    "NVL(B.RELATIONSHIP,'-'), "+//30
					"NVL(B.HOME_TEL_NO,'-'), "+//31
					"NVL(B.OFFICE_TEL_NO,'-') REL_OFF_TEL, "+//32
					"NVL(B.MOBILE_NO,'-') REL_MOB, "+//33
					"NVL(TO_CHAR(A.DATE_OF_BIRTH,'DD-MM-YYYY'),'-'), "+//34
					"NVL(A.PASSPORT_NO,'-'), "+//35
  		    "NVL(A.NATIONALITY,'-'), "+//36
					"NVL(A.MARITAL_STATUS,'-'), "+//37
					"DECODE(A.GENDER,'M','Male','Female'), "+//38
					"NVL(A.BA_NATURE_OF_BUSINESS,'-'), "+//39
					"NVL(A.BA_PROFESSION,'-'), "+//40
					"NVL(A.BA_QUALIFICATIONS,'-'), "+//41
					"NVL(A.BA_DESIGNATION,'-'), "+//42
					"NVL(A.NO_OF_CHILDREN,0), "+//43
  		    "NVL(A.DEPENDENTS,0), "+//44
					"NVL(A.CITY_CODE,'-'), "+//45
					"NVL(A.VAT_REG_NO,'-'), "+//46
					"NVL(A.DRIVING_LICENSE_NO,'-'), "+//47
					"NVL(A.POSTALCODE,'-') POSTALCODE, "+//48
					"NVL(A.GRIB_NO,'-'),  "+//49
					"DECODE(A.ACTIVE_STATUS,'Y','Active','N','Deactive','E','Initial Credit Approval', "+
				  "'I','Waiting for Credit Approval','T','Terminated','B','Black Listed','Other'), "+//50
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.CITY_CODE,'C'), "+//51
					"NVL(A.REGISTERED_ADDRESS1,'-'), "+//52
  			  "NVL(A.REGISTERED_ADDRESS2,'-'), "+//53
  			  "NVL(A.REGISTERED_CITY_CODE,'-'), "+//54
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.REGISTERED_CITY_CODE,'C'), "+//55
					"NVL(A.KEY_DECISION_MAKER,'-'), "+//56
					"NVL(A.CONTACT_FOR_PAYMENT,'-'), "+//57
					"NVL(A.DESIGNATION,'-'), "+//58
					"A.REGISTERED_STATUS, "+//59
  				"NVL(A.CORRESPONDENCE_STATUS,'-'), "+//60
  				"NVL(A.F_TEL_NO,'-'), "+//61
  				"NVL(A.F_FAX_NO,'-'), "+//62
  				"NVL(A.F_EMAIL,'-'), "+//63
  				"NVL(A.ISSUED_SHARE_CAPITAL,0), "+//64
  				"NVL(TO_CHAR(A.DATE_OF_INCORPORATION,'DD-MM-YYYY'),'-'), "+//65
  				"NVL(A.VAT_REG_NO,'-'), "+//66
  				"NVL(TO_CHAR(A.VAT_REG_DATE,'DD-MM-YYYY'),'-'), "+//67
					"NVL(A.BUSINESS_SUB_SECTOR,'-'),"+//68
          "NVL(A.CLIENT_CATEGORY,'-'), "+//69
					"NVL(A.BUSINESS_CERTIFICATE_NO,'-'), "+//70
					"NVL(FACTORY_ADDRESS1,'-'), "+//71
    			"NVL(FACTORY_ADDRESS2,'-'), "+//72
  			  "NVL(FACTORY_STATUS,'-'),  "+//73
			    "NVL(F_CONTACT_PERSON,'-'), "+//74
					"NVL(TEL_NO_GEN,'-'), "+//75
  			  "NVL(FAX_NO_GEN,'-'), "+//76
  			  "NVL(EMAIL_GEN,'-'), "+//77
  			  "NVL(DRIVING_LICENSE_NO,'-') "+//78
				  "FROM "+m_schema_name+".AF_CO_MAS_CLIENT A , "+m_schema_name+".AF_CO_MAS_CLIENT_RELATIVE B "+
				  "WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND A.CLIENT_CODE=B.CLIENT_CODE(+) ");
				
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>CRIB No</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(49)+"</td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input></td>");
					out.println("<td width='30%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Business Sub Sector</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(68)+"</td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Client Category</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(69)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					if(rs.getString(2).equals("Corporate")){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Business Certification No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(70)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input></td>");
						out.println("<td width='30%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Date of Incorporation</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(65)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Issued share Capital Rs.</b></td>");
						out.println("<td width='30%' class=div_input>"+nf.format(rs.getDouble(64))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>VAT Reg. No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(66)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>VAT Reg. Date</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(67)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					else{
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Title</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(3)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input></td>");
						out.println("<td width='30%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>First Name</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(4)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Last Name</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(5)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Initials</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(10)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Other Names</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(12)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>NIC No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Passport No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(35)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Date of Birth</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(34)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>No of Children</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(43)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Driving License No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(78)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Dependents</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(44)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Business Activities</b></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>Nature of Business</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(39)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input>Profession</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(40)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>Designation</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(42)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input>Qualifications</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(41)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Factory Information</b></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Factory Status</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(73)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(71)+" "+rs.getString(72)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(74)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(61)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>FAX No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(62)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Email Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(63)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Correspondence Information</b></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Status</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(60)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+" "+rs.getString(14)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(75)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>FAX No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(76)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Email Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(77)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
				}
			  else{
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
			}			*/
			else if(m_chksql.equals("SHOW_EMPLOYMENT_INFO")){
				
				int count = 0;	
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery("SELECT "+
					" NVL(ORGANIZATION,'-'), "+//1
					" NVL(ADDRESS1,'-'), "+//2
					" NVL(TEL_NO,'-'), "+//3
					" NVL(TO_CHAR(FROM_DATE,'DD-MM-YYYY'),'-'), "+//4
					" NVL(TO_CHAR(TO_DATE,'DD-MM-YYYY'),'-'), "+//5
					" NVL(DESIGNATION,'-') "+//6
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_EMPLOYMENT "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				
				boolean more_gua = rs.next();
				if (!more_gua) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_gua){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Organisation</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Tel No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>From Date</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>To Date</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Designation</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_gua = rs.next();
				}
				
			}
			
			else if(m_chksql.equals("SHOW_BROKER_INFO")){
				
				int count = 0;	
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery("SELECT "+
					" A.BROKER_CODE, "+//1
					" A.TITLE ||' '|| A.FIRST_NAME ||' '||A.LAST_NAME, "+//2
					" NVL(A.ID_NO,'-'), "+//3
					" A.ADDRESS1 ||' '|| A.ADDRESS2 , "+//4
					" NVL(A.LOCATION_CODE,'-'), "+//5
					" NVL(A.CITY_CODE,'-'), "+//6
					" NVL(A.CONTACT_NO,'-'), "+//7
					" NVL(A.MOBILE_NO,'-'),"+ //8
					" NVL(A.FAX_NO,'-'),"+ //9
					" NVL(A.SECTOR_CODE,'-'),"+ //10
					" NVL(A.COMMISSION_RATE,0),"+ //11
					" NVL(A.COMMISSION_AMOUNT,0),"+ //12
					" NVL(A.POSTAL_CODE,'-') "+ //13
					
					
					" FROM "+m_schema_name+".AF_CO_MAS_BROKER A "+
					//" WHERE CLIENT_CODE='"+m_client_code+"' ");
				     " WHERE BROKER_CODE='"+m_client_code+"'  ");
				
				/*rs= stmt1.executeQuery("SELECT "+
					" A.BROKER_CODE, "+//1
					" A.TITLE ||' '|| A.FIRST_NAME ||' '||A.LAST_NAME, "+//2
					" NVL(A.ID_NO,'-'), "+//3
					" A.ADDRESS1 ||' '|| A.ADDRESS2 , "+//4
					" A.LOCATION_CODE, "+//5
					" A.CITY_CODE, "+//6
					" A.CONTACT_NO, "+//7
					" A.MOBILE_NO,"+ //8
					" A.FAX_NO,"+ //9
					" A.SECTOR_CODE,"+ //10
					" A.COMMISSION_RATE,"+ //11
					" A.COMMISSION_AMOUNT,"+ //12
					" A.POSTAL_CODE, "+ //13
					" B.PAYEE_WHT, "+
					" B.PAYEE_VAT_REG_NO "+
					////" "+
					//" "+
					" FROM "+m_schema_name+".AF_CO_MAS_BROKER A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
					//" WHERE CLIENT_CODE='"+m_client_code+"' ");
				     " WHERE BROKER_CODE='"+m_client_code+"' AND  A.BROKER_CODE = B.BROKER_CODE ");
				*/
				out.println("<HTML><HEAD><TITLE>Broker Information  - "+m_client_code+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				//out.println("<SCRIPT language=\"JavaScript\">"); 
		
				//out.println("</SCRIPT>");
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				boolean more_gua = rs.next();
				if (!more_gua) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_gua){
					
					/* out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>Broker Information  - "+m_client_code+" </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");*/
					
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Broker Information  - "+m_client_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<br>");
				out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Broker Code</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>ID No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Location Code</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>City Code</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Posatl Code</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Mobile No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Fax No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Sector Code</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Commission Rate</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Commission Amount </td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
				/*	out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Vat Reg No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>WHT</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");*/
					
					
					
					
					out.println("</table>");
					out.println("<br>");
					more_gua = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			
			
			
			else if(m_chksql.equals("SHOW_BANK_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE),   "+//1
					" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),  "+//2
					" NVL(ACCOUNT_NO,'-'),  "+//3
					" NVL(TO_CHAR(FROM_DATE,'DD-MM-YYYY'),'-'),  "+//4
					" NVL(REFERENCE,'-'),  "+//5
					" NVL(TEL_NO,'-'),  "+//6
					" NVL(FAX_NO,'-'),  "+//7
					" NVL(RELATIONSHIP,0)  "+//8
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS  "+
					" WHERE CLIENT_CODE='"+m_client_code+"' AND ACTIVE_STATUS='Y' ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Bank Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Branch Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Account No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>From Date</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Reference</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Fax No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Relationship(Mts)</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			else if(m_chksql.equals("SHOW_CREDIT_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT "+
					"NVL(INSTITUTION,'-'), "+//1
					"NVL(CONTACT_PERSON,'-'), "+//2
					"DECODE(TYPE_OF_FACILITY,'VEHICLE LOAN','Vehicle Loan','HOUSE LOAN','House Loan','PERSONAL LOAN','Personal Loan','CREDIT CARD','Credit Card','TERM LOAN','Term Loan','LEASE','Lease','OVER DRAFT','Over Draft','LC FACILITY','LC Facility','PLEDGE LOAN','Pledge Loan'),  "+//3
					"NVL(SECURITY,'-'), "+//4
					"NVL(APPROVED_AMOUNT,0), "+//5
					"NVL(MONTHLY_RENTAL,0), "+//6
					"NVL(MONTHS,0), "+//7
					"NVL(EQUIPMENT,'-'), "+//8
					"NVL(BALANCE_AMOUNT,0), "+//9     
					"NVL(CONTRACT_NO,'-') "+//10
					"FROM "+m_schema_name+".AF_CO_PRO_APP_CREDIT_FACILITIE "+
					"WHERE CLIENT_CODE='"+m_client_code+"' AND ACTIVE_STATUS='Y' ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Institute Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Type Of Facility</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Security</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Approved Amount</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Monthly Rental</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Period (Mts.)</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Equipment</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Balance Outstanding </td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contract No </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			
			else if(m_chksql.equals("SHOW_GUARANTOR_INFO")){
				String m_client_code=req.getParameter("client_code");
				String m_fin="";
				String guarantor_name ="";
				String nic_reg_no     ="";
				
				rs1= stmt1.executeQuery("SELECT A.FINANCE_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A"+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+ 
					//" AND A.APPLICATION_STATUS <> 'TERMI'  "+ // added by udara 12-12-2014
					" ");
				
				boolean more_fin=rs1.next();
				if(more_fin){
					m_fin = rs1.getString(1);
					//more_fin=rs1.next();
				}
				
				rs= stmt1.executeQuery(" SELECT A.APPLICATION_NO, "+//1
					" A.GUARANTOR_CODE, "+ //2
					" NVL(A.RELATIONSHIP,'-'), "+ //3
					" NVL(A.PERIOD,0), "+ //4
					" NVL(A.TEL_NO,'-'), "+ //5
					" NVL(B.FINANCE_NO,'-') "+//6
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A, "+
					"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+ 
					" AND   A.ACTIVE_STATUS='Y' "+
					//" AND   B.FINANCE_NO='"+m_fin+"' "+ 
					" AND B.CLIENT_CODE='"+m_client_code+"' "+ 
					" ");
				
				boolean more_gurnt = rs.next();
				if (!more_gurnt) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				out.println("<table align='center' width='100%' class='table' >");
				if (more_gurnt) {
					out.println("<tr>");
					out.println("<td width='1%'></td>");
					out.println("<td width='20%' class=div_input ><b>Finance No.</b></td>");
					out.println("<td width='15%' class=div_input ><b>Code </b></td>");
					out.println("<td width='15%' class=div_input ><b>Name </b></td>");
					out.println("<td width='10%' class=div_input ><b>Relationship </b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Telephone No</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>NIC/Business Reg No</b></td>");
					out.println("</tr>");
					out.println("<tr><td></td></tr>");
				}
				while(more_gurnt){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_client_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>"); // out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					
					rs1= stmt.executeQuery(" SELECT NVL(A.FULL_NAME,'-'), "+//1
						//" NVL(NVL(A.NIC_NO,A.BUSINESS_CERTIFICATE_NO),'-')"+//2 // commented by udara 15-06-2017
						" NVL(NVL(NVL(A.NIC_NO,A.PASSPORT_NO),A.BUSINESS_CERTIFICATE_NO),'-')"+//2 // added by udara 15-06-2017
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+
						" WHERE A.CLIENT_CODE ='"+rs.getString(2)+"' ");
					boolean more_gurnt_name = rs1.next();
					if(more_gurnt_name){
						guarantor_name = rs1.getString(1);
						nic_reg_no     = rs1.getString(2);
					}
					out.println("<td width='15%' class=div_input >"+guarantor_name+"</td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='20%' align='right' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='20%' align='right' class=div_input >"+nic_reg_no+"</td>");
					out.println("</tr>");			
					more_gurnt = rs.next();
				}
				
				out.println("</table>");
			}
			else if(m_chksql.equals("SHOW_NON_RELATED_REF_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery("SELECT "+
					" NVL(NAME,'-'), "+//1
					" NVL(PERIOD,0), "+//2
					" NVL(RELATIONSHIP,'-'), "+//3
					" NVL(DESIGNATION,'-'), "+//4
					" NVL(HOME_TEL_NO,'-'), "+//5
					" NVL(OFFICE_TEL_NO,'-'), "+//6
					" NVL(MOBILE_NO,'-') "+//7
					"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_NONRELATIVE "+
					"WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Referee Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Period</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Relationship</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Designation</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Home Tel No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Office Tel No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Mobile No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			
			
			else if(m_chksql.equals("SHOW_TRANSACTION_HISTORY_INFO")){
				//mcp 
				try
				{
				String m_client_code=req.getParameter("client_code");
				
				int count = 0;
				String m_string="";		
				String m_orient_name="";
				String m_name="";
				String m_cheque_no="";
				String m_rec_date = "";
				
				double m_cum_value=0;
				double m_val=0;
				double m_debit=0;
				double m_credit=0;
				
				
				String		Sql_company_details=" SELECT "+
					" COMPANY_NAME "+
					" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				
				String		Sql_client_name=" SELECT "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME('"+m_client_code+"') FROM DUAL ";
				
				
				
				String		Sql_invoice=" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP, "+
					//" NVL(CHEQUE_NO,'-') CHEQUE_NO, "+ // commented by udara on 03-12-2012
					" NVL(CHEQUE_NO,CASE WHEN (DUE_DATE < SYSDATE)  THEN "+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(REF_NO) END) CHEQUE_NO, "+ // added by udara on 03-12-2012
					" DESCRIPTION,STATUS,RE_DATE "+  //
					" FROM "+
					" ( "+
					"  SELECT "+
					"  INVOICE_NO REF_NO, "+
					"  VALUE_DATE   DUE_DATE, "+ // DUE_DATE --modified nuwan de silva 25-07-07 REF NO 708
					"  TOTAL_AMOUNT AMOUNT, "+
					"  'INVOICE' TYP, "+
					//"  NVL(NULL,'-') CHEQUE_NO, "+ // commented by udara on 03-12-2012
					"    NULL CHEQUE_NO, "+ // added by udara on 03-12-2012
					//"  'ACCOUNT RENTAL & VAT RECEIVABLES' DESCRIPTION ,"+
					//"  DECODE(INVOICE_TYPE,'INV_OTHER','Other Invoice','INV_TAX','Tax Invoice','ODI','OD Interest','INV_RESI','Residual Invoice','INV_GENER','RENTAL & VAT') DESCRIPTION, "+ //modified by nuwan de silva on 21-08-07
					//"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),'Other') DESCRIPTION , "+
					"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+
					
					"  NULL STATUS,  "+
					"  '-' RE_DATE   "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"  WHERE CLIENT_CODE='"+m_client_code+"' AND "+
					" ACTIVE_STATUS='Y' "+
					" AND VALUE_DATE <=SYSDATE "+  
					"  UNION "+
					//RECEIPT--------------------------------------------- 
					" SELECT "+
					"     REC_NO REF_NO, "+ //1
					"     EFF_VALDATE  DUE_DATE, "+ //2											
					"     REC_AMOUNT AMOUNT, "+ //3
					"     'RECEIPT' TYP, "+ //4
					//"     'Cheque - '|| NVL(CHEQUE_NO,'-')  CHEQUE_NO ,"+ //5 //Modified by Chandana on 19/10/2007
					"     DECODE(SETTLE_MODE,'CHEQUE','Cheque - '||CHEQUE_NO,'CASH','Cash','STD_ORD','Standing Order','DIR_DEP','Direct Deposit') CHEQUE_NO, "+ //5
					
					//"     DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Receipt - Chq Return','Receipt - Cheque'),'CASH','Receipt - Cash') DESCRIPTION, "+ //6
					"     NVL(OTH_COMMENTS,'-') DESCRIPTION , "+ //added by nwuan de silva 17-10-07
					"     STATUS ,"+ //7
					"     TO_CHAR(REALISED_DATE,'DD-MM-YYYY')  RE_DATE "+ //8
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					" AND EFF_VALDATE <=SYSDATE "+  
					
					//" AND STATUS <> 'C' "+ //comment by nuwan de silva on 24-03-2008
					"	UNION "+
					
					//ODI---------------------------------------------
					" SELECT "+
					"    INVOICE_NO REF_NO, "+
					"    DUE_DATE  DUE_DATE, "+
					"    ODI_SETTLED_AMOUNT AMOUNT, "+ //ODI_SETTLED_AMOUNT  ODI_CAL_AMOUNT //modified by nuwan de silva on 14-09-07 //modified by nuwan de silva on 17-12-07
					"    'ODI' TYP, "+
					"    NULL CHEQUE_NO, "+
					"    'Over Due Interst' DESCRIPTION, "+
					"    NULL STATUS, "+
					"    '-'  RE_DATE "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
					" WHERE ODI_SETTLED_AMOUNT > 0   "+
					" AND  INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE CLIENT_CODE='"+m_client_code+"' AND "+
					"          ACTIVE_STATUS='Y' "+
					") "+
					" AND DUE_DATE <=SYSDATE "+  
					
					//		"  AND ACTIVE_STATUS='Y' "+
					
					
					
					
					//comment by nuwan de silva on 21-12-07
					"	UNION "+
					//CREDIT DEBIT---------------------------------------------
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					" CREDIT_TYPE STATUS,  '-' RE_DATE "+
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE CLIENT_CODE='"+m_client_code+"' AND "+
					"    ACTIVE_STATUS='Y' "+
					" ) "+
					" AND ADJUSTED_DATE <=SYSDATE "+  
					
					//	"    AND ACTIVE_STATUS='Y' "+
					
					
					
					//added by nuwan de silva on 21-12-07
					/*	" UNION "+
					" SELECT "+
					" REF_NO, "+
					" EFF_DATE DUE_DATE, "+
					" AMOUNT,"+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CR_DR_TYPE,'CR','Credit Note','Debit Note') || ' - ' ||  NVL(REMARKS,' ' ) DESCRIPTION, "+
					" CR_DR_TYPE STATUS "+
					" FROM "+m_schema_name+".AF_CO_PRO_CR_DR_DETAILS "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					" AND   ACTIVE_STATUS='Y' "+
 

				  */
					//comment by nuwan de silva on 19-09-07--------------------
					/*" UNION "+
					
				 //PAYMENT---------------------------------------------
			
					" SELECT "+
					
					"   PAYMENT_NO, "+
					"   ENTDATE, "+
					"   PAY_AMOUNT, "+
					"   'OTHER' TYP, "+
					"   CHEQUE_NO, "+
					"   INITCAP(SETTLE_MODE) DESCRIPTION, "+
					"   NULL STATUS "+
					
					"   FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
					"   WHERE CLIENT_CODE='"+m_client_code+"' "+
					*/
					
					
					//RETURN CHARGES---------------------------------------------
					/*"      UNION      "+
					"     SELECT  "+
							"     REC_NO REF_NO,  "+
							"     EFF_VALDATE DUE_DATE, "+
							"     100  AMOUNT,  "+
							"     'RET_CHARGE' TYP, "+
							"     CHEQUE_NO  CHEQUE_NO , "+
							"     'Return Cheque Charges'  DESCRIPTION,  "+ 
					"               STATUS  "+
							"     FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+ 
							"     WHERE CLIENT_CODE='"+m_client_code+"'  "+
							"     AND STATUS ='RET'  "+
							*/
					//commented by SH on 04-07-2008
					/*
							"   UNION      "+
							"		SELECT    "+
							"		RETURN_NO REF_NO,   "+
							"		EFF_VALDATE DUE_DATE,  "+
							"		NVL(RETURN_CHARGE,0)  AMOUNT,   "+
							"		'RET_CHARGE' TYP,  "+
							"		'Cheque - '||CHEQUE_NO  CHEQUE_NO ,  "+
							"		'Return Cheque Charges'  DESCRIPTION,    "+
							"		STATUS   "+
							"		FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B "+
							"		WHERE A.REC_NO=B.RECEIPT_NO "+
							"		AND CLIENT_CODE='"+m_client_code+"'   "+
							"		AND STATUS='RET' "+
							"   AND EFF_VALDATE <=SYSDATE "+  
					*/
					
					
					" ) "+ 
					
					" ORDER BY DUE_DATE ";
				
				
				
				rs=stmt1.executeQuery(Sql_company_details);
				boolean  more =rs.next();
				
				//out.println(Sql_invoice);
				
				if(more)
				{
					m_orient_name=rs.getString(1);
				}
				
				rs=stmt1.executeQuery(Sql_client_name);
				more =rs.next();
				
				
				
				if(more)
				{
					m_name=rs.getString(1);
				}
				
				
				
				rs=stmt1.executeQuery(Sql_invoice);
				boolean  more_inv =rs.next();
				
				
				if (!more_inv) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_inv){
					count++;
					
					if(count==1){
						
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='*%'align='center' class=div_input><b>"+m_orient_name.toUpperCase()+"</b></td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td width='*%'align='center' class=div_input><b>Asset Finance Ledger</b></td>");
						out.println("</tr>");
						out.println("</table>");
						
						out.println("<hr color='black'>");
						
						out.println("<br>");
						
						out.println("<table align='center' width='100%' class='table' >");
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>Lessee Name</td>");
						out.println("<td width='50%' class=div_input>"+m_name+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						
						out.println("</table>");
						
						out.println("<br>");
						
						out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>Date</td>");
						out.println("<td width='15%' class=div_input>Doc Ref</td>");
						out.println("<td width='30%' class=div_input>Narration</td>");
						out.println("<td width='10%' class=div_input>Reference No.</td>"); //Cheque No.
						out.println("<td width='10%' class=div_input align='right'>Debit</td>");
						out.println("<td width='10%' class=div_input align='right'>Credit</td>");
						out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
						out.println("<td width='4%' class=div_input></td>");
						out.println("</tr>");
						
						
					}
					m_debit =0;
					m_credit=0;
					
					if(rs.getString(4).equals("INVOICE")){
						m_debit=rs.getDouble(3);
						
					}
					
					else if(rs.getString(4).equals("RECEIPT")){
						if(rs.getString(7).equals("RET")){							
							m_debit=rs.getDouble(3);
							m_credit=rs.getDouble(3);
							m_rec_date = rs.getString(8);
							
						}
						else
						{
							m_credit=rs.getDouble(3);	
							m_rec_date = rs.getString(2);
						}
					}
					
					else if(rs.getString(4).equals("DR/CR")){
						
						if(rs.getString(7).equals("DR")){
							m_debit=rs.getDouble(3);
						}
						else
						{
							m_credit=rs.getDouble(3);
						}
						
					}
					
					else if(rs.getString(4).equals("ODI")){
						
						m_debit=rs.getDouble(3);
						
					}
					else if(rs.getString(4).equals("OTHER")){
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RET_CHARGE")){ //added by nuwan de silva on 14-08-07
						m_debit=rs.getDouble(3);
						
					}
					
					m_val=m_debit-m_credit;
					
					m_cum_value=m_cum_value+m_val;
					
					
					
					if(rs.getString(4).equals("INVOICE")){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						//out.println("<td width='10%' class=div_input>&nbsp;</td>"); // commented by udara on 03-12-2012
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>"); // added by udara on 03-12-2012
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						out.println("</tr>");
						
					}
					
					else if(rs.getString(4).equals("RECEIPT")){
						
						if(rs.getString(7).equals("RET")){
							//m_val=m_debit-m_credit;
							m_val=m_credit;
							m_cum_value=m_cum_value-m_val;					
							
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							}
							
							//if(rs.getString(7).equals("RET") ){
							//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							//}
							//else
							//{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							//}
							
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}
							out.println("</tr>");
							
							//m_val=m_debit-m_credit;
							m_val=m_debit;
							m_cum_value=m_cum_value+m_val;
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(8)+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>"+rs.getString(6)+" - Return </td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							}
							//if(rs.getString(7).equals("RET") ){
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							//}
							//else
							//{
							//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							//}
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}else{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}
							out.println("</tr>");
						}
						//================================================
						
						else if(!rs.getString(7).equals("RET") ){
							
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+m_rec_date+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							}
							
							if(rs.getString(7).equals("RET") ){
								out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							}
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}
							out.println("</tr>");
							
							//---------------------------------------------------------------------------------------------------
							if(rs.getString(7).equals("CAD")  || rs.getString(7).equals("C")){
								m_debit =0;
								m_credit=0;
								m_debit=rs.getDouble(3);
								
								m_val=m_debit-m_credit;
								m_cum_value=m_cum_value+m_val;
								
								out.println("<tr>");
								out.println("<td width='1%'></td>"); 
								out.println("<td width='10%' class=div_input>"+m_rec_date+"</td>");
								out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
								out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
								if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
									out.println("<td width='10%' class=div_input>&nbsp;</td>");
								}
								else
								{
									out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								}
								
								//if(rs.getString(7).equals("RET") ){
								out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								//}
								//else
								//{
								//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
								//	}
								out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
								if(m_cum_value>0){
									out.println("<td width='4%' class=div_input>&nbsp;</td>");
								}
								else
								{
									out.println("<td width='4%' class=div_input>Cr</td>");
								}
								out.println("</tr>");
							}
						}
						//---------------------------------------------------------------------------------------------------
						
						
					}
					/*else if(rs.getString(4).equals("RECEIPT")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
          //---------------------------------------------------------------------------------------------------
					if(rs.getString(7).equals("CAD")  || rs.getString(7).equals("C")){
					m_debit =0;
					m_credit=0;
					m_debit=rs.getDouble(3);
          				
					m_val=m_debit-m_credit;
          m_cum_value=m_cum_value+m_val;

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					//if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
				  //	}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					//---------------------------------------------------------------------------------------------------
					
					
					}*/
					//return charges------------
					else if(rs.getString(4).equals("RET_CHARGE")){
						
						
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
						}
						
						
						if(rs.getString(7).equals("RET") ){
							
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						}
						
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						
						
						out.println("</tr>");
						
					}
					//--------------------
					else if(rs.getString(4).equals("ODI")){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						//out.println("<td width='10%' class=div_input>&nbsp;</td>"); // commented by udara on 03-12-2012
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>"); // added by udara on 03-12-2012
						
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						out.println("</tr>");
						
					}
					
					else if(rs.getString(4).equals("OTHER")){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						//out.println("<td width='10%' class=div_input>&nbsp;</td>"); // commented by udara on 03-12-2012
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>"); // added by udara on 03-12-2012
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						out.println("</tr>");
						
					}
					
					
					else if(rs.getString(4).equals("DR/CR")){
						
						
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						//out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
						
						
						if(rs.getString(7).equals("DR") ){
							
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						}
						
						
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						
						
						out.println("</tr>");
						
					}
					
					
					
					
					more_inv = rs.next();
				}
				
				out.println("</table>");
				
				
				String		Sql_Unallocated=" SELECT "+ 
					"  A.REC_NO, "+
					"  A.REC_AMOUNT, "+
					"  B.allocated_amount, "+
					"  B.bal_tobe_receive, "+
					"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
					"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"  WHERE  A.REC_NO=B.REC_NO /*REC_NO NOT IN */ "+
					"  AND A.CLIENT_CODE=UPPER('"+m_client_code+"') AND  B.BAL_TOBE_RECEIVE > 0 AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
				
				
				rs=stmt.executeQuery(Sql_Unallocated);
				more =rs.next();
				
				if (more) {
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipts Details</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Effective Value Date</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Receipt Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Allocated Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Balance To Be Allocated</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double sum_amount=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp;</td>");
					
					out.println("</tr>");
					sum_amount=sum_amount+rs.getDouble(4);
					more = rs.next();
				}
				
				if(sum_amount >0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='10%' class=div_input >&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(sum_amount)+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
				}
				out.println("</table>");
				
				
				//Added by Chandana on 04/10/2007
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Client Special Comments </B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");
				
				rs=stmt1.executeQuery("SELECT TO_CHAR(ENT_DATE,'DD/MM/YYYY'),COMMENTS,ENT_USER "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT "+
					" WHERE CLIENT_CODE ='"+m_client_code+"'");
				boolean  more4 =rs.next();			
				
				if(more4){
					out.println("<table align='center' width='100%' class='table' >");
					while(more){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input valign='top'><B>Date: </B>"+rs.getString(1)+" &nbsp <B>User: </B>"+rs.getString(3)+"</td>");
						out.println("<td width='2%'>&nbsp</td>");
						out.println("<td width='70%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr></tr>");
						more4 =rs.next();
					}
					out.println("<tr></tr>");
					out.println("</table>");
				}else{
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' align='center' class=div_input >No Special Comments</td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				out.println("<br>");
				out.println("<br>");
				/*
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Comments - Follow Up</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");
				
				
				rs=stmt1.executeQuery
				   (" (SELECT to_char(ENT_DATE,'DD-MM-YYYY') FROM DUAL ), "+
					"  NVL(ENT_REMARKS,'-')   , "+
					"  SELECT "+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER) "+
					" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
					" WHERE DIVISION_CODE='AF' AND "+
					" SUB_DIVISION_CODE='RECOVERY' AND "+
					" ID_NO IN (SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE "+
					" CLIENT_CODE=UPPER('"+m_client_code+"') ) ");
				
				
				
				boolean  more5 =rs.next();			
				
				if(more5){
					out.println("<table align='center' width='100%' class='table' >");
					while(more){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input valign='top'><B>Date: </B>"+rs.getString(1)+" &nbsp <B>User: </B>"+rs.getString(3)+"</td>");
						out.println("<td width='2%'>&nbsp</td>");
						out.println("<td width='70%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr></tr>");
						more5 =rs.next();
					}
					out.println("</table>");
				}else{
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' align='center' class=div_input >No Follow Up Comments</td>");
					out.println("</tr>");
					out.println("</table>");
				}	
				*/
				
				}			
				catch(Exception e)	
				{
				
				
				
						ByteArrayOutputStream ostrtest = new ByteArrayOutputStream();
					    e.printStackTrace(new PrintStream(ostrtest));
						out.println("test"+ostrtest.toString());
				
					}				
				
				//mcp						
			}
			
			
			/*		else if(m_chksql.equals("SHOW_CONTRACT_INFO")){
					
					int count = 0;
					String m_string="";								
					String m_client_code=req.getParameter("client_code");
					
						
						
					String		Sql_Contracts=" SELECT "+
											" APPLICATION_NO, "+
											" NVL(FINANCE_NO,'-'), "+
											" NVL(TRANSACTION_TYPE,'-'), "+
											" APPLICATION_STATUS "+
											" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
											" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')  ";
				
					
					
			
			
					rs=stmt1.executeQuery(Sql_Contracts);
					boolean  more =rs.next();
							
				
			if (!more) {
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					
					if (more) {
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Application No</b></td>");
						out.println("<td width='15%' class=div_input><b>Finance No</b></td>");
						out.println("<td width='15%' class=div_input><b>Transaction Type</b></td>");
						out.println("<td width='10%' class=div_input><b>Application Status</b></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						out.println("</table>");
						out.println("<br>");
					}
					out.println("<table align='center' width='100%' class='table' >");
					while(more){
						count++;
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_application_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_application_receipt_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(4)+"</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
						more = rs.next();
					}
					
			
						out.println("</table>");
						
					
					
				}
				
				
				*/
			
			
			
			
			
			
			else if(m_chksql.equals("SHOW_INCOME_EXPENSE_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT "+
					" NVL(B.DESCRIPTION,'-'), "+//1
					" NVL(A.AMOUNT,0), "+//2
					" DECODE(A.TYPE,'IN','Income','Expense') TYPE "+//3
					"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_INCOME_EXPEN A,LAKDL.AF_CO_MAS_INCOME_EXPENCE_TYPE B  "+
					"WHERE CLIENT_CODE='"+m_client_code+"' AND A.I_E_CODE=B.I_E_CODE "+
					"ORDER BY TYPE DESC ");
				
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Description</b></td>");
					out.println("<td width='8%' class=div_input><b>Type</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Amount</b></td>");
					//out.println("<td width='1%'></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='8%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					//out.println("<td width='1%'></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					//out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			else if(m_chksql.equals("SHOW_FAMILY_MEMBER_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT "+
					" MEMBER, "+//1
					" NVL(NAME,'-'), "+//2
					" NVL(ADDRESS1,'-'), "+//3
					" NVL(AGE,0), "+//4
					" NVL(TELEPHONE_NO,'-'), "+//5
					" NVL(MOBILE_NO,'-') "+//6
					"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_FAMILY_MEMBER "+
					"WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Member</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Member Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Age</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Tel No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Mobile No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}	
			
			
			
			
			
			
			
			else if(m_chksql.equals("SHOW_INCOME_EXPENSE_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT "+
					" NVL(B.DESCRIPTION,'-'), "+//1
					" NVL(A.AMOUNT,0), "+//2
					" DECODE(A.TYPE,'IN','Income','Expense') TYPE "+//3
					"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_INCOME_EXPEN A,LAKDL.AF_CO_MAS_INCOME_EXPENCE_TYPE B  "+
					"WHERE CLIENT_CODE='"+m_client_code+"' AND A.I_E_CODE=B.I_E_CODE "+
					"ORDER BY TYPE ");
				
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Description</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Amount</b></td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Type</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					//out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			else if(m_chksql.equals("SHOW_FAMILY_MEMBER_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT "+
					" MEMBER, "+//1
					" NVL(NAME,'-'), "+//2
					" NVL(ADDRESS1,'-'), "+//3
					" NVL(AGE,0), "+//4
					" NVL(TELEPHONE_NO,'-'), "+//5
					" NVL(MOBILE_NO,'-') "+//6
					"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_FAMILY_MEMBER "+
					"WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Member</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Member Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Age</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Tel No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Mobile No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}	
			else if(m_chksql.equals("SHOW_DIRECTOR_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT "+
					" NVL(NAME,'-'), "+//1
					" NVL(NIC_NO,'-'), "+//2
					" NVL(STAKE,0), "+//3
					" NVL(NO_OF_SHARES,0), "+//4
					" NVL(VALUE,0), "+//5
					" NVL(POSITION,'-') "+//6
					"FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS "+
					"WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Director Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Director NIC</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Stake</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Number of Shares</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Value</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Position</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			else if(m_chksql.equals("SHOW_BUSINESS_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT "+
					" NVL(ACTIVITY,'-'), "+//1
					" NVL(INITCAP(CAT_TYPE_CODE),'-') "+//2
					"FROM "+m_schema_name+".AF_CO_PRO_APP_BUSINESS_ACTIVI "+
					"WHERE  CLIENT_CODE='"+m_client_code+"' ");
				
				boolean more_dir = rs.next();
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Activity</b></td>");
					out.println("<td width='20%' class=div_input><b>Category</b></td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					//out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			else if(m_chksql.equals("SHOW_SUBSIDIARY_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT  "+
					" NAME,   "+//1
					" NVL(STAKE,0),  "+//2
					" NVL(VALUE,0),  "+//3
					" NVL(TEL_NO,'-'),  "+//4
					" NVL(OFFICER,'-'),  "+//5
					" NVL(ACTIVITIES,'-')  "+//6
					" FROM "+m_schema_name+".AF_CO_MAS_SUBSIDIARIES  "+
					" WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				
				boolean more_comp = rs.next();
				if (!more_comp) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_comp){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b>Company Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Stake</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Value</td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No.</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Officer</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Business Activities</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_comp = rs.next();
				}	
				
			}
			else if(m_chksql.equals("SHOW_AUDITOR_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				
				rs= stmt1.executeQuery(" SELECT "+
					" NVL(NAME,'-'), "+//1
					" NVL(ADDRESS1,'-'), "+//2
					" NVL(ADDRESS2,'-'), "+//3
					" NVL(REFERENCE,'-'), "+//4
					" NVL(TEL_NO,'-'), "+//5
					" NVL(FAX_NO,'-'), "+//6
					" NVL(RELATIONSHIP,0) "+//7	
					"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_AUDITOR "+
					"WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Auditor Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Auditor Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+","+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Reference</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Fax No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Relationship</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}			
			else if(m_chksql.equals("SHOW_SUPPLIER_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				
				rs= stmt1.executeQuery(" SELECT "+
					" NVL(CUSTOMER_NAME,'-'), "+//1
					" NVL(ADDRESS,'-'), "+//2
					" NVL(INITCAP(TYPE),'-'), "+//3
					" NVL(TEL_NO,'-'), "+//4
					" NVL(CONTACT_PERSON,'-'), "+//5
					" NVL(RELATIONSHIP,0) "+//6
					"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_CUSTOMERS "+
					"WHERE CLIENT_CODE='"+m_client_code+"' ");
				
				
				boolean more_dir = rs.next();
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>"+count+". </b> Name</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input> Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Type</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Relationship</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
			}
			//-------------------------#################----End of Client Details Drill ----#####################------------------------------------
			
			else if(m_chksql.equals("SHOW_TRANSACTION_TYPE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_trans_code=req.getParameter("trans_code");	
				
				rs= stmt1.executeQuery(" SELECT "+
					"TRAN_CODE, "+	
					" NVL(DESCRIPTION,'-'), "+
					" DECODE(DEFAULT_VALUE,'Y','Yes','No') "+
					"FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
					"WHERE TRAN_CODE='"+m_trans_code+"' AND ACTIVE_STATUS='Y' ");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Transaction Type Details - Transaction Type Code: "+m_trans_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Transaction Type Details - Transaction Type Code: "+m_trans_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Transaction Type Code "+m_trans_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Transaction Type Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Transaction Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_TRANSACTION_SUB_TYPE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_trans_sub_code=req.getParameter("trans_sub_code");	
				
				rs= stmt1.executeQuery("SELECT "+
					" A.TRN_SUB_TYPE, "+//1
					" NVL(A.DESCRIPTION,'-'), "+//2
					" NVL(A.TRN_CODE,'-'), "+//3
					" NVL(B.DESCRIPTION,'-'), "+//4    
					" NVL(A.RATE,0), "+//5
					" NVL(DECODE(A.DEFAULT_VALUE,'Y','Yes','No'),'-') "+//6
					" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE A,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B "+
					" WHERE A.TRN_CODE=B.TRAN_CODE "+
					"AND  A.TRN_SUB_TYPE='"+m_trans_sub_code+"' AND A.ACTIVE_STATUS='Y' ");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Transaction Sub Type Details - Transaction Sub Type Code: "+m_trans_sub_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Transaction Sub Type Details - Transaction Sub Type Code: "+m_trans_sub_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Transaction Sub Type Code "+m_trans_sub_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='35%' class=div_input><b> Transaction Sub Type Code</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(1)+"</td>");
					//out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='35%' class=div_input><b> Transaction Sub Type</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(2)+"</td>");
					//out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='35%' class=div_input><b> Transaction Type</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(4)+"</td>");
					//out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='35%' class=div_input><b> Rate</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(5)+"</td>");
					//out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='35%' class=div_input><b> Default Value</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(6)+"</td>");
					//out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_ITEM_SUB_CATEGORY_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_item_sub_cat=req.getParameter("item_sub_cat");	
				
				rs= stmt1.executeQuery(" SELECT "+
					" A.ITEM_SUB_CAT, "+//1
					" NVL(A.DESCRIPTION,'-'), "+//2
					" NVL(A.ITEM_CAT_CODE,'-'), "+//3
					" NVL(B.DESCRIPTION,'-'), "+//4						 
					" NVL(A.VAT,0), "+//5
					" NVL(A.VAT_APP,0), "+//6
					" NVL(A.CAPITAL_ALLOWANCE,0),"+//7
					" NVL(TO_CHAR(A.CAP_ALLO_EFF_FATE,'DD-MM-YYYY'),'-'), "+//8
					" NVL(DECODE(A.DEFAULT_VALUE,'Y','Yes','No'),'-') "+//9
					"FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY A,"+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY B "+
					"WHERE A.ITEM_CAT_CODE=B.ITEM_CAT_CODE "+
					"AND A.ACTIVE_STATUS='Y' AND A.ITEM_SUB_CAT=UPPER('"+m_item_sub_cat+"') ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Item Sub Category Details - Item Sub Category Code: "+m_item_sub_cat+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Item Sub Category Details - Item Sub Category Code: "+m_item_sub_cat+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Item Sub Category Code "+m_item_sub_cat+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Item Sub Category Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Item Sub Category</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Item Category</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> VAT Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> VAT Applicable Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Capital Allowance</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Effective Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_ENGINE_CAPACITY_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_capacity_code=req.getParameter("capacity_code");		
				
				rs= stmt1.executeQuery(" SELECT "+
					" NVL(CAPACITY_CODE,'-'), "+//1
					" NVL(DESCRIPTION,'-'), "+//2
					" DECODE(DEFAULT_VALUE,'Y','Yes','No') "+//3
					"FROM "+m_schema_name+".AF_CO_MAS_ENGINE_CAPACITY "+
					"WHERE CAPACITY_CODE='"+m_capacity_code+"' AND ACTIVE_STATUS='Y' ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Engine Capacity Details - Engine Capacity Code: "+m_capacity_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Engine Capacity Details - Engine Capacity Code: "+m_capacity_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Engine Capacity Code "+m_capacity_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Capacity Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Description</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_MAKE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_make_code=req.getParameter("make_code");		
				
				rs= stmt1.executeQuery(" SELECT "+
					" MAKE_CODE, "+
					" NVL(MAKE_DESC,'-'), "+
					" DECODE(DEFAULT_VALUE,'Y','Yes','No') "+
					"FROM "+m_schema_name+".AF_CO_MAS_MAKE "+
					"WHERE ACTIVE_STATUS='Y' AND MAKE_CODE='"+m_make_code+"' ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Make Details - Make Code: "+m_make_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Make Details - Make Code: "+m_make_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Make Code "+m_make_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Make Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Make Description</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_MODEL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_model_code=req.getParameter("model_code");	
				
				rs= stmt1.executeQuery(" SELECT "+
					" A.MODEL_CODE, "+//1
					" NVL(A.DESCRIPTION,'-'), "+//2
					" NVL(A.MAKE_CODE,'-'),  "+//3
					" NVL(B.MAKE_DESC,'-'), "+//4
					" NVL(A.FUEL_TYPE,'-'), "+//5
					" NVL(A.TAX_RATE,0), "+//6
					" NVL(A.TAX_FOR_LEASE,0), "+//7
					" DECODE(A.DEFAULT_VALUE,'Y','Yes','No') "+//8
					"FROM "+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_CO_MAS_MAKE B "+
					"WHERE A.MAKE_CODE=B.MAKE_CODE "+
					"AND A.MODEL_CODE='"+m_model_code+"' AND A.ACTIVE_STATUS='Y' ");
				
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Model Details - Model Code: "+m_model_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Model Details - Model Code: "+m_model_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Model Code "+m_model_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Model Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Model Desc.</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Make</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Fuel Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> TAX Rate</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> TAX For Lease</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_SUB_MODEL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_sub_model_code=req.getParameter("sub_model_code");		
				
				rs= stmt1.executeQuery(" SELECT "+
					" A.SUB_CODE,"+//1
					" NVL(A.DESCRIPTION,'-'), "+//2
					" NVL(A.MODEL_CODE,'-'), "+//3
					" NVL(B.DESCRIPTION,'-'), "+//4
					" NVL(A.ENGINE_CAPACITY,0), "+//5
					" NVL(A.OPTION_TYPE,'-'), "+//6
					" NVL(INITCAP(C.COUNTRY_DESC),'-'), "+//7
					" NVL(A.YEAR_OF_MANUFACTURE,0), "+//8
					" DECODE(A.DEFAULT_VALUE,'Y','Yes','No') "+//9
					"FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE A,"+m_schema_name+".AF_CO_MAS_MODEL B,"+m_schema_name+".AF_CO_MAS_COUNTRY C "+
					"WHERE A.SUB_CODE='"+m_sub_model_code+"' AND A.ACTIVE_STATUS='Y' "+
					"AND A.MODEL_CODE=B.MODEL_CODE AND A.COUNTRY_CODE=C.COUNTRY_CODE ");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Sub Model Details - Sub Model Code: "+m_sub_model_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Sub Model Details - Sub Model Code: "+m_sub_model_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Sub Model Code "+m_sub_model_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sub Model Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sub Model Desc.</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Model</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Engine Capacity</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Option Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Country</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Year of Manufacture</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_ASSET_CONDITION_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_asset_code=req.getParameter("asset_code");			
				
				rs= stmt1.executeQuery(" SELECT "+
					"CODE, "+
					"NVL(DESCRIPTION,'-'), "+
					"DECODE(DEFAULT_VALUE,'N','No','Yes') "+
					"FROM "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET "+
					"WHERE CODE='"+m_asset_code+"' AND ACTIVE_STATUS='Y' ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Condition of Asset Details - Condition of Asset Code : "+m_asset_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Condition of Asset Details - Condition of Asset Code : "+m_asset_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Condition of Asset Code "+m_asset_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Condition of Asset Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Condition of Asset</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_ASSET_USAGE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_usage_type=req.getParameter("usage_type");			
				
				rs= stmt1.executeQuery(" SELECT "+
					" USAGE_TYPE, "+
					" NVL(DESCRIPTION,'-'), "+
					" NVL(DECODE(DEFAULT_VALUE,'Y','Yes','No'),'-') "+
					"FROM "+m_schema_name+".AF_CO_MAS_ASSET_USAGE_TYPE "+
					"WHERE ACTIVE_STATUS='Y' AND USAGE_TYPE='"+m_usage_type+"' ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Asset Usage Type Details - Asset Usage Type : "+m_usage_type+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Asset Usage Type Details - Asset Usage Type : "+m_usage_type+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Asset Usage Type "+m_usage_type+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Asset Usage Type </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Asset Usage</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_MILEAGE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_sub_model_code=req.getParameter("sub_model_code");			
				
				rs= stmt1.executeQuery(" SELECT DISTINCT "+
					" A.MODEL, "+//1
					" NVL(B.DESCRIPTION,'-'), "+//2
					" NVL(A.SUB_MODEL,'-'), "+//3
					" NVL(C.DESCRIPTION,'-'), "+//4
					" NVL(D.DESCRIPTION,'-'), "+//5 usage
					" NVL(A.USAGE_FROM,0), "+//6
					" NVL(A.USAGE_TO,0), "+//7
					" NVL(A.AMOUNT,0) "+//8
					"FROM "+m_schema_name+".AF_CO_MAS_MILEAGE A ,"+m_schema_name+".AF_CO_MAS_MODEL B,"+m_schema_name+".AF_CO_MAS_SUB_MODLE C,"+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET D "+
					"WHERE A.SUB_MODEL='"+m_sub_model_code+"' AND A.ACTIVE_STATUS='Y' "+
					"AND A.SUB_MODEL=C.SUB_CODE "+
					"AND A.MODEL=B.MODEL_CODE "+
					"AND A.CONDITION_OF_ASSET=D.CODE ");
				
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Mileage Details - Sub Model : "+m_sub_model_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Mileage Details - Sub Model : "+m_sub_model_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Sub Model Code "+m_sub_model_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>"+count+".<b> Model </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Sub Model</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Condition of Asset</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Usage From</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Usage To</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_VALUER_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_valuer_code=req.getParameter("valuer_code");					
				
				rs= stmt1.executeQuery(" SELECT "+
					" VALUER_CODE, "+//1
					" NVL(FIRST_NAME,'-'), "+//2
					" NVL(LAST_NAME,'-'), "+//3
					" NVL(ADDRESS,'-'), "+//4
					" NVL(ADDRESS2,'-'), "+ //5
					" NVL(INITCAP(CITY_CODE),'-'), "+//6
					" NVL(TEL_NO,'-'), "+//7
					" NVL(MOBILE_NO,'-'), "+//8
					" NVL(VALUER_AMOUNT,0), "+//9
					" DECODE(DEFAULT_VALUE,'Y','Yes','No') "+//10
					"FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
					"WHERE VALUER_CODE='"+m_valuer_code+"' AND ACTIVE_STATUS='Y' ");
				
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Valuer Details - Valuer Code : "+m_valuer_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Valuer Details - Valuer Code : "+m_valuer_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Valuer Code "+m_valuer_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Valuer Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> First Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Last Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+","+rs.getString(5)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>City</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Tel No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Mobile No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Valuer Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_GARAGE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_garage_code=req.getParameter("garage_code");			
				
				rs= stmt1.executeQuery(" SELECT "+
					" GARAGE_CODE, "+
					" NVL(NAME,'-') "+
					"FROM "+m_schema_name+".AF_CO_MAS_GARAGE "+
					"WHERE GARAGE_CODE='"+m_garage_code+"' AND ACTIVE_STATUS='Y' ");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Garage Details - Garage Code : "+m_garage_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Garage Details - Garage Code : "+m_garage_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Garage Code "+m_garage_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Garage Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Garage Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_APPLICABLE_DOC_DRILL")){
				
				int count = 0;
				String m_string="";				
				String m_doc_code=req.getParameter("doc_code");						
				
				rs= stmt1.executeQuery(" SELECT "+
					" A.CODE, "+//1
					" F.DESCRIPTION,"+//2	
					" NVL(A.ENTITY_TYPE,'-'), "+//3
					" NVL(B.DESCRIPTION,'-'), "+//4
					" NVL(A.ITEM_CAT_CODE,'-'), "+//5
					" NVL(E.DESCRIPTION,'-'), "+//6
					" NVL(A.FROM_SCREEN_NO,0), "+//7
					" NVL(A.TO_SCREEN_NO,0), "+//8
					" NVL(A.DIVISION_CODE,'-'), "+//9
					" NVL(INITCAP(D.DESCRIPTION),'-'), "+//10
					" NVL(A.PRODUCT_CODE,'-'), "+//11
					" NVL(C.DESCRIPTION,'-') "+//12
					"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE A,"+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY B, "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE C, "+
					""+m_schema_name+".CO_CO_MAS_DIVISION D, "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY E ,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED F"+
					"WHERE  A.ENTITY_TYPE=B.ENTITY_CODE(+) "+
					"AND A.PRODUCT_CODE=C.TRAN_CODE(+) "+
					"AND A.DIVISION_CODE=D.DIVISION_CODE(+) "+
					"AND A.ITEM_CAT_CODE=E.ITEM_CAT_CODE(+) "+
					"AND A.CODE=F.CODE(+) "+
					"AND A.CODE=UPPER('"+m_doc_code+"') ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Document Details - Document Code : "+m_doc_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Document Details - Document Code : "+m_doc_code+"  </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Document Code "+m_doc_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>"+count+".<b> Document Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					/*out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Document Description </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Item Category</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>From Screen</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>To Screen</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Product</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");*/
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_INCOME_EXPENSE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_ie_code=req.getParameter("ie_code");			
				
				
				rs= stmt1.executeQuery(" SELECT "+
					" I_E_CODE, "+
					" NVL(DESCRIPTION,'-'), "+
					" DECODE(TYPE,'IN','Income','Expense'), "+
					" DECODE(DEFAULT_VALUE,'Y','Yes','No') "+
					"FROM "+m_schema_name+".AF_CO_MAS_INCOME_EXPENCE_TYPE "+
					"WHERE I_E_CODE='"+m_ie_code+"' AND ACTIVE_STATUS='Y' ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Income Expense Details - Income Expense Code : "+m_ie_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Income Expense Details - Income Expense Code : "+m_ie_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Income Expense Code "+m_ie_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Income Expense Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Descripton</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_NATIONALITY_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_nation_code=req.getParameter("nation_code");			
				
				rs= stmt1.executeQuery(" SELECT "+
					" NATIONALITY_CODE, "+
					" DESCRIPTION "+
					"FROM "+m_schema_name+".AF_CO_MAS_NATIONALITY "+
					"WHERE NATIONALITY_CODE='"+m_nation_code+"' AND ACTIVE_STATUS='Y' ");
				
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Nationality Details - Nationality Code : "+m_nation_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Nationality Details - Nationality Code : "+m_nation_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Nationality Code "+m_nation_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Nationality Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Descripton</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_LEGAL_ENTITY_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_entity_code=req.getParameter("entity_code");			
				
				rs= stmt1.executeQuery(" SELECT "+
					" ENTITY_CODE, "+
					" NVL(DESCRIPTION,'-'), "+
					" DECODE(DEFAULT_VALUE,'Y','Yes','No') "+
					"FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
					"WHERE ENTITY_CODE='"+m_entity_code+"' AND ACTIVE_STATUS='Y' ");
				
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Legal Entity Details - Legal Entity Code : "+m_entity_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Legal Entity Details - Legal Entity Code : "+m_entity_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Legal Entity Code "+m_entity_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Entity Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Descripton</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_LICENSEE_SETTLE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_acc_no=req.getParameter("acc_no");			
				
				rs= stmt1.executeQuery(" SELECT "+
					" ACC_NO, "+//1
					" NVL(BRANCH_CODE,'-'), "+//2
					" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-'), "+//3
					" NVL(ACC_SYS_REFNO,'-'), "+//4
					" NVL(DECODE(SETTLEMENT_STATUS,'N','No','Yes'),'-'), "+//5
					" NVL(CURR_CODE,'-'), "+//6
					" NVL(ACC_DESC,'-'), "+//7
					" NVL(ACC_CODE,'-') "+//8
					"FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
					"WHERE ACC_NO='"+m_acc_no+"' AND ACTIVE_STATUS='Y' ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Licensee Settlement Details - Account No : "+m_acc_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Licensee Settlement Details - Account No : "+m_acc_no+"  </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Account No "+m_acc_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Account No </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Branch </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Accounting System Reference </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Currency </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Account Desc. </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Account Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Settlement Status </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_TEAM_MEMBERS_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_team_id=req.getParameter("team_id");			
				
				rs= stmt1.executeQuery(" SELECT "+
					" A.TEAM_ID, "+//1
					" NVL(B.TEAM_DESC,'-'), "+//2
					" NVL(B.TEAM_HEAD,'-'), "+//3
					" NVL(B.DIVISION_CODE,'-'), "+//4
					" NVL(B.SUB_DIVISION_CODE,'-'), "+//5
					" NVL(A.USER_ID,'-'), "+//6
					" NVL(C.NAME,'-'), "+//7
					" NVL(C.EMP_ID,'-'), "+//8
					" NVL(C.DIVISION_CODE,'-') "+//9
					"FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A,"+m_schema_name+".AF_CO_MAS_TEAMS B,"+m_schema_name+".CO_CO_MAS_USER C "+
					"WHERE A.TEAM_ID=B.TEAM_ID AND A.ACTIVE_STATUS='Y' "+
					"AND A.USER_ID=C.USER_ID "+
					"AND A.TEAM_ID='"+m_team_id+"' ");
				
				String m_team_id1="",m_temp_team_id="";	
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Team Member Details - Team ID : "+m_team_id+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Team Member Details - Team ID : "+m_team_id+"  </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Team ID "+m_team_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				if(more_dir){
					m_team_id1=rs.getString(1);							     
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Team ID </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Team Desc. </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Team Head</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sub Division Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br><br>");	
					while(more_dir){
						count++;
						m_temp_team_id=rs.getString(1);
						if(m_temp_team_id.equals(m_team_id)){
							if(count==1){
								//wild
								out.println("<table align='center' width='100%' class='table' >");
								out.println("<tr>");
								out.println("<td width='1%'></td>"); 
								out.println("<td width='20%' class=div_input><b>User ID</b></td>");
								out.println("<td width='25%' class=div_input><b>Name</b></td>");
								out.println("<td width='20%' class=div_input><b>Employee ID</b></td>"); 
								out.println("<td width='20%' class=div_input><b>Division Code</b></td>");
								out.println("<td width='*%'></td>");
								out.println("</tr>");
								out.println("</table>");
							}
							out.println("<table align='center' width='100%' class='table' >");
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='20%' class=div_input>"+rs.getString(6)+"</td>");
							out.println("<td width='25%' class=div_input>"+rs.getString(7)+"</td>");
							out.println("<td width='20%' class=div_input>"+rs.getString(8)+"</td>"); 
							out.println("<td width='20%' class=div_input>"+rs.getString(9)+"</td>");
							out.println("<td width='*%'></td>");
							out.println("</tr>");
							out.println("</table>");
							m_temp_team_id=rs.getString(1);	
						}
						
						more_dir = rs.next();
					}
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_CUSTOMER_CAT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_cat_type_code=req.getParameter("cat_type_code");			
				
				rs= stmt1.executeQuery(" SELECT "+
					" CAT_TYPE_CODE, "+
					" NVL(DESCRIPTION,'-'), "+
					" DECODE(DEFAULT_VALUE,'Y','Yes','No') "+
					"FROM "+m_schema_name+".AF_MK_MAS_CUSTOMER_CATOGORY "+
					"WHERE CAT_TYPE_CODE=UPPER('"+m_cat_type_code+"') AND  ACTIVE_STATUS='Y' ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Customer Category Details - Category Type Code : "+m_cat_type_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Customer Category Details - Category Type Code : "+m_cat_type_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Category Type Code "+m_cat_type_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Cat. Type Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Category Descripton</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_LEAD_SOURCE_CAT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_src_code=req.getParameter("src_code");			
				
				
				rs= stmt1.executeQuery(" SELECT "+
					" SOURCE_CODE, "+
					" NVL(NAME,'-'), "+
					" DECODE(DEFAULT_VALUE,'Y','Yes','No') "+
					"FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT "+
					"WHERE SOURCE_CODE=UPPER('"+m_src_code+"') AND ACTIVE_STATUS='Y' ");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Lead Source Category Details - Lead Source Cat Code : "+m_src_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Lead Source Category Details - Lead Source Cat Code : "+m_src_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Lead Source Cat Code "+m_src_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Lead Source Cat. Code </b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Lead Source Cat. Desc.</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			
			
			
			
			else if(m_chksql.equals("SHOW_YARD_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_yard_code=req.getParameter("yard_code");					
				
				rs= stmt1.executeQuery(" SELECT "+
					" YARD_CODE, "+//1
					" NVL(NAME,'-'), "+//2
					" NVL(ADDRESS1,'-'), "+//3
					" NVL(ADDRESS2,'-'), "+//4
					" NVL(CITY_CODE,'-'), "+//5
					" NVL(TEL_NO,'-'), "+//6
					" NVL(FAX_NO,'-'), "+//7
					" DECODE(DEFAULT_VALUE,'Y','Yes','No') "+//8
					"FROM "+m_schema_name+".AF_CO_MAS_YARD "+
					"WHERE YARD_CODE='"+m_yard_code+"' AND ACTIVE_STATUS='Y' ");
				
				
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Yard  Details - Yard Code : "+m_yard_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>  Yard  Details - Yard Code : "+m_yard_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Yard Code "+m_yard_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Yard Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+","+rs.getString(4)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> City</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Tel No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Fax No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Default Value</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			
			
			
			/* else if(m_chksql.equals("SHOW_MISSING_VEHICLE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_vehicle_no=req.getParameter("vehicle_no");					
	
						rs= stmt1.executeQuery(" SELECT "+
						   " VEHICLE_NO, "+
						   " TO_CHAR(MISSING_DATE,'DD-MM-YYYY'), "+
						   " NVL(ENGIN_NO,'-'), "+
						   " NVL(CHASSISS_NO,'-') "+
						 "FROM "+m_schema_name+".AF_CO_PRO_MISSING_VEHICLES "+
						 "WHERE VEHICLE_NO='"+m_vehicle_no+"' ");

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> Missing Vehicle  Details - Vehicle No : "+m_vehicle_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Missing Vehicle  Details - Vehicle No : "+m_vehicle_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Vehicle No "+m_vehicle_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vehicle No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Missing Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Engine No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Chassis No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			*/
			
			/*else if(m_chksql.equals("SHOW_FOLLOWUP_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_followup_no=req.getParameter("followup_no");					
									
						rs= stmt1.executeQuery(" SELECT "+
						   " A.FOLLOW_UP_NO, "+//1
						   " NVL(A.ID_NO,'-'), "+//2
						   " NVL(A.ACTION_TOBE_TAKEN,'-'), "+//3
						   " NVL((SELECT CATEGORY_NAME FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY WHERE CATEGORY_CODE=A.ACTION_TOBE_TAKEN),'-'),"+//4
						   " NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-'), "+//5
						   " NVL(A.ACTION_TAKEN,'-'), "+//6
						   " NVL((SELECT CATEGORY_NAME FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY WHERE CATEGORY_CODE=A.ACTION_TAKEN),'-'),"+//7
						   " NVL(TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY'),'-'), "+//8
						   " NVL(A.ACTION_SET_FOR,'-'), "+//9
						   " NVL(A.SCREEN_NAME,'-'), "+//10
						   " NVL(A.DIVISION_CODE,'-'), "+//11
							 " NVL(C.DESCRIPTION,'-'), "+//12	
						   " NVL(A.ENT_REMARKS,'-'), "+//13
						   " NVL(A.REMARKS,'-'), "+//14
						   " NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MM-YYYY'),'-'), "+//15
						   " DECODE(A.STATUS,'INPROGRESS','In Progress','COMPLETED','Completed','PENDING','Pending'), "+//16
						   " NVL(A.PRIORITY,0), "+//17
						   " NVL(A.PREV_FOLLOWUP_NO,'-'), "+//18
						   " NVL(A.ORG_FOLLOWUP_NO,'-'), "+//19
						   " NVL(A.FOLLOWUP_TIME,'-'), "+//20
						   " NVL(A.SUB_DIVISION_CODE,'-'), "+//21
							 " NVL(D.DESCRIPTION,'-'), "+	//22
						   " NVL(A.PRODUCT_CODE,'-'), "+//23
							 " NVL(E.DESCRIPTION,'-') "+//24	
						 "FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A,"+m_schema_name+".CO_CO_MAS_DIVISION C,"+m_schema_name+".CO_CO_MAS_SUB_DIVISION D, "+
						 ""+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE E "+	
						 "WHERE "+//AND B.CATEGORY_CODE=A.ACTION_TAKEN(+)
						 "A.DIVISION_CODE=C.DIVISION_CODE "+	
						 "AND A.SUB_DIVISION_CODE=D.SUB_DIVISION_CODE"+	
						 "AND A.PRODUCT_CODE=E.TRAN_CODE "+	
						 "AND A.FOLLOW_UP_NO='"+m_followup_no+"' ");


				boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> FollowUp  Details - FollowUp No : "+m_followup_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> FollowUp  Details - FollowUp No : "+m_followup_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for FollowUp No "+m_followup_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> FollowUp No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>ID No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='20%' class=div_input><b>Division Name</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sub Division Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(21)+"</td>");
					out.println("<td width='20%' class=div_input><b>Sub Division Name</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(22)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Product Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(23)+"</td>");
					out.println("<td width='20%' class=div_input><b>Product Name</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(24)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Action To Be Taken</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='20%' class=div_input><b>Action Taken</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Action Assign To</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='20%' class=div_input><b>Action Target Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Actual Action Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Entered User Remarks</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("<td width='20%' class=div_input><b>Remarks</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Screen Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Priority</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Previous FollowUp No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>FollowUp Time</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}*/
			
			
			
			/*else if(m_chksql.equals("SHOW_POD_CHEQUE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_pod_ref_no=req.getParameter("pod_ref_no");					
				
				
									rs= stmt1.executeQuery(" SELECT "+
									  "  POD_REF_NO,"+//1
									  "  NVL(DECODE(STATUS,'INV','Invoice','ENT','Enter','WIT','Withdraw','REC','Receipt'),'-'),"+//2
									  "  NVL(FINANCE_NO,'-'),"+//3
									  "  NVL(REC_NO,'-'),"+//4
									  "  NVL(SUS_REF_NO,'-'),"+//5
									  "  NVL(CHEQUE_NO,'-'),"+//6
									  "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//7
									  "  NVL(CHEQUE_AMOUNT,0),"+//8
									  "  NVL(SETTLE_MODE,'-'),"+//9
									  "  NVL(PAYER_BRANCH_CODE,'-'),"+//10
									  "  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'),"+//11
									  "  NVL(PAYER_ACC_NO,'-'),"+//12
									  "  NVL(CLIENT_CODE,'-'),"+//13
									  "  NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'),"+//14
									  "  NVL(ENTRY_TYPE,'-'),"+//15
									  "  NVL(OTH_COMMENTS,'-'),"+//16
									  "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-'),"+//17
									  "  NVL(CURR_CODE,'-'),"+//18
									  "  NVL(EXCHANGE_RATE_REP_CURR,0),"+//19
									  "  NVL(REC_AMOUNT_REP_CURR,0)"+//20
									 "FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
									 "WHERE POD_REF_NO='"+m_pod_ref_no+"'");

						boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> POD Cheque Details - POD Ref No : "+m_pod_ref_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> POD Cheque Details - POD Ref No : "+m_pod_ref_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for POD Ref No  "+m_pod_ref_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> POD Ref No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>POD Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>SUS Ref No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(5)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='20%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cheque Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='20%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payer Branch Name</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='20%' class=div_input><b>Payer Account No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(13)+"')><u>"+rs.getString(13)+"</u></td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(13)+"')><u>"+rs.getString(14)+"</u></td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					//wildd
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Entry Type</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Other Comments</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Realised Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Currency</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Exchange Rate Reporting Currency</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(19))+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Reporting Currency Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(20))+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}*/
			/*else if(m_chksql.equals("SHOW_SETTLE_RECEIPT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_receipt_no=req.getParameter("receipt_no");					
				
				
						rs= stmt1.executeQuery(" SELECT "+
						  "  REC_NO,"+//1
						  "  NVL(DECODE(STATUS,'E','Entered','B','Bank','C','Cancel','REC','Receipt','RET','Return'),'-'),"+//2
						  "  NVL(DECODE(RECON_STATUS,'Y','Yes','N','No'),'-'),"+//3
						  "  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'),"+//4
						  "  NVL(RECON_BY,'-'),"+//5
						  "  NVL(SUS_REF_NO,'-'),"+//6
						  "  NVL(SETTLE_MODE,'-'),"+//7
						  "  NVL(PAYER_BRANCH_CODE,'-'),"+//8
						  "  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'),"+//9
						  "  NVL(PAYER_ACC_NO,'-'),"+//10
						  "  NVL(ENTRY_TYPE,'-'),"+//11
						  "  NVL(REC_AMOUNT,0),"+//12
						  "  NVL(CLIENT_CODE,'-'),"+//13
						  "  NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'),"+//14
						  "  NVL(BRANCH_CODE,'-'),"+//15
						  "  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-'),"+//16
						  "  NVL(ACC_NO,'-'),"+//17
						  "  NVL(OTH_COMMENTS,'-'),"+//18
						  "  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//19
						  "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-'),"+//20
						  "  NVL(CURR_CODE,'-'),"+//21
						  "  NVL(REC_AMOUNT_CURR,0),"+//22
						  "  NVL(EXCHANGE_RATE_BANK,0),"+//23
						  "  NVL(EXCHANGE_RATE_REP_CURR,0),"+//24
						  "  NVL(EXCHANGE_GAIN_LOSS,0),"+//25
						  "  NVL(REC_AMOUNT_REP_CURR,0),"+//26
						  "  NVL(CHEQUE_NO,'-'),"+//27
						  "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//28
						  "  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'),"+//29
						  "  NVL(TENDER_AMOUNT,0),"+//30
						  "  NVL(RETURN_AMOUNT,0),"+//31
						  "  NVL(RENTAL_OTER_INVOICE,0),"+//32
						  "  NVL(INSURANCE,0),"+//33
						  "  NVL(LUXURY_TAX,0),"+//34
						  "  NVL(REVANUE_LICENCE,0),"+//35
						  "  NVL(RMV_CHARGES,0)"+//36
						 "FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						 "WHERE REC_NO='"+m_receipt_no+"'");

						boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> Settlement Receipt Details - Receipt No : "+m_receipt_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Settlement Receipt Details - Receipt No : "+m_receipt_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Receipt No  "+m_receipt_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Receipt No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Recon Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Recon Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Recon By</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>SUS Ref No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='20%' class=div_input><b>Payer Branch Name</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payer Acc. No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='20%' class=div_input><b>Entry Type</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(13)+"')><u>"+rs.getString(14)+"</u></td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Account No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					//wildd
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Other Comment</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='20%' class=div_input><b>EFF Value Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Realised Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(21)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Amount Current</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(22))+"</td>");
					out.println("<td width='20%' class=div_input><b>Ex. Rate Bank</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(23))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Ex. Rate Reporting Curr.</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(24))+"</td>");
					out.println("<td width='20%' class=div_input><b>Ex. Gain Loss</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(25))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Amount Reporting Curr.</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(26))+"</td>");
					out.println("<td width='20%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("<td width='20%' class=div_input><b>Bank Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Tender Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(30))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Return Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(31))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Rental Other Invoice</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(32))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Insurance</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(33))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Luxury Tax</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(34))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Revanue Licence</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(35))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>RMV Charge</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(36))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}*/
			else {
				out.println("Undefined");
			}
			
			//out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try {out.println(e.toString());}catch (Exception eti) {}
			try {
				conn.close();
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			//ServletOutputStream out = res.getOutputStream();
			out = res.getOutputStream();
			out.println(ostr.toString());
			
			out.close();
			
		}
	}
}


