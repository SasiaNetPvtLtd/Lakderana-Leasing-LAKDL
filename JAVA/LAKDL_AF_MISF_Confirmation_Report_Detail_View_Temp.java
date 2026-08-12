//Created by Minal on 31-12-2014 for #14902
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MISF_Confirmation_Report_Detail_View_Temp extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt_2,stmt,stmt_invoice,stmt_rental,stmt_pricing,stmt_charges;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	
	
	// public ResultSet rs1,rs_doc_charge;
	public ResultSet rs,rs2,rs3,rs_rental,rs_pricing,rs_charges;
	
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
			
			//************************************************************	
			//LAKDL_AF_RE_PRO_drill_downs obj =new LAKDL_AF_RE_PRO_drill_downs();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			
			//**************************************************************					
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
			
			ServletOutputStream out = res.getOutputStream();
			m_chksql=req.getParameter("chksql");
			
			stmt_invoice=conn.createStatement();
			stmt_pricing=conn.createStatement();
			stmt=conn.createStatement();
			stmt_2=conn.createStatement();
			stmt_rental=conn.createStatement();
			stmt_charges = conn.createStatement ();
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("main_page")){
				//out.println("sfsdsd");
				
				String m_finance_no=req.getParameter("finance_no").trim();
				
				String m_insurance_company = "";
				
				rs = stmt.executeQuery(" "+
							" select INSUR_COM "+
							" from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA "+
							" where FINANCE_NO = '"+m_finance_no+"' "+
							" ORDER BY ENT_DATE DESC "+	 
					" ");
				
				if(rs.next()){
					m_insurance_company = rs.getString(1);
				}
				
				rs = stmt.executeQuery(" "+
					" SELECT "+
						" FINANCE_NO, EXISTING_CUSTOMER, EXIST_FINANCE_NO, LOCATION_CODE, REPORT_DATE, "+ // 5
						" NAME_1_HIRER_CODE, NAME_1_HIRER, CONTRACT_NO_HIRE, NAME_2_HIRER_CODE, NAME_2_HIRER, "+ // 10
						" VEHICAL_NO_HIRER, NIC_NO_1_HIRER, CONTACT_NUMBER_HIRER, NIC_NO_2_HIRER, ADDRESS_HIRER, "+ // 15
						" PROVINCE_HIRER, VALUATION_INS_DETAILS, MARKET, FORCED_SALE_INS_DETAILS, VEHICAL_NO_2_INS_DETAILS, "+ // 20
						" GRADE_INS_DETAILS, CHASSIS_NO_INS_DETAILS, COLOUR_INS_DETAILS, ENGINE_NO_INS_DETAILS, HIRER_AGREE, "+ // 25
						" NIC_COPY_1_AGREE, NAME_VENDOR_CODE, NAME_VENDOR, NIC_COPY_VENDOR, NIC_NO_1_VENDOR, "+ // 30
						" SIGNATOR_VENDOR, INTRODUCER_VENDOR, CR_BOOK_DOCS, COPY_1_DOCS, MTA_6_DOCS, "+ // 35
						" PHOTOS_DOCS, DELETION_DOCS, COPY_2_DOCS, MTA_8_DOCS, VIC_DOCS, "+ // 40
						" LICENSE_DOCS, COPY_3_DOCS, MTA_3_DOCS, DUP_KEY_DOCS, REFINANCE_CASE_INSURENCE, "+ // 45
						" INITAL_CHARGES_INSURENCE, PAID_1_INSURENCE, BAL_1_INSURENCE, STAMP_DUTY_INSURENCE, PAID_2_INSURENCE, "+ // 50
						" BAL_2_INSURENCE, INSURENCE_CHARGES_INSU, PAID_3_INSURENCE, BAL_3_INSURENCE, BAL_4_INSURENCE, "+ // 55
						" SUM_INSURED_INSU, INSURENCE_COM_SELECTION, INSURANCE_UPDATE_INSU, COVER_NOTE_INSU, RENTAL_INSURENCE, "+ // 60
						" RENTAL_LETTER_ISSUED_INSU, CAPITAL_INSURANCE, SLAB_INSURANCE, INSPECTION_APPROVALS, CAPITAL_APPROVALS, "+ // 65
						" CR_BOOK_NO_APPROVALS, INT_CHARGES_APPROVALS, CLOSING_STAT_APPROVALS, LESS_FIRST_RENT_APPROVAL, F_RENTAL_APPROVALS, "+ // 70
						" CLS_STAT_APPROVAL, OTHER_APPROVAL, OTHER_CHARGES_APPROVAL, STAMP_DUTY_APPROVAL, CR_APPROVALS, "+ // 75
						" ENTRY_APPROVAL, INSU_CHARGES_APPROVALS, TO_BE_PAID_APPROVALS, RMV_PAPER_APPROVAL, CHECKED_BY_APPROVALS, "+ // 80
						" TEL_APPROVAL, CASE_CANVASSED_BY, NAME_CASE, APPROVED_CAPITALS_CREDIT_CO, DIRECTOR_NAME_CREDIT_CO, "+ // 85
						" SIGANTURE_CREDIT_CO, ENT_USER, ENT_DATE, REPORT_STATUS, LEAD_SOURCE "+ // 90
							" FROM "+m_schema_name+".AF_CONFIRMATION_REPORT "+
							" WHERE FINANCE_NO = '"+m_finance_no+"' "+
					" ");
				
				
				String	 m_FINANCE_NO	=	"";
				String	 m_EXISTING_CUSTOMER	=	"";
				String	 m_EXIST_FINANCE_NO	=	"";
				String	 m_LOCATION_CODE	=	"";
				String	 m_REPORT_DATE	=	"";
				String	 m_NAME_1_HIRER_CODE	=	"";
				String	 m_NAME_1_HIRER	=	"";
				String	 m_CONTRACT_NO_HIRE	=	"";
				String	 m_NAME_2_HIRER_CODE	=	"";
				String	 m_NAME_2_HIRER	=	"";
				String	 m_VEHICAL_NO_HIRER	=	"";
				String	 m_NIC_NO_1_HIRER	=	"";
				String	 m_CONTACT_NUMBER_HIRER	=	"";
				String	 m_NIC_NO_2_HIRER	=	"";
				String	 m_ADDRESS_HIRER	=	"";
				String	 m_PROVINCE_HIRER	=	"";
				String	 m_VALUATION_INS_DETAILS	=	"";
				double	 m_MARKET	=	0;
				double	 m_FORCED_SALE_INS_DETAILS	=	0;
				String	 m_VEHICAL_NO_2_INS_DETAILS	=	"";
				String	 m_GRADE_INS_DETAILS	=	"";
				String	 m_CHASSIS_NO_INS_DETAILS	=	"";
				String	 m_COLOUR_INS_DETAILS	=	"";
				String	 m_ENGINE_NO_INS_DETAILS	=	"";
				String	 m_HIRER_AGREE	=	"";
				String	 m_NIC_COPY_1_AGREE	=	"";
				String	 m_NAME_VENDOR_CODE	=	"";
				String	 m_NAME_VENDOR	=	"";
				String	 m_NIC_COPY_VENDOR	=	"";
				String	 m_NIC_NO_1_VENDOR	=	"";
				String	 m_SIGNATOR_VENDOR	=	"";
				String	 m_INTRODUCER_VENDOR	=	"";
				String	 m_CR_BOOK_DOCS	=	"";
				String	 m_COPY_1_DOCS	=	"";
				String	 m_MTA_6_DOCS	=	"";
				String	 m_PHOTOS_DOCS	=	"";
				String	 m_DELETION_DOCS	=	"";
				String	 m_COPY_2_DOCS	=	"";
				String	 m_MTA_8_DOCS	=	"";
				String	 m_VIC_DOCS	=	"";
				String	 m_LICENSE_DOCS	=	"";
				String	 m_COPY_3_DOCS	=	"";
				String	 m_MTA_3_DOCS	=	"";
				String	 m_DUP_KEY_DOCS	=	"";
				String	 m_REFINANCE_CASE_INSURENCE	=	"";
				double	 m_INITAL_CHARGES_INSURENCE	=	0;
				double	 m_PAID_1_INSURENCE	=	0;
				double	 m_BAL_1_INSURENCE	=	0;
				double	 m_STAMP_DUTY_INSURENCE	=	0;
				double	 m_PAID_2_INSURENCE	=	0;
				double	 m_BAL_2_INSURENCE	=	0;
				double	 m_INSURENCE_CHARGES_INSU	=	0;
				double	 m_PAID_3_INSURENCE	=	0;
				double	 m_BAL_3_INSURENCE	=	0;
				double	 m_BAL_4_INSURENCE	=	0;
				double	 m_SUM_INSURED_INSU	=	0;
				String	 m_INSURENCE_COM_SELECTION	=	"";
				String	 m_INSURANCE_UPDATE_INSU	=	"";
				String	 m_COVER_NOTE_INSU	=	"";
				double	 m_RENTAL_INSURENCE	=	0;
				String	 m_RENTAL_LETTER_ISSUED_INSU	=	"";
				double	 m_CAPITAL_INSURANCE	=	0;
				double	 m_SLAB_INSURANCE	=	0;
				String	 m_INSPECTION_APPROVALS	=	"";
				double	 m_CAPITAL_APPROVALS	=	0;
				String	 m_CR_BOOK_NO_APPROVALS	=	"";
				double	 m_INT_CHARGES_APPROVALS	=	0;
				double	 m_CLOSING_STAT_APPROVALS	=	0;
				double	 m_LESS_FIRST_RENT_APPROVAL	=	0;
				double	 m_F_RENTAL_APPROVALS	=	0;
				double	 m_CLS_STAT_APPROVAL	=	0;
				double	 m_OTHER_APPROVAL	=	0;
				double	 m_OTHER_CHARGES_APPROVAL	=	0;
				double	 m_STAMP_DUTY_APPROVAL	=	0;
				String	 m_CR_APPROVALS	=	"";
				//double	 m_ENTRY_APPROVAL	=	0;
				int	 m_ENTRY_APPROVAL	=	0; // udara 24-04-2015
				double	 m_INSU_CHARGES_APPROVALS	=	0;
				double	 m_TO_BE_PAID_APPROVALS	=	0;
				String	 m_RMV_PAPER_APPROVAL	=	"";
				String	 m_CHECKED_BY_APPROVALS	=	"";
				String	 m_TEL_APPROVAL	=	"";
				String	 m_CASE_CANVASSED_BY	=	"";
				String	 m_NAME_CASE	=	"";
				double	 m_APPROVED_CAPITALS_CREDIT_CO	=	0;
				String	 m_DIRECTOR_NAME_CREDIT_CO	=	"";
				String	 m_SIGANTURE_CREDIT_CO	=	"";
				String	 m_ENT_USER	=	"";
				String	 m_ENT_DATE	=	"";
				String	 m_REPORT_STATUS	=	"";
				String   m_LEAD_SOURCE = "";
		
				if(rs.next()){
					
					 m_FINANCE_NO	        		=	rs.getString("FINANCE_NO");
					 m_EXISTING_CUSTOMER			=	rs.getString("EXISTING_CUSTOMER");
					 m_EXIST_FINANCE_NO	    		=	rs.getString("EXIST_FINANCE_NO");
					 m_LOCATION_CODE	    		=	rs.getString("LOCATION_CODE");
					 m_REPORT_DATE	        		=	rs.getString("REPORT_DATE"); // 5
					 m_NAME_1_HIRER_CODE			=	rs.getString("NAME_1_HIRER_CODE");
					 m_NAME_1_HIRER	        		=	rs.getString("NAME_1_HIRER");
					 m_CONTRACT_NO_HIRE	    		=	rs.getString("CONTRACT_NO_HIRE");
					 m_NAME_2_HIRER_CODE			=	rs.getString("NAME_2_HIRER_CODE");
					 m_NAME_2_HIRER	        		=	rs.getString("NAME_2_HIRER"); // 10
					 m_VEHICAL_NO_HIRER	    		=	rs.getString("VEHICAL_NO_HIRER");
					 m_NIC_NO_1_HIRER	    		=	rs.getString("NIC_NO_1_HIRER");
					 m_CONTACT_NUMBER_HIRER			=	rs.getString("CONTACT_NUMBER_HIRER");
					 m_NIC_NO_2_HIRER	    		=	rs.getString("NIC_NO_2_HIRER");
					 m_ADDRESS_HIRER	    		=	rs.getString("ADDRESS_HIRER"); // 15
					 m_PROVINCE_HIRER	    		=	rs.getString("PROVINCE_HIRER");
					 m_VALUATION_INS_DETAILS		=	rs.getString("VALUATION_INS_DETAILS");
					 m_MARKET	                	=	rs.getDouble("MARKET");
					 m_FORCED_SALE_INS_DETAILS		=	rs.getDouble("FORCED_SALE_INS_DETAILS");
					 m_VEHICAL_NO_2_INS_DETAILS		=	rs.getString("VEHICAL_NO_2_INS_DETAILS"); // 20
					 m_GRADE_INS_DETAILS	    	=	rs.getString("GRADE_INS_DETAILS");
					 m_CHASSIS_NO_INS_DETAILS		=	rs.getString("CHASSIS_NO_INS_DETAILS");
					 m_COLOUR_INS_DETAILS	    	=	rs.getString("COLOUR_INS_DETAILS");
					 m_ENGINE_NO_INS_DETAILS		=	rs.getString("ENGINE_NO_INS_DETAILS");
					 m_HIRER_AGREE	            	=	rs.getString("HIRER_AGREE"); // 25
					 m_NIC_COPY_1_AGREE	        	=	rs.getString("NIC_COPY_1_AGREE");
					 m_NAME_VENDOR_CODE	        	=	rs.getString("NAME_VENDOR_CODE");
					 m_NAME_VENDOR	            	=	rs.getString("NAME_VENDOR");
					 m_NIC_COPY_VENDOR	        	=	rs.getString("NIC_COPY_VENDOR");
					 m_NIC_NO_1_VENDOR	        	=	rs.getString("NIC_NO_1_VENDOR"); // 30
					 m_SIGNATOR_VENDOR	        	=	rs.getString("SIGNATOR_VENDOR");
					 m_INTRODUCER_VENDOR	    	=	rs.getString("INTRODUCER_VENDOR");
					 m_CR_BOOK_DOCS	            	=	rs.getString("CR_BOOK_DOCS");
					 m_COPY_1_DOCS	            	=	rs.getString("COPY_1_DOCS");
					 m_MTA_6_DOCS	            	=	rs.getString("MTA_6_DOCS"); // 35
					 m_PHOTOS_DOCS	            	=	rs.getString("PHOTOS_DOCS");
					 m_DELETION_DOCS	        	=	rs.getString("DELETION_DOCS");
					 m_COPY_2_DOCS	            	=	rs.getString("COPY_2_DOCS");
					 m_MTA_8_DOCS	            	=	rs.getString("MTA_8_DOCS");
					 m_VIC_DOCS	    				=	rs.getString("VIC_DOCS"); // 40
					 m_LICENSE_DOCS					=	rs.getString("LICENSE_DOCS");
					 m_COPY_3_DOCS					=	rs.getString("COPY_3_DOCS");
					 m_MTA_3_DOCS					=	rs.getString("MTA_3_DOCS");
					 m_DUP_KEY_DOCS					=	rs.getString("DUP_KEY_DOCS");
					 m_REFINANCE_CASE_INSURENCE		=	rs.getString("REFINANCE_CASE_INSURENCE"); // 45
					 m_INITAL_CHARGES_INSURENCE		=	rs.getDouble("INITAL_CHARGES_INSURENCE");
					 m_PAID_1_INSURENCE				=	rs.getDouble("PAID_1_INSURENCE");
					 m_BAL_1_INSURENCE				=	rs.getDouble("BAL_1_INSURENCE");
					 m_STAMP_DUTY_INSURENCE			=	rs.getDouble("STAMP_DUTY_INSURENCE");
					 m_PAID_2_INSURENCE				=	rs.getDouble("PAID_2_INSURENCE"); // 50
					 m_BAL_2_INSURENCE				=	rs.getDouble("BAL_2_INSURENCE");
					 m_INSURENCE_CHARGES_INSU		=	rs.getDouble("INSURENCE_CHARGES_INSU");
					 m_PAID_3_INSURENCE				=	rs.getDouble("PAID_3_INSURENCE");
					 m_BAL_3_INSURENCE				=	rs.getDouble("BAL_3_INSURENCE");
					 m_BAL_4_INSURENCE				=	rs.getDouble("BAL_4_INSURENCE"); // 55
					 m_SUM_INSURED_INSU				=	rs.getDouble("SUM_INSURED_INSU");
					 m_INSURENCE_COM_SELECTION		=	rs.getString("INSURENCE_COM_SELECTION");
					 m_INSURANCE_UPDATE_INSU		=	rs.getString("INSURANCE_UPDATE_INSU");
					 m_COVER_NOTE_INSU				=	rs.getString("COVER_NOTE_INSU");
					 m_RENTAL_INSURENCE				=	rs.getDouble("RENTAL_INSURENCE"); // 60
					 m_RENTAL_LETTER_ISSUED_INSU	=	rs.getString("RENTAL_LETTER_ISSUED_INSU");
					 m_CAPITAL_INSURANCE			=	rs.getDouble("CAPITAL_INSURANCE");
					 m_SLAB_INSURANCE				=	rs.getDouble("SLAB_INSURANCE");
					 m_INSPECTION_APPROVALS			=	rs.getString("INSPECTION_APPROVALS");
					 m_CAPITAL_APPROVALS			=	rs.getDouble("CAPITAL_APPROVALS"); // 65
					 m_CR_BOOK_NO_APPROVALS			=	rs.getString("CR_BOOK_NO_APPROVALS");
					 m_INT_CHARGES_APPROVALS		=	rs.getDouble("INT_CHARGES_APPROVALS");
					 m_CLOSING_STAT_APPROVALS		=	rs.getDouble("CLOSING_STAT_APPROVALS");
					 m_LESS_FIRST_RENT_APPROVAL		=	rs.getDouble("LESS_FIRST_RENT_APPROVAL");
					 m_F_RENTAL_APPROVALS			=	rs.getDouble("F_RENTAL_APPROVALS"); // 70
					 m_CLS_STAT_APPROVAL			=	rs.getDouble("CLS_STAT_APPROVAL");
					 m_OTHER_APPROVAL				=	rs.getDouble("OTHER_APPROVAL");
					 m_OTHER_CHARGES_APPROVAL		=	rs.getDouble("OTHER_CHARGES_APPROVAL");
					 m_STAMP_DUTY_APPROVAL			=	rs.getDouble("STAMP_DUTY_APPROVAL");
					 m_CR_APPROVALS					=	rs.getString("CR_APPROVALS"); // 75
					 //m_ENTRY_APPROVAL				=	rs.getDouble("ENTRY_APPROVAL");
					 m_ENTRY_APPROVAL				=	rs.getInt("ENTRY_APPROVAL"); // udara 24-04-2015
					 m_INSU_CHARGES_APPROVALS		=	rs.getDouble("INSU_CHARGES_APPROVALS");
					 m_TO_BE_PAID_APPROVALS			=	rs.getDouble("TO_BE_PAID_APPROVALS");
					 m_RMV_PAPER_APPROVAL			=	rs.getString("RMV_PAPER_APPROVAL");
					 m_CHECKED_BY_APPROVALS			=	rs.getString("CHECKED_BY_APPROVALS"); // 80
					 m_TEL_APPROVAL					=	rs.getString("TEL_APPROVAL");
					 m_CASE_CANVASSED_BY			=	rs.getString("CASE_CANVASSED_BY");
					 m_NAME_CASE					=	rs.getString("NAME_CASE");
					 m_APPROVED_CAPITALS_CREDIT_CO	=	rs.getDouble("APPROVED_CAPITALS_CREDIT_CO");
					 m_DIRECTOR_NAME_CREDIT_CO		=	rs.getString("DIRECTOR_NAME_CREDIT_CO"); // 85
					 m_SIGANTURE_CREDIT_CO			=	rs.getString("SIGANTURE_CREDIT_CO");
					 m_ENT_USER						=	rs.getString("ENT_USER");
					 m_ENT_DATE						=	rs.getString("ENT_DATE");
					 m_REPORT_STATUS				=	rs.getString("REPORT_STATUS"); // 89
					 m_LEAD_SOURCE					=	rs.getString("LEAD_SOURCE"); // 90
				}
				
				
				
				out.println("<HTML><HEAD><TITLE>Confirmation Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<script>");
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("function validate_before_save(){ ");
				//out.println("  save_report_details(document.Form1.hid_finance_no.value);  "); 
				
				out.println("       var count = 0; ");
				
				out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("		    if(document.Form1.elements[i].value==''){ ");
				//out.println("			   alert('Please enter the value'); ");
				//out.println("			   alert('Please enter the value ' + document.Form1.elements[i].name); ");
				
				out.println("              if('"+m_username+"'=='LAKDLALL'){    ");
				out.println("			       document.Form1.elements[i].style.borderColor=\"red\" ; ");
				out.println("			       document.Form1.elements[i].style.backgroundColor=\"yellow\" ; ");
				out.println("			       alert('Please enter the value ' + document.Form1.elements[i].name); ");
				out.println("			   }");
				out.println("			   else{");
				out.println("			       document.Form1.elements[i].style.borderColor=\"red\" ; ");
				out.println("			       document.Form1.elements[i].style.backgroundColor=\"yellow\" ; ");
				out.println("			       alert('Please enter the value'); ");
				out.println("			   }");
				
				out.println("			   document.Form1.elements[i].focus(); ");
				out.println("              count = count + 1; ");
				out.println("			   break; ");
				out.println("			}");
				out.println("			else{");
				out.println("       		   count = 0; ");
				
				out.println("               if(document.Form1.elements[i].name!='Save'){ ");
				out.println("			       document.Form1.elements[i].style.borderColor=\"black\" ; ");
				out.println("			       document.Form1.elements[i].style.backgroundColor=\"white\" ; ");
				out.println("			    }");
				
				out.println("			}");
				out.println("		}");
				
				out.println("		if(count==0){");
				out.println("  			save_report_details(document.Form1.hid_finance_no.value);  "); 
				out.println("		}");
				
				
				out.println("} ");  
			
				out.println("function save_report_details(m_finance_no){");
				out.println("	if(confirm(\"Are you sure you want to save?\")){ "); 
				
				out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("		   document.Form1.elements[i].disabled=false;");
				out.println("		}");
				
				
				//out.println("     alert('test :::: ' + m_finance_no);  "); 
			    //out.println("	 document.Form1.action=\""+m_class_url+"/"+m_schema_name+"AF_MISF_Confirmation_Report_Detail_Save?FIN_NO=\"+m_finance_no+\"&doc_1="+m_doc_1+"&doc_2="+m_doc_2+"&doc_3="+m_doc_3+"\";");
			    //out.println("	 document.Form1.action=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_Detail_Save\";");
				out.println("	 document.Form1.action=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_Detail_Save?view_status=new\";"); 
				out.println("	 document.Form1.submit();	"); 
			    out.println("	}"); 
				out.println("}");
				
				
				/*
				out.println("function validate_number_text_boxes(obj){ ");
				out.println("   var val = unformat_noobject(obj.value); ");
				out.println("   if(isNaN(val)){ ");
				out.println("    	alert('Please enter a number'); ");
				out.println("   	obj.value = ''; ");
				out.println("   	obj.focus(); ");
				out.println("   } ");
				out.println("   else{ ");
				out.println("   	obj.value = format_noobject(val); ");
				out.println("   } ");
				
				out.println("   set_cash_to_be_paid(); "); // added by udara 02-04-2015
				
				out.println("}");
				*/
				
				
				out.println("function validate_number_text_boxes(obj){ ");
				out.println("   var val = unformat_noobject(obj.value); ");
				
				out.println("   if(obj.value==''){ ");
				out.println("      obj.value='';  ");
				out.println("   } ");
				out.println("   else if(isNaN(val)){ ");
				out.println("    	alert('Please enter a number'); ");
				out.println("   	obj.value = ''; ");
				out.println("   	obj.focus(); ");
				out.println("   } ");
				out.println("   else{ ");
				out.println("   	obj.value = format_noobject(val); ");
				out.println("   } ");
				
				out.println("   set_cash_to_be_paid(); "); // added by udara 02-04-2015
				
				out.println("}");
				
				
				// ==============================================================================================================
				
				out.println("function set_initial_charges_insurance(){ ");
				out.println("    document.Form1.TXT_BAL_1_INSURENCE.value  =  unformat_noobject(document.Form1.TXT_INITAL_CHARGES_INSURENCE.value) - unformat_noobject(document.Form1.TXT_PAID_1_INSURENCE.value); ");
				out.println("    document.Form1.TXT_BAL_1_INSURENCE.value  =  format_noobject(document.Form1.TXT_BAL_1_INSURENCE.value); ");
				out.println("    document.Form1.TXT_INT_CHARGES_APPROVALS.value = document.Form1.TXT_BAL_1_INSURENCE.value; ");
				out.println("}");
				
				out.println("function set_stamp_duty_charges(){ ");
				out.println("    document.Form1.TXT_BAL_2_INSURENCE.value  =  unformat_noobject(document.Form1.TXT_STAMP_DUTY_INSURENCE.value) - unformat_noobject(document.Form1.TXT_PAID_2_INSURENCE.value); ");
				out.println("    document.Form1.TXT_BAL_2_INSURENCE.value  =  format_noobject(document.Form1.TXT_BAL_2_INSURENCE.value); ");
				out.println("    document.Form1.TXT_STAMP_DUTY_APPROVAL.value = document.Form1.TXT_BAL_2_INSURENCE.value; ");
				out.println("}");
				
				
				out.println("function set_insurance_charges(){ ");
				//out.println("    document.Form1.TXT_BAL_3_INSURENCE.value  =  unformat_noobject(document.Form1.TXT_INSURENCE_CHARGES_INSU.value) - unformat_noobject(document.Form1.TXT_PAID_3_INSURENCE.value); ");
				out.println("    document.Form1.TXT_BAL_3_INSURENCE.value  =  format_noobject(document.Form1.TXT_BAL_3_INSURENCE.value); ");
				out.println("    document.Form1.TXT_INSU_CHARGES_APPROVALS.value = document.Form1.TXT_BAL_3_INSURENCE.value; ");
				out.println("}");
				
				
				/*
				out.println("function set_insurance_charges(){ ");
				//out.println("    document.Form1.TXT_BAL_3_INSURENCE.value  =  unformat_noobject(document.Form1.TXT_INSURENCE_CHARGES_INSU.value) - unformat_noobject(document.Form1.TXT_PAID_3_INSURENCE.value); ");
				
				out.println("  if(document.Form1.TXT_BAL_3_INSURENCE.value!=''){ ");
				out.println("    document.Form1.TXT_BAL_3_INSURENCE.value  =  format_noobject(document.Form1.TXT_BAL_3_INSURENCE.value); ");
				out.println("    document.Form1.TXT_INSU_CHARGES_APPROVALS.value = document.Form1.TXT_BAL_3_INSURENCE.value; ");
				out.println("  } ");
				
				out.println("}");
				*/
				
				
				out.println("function set_insurance_charges_balance(){ ");
				out.println("    document.Form1.TXT_BAL_4_INSURENCE.value  =  unformat_noobject(document.Form1.TXT_INSURENCE_CHARGES_INSU.value) - unformat_noobject(document.Form1.TXT_PAID_3_INSURENCE.value) - unformat_noobject(document.Form1.TXT_BAL_3_INSURENCE.value); ");
				out.println("    document.Form1.TXT_BAL_4_INSURENCE.value  =  format_noobject(document.Form1.TXT_BAL_4_INSURENCE.value); ");
				out.println("}");
				
				
				out.println("function set_capital_values(){ ");
				out.println("   document.Form1.TXT_CAPITAL_APPROVALS.value = document.Form1.TXT_CAPITAL_INSURANCE.value;  ");
				out.println("   document.Form1.TXT_APPROVED_CAPITALS_CREDIT_CO.value = document.Form1.TXT_CAPITAL_INSURANCE.value;  ");
				out.println("}");
				
				out.println("function set_approval_values(){ ");
				out.println("   document.Form1.TXT_LESS_FIRST_RENT_APPROVAL.value = unformat_noobject(document.Form1.TXT_F_RENTAL_APPROVALS.value);  ");
				out.println("   document.Form1.TXT_LESS_FIRST_RENT_APPROVAL.value = format_noobject(document.Form1.TXT_LESS_FIRST_RENT_APPROVAL.value); ");
				
				out.println("   document.Form1.TXT_CLS_STAT_APPROVAL.value = unformat_noobject(document.Form1.TXT_CLOSING_STAT_APPROVALS.value);  ");
				out.println("   document.Form1.TXT_CLS_STAT_APPROVAL.value = format_noobject(document.Form1.TXT_CLS_STAT_APPROVAL.value); ");
				
				out.println("   document.Form1.TXT_OTHER_CHARGES_APPROVAL.value = unformat_noobject(document.Form1.TXT_OTHER_APPROVAL.value);  ");
				out.println("   document.Form1.TXT_OTHER_CHARGES_APPROVAL.value = format_noobject(document.Form1.TXT_OTHER_CHARGES_APPROVAL.value); ");
				
				out.println("}");
				
				out.println("function set_cash_to_be_paid(){ ");
				//out.println("   document.Form1.TXT_TO_BE_PAID_APPROVALS.value =   unformat_noobject(document.Form1.TXT_INT_CHARGES_APPROVALS.value) - (unformat_noobject(document.Form1.TXT_LESS_FIRST_RENT_APPROVAL.value) + unformat_noobject(document.Form1.TXT_CLS_STAT_APPROVAL.value) + unformat_noobject(document.Form1.TXT_OTHER_CHARGES_APPROVAL.value) + unformat_noobject(document.Form1.TXT_STAMP_DUTY_APPROVAL.value) + unformat_noobject(document.Form1.TXT_INSU_CHARGES_APPROVALS.value) + unformat_noobject(document.Form1.TXT_TO_BE_PAID_APPROVALS.value) )  ");
				out.println("   document.Form1.TXT_TO_BE_PAID_APPROVALS.value =   parseFloat(unformat_noobject(document.Form1.TXT_CAPITAL_APPROVALS.value)) - (parseFloat(unformat_noobject(document.Form1.TXT_INT_CHARGES_APPROVALS.value)) + parseFloat(unformat_noobject(document.Form1.TXT_LESS_FIRST_RENT_APPROVAL.value)) + parseFloat(unformat_noobject(document.Form1.TXT_CLS_STAT_APPROVAL.value)) + parseFloat(unformat_noobject(document.Form1.TXT_OTHER_CHARGES_APPROVAL.value)) + parseFloat(unformat_noobject(document.Form1.TXT_STAMP_DUTY_APPROVAL.value)) + parseFloat(unformat_noobject(document.Form1.TXT_INSU_CHARGES_APPROVALS.value)));   ");
				out.println("   document.Form1.TXT_TO_BE_PAID_APPROVALS.value =   format_noobject(document.Form1.TXT_TO_BE_PAID_APPROVALS.value); ");
				out.println("}");
				
				
				
				
				out.println("function on_load_set(){ ");
				out.println("    set_initial_charges_insurance(); ");
				out.println("    set_stamp_duty_charges(); ");
				out.println("    set_insurance_charges(); ");
				out.println("    set_insurance_charges_balance(); ");
				out.println("    set_capital_values(); ");
				out.println("    set_approval_values(); ");
				//out.println("    set_cash_to_be_paid(); ");
				out.println("    freez_objects(); ");
				
				out.println("    check_nic_div(document.Form1.TXT_HIRER_CLI_TYPE.value, document.Form1.TXT_HIRER_NIC.value, document.Form1.TXT_HIRER_GENDER.value, document.Form1.TXT_HIRER_DOB.value, DIV_TXT_HIRER_NIC); "); // added by udara 24-04-2015 // ok
				//out.println("    check_nic_div(document.Form1.TXT_HIRER_CLI_TYPE_2.value, document.Form1.TXT_HIRER_NIC_2.value, document.Form1.TXT_HIRER_GENDER_2.value, document.Form1.TXT_HIRER_DOB_2.value, DIV_TXT_HIRER_NIC_2); "); // added by udara 24-04-2015
				
				//out.println("    alert('on_load_set end'); ");
				
				// added by udara 28-04-2015
				/*
				out.println("   document.Form1.TXT_PAID_1_INSURENCE.value = '';  ");
				out.println("   document.Form1.TXT_PAID_2_INSURENCE.value = '';  ");
				out.println("   document.Form1.TXT_PAID_3_INSURENCE.value = '';  ");
				out.println("   document.Form1.TXT_BAL_3_INSURENCE.value = '';  ");
				*/
				// end by udara 28-04-2015
				
				out.println("}");
				
				out.println("function freez_objects(){ ");
				
				out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("		   document.Form1.elements[i].disabled=true;");
				out.println("		}");
				
				out.println("    document.Form1.TXT_BAL_3_INSURENCE.disabled = false; ");
				out.println("    document.Form1.TXT_INSPECTION_APPROVALS.disabled = false; ");
				out.println("    document.Form1.TXT_F_RENTAL_APPROVALS.disabled = false; ");
				out.println("    document.Form1.TXT_OTHER_APPROVAL.disabled = false; ");
				out.println("    document.Form1.TXT_CR_APPROVALS.disabled = false; ");
				out.println("    document.Form1.TXT_RMV_PAPER_APPROVAL.disabled = false; ");
				//out.println("    document.Form1.TXT_ENTRY_APPROVAL.disabled = false; "); // commented by udara 02-04-2015
				out.println("    document.Form1.TXT_CHECKED_BY_APPROVALS.disabled = false; ");
				out.println("    document.Form1.TXT_TEL_APPROVAL.disabled = false; ");
				
				//out.println("    document.Form1.TXT_NIC_NO_1_VENDOR.disabled = false; ");  // commented by udara 23-03-2015
				
				out.println("    document.Form1.Save.disabled = false; "); 
				
				out.println("    document.Form1.TXT_PAID_1_INSURENCE.disabled = false; "); 
				out.println("    document.Form1.TXT_PAID_2_INSURENCE.disabled = false; "); 
				
				out.println("    document.Form1.TXT_PROVINCE_HIRER.disabled = false; "); // added by udara 23-03-2015 
				out.println("    document.Form1.TXT_GRADE_INS_DETAILS.disabled = false; "); // added by udara 23-03-2015 
				
				//out.println("    document.Form1.TXT_NIC_COPY_1_AGREE.disabled = false; "); // added by udara 23-03-2015  
				out.println("    document.Form1.TXT_HIRER_AGREE.disabled = false; "); // added by udara 23-03-2015 
				
				out.println("    document.Form1.TXT_SIGNATOR_VENDOR.disabled = false; "); // added by udara 23-03-2015  
				
				out.println("    document.Form1.TXT_INSURANCE_UPDATE_INSU.disabled = false; "); // added by udara 23-03-2015 
				out.println("    document.Form1.TXT_COVER_NOTE_INSU.disabled = false; "); // added by udara 23-03-2015
				out.println("    document.Form1.TXT_RENTAL_LETTER_ISSUED_INSU.disabled = false; "); // added by udara 23-03-2015
				//out.println("    document.Form1.TXT_INSURENCE_COM_SELECTION.disabled = false; "); // added by udara 23-03-2015
				
				out.println("    document.Form1.TXT_STAMP_DUTY_INSURENCE.disabled = false; "); // added by udara 02-04-2015
				out.println("    document.Form1.TXT_PAID_3_INSURENCE.disabled = false; "); // added by udara 02-04-2015
				
				out.println("    document.Form1.TXT_INSURENCE_COM_SELECTION.value = '"+m_insurance_company+"'; "); // added by udara 02-04-2015
				out.println("    set_cash_to_be_paid(); "); // added by udara 02-04-2015
				
				// commented by udara 22-04-2015
				
				// added by udara 23-03-2015
				out.println("		for (var i=1; i <= document.Form1.hid_guarantor_count.value; i++ ) {");
				//out.println("		   document.getElementById(\"TXT_NIC_COPY_AGREE_\"+i).disabled = false;");
				out.println("		   document.getElementById(\"TXT_GUARANTOR_AGREE_\"+i).disabled = false;");
				out.println("		}");
				// end by udara 23-03-2015
				
				/*
				// added by udara 24-04-2015
				out.println("		if(document.Form1.TXT_REFINANCE_CASE_INSURENCE.value=='N'){");
				out.println("            document.Form1.TXT_REFINANCE_CASE_INSURENCE.disabled = false; ");
				out.println("       }");
				// end by udara 24-04-2015
				*/
				
				// added by udara 27-04-2015
				out.println("		if(document.Form1.TXT_REFINANCE_CASE_INSURENCE.value=='N'){");
				out.println("       	document.Form1.TXT_CLOSING_STAT_APPROVALS.disabled = false; "); 
				out.println("       }");
				// end by udara 27-04-2015
				
				out.println("	document.Form1.TXT_PROVINCE_HIRER.value				=	 '"+m_PROVINCE_HIRER+"';  ");
				out.println("	document.Form1.TXT_GRADE_INS_DETAILS.value			=	 '"+m_GRADE_INS_DETAILS+"';  ");
				out.println("	document.Form1.TXT_HIRER_AGREE.value				=	 '"+m_HIRER_AGREE+"';  ");
				out.println("	document.Form1.TXT_SIGNATOR_VENDOR.value			=	 '"+m_SIGNATOR_VENDOR+"';  ");
				out.println("	document.Form1.TXT_PAID_1_INSURENCE.value			=	 '"+nf.format(m_PAID_1_INSURENCE)+"';  ");
				out.println("	document.Form1.TXT_STAMP_DUTY_INSURENCE.value		=	 '"+nf.format(m_STAMP_DUTY_INSURENCE)+"';  ");
				out.println("	document.Form1.TXT_PAID_2_INSURENCE.value			=	 '"+nf.format(m_PAID_2_INSURENCE)+"';  ");
				out.println("	document.Form1.TXT_PAID_3_INSURENCE.value			=	 '"+nf.format(m_PAID_3_INSURENCE)+"';  ");				
				out.println("	document.Form1.TXT_BAL_3_INSURENCE.value			=	 '"+nf.format(m_BAL_3_INSURENCE)+"';  ");
				out.println("	document.Form1.TXT_INSURANCE_UPDATE_INSU.value		=	 '"+m_INSURANCE_UPDATE_INSU+"';  ");
				out.println("	document.Form1.TXT_COVER_NOTE_INSU.value			=	 '"+m_COVER_NOTE_INSU+"';  ");
				out.println("	document.Form1.TXT_RENTAL_LETTER_ISSUED_INSU.value	=	 '"+m_RENTAL_LETTER_ISSUED_INSU+"';  ");
				out.println("	document.Form1.TXT_INSPECTION_APPROVALS.value		=	 '"+m_INSPECTION_APPROVALS+"';  ");
				out.println("	document.Form1.TXT_CLOSING_STAT_APPROVALS.value		=	 '"+nf.format(m_CLOSING_STAT_APPROVALS)+"';  ");
				out.println("	document.Form1.TXT_LESS_FIRST_RENT_APPROVAL.value	=	 '"+nf.format(m_LESS_FIRST_RENT_APPROVAL)+"';  ");
				out.println("	document.Form1.TXT_OTHER_APPROVAL.value				=	 '"+nf.format(m_OTHER_APPROVAL)+"';  ");
				out.println("	document.Form1.TXT_CR_APPROVALS.value				=	 '"+m_CR_APPROVALS+"';  ");
				out.println("	document.Form1.TXT_RMV_PAPER_APPROVAL.value			=	 '"+m_RMV_PAPER_APPROVAL+"';  ");
				out.println("	document.Form1.TXT_TEL_APPROVAL.value				=	 '"+m_TEL_APPROVAL+"';  ");
				

				out.println("}");
				
				// added by udara 24-04-2015
				out.println("function check_nic_div(m_tmp_client_type,m_tmp_nic_no,m_tmp_gender,m_tmp_date_of_birth,div_obj){");
				/*
				out.println("   alert(m_tmp_client_type); ");
				out.println("   alert(m_tmp_nic_no); ");
				out.println("   alert(m_tmp_gender); ");
				out.println("   alert(m_tmp_date_of_birth); ");
				out.println("   alert(div_obj); ");
				out.println("   alert(validate_NIC_Return(m_tmp_nic_no,m_tmp_gender,m_tmp_date_of_birth)); ");
				*/
				
				out.println("   if(m_tmp_client_type=='I'){ ");
				out.println("     if(!validate_NIC_Return(m_tmp_nic_no,m_tmp_gender,m_tmp_date_of_birth)){");
				out.println("        div_obj.style.color='red';");
				//out.println("         DIV_TXT_HIRER_NIC.style.color='red';");
				out.println("     }");
				out.println("   }");
				
				
				
				out.println("}");
				
				
				// end by udara 24-04-2015
				
				
				// ==============================================================================================================
				
				
				out.println("</script>");
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' onLoad=\"on_load_set()\" >"); //onLoad=\"add_button()\"
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_finance_no' VALUE='"+m_finance_no+"' >"); // added by udara 06-03-2015
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> LAKDERANA INVESTMENTS LTD - CONFIRMATION REPORT - "+m_finance_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				// udara 13-03-2015
				String mmm_client_code = "-";
				int mmm_fin_count = 0;
				
				String lead_source_status = ""; // added by udara 24-04-2015
				
				rs = stmt.executeQuery(" "+
					" SELECT "+
						" CLIENT_CODE, "+  
						//" DECODE(LEAD_SOURCE_CATEGORY,'BROKER','Broker','DIRECT','Direct','TEST','Re-finance','Other') "+ // added by udara 24-04-2015
						" NVL("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_DESC(LEAD_SOURCE_CATEGORY),'-') "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
							" WHERE FINANCE_NO = '"+m_finance_no+"' "+
					" ");
				
				if(rs.next()){
					mmm_client_code = rs.getString(1);
					lead_source_status = rs.getString(2); // added by udara 24-04-2015
				}
				
				rs = stmt.executeQuery(" "+
					" SELECT "+
						" COUNT(FINANCE_NO) "+  
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
							" WHERE CLIENT_CODE = '"+mmm_client_code+"' "+
							" AND FINANCE_NO IS NOT NULL  "+
					" ");
				
				if(rs.next()){
					mmm_fin_count = rs.getInt(1);
				}
				// end udara 13-03-2015
				
				//--------------------------Top--------------------------------------
				
				// added by udara 24-04-2015
				out.println("<table align='center' width='100%' class='table' border=0 >");
				out.println("<tr height='25px'>");
				out.println("<td width='10%'> &nbsp; </td>");
			    out.println("<td width='20%'> <b>STATUS</B></td>");  
				//out.println("<td width='4%'>  &nbsp; </td>"); 
				
				out.println("<td width='4%'>"); 
				out.println(" <input class='txt_input' type='text'   name='TXT_LEAD_SOURCE'      maxlength='200' size='20' style=\"width:250px;\" value='"+lead_source_status+"' >");
				out.println("</td>"); 
				
				out.println("<td width='20%'> &nbsp; </td>"); 
				out.println("<td width='20%'> &nbsp; </td>"); 
				out.println("<td width='20%'> &nbsp; </td>"); 
				out.println("<td width='50%'> &nbsp; </td>"); 
				
				out.println("</tr>");
				//out.println("</table>");
				// end by udara 24-04-2015
				
				//out.println("<table align='center' width='100%' class='table' border=0 >");
				out.println("<tr height='25px'>");
				out.println("<td width='10%'></td>");  
				out.println("<td width='20%' valign='top' ><b>EXISTING CUSTOMER</td>");
				out.println("<td width='4%'  valign='top' ><select class='txt_input' name='TXT_EXISTING_CUSTOMER'>");  // TXT_EXSIT_CUST
				//out.println("<option value='YES'  > Yes </option>");
				//out.println("<option value='NO' > No </option>");
				
				// udara 13-03-2015
				if(mmm_fin_count > 1){
					out.println("<option value='YES' selected > Yes </option>");
				 	out.println("<option value='NO' > No </option>");
				}
				else{
					out.println("<option value='YES'  > Yes </option>");
				 	out.println("<option value='NO' selected > No </option>");
				}
				// end udara 13-03-2015
				
				out.println("</select>");
				out.println("</td>");
				out.println("<td width='20%'> &nbsp; </td>");
				
				//out.println("<td width='20%' ><DIV id='DIV_TXT_CONTRACT_BRANCH' class=div_input style=\"display: none;\" ><b>IF 'YES' CONTRACT NUMBER</DIV></td>");
				//out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_CONTRACT_BRANCH' maxlength='200' size='10' style=\"display: none;\" value='-' ></td>"); 
				
				// udara 13-03-2015
				out.println("<td width='20%'>");

				rs = stmt.executeQuery(" "+
					" SELECT "+
						" FINANCE_NO "+  
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
							" WHERE CLIENT_CODE = '"+mmm_client_code+"' "+
							" AND FINANCE_NO IS NOT NULL  "+
					" ");
				
				if(mmm_fin_count > 1){
						out.println("<table border=0>");
						
						out.println(" <tr><td><b>Existing Contracts</b></td></tr>");
						
						while(rs.next()){
							//out.println(" <tr><td> "+rs.getString(1)+" </td></tr>");
							out.println("<tr><td  STYLE='text-align:left;   cursor:hand;'  onclick=\"show_transaction_info('','"+rs.getString(1)+"');\"  ><u>"+rs.getString(1)+"</u></td></tr>"); 
						}
						out.println("</table>");
				}
			
				out.println("</td>");
				
				out.println("<td width='20%' ><DIV id='DIV_TXT_CONTRACT_BRANCH' class=div_input style=\"display: none;\" ><b>IF 'YES' CONTRACT NUMBER</DIV></td>");
				out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_CONTRACT_BRANCH' maxlength='200' size='10' style=\"display: none;\" value='-' ></td>"); 
				
				// end udara 13-03-2015
				
				
				out.println("</tr>");
				out.println("</table>");
				
				
				// added by udara 20-01-2015
				String m_user_location = "-";
				String m_dd = "";
				String m_mm = "";
				String m_yy = "";
				
				rs = stmt.executeQuery(" "+
						" SELECT "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"'), "+
						    " TO_CHAR(SYSDATE,'DD'), "+
							" TO_CHAR(SYSDATE,'MM'), "+
							" TO_CHAR(SYSDATE,'YYYY') "+
						" FROM DUAL "+
					" ");
				
				if(rs.next()){
					m_user_location = rs.getString(1);
					m_dd = rs.getString(2);
					m_mm = rs.getString(3);
					m_yy = rs.getString(4);
				}	
				// end by udara 20-01-2015
				
				out.println("<hr>");
				//--------------------------1.Branch--------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='6%' ><b>1) BRANCH</b></td>");
				out.println("</tr>");
				out.println("<tr  height='25px'>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='20%' ><b>BRANCH</b></td>"); //<DIV id='DIV_TXT_LOCATION_CODE'  class=txt_input></DIV>
				out.println("<td width='4%' ><select name=\"TXT_LOCATION_CODE\" class=\"txt_input\" >");	
				rs = stmt_2.executeQuery(
					" SELECT LOCATION_CODE, "+
					"        LOCATION_DESC "+
					" FROM "+m_schema_name+".AF_CO_MAS_LOCATION  WHERE ACTIVE_STATUS='Y' ");
				
				out.println("<OPTION value=\"ALL\" >ALL</option>"); // out.println("<OPTION value=\"ALL\" SELECTED >ALL</option>");
				while(rs.next()){
					//out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					
					if(m_user_location.equals(rs.getString(1)))
						out.println("<OPTION value=\""+rs.getString(1)+"\" SELECTED >"+rs.getString(2)+"</option>");
					else
						out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					
				}	
				out.println("</SELECT></TD>");
				out.println("<td width='20%'></td>");
				out.println("<td width='20%'ID=VDATE><b>DATE</b></td>");
				out.println("<td width='50%'>");
				out.println("    <input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR) value='"+m_dd+"' > ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR) value='"+m_mm+"' > ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR) value='"+m_yy+"' >");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>"); 
				
				out.println("<hr>");
				
				// added by udara 20-01-2015
				String hirer_cli_code = "-";
				String hirer_co_app_code = "-";
				String hirer_cli_name = "-";
				String hirer_co_app_name = "-";
				String hirer_veh_no = "-";
				
				String hirer_cli_id_no = "-";
				String hirer_co_app_id_no = "-";
				String hirer_cus_contact_no = "-";
				String hirer_cus_address = "-";
				
				String hirer_city = "-";
				String hirer_district = "-";
				String hirer_province = "-";
				
				String m_application_no = "-";
				String m_lead_source_code = "-";
				String m_lead_source_name = "-";
				String m_re_fin_no = "-";
				
				String m_cr_officer = "-";
				String m_cr_officer_name = "-";
				
				rs = stmt.executeQuery(" "+
					" SELECT "+
						" CLIENT_CODE, "+  
						" CO_APPLICANT, "+
					    " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+
						" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-'), "+
						" NVL("+m_schema_name+".AF_CO_GET_CLIENT_FULL_ADDRESS(CLIENT_CODE),'-'), "+
						" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(APPLICATION_NO),'-'), "+
						" APPLICATION_NO, "+
						" LEAD_SOURCE_NAME, "+
						//" NVL("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_DESC(LEAD_SOURCE_NAME),'-'), "+ // commented by udara 23-03-2015
						//" NVL((SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=LEAD_SOURCE_NAME ),LEAD_SOURCE_NAME), "+ // added by udara 23-03-2015
						" NVL(NVL((SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=LEAD_SOURCE_NAME ),LEAD_SOURCE_NAME),'-') , "+ // added by udara 24-04-2015
						" NVL(RE_FIN_NO,'-'), "+
						" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_3(APPLICATION_NO),'-'), "+
						" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_2(APPLICATION_NO),'-') "+
						//" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME("+m_schema_name+".AF_CO_GET_CR_OFFICER(APPLICATION_NO)),'-') "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
							" WHERE FINANCE_NO = '"+m_finance_no+"' "+
					" ");
				
				if(rs.next()){
					hirer_cli_code = rs.getString(1);
					hirer_co_app_code = rs.getString(2);
					hirer_cli_name = rs.getString(3);
					hirer_co_app_name = rs.getString(4);
					hirer_cus_address = rs.getString(5);
					hirer_veh_no = rs.getString(6);
					m_application_no = rs.getString(7);
					m_lead_source_code = rs.getString(8);
					m_lead_source_name = rs.getString(9);
					m_re_fin_no = rs.getString(10);
					m_cr_officer = rs.getString(11);
					m_cr_officer_name = rs.getString(12);
				}
				
				String hirer_client_type = ""; // added by udara 24-04-2015 
				String hirer_gender = ""; // added by udara 24-04-2015 
				String hirer_dob = ""; // added by udara 24-04-2015 
				String hirer_nic_no = ""; // added by udara 24-04-2015 
				
				rs = stmt.executeQuery(" "+
					" SELECT "+
						" NVL(NIC_NO,'-'), "+  
						" NVL(MOBILE_NO,'-'), "+
						" CITY_CODE, "+
						
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'), "+
						" NVL(CLIENT_TYPE,'-'), "+ // added by udara 24-04-2015  
						" NVL(GENDER,'-'), "+ // added by udara 24-04-2015
						" NVL(TO_CHAR(DATE_OF_BIRTH,'DD-MM-YYYY'),'-') "+ // added by udara 24-04-2015

							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
							" WHERE CLIENT_CODE = '"+hirer_cli_code+"' "+
					" ");
				
				if(rs.next()){
					hirer_cli_id_no = rs.getString(1);
					hirer_cus_contact_no = rs.getString(2);
					hirer_city = rs.getString(3);
					
					hirer_nic_no = rs.getString(4); // added by udara 24-04-2015 
					hirer_client_type = rs.getString(5); // added by udara 24-04-2015 
					hirer_gender = rs.getString(6); // added by udara 24-04-2015 
					hirer_dob = rs.getString(7); // added by udara 24-04-2015 					
					
				}
				
				
				String hirer_co_app_client_type = ""; // added by udara 24-04-2015 
				String hirer_co_app_gender = ""; // added by udara 24-04-2015 
				String hirer_co_app_dob = ""; // added by udara 24-04-2015 
				String hirer_co_app_nic_no = ""; // added by udara 24-04-2015 
				
				
				rs = stmt.executeQuery(" "+
					" SELECT "+
						" NVL(NIC_NO,'-'), "+  
						
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'), "+
						" CLIENT_TYPE, "+ // added by udara 24-04-2015  
						" GENDER, "+ // added by udara 24-04-2015
						" NVL(TO_CHAR(DATE_OF_BIRTH,'DD-MM-YYYY'),'-') "+ // added by udara 24-04-2015
						
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
							" WHERE CLIENT_CODE = '"+hirer_co_app_code+"' "+
					" ");
				
				if(rs.next()){
					hirer_co_app_id_no = rs.getString(1);
					
					hirer_co_app_nic_no = rs.getString(2); // added by udara 24-04-2015 
					hirer_co_app_client_type = rs.getString(3); // added by udara 24-04-2015 
					hirer_co_app_gender = rs.getString(4); // added by udara 24-04-2015 
					hirer_co_app_dob = rs.getString(5); // added by udara 24-04-2015 		
					
				}
				
				
				rs = stmt.executeQuery(" "+
						" SELECT DISTRICT_CODE "+
						" FROM "+m_schema_name+".AF_CO_MAS_CITY "+
						" WHERE CITY_CODE = '"+hirer_city+"' "+
						" ");
				
				if(rs.next()){
					hirer_district = rs.getString(1);
				}
				
				rs = stmt.executeQuery(" "+
						" SELECT PROVINCE_CODE "+
						" FROM "+m_schema_name+".AF_CO_MAS_DISTRICT "+
						" WHERE DISTRICT_CODE = '"+hirer_district+"' "+
						" ");
				
				if(rs.next()){
					hirer_province = rs.getString(1);
				}
				
				// end by udara 20-01-2015
				
				
				//--------------------------2.Hirer--------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='6%' ><b>2) HIRER</b></td>");
				out.println("</tr>");
				out.println("<tr  height='25px'>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='9.9%' ><b>NAME 1</b></td>");
				out.println("<td width='9%'></td>");
				out.println("<td width='4%' >");
				out.println("<input class='txt_input' type='hidden' name='TXT_NAME_1_HIRER_CODE' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_cli_code+"' >");
				out.println("<input class='txt_input' type='text'   name='TXT_NAME_1_HIRER'      maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_cli_name+"' >");
				
				out.println("<input class='txt_input' type='hidden' name='TXT_HIRER_NIC' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_nic_no+"' >"); // added by udara 24-04-2015
				out.println("<input class='txt_input' type='hidden' name='TXT_HIRER_CLI_TYPE' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_client_type+"' >"); // added by udara 24-04-2015
				out.println("<input class='txt_input' type='hidden' name='TXT_HIRER_GENDER' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_gender+"' >"); // added by udara 24-04-2015
				out.println("<input class='txt_input' type='hidden' name='TXT_HIRER_DOB' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_dob+"' >"); // added by udara 24-04-2015
				
				out.println("</td>"); 
				out.println("<td width='21%'></td>");
				out.println("<td width='20%' ><b>CONTACT NUMBER</td>");//(Select prefix from drop down list)
				out.println("<td width='50%' >");
				out.println("<input class='txt_input' type='text' name='TXT_CONTACT_NUMBER_HIRER' maxlength='200' size='10' value='"+hirer_cus_contact_no+"'  >");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='9.9%' ><b>NAME 2</b></td>"); 
				out.println("<td width='10%'></td>");
				out.println("<td width='4%' >");
			    out.println("<input class='txt_input' type='hidden' name='TXT_NAME_2_HIRER_CODE' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_co_app_code+"' >");
			    out.println("<input class='txt_input' type='text'   name='TXT_NAME_2_HIRER'      maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_co_app_name+"' >"); // hirer_co_app_code
				
				/*
				out.println("<input class='txt_input' type='hidden' name='TXT_HIRER_NIC_2' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_co_app_nic_no+"' >"); // added by udara 24-04-2015
				out.println("<input class='txt_input' type='hidden' name='TXT_HIRER_CLI_TYPE_2' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_co_app_client_type+"' >"); // added by udara 24-04-2015
				out.println("<input class='txt_input' type='hidden' name='TXT_HIRER_GENDER_2' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_co_app_gender+"' >"); // added by udara 24-04-2015
				out.println("<input class='txt_input' type='hidden' name='TXT_HIRER_DOB_2' maxlength='200' size='20' style=\"width:250px;\" value='"+hirer_co_app_dob+"' >"); // added by udara 24-04-2015
				*/
				
				out.println("<td width='21%'></td>");
				out.println("<td width='20%' ><DIV id='DIV_TXT_VEHICLE_NO_HIRER' class=div_input><b>VEHICLE NO</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_VEHICAL_NO_HIRER' maxlength='200' size='10' value='"+hirer_veh_no+"' >"); 
				out.println("</tr>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='9.9%' ><DIV id='DIV_TXT_HIRER_NIC' class=div_input><b>NIC NO 1</b></DIV></td>"); // udara 24-04-2015 
				out.println("<td width='10%'></td>");
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_NIC_NO_1_HIRER' maxlength='200' size='10' value='"+hirer_cli_id_no+"' >");  
				out.println("<td width='20%'></td>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CONTACT_NO_HIRER' class=div_input> <b>ADDRESS</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_ADDRESS_HIRER' maxlength='200' size='10' style=\"width:250px;\" value='"+hirer_cus_address+"' ></td>"); 
				out.println("</tr>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='10%'></td>"); 
				//out.println("<td width='9.9%' ><b>NIC NO 2</b></td>"); 
				out.println("<td width='9.9%' ><DIV id='DIV_TXT_HIRER_NIC_2' class=div_input><b>NIC NO 2</b></DIV></td>"); // udara 24-04-2015 
				out.println("<td width='10%'></td>");
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_NIC_NO_2_HIRER' maxlength='200' size='10' value='"+hirer_co_app_id_no+"' ></td>"); 
				out.println("<td width='20%' ></td>");  
				out.println("<td width='20%'> <b>PROVINCE</b> </td>"); 
				out.println("<td width='20%' > ");
			    ////////////////
				
				out.println("<select class='txt_input' name='TXT_PROVINCE_HIRER' >"); 
				
				rs = stmt.executeQuery(" "+
						" SELECT "+
							" PROVINCE_CODE, "+
							" PROVINCE_DESC "+
								" FROM "+m_schema_name+".AF_CO_MAS_PROVINCE "+
								" WHERE ACTIVE_STATUS = 'Y' "+
						" ");
	
				
				// added by udara 23-03-2015
				out.println("<OPTION value='' ></option>");
				out.println("<OPTION value='CP' > Central Province </option>");
				out.println("<OPTION value='EP' > Eastern Province </option>");
				out.println("<OPTION value='NP' > Nothern Province </option>");
				out.println("<OPTION value='SP' > Southern Province </option>");
				out.println("<OPTION value='WP' > Western Province </option>");
				out.println("<OPTION value='NWP' > North Western Province </option>");
				out.println("<OPTION value='NCP' > North Central Province </option>");
				out.println("<OPTION value='UP' > Uva Province </option>");
				out.println("<OPTION value='SP' > Sabaragamuwa Province </option>");
				// end by udara 23-03-2015
				
				out.println("</select>");
				
			    out.println("</td>"); 
				
				
				out.println("</tr>");
				//out.println("</table>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='4%'></td>"); //(Select Province from drop down list)	
				out.println("<td width='9%'></td>");
				out.println("<td width='4%' >");
				
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='9.9%' > </td>");
				out.println("<td width='9%'></td>");
				out.println("<td width='4%' >");
				out.println("</td>"); 
				out.println("<td width='21%'></td>");
				out.println("<td width='20%' style=\"display: none;\" ><b>CONTRACT NO</td>");//(Select prefix from drop down list)
				out.println("<td width='50%' style=\"display: none;\" >");

				out.println("<input class='txt_input' type='text' name='TXT_CONTRACT_NO_HIRE' maxlength='200' size='10'  value='"+m_finance_no+"' >"); // udara 13-03-2015
				
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("</table>");
				out.println("<hr>");
				
				// added by udara 22-01-2015
				int valuation_count = 0;
				String valuation_status = "P";
				
				rs = stmt.executeQuery(" "+
							" SELECT COUNT(STATUS) "+
								" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
								" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
								" AND CLIENT_CODE = '"+hirer_cli_code+"' "+
								" AND DOCUMENT_TYPE = 'VALUATION' "+
						" ");
				
				if(rs.next()){
					valuation_count = rs.getInt(1);
				}
				
				
				if(valuation_count > 0){
					
					rs = stmt.executeQuery(" "+
							" SELECT STATUS "+
								" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
								" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
								" AND CLIENT_CODE = '"+hirer_cli_code+"' "+
								" AND DOCUMENT_TYPE = 'VALUATION' "+
						" ");
					
					if(rs.next()){
						valuation_status = rs.getString(1);
					}
					
				}

				
				// end by udara 22-01-2015
				
				
				//--------------------------Vehicle Inspection Details--------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='20%' ><b>3) VEHICLE INSPECTION DETAILS</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr height='25px'>");
				out.println("<td width='10%'></td>");  
				out.println("<td width='4%' ><b>VALUATION</td>");
				out.println("<td width='4%' >");
				out.println("<select class='txt_input' name='TXT_VALUATION_INS_DETAILS'>"); 
				/*
				out.println("<option value='Y' > Yes </option>");
				out.println("<option value='N' > No </option>");
				out.println("<option value='P' > Pending </option>");
				*/
				
				if(valuation_status.equals("Y")){
					out.println("<option value='Y' selected > Yes </option>");
					out.println("<option value='N' > No </option>");
					out.println("<option value='P' > Pending </option>");
				}
				//else if(valuation_status.equals("N")){ // commented by udara 11-05-2015
				else if(valuation_status.equals("A")){ // added by udara 11-05-2015
					out.println("<option value='Y' > Yes </option>");
					out.println("<option value='N' selected > No </option>");
					out.println("<option value='P' > Pending </option>");
				}
				//else if(valuation_status.equals("P")){ // commented by udara 11-05-2015
				else if(valuation_status.equals("N")){ // added by udara 11-05-2015
					out.println("<option value='Y' > Yes </option>");
					out.println("<option value='N' > No </option>");
					out.println("<option value='P' selected > Pending </option>");
				}
				
				out.println("</select>");
				out.println("</td>");
				
				String m_reg_no = "-";
				String m_engine_no = "-";
				String m_chasis_no = "-";
				String m_colour = "-";
				double m_value = 0;
				double m_forced_sale_value = 0;
				//double m_valuation_notes = 0; // added by udara 23-03-2015
				int m_valuation_notes = 0; // added by udara 24-04-2015
				
				rs = stmt.executeQuery(" "+
							" SELECT "+
								" NVL(FORCED_SALES_VALUE,0), "+
								" NVL(REG_NO,'-'), "+
								" NVL(ENGINE_NO,'-'), "+
								" NVL(CHASSIS_NO,'-'), "+
								" NVL(COLOUR,'-'), "+
								" NVL(VALUE,0), "+
								" NVL(NOTES,0)  "+ // added by udara 23-03-2015
									" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
						" ");
				
				if(rs.next()){
					m_forced_sale_value = rs.getDouble(1);
					m_reg_no = rs.getString(2);
					m_engine_no = rs.getString(3);
					m_chasis_no = rs.getString(4);
					m_colour = rs.getString(5);
					m_value = rs.getDouble(6);
					//m_valuation_notes = rs.getDouble(7);
					m_valuation_notes = rs.getInt(7); // udara 24-04-2015
				}
				
				// added by udara 23-03-2015
				rs = stmt.executeQuery(" "+
						" SELECT "+
							" NVL(ENGINE_NO,'-'), "+
							" NVL(CHASSIS_NO,'-'), "+
							" NVL(REG_NO,'-'), "+
							" NVL(COLOUR,'-') "+
								" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
								" WHERE APPLICATION_NO = '"+m_application_no+"' "+
								" AND   ACTIVE_STATUS='Y' "+
								" ");
				
				if(rs.next()){
					m_engine_no = rs.getString(1);
					m_chasis_no = rs.getString(2);
					m_reg_no = rs.getString(3);
					m_colour = rs.getString(4);
				}
				// end by udara 23-03-2015
				
				
				out.println("<td width='10%'></td>");
				out.println("<td width='5%' ><DIV id='DIV_TXT_MARKET_INS_DETAILS' class=div_input><b>MARKET</DIV></td>");
				out.println("<td  ><input class='txt_input' type='text' name='TXT_MARKET' maxlength='200' size='10' value='"+nf.format(m_value)+"' onblur='validate_number_text_boxes(this);' ></td>"); 
				out.println("</td>");
				out.println("<td width='5%'></td>");
				out.println("<td width='5%' ><DIV id='DIV_TXT_FORCED_SALE' class=div_input><b>FORCED SALE</DIV></td>");
				out.println("<td  ><input class='txt_input' type='text' name='TXT_FORCED_SALE_INS_DETAILS' maxlength='200' size='10' value='"+nf.format(m_forced_sale_value)+"' onblur='validate_number_text_boxes(this);' ></td>"); 
				out.println("</tr>");
				
				out.println("<tr height='25px'>");
				out.println("<td width='10%'></td>");  
				out.println("<td width='19%' ><DIV id='DIV_TXT_VEHICLE_NO_2_INS_DETAILS' class=div_input><b>VEHICLE NO</b></DIV></td>"); 
				out.println("<td  ><input class='txt_input' type='text' name='TXT_VEHICAL_NO_2_INS_DETAILS' maxlength='200' size='10' value='"+m_reg_no+"' >"); 
				out.println("</td>");
				
				/*
				out.println("<td width='10%'></td>");
				out.println("<td width='10%' ><DIV id='DIV_TXT_GRADE_INS_DETAILS' class=div_input><b>GRADE</DIV></td>");
				out.println("<td  ><input class='txt_input' type='text' name='TXT_GRADE_INS_DETAILS' maxlength='200' size='10' value='' ></td>"); 
				out.println("</td>");
				*/
				
				out.println("<td width='10%'></td>");
				out.println("<td width='10%' ><DIV id='DIV_TXT_GRADE_INS_DETAILS' class=div_input><b>GRADE</DIV></td>");
				out.println(" <td>");
				out.println("   <select class='txt_input' name='TXT_GRADE_INS_DETAILS'>"); 
				out.println("     <option value='' ></option>");
				out.println("     <option value='A' > A </option>");
				out.println("     <option value='B' > B </option>");
				out.println("     <option value='C' > C </option>");
				out.println("     <option value='D' > D </option>");
				out.println("   </select>");
				out.println(" </td>"); 
				out.println("</td>");
				
				out.println("<td width='10%'></td>");
				out.println("<td width='10%' ><DIV id='DIV_TXT_CHASSIS_NO_INS_DETAILS' class=div_input><b>CHASSIS NO.</DIV></td>");
				out.println("<td  ><input class='txt_input' type='text' name='TXT_CHASSIS_NO_INS_DETAILS' maxlength='200' size='10' value='"+m_chasis_no+"'  ></td>"); 
				out.println("</tr>");
				
				out.println("<tr height='25px'>");
				out.println("<td width='10%'></td>");  
				out.println("<td width='19%' ><DIV id='DIV_TXT_COLOUR_INS_DETAILS' class=div_input><b>COLOUR</b></DIV></td>"); 
				out.println("<td  ><input class='txt_input' type='text' name='TXT_COLOUR_INS_DETAILS' maxlength='200' size='10' value='"+m_colour+"' >"); 
				out.println("</td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%' ><DIV id='DIV_TXT_ENGINE_NOINS_DETAILS' class=div_input><b>ENGINE NO.</DIV></td>");
				out.println("<td  ><input class='txt_input' type='text' name='TXT_ENGINE_NO_INS_DETAILS' maxlength='200' size='10' value='"+m_engine_no+"' ></td>"); 
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<hr>");
				//--------------------------Agreement & all papaers signed by--------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='20%' ><b>4) AGREEMENT & ALL PAPERS SIGNED BY</b></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align='center' width='100%'  class='table' border=0 >");
				
				out.println("<tr height='25px'>");
				
				out.println("<td width='4%'> &nbsp; </td>");  
				
				out.println("<td width='6%' ><b>HIRER</td>");
				
				int hirer_nic_count_org = 0; // added by udara 22-04-2015
				String hirer_nic_status_org = "P"; // added by udara 22-04-2015
				
				/*
				// added by udara 22-04-2015
				
				rs = stmt.executeQuery(" "+
							" SELECT COUNT(STATUS) "+
								" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
								" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
								" AND CLIENT_CODE = '"+hirer_cli_code+"' "+
								" AND DOCUMENT_TYPE = 'NIC' "+
						" ");
				
				if(rs.next()){
					hirer_nic_count_org = rs.getInt(1);
				}
				
				if(hirer_nic_count_org > 0){
					
					rs = stmt.executeQuery(" "+
							" SELECT STATUS "+
								" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
								" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
								" AND CLIENT_CODE = '"+hirer_cli_code+"' "+
								" AND DOCUMENT_TYPE = 'NIC' "+
						" ");
					
					if(rs.next()){
						hirer_nic_status_org = rs.getString(1);
					}
					
				}
				
				// end by udara 22-04-2015
				*/
				
				
				out.println("<td width='4%' >");
				
				// commented by udara 22-04-2015
				
			    out.println("   <select class='txt_input' name='TXT_HIRER_AGREE'>"); 
				out.println("     <option value=''  ></option>");
				out.println("     <option value='Y'  > Yes </option>");
				out.println("     <option value='N' > No </option>");
				out.println("     <option value='P' > Pending </option>");
				out.println("   </select>");
				
				
				/*
				// added by udara 22-04-2015
				out.println("   <select class='txt_input' name='TXT_HIRER_AGREE'>"); 
				
				if(hirer_nic_status_org.equals("Y")){

					out.println("     <option value='Y'  selected > Yes </option>");
					out.println("     <option value='N' > No </option>");
					out.println("     <option value='P' > Pending </option>");
					
				}
				
				else if(hirer_nic_status_org.equals("N")){

					out.println("     <option value='Y' > Yes </option>");
					out.println("     <option value='N' selected > No </option>");
					out.println("     <option value='P' > Pending </option>");
					
				}
				else if(hirer_nic_status_org.equals("P")){
					
					out.println("     <option value='Y' > Yes </option>");
					out.println("     <option value='N' > No </option>");
					out.println("     <option value='P' selected > Pending </option>");
					
				}
				
				out.println("   </select>");
				// end by udara 22-04-2015
				*/
				
				
				out.println("</td>");
				
				out.println("<td width='2%' > &nbsp; </td>");
				out.println("<td width='3%' > &nbsp; </td>");
				out.println("<td width='4%' > &nbsp; </td>");
				out.println("<td width='2%' > &nbsp; </td>");

				
				int hirer_nic_count = 0;
				String hirer_nic_status = "P";
				
				rs = stmt.executeQuery(" "+
							" SELECT COUNT(STATUS) "+
								" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
								" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
								" AND CLIENT_CODE = '"+hirer_cli_code+"' "+
								" AND DOCUMENT_TYPE = 'NIC' "+
								//" AND DOCUMENT_TYPE = 'NICG' "+
						" ");
				
				if(rs.next()){
					hirer_nic_count = rs.getInt(1);
				}
				
				if(hirer_nic_count > 0){
					
					rs = stmt.executeQuery(" "+
							" SELECT STATUS "+
								" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
								" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
								" AND CLIENT_CODE = '"+hirer_cli_code+"' "+
								" AND DOCUMENT_TYPE = 'NIC' "+
								//" AND DOCUMENT_TYPE = 'NICG' "+
						" ");
					
					if(rs.next()){
						hirer_nic_status = rs.getString(1);
					}
					
				}
				
				
				out.println("<td width='5%' ><b>NIC COPY</td>");
				
				out.println("<td width='4%' >");
				out.println("  <select class='txt_input' name='TXT_NIC_COPY_1_AGREE' >");
			
				// commented by udara 23-03-2015
				
				if(hirer_nic_status.equals("Y")){
					out.println("      <option value='Y' selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");
				}
				//else if(hirer_nic_status.equals("N")){ // commented by udara 11-05-2015
				else if(hirer_nic_status.equals("A")){ // added by udara 11-05-2015
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");
				}
				//else if(hirer_nic_status.equals("P")){ // commented by udara 11-05-2015
				else if(hirer_nic_status.equals("N")){ // added by udara 11-05-2015
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");
				}
				
				
				
				/*
				// added by udara 23-03-2015
				out.println("      <option value='Y' > Yes </option>");
				out.println("      <option value='N' > No </option>");
				out.println("      <option value='P' > Pending </option>");
				// end by udara 23-03-2015
				*/
				
				
				out.println("  </select>");
				out.println("</td>");
				
				
				//out.println("<td width='10%'></td>");
				
				out.println("<td width='2%' > &nbsp; </td>");
				out.println("<td width='5%' > &nbsp; </td>");
				out.println("<td width='4%' > &nbsp; </td>");
				
				out.println("</tr>");
				//out.println("</table>");
				
				//out.println("<table align='center' width='100%'   class='table' border=1 >");
				
				// ====================================================================================================================
				int total_guarantor_count = 0;
				
				rs = stmt.executeQuery(" "+
						" SELECT COUNT(GUARANTOR_CODE) "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
							" WHERE APPLICATION_NO = '"+m_application_no+"' "+
							" AND ACTIVE_STATUS = 'Y' "+
				" ");
				
				if(rs.next()){
					total_guarantor_count = rs.getInt(1);
					
				}
				
				out.println("<tr> <INPUT TYPE='Hidden' NAME='hid_guarantor_count' VALUE='"+total_guarantor_count+"' > </tr>");
				
				
				rs = stmt.executeQuery(" "+
						" SELECT GUARANTOR_CODE, "+
							" "+m_schema_name+".AF_GET_CLIENT_FULL_NAME(GUARANTOR_CODE), "+
							" (SELECT NVL(NIC_NO,'-') FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE = GUARANTOR_CODE) GUARANTOR_NIC "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
							" WHERE APPLICATION_NO = '"+m_application_no+"' "+
							" AND ACTIVE_STATUS = 'Y' "+
				" ");
				
				//int guarantor_count = 1;
				int guarantor_count = 0;
				double guarantor_nic_count = 0;
				String guarantor_nic_status = "P";
				
				while(rs.next()){
					
					guarantor_count = guarantor_count + 1;
					
					guarantor_nic_count = 0;
				    guarantor_nic_status = "P";
					
					out.println("<tr height='25px'>");
				
					out.println("<td width='4%'> &nbsp; </td>"); 
		
					//out.println("<td width='6%' ><b>GUARANTOR "+guarantor_count+" <INPUT TYPE='Hidden' NAME='hid_guarantor_count' VALUE='"+total_guarantor_count+"' ></td>");
					out.println("<td width='6%' ><b>GUARANTOR "+guarantor_count+" </td>");
					
					out.println("<td width='4%' >");
					out.println("   <select class='txt_input' name='TXT_GUARANTOR_AGREE_"+guarantor_count+"' id='TXT_GUARANTOR_AGREE_"+guarantor_count+"' >"); 
					out.println("      <option value='' ></option>");
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");
					out.println("   </select>");
					out.println("</td>");
					
					out.println("<td width='2%'> &nbsp; </td>"); 
					
					out.println("<td width='3%' ><DIV id='DIV_TXT_NAME_AGREE_"+guarantor_count+"' class=div_input><b>NAME</DIV></td>");
					//out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_NAME_AGREE_"+guarantor_count+"' maxlength='200' size='10' style=\"width:180px;\" value='"+rs.getString(2)+"' ></td>"); 
					
					out.println("<td width='4%' >");
				    out.println(" <input class='txt_input' type='text' name='TXT_NAME_AGREE_"+guarantor_count+"' maxlength='200' size='10' style=\"width:200px;\" value='"+rs.getString(2)+"' > ");
				    out.println(" <INPUT TYPE='Hidden' name='TXT_CODE_AGREE_"+guarantor_count+"' value='"+rs.getString(1)+"' >  ");
					out.println("</td>"); 
					
					out.println("<td width='2%'> &nbsp; </td>"); 
					out.println("<td width='5%' ><b>NIC COPY</td>");
					
					rs2 = stmt_2.executeQuery(" "+
							" SELECT COUNT(STATUS) "+
								" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
								" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
								" AND CLIENT_CODE = '"+rs.getString(1)+"' "+
								" AND DOCUMENT_TYPE = 'NIC' "+ // released by udara 13-05-2015
								//" AND DOCUMENT_TYPE = 'NICG' "+
						" ");
				
					if(rs2.next()){
						guarantor_nic_count = rs2.getInt(1);
					}
					
					if(guarantor_nic_count > 0){
						
						rs2 = stmt_2.executeQuery(" "+
								" SELECT STATUS "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'NIC' "+ // released by udara 13-05-2015
									//" AND DOCUMENT_TYPE = 'NICG' "+
							" ");
						
						if(rs2.next()){
							guarantor_nic_status = rs2.getString(1);
						}
						
					}
					
					
					out.println("<td width='4%' >");
	 				out.println("   <select class='txt_input' name='TXT_NIC_COPY_AGREE_"+guarantor_count+"' id='TXT_NIC_COPY_AGREE_"+guarantor_count+"' >"); 
					
					// commented by udara 23-03-2015	
						
					if(guarantor_nic_status.equals("Y")){	
						out.println("     <option value='Y' selected > Yes </option>");
						out.println("     <option value='N' > No </option>");
						out.println("     <option value='P' > Pending </option>");
					}
					//else if(guarantor_nic_status.equals("N")){ // commented by udara 11-05-2015
					else if(guarantor_nic_status.equals("A")){ // added by udara 11-05-2015
						out.println("     <option value='Y' > Yes </option>");
						out.println("     <option value='N' selected > No </option>");
						out.println("     <option value='P' > Pending </option>");
					}
					//else if(guarantor_nic_status.equals("P")){ // commented by udara 11-05-2015
					else if(guarantor_nic_status.equals("N")){ // added by udara 11-05-2015
						out.println("     <option value='Y' > Yes </option>");
						out.println("     <option value='N' > No </option>");
						out.println("     <option value='P' selected > Pending </option>");
					}
					
					
					// commented by udara 22-04-2015
					/*
					// added by udara 23-03-2015
					out.println("     <option value='Y' > Yes </option>");
					out.println("     <option value='N' > No </option>");
					out.println("     <option value='P' > Pending </option>");
					// added by udara 23-03-2015
					*/
					
					out.println("   </select>");
					out.println("</td>");
					
					
					out.println("<td width='2%'> &nbsp; </td>");
					out.println("<td width='5%' ><DIV id='DIV_TXT_NUMBER_AGREE_"+guarantor_count+"' class=div_input><b>NUMBER</DIV></td>");
					out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_NUMBER_AGREE_"+guarantor_count+"' maxlength='200' size='10' value='"+rs.getString(3)+"' ></td>"); 
					
					out.println("</tr>");
					
				}

				// ====================================================================================================================
				
				/*
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'></td>");  
				out.println("<td width='6%' ><b>GUARANTOR 2</td>");
				out.println("<td  width='4%'><select class='txt_input' name='TXT_GUARANTOR_2_AGREE'>"); 
				out.println("<option value='YES'  > Yes </option>");
				out.println("<option value='NO' > No </option>");
				out.println("<option value='PENDING' > Pending </option>");
				out.println("</select>");
				out.println("</td>");
				
				out.println("<td width='2%'></td>"); 
				out.println("<td width='3%' ><DIV id='DIV_TXT_NAME_AGREE_2' class=div_input><b>NAME</DIV></td>");
				out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_NAME_AGREE_2' maxlength='200' size='10' ></td>"); 
				out.println("<td width='2%'></td>");
				out.println("<td width='5%' ><b>NIC COPY</td>");
				out.println("<td  ><select class='txt_input' name='TXT_NIC_COPY_2_AGREE'>"); 
				out.println("<option value='YES'  > Yes </option>");
				out.println("<option value='NO' > No </option>");
				out.println("<option value='PENDING' > Pending </option>");
				out.println("</select>");
				out.println("</td>");
				out.println("<td width='2%'></td>");
				out.println("<td width='5%' ><DIV id='DIV_TXT_NUMBER_1_AGREE' class=div_input><b>NUMBER</DIV></td>");
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_NUMBER_1_AGREE' maxlength='200' size='10' ></td>"); 
				out.println("</tr>");
				
				*/
				
				out.println("</table>");
				
				out.println("<hr>");
				//--------------------------Vendor--------------------------------------
				
				String vender_code = "-";
				String vender_name = "-";
				
				rs = stmt.executeQuery(" "+
							" SELECT VENDOR_CODE, "+
							" "+m_schema_name+".AF_CO_GET_VENDOR_NAME(VENDOR_CODE) "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
							" WHERE APPLICATION_NO = '"+m_application_no+"' "+
							" AND ACTIVE_STATUS = 'Y' "+						
				" ");
				
				if(rs.next()){
					vender_code = rs.getString(1);
					vender_name	= rs.getString(2);
				}
				
				
				int vender_nic_count = 0;
				String vender_nic_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									//" AND CLIENT_CODE = '"+vender_code+"' "+ 
									 //" AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+	
									" AND DOCUMENT_TYPE = 'VNIC' "+
							" ");
				
				if(rs.next()){
					vender_nic_count = rs.getInt(1);
				}
				
				if(vender_nic_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									//" AND CLIENT_CODE = '"+vender_code+"' "+ 
									//" AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+
									" AND DOCUMENT_TYPE = 'VNIC' "+
							" ");
					
					if(rs.next()){
						vender_nic_status = rs.getString(1);
					}
					
					
				}
				
				int vender_sig_count = 0;
				String vender_sig_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									//" AND CLIENT_CODE = '"+vender_code+"' "+ 
									//" AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+
									" AND DOCUMENT_TYPE = 'VSIG' "+
							" ");
				
				if(rs.next()){
					vender_sig_count = rs.getInt(1);
				}
				
				if(vender_sig_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									//" AND CLIENT_CODE = '"+vender_code+"' "+ 
									//" AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+
									" AND DOCUMENT_TYPE = 'VSIG' "+
							" ");
					
					if(rs.next()){
						vender_sig_status = rs.getString(1);
					}
					
					
				}
				
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='20%' ><b>5) VENDOR</b></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr height='25px'>");
				out.println("<td width='2%'></td>"); 
				out.println("<td width='1%' ><b>NAME </b></td>");
				//out.println("<td width='5%'></td>");
				out.println("<td width='4%' ><input class='txt_input' type='hidden' name='TXT_NAME_VENDOR_CODE' maxlength='200' size='10' value='"+vender_code+"' ><input class='txt_input' type='text' name='TXT_NAME_VENDOR' maxlength='200' size='10' value='"+vender_name+"' style=\"width:250px;\" ></td>"); 
				out.println("<td width='1%'></td>");
				out.println("<td width='5%' ><b>NIC COPY</td>");
				
				out.println("<td width='4%' >"); 
		        out.println("   <select class='txt_input' name='TXT_NIC_COPY_VENDOR'>"); 

				//if(vender_nic_status.equals("P")){ // commented by udara 11-05-2015
				if(vender_nic_status.equals("N")){ // added by udara 11-05-2015
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(vender_nic_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(vender_nic_status.equals("N")){ // commented by udara 11-05-2015
				else if(vender_nic_status.equals("A")){	// added by udara 11-05-2015
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
					
					
				out.println("   </select>");
				out.println("</td>");
				
				out.println("</tr>");
				
				// added by udara 23-03-2015
				
				String vendor_nic = "";
				
				rs = stmt.executeQuery(" "+
								" SELECT NVL(VAT_REG_NO,'-') "+
									" FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
									" WHERE VENDOR_CODE = '"+vender_code+"' "+ 
							" ");
			
				if(rs.next()){
					vendor_nic = rs.getString(1);
				}
				
				// end by udara 23-03-2015
				
				out.println("<tr  height='25px'>");
				out.println("<td width='2%'></td>"); 
				out.println("<td width='3%' ><b>NIC NO. </b></td>");
			//	out.println("<td width='6%'></td>");
				//out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_NIC_NO_1_VENDOR' maxlength='200' size='10' >"); // commented by udara 23-03-2015
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_NIC_NO_1_VENDOR' maxlength='200' size='10' value='"+vendor_nic+"' >");
				out.println("<td width='1%'></td>");
				out.println("<td width='5%' ><b>SIGNATURES OF VENDOR PAPERS</td>");
				
				out.println("<td  width='4%'>");
		
				out.println("<select class='txt_input' name='TXT_SIGNATOR_VENDOR'>"); 
				
				// commented by udara 23-03-2015
				/*
				if(vender_sig_status.equals("P")){
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(vender_sig_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				else if(vender_sig_status.equals("N")){					
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				*/
				
				// added by udara 23-03-2015
				out.println("      <option value='' ></option>"); // udara 27-04-2015
				out.println("      <option value='Y' > Yes </option>");
				out.println("      <option value='N' > No </option>");
				out.println("      <option value='P' > Pending </option>");	
				
				// end by udara 23-03-2015
				
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='3%' ><b>INTRODUCER </b></td>");
				//out.println("<td width='6%'></td>");
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_INTRODUCER_VENDOR' maxlength='200' size='10' value='"+m_lead_source_name+"' style=\"width:250px;\" >"); 
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<hr>");
				//--------------------------Documents--------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='20%' ><b>6) DOCUMENTS</b></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align='center' width='100%' class='table'  >"); // border=1
				out.println("<tr height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9%'> &nbsp; </td>");
				out.println("<td width='4%'><b>ORIGINAL</b></td>");
				
				//out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='4%'><b>COPIES</b></td>");
				out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("<td width='6%'> &nbsp; </td>");
				out.println("<td width='4%'> &nbsp; </td>");
				out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("<td width='6%'> &nbsp; </td>"); 
				out.println("<td width='4%'> &nbsp; </td>"); 
				
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("</tr>");
				//out.println("</table>");
				
				
				//out.println("<table align='center' width='100%' class='table' border=1 >");
				
				
				out.println("<tr height='25px'>");
				
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9%' ><b>CR BOOK</td>");
				
				int cr_book_count = 0;
				String cr_book_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  //" AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'CRBOOK' "+
							" ");
				
				if(rs.next()){
					cr_book_count = rs.getInt(1);
				}
				
				if(cr_book_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'CRBOOK' "+
							" ");
					
					if(rs.next()){
						cr_book_status = rs.getString(1);
					}
					
					
				}
				
				
				out.println("<td width='4%' ><select class='txt_input' name='TXT_CR_BOOK_DOCS'>"); 
				
				//if(cr_book_status.equals("P")){ // commented by udara 11-05-2015
				if(cr_book_status.equals("N")){ // added by udara 11-05-2015
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(cr_book_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(cr_book_status.equals("N")){ // commented by udara 11-05-2015
				else if(cr_book_status.equals("A")){ // added by udara 11-05-2015
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				out.println("</select>");
				out.println("</td>");
				
				
				
				//out.println("<td width='5%'></td>"); 
				//out.println("<td width='10%' ></td>");
				
				int cr_book_count_copy = 0;
				String cr_book_status_copy = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'CRC' "+
							" ");
				
				if(rs.next()){
					cr_book_count_copy = rs.getInt(1);
				}
				
				if(cr_book_count_copy>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'CRC' "+
							" ");
					
					if(rs.next()){
						cr_book_status_copy = rs.getString(1);
					}
					
					
				}
				
				
				out.println("<td width='4%'>");
				out.println("<select class='txt_input' name='TXT_COPY_1_DOCS'>"); 
				
				//if(cr_book_status_copy.equals("P")){ // commented by udara 11-05-2015
				if(cr_book_status_copy.equals("N")){ // added by udara 11-05-2015
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(cr_book_status_copy.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(cr_book_status_copy.equals("N")){	// commented by udara 11-05-2015	
				else if(cr_book_status_copy.equals("A")){ // commented by udara 11-05-2015
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				out.println("</select>");
				out.println("</td>");
				
				
				
				int mta6_count = 0;
				String mta6_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'MTA6' "+
							" ");
				
				if(rs.next()){
					mta6_count = rs.getInt(1);
				}
				
				if(mta6_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'MTA6' "+
							" ");
					
					if(rs.next()){
						mta6_status = rs.getString(1);
					}
					
					
				}
				
				
				
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='6%' ><b>MTA 6</td>");
				out.println("<td width='4%' >");
	
	            out.println("<select class='txt_input' name='TXT_MTA_6_DOCS'>"); 
				
				//if(mta6_status.equals("P")){ // commented by udara 11-05-2015
				if(mta6_status.equals("N")){
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(mta6_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(mta6_status.equals("N")){ // commented by udara 11-05-2015
				else if(mta6_status.equals("A")){	
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				
				out.println("</select>");
				
				out.println("</td>");
				
				
				
				int photos_count = 0;
				String photos_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ //  " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'PHOTO' "+
							" ");
				
				if(rs.next()){
					photos_count = rs.getInt(1);
				}
				
				if(photos_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'PHOTO' "+
							" ");
					
					if(rs.next()){
						photos_status = rs.getString(1);
					}
					
					
				}
				
				
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("<td width='6%' ><b>PHOTOS</td>");
				out.println("<td width='4%' >");
	            out.println("<select class='txt_input' name='TXT_PHOTOS_DOCS'>"); 
				
				//if(photos_status.equals("P")){
				if(photos_status.equals("N")){ // commented by udara 11-05-2015
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(photos_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				/*
				else if(photos_status.equals("N")){					
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				*/
				
				// added by udara 08-05-2015
				else if(photos_status.equals("A")){					
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				// end by udara 08-05-2015
				
				out.println("</select>");
				out.println("</td>");
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("</tr>");
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9%' ><b>DELETION</td>");
				
				int delete_count = 0;
				String delete_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'DEL' "+
							" ");
				
				if(rs.next()){
					delete_count = rs.getInt(1);
				}
				
				if(delete_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'DEL' "+
							" ");
					
					if(rs.next()){
						delete_status = rs.getString(1);
					}
					
					
				}
				
				out.println("<td width='4%' >");
				out.println("<select class='txt_input' name='TXT_DELETION_DOCS'>"); 
				
				//if(delete_status.equals("P")){ // commented by udara 11-05-2015
				if(delete_status.equals("N")){
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(delete_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(delete_status.equals("N")){ // commented by udara 11-05-2015
				else if(delete_status.equals("A")){
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				
				out.println("</select>");
				out.println("</td>");
				
				
				//out.println("<td width='5%'></td>"); 
				//out.println("<td width='10%' ></td>");
				
				
				int delete_copy_count = 0;
				String delete_copy_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+ // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'DELC' "+
							" ");
				
				if(rs.next()){
					delete_copy_count = rs.getInt(1);
				}
				
				if(delete_copy_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'DELC' "+
							" ");
					
					if(rs.next()){
						delete_copy_status = rs.getString(1);
					}
					
					
				}
				
				
				
				out.println("<td width='4%' >");
				
				out.println("<select class='txt_input' name='TXT_COPY_2_DOCS'>"); 
				
				//if(delete_copy_status.equals("P")){ // commented by udara 11-05-2015
				if(delete_copy_status.equals("N")){
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(delete_copy_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(delete_copy_status.equals("N")){	// commented by udara 11-05-2015
				else if(delete_copy_status.equals("A")){
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				
				out.println("</select>");
				
	            out.println("</td>"); 
				
				out.println("<td width='5%'> &nbsp; </td>"); 
				
				int mta8_count = 0;
				String mta8_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'MTA8' "+
							" ");
				
				if(rs.next()){
					mta8_count = rs.getInt(1);
				}
				
				if(mta8_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'MTA8' "+
							" ");
					
					if(rs.next()){
						mta8_status = rs.getString(1);
					}
					
					
				}
				
				
				out.println("<td width='6%' ><b>MTA 8</td>");
				out.println("<td width='4%' >");
	            out.println("<select class='txt_input' name='TXT_MTA_8_DOCS'>"); 
				
				//if(mta8_status.equals("P")){ // commented by udara 11-05-2015
				if(mta8_status.equals("N")){
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(mta8_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(mta8_status.equals("N")){	// commented by udara 11-05-2015	
				else if(mta8_status.equals("A")){
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				
				
				out.println("</select>");
				out.println("</td>");
				
				int vic_count = 0;
				String vic_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'VIC' "+
							" ");
				
				if(rs.next()){
					vic_count = rs.getInt(1);
				}
				
				if(vic_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'VIC' "+
							" ");
					
					if(rs.next()){
						vic_status = rs.getString(1);
					}
					
					
				}
				
				
				
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("<td width='6%' ><b>VIC</td>");
				out.println("<td width='4%' >");
                out.println("<select class='txt_input' name='TXT_VIC_DOCS'>"); 
				
				//if(vic_status.equals("P")){ // commented by udara 11-05-2015
				if(vic_status.equals("N")){
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(vic_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(vic_status.equals("N")){	// commented by udara 11-05-2015
				else if(vic_status.equals("A")){
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				
				out.println("</select>");
				out.println("</td>");
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("</tr>");
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9%' ><b>REV.LICENSE</td>");
				
				
				int rev_lic_count = 0;
				String rev_lic_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'REV.LICE.' "+
							" ");
				
				if(rs.next()){
					rev_lic_count = rs.getInt(1);
				}
				
				if(rev_lic_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'REV.LICE.' "+
							" ");
					
					if(rs.next()){
						rev_lic_status = rs.getString(1);
					}
					
					
				}
				
				
				out.println("<td width='4%' >");

                out.println("<select class='txt_input' name='TXT_LICENSE_DOCS'>"); 
				
				//if(rev_lic_status.equals("P")){ // commented by udara 11-05-2015
				if(rev_lic_status.equals("N")){
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(rev_lic_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(rev_lic_status.equals("N")){ // commented by udara 11-05-2015
				else if(rev_lic_status.equals("A")){
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				
				out.println("</select>");
				
				out.println("</td>");
				//out.println("<td width='5%'></td>"); 
				//out.println("<td width='10%' ></td>");
				out.println("<td width='4%' >");

				int rev_lic_copy_count = 0;
				String rev_lic_copy_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'RLC' "+
							" ");
				
				if(rs.next()){
					rev_lic_copy_count = rs.getInt(1);
				}
				
				if(rev_lic_copy_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'RLC' "+
							" ");
					
					if(rs.next()){
						rev_lic_copy_status = rs.getString(1);
					}
					
					
				}

                //out.println("<input class='txt_input' type='text' name='TXT_COPY_3_DOCS' maxlength='200' size='10' >");
				
				out.println("<select class='txt_input' name='TXT_COPY_3_DOCS'>"); 
				
				//if(rev_lic_copy_status.equals("P")){ // commented by udara 11-05-2015
				if(rev_lic_copy_status.equals("N")){ 
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(rev_lic_copy_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(rev_lic_copy_status.equals("N")){	// commented by udara 11-05-2015
				else if(rev_lic_copy_status.equals("A")){
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				
				out.println("</select>");
				
				
				out.println("</td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='6%' ><b>MTA 3</td>");
				
				int mta3_count = 0;
				String mta3_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'MTA3' "+
							" ");
				
				if(rs.next()){
					mta3_count = rs.getInt(1);
				}
				
				if(mta3_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'MTA3' "+
							" ");
					
					if(rs.next()){
						mta3_status = rs.getString(1);
					}
					
					
				}
				
				
				out.println("<td width='4%' >");

                out.println("<select class='txt_input' name='TXT_MTA_3_DOCS'>"); 

				//if(mta3_status.equals("P")){ // commented by udara 11-05-2015
				if(mta3_status.equals("N")){
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(mta3_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(mta3_status.equals("N")){	 // commented by udara 11-05-2015	
				else if(mta3_status.equals("A")){
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}


				out.println("</select>");


				out.println("</td>");
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("<td width='6%' ><b>DUP.KEY</td>");
				
				int dup_key_count = 0;
				String dup_key_status = "P";
				
				rs = stmt.executeQuery(" "+
								" SELECT COUNT(STATUS) "+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'DUPKEY' "+
							" ");
				
				if(rs.next()){
					dup_key_count = rs.getInt(1);
				}
				
				if(dup_key_count>0){
					
					rs = stmt.executeQuery(" "+
								" SELECT STATUS"+
									" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
									" WHERE  APPLICATION_NO = '"+m_application_no+"' "+
									" AND CLIENT_CODE = '"+hirer_cli_code+"' "+  // " AND CLIENT_CODE = '"+rs.getString(1)+"' "+
									" AND DOCUMENT_TYPE = 'DUPKEY' "+
							" ");
					
					if(rs.next()){
						dup_key_status = rs.getString(1);
					}
					
					
				}
				
				out.println("<td width='4%' >");
				out.println("<select class='txt_input' name='TXT_DUP_KEY_DOCS'>"); 
				
				//if(dup_key_status.equals("P")){ // commented by udara 11-05-2015
				if(dup_key_status.equals("N")){
					out.println("      <option value='Y' > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' selected > Pending </option>");				
				}
				else if(dup_key_status.equals("Y")){
					out.println("      <option value='Y'  selected > Yes </option>");
					out.println("      <option value='N' > No </option>");
					out.println("      <option value='P' > Pending </option>");						
				}
				//else if(dup_key_status.equals("N")){	// commented by udara 11-05-2015
				else if(dup_key_status.equals("A")){
					out.println("      <option value='Y'  > Yes </option>");
					out.println("      <option value='N' selected > No </option>");
					out.println("      <option value='P' > Pending </option>");	
				}
				
				out.println("</select>");
				out.println("</td>");
				out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<hr>");
				//--------------------------Initial and Insurence--------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='20%' ><b>7) INITIAL AND INSURANCE</b></td>");
				out.println("</tr>");
				out.println("</table>");
				
				double m_initial_charge_amnt = 0;
				double m_initial_charge_paid = 0;
				double m_initial_charge_bal  = 0;
				
				double m_stamp_duty_amnt = 0;
				double m_stamp_duty_paid = 0;
				double m_stamp_duty_bal  = 0;
				
				
				
				double m_sum_insured = 0;
				
				rs = stmt.executeQuery(" "+  
					//" select C.SUB_CHAGE_CODE, C.AMOUNT "+
					" select SUM(C.AMOUNT) "+
					" from "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS a, "+m_schema_name+".AF_MK_PRO_PRICING B, "+m_schema_name+".AF_MK_PRO_PRICING_CHARGES C "+
					" where a.FINANCE_NO = '"+m_finance_no+"' "+
					//" AND SUB_CHAGE_CODE = 'SERVICECHR' "+ // udara 24-04-2015
					//" AND SUB_CHAGE_CODE <> 'BROKERCOMM' "+ 
					" AND SUB_CHAGE_CODE NOT IN ('BROKERCOMM','INSURANCE') "+
					" AND A.INQUARY_NO = B.INQUIRY_NO "+
					" and B.PRICING_NO = C.PRICING_NO "+
				" ");
				
				if(rs.next()){
						m_initial_charge_amnt = rs.getDouble(1);
				}
				
				rs = stmt.executeQuery(" "+ 
					  " select  REF_NO, SUM(NVL(B.SETTELED_AMOUNT,0)) "+
	                  " FROM  "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A , "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT C "+
	                  " WHERE A.SUS_REF_NO = B.SUS_REF_NO "+ 
	                  " AND C.PAYMENT_NO = B.PAYMENT_NO "+
	                  " and a.SUSPENSE_ENTRY_TYPE = 'SERVICECHR' "+
	                  " AND A.REF_NO = '"+m_application_no+"' "+
	                  " GROUP BY REF_NO "+
					" ");
				
				if(rs.next()){
						m_initial_charge_paid = rs.getDouble(2);
				}
				
				m_initial_charge_bal = m_initial_charge_amnt - m_initial_charge_paid;
				
				// ===============================================================================================================
				
				rs = stmt.executeQuery(" "+  
					" select C.SUB_CHAGE_CODE, C.AMOUNT "+
					" from "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS a, "+m_schema_name+".AF_MK_PRO_PRICING B, "+m_schema_name+".AF_MK_PRO_PRICING_CHARGES C "+
					" where a.FINANCE_NO = '"+m_finance_no+"' "+
					" AND SUB_CHAGE_CODE = 'STDUTY' "+
					" AND A.INQUARY_NO = B.INQUIRY_NO "+
					" and B.PRICING_NO = C.PRICING_NO "+
				" ");
				
				if(rs.next()){
						m_stamp_duty_amnt = rs.getDouble(2);
				}
				
				rs = stmt.executeQuery(" "+ 
					  " select  REF_NO, SUM(NVL(B.SETTELED_AMOUNT,0)) "+
	                  " FROM  "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A , "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT C "+
	                  " WHERE A.SUS_REF_NO = B.SUS_REF_NO "+ 
	                  " AND C.PAYMENT_NO = B.PAYMENT_NO "+
	                  " and a.SUSPENSE_ENTRY_TYPE = 'STDUTY' "+
	                  " AND A.REF_NO = '"+m_application_no+"' "+
	                  " GROUP BY REF_NO "+
					" ");
				
				if(rs.next()){
						m_stamp_duty_paid = rs.getDouble(2);
				}
				
				m_stamp_duty_bal = m_stamp_duty_amnt - m_stamp_duty_paid;
				
				// ===============================================================================================================
				
				
				
				/*
				
				rs = stmt.executeQuery(" "+                                    
						 " SELECT "+  
							  " SUM(TOTAL_AMOUNT), "+
							  " SUM(SETTELE_AMOUNT), "+
						      " SUM(BALANCE_TO_BE_RECEIVED)  "+
						      " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
							      " WHERE     ACTIVE_STATUS='Y'  "+
							      " AND  A.FINANCE_NO =  '"+m_finance_no+"'  "+
							      " AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE')  "+									
							" ");
					
					if(rs.next()){
						//dup_key_status = rs.getString(1);
						m_initial_charge_amnt = rs.getDouble(1);
						m_initial_charge_paid = rs.getDouble(2);
						m_initial_charge_bal  = rs.getDouble(3);
					}
				
				
				
				rs = stmt.executeQuery(" "+                                    
						 " SELECT "+  
							  " SUM(TOTAL_AMOUNT), "+
							  " SUM(SETTELE_AMOUNT), "+
						      " SUM(BALANCE_TO_BE_RECEIVED)  "+
						      " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
							      " WHERE     ACTIVE_STATUS='Y'  "+
							      " AND  A.FINANCE_NO =  '"+m_finance_no+"'  "+
							      " AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE')  "+									
							" ");
					
					if(rs.next()){
						//dup_key_status = rs.getString(1);
						m_stamp_duty_amnt = rs.getDouble(1);
						m_stamp_duty_paid = rs.getDouble(2);
						m_stamp_duty_bal  = rs.getDouble(3);
					}
				
				
				*/
				
				double m_insur_charge_amnt = 0;
				double m_insur_charge_paid = 0;
				double m_insur_charge_bal  = 0;
				
				
				
				rs = stmt.executeQuery(" "+                                    
						 " SELECT "+  
							  " SUM(TOTAL_AMOUNT), "+
							  " SUM(SETTELE_AMOUNT), "+
						      " SUM(BALANCE_TO_BE_RECEIVED)  "+
						      " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
							      " WHERE     ACTIVE_STATUS='Y'  "+
							      " AND  A.FINANCE_NO =  '"+m_finance_no+"'  "+
							      " AND (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE')  "+									
							" ");
					
					if(rs.next()){
						m_insur_charge_amnt = rs.getDouble(1);
						//m_insur_charge_paid = rs.getDouble(2);
						//m_insur_charge_bal  = rs.getDouble(3);
					}
					
					
				
				rs = stmt.executeQuery(" "+  
					  " select  REF_NO, SUM(NVL(B.SETTELED_AMOUNT,0)) "+
					  " FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A , "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT C , AF_IS_PRO_ASET_INSUR_DETA D "+
					  " WHERE A.SUS_REF_NO = B.SUS_REF_NO  "+ 
					  " and C.PAYMENT_NO = B.PAYMENT_NO  "+
					  " and a.SUSPENSE_ENTRY_TYPE = 'INSURANCE'   "+  
					  " and D.REF_DEBIT_NOTE_NO = a.REF_NO "+
					  " and d.FINANCE_NO = '"+m_finance_no+"' "+
					  " group by a.REF_NO "+
					" ");
				
				if(rs.next()){
						m_insur_charge_paid = rs.getDouble(2);
				}
				
				m_insur_charge_bal = m_insur_charge_amnt - m_insur_charge_paid;
				
				
				out.println("<table align='center' width='100%' class='table'  >"); // border=1
				out.println("<tr height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='9%'> &nbsp; </td>");  
				out.println("<td width='4%' ><b>AMOUNT</td>");			
 
				out.println("<td width='4%' ><b>PAID</td>");
				out.println("<td width='5%'> <b>BALANCE </td>"); 
				 
				out.println("<td width='6%' > &nbsp; </td>");
				
				out.println("<td width='4%' ><b>REFINANCE CASE</td>");
				out.println("<td width='5%' ><select class='txt_input' name='TXT_REFINANCE_CASE_INSURENCE'>"); 
				
				if(m_re_fin_no.equals("-")){
					out.println("<option value='Y'  > Yes </option>");
					out.println("<option value='N' selected > No </option>");
				}
				else{
					out.println("<option value='Y' selected > Yes </option>");
					out.println("<option value='N'  > No </option>");
				}
				
				out.println("</select>");
				out.println("</td>"); 
				
				out.println("<td width='6%'> &nbsp; </td>");  
				out.println("<td width='4%'> &nbsp; </td>");  
				out.println("<td width='5%'> &nbsp; </td>");  
				
				out.println("</tr>");
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9%' ><b>INITIAL CHARGES</td>");
				out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_INITAL_CHARGES_INSURENCE' maxlength='200' size='10' value='"+nf.format(m_initial_charge_amnt)+"' onblur='validate_number_text_boxes(this);set_initial_charges_insurance();' ></td>");
				//out.println("</td>");
				
				//out.println("<td width='5%'> &nbsp; </td>");  
				//out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_PAID_1_INSURENCE' maxlength='200' size='10' value='"+nf.format(m_initial_charge_paid)+"' onblur='validate_number_text_boxes(this);set_initial_charges_insurance();' ></td>");
				out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_PAID_1_INSURENCE' maxlength='200' size='10'  onblur='validate_number_text_boxes(this);set_initial_charges_insurance();set_cash_to_be_paid();' ></td>");
				//out.println("</td>");
				
				
				//out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='5%'><input class='txt_input' type='text' name='TXT_BAL_1_INSURENCE' maxlength='200' size='10' value='"+nf.format(m_initial_charge_bal)+"' onblur='validate_number_text_boxes(this);set_initial_charges_insurance();' ></td>");
				
				out.println("<td width='6%'> &nbsp; </td>");  
				out.println("<td width='4%'> &nbsp; </td>");
			    out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("<td width='6%'> &nbsp; </td>");  
				out.println("<td width='4%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>");
				
				//out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9%' ><b>STAMP DUTY</td>");
				out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_STAMP_DUTY_INSURENCE' maxlength='200' size='10' value='"+nf.format(m_stamp_duty_amnt)+"' onblur='validate_number_text_boxes(this);set_stamp_duty_charges();set_cash_to_be_paid();' ></td>");
				
				//out.println("</td>");
				
				//out.println("<td width='5%'> &nbsp; </td>");  
				//out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_PAID_2_INSURENCE' maxlength='200' size='10' value='"+nf.format(m_stamp_duty_paid)+"' onblur='validate_number_text_boxes(this);set_stamp_duty_charges();' ></td>");
				out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_PAID_2_INSURENCE' maxlength='200' size='10'  onblur='validate_number_text_boxes(this);set_stamp_duty_charges();set_cash_to_be_paid();' ></td>");
				//out.println("</td>");
				
				
				//out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='5%'><input class='txt_input' type='text' name='TXT_BAL_2_INSURENCE' maxlength='200' size='10' value='"+nf.format(m_stamp_duty_bal)+"' onblur='validate_number_text_boxes(this);set_stamp_duty_charges();' ></td>");
				
				//out.println("</td>");
				
				out.println("<td width='6%'> &nbsp; </td>");  
				out.println("<td width='4%' ><b>BALANCE</td>");
				
				out.println("<td width='5%'> &nbsp; </td>");  
				
				out.println("<td width='6%'> &nbsp; </td>");
				out.println("<td width='4%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("</tr>");
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9%' ><b>INSURENCE CHARGES</td>");
				out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_INSURENCE_CHARGES_INSU' maxlength='200' size='10' value='"+nf.format(m_insur_charge_amnt)+"' onblur='validate_number_text_boxes(this); set_insurance_charges_balance();' ></td>"); // set_insurance_charges();
				
				//out.println("</td>");
				
				//out.println("<td width='5%'> &nbsp; </td>");  
				//out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_PAID_3_INSURENCE' maxlength='200' size='10' value='"+nf.format(m_insur_charge_paid)+"' onblur='validate_number_text_boxes(this);set_insurance_charges_balance();set_cash_to_be_paid();' ></td>"); // commented by udara 29-04-2015 // set_insurance_charges(); 
				out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_PAID_3_INSURENCE' maxlength='200' size='10'  onblur='validate_number_text_boxes(this);set_insurance_charges_balance();set_cash_to_be_paid();' ></td>"); // value='"+nf.format(m_insur_charge_paid)+"'
				//out.println("</td>");
				
				
				//out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='5%'><input class='txt_input' type='text' name='TXT_BAL_3_INSURENCE' maxlength='200' size='10'  onblur='validate_number_text_boxes(this);set_insurance_charges_balance();set_insurance_charges();set_cash_to_be_paid();' ></td>");// set_insurance_charges(); //(payment to be deducted) // value='"+nf.format(m_insur_charge_bal)+"'
				
				//out.println("</td>");
				
				out.println("<td width='6%'> &nbsp; </td>");  
				//out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_BAL_4_INSURENCE' maxlength='200' size='10' value='"+nf.format(m_insur_charge_bal)+"' onblur='validate_number_text_boxes(this);' ></td>");
				out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_BAL_4_INSURENCE' maxlength='200' size='10' value='"+nf.format(m_insur_charge_amnt-m_insur_charge_paid-m_insur_charge_bal)+"' onblur='validate_number_text_boxes(this);set_insurance_charges_balance();' ></td>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				
				out.println("<td width='6%'> &nbsp; </td>");
				out.println("<td width='4%'> &nbsp; </td>");
				out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("</tr>");
				
				
				double sum_insu_amt = 0;
					rs=stmt.executeQuery(" "+
					 " SELECT  SUM_INSSURED,PAYEE_NAME FROM (   "+
							 " SELECT NVL(E.SUM_INSSURED,0) SUM_INSSURED, NVL(E.INSUR_COM,'-') INSUR_COM , NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(E.INSUR_COM),'-') PAYEE_NAME "+
								  " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E "+
								  " WHERE E.FINANCE_NO = '"+m_finance_no+"' "+
									" ORDER BY E.ENT_DATE  DESC "+
								" ) "+
						"  WHERE ROWNUM = 1 "+
						" ");

						
						if(rs.next())
						{
							sum_insu_amt=rs.getDouble(1);
						}
						
						
				// ==========================================================================================================
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'>  &nbsp; </td>");  
				out.println("<td width='9%' > &nbsp; </td>"); // sum insu title
				out.println("<td width='4%'>  &nbsp; </td>");

				out.println("<td width='4%' > &nbsp; </td>"); 

				out.println("<td width='5%'> (payment to be deducted) </td>");  
				out.println("<td width='6%'> &nbsp; </td>");
				
				out.println("<td width='10%'> &nbsp; </td>");  
				out.println("<td width='5%' > &nbsp; </td> "); 

				out.println("<td width='6%'> &nbsp; </td>");
				
				out.println("<td width='4%'> &nbsp; </td>");
				out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("</tr>");
				
				// ==========================================================================================================
						
						
						
				
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9%' ><b>SUM INSURED</td>");
				out.println("<td width='4%'><input class='txt_input' type='text' name='TXT_SUM_INSURED_INSU' maxlength='200' size='10' value='"+nf.format(sum_insu_amt)+"' onblur='validate_number_text_boxes(this);' ></td>");
				
				//out.println("</td>");
				
				rs=stmt.executeQuery(" "+
						" SELECT PAYEE_CODE, "+ 
						" PAYEE_NAME "+
						" FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF "+
						" WHERE ACTIVE_STATUS = 'Y' "+
						" AND SUB_TYPE_CODE = 'INSURANCE' "+
						" ");
				
				//out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='4%' ><select class='txt_input' name='TXT_INSURENCE_COM_SELECTION'>"); //Select the Insurance Company
				//out.println("<option value='ALL'  > ALL </option>");
				
				out.println("<option value=''  ></option>"); // added by udara 24-03-2015
				
				while(rs.next()){					
					out.println("<option value='"+rs.getString(1)+"'  > "+rs.getString(2)+" </option>");
				}
				
				out.println("<option value='OTHER'  > Other </option>"); // added by udara 24-03-2015
				
				out.println("</select>");
				out.println("</td>");
				
				
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='6%'> &nbsp; </td>");
				
				//out.println("</td>");
				
				out.println("<td width='10%'><b>INSURANCE UPDATE (ASSIGNMENT LETTER RECEIVED)</td>");  
			//	out.println("<td width='5%'><input class='txt_input' type='text' name='TXT_INITAL_CHARGES' maxlength='200' size='10' ></td>");
				out.println("<td width='5%' ><select class='txt_input' name='TXT_INSURANCE_UPDATE_INSU'>"); 
				out.println("<option value=''  ></option>");
				out.println("<option value='Y'  > Yes </option>");
				out.println("<option value='N'  > No </option>");
				out.println("</select></td>");
				
				out.println("<td width='6%'> &nbsp; </td>");
				
				out.println("<td width='4%'> &nbsp; </td>");
				out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("</tr>");
				
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9%' ><b>COVER NOTE ISSUED</td>");
				out.println("<td width='4%' ><select class='txt_input' name='TXT_COVER_NOTE_INSU'>"); 
				out.println("<option value=''  ></option>");
				out.println("<option value='Y'  > Yes </option>");
				out.println("<option value='N'  > No </option>");
				out.println("<option value='P' > Pending </option>");
				out.println("</select>");
				out.println("</td>");
				
				out.println("<td width='4%'> &nbsp; </td>");  
				out.println("<td width='5%'> &nbsp; </td>");  
				
				out.println("<td width='6%'> &nbsp; </td>");  
				//out.println("<td width='4%'> &nbsp; </td>");
				
				double net_rental_amt=0;	
				
				rs=stmt.executeQuery(" "+
					" SELECT "+
							  " APPLICATION_NO, "+
							  " NET_RENTAL_AMOUNT "+
							  " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
							  " WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
							  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
							  " WHERE FINANCE_NO='"+m_finance_no+"' AND "+
							  " APPLICATION_STATUS <>'CANCEL' "+
							  "  ) "+
							  " GROUP BY   APPLICATION_NO,NET_RENTAL_AMOUNT ");                    

							
				if(rs.next()){
					net_rental_amt=rs.getDouble(2);
				}
				
				
				out.println("<td width='4%'><b>RENTAL</td>");  
				out.println("<td width='5%'><input class='txt_input' type='text' name='TXT_RENTAL_INSURENCE' maxlength='200' size='10' value='"+nf.format(net_rental_amt)+"' onblur='validate_number_text_boxes(this);' ></td>");
				
				out.println("<td width='6%'> &nbsp; </td>");
				out.println("<td width='4%'> &nbsp; </td>");
				
				out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("</tr>");
				//out.println("</table>");
				
				//out.println("<table align='center' width='100%' class='table' border=1 >");
				out.println("<tr  height='25px'>");
				
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("<td width='9%' ><b>RENTAL LETTER ISSUED</b></td>"); 
				//out.println("<td width='2%'></td>");
				out.println("<td width='4%' ><select class='txt_input' name='TXT_RENTAL_LETTER_ISSUED_INSU'>"); 
				out.println("<option value=''  ></option>");
				out.println("<option value='Y'  > Yes </option>");
				out.println("<option value='N' > No </option>");
				out.println("<option value='P' > Pending </option>");
				out.println("</select>");
				
				
				double m_capital_amount = 0;
				
				rs=stmt.executeQuery(" "+
						" SELECT NVL("+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT('"+m_application_no+"'),0) "+
						" FROM DUAL "+
						" ");    
				
				if(rs.next()){
					m_capital_amount=rs.getDouble(1);
				}
				
				int slab_no = 0;
				
				rs=stmt.executeQuery(" "+
						" SELECT   NVL ( MAX ( A.PERIOD ), 0 ) PERIOD "+
		                " FROM   "+m_schema_name+".AF_CO_PRO_APP_PRICING A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
		                " WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
		                " AND A.PRICING_NO = B.PRICING_NO "+
		                " AND A.PRO_INVOICE_NO = B.INVOICE_NO "+
		                " and a.APPLICATION_NO = '"+m_application_no+"' "+
		                " AND B.ACTIVE_STATUS IN ('Y','T') "+
						" ");   
				
				if(rs.next()){
					slab_no=rs.getInt(1);
				}
				
				out.println("<td width='4%'> <b>CAPITAL</b> </td>");
				out.println("<td width='5%' > <input class='txt_input' type='text' name='TXT_CAPITAL_INSURANCE' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_capital_values();set_cash_to_be_paid();' value='"+nf.format(m_capital_amount)+"' > </td>"); 
				//out.println("<td width='2%'></td>");
				out.println("<td width='6%' > &nbsp; </td>"); 
				out.println("<td width='4%'> <DIV id='DIV_TXT_SLAB_INSURANCE' class=div_input><b>PERIOD(MONTHS)</b></DIV> </td>");
				out.println("<td width='5%' > <input class='txt_input' type='text' name='TXT_SLAB_INSURANCE' maxlength='200' size='10' onblur='validate_number_text_boxes(this);' value='"+slab_no+"' > </td>"); 
				out.println("<td width='6%' > &nbsp; </td>"); 
				out.println("<td width='4%'> &nbsp; </td>");
				
				out.println("<td width='5%'> &nbsp; </td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr>");
				//--------------------------Approvals--------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='20%' ><b>8) APPROVAL</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<table align='center' width='100%' class='table'  >"); // border=1
				out.println("<tr  height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='10%' ><b>CUSTOMER INSPECTION BY</b></td>"); 
				//out.println("<td width='2%'></td>");
				
				//out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_INSPECTION_APPROVALS' maxlength='200' size='10' ></td>"); // commented by udara 30-01-2015
				rs=stmt.executeQuery(" "+
								" SELECT USER_ID, "+
								" NAME "+
									" FROM "+m_schema_name+".CO_CO_MAS_USER "+
									" WHERE ACTIVE_STATUS = 'Y' "+
								" ");    
				
				
				out.println("<td width='4%' >");
				out.println("<select class='txt_input' name='TXT_INSPECTION_APPROVALS' style=\"width:200px;\" >"); 
				out.println("   <option value=''  ></option>");
				while(rs.next()){
					out.println("   <option value='"+rs.getString(1)+"'  > "+rs.getString(2)+" </option>");
				}
				
				out.println("</select>");
				out.println("</td>");
				
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_CAPITAL_APPROVALS' class=div_input><b>CAPITAL</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_CAPITAL_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_capital_values();set_cash_to_be_paid();' value='"+nf.format(m_capital_amount)+"' >"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("</tr>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='9.9%' ><b>CR BOOK NO.</b></td>"); 
				//out.println("<td width='2%'></td>");
				
				
				String cr_book_no = "-";
				
				rs=stmt.executeQuery(" "+
								" SELECT NVL(EXTRAS_INCLUDED,'-') "+
									" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
									" WHERE APPLICATION_NO = '"+m_application_no+"' "+
								" ");
			
				if(rs.next()){
					cr_book_no = rs.getString(1); 
				}
				
				
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_CR_BOOK_NO_APPROVALS' maxlength='200' size='10' value='"+cr_book_no+"' ></td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='10%' ><DIV id='DIV_INT_CHARGES_APPROVALS' class=div_input><b>LESS INITIAL CHARGES</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_INT_CHARGES_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_initial_charges_insurance();set_cash_to_be_paid();' >"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("</tr>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				
				double closing_settlement = 0;
				double closing_settlement_value = 0;
				
				rs=stmt.executeQuery(" "+
								" SELECT "+m_schema_name+".AF_CO_GET_CLOSE_REC_AMNT('"+m_re_fin_no+"') "+ // m_re_fin_no // m_finance_no
									" FROM DUAL "+
								" ");
			
				if(rs.next()){
					closing_settlement_value = rs.getDouble(1); 
				}
				
				if(m_re_fin_no.equals("-")){
					closing_settlement = 0;
				}
				else{
					closing_settlement = closing_settlement_value;
				}
				
				out.println("<td width='9.9%' ><b>CLOSING SETTLEMENT</b></td>"); 
				//out.println("<td width='2%'></td>");
				
				//out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_CLOSING_STAT_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();' value='"+nf.format(closing_settlement)+"' ></td>"); // commented by udara 27-04-2015
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_CLOSING_STAT_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();set_cash_to_be_paid();' value='"+nf.format(closing_settlement)+"' ></td>"); // commented by udara 27-04-2015
				/*
				if(m_re_fin_no.equals("-")){
					out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_CLOSING_STAT_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();set_cash_to_be_paid();' value='"+nf.format(closing_settlement)+"' ></td>");
				}
				else{
					out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_CLOSING_STAT_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();set_cash_to_be_paid();' value='"+nf.format(closing_settlement)+"' disabled ></td>");
				}
				*/
				
				
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_LESS_FIRST_RENT_APPROVAL' class=div_input><b>LESS FIRST RENTAL</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_LESS_FIRST_RENT_APPROVAL' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();set_cash_to_be_paid();' >"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("</tr>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='9.9%' ><b>FIRST RENTAL</b></td>"); 
				//out.println("<td width='2%'></td>");
				//out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_F_RENTAL_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();set_cash_to_be_paid();' ></td>"); // commented by udara 27-04-2015
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_F_RENTAL_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();set_cash_to_be_paid();' value='"+nf.format(0)+"' ></td>"); // added by udara 27-04-2015
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				//out.println("<td width='10%' ><DIV id='DIV_TXT_CLS_STAT_APPROVAL' class=div_input><b>LESS CLOSING STATEMENT</b></DIV></td>"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_CLS_STAT_APPROVAL' class=div_input><b>LESS CLOSING SETTLEMENT</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_CLS_STAT_APPROVAL' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();set_cash_to_be_paid();' >"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("</tr>");
				
				out.println("<tr  height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='9.9%' ><b>OTHER (    )</b></td>"); 
				//out.println("<td width='2%'></td>");
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_OTHER_APPROVAL' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();set_cash_to_be_paid();' value='"+nf.format(0)+"' ></td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_OTHER_CHARGES_APPROVAL' class=div_input><b>OTHER CHARGES</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_OTHER_CHARGES_APPROVAL' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_approval_values();set_cash_to_be_paid();' >"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("</tr>");
				
				
				out.println("<tr  height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='9.9%' > &nbsp; </td>"); 
				
				out.println("<td width='4%' > &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_STAMP_DUTY_APPROVAL' class=div_input><b>LESS STAMP DUTY</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_STAMP_DUTY_APPROVAL' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_stamp_duty_charges();set_cash_to_be_paid();' >"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("</tr>");
				//out.println("</table>");
				
				//out.println("<table align='center' width='100%' class='table' border=1 >");
				out.println("<tr  height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='9.9%' ><b>CR BOOK</b></td>"); 
				//out.println("<td width='2%'></td>");
				
				out.println("<td width='4%' >");
				//out.println("<input class='txt_input' type='text' name='TXT_CR_APPROVALS' maxlength='200' size='10' ></td>"); 
				out.println("   <select class='txt_input' name='TXT_CR_APPROVALS'>");
				out.println("     <option value='' ></option>");
				out.println("     <option value='ORIGINAL' > Original </option>");
				out.println("     <option value='DUPLICATE' > Duplicate </option>");
				out.println("   </select>");
				out.println(" </td>"); 

				
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='9.9%' ><b>No. OF CR ENTRIES</b></td>"); 
				//out.println("<td width='2%'></td>");
				//out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_ENTRY_APPROVAL' maxlength='200' size='10' onblur='validate_number_text_boxes(this);' value='"+nf.format(m_valuation_notes)+"' disabled ></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_ENTRY_APPROVAL' maxlength='200' size='10' onblur='validate_number_text_boxes(this);' value='"+m_valuation_notes+"' disabled ></td>"); // udara 24-04-2015
				out.println("<td width='10%'> &nbsp; </td>");
				out.println("<td width='10%' ><DIV id='DIV_TXT_INSU_CHARGES_APPROVALS' class=div_input><b>LESS INSURANCE CHARGES</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_INSU_CHARGES_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_insurance_charges();set_cash_to_be_paid();' ></td>"); 
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("</tr>");
				
				
				out.println("<tr  height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='9.9%' > &nbsp; </td>"); 
				//out.println("<td width='2%'></td>");
				out.println("<td width='4%' > &nbsp; </td>"); 
				out.println("<td width='10%'> &nbsp; </td>");
				out.println("<td width='9.9%' > &nbsp; </td>"); 
				//out.println("<td width='2%'></td>");
				out.println("<td width='4%' > &nbsp; </td>"); 
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_TO_BE_PAID_APPROVALS' class=div_input><b>CASH TO BE PAID</b></DIV></td></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_TO_BE_PAID_APPROVALS' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_cash_to_be_paid();' >"); 
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("</tr>");
				
				
				out.println("<tr  height='25px'>");
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='9.9%' ><b>RMV PAPERS & ALL DOCUMENTS IN ORDER </b></td>"); 
				//out.println("<td width='2%'></td>");
				
				out.println("<td width='4%' >");
			    //out.println("<input class='txt_input' type='text' name='TXT_RMV_PAPER_APPROVAL' maxlength='200' size='10' >");
				out.println("<select class='txt_input' name='TXT_RMV_PAPER_APPROVAL'>"); 
				out.println("<option value='' ></option>");
				out.println("<option value='Y' > Yes </option>");
				out.println("<option value='N' > No </option>");
				out.println("<option value='P' > Pending </option>");
				out.println("</select>");
		        out.println("</td>"); 
				
				out.println("<td width='10%'> &nbsp; </td>");
				out.println("<td width='9.9%' ><b> &nbsp; </b></td>"); // CHECKED BY
				
				//out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_CHECKED_BY_APPROVALS' maxlength='200' size='10' ></td>"); 
				
				// ========================================================================================================
				
				rs=stmt.executeQuery(" "+
								" SELECT USER_ID, "+
								" NAME "+
									" FROM "+m_schema_name+".CO_CO_MAS_USER "+
									" WHERE ACTIVE_STATUS = 'Y' "+
								" ");    
				
				
				out.println("<td width='4%' >");
				out.println("<select class='txt_input' name='TXT_CHECKED_BY_APPROVALS' style=\"width:200px;\" style=\"display: none;\" >"); 
				
				while(rs.next()){
					out.println("   <option value='"+rs.getString(1)+"'  > "+rs.getString(2)+" </option>");
				}
				
				out.println("</select>");
				out.println("</td>");
				
				// ========================================================================================================
				
				out.println("<td width='5%'> &nbsp; </td>"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_TEL_APPROVAL' class=div_input><b>TELEPHONE APPROVAL - DIRECTOR NAME</b></DIV></td>"); 
				//out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_TEL_APPROVAL' maxlength='200' size='10' >"); 
				
				// ========================================================================================================
				
				/*
				rs=stmt.executeQuery(" "+
								" SELECT USER_ID, "+
								" NAME "+
									" FROM "+m_schema_name+".CO_CO_MAS_USER "+
									" WHERE ACTIVE_STATUS = 'Y' "+
								" ");    
				*/
				
				out.println("<td width='4%' >");
				out.println("<select class='txt_input' name='TXT_TEL_APPROVAL' style=\"width:200px;\" >"); 
				
				//while(rs.next()){
				//	out.println("   <option value='"+rs.getString(1)+"'  > "+rs.getString(2)+" </option>");
				//}
				
				out.println("   <option value=''  ></option>");
				out.println("   <option value='LM'  > Lasitha Marasinghe </option>");
				out.println("   <option value='RE'  > Regiee Ekanayake </option>");
				out.println("   <option value='UW'  > Uditha Wimalasooriya </option>");
				out.println("   <option value='DK'  > Dinesh Kumaranayake </option>");
				out.println("   <option value='DS'  > Dilka Sanjeewanie </option>");
				
				out.println("</select>");
				out.println("</td>");
				
				// ========================================================================================================
				
				
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr>");
				//--------------------------Case Canvassed by--------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='20%' ><b>9) CASE CANVASSED BY</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				out.println("<td width='2%'> &nbsp; </td>"); 
				out.println("<td width='2%' ><b>NAME</b></td>"); 
				//out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_CASE_CANVASSED_BY' maxlength='200' size='10' style=\"width:200px;\" >"); 
				
				
				// ========================================================================================================
				
				/*
				rs=stmt.executeQuery(" "+
								" SELECT USER_ID, "+
								" NAME "+
									" FROM "+m_schema_name+".CO_CO_MAS_USER "+
									" WHERE ACTIVE_STATUS = 'Y' "+
								" ");    
				
				
				out.println("<td width='4%' >");
				out.println("<select class='txt_input' name='TXT_CASE_CANVASSED_BY' style=\"width:200px;\" >"); 
				
				while(rs.next()){
					out.println("   <option value='"+rs.getString(1)+"'  > "+rs.getString(2)+" </option>");
				}
				
				out.println("</select>");
				
				
				out.println("</td>");
				*/
				
				out.println("<td width='4%' >");
				out.println("<input class='txt_input' type='hidden' name='TXT_CASE_CANVASSED_BY' maxlength='200' size='20' style=\"width:250px;\" value='"+m_cr_officer+"' >");
				out.println("<input class='txt_input' type='text'   name='TXT_CASE_CANVASSED_BY_NAME'      maxlength='200' size='20' style=\"width:250px;\" value='"+m_cr_officer_name+"' >");
				out.println("</td>"); 
				
				
				// ========================================================================================================
				
				
				out.println("<td width='1%'></td>");
				out.println("<td width='2%' ><DIV id='DIV_TXT_NAME_CASE' class=div_input style=\"display: none;\" ><b>VEHICLE NO</b></DIV></td>"); 
				out.println("<td width='4%' ><input class='txt_input' type='text' name='TXT_NAME_CASE' maxlength='200' size='10' style=\"display: none;\" value=\"-\" >"); 
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<hr>");
				//--------------------------Credit Commitee Approval--------------------------------------
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr  height='25px'>");
				//out.println("<td width='20%' ><b>10) CREDIT COMMITEE APPROVAL</b></td>");
				out.println("<td width='20%' ><b>10) CREDIT COMMITTEE APPROVAL</b></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align='center' width='100%' class='table' >");
				
				out.println("<tr height='25px'>");
				
				out.println("<td width='10%'></td>"); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='15%' ><DIV id='DIV_APPROVED_CAPITALS_CREDIT_COM' class=div_input><b>APPROVED CAPITAL(Rs)</DIV></td>");
				//out.println("<td width='9%'></td>");
				out.println("<td ><input class='txt_input' type='number' name='TXT_APPROVED_CAPITALS_CREDIT_CO' maxlength='200' size='10' onblur='validate_number_text_boxes(this);set_capital_values();' value='"+nf.format(m_capital_amount)+"' ></td>"); 
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table' >");
				
				out.println("<tr height='25px' style=\"display: none;\" >");
				out.println("<td width='5%'> &nbsp; </td>");  
				out.println("<td width='4%' ><input class='txt_input' type='number' name='TXT_DIRECTOR_NAME_CREDIT_CO' maxlength='200' size='10' value='-' ></td>"); 
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("<td width='4%' ><input class='txt_input' type='number' name='TXT_SIGANTURE_CREDIT_CO' maxlength='200' size='10' value='-' ></td>"); 
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("</tr>");
				
				// added udara 13-03-2015
				
				out.println("<tr height='25px' >");
				out.println("<td width='5%'></td>");  
				out.println("<td width='6%' align='center' > ............................... </td>"); 
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("<td width='6%' align='center' > ............................... </td>"); 
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("</tr>");
				
				// end by udara 13-03-2015
				
				out.println("<tr height='25px'>");
				out.println("<td width='5%'></td>");  
				out.println("<td width='6%' align='center' ><b>DIRECTOR NAME</td>"); 
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("<td width='6%' align='center' ><b>SIGNATURE</td>"); 
				out.println("<td width='5%'> &nbsp; </td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='right'><input class='mainbut' type='button' name='Save' onClick=\"validate_before_save('"+m_finance_no+"')\" VALUE = 'Save Report' ></TD></TR>"); // added by udara 10-10-2014
				out.println("</TABLE>");
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); // udara 24-04-2015
				
				out.println("</BODY></HTML>");
				
				
			}
			
			
			else {
				out.println("Undefined");
			}
			
			out.close();
			conn.close();
			this.destroy();
			
			
		}
		catch (Exception e) {
			try {
				conn.close();
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
	}
}
