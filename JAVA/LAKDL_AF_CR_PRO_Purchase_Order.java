//--
//SCREEN NAME:PURCHASE ORDER PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

// TXT_ENGINE_NO DISABLED ADDED BY NS ON 27-10-2011
// TXT_CHASSISS_NO DISABLED ADDED BY NS ON 27-10-2011

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_CR_PRO_Purchase_Order extends javax.servlet.http.HttpServlet { 
	
	/*
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	ServletOutputStream out =  null;
	Statement stmt,stmt2,stmt_app,stmt_invoice,stmt1;
	public ResultSet rs,rs2,rs_app,rs_invoice,rs1;
	*/
	
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
	public  void service(HttpServletRequest req, HttpServletResponse res) { // synchronized
		
		Connection conn=null;
		java.text.NumberFormat nf=null;
		java.lang.Math a=null;
		ServletOutputStream out =  null;
		Statement stmt=null,stmt2=null,stmt_app=null,stmt_invoice=null,stmt1=null;
		ResultSet rs=null,rs2=null,rs_app=null,rs_invoice=null,rs1=null;
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			String m_username=m_sn_methods.username;
			
			
			//Declaring String Variables--------------------------------
			String m_application_no=null;
			String m_pur_ord_no=null;
			String m_string="";
			String m_Payement_Mode="";
			String m_scr_position="";
			String m_doc_pos="";
			String screen_name="AF_CR_PRO_PURCHASE_ORDER";
			String m_CLOSE="";
			String m_Day_dd="";
			String m_Day_mm="";
			String m_Day_yy="";
			String m_Next_Day_dd="";
			String m_Next_Day_mm="";
			String m_Next_Day_yy="";
			String m_client_code="";				
			String m_curr_code="";
			String m_date="";
			String m_finance_no="";//added by nuwan de silva on 30-10-07
			
			//Declaring double Variables--------------------------------
			double m_other_invoice=0;
			double m_invoice=0;
			double m_invoice_termina=0;
			double m_odi_interest=0;
			double m_total=0;
			double rep_amount=0;
			double m_Other_Charges=0;
			double m_NIBSM=0;
			double m_AMI_VALUES=0;
			double m_total_charge=0;
			double m_total_charge_rep=0;
			double m_Invoice_Amount=0;
			double m_doc_charges=0;
			double m_amo_charges=0;
			double m_general_amount=0;
			double m_receipt_charges=0;
			double receipt_total=0;
			double receipt_total_rep=0;
			double m_nibsm_charges=0;
			double tot=0;
			double Total_amount_charges=0;
			double Total_amount_charges_rep=0;
			double amount_to_be_charged=0;
			double m_exchange_rate=0;
			double tot_rep=0;
			double m_chargeble_amount=0;
			// added by nuwan de silva on 30-10-07-------------------
			double _m_total_other_inv=0;
			double _m_total_rent_inv=0;
			double _m_total_ter_inv=0;
			double _m_total_odi_interest=0;
			//---------------------------------------------------------	
			//Declaring int Variables---------------------------------
			
			int m_AMI=0;
			int m_PERIOD=0;
			int b_flag_date=0;
			int m_Count=0;
			int b_flag_date_act=0;
			//--------------------------------------------------------
			//declaring boolean---------------------------------------
			boolean more=false;
			//---------------------------------------------------------
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			//	screen_name="AF_CR_PRO_PURCHASE_ORDER";
			String m_application_no1="";
			if(req.getParameter("application_no")!=null){
				m_application_no1=req.getParameter("application_no");//changed by delanjali
			}
			if(req.getParameter("pur_ord_no")!=null){
				m_pur_ord_no=req.getParameter("pur_ord_no");
			}
			m_CLOSE = req.getParameter("CLOSE");
			if(req.getParameter("CLOSE")==null){
				m_CLOSE="N";
			}
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			stmt = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt_app= conn.createStatement ();
			stmt_invoice= conn.createStatement ();
			stmt1 = conn.createStatement ();
			rs= stmt.executeQuery ( " SELECT "+
				" CLIENT_CODE  "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
				" WHERE APPLICATION_NO='"+m_application_no1+"' ");
			more=rs.next();
			if(more){
				m_client_code=rs.getString(1);}
			rs.close();
			
			
			String		Sql_other_inv=" SELECT "+ 
				"	  FINANCE_NO,SUM(BALANCE_TO_BE_RECEIVED) OTHER_INV ,MAX(B.EXCHANGE_RATE) "+
				"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
				"		WHERE CLIENT_CODE='"+m_client_code+"' AND "+
				"   A.CURRENCY_CODE=B.CURR_CODE AND "+
				"   TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
				"   INVOICE_TYPE NOT IN ('INV_GENER','TERMINA')   AND "+
				"   ACTIVE_STATUS='Y' AND "+
				"   BALANCE_TO_BE_RECEIVED>0 "+
				"   GROUP BY FINANCE_NO "; 
			
			rs= stmt.executeQuery (Sql_other_inv);
			more=rs.next();
			while(more){
				_m_total_other_inv+=rs.getDouble(2)*rs.getDouble(3);
				more=rs.next();
			}
			rs.close();
			
			String		Sql_rent_inv=" SELECT "+ 
				"	  FINANCE_NO,SUM(BALANCE_TO_BE_RECEIVED) INV,MAX(B.EXCHANGE_RATE) "+
				"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE A ,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
				"		WHERE CLIENT_CODE='"+m_client_code+"' AND "+
				"   A.CURRENCY_CODE=B.CURR_CODE AND "+
				"   TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
				"   INVOICE_TYPE='INV_GENER'   AND "+
				"   BALANCE_TO_BE_RECEIVED>0 "+
				"   GROUP BY FINANCE_NO ";
			rs= stmt.executeQuery (Sql_rent_inv);
			more=rs.next();
			while(more){
				_m_total_rent_inv+=rs.getDouble(2)*rs.getDouble(3);
				more=rs.next();
			}
			rs.close();			
			
			
			String		Sql_termination_inv=" SELECT "+ 
				" FINANCE_NO, "+
				" SUM(BALANCE_AMOUNT), "+
				" MAX(B.EXCHANGE_RATE) "+
				" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A ,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
				"	WHERE CLIENT_CODE='"+m_client_code+"' AND "+
				" A.CURR_CODE=B.CURR_CODE AND "+
				" TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
				" BALANCE_AMOUNT >0 "+
				" GROUP BY FINANCE_NO ";
			rs= stmt.executeQuery (Sql_termination_inv);
			more=rs.next();
			while(more){
				_m_total_ter_inv+=rs.getDouble(2)*rs.getDouble(3);
				more=rs.next();
			}
			rs.close();				
			
			
			String		Sql_odi_interest="SELECT B.FINANCE_NO FINANCE_NO,SUM(A.ODI),A.EXCHANGE_RATE EXCHANGE_RATE "+
				"               FROM    "+
				"           (SELECT INVOICE_NO,SUM(ODI_BAL_AMOUNT) ODI,A.CURR_CODE CURR_CODE ,MAX(B.EXCHANGE_RATE) EXCHANGE_RATE,TO_CHAR(A.DUE_DATE,'DD-MM-YYYY') DUE_DATE   "+ 
				" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B   "+
				" WHERE A.CURR_CODE=B.CURR_CODE AND "+
				" TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
				" INVOICE_NO IN "+
				" (SELECT  "+
				" INVOICE_NO  "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+ 
				"	WHERE CLIENT_CODE='"+m_client_code+"' AND "+
				" ACTIVE_STATUS='Y'   "+
				" )  "+
				"   GROUP BY INVOICE_NO,A.CURR_CODE,A.DUE_DATE "+
				"               )A, "+
				"                "+
				"               (SELECT "+
				" FINANCE_NO,INVOICE_NO  "+
				"							 FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
				"		WHERE CLIENT_CODE='"+m_client_code+"' AND "+
				"							 ACTIVE_STATUS='Y'   "+
				"							 )  B    "+
				"               WHERE A.INVOICE_NO=B.INVOICE_NO "+
				" GROUP BY FINANCE_NO,EXCHANGE_RATE ";
			
			rs= stmt.executeQuery (Sql_odi_interest);
			more=rs.next();
			while(more){
				_m_total_odi_interest=rs.getDouble(2)*rs.getDouble(3);
				more=rs.next();
			}
			rs.close();				
			
			
			
			
			rs_app= stmt_app.executeQuery ( "	SELECT "+
				" APPLICATION_NO,CURRENCY_CODE ,MAX(B.EXCHANGE_RATE) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
				" WHERE CLIENT_CODE='"+m_client_code+"' AND  "+
				" A.CURRENCY_CODE=B.CURR_CODE AND "+
				" TO_DATE(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
				" APPLICATION_STATUS='VERIFYL' 	 "+
				" GROUP BY APPLICATION_NO,CURRENCY_CODE "+
				" ORDER BY CURRENCY_CODE ");
			
			boolean more_app=rs_app.next();
			
			while(more_app){
				m_application_no=rs_app.getString(1);
				m_exchange_rate=rs_app.getDouble(3);
				m_curr_code=rs_app.getString(2);
				
				
				rs_invoice= stmt_invoice.executeQuery (	"  SELECT                       																		 "+
					"  INVOICE_NO,PRICING_NO 																						 "+
					"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS              "+
					"  WHERE APPLICATION_NO='"+m_application_no+"' AND            "+
					"  PURCHASE_ORDER_NO IS NULL ");
				
				
				
				
				boolean 	more_invoice=rs_invoice.next();	
				String m_invoice_no="";
				String m_pricing_no="";
				//Assiging Variables To Zero Values At The Begining
				tot=0;
				m_NIBSM=0;
				m_AMI_VALUES=0;
				m_amo_charges=0;
				m_general_amount=0;
				m_nibsm_charges=0;
				tot_rep=0;
				
				while(more_invoice){
					m_invoice_no=rs_invoice.getString(1);
					m_pricing_no=rs_invoice.getString(2);
					
					
					//added by nuwan de silva 27-08-07--------
					rs= stmt.executeQuery ("SELECT "+
						" NVL("+m_schema_name+".AF_CO_GET_AMI_AMOUNT('"+m_application_no+"','"+m_pricing_no+"','"+m_invoice_no+"'),0) FROM DUAL ");
					
					more=rs.next();
					if(more){
						m_AMI_VALUES=rs.getDouble(1);
					}
					
					rs.close();
					rs= stmt.executeQuery ( "		SELECT "+
						"   NVL(SUM(AMOUNT),0) "+
						"   FROM  "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES "+
						"   WHERE APPLICATION_NO='"+m_application_no+"'  AND PRO_INVOICE_NO='"+m_invoice_no+"' AND "+
						"   CHARGE_TYPE='INV' ");
					
					more=rs.next();
					if(more){
						m_amo_charges=rs.getDouble(1); 
					}
					
					rs.close();
					rs= stmt.executeQuery ( "  SELECT "+
						"  SUM(GRENTAL_AMOUNT) "+
						"  FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
						"  WHERE APPLICATION_NO='"+m_application_no+"'  AND PRO_INVOICE_NO='"+m_invoice_no+"' "+
						"  AND INSTALLMENT_NO=0 ");
					more=rs.next();
					if(more){
						m_general_amount=rs.getDouble(1);
					}
					
					rs.close();
					rs= stmt.executeQuery (" SELECT "+
						"  SUM(NIBSM) "+
						"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
						"  WHERE APPLICATION_NO='"+m_application_no+"'  AND PRO_INVOICE_NO='"+m_invoice_no+"' ");
					more=rs.next();
					if(more){
						m_nibsm_charges=rs.getDouble(1);
					}
					
					tot=tot+m_AMI_VALUES+m_amo_charges+m_general_amount+m_nibsm_charges;
					//out.println("\ntot"+tot);
					more_invoice=rs_invoice.next();		
					
				}
				
				Total_amount_charges=Total_amount_charges+tot;	
				tot_rep=tot*m_exchange_rate;		
				Total_amount_charges_rep=Total_amount_charges_rep+tot_rep;		
				more_app=rs_app.next();		
				
			}
			
			//out.println("Total_amount_charges_rep"+Total_amount_charges_rep);
			
			String Sql_Receipt="SELECT "+
				" RECEIPT_NO,SET_TYPE,REC_TOTAL,CURR_CODE,EXCHANGE_RATE "+
				" FROM "+
				" (   "+
				" SELECT "+
				" RECEIPT_NO RECEIPT_NO, "+//1
				" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','DIR_DEP','Direct Deposit','STD_ORD','Standing Order') SET_TYPE,  "+ //2
				" (RECEIPT_AMOUNT - SUM(INVOICED_AMOUNT)) REC_TOTAL, "+ //3
				" B.CURR_CODE  CURR_CODE,"+ //4
				" MAX(C.EXCHANGE_RATE) EXCHANGE_RATE "+ //5
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B ,"+
				" "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE C "+
				" WHERE B.REC_NO=A.RECEIPT_NO AND  CLIENT_CODE ='"+m_client_code+"' AND  "+
				" B.CURR_CODE=C.CURR_CODE AND "+
				" STATUS NOT IN('RET','C')    "+
				" GROUP BY A.RECEIPT_NO,A.RECEIPT_AMOUNT,B.SETTLE_MODE,B.CURR_CODE "+
				
				" UNION  "+
				
				" SELECT   "+
				" DISTINCT REC_NO RECEIPT_NO,    "+ //1
				" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','DIR_DEP','Direct Deposit','STD_ORD','Standing Order') SET_TYPE,   "+ //2
				" NVL(REC_AMOUNT,0) REC_TOTAL , "+ //3
				" A.CURR_CODE  CURR_CODE ,"+ //4
				" MAX(B.EXCHANGE_RATE) EXCHANGE_RATE "+ //5
				"    FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  A, "+ 
				" "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE B "+
				"    WHERE  CLIENT_CODE ='"+m_client_code+"'    "+
				"    AND A.STATUS NOT IN('RET','C') AND   "+
				"    A.CURR_CODE=B.CURR_CODE AND "+
				"    RENTAL_OTER_INVOICE >0  AND  "+
				"    REC_NO NOT IN (  "+
				"    SELECT RECEIPT_NO   "+
				"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+   
				"    WHERE ALLOCATION_NO IS NOT NULL ) "+
				"    GROUP BY REC_NO,SETTLE_MODE,REC_AMOUNT,A.CURR_CODE "+
				"   )  "+
				"   WHERE REC_TOTAL >0  "+
				"   ORDER BY RECEIPT_NO ";
			
			rs= stmt.executeQuery(Sql_Receipt);
			more=rs.next();
			
			rep_amount=0;
			while(more){
				rep_amount=rs.getDouble(3)*rs.getDouble(5);
				receipt_total_rep=receipt_total_rep+rep_amount;
				more=rs.next();
			}
			
			Total_amount_charges_rep+=_m_total_odi_interest+_m_total_ter_inv+_m_total_rent_inv+_m_total_other_inv;	//added by nuwan de silva 30-10-07				
			
			/*if(receipt_total_rep<Total_amount_charges_rep){
			m_chargeble_amount=Total_amount_charges_rep-receipt_total_rep; //added by nuwan de silva 13-07-2007-
		}
			else if(receipt_total_rep<Total_amount_charges_rep){
			m_chargeble_amount=receipt_total_rep -Total_amount_charges_rep; //added by nuwan de silva 13-07-2007-
		}*/
			
			m_chargeble_amount=Total_amount_charges_rep -receipt_total_rep; //added by nuwan de silva 13-07-2007-
			
			
			
			rs= stmt.executeQuery ("SELECT "+
				" POSITION  "+
				" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
				" WHERE  SCREEN_NAME=SCREEN_NAME");
			more=rs.next();
			if(more){
				m_scr_position=rs.getString(1);
			}
			
			/* //comment by ns 02-03-2010
			rs= stmt.executeQuery ("SELECT "+
	"  TO_CHAR(B.PURCHASE_ORDER_DATE,'DD'),TO_CHAR(B.PURCHASE_ORDER_DATE,'MM'),TO_CHAR(B.PURCHASE_ORDER_DATE,'YYYY'), "+
	"  TO_CHAR(A.RENTAL_DATE,'DD'),TO_CHAR(A.RENTAL_DATE,'MM'),TO_CHAR(A.RENTAL_DATE,'YYYY') "+
	"  FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER B "+
	"  WHERE A.APPLICATION_NO=B.APPLICATION_NO AND B.APPLICATION_NO='"+m_application_no1+"' AND "+
	"  TO_NUMBER(A.INSTALLMENT_NO)=0 AND ACTIVE_STATUS<>'CANCEL' ");
			
			more=rs.next();
			
			if(more){
			b_flag_date=1;
			m_Day_dd=rs.getString(1);
			m_Day_mm=rs.getString(2);
			m_Day_yy=rs.getString(3);
			m_Next_Day_dd=rs.getString(4);
			m_Next_Day_mm=rs.getString(5);
			m_Next_Day_yy=rs.getString(6);
			
			}
			*/
			
			
			
			if(b_flag_date==0){
				/*rs= stmt.executeQuery ("SELECT "+
 				" NVL(TO_CHAR(ACTIVATED_DATE,'DD'),'-'),NVL(TO_CHAR(ACTIVATED_DATE,'MM'),'-'),NVL(TO_CHAR(ACTIVATED_DATE,'YYYY'),'-') , "+
    		" NVL(TO_CHAR(ADD_MONTHS(ACTIVATED_DATE,1),'DD'),'-'), NVL(TO_CHAR(ADD_MONTHS(ACTIVATED_DATE,1),'MM'),'-'),NVL(TO_CHAR(ADD_MONTHS(ACTIVATED_DATE,1),'YYYY'),'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE APPLICATION_NO='"+m_application_no1+"' ");*
				//changed by delanjali on 2007-09-19 as requested
				
				
				//Added by delanjali on 2007-09-19
				
				//modified by madhawa 2009-10-16 commented below sql and aded next sql
				
				/*rs= stmt.executeQuery ("SELECT "+
 				" NVL(TO_CHAR(ACTIVATED_DATE,'DD'),'-'),NVL(TO_CHAR(ACTIVATED_DATE,'MM'),'-'),NVL(TO_CHAR(ACTIVATED_DATE,'YYYY'),'-'),  "+
				" substr("+m_schema_name+".AF_CO_GET_RENTAL_DATE('"+m_application_no1+"'),0,2),substr("+m_schema_name+".AF_CO_GET_RENTAL_DATE('"+m_application_no1+"'),4,2),substr("+m_schema_name+".AF_CO_GET_RENTAL_DATE('"+m_application_no1+"'),7,4) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE APPLICATION_NO='"+m_application_no1+"' ");
				*/
				//end modified by madhawa 2009-10-16 commented below sql and aded next sql
				
				/****
				//modified by madhawa 2009-10-16 added sql
				rs= stmt.executeQuery ("SELECT "+
															" NVL(TO_CHAR(ACTIVATED_DATE,'DD'),'-'),NVL(TO_CHAR(ACTIVATED_DATE,'MM'),'-'),NVL(TO_CHAR(ACTIVATED_DATE,'YYYY'),'-'),  "+
															" substr(TO_CHAR(ADD_MONTHS(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE('"+m_application_no1+"'),'DD-MON-YYYY'),1),'DD-MM-YYYY'),0,2) ,"+ 
															" substr(TO_CHAR(ADD_MONTHS(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE('"+m_application_no1+"'),'DD-MON-YYYY'),1),'DD-MM-YYYY'),4,2) , "+ 
															" substr(TO_CHAR(ADD_MONTHS(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE('"+m_application_no1+"'),'DD-MON-YYYY'),1),'DD-MM-YYYY'),7,4)"+  
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															" WHERE APPLICATION_NO='"+m_application_no1+"'");
        *****/
				
				//added by ns on 02-03-2010 
				rs= stmt.executeQuery ("SELECT "+
					" NVL(TO_CHAR(ACTIVATED_DATE,'DD'),'-'),NVL(TO_CHAR(ACTIVATED_DATE,'MM'),'-'),NVL(TO_CHAR(ACTIVATED_DATE,'YYYY'),'-')  "+
					" ,TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE('"+m_application_no1+"'),'DD-MM-YYYY') ,'DD')   "+
					" ,TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE('"+m_application_no1+"'),'DD-MM-YYYY') ,'MM')   "+
					" ,TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE('"+m_application_no1+"'),'DD-MM-YYYY') ,'YYYY') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_NO='"+m_application_no1+"'");
				
				
				//end modified by madhawa 2009-10-16 added sql
				more=rs.next();
				if(more){
					m_Day_dd=rs.getString(1);
					m_Day_mm=rs.getString(2);
					m_Day_yy=rs.getString(3);
					m_Next_Day_dd=rs.getString(4);
					m_Next_Day_mm=rs.getString(5);
					m_Next_Day_yy=rs.getString(6);
					
					
				}
			}
			
			
			if(m_Day_dd.equals("-") && m_Day_mm.equals("-") && m_Day_yy.equals("-")){
				
				rs= stmt.executeQuery ("SELECT "+
					" TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY'),  "+
					" TO_CHAR(ADD_MONTHS(SYSDATE,1),'DD'), TO_CHAR(ADD_MONTHS(SYSDATE,1),'MM'),TO_CHAR(ADD_MONTHS(SYSDATE,1),'YYYY') "+
					" FROM DUAL  ");
				more=rs.next();
				if(more){
					m_Day_dd=rs.getString(1);
					m_Day_mm=rs.getString(2);
					m_Day_yy=rs.getString(3);
					m_Next_Day_dd=rs.getString(4);
					m_Next_Day_mm=rs.getString(5);
					m_Next_Day_yy=rs.getString(6);
					
					
				}
			}
			rs= stmt.executeQuery ("SELECT "+
				" PAYMENT_MODE  "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING  "+
				" WHERE APPLICATION_NO='"+m_application_no1+"' ");
			
			more=rs.next();
			if(more){
				m_Payement_Mode=rs.getString(1);
			}
			
			/*	rs= stmt.executeQuery ("SELECT "+
 				" NIBSM, "+
 				" AMI, "+
				" PERIOD "+	
 				" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
 				" WHERE APPLICATION_NO='"+m_application_no+"' ");
				more=rs.next();
				if(more){
				m_NIBSM=rs.getDouble(1);
				m_AMI=rs.getInt(2);
				m_PERIOD=rs.getInt(3);
				}
				int count=0;
				if(m_NIBSM>0)	{
				count=(m_PERIOD-m_AMI)-1;
				rs= stmt.executeQuery ("SELECT "+
 				"	SUM(GRENTAL_AMOUNT) SUM "+
        "	FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
 				"	WHERE APPLICATION_NO='"+m_application_no+"'  "+
 				"	AND INSTALLMENT_NO >("+count+") ");
        }	else{
				count=(m_PERIOD-m_AMI);
				rs= stmt.executeQuery ("SELECT "+
 				"	SUM(GRENTAL_AMOUNT) SUM "+
        "	FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
 				"	WHERE APPLICATION_NO='"+m_application_no+"'  "+
 				"	AND INSTALLMENT_NO >=("+count+") ");
				}
					
        more=rs.next();
				if(more){
				m_AMI_VALUES=rs.getDouble(1);
				}
				 rs= stmt.executeQuery ("SELECT "+
 				 " COUNT(APPLICATION_NO) "+
         " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
         " WHERE APPLICATION_NO='"+m_application_no+"' AND  "+
         " PURCHASE_ORDER_NO IS NOT NULL ");
				more=rs.next();
				if(more){
				m_Count=rs.getInt(1);
				}
				if(m_Count>0){
				 rs= stmt.executeQuery ("SELECT "+
 				 " SUM(TOTAL_AMOUNT) INVOICE_AMOUNT "+
 				 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
 				 " WHERE APPLICATION_NO='"+m_application_no+"' AND  "+
 				 " PURCHASE_ORDER_NO IS NOT NULL ");
				}
				more=rs.next();
				if(more){
				m_Invoice_Amount=rs.getDouble(1);
				}
					rs= stmt.executeQuery ("SELECT "+
					"  NVL(SUM(AMOUNT),0) "+
					"  FROM  "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES "+
					"  WHERE APPLICATION_NO='"+m_application_no+"' AND  "+
					"  CHARGE_TYPE='INV' ");
					more=rs.next();
					if(more){
					m_amo_charges=rs.getDouble(1);
					}
					
				if(m_Payement_Mode.equals("ADVANCE")){
				rs= stmt.executeQuery ("SELECT (C.REC_TOTAL - (A.CHARGES+B.GRENTAL_AMOUNT)) CHARGES "+
 				"  FROM "+
 				"  (SELECT "+
 				"  SUM(NIBSM) CHARGES "+
 				"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
 				"  WHERE APPLICATION_NO='"+m_application_no+"' "+
 				"  )A, "+
 				" (SELECT "+
 				" SUM(GRENTAL_AMOUNT) GRENTAL_AMOUNT "+
 				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
 				" WHERE APPLICATION_NO='"+m_application_no+"' "+
 				"  AND INSTALLMENT_NO=0)B, "+
					
				" (SELECT "+
 				" NVL(SUM(RENTAL_OTER_INVOICE),0) REC_TOTAL "+
        " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
        " WHERE CLIENT_CODE = "+
        " (SELECT CLIENT_CODE  "+
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO='"+m_application_no+"') "+
        " AND STATUS NOT IN('RET','C') )C     ");
			 }else{
				rs= stmt.executeQuery ("SELECT (C.REC_TOTAL - A.CHARGES)  CHARGES  "+
				"  FROM "+
 				"  (SELECT SUM(NIBSM) CHARGES "+
 				"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
 				"  WHERE APPLICATION_NO='"+m_application_no+"') A, "+
			  " (SELECT "+
 				" NVL(SUM(RENTAL_OTER_INVOICE),0) REC_TOTAL "+
        " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
        " WHERE CLIENT_CODE = "+
        " (SELECT CLIENT_CODE  "+
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO='"+m_application_no+"') "+
        " AND STATUS NOT IN('RET','C') )C     ");
				}
				 more=rs.next();
				if(more){
				m_Other_Charges=rs.getDouble(1);
				}
				m_total_charge=m_Other_Charges-(m_AMI_VALUES+m_amo_charges);
				*/
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Application Process</TITLE>"); 
			
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			
			out.println("var lineno_con=0;");
			out.println("var arr_size_con=0;");
			//	out.println("var del_row=0;");
			
			out.println("var b_flag=0;"); //Boolean Variable To Hold The Status.
			out.println("var b_chk_doc_state=0;");
			out.println("var b_inv_exsist=0;");
			out.println("var b_flag_code=0;");
			out.println("var b_flag_charge=0;");
			out.println("var m_count=0;");
			out.println("var b_flag_date=0;");
			
			//out.println("var count=0;");
			out.println("var m_check_approv=0;");  //Boolean Variable To Hold The Status. nuwan de silva 19-06-07
			
			out.println("var flag2=0;"); //Boolean Variable To Hold The Status.
			out.println("var flag3=0;"); //Boolean Variable To Hold The Status.
			out.println("var flag4=0;"); //Boolean Variable To Hold The Status.
			
			out.println("var count1=0;"); //Boolean Variable To Hold The Status.
			out.println("var count2=0;"); //Boolean Variable To Hold The Status.
			out.println("var count3=0;"); //Boolean Variable To Hold The Status.
			
			//Ducuments Reference
			out.println("var line_doc=0;");
			out.println("var arr_size_doc=0;");
			out.println("var arr_size_doc_temp=0;");
			
			out.println("var no_row=0;");
			out.println("var row_count=0;");
			out.println("var row_val=0;");
			out.println("var b_chk_state_val=0;");
			out.println("doc_count=0;");
			out.println("row_doc_count=0;");
			out.println("var line_count=0;");
			
			out.println("var line_doc_entity=0;");
			out.println("var arr_size_doc_entity=0;");
			out.println("var del_row_count=0;");
			out.println("var count_inv_no=0;");
			
			out.println("var array_invoice=new Array();");
			out.println("var array_gross=new Array();");
			out.println("var array_vat=new Array();");
			out.println("var array_net=new Array();");
			out.println("var array_invoice_hid_number=new Array();");
			out.println("var array_capital=new Array();");
			out.println("var array_engine_no=new Array();");
			out.println("var array_chassiss_no=new Array();");
			out.println("var array_asset_id=new Array();");
			
			out.println("var array_invoice_data=new Array();");
			out.println("var array_inv_documents=new Array();");
			out.println("var array_doc_applicant=new Array();");
			out.println("var array_hid_pos=new Array();");
			
			//array to hold the documents	
			out.println("var array_code=new Array();");
			out.println("var array_desc=new Array();");
			out.println("var array_remark=new Array();");
			out.println("var array_required=new Array();");
			out.println("var array_inv=new Array();");
			out.println("var array_prv_status=new Array();");
			out.println("var array_prv_remark=new Array();");
			out.println("var array_not_applicable=new Array();");
			out.println("var array_remark_fol=new Array();");
			out.println("var array_status_fol=new Array();");
			out.println("var m_pur_ord_no1='"+m_pur_ord_no+"'");
			
			//Declare Global Arrays
			out.println("var array_follow_up_no=new Array();");
			out.println("var array_condition=new Array();");
			out.println("var array_status=new Array();");
			out.println("var array_document=new Array();"); //added by nuwan de silva on 09-11-07
			out.println("var array_stage=new Array();");
			out.println("var array_document_code=new Array();");
			
			out.println("var array_doc=new Array();");
			out.println("var array_doc_entity=new Array();");
			out.println("var m_fin=0");
			out.println("var m_cond_status=0");
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//!-----------Function To Hold The Current Make Request -------------//
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			//------------------------------------------------------------------------
			
			
			out.println("function get_conditions(){ "); 
			out.println("assignState('M_CON')");				
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=COMPLETED\";");
			
			// out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			
			out.println("}");
			
			
			out.println("function load_Follow(row_No){ "); 
			out.println("m_fol_no=\"TXT_FOLLOW_UP_NO\"+row_No");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&scr_name=AF_CR_PRO_PURCHASE_ORDER&status=Y&Followu_no='+document.Form1.elements[m_fol_no].value;"); //modified by nuwan de silva on 17-10-07--------
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			//____________________________________________________________________________________________________________________________________________________________//
			
			//// conditions Section=====================================================================
			
			//comment by nuwan de silva on 09-11-07
			/*out.println("function header_conditions(){");
		out.println("m_table_con.innerHTML=\"\"");
		out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\"><TR>'+");
		out.println("'<TD WIDTH=\"14%\" align=\"left\"><B>Follow up No</B></TD>'+");
		out.println("'<TD WIDTH=\"10%\" align=\"left\">&nbsp;</TD>'+");
		out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD>'+");
		out.println("'<TD WIDTH=\"15%\" align=\"left\"><B>Status</B></TD>'+");
		out.println("'<TD WIDTH=\"10%\"  align=\"center\"><b>Checked</b></TD>'+");
		out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
		out.println("'<TD WIDTH=\"*%\" ></TD>' +");
		out.println("'</TR></table>';");
		out.println("}");
		*/
			
			out.println("function header_conditions(){");
			out.println("m_table_con.innerHTML=\"\"");
			out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\" ><TR class=pdn_txtpos2>'+");
			out.println("'<TD WIDTH=\"12%\" align=\"left\"><B>Follow up No</B></TD>'+");
			out.println("'<TD WIDTH=\"7%\" align=\"left\">&nbsp;</TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"left\">Type</TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Document</TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">Stage Entered</TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\"><B>Condition</B></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\"><B>Status</B></TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"left\"><B>Checked</TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			//out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</TR></table>';");
			out.println("}");
			
			//comment by nuwan de silva on 09-11-07
			/*out.println("function display_data_conditions(data_vec){ "); 
			out.println("lineno_con=0 ");
			out.println("arr_size_con=0 ");
			out.println("var i=0");
			out.println("header_conditions();");
			out.println("if(data_vec.length>0){");
			out.println("while(i<data_vec.length){");
			out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>'+");									
			out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno_con+' maxlength=\"10\" size=\"10\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
			//out.println("'<TD WIDTH=\"10%\"  align=\"left\"><a href  style=\"{cursor:hand; }\" onclick=\"load_Follow('+lineno_con+')\" >Follow up</a></TD>'+");//&nbsp;&nbsp;Follow up
			out.println("'<TD WIDTH=\"10%\"  align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"load_Follow('+[lineno_con]+')\" ><u>Follow up</u></TD>'+");//&nbsp;&nbsp;Follow up
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno_con+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>'+");
			out.println("'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[i+2]+'</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno_con+'	VALUE='+data_vec[i+2]+'>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+lineno_con+' VALUE=\"\" onclick=\"check_condition(this)\"></td>'+");			
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno_con+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+lineno_con+')\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("i=i+3;");
			out.println("lineno_con=lineno_con+1;");
			out.println("arr_size_con=arr_size_con+1;");
			out.println("}");
			out.println("}");
			out.println("else if(data_vec.length==0){");
			out.println("add_row_conditions()");
			out.println("}");
			out.println("}");
			*/
			out.println("function display_data_conditions(data_vec){ "); 
			out.println("lineno_con=0 ");
			out.println("arr_size_con=0 ");
			out.println("var i=0");
			out.println("header_conditions();");
			out.println("if(data_vec.length>0){");
			out.println("while(i<data_vec.length){");
			
			out.println("m_fol='<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno_con+' maxlength=\"10\" size=\"10\" style=\"{width:110px}\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
			out.println("      '<TD WIDTH=\"7%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+lineno_con+')\" ><U>Follow up</U></TD>';");//&nbsp;&nbsp;Follow up
			
			out.println("if(data_vec[i+3]==\"-\"){");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\" >-</TD>';");//&nbsp;&nbsp;Follow up
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" ) >'+data_vec[i+2].replace('$','&')+'</TD>';");//&nbsp;&nbsp;Follow up
			out.println("}");
			out.println("else {");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\" >Document</TD>';");//&nbsp;&nbsp;Follow up
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=show_document_drill(\"'+data_vec[i+6]+'\") ><U>'+data_vec[i+2].replace('$','&')+'</U></TD>';");//&nbsp;&nbsp;Follow up
			out.println("}");
			
			out.println("m_stage='<TD WIDTH=\"10%\"  align=\"left\"   >'+data_vec[i+3]+'</TD>';");
			out.println("m_condition='<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno_con+' style=\"width:175px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>';");
			out.println("m_status='<TD WIDTH=\"10%\"  align=\"left\">'+data_vec[i+5]+'</TD>';");
			out.println("m_chk_condition='<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+lineno_con+' VALUE=\"\" onclick=\"check_condition(this)\"></td>';");			
			out.println("m_button='<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno_con+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+lineno_con+')\" disabled></TD>';");
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno_con+'	VALUE='+data_vec[i+5]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+lineno_con+'	VALUE=\"'+data_vec[i+2].replace('$','&')+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+lineno_con+'	VALUE=\"'+data_vec[i+6]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+lineno_con+'	VALUE=\"'+data_vec[i+3]+'\" >';");
			out.println("m_writedata='<TR>'+m_fol+m_type+m_doc+m_stage+m_condition+m_status+m_chk_condition+m_button+'</TR>'+m_hid_input;"); 
			out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("i=i+7;");
			out.println("lineno_con=lineno_con+1;");
			out.println("arr_size_con=arr_size_con+1;");
			out.println("}");
			out.println("}");
			
			out.println("else if(data_vec.length==0){");
			out.println("add_row_conditions()");
			out.println("}");
			out.println("}");
			
			
			/*----------------------------------------------------------------
			Purpose  : Add Conditions
			----------------------------------------------------------------*/			
			//comment by nuwan de silva on 09-11-07
			/*out.println("function add_row_conditions(){"); 
			out.println(" var b_flag_con=0;");
			out.println("if(lineno_con!=0){");
			out.println("count=lineno_con-1;");
			out.println("m_condition=\"TXT_CONDITION\"+count");
			out.println("if(document.Form1.elements[m_condition].value==\"\") {");
			out.println("alert('Condition can not be null.');");
			out.println("b_flag_con=1;");
			out.println("}");
			out.println("}");
			
			out.println("if(b_flag_con==0){");
			out.println("m_table_con.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno_con+'>'+");									
			out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno_con+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno_con+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\">'+");
			out.println("'<TD WIDTH=\"15%\"  align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno_con+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+lineno_con+' VALUE=\"\" onclick=\"check_condition(this)\"></td>'+");			
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno_con+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+lineno_con+')\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("lineno_con=lineno_con+1;");
			out.println("arr_size_con=arr_size_con+1;");
			out.println("}");
			out.println("}");
			*/
			
			out.println("function add_row_conditions(){"); 
			out.println("b_flag=0;");
			out.println("if(lineno_con!=0){");
			out.println("count=lineno_con-1;");
			out.println("m_condition=\"TXT_CONDITION\"+count");
			out.println("if(document.Form1.elements[m_condition].value==\"\") {");
			out.println("alert('Condition can not be null.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("}");
			out.println("if(b_flag==0){");
			out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno_con+'>'+");									
			out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno_con+' maxlength=\"10\" style=\"{width:110px}\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno_con+' style=\"width:175px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\">'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno_con+'	        VALUE=\"-\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+lineno_con+'	          VALUE=\"-\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+lineno_con+'	            VALUE=\"-\" >'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+lineno_con+'	    VALUE=\"-\" >'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+lineno_con+' VALUE=\"\" onclick=\"check_condition(this)\"></td>'+");			
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno_con+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+lineno_con+')\"></TD>'+");
			out.println("'</tr></table>';");
			out.println("lineno_con=lineno_con+1;");
			out.println("arr_size_con=arr_size_con+1;");
			out.println("}");
			out.println("}");
			
			
			// Added by Disnaka Jayasuriya on 2009.10.12, 
			// Purpose - Validate Gross Amount
			out.println("function check_number_decimal(obj,size){");
			out.println("if (obj.value != \"\") {");
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			out.println("}");
			
			//comment by nuwan de silva on 09-11-07
			/*out.println("function del_row_conditions(rowNo){"); 
			out.println("if(arr_size_con!=1){");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_con;i++){");
			out.println("m_follow_up=\"TXT_FOLLOW_UP_NO\"+i");
			out.println("m_condition=\"TXT_CONDITION\"+i");
			out.println("m_status=\"hid_TXT_STATUS\"+i");					
			out.println("if(i==rowNo)");
			out.println("continue;");
			out.println("array_follow_up_no[j]=document.Form1.elements[m_follow_up].value;");
			out.println("array_condition[j]=document.Form1.elements[m_condition].value;");
			out.println("array_status[j]=document.Form1.elements[m_status].value;");    
			out.println("j=j+1;");
			out.println("}");
			out.println("lineno_con=lineno_con-1;");
			out.println("arr_size_con=arr_size_con-1;");
			out.println("write_data_conditions(arr_size_con);");
			out.println("}");
			*/
			
			out.println("function del_row_conditions(rowNo){"); 
			out.println("if(arr_size_con!=1){");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size_con;i++){");
			out.println("m_follow_up=\"TXT_FOLLOW_UP_NO\"+i");
			out.println("m_condition=\"TXT_CONDITION\"+i");
			out.println("m_status=\"hid_TXT_STATUS\"+i");					
			out.println("m_document=\"hid_DOCUMENT\"+i");					
			out.println("m_document_code=\"hid_DOCUMENT_CODE\"+i");					
			out.println("m_stage=\"hid_STAGE\"+i");			
			out.println("if(i==rowNo)");
			out.println("continue;");
			out.println("array_follow_up_no[j]       = document.Form1.elements[m_follow_up].value;");
			out.println("array_condition[j]          = document.Form1.elements[m_condition].value;");
			out.println("array_status[j]             = document.Form1.elements[m_status].value;");    
			out.println("array_document[j]           = document.Form1.elements[m_document].value;"); //added by nwuan de silva on 09-11-07
			out.println("array_stage[j]              = document.Form1.elements[m_stage].value;");
			out.println("array_document_code[j]     = document.Form1.elements[m_document_code].value;"); //added by nwuan de silva on 09-11-07
			out.println("j=j+1;");
			out.println("}");
			out.println("lineno_con=lineno_con-1;");
			out.println("arr_size_con=arr_size_con-1;");
			out.println("write_data_conditions(arr_size_con);");
			out.println("}");
			
			//comment by nuwan de silva on 09-11-07
			/*out.println("function write_data_conditions(size){");
			//out.println("m_table_con.innerHTML=\"\";");
			out.println("header_conditions();");
	out.println(" for(var j=0;j<size;j++){");
			out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
	out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr >'+");									
			out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+lineno_con+' VALUE=\"\" onclick=\"check_condition(this)\"></td>'+");			
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+j+')\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
	out.println("continue;");
			out.println("}");
			out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
	out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr >'+");									
			out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" value='+array_condition[j]+' maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+lineno_con+' VALUE=\"\" onclick=\"check_condition(this)\"></td>'+");			
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+j+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
	out.println("continue;");
			out.println("}");
			out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
	out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr >'+");									
			out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"load_Follow('+[j]+')\" ><u>Follow up</u></TD>'+");//&nbsp;&nbsp;Follow up
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">'+array_status[j]+'</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE='+array_status[j]+'>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+lineno_con+' VALUE=\"\" onclick=\"check_condition(this)\"></td>'+");			
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+j+')\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
	out.println("continue;");
			out.println("}");
				out.println("}");		
			out.println("}");		
			out.println("}");		
			
			*/
			
			
			//added by nuwan de silva on 09-11-07----
			out.println("function write_data_conditions(size){");
			out.println("sum=0;");
			out.println("m_table_con.innerHTML=\"\";");
			out.println("header_conditions();");
			out.println(" for(var j=0;j<size;j++){");
			out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
			out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\"  style=\"{width:110px;}\"  disabled></TD>'+");
			out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:175px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	      VALUE=\"-\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	        VALUE=\"-\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	            VALUE=\"-\" >'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	    VALUE=\"-\" >'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+j+' VALUE=\"\" onclick=\"check_condition(this)\"></td>'+");			
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+j+')\"></TD>'+");
			out.println("'</tr></table>';");
			out.println("continue;");
			out.println("}");
			out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
			out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" style=\"{width:110px}\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:175px; height:20px;\" value=\"'+array_condition[j]+'\" maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">-</TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+j+' VALUE=\"\" onclick=\"check_condition(this)\"></td>'+");			
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	     VALUE=\"-\" >'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	       VALUE=\"-\" >'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	           VALUE=\"-\" >'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	 VALUE=\"-\" >'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+j+')\" ></TD>'+");
			out.println("'</tr></table>';");
			out.println("continue;");
			out.println("}");
			
			out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
			out.println("m_fol='<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" style=\"{width:110px}\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"7%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+j+')\" >Follow up</TD>';"); //Follow up
			
			out.println("if(array_stage[j]==\"-\"){");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\">-</TD>';");
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" >'+array_document[j]+'</TD>';");
			out.println("}");
			out.println("else {");
			out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\">Document</TD>';");
			out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=show_document_drill(\"'+array_document_code[j]+'\") ><u>'+array_document[j]+'</u></TD>';");
			out.println("}");
			
			out.println("m_stage='<TD WIDTH=\"10%\"  align=\"left\">'+array_stage[j]+'</TD>';");
			out.println("m_condition='<TD WIDTH=\"20%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:175px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>';");
			out.println("m_status='<TD WIDTH=\"10%\" align=\"left\">'+array_status[j]+'</TD>';");
			out.println("m_chk_condition='<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_CONDITION'+j+' VALUE=\"\" onclick=\"check_condition(this)\"></td>';");			
			out.println("m_button='<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row_conditions('+j+')\" disabled></TD>';");
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	        VALUE='+array_status[j]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	          VALUE=\"'+array_document[j]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	              VALUE=\"'+array_stage[j]+'\" >'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	      VALUE=\"'+array_document_code[j]+'\" >';");
			
			out.println("m_writedata='<TR>'+m_fol+m_type+m_doc+m_stage+m_condition+m_status+m_chk_condition+m_button+'</TR>'+m_hid_input;"); 
			out.println("m_table_con.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("continue;");
			out.println("}");
			out.println("}");		
			out.println("}");		
			out.println("}");		
			
			
			//// end conditions Section=====================================================================
			//___________________________________________________________________________________________________________________________________________________________________________________________________				
			out.println("function load_pur_ord_data(){");
			//m_string="";
			out.println(" pur_ord_no='"+m_pur_ord_no+"'");			
			out.println(" if(pur_ord_no!='null'){");
			out.println("   document.Form1.TXT_PURCHASE_ORDER_NO.value=pur_ord_no; "); 
			out.println("load_screen_status(\"DEL\")");
			out.println("assignState('M1');");			
			out.println("makeRequest(document.Form1.TXT_PURCHASE_ORDER_NO);");			
			out.println("}");			
			out.println("}");		
			
			out.println("function load_app_data(){");
			out.println(" pur_ord_no='"+m_pur_ord_no+"'");			
			out.println(" if(pur_ord_no=='null'){");
			out.println(" application_no='"+m_application_no1+"'");
			out.println(" m_count='"+m_Count+"'");
			out.println(" m_total_charge='"+m_total_charge+"'");
			//out.println(" alert('application_no:'+m_count);");
			out.println(" if(application_no!='null'){"); 
			//out.println("get_conditions();");
			out.println("   document.Form1.TXT_APPLICATION_NO.value=application_no; "); 
			out.println("   document.Form1.hid_close_sts.value='Y';");
			
			/*	out.println("if(m_total_charge<0){");
			out.println("   document.Form1.hid_invoice_amount.value='"+a.abs(m_total_charge)+"';"); 
			out.println("   document.Form1.TXT_OTHER_CHATGES.value='"+nf.format(a.abs(m_total_charge))+"'; "); 
			out.println("  }");
			out.println("else{");
			out.println("   document.Form1.TXT_OTHER_CHATGES.value=\"0.00\"; "); 
			out.println("  }");
			*/	
			out.println("   document.Form1.hid_invoice_amount.value='"+a.abs(m_chargeble_amount)+"';"); 
			//out.println("   document.Form1.TXT_OTHER_CHATGES.value='"+nf.format(a.abs(m_chargeble_amount))+"'; "); 
			out.println("   document.Form1.TXT_OTHER_CHATGES.value='"+nf.format((m_chargeble_amount))+"'; "); 
			//	out.println("get_amount_to_be_charged()");
			out.println("get_doc_entity_apllication()");				
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=false");
			out.println("document.Form1.BUT_TXT_VENDOR_CODE.disabled=false");
			out.println("get_sys_date();");				
			out.println("}");
			out.println("}");			
			out.println("}");
			
			
			
			out.println("function get_sys_date(){");
			
			out.println("b_flag_date='"+b_flag_date+"';");
			
			//	out.println("alert("+b_flag_date_act+");");	
			
			out.println("     document.Form1.TXT_ACT_DATE_DD.value=\""+m_Day_dd+"\";");
			out.println("     document.Form1.TXT_ACT_DATE_MM.value=\""+m_Day_mm+"\";");
			out.println("     document.Form1.TXT_ACT_DATE_YY.value=\""+m_Day_yy+"\";");
			
			
			out.println("     document.Form1.TXT_NEXT_DATE_DD.value=\""+m_Next_Day_dd+"\";");
			out.println("     document.Form1.TXT_NEXT_DATE_MM.value=\""+m_Next_Day_mm+"\";");
			out.println("     document.Form1.TXT_NEXT_DATE_YY.value=\""+m_Next_Day_yy+"\";");
			
			out.println("if('"+b_flag_date+"'==1){");
			
			out.println("     document.Form1.TXT_ACT_DATE_DD.disabled=true;");
			out.println("     document.Form1.TXT_ACT_DATE_MM.disabled=true;");
			out.println("     document.Form1.TXT_ACT_DATE_YY.disabled=true;");
			
			
			out.println("     document.Form1.TXT_NEXT_DATE_DD.disabled=true;");
			out.println("     document.Form1.TXT_NEXT_DATE_MM.disabled=true;");
			out.println("     document.Form1.TXT_NEXT_DATE_YY.disabled=true;");
			
			out.println("}");
			
			out.println("}");
			
			
			
			//!--------Display The Header -------------------------------------//
			out.println("function header(){");			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><TR>'+");
			out.println("'<TD WIDTH=\"20%\"     align=\"center\"><B>Invoice Number</B></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\"     align=\"left\"><B>Engine/Serial No *</B></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\"     align=\"left\"><B>Chassis No *</B></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\"     align=\"right\"><B>Gross Price</B></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\"     align=\"right\"><B>VAT</B></TD>' +");
			out.println("'<TD WIDTH=\"12.5%\"     align=\"right\"><B>Net Price&nbsp;</B></TD>' +");
			out.println("'<TD WIDTH=\"10%\"       align=\"left\"><B>Capital Allowance %</B></TD>' +");
			out.println("'<TD WIDTH=\"7.5%\"        align=\"center\">&nbsp;</TD>' +");
			out.println("'</TR></table>';");
			out.println("b_chk_state_val=1;");
			out.println("}");
			
			//--------------------------------------------------------------------------		
			
			
			//!-----------Validate Invoice Bumber ---------------------------------------
			out.println("function validate_invoice_number(rowNo){");
			out.println("m_inv_no=\"TXT_INVOICE_NO\"+rowNo");
			out.println("assignState('M8')");
			out.println("document.Form1.hid_row_no.value=rowNo;"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_invoice_validate_display&data_val=\"+document.Form1.TXT_VENDOR_CODE.value+\"&data_val2=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val3=\"+document.Form1.elements[m_inv_no].value+\"&ac_status=Y\";");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");	
			
			//------------------------------------------------------------------------------------------------
			
			//!-----------Assign Values To The Invoice Number ------------------------------------------
			out.println("function fill_invoice_no(rowNo,data_vec){");
			out.println("m_inv_no=\"TXT_INVOICE_NO\"+rowNo;");
			out.println("m_gross=\"TXT_GROSS\"+rowNo;");	
			out.println("m_vat=\"TXT_VAT\"+rowNo;");
			out.println("m_net=\"TXT_NET\"+rowNo;");	
			out.println("m_capital=\"TXT_CAPITAL\"+rowNo;");	
			out.println("m_engine_no=\"TXT_ENGINE_NO\"+rowNo;");	
			out.println("m_chassiss_no=\"TXT_CHASSISS_NO\"+rowNo;");	
			out.println("m_asset_id=\"hid_TXT_ASSET_ID\"+rowNo;");	
			out.println("    document.Form1.elements[m_inv_no].value=data_vec[0];"); 
			out.println("    document.Form1.elements[m_gross].value=data_vec[6];"); 
			out.println("    document.Form1.elements[m_vat].value=data_vec[5];"); 
			out.println("    document.Form1.elements[m_net].value=data_vec[4];");
			out.println("    document.Form1.elements[m_capital].value=data_vec[7];");
			
			
			out.println("if(data_vec[2]=='-' || data_vec[3]=='-'){ ");
			out.println("    document.Form1.elements[m_engine_no].value='';");
			out.println("    document.Form1.elements[m_chassiss_no].value='';");
			out.println("}");
			out.println("else {");
			out.println("    document.Form1.elements[m_engine_no].value=data_vec[2];");
			out.println("    document.Form1.elements[m_chassiss_no].value=data_vec[3];");
			out.println("}");
			out.println("    document.Form1.elements[m_asset_id].value=data_vec[1];");
			
			out.println("check_exist_of_invoice_number(document.Form1.elements[m_inv_no].value,rowNo);");
			out.println("if(b_inv_exsist==0){");
			out.println("m_invoice_hid_number=\"hid_TXT_INVOICE_NUMBER\"+rowNo");			
			out.println("if(document.Form1.elements[m_invoice_hid_number].value!=\"\"){");
			out.println("document.Form1.hid_inv_no_chk.value=document.Form1.elements[m_invoice_hid_number].value;");
			out.println("}");
			
			/*out.println("m_invoice_hid_number=\"hid_TXT_INVOICE_NUMBER\"+rowNo");			
			out.println("if(document.Form1.elements[m_invoice_hid_number].value!=\"\"){");
			out.println("document.Form1.hid_inv_no_chk.value=document.Form1.elements[m_invoice_hid_number].value;");
			out.println("}");
			*/
			out.println("del_row_assign(rowNo);");
			out.println("show_invoice_doc(document.Form1.elements[m_invoice].value);");
			out.println("}");
			out.println("}"); 
			
			//--------------------------------------------------------------------------------------------------
			
			
			//!----------------Disable The Fields-----------------------------------------------------------
			out.println("function disable_fields(size){");
			out.println("for(i=0;i<size;i++){");
			out.println("m_gross=\"TXT_GROSS\"+i");
			out.println("m_vat=\"TXT_VAT\"+i");
			out.println("m_net=\"TXT_NET\"+i");
			
			
			
			//out.println("document.Form1.elements[m_gross].disabled=true;");
			out.println("document.Form1.elements[m_vat].disabled=true;");
			out.println("document.Form1.elements[m_net].disabled=true;}");
			
			
			
			out.println("}");
			
			//--------------------------------------------------------------------------------------------
			
			
			
			
			out.println("function get_doc(){");
			out.println("assignState('M5');"); 
			
			
			out.println("while(row_count<array_invoice_data.length){");
			out.println("document.Form1.hid_inv_no.value=array_invoice_data[row_count];");
			
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_get_documents_edit&data_val=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&data_val2=\"+document.Form1.hid_inv_no.value+\"&data_val3=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=Y\";");
			
			
			out.println("load_interface(m_url,'XML');");
			out.println("row_count=row_count+8;");
			
			//out.println("window.open(m_url);");
			
			out.println("}");
			out.println("}");
			
			
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			out.println("function document_entity_edit(){ ");
			
			out.println("assignState('M12');"); 
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_get_entity_doc_edit&data_val2=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&ac_status=Y\";");
			
			
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			
			out.println("}");		
			
			
			
			
			out.println("  function  display_documents_entity_edit(data_vec_doc_entity){");
			//	out.println("alert('check'+data_vec_doc_entity.length);");
			// out.println("var j=1;");
			out.println("var i=0;");
			
			out.println("if(data_vec_doc_entity.length>0){");
			
			out.println("header_doc_entity(data_vec_doc_entity);	");		
			
			out.println("}");
			
			out.println("while(i<data_vec_doc_entity.length){");
			
			
			out.println("m_description='<TD WIDTH=\"20%\">'+data_vec_doc_entity[i+2].replace('$','&')+'</TD>';");
			out.println("m_prv_remark='<TD WIDTH=\"10%\">'+data_vec_doc_entity[i+3]+'</TD>';");		
			out.println("if(data_vec_doc_entity[i+4]==\"Y\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">Yes</TD>';");	
			out.println("else if(data_vec_doc_entity[i+4]==\"N\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">No</TD>';");	
			out.println("else if(data_vec_doc_entity[i+4]==\"A\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">Not Applicable</TD>';");	
			
			out.println("else if(data_vec_doc_entity[i+4]==\"-\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");	
			
			
			
			
			out.println("m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED_ENTITY'+line_doc_entity+' VALUE=\"\" onclick=\"change_val_req_entity('+line_doc_entity+')\"></td>';");			
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE_ENTITY'+line_doc_entity+' VALUE=\"\" onclick=\"change_val_not_app_entity('+line_doc_entity+')\"></td>';");			
			out.println("m_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK_ENTITY'+line_doc_entity+' VALUE=\"\" maxlength=\"50\" size=\"20\"></td>';");		
			
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_ENTITY'+line_doc_entity+' VALUE=\"off\" onclick=\"change_val_fol_up_entity('+line_doc_entity+')\"></td>';");			
			
			out.println("					if(data_vec[i+6]==\"-\") ");
			out.println("					{ ");
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_ENTITY'+line_doc_entity+' VALUE=\"off\" onclick=\"change_val_fol_up_entity('+line_doc_entity+')\"></td>';");			
			out.println("					} ");
			
			out.println("					else ");
			out.println("					{ ");
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_ENTITY'+line_doc_entity+' VALUES=\"on\" checked  onclick=\"change_val_fol_up_entity('+line_doc_entity+')\"></td>';");			
			out.println("					} ");
			
			
			out.println("m_followup_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_FOLLOW_UP_REMARK_ENTITY'+line_doc_entity+' VALUE=\"'+data_vec_doc_entity[i+5]+'\" maxlength=\"50\" size=\"20\" disabled ></td>';"); // disabled by udara 08-04-2016		
			
			
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DOC_CODE_ENTITY'+line_doc_entity+'	VALUE='+data_vec_doc_entity[i+1]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESCRIPTION_ENTITY'+line_doc_entity+'	VALUE='+data_vec_doc_entity[i+2].replace('$','&')+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_REMARK_ENTITY'+line_doc+'	VALUE=\"'+data_vec_doc_entity[i+3]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_STATUS_ENTITY'+line_doc+'	VALUE=\"'+data_vec_doc_entity[i+4]+'\">'+");
			
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INV_NO_ENTITY'+line_doc+'	VALUE='+document.Form1.hid_inv_no.value+'>';");
			
			out.println("m_writedata='<TR>'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;"); 
			
			
			out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			
			out.println("i=i+7;");
			out.println("line_doc_entity=line_doc_entity+1;");
			out.println("arr_size_doc_entity=arr_size_doc_entity+1;");		
			out.println("}"); //End while loop
			//	out.println("alert('arr size entity'+arr_size_doc_entity);");
			
			out.println("if(document.Form1.SCREEN_NAME.value!=\"NEW\"){");	
			
			//***********************************Get The Invoices********************************************				
			out.println("    assignState('M7');"); 
			out.println("    makeRequest(document.Form1.TXT_PURCHASE_ORDER_NO);");
			//*****************************************************************************************************************
			out.println("}");		
			
			out.println("}");		
			
			//function display the documents.
			
			out.println("function display_invoice_no_doc(){");
			//out.println("alert('a');");
			out.println("assignState('M13');"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_invoice_no_doc&data_val=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value;");
			out.println("load_interface(m_url,'XML');");
			//	out.println("window.open(m_url);");
			out.println("}");
			
			
			
			out.println("function get_vector_values(data_vec){");
			
			out.println("array_invoice_data=data_vec;");
			out.println("b_chk_state_val=0;");
			out.println("row_val=0;");
			out.println("row_count=0;");
			
			
			
			//		out.println("display_invoice_no_doc();");//call to get the dtails of invoices that no documents
			out.println("get_doc();");
			out.println("}");
			
			
			
			
			out.println("function display_data2(data_vec_inv,data_vec_doc){");
			
			out.println("if(b_chk_state_val==0){");
			out.println("lineno=0;");
			out.println("arr_size=0;");
			out.println("m_table.innerHTML=\"\"");
			out.println("header();");
			out.println("}");
			
			//out.println("j=0;");
			//out.println("i=0;");
			
			out.println("if(data_vec_doc.length==0){");
			
			
			/* out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" size=\"50\" value='+data_vec_inv[row_val]+' onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value='+data_vec_inv[row_val+1]+' size=\"10\" STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+data_vec_inv[row_val+2]+' size=\"10\" STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\" size=\"10\" value='+data_vec_inv[row_val+3]+' STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"2\" size=\"10\" value='+data_vec_inv[row_val+4]+' STYLE=\"{text-align:right;width:100px;}\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+data_vec_inv[row_val]+'\">'+");
			out.println("'</td></tr></table>';");
			*/
			
			
			/*out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+data_vec_inv[row_val]+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"10\" value='+data_vec_inv[row_val+5]+'  size=\"10\" STYLE=\"{width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value='+data_vec_inv[row_val+6]+'  size=\"10\" STYLE=\"{width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value='+data_vec_inv[row_val+1]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+data_vec_inv[row_val+2]+''  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value='+data_vec_inv[row_val+3]+' size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"2\"  value='+data_vec_inv[row_val+4]+' size=\"10\" STYLE=\"{text-align:right; width:50px;}\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+data_vec_inv[row_val]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"'+data_vec_inv[row_val+7]+'\"></TD>'+");
			out.println("'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			out.println("'</tr></table>';");	
			*/
			out.println("m_inv='<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+data_vec_inv[row_val]+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("      '<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>';");
			
			
			out.println("m_eng_no='<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"'+data_vec_inv[row_val+5]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled ></TD>'+");
			out.println("          '<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"'+data_vec_inv[row_val+6]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>';");
			
			out.println("m_data='<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"24\" onblur=\"check_number_decimal(this,20)\" value='+data_vec_inv[row_val+1]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+data_vec_inv[row_val+2]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value='+data_vec_inv[row_val+3]+' size=\"10\" STYLE=\"{text-align:right;width:100px;}\">&nbsp;</TD>'+");
			out.println("				'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"6\"  onblur=\"check_number_precent(this,6)\" value='+data_vec_inv[row_val+4]+' size=\"10\" STYLE=\"{text-align:right; width:75px;}\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+data_vec_inv[row_val]+'\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"'+data_vec_inv[row_val+7]+'\"></TD>'+");
			out.println("				'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>';");
			
			out.println("m_writedata='<TR>'+m_inv+m_eng_no+m_data+'</TR>';");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			
			
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			
			out.println("get_doc();");		
			
			out.println("}");
			
			
			
			out.println("else");
			out.println("if(data_vec_doc.length>0){");
			
			/*	out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" size=\"50\" value='+data_vec_inv[row_val]+' onblur=\"validate_invoice_number('+lineno+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			 	out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value='+data_vec_inv[row_val+1]+' size=\"10\" STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+data_vec_inv[row_val+2]+' size=\"10\" STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\" size=\"10\" value='+data_vec_inv[row_val+3]+' STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"2\" size=\"10\" value='+data_vec_inv[row_val+4]+' STYLE=\"{text-align:right;width:100px;}\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+data_vec_inv[row_val]+'\">'+");
				out.println("'</td></tr></table>';");
				*/
			
			out.println("m_inv='<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+data_vec_inv[row_val]+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("      '<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>';");
			
			
			out.println("m_eng_no='<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"'+data_vec_inv[row_val+5]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>'+");
			out.println("          '<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"'+data_vec_inv[row_val+6]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>';");
			
			out.println("m_data='<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"24\" onblur=\"check_number_decimal(this,20)\" value='+data_vec_inv[row_val+1]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+data_vec_inv[row_val+2]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value='+data_vec_inv[row_val+3]+' size=\"10\" STYLE=\"{text-align:right;width:100px;}\">&nbsp;</TD>'+");
			out.println("				'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"6\"  onblur=\"check_number_precent(this,6)\" value='+data_vec_inv[row_val+4]+' size=\"10\" STYLE=\"{text-align:right; width:75px;}\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+data_vec_inv[row_val]+'\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"'+data_vec_inv[row_val+7]+'\"></TD>'+");
			out.println("				'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>';");
			
			out.println("m_writedata='<TR>'+m_inv+m_eng_no+m_data+'</TR>';");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			
			out.println("display_documents2(data_vec_doc);");
			
			out.println("get_doc();}");
			
			
			//	out.println("j=j+1;");
			out.println("row_val=row_val+8;");
			
			
			
			
			//		out.println("}"); //End of for loop;
			
			//			out.println("}");
			
			
			
			
			//	out.println("lineno=lineno+1;");
			//	out.println("arr_size=arr_size+1;");
			
			out.println("cal_values();");
			out.println("disable_fields(arr_size)");
			
			
			out.println("disable_controls();");
			
			out.println("disable_new()");
			
			out.println("}"); 
			
			
			
			out.println("function display_data(data_vec) {");
			
			out.println("       document.Form1.TXT_PURCHASE_ORDER_NO.value=data_vec[0] "); 
			out.println("       document.Form1.TXT_APPLICATION_NO.value=data_vec[1]  "); 
			out.println("       document.Form1.TXT_VENDOR_CODE.value=data_vec[2] "); 
			out.println("       document.Form1.TXT_VENDOR_NAME.value=data_vec[3] "); 
			out.println("       document.Form1.TXT_BRANCH_CODE.value=data_vec[4] "); 
			out.println("       document.Form1.TXT_BRANCH_NAME.value=data_vec[5] "); 
			//out.println("b_flag_code=1");
			//out.println("get_branch_code()");
			out.println("    document_entity_edit();"); //display the documents clinet wise
			
			//out.println("    assignState('M7');"); 
			//out.println("    makeRequest(document.Form1.TXT_PURCHASE_ORDER_NO);");
			
			out.println("			}");
			
			
			//=====================Function Clear The Array======================================
			out.println("function clear_arrays(rowNo){");
			out.println("var line_count=0;");
			
			out.println("while(line_count<array_inv.length)");
			out.println("if(document.Form1.hid_inv_no_chk.value==array_inv[line_count]){");
			// out.println("alert('array desc'+array_desc[line_count]);");
			out.println("array_inv[line_count]=\"\"");
			out.println("array_code[line_count]=\"\";");
			out.println("array_desc[line_count]=\"\";");
			out.println("array_remark[line_count]=\"\";");
			out.println("array_required[line_count]=\"\";");
			out.println("array_prv_status[line_count]=\"\";");
			out.println("array_prv_remark[line_count]=\"\";");
			out.println("array_not_applicable[line_count]=\"\";");
			
			out.println("line_count=line_count+1;");
			
			out.println("}");
			
			
			out.println("}");
			
			
			//================================================================
			
			
			
			out.println("function assingn_new_inv_data(data_vec,rowNo){");
			out.println("line_count=0;");  
			out.println("if(b_chk_state_val==0){");
			out.println("lineno=0;");
			out.println("m_table.innerHTML=\"\"");
			out.println("header();");
			out.println("}");
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("if(i==rowNo){");
			out.println("document.Form1.hid_inv_no.value=array_invoice[lineno];");		
			out.println("m_inv='<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+document.Form1.hid_inv_no.value+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("      '<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>';");
			out.println("if(array_engine_no[lineno]==\"\" || array_chassiss_no[lineno]==\"\" ){");
			out.println("m_eng_no='<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>'+");
			out.println("         '<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>';");
			out.println("}");
			out.println("else {");
			out.println("m_eng_no='<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"'+array_engine_no[lineno]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>'+");
			out.println("          '<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"'+array_chassiss_no[lineno]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>';");
			out.println("}");
			out.println("m_data='<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"24\" onblur=\"check_number_decimal(this,20)\" value='+array_gross[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+array_vat[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value='+array_net[lineno]+' size=\"10\" STYLE=\"{text-align:right;width:100px;}\">&nbsp;</TD>'+");
			out.println("				'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"6\" onblur=\"check_number_precent(this,6)\"  value='+array_capital[lineno]+' size=\"10\" STYLE=\"{text-align:right; width:75px;}\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+array_invoice[lineno]+'\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"'+array_asset_id[lineno]+'\"></TD>'+");
			out.println("				'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>';");
			out.println("m_writedata='<TR>'+m_inv+m_eng_no+m_data+'</TR>';");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("display_documents3(data_vec);");
			out.println("lineno=lineno+1;");
			out.println("continue;");
			out.println("}");
			out.println("document.Form1.hid_inv_no.value=array_invoice[lineno];");
			out.println("if(array_invoice[lineno]==\"\" ){");
			/*	out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value=\"\" size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			 	out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value=\"\"  size=\"10\" STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value=\"\" size=\"10\" STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"2\"  value=\"\"  size=\"10\" STYLE=\"{text-align:right; width:100px;}\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+array_invoice[lineno]+'\">'+");
				out.println("'</td></tr></table>';");
				*/
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+document.Form1.hid_inv_no.value+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"24\" onblur=\"check_number_decimal(this,20)\" value=\"\"  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value=\"\" size=\"10\" STYLE=\"{text-align:right;width:100px;}\">&nbsp;</TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"6\"  value=\"\" onblur=\"check_number_precent(this,6)\" size=\"10\" STYLE=\"{text-align:right; width:75px;}\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+array_invoice[lineno]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"\"></TD>'+");
			out.println("'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			out.println("'</tr></table>';");	
			
			
			out.println("lineno=lineno+1;");
			out.println("continue;");
			
			out.println("}");	
			
			/*out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+document.Form1.hid_inv_no.value+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"10\" value='+array_engine_no[lineno]+'  size=\"10\" STYLE=\"{width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value='+array_chassiss_no[lineno]+'  size=\"10\" STYLE=\"{width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value='+array_gross[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+array_vat[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value='+array_net[lineno]+' size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"2\"  value='+array_capital[lineno]+' size=\"10\" STYLE=\"{text-align:right; width:50px;}\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+array_invoice[lineno]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"'+array_asset_id[lineno]+'\"></TD>'+");
			out.println("'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			out.println("'</tr></table>';");	
			*/
			
			out.println("m_inv='<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+document.Form1.hid_inv_no.value+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("      '<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>';");
			
			out.println("if(array_engine_no[lineno]==\"\" || array_chassiss_no[lineno]==\"\" ){");
			
			out.println("m_eng_no='<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\"></TD>'+");
			out.println("         '<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\"></TD>';");
			out.println("}");
			
			out.println("else {");
			out.println("m_eng_no='<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"'+array_engine_no[lineno]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>'+");
			out.println("          '<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"'+array_chassiss_no[lineno]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>';");
			out.println("}");
			
			out.println("m_data='<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value='+array_gross[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+array_vat[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value='+array_net[lineno]+' size=\"10\" STYLE=\"{text-align:right;width:100px;}\">&nbsp;</TD>'+");
			out.println("				'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"6\"  onblur=\"check_number_precent(this,6)\" value='+array_capital[lineno]+' size=\"10\" STYLE=\"{text-align:right; width:75px;}\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+array_invoice[lineno]+'\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"'+array_asset_id[lineno]+'\"></TD>'+");
			out.println("				'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>';");
			out.println("m_writedata='<TR>'+m_inv+m_eng_no+m_data+'</TR>';");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			out.println("doc(array_invoice[lineno]);");
			out.println("lineno=lineno+1;");
			out.println("}"); 	    
			out.println("cal_values();");
			out.println("disable_fields(arr_size)");
			//	out.println("clear_arrays(rowNo);");
			out.println("}"); 
			
			
			
			out.println("function get_vector(data_vec) {");
			out.println("			m_inv_no=\"TXT_INVOICE_NO\"+document.Form1.hid_row_no.value");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_PURCHASE_ORDER_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("     help_update('1','10','0','m_help_TXT_PURCHASE_ORDER_NO','99');}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_PURCHASE_ORDER_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     display_data(data_vec);");
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M7'){");
			out.println("     get_vector_values(data_vec);");//assing invoce data
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M5'){");
			out.println("			display_data2(array_invoice_data,data_vec);");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_inv_no].value!=\"\"){");
			//out.println("     del_row_assign(rowNo);"); //added by nuwan de silva on 13-07-07----------------
			out.println("       fill_invoice_no(document.Form1.hid_row_no.value,data_vec);"); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_inv_no].value!=\"\"){");
			out.println("     help_button_3(document.Form1.hid_row_no.value);");
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M_CON'  && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("     display_data_conditions(data_vec);"); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("			help_button_1('1','10','0','m_help_TXT_APPLICATION_NO_PUR_ORD','1')");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("    	document.Form1.TXT_APPLICATION_NO.value=data_vec[0];"); 
			//ADDED BY DELANJALI ON 2007-09-19
			out.println("     document.Form1.TXT_ACT_DATE_DD.value=data_vec[1];");
			out.println("     document.Form1.TXT_ACT_DATE_MM.value=data_vec[2];"); 
			out.println("     document.Form1.TXT_ACT_DATE_YY.value=data_vec[3];"); 
			out.println("     document.Form1.TXT_NEXT_DATE_DD.value=data_vec[4];"); 
			out.println("     document.Form1.TXT_NEXT_DATE_MM.value=data_vec[5];"); 
			out.println("     document.Form1.TXT_NEXT_DATE_YY.value=data_vec[6];"); 
			
			
			
			out.println("			get_doc_entity_apllication();");
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M9' && data_vec.length>0){");
			out.println("     assingn_new_inv_data(data_vec,document.Form1.hid_row_no.value);");	
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M10' && data_vec.length>0){");
			out.println("     display_documents_entity(data_vec);");
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M11'){");
			out.println("     write_data(data_vec);");
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M12'){");// && data_vec.length>0)
			out.println("     display_documents_entity_edit(data_vec);");//display the documents client wise
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M13' ){");
			out.println("     show_invoice_no_doc(data_vec);");
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M15'){");
			out.println("    document.Form1.TXT_BRANCH_CODE.value=data_vec[0];"); 
			out.println("   if(b_flag_code==1)");
			out.println("    document_entity_edit();"); //display the documents clinet wise
			out.println("			}");
			out.println("		else	if(document.Form1.hid_chk_status.value=='M16' ){");
			out.println("    document.Form1.hid_TXT_CURR_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.hid_TXT_EXCHANGE_RATE.value=data_vec[1];"); 
			out.println("			}");
			out.println("		else	if(document.Form1.hid_chk_status.value=='M17' ){");
			//	out.println("     display_documents3(data_vec);");
			out.println("			}");
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M20'){");
			//out.println("     display_documents_entity(data_vec);");
			out.println("     display_documents_entity_app_no(data_vec);");
			out.println("			data_vec='';");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M56' && document.Form1.TXT_VENDOR_CODE.value!=\"\"){");
			out.println("     document.Form1.TXT_VENDOR_CODE.value=data_vec[0];"); 
			out.println("     i=0;");
			out.println("     m_inv_btn=\"BUT_TXT_INVOICE_NO_HELP\"+i");
			out.println("     if(document.Form1.TXT_VENDOR_CODE.value!=\"\"){");
			out.println("     document.Form1.elements[m_inv_btn].disabled=false;}");
			out.println("     get_branch_code()");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M56'  && document.Form1.TXT_VENDOR_CODE.value!=\"\"){");
			out.println("     help_button_2('1','10','1','m_help_SUPPLIER_PURCHASE_ORDER','2');");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M72'  && document.Form1.TXT_BRANCH_CODE.value!=\"\"){");
			out.println("     document.Form1.TXT_BRANCH_CODE.value=data_vec[0];"); 
			out.println("     document.Form1.TXT_BRANCH_NAME.value=data_vec[1];"); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M72'  && document.Form1.TXT_BRANCH_CODE.value!=\"\"){");
			out.println("     help_button_branch_pur('1','10','0','m_help_BRANCH_CODE_PURCHASE_ORDER','4');");
			out.println("			}");
			out.println("			else");//Added By Sandun on 28-04-2009
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M_IRR'  && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("     document.Form1.HID_IRR.value=data_vec[0];");
			//out.println("			alert(document.Form1.HID_IRR.value);");
			out.println("			}");
			
			
			//*******************************************************************************************************************************************************************************************************
			//**MODIFIED BY :DELANJALI
			//**DATE				:02-07-2007
			/* out.println("			if(data_vec.length!=1 && document.Form1.hid_chk_status.value=='J' && document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("alert('Cannot enter finance no')");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true");
			out.println("			}");
			out.println("			if(data_vec[0]=='@j@' && document.Form1.hid_chk_status.value=='J' && document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true");
			out.println("			}");*/
			//*******************************************************************************************************************************************************************************************************
			//out.println("window.open(m_url);");
			
			
			// added by udara 28-03-2014
			
			out.println(" if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M30'  && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			//out.println("     alert(data_vec[0]);");
			//out.println("     save_window(); ");
			
			
			out.println("    if(data_vec[0]>0){ ");
			//out.println("        alert('Insurance details are already entered from Insurance Module');");
			
			//out.println("        save_window(); "); // commented by udara 16-03-2015
			
			// added by udara 16-03-2015
			out.println("          if(document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("               alert('Cannot delete PO, Insurance details are already entered from Insurance Module.');");
			out.println("          } ");
			out.println("          else{ ");
			out.println("             save_window(); "); 
			out.println("          } ");
			// end by udara 16-03-2015
				
			out.println("    }");
			out.println("    else{");
			out.println("        alert('Insurance details are not entered');");
			out.println("        save_window(); ");
			out.println("    }");
			
			out.println(" }");
			
			// end by udara 28-03-2014
			
			
			
			out.println("}");
			
			
			out.println("function get_branch_code() {");
			
			out.println("assignState('M15');"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_BRANCH_ID&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val2=\"+document.Form1.TXT_VENDOR_CODE.value;");
			//	out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}"); 
			
			out.println("function get_doc_entity_apllication(){");
			out.println("			assignState('M20');"); 
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_documents_entity_app_no&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url);");
			out.println("			load_interface(m_url,'XML');");					
			out.println("}"); 
			
			out.println("function get_amount_to_be_charged(){");
			out.println("			assignState('M_CHARGE');"); 
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_other_charges&app_no=\"+document.Form1.TXT_APPLICATION_NO.value+\"&payment_type="+m_Payement_Mode+"&ac_status=Y\";");
			//	out.println("window.open(m_url);");
			out.println("			load_interface(m_url,'XML');");					
			out.println("}"); 
			
			out.println("function get_IRR(){");//Added By Sandun on 28-04-2009
			out.println("			assignState('M_IRR');"); 
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_AF_CR_PRO_IRR_validation&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"\";");
			//out.println("window.open(m_url);");
			out.println("			load_interface(m_url,'XML');");					
			out.println("}"); 
			
			
			
			
			
			
			out.println("function show_invoice_no_doc(data_vec_inv) {");
			
			out.println("if(b_chk_state_val==0){");
			out.println("lineno=0;");
			out.println("arr_size=0;");
			out.println("m_table.innerHTML=\"\"");
			out.println("header();");
			out.println("}");
			
			out.println("var i=0;");
			
			out.println("while(i<data_vec_inv.length){");
			
			
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" size=\"50\" value='+data_vec_inv[i]+' onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value='+data_vec_inv[i+1]+' size=\"10\" STYLE=\"{text-align:right;}\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+data_vec_inv[i+2]+' size=\"10\" STYLE=\"{text-align:right;}\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\" size=\"10\" value='+data_vec_inv[i+3]+' STYLE=\"{text-align:right;}\" >'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\">'+");
			out.println("'</td></tr></table>';");
			
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			out.println("i=i+4;");
			out.println("}");
			
			out.println("get_doc();");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("m_inv_no=\"TXT_INVOICE_NO\"+document.Form1.hid_row_no.value");
			
			out.println("if(document.Form1.hid_chk_status.value=='M1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_validate&data_val=\"+obj.value+\"&ac_status=VERIFY&ac_status2=Y\";");
			out.println("else if(document.Form1.hid_chk_status.value=='M2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_CRO_PRO_Application_no_validation&data_val=\"+obj.value+\"&ac_status=VERIFYL&ac_status2=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M7')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M56')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_supplier&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val2=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M72')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_branch_code&data_val=\"+document.Form1.TXT_VENDOR_CODE.value+\"&data_val2=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val3=\"+obj.value+\"&ac_status=Y\";");
			
			
			//**MODIFIED BY :DELANJALI*******************************************************************************************************************************************************************************
			//**DATE				:02-07-2007
			//*******************************************************************************************************************************************************************************************************
			//out.println("else if(document.Form1.hid_chk_status.value=='J')");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_finance&data_val1=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val=\"+obj.value;");
			//*******************************************************************************************************************************************************************************************************
			
			//out.println("window.open(m_url);")	;
			//		out.println("window.open(m_url)");	
			
			out.println("load_interface(m_url,'XML');");
			
			
			out.println("}");
			
			
			
			
			
			
			
			
			out.println("function cal_values(){");
			out.println("sum_vat=0;");
			out.println("sum_net=0;");
			out.println("for(var i=0;i<arr_size;i++){");			
			out.println("m_vat=\"TXT_VAT\"+i");
			out.println("m_net=\"TXT_NET\"+i");
			
			out.println("if(document.Form1.elements[m_vat].value!=\"\")");
			out.println("sum_vat=sum_vat+parseFloat(unformat_number(document.Form1.elements[m_vat]));");
			out.println("if(document.Form1.elements[m_net].value!=\"\")");
			out.println("sum_net=sum_net+parseFloat(unformat_number(document.Form1.elements[m_net]));");
			out.println("}");			
			
			
			out.println("document.Form1.hid_TXT_SUM_VAT.value=sum_vat;");		
			out.println("document.Form1.hid_TXT_SUM_NET.value=sum_net;");		
			//		out.println("alert('sum_vat' +document.Form1.hid_TXT_SUM_VAT.value)");
			//	out.println("alert('sum_net' +document.Form1.hid_TXT_SUM_NET.value)");
			//out.println("format_number2(document.Form1.TXT_TOT,25);");
			
			
			out.println("}");
			
			
			out.println("function chk_data(){");
			///	 out.println("alert('chk');");
			out.println("b_flag=0;");
			out.println("b_flag_on=0;");	
			
			out.println("flag2=0; "); 
			out.println("flag3=0; "); 
			out.println("flag4=0; "); 
			
			out.println("count1=0; "); 
			out.println("count2=0; "); 
			out.println("count3=0; "); 
			out.println("b_flag_charge=0; "); 
			
			
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
			
			
			out.println("for(var i=0;i<lineno;i++){");
			
			out.println("m_inv_no=\"TXT_INVOICE_NO\"+i");
			out.println("m_engine_no=\"TXT_ENGINE_NO\"+i");
			out.println("m_chassiss_no=\"TXT_CHASSISS_NO\"+i");
			
			
			out.println("if(document.Form1.elements[m_inv_no].value==\"\") {");
			out.println("alert('Please select an invoice number');");
			out.println("b_flag=1;");
			out.println("break;");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_engine_no].value==\"\") {");
			out.println("alert('Please enter a engine/serial number');");
			out.println("b_flag=1;");
			out.println("}");						
			
			out.println("else if(document.Form1.elements[m_chassiss_no].value==\"\") {");
			out.println("alert('Please enter a chassiss Number ');");
			out.println("b_flag=1;");
			out.println("}");		
			
			out.println("}");
			
			
			out.println("for(var i=0;i<arr_size_doc;i++){");
			
			out.println("m_not_applicable=\"CHK_NOT_APPLICABLE\"+i");
			out.println("m_required=\"CHK_REQUIRED\"+i");
			out.println("m_hid_doc=\"hid_DOC_POS\"+i");
			
			out.println("if(document.Form1.elements[m_required].checked==false && document.Form1.elements[m_not_applicable].checked==false ){");
			
			out.println("if(document.Form1.elements[m_hid_doc].value=="+m_scr_position+"){");
			out.println("flag4=1;");
			out.println("break;");
			out.println("}");
			
			out.println("}");
			
			out.println("}");
			
			
			out.println("for(var i=0;i<arr_size_doc_entity;i++){");
			
			//		out.println("alert('size'+arr_size_doc_entity);");
			
			//	out.println("alert('i'+i);");
			
			out.println("m_not_applicable_entity=\"CHK_NOT_APPLICABLE_ENTITY\"+i");
			out.println("m_required_entity=\"CHK_REQUIRED_ENTITY\"+i");
			out.println("m_hid_doc_entity=\"hid_DOC_POS_ENTITY\"+i");
			
			//	out.println("alert('val req'+document.Form1.elements[m_required].value);");
			//out.println("alert('val not'+document.Form1.elements[m_not_applicable].value);");
			//out.println("alert('val hid'+document.Form1.elements[m_hid_doc_entity].value);");
			
			out.println("if(document.Form1.elements[m_required_entity].checked==false && document.Form1.elements[m_not_applicable_entity].checked==false ){");
			
			out.println("if(document.Form1.elements[m_hid_doc_entity].value=="+m_scr_position+"){");
			out.println("count3=1;");
			out.println("break;");
			out.println("}");
			
			out.println("}");
			
			out.println("}");
			
			
			
			
			out.println("for(var i=0;i<arr_size_doc;i++){");
			
			out.println("m_chk_status_fol=\"CHK_FOLLOW_UP_DOC\"+i");
			out.println("m_remark_fol=\"TXT_FOLLOW_UP_REMARK_DOC\"+i");
			
			out.println("m_chk_status=\"CHK_REQUIRED\"+i");
			out.println("m_chk_not_applicable=\"CHK_NOT_APPLICABLE\"+i");
			
			
			out.println("if(document.Form1.elements[m_chk_status_fol].checked==true && (document.Form1.elements[m_remark_fol].value=='-' || document.Form1.elements[m_remark_fol].value=='')){");
			out.println("flag2=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false && document.Form1.elements[m_chk_not_applicable].checked==false && document.Form1.elements[m_chk_status_fol].checked==false){");
			out.println("count1=1; "); 
			out.println("} "); 
			
			out.println("} "); 
			
			
			out.println("for(i=0;i<arr_size_doc_entity;i++){");
			
			out.println("m_chk_status_fol_entity=\"CHK_FOLLOW_UP_ENTITY\"+i");
			out.println("m_remark_fol_entity=\"TXT_FOLLOW_UP_REMARK_ENTITY\"+i");
			
			out.println("m_chk_status_entity=\"CHK_REQUIRED_ENTITY\"+i");
			out.println("m_chk_not_applicable_entity=\"CHK_NOT_APPLICABLE_ENTITY\"+i");
			
			
			out.println("if(document.Form1.elements[m_chk_status_fol_entity].checked==true && (document.Form1.elements[m_remark_fol_entity].value=='-' || document.Form1.elements[m_remark_fol_entity].value=='')){");
			out.println("flag3=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status_entity].checked==false && document.Form1.elements[m_chk_not_applicable_entity].checked==false && document.Form1.elements[m_chk_status_fol_entity].checked==false){");
			out.println("count2=1; "); 
			out.println("} "); 
			
			
			out.println("} "); 
			
			out.println("if(parseFloat(unformat_noobject(document.Form1.TXT_OTHER_CHATGES.value))>0) {");
			out.println("b_flag_charge=1");
			//out.println("alert('test');");
			out.println("}");
			
			
			
			
			out.println("}");
			
			out.println("}");	
			
			/*---------------------------------------------------
			
			Purpose : This Function Used To Add a New Row
			
			-----------------------------------------------------*/
			
			out.println("function add_row(){"); 
			
			out.println("b_flag=0;");
			out.println("count=lineno;");
			
			out.println("if(count!=0){");
			out.println("count=count-1;");
			out.println("m_inv_no=\"TXT_INVOICE_NO\"+count");
			out.println("m_engine_no=\"TXT_ENGINE_NO\"+count");
			out.println("m_chassiss_no=\"TXT_CHASSISS_NO\"+count");
			
			out.println("if(document.Form1.elements[m_inv_no].value==\"\") {");
			out.println("alert('Please select an invoice number');");
			out.println("b_flag=1;");
			out.println("}");						
			
			out.println("else if(document.Form1.elements[m_engine_no].value==\"\") {");
			out.println("alert('Please enter a engine/serial number');");
			out.println("b_flag=1;");
			out.println("}");						
			
			out.println("else if(document.Form1.elements[m_chassiss_no].value==\"\") {");
			out.println("alert('Please enter a chassiss Number ');");
			out.println("b_flag=1;");
			out.println("}");						
			
			
			out.println("else{");
			out.println("b_count=0;");
			out.println("tmp_inv_no=document.Form1.elements[m_inv_no].value;");
			
			out.println("for(var i=0;i<count;i++){");
			
			out.println("m_tmp_inv_no=\"TXT_INVOICE_NO\"+i");
			out.println("if(lineno>=2){");
			out.println("if(document.Form1.elements[m_tmp_inv_no].value==tmp_inv_no){");
			out.println("alert('Invoice Number Can not Be Duplicated')");
			out.println("b_flag=1;");
			out.println("b_count=1};");
			
			out.println("if(b_count==1){");
			out.println("break;}");
			
			out.println("}");
			
			out.println("}");
			
			out.println("}");
			
			out.println("}");
			
			out.println("if(b_flag==0){");
			
			out.println("m_table.innerHTML+='<table border=\"0\" align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' style=\"width:120px;\" maxlength=\"50\" size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+'   style=\"width:100px;\" maxlength=\"50\" size=\"10\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+'     style=\"width:100px;\"   maxlength=\"50\" size=\"10\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+'   style=\"width:100px;\" maxlength=\"24\" onblur=\"check_number_decimal(this,20)\" size=\"10\" STYLE=\"{text-align:right;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+'     style=\"width:100px;\"   maxlength=\"50\" size=\"10\" STYLE=\"{text-align:right;}\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+'     style=\"width:100px;\"   maxlength=\"50\" size=\"10\" STYLE=\"{text-align:right;}\" disabled >&nbsp;</TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' style=\"width:75px;\" maxlength=\"6\" size=\"10\" onblur=\"check_number_precent(this,6)\" STYLE=\"{text-align:right;}\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"0\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"0\"></TD>'+");
			out.println("'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			
			out.println("'</td></tr></table>';");
			
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			
			out.println("}");
			
			//end of add row-----------------------------------------------------*/
			
			
			
			out.println("disable_fields(arr_size)");
			out.println("cal_values();");
			out.println("}");
			
			
			out.println("function del_row(rowNo){"); 
			
			out.println("if(rowNo!=0){");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("m_invoice=\"TXT_INVOICE_NO\"+i");
			out.println("m_gross=\"TXT_GROSS\"+i");
			out.println("m_vat=\"TXT_VAT\"+i");
			out.println("m_net=\"TXT_NET\"+i");
			out.println("m_capital=\"TXT_CAPITAL\"+i");
			out.println("m_engine_no=\"TXT_ENGINE_NO\"+i");
			out.println("m_chassiss_no=\"TXT_CHASSISS_NO\"+i");
			out.println("m_asset_id=\"hid_TXT_ASSET_ID\"+i");
			
			out.println("if(i==rowNo)");
			out.println("continue;");
			
			out.println("array_invoice[j]=document.Form1.elements[m_invoice].value;");
			out.println("array_gross[j]=document.Form1.elements[m_gross].value;");
			out.println("array_vat[j]=document.Form1.elements[m_vat].value;");
			out.println("array_net[j]=document.Form1.elements[m_net].value;");
			out.println("array_capital[j]=document.Form1.elements[m_capital].value;");			   
			//out.println("array_engine_no[j]=document.Form1.elements[m_engine_no].value;");			   
			//out.println("array_chassiss_no[j]=document.Form1.elements[m_chassiss_no].value;");			   
			
			out.println("if(document.Form1.elements[m_engine_no].value==''){");
			
			out.println("array_engine_no[j]='';");
			out.println("}");
			out.println("else{");
			out.println("array_engine_no[j]=document.Form1.elements[m_engine_no].value;");
			out.println("}");
			
			out.println("if(document.Form1.elements[m_chassiss_no].value==''){");
			out.println("array_chassiss_no[j]='';");
			out.println("}");
			out.println("else{");
			out.println("array_chassiss_no[j]=document.Form1.elements[m_chassiss_no].value;");
			out.println("}");
			
			out.println("array_asset_id[j]=document.Form1.elements[m_asset_id].value;");			   
			
			out.println("j=j+1;");
			
			out.println("}");
			
			
			out.println("for(var i=0;i<arr_size_doc;i++){");
			out.println("m_code=\"hid_TXT_DOC_CODE\"+i");
			out.println("m_desc=\"hid_TXT_DESCRIPTION\"+i");
			out.println("m_remark=\"TXT_REMARK\"+i");
			out.println("m_required=\"CHK_REQUIRED\"+i");
			out.println("m_inv=\"hid_TXT_INV_NO\"+i");
			out.println("m_prv_status=\"hid_TXT_PRV_STATUS\"+i");
			out.println("m_prv_remark=\"hid_TXT_PRV_REMARK\"+i");
			out.println("m_not_applicable=\"CHK_NOT_APPLICABLE\"+i");
			out.println("m_chk_status_fol=\"CHK_FOLLOW_UP_DOC\"+i");
			out.println("m_remark_fol=\"TXT_FOLLOW_UP_REMARK_DOC\"+i");
			out.println("m_hid_pos=\"hid_DOC_POS\"+i");
			
			
			
			
			
			//out.println("if(i==rowNo)");
			//out.println("continue;");
			
			out.println("array_code[i]=document.Form1.elements[m_code].value;");
			out.println("array_desc[i]=document.Form1.elements[m_desc].value;");
			
			out.println("array_remark[i]=document.Form1.elements[m_remark].value;");
			
			out.println("if(document.Form1.elements[m_required].value==\"on\"){");
			out.println("array_required[i]=\"on\"");
			out.println("}");
			out.println("else if(document.Form1.elements[m_required].value==\"off\"){");
			out.println("array_required[i]=\"off\"");
			out.println("}");
			
			out.println("array_inv[i]=document.Form1.elements[m_inv].value;");		
			
			
			out.println("array_prv_status[i]=document.Form1.elements[m_prv_status].value;");
			
			out.println("array_prv_remark[i]=document.Form1.elements[m_prv_remark].value;");
			
			out.println("if(document.Form1.elements[m_not_applicable].value==\"on\"){");
			out.println("array_not_applicable[i]=\"on\"");
			out.println("}");
			out.println("else if(document.Form1.elements[m_not_applicable].value==\"off\"){");
			out.println("array_not_applicable[i]=\"off\"");
			out.println("}");
			
			out.println("array_remark_fol[i]=document.Form1.elements[m_remark_fol].value;");
			
			out.println("if(document.Form1.elements[m_chk_status_fol].value==\"on\"){");
			out.println("array_status_fol[i]=\"on\"");
			//out.println("alert('value'+array_status_fol[i]);");
			out.println("}");
			out.println("else if(document.Form1.elements[m_chk_status_fol].value==\"off\"){");
			out.println("array_status_fol[i]=\"off\"");
			//out.println("alert('value'+array_status_fol[i]);");
			out.println("}");
			
			
			out.println("array_hid_pos[i]=document.Form1.elements[m_hid_pos].value;");
			
			
			out.println("}");
			
			out.println("arr_size=arr_size-1;");
			out.println("line_doc=0;");
			out.println("arr_size_doc_temp=arr_size_doc;");
			out.println("arr_size_doc=0;");
			out.println("lineno=0;");
			out.println("del_row_count=0;");
			out.println("b_chk_state_val=0;");
			
			out.println("write_data_new();");
			out.println("}"); 
			
			out.println("}"); 
			
			
			out.println("function del_row_assign(rowNo){"); 
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("m_invoice=\"TXT_INVOICE_NO\"+i");
			out.println("m_gross=\"TXT_GROSS\"+i");
			out.println("m_vat=\"TXT_VAT\"+i");
			out.println("m_net=\"TXT_NET\"+i");
			out.println("m_capital=\"TXT_CAPITAL\"+i");
			out.println("m_engine_no=\"TXT_ENGINE_NO\"+i");
			out.println("m_chassiss_no=\"TXT_CHASSISS_NO\"+i");
			out.println("m_asset_id=\"hid_TXT_ASSET_ID\"+i");
			out.println("if(j==0 && m_count>0 && parseFloat(unformat_number(document.Form1.hid_invoice_amount))>0 ){");
			out.println("document.Form1.hid_new_value_invoice.value=parseFloat(unformat_number(document.Form1.elements[m_gross]))+parseFloat(unformat_number(document.Form1.hid_invoice_amount));");
			out.println("format_number(document.Form1.hid_new_value_invoice,20)");
			out.println("array_gross[j]=document.Form1.hid_new_value_invoice.value");
			out.println("}");
			out.println("else {");
			out.println("array_gross[j]=document.Form1.elements[m_gross].value;");
			out.println("}");
			out.println("array_invoice[j]=document.Form1.elements[m_invoice].value;");
			// out.println("array_gross[j]=document.Form1.elements[m_gross].value;");
			out.println("array_vat[j]=document.Form1.elements[m_vat].value;");
			out.println("array_net[j]=document.Form1.elements[m_net].value;");
			out.println("array_capital[j]=document.Form1.elements[m_capital].value;");
			out.println("if(document.Form1.elements[m_engine_no].value==''){");
			out.println("array_engine_no[j]='';");
			out.println("}");
			out.println("else{");
			out.println("array_engine_no[j]=document.Form1.elements[m_engine_no].value;");
			out.println("}");
			out.println("if(document.Form1.elements[m_chassiss_no].value==''){");
			out.println("array_chassiss_no[j]='';");
			out.println("}");
			out.println("else{");
			out.println("array_chassiss_no[j]=document.Form1.elements[m_chassiss_no].value;");
			out.println("}");
			out.println("array_asset_id[j]=document.Form1.elements[m_asset_id].value;");			   
			out.println("j=j+1;");
			out.println("}");
			out.println("for(var i=0;i<arr_size_doc;i++){");
			out.println("m_code=\"hid_TXT_DOC_CODE\"+i");
			out.println("m_desc=\"hid_TXT_DESCRIPTION\"+i");
			out.println("m_remark=\"TXT_REMARK\"+i");
			out.println("m_required=\"CHK_REQUIRED\"+i");
			out.println("m_inv=\"hid_TXT_INV_NO\"+i");
			out.println("m_prv_status=\"hid_TXT_PRV_STATUS\"+i");
			out.println("m_prv_remark=\"hid_TXT_PRV_REMARK\"+i");
			out.println("m_not_applicable=\"CHK_NOT_APPLICABLE\"+i");
			out.println("m_chk_status_fol=\"CHK_FOLLOW_UP_DOC\"+i");
			out.println("m_remark_fol=\"TXT_FOLLOW_UP_REMARK_DOC\"+i");
			out.println("m_hid_pos=\"hid_DOC_POS\"+i");
			
			out.println("array_inv[i]=document.Form1.elements[m_inv].value;");		
			out.println("array_hid_pos[i]=document.Form1.elements[m_hid_pos].value;");		
			out.println("array_code[i]=document.Form1.elements[m_code].value;");
			out.println("array_desc[i]=document.Form1.elements[m_desc].value;");
			out.println("array_remark[i]=document.Form1.elements[m_remark].value;");
			out.println("if(document.Form1.elements[m_required].value==\"on\"){");
			out.println("array_required[i]=\"on\"");
			out.println("}");
			out.println("else if(document.Form1.elements[m_required].value==\"off\"){");
			out.println("array_required[i]=\"off\"");
			out.println("}");
			out.println("array_prv_status[i]=document.Form1.elements[m_prv_status].value;");
			out.println("array_prv_remark[i]=document.Form1.elements[m_prv_remark].value;");
			out.println("if(document.Form1.elements[m_not_applicable].value==\"on\"){");
			out.println("array_not_applicable[i]=\"on\"");
			out.println("}");
			out.println("else if(document.Form1.elements[m_not_applicable].value==\"off\"){");
			out.println("array_not_applicable[i]=\"off\"");
			out.println("}");
			out.println("array_remark_fol[i]=document.Form1.elements[m_remark_fol].value;");
			out.println("if(document.Form1.elements[m_chk_status_fol].value==\"on\"){");
			out.println("array_status_fol[i]=\"on\"");
			//out.println("alert('value'+array_status_fol[i]);");
			out.println("}");
			out.println("else if(document.Form1.elements[m_chk_status_fol].value==\"off\"){");
			out.println("array_status_fol[i]=\"off\"");
			//out.println("alert('value'+array_status_fol[i]);");
			out.println("}");
			out.println("}");
			out.println("line_doc=0;");
			out.println("arr_size_doc_temp=arr_size_doc;");
			out.println("arr_size_doc=0;");
			out.println("lineno=0;");
			out.println("del_row_count=0;");
			out.println("b_chk_state_val=0;");
			out.println("}"); 
			
			
			
			//*********************************************************************************************************
			out.println("function write_data_new(){");
			out.println("if(b_chk_state_val==0){");
			out.println("lineno=0;");
			out.println("m_table.innerHTML=\"\"");
			out.println("header();");
			out.println("}");
			
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("if(array_invoice[i]==\"\"){");
			/*out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value=\"\" size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value=\"\"  size=\"10\" STYLE=\"{text-align:right;width:150px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{text-align:right; width:150px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value=\"\" size=\"10\" STYLE=\"{text-align:right; width:150px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"20%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"2\"  value=\"\" size=\"10\" STYLE=\"{text-align:right;width:100px;}\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+array_invoice[lineno]+'\">'+");
			out.println("'</td></tr></table>';");
			*/
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+document.Form1.hid_inv_no.value+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"24\" onblur=\"check_number_decimal(this,20)\" value=\"\"  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value=\"\" size=\"10\" STYLE=\"{text-align:right;width:100px;}\">&nbsp;</TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"6\"  onblur=\"check_number_precent(this,6)\" value=\"\" size=\"10\" STYLE=\"{text-align:right; width:75px;}\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+array_invoice[lineno]+'\"></TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"\"></TD>'+");
			out.println("'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			out.println("'</tr></table>';");	
			out.println("lineno=lineno+1;");
			out.println("continue;");
			out.println("}");
			/*	out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+document.Form1.hid_inv_no.value+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"10\" value='+array_engine_no[lineno]+'  size=\"10\" STYLE=\"{width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value='+array_chassiss_no[lineno]+'  size=\"10\" STYLE=\"{width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value='+array_gross[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+array_vat[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value='+array_net[lineno]+' size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"2\"  value='+array_capital[lineno]+' size=\"10\" STYLE=\"{text-align:right; width:50px;}\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+array_invoice[lineno]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"'+array_asset_id[lineno]+'\"></TD>'+");
			out.println("'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			out.println("'</tr></table>';");	
			*/
			out.println("m_inv='<TD WIDTH=\"20%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+document.Form1.hid_inv_no.value+' STYLE=\"{width:120px;}\"size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("      '<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>';");
			out.println("if(array_engine_no[lineno]==\"\" || array_chassiss_no[lineno]==\"\" ){");
			out.println("m_eng_no='<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>'+");
			out.println("         '<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>';");
			out.println("}");
			out.println("else {");
			out.println("m_eng_no='<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ENGINE_NO'+lineno+' maxlength=\"50\" value=\"'+array_engine_no[lineno]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>'+");
			out.println("          '<TD WIDTH=\"12.5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CHASSISS_NO'+lineno+' maxlength=\"50\" value=\"'+array_chassiss_no[lineno]+'\"  size=\"10\" STYLE=\"{width:100px;}\" disabled></TD>';");
			out.println("}");
			out.println("m_data='<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"10\" value='+array_gross[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+array_vat[lineno]+'  size=\"10\" STYLE=\"{text-align:right;width:100px;}\"></TD>'+");
			out.println("				'<TD WIDTH=\"12.5%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value='+array_net[lineno]+' size=\"10\" STYLE=\"{text-align:right;width:100px;}\">&nbsp;</TD>'+");
			out.println("				'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CAPITAL'+lineno+' maxlength=\"6\"  onblur=\"check_number_precent(this,6)\" value='+array_capital[lineno]+' size=\"10\" STYLE=\"{text-align:right; width:75px;}\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INVOICE_NUMBER'+lineno+'	VALUE=\"'+array_invoice[lineno]+'\">'+");
			out.println("				'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_ASSET_ID'+lineno+'	VALUE=\"'+array_asset_id[lineno]+'\"></TD>'+");
			out.println("				'<TD WIDTH=\"7.5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></TD>';");
			out.println("m_writedata='<TR>'+m_inv+m_eng_no+m_data+'</TR>';");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			out.println("document.Form1.hid_inv_no.value=array_invoice[lineno];");
			out.println("doc(array_invoice[lineno]);");
			out.println("lineno=lineno+1;");
			out.println("}"); 	    
			out.println("cal_values();");
			out.println("disable_fields(arr_size)");
			out.println("}"); 
			
			out.println("function doc(inv_no){");
			out.println("var count_del=0;");
			//out.println("b_state=0;");
			out.println("header_doc();	");		
			
			out.println("for(var i=0;i<arr_size_doc_temp;i++){");
			out.println("b_state=0;");
			out.println("if(array_inv[i]==inv_no){");
			out.println("m_description='<TD WIDTH=\"20%\">'+array_desc[i]+'</TD>';");
			
			out.println("if(array_prv_remark[i]==\"\")");
			out.println("m_prv_remark='<TD WIDTH=\"10%\">-</TD>';");	
			
			out.println("else");
			out.println("m_prv_remark='<TD WIDTH=\"10%\">'+array_prv_remark[i]+'</TD>';");	
			
			out.println("if(array_prv_status[i]==\"Y\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">Yes</TD>';");	
			
			out.println("else if(array_prv_status[i]==\"N\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">No</TD>';");	
			
			out.println("else if(array_prv_status[i]==\"A\"){");
			out.println("m_prv_status='<TD WIDTH=\"10%\">Not Applicable</TD>';");	
			out.println("b_state=1;");
			out.println("count_del=count_del+1;");
			out.println("}");
			
			out.println("else");// if(array_prv_status[i]==\"-\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");
			
			out.println("if(array_required[i]==\"on\")");
			out.println("m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+line_doc+' VALUE=\"on\"  checked onclick=\"change_val_req('+line_doc+')\"></td>';");			
			out.println("else if(array_required[i]==\"off\")");
			out.println("m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+line_doc+' VALUE=\"off\"  onclick=\"change_val_req('+line_doc+')\"></td>';");			
			
			
			out.println("if(array_not_applicable[i]==\"on\")");
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE'+line_doc+' VALUE=\"on\" checked onclick=\"change_val_not_app('+line_doc+')\"></td>';");			
			out.println("else if(array_not_applicable[i]==\"off\")");
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE'+line_doc+' VALUE=\"off\" onclick=\"change_val_not_app('+line_doc+')\"></td>';");			
			
			out.println("m_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK'+line_doc+' VALUE=\"'+array_remark[i]+'\" maxlength=\"50\" size=\"20\"></td>';");		
			
			//out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_DOC'+line_doc+' VALUE=\"off\" onclick=\"change_val_fol_up_doc('+line_doc+')\"></td>';");			
			
			out.println("if(array_status_fol[i]==\"on\")");
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_DOC'+line_doc+' VALUE=\"on\" checked onclick=\"change_val_fol_up_doc('+line_doc+')\"></td>';");			
			out.println("else if(array_status_fol[i]==\"off\")");
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_DOC'+line_doc+' VALUE=\"off\" onclick=\"change_val_fol_up_doc('+line_doc+')\"></td>';");			
			
			out.println("m_followup_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_FOLLOW_UP_REMARK_DOC'+line_doc+' VALUE=\"'+array_remark_fol[i]+'\" maxlength=\"50\" size=\"20\"></td>';");		
			
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DOC_CODE'+line_doc+'	VALUE='+array_code[i]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESCRIPTION'+line_doc+'	VALUE=\"'+array_desc[i]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_REMARK'+line_doc+'	VALUE=\"'+array_prv_remark[i]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOC_POS'+line_doc+'	VALUE=\"'+array_hid_pos[i]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_STATUS'+line_doc+'	VALUE=\"'+array_prv_status[i]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INV_NO'+line_doc+'	VALUE='+document.Form1.hid_inv_no.value+'>';");
			
			out.println("if(count_del==1 && b_state==1){");
			out.println("m_error='<TR STYLE=\"{color:red;}\"><TD WIDTH=\"100%\"><b>System Exceptions</TD></TR>';");			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" >'+");
			out.println("m_error+'</table>';");
			out.println("}");
			out.println("if(b_state==1){");	
			out.println("m_writedata='<TR STYLE=\"{color:red;}\">'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;");
			//out.println("m_writedata='<TR STYLE=\"{color:red;}\">'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;");
			//out.println("array_doc[line_doc]=m_writedata;");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"  bordercolor=\"black\" border=\"1\" cellspacing=\"0\" >'+");
			out.println("m_writedata+'</table>';");
			out.println("}");
			out.println("else");
			out.println("{");
			out.println("m_writedata='<TR>'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println("m_writedata+'</table>';");
			out.println("}");
			//out.println("m_writedata='<TR>'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;");
			//out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			//   out.println("m_writedata+'</table>';");
			out.println("count_del=count_del+1;");
			out.println("line_doc=line_doc+1;");
			out.println("arr_size_doc=arr_size_doc+1;");		
			out.println("}");
			out.println("}");
			out.println("}");
			
			
			out.println("function write_new_data_from_data_vec(data_vec,rowNo){");
			
			out.println("m_inv_no=\"hid_TXT_INVOICE_NUMBER\"+rowNo;");
			
			out.println("header_doc();	");		
			
			out.println("for(var i=0;i<arr_size_doc_temp;i++){");
			
			out.println("m_description='<TD WIDTH=\"30%\">'+data_vec[i+1]+'</TD>';");
			out.println("m_prv_remark='<TD WIDTH=\"20%\">'+array_prv_remark[i]+'</TD>';");	
			
			out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");
			out.println("m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+line_doc+' VALUE=\"off\"  onclick=\"change_val_req('+line_doc+')\"></td>';");			
			
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE'+line_doc+' VALUE=\"off\" onclick=\"change_val_not_app('+line_doc+')\"></td>';");			
			
			
			out.println("m_remark='<TD WIDTH=\"20%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK'+line_doc+' VALUE=\"\" maxlength=\"50\" size=\"20\"></td>';");		
			
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DOC_CODE'+line_doc+'	VALUE='+data_vec[i]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESCRIPTION'+line_doc+'	VALUE=\"'+data_vec[i]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_REMARK'+line_doc+'	VALUE=\"'+data_vec[i]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_STATUS'+line_doc+'	VALUE=\"'+data_vec[i]+'\">'+");
			//out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOC_POS'+line_doc+'	VALUE=\"'+array_hid_pos[i]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INV_NO'+line_doc+'	VALUE='+document.Form1.hid_inv_no.value+'>';");
			
			
			out.println("m_writedata='<TR>'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+'</TR>'+m_hid_input;");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println("m_writedata+'</table>';");
			
			out.println("line_doc=line_doc+1;");
			out.println("arr_size_doc=arr_size_doc+1;");		
			
			out.println("}");
			
			
			out.println("}");
			
			//*****************************************************8
			
			
			
			out.println("function write_data2(){");
			out.println("assignState('M11');"); 
			
			out.println("while(del_row_count<arr_size){");
			
			//	out.println("alert('invoice no'+array_invoice[del_row_count]);");
			
			
			out.println("document.Form1.hid_inv_no.value=array_invoice[del_row_count];");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_edit_doc&data_val=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&data_val2=\"+array_invoice[del_row_count];");
			
			
			//    out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("del_row_count=del_row_count+1;");
			
			
			out.println("}"); 
			
			out.println("}"); 
			
			
			
			out.println("function write_data(data_vec_doc){");
			
			
			out.println("if(b_chk_state_val==0){");
			out.println("lineno=0;");
			out.println("m_table.innerHTML=\"\"");
			out.println("header();");
			out.println("}");
			
			
			
			out.println("if(data_vec_doc.length==0){");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" value='+array_invoice[lineno]+' size=\"50\" onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"24\" onblur=\"check_number_decimal(this,20)\" value='+array_gross[lineno]+'  size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+array_vat[lineno]+'  size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\"  value='+array_net[lineno]+' size=\"10\" >'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\">'+");
			out.println("'</td></tr></table>';");
			
			
			out.println("}");
			
			
			
			
			out.println("else if(data_vec_doc.length>0){");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_INVOICE_NO'+lineno+' maxlength=\"50\" size=\"50\" value='+array_invoice[lineno]+' onblur=\"validate_invoice_number('+lineno+')\">'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+lineno+' maxlength=\"24\" onblur=\"check_number_decimal(this,20)\" value='+array_gross[lineno]+' size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+lineno+' maxlength=\"50\" value='+array_vat[lineno]+' size=\"10\"></TD>'+");
			out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+lineno+' maxlength=\"2\" size=\"10\" value='+array_net[lineno]+'  >'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\">'+");
			
			out.println("'</td></tr></table>';");
			
			
			out.println("display_documents2(data_vec_doc);");
			
			
			
			
			out.println("}");
			
			out.println("lineno=lineno+1;");	
			out.println("write_data2();");
			
			
			out.println("}");		
			
			
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VENDOR_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_VENDOR_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_BRANCH_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_BRANCH_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 			    	
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function validate_IRR(){");
			out.println("if(parseFloat(document.Form1.HID_IRR.value) < 0 ){  "); 		//Added By Sandun on 			
			out.println("alert(\"IRR value is negative..!\")");
			out.println("document.Form1.btn_save.disabled=true;");
			out.println("return false;"); 
			out.println("}");
			out.println("else{");
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function get_chk_box_doc_values(){ ");
			out.println("for (var i=0; i < arr_size_doc; i++ ) {");
			out.println("m_chk_required=\"CHK_REQUIRED\"+i;");
			// out.println("alert('vlaue'+document.Form1.elements[m_chk_required].value);");
			
			out.println("}"); 
			out.println("}");
			
			//****DELANJALI***************************************************************************************************************************************************************************************************
			//out.println("function check_finance(){");
			//out.println("assignState('J1')");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_invoice&data_val=\"+document.Form1.TXT_APPLICATION_NO.value;");
			//*******************************************************************************************************************************************************************************************************
			//out.println("window.open(m_url);")	;
			//out.println("load_interface(m_url,'XML');");
			//out.println("}");
			
			// added by udara 28-03-2014
			
			out.println("function m_validate_insurance(val){");
			out.println("  assignState('M30');"); 
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_validate_insurance_details&data_val=\"+document.Form1.TXT_APPLICATION_NO.value;");
			out.println("  load_interface(m_url,'XML');");
			out.println("}");
			
			// end by udara 28-03-2014
			
			
			
			out.println("function before_submit(){ "); 
			
			out.println(    "if(validate_data()){");
			//get curr & rate
			out.println("     if(validate_IRR()){ ");
			out.println("       i=0");
			out.println("       assignState('M16');"); 
			out.println("       m_invoice=\"TXT_INVOICE_NO\"+i;");
			out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_curr_code&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val2=\"+document.Form1.elements[m_invoice].value;");
			//out.println("window.open(m_url);");
			out.println("       load_interface(m_url,'XML');");
			
			//----------------------------------------------
			out.println("      chk_data();");
			out.println("      if(b_flag==0)");
			out.println("         if(flag4==0 && count3==0 ){");
			out.println("             if(count1==0 && count2==0 ){");
			out.println("                if(flag2==0 && flag3==0 ){");
			//out.println("                     if(b_flag_charge==0){"); //&& m_count==0  //disnaka
			
			//**MODIFIED BY :DELANJALI*******************************************************************************************************************************************************************************
			//**DATE				:02-07-2007
			//*******************************************************************************************************************************************************************************************************
			//out.println("   check_finance();");
			//*******************************************************************************************************************************************************************************************************
			//*************************************************************************************************************
			//-modifed by : delanjali
			//-date				: 13-02-2007
			//-purpose		: to prevent the PO geting printed if there is a condition with pending status.
			
			out.println("		                    check_foll_con();");
			out.println("	                        if(m_cond_status==1 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("                               alert('Cannot print PO,there is a pending Follow up')");
			out.println("                           }");
			out.println("                           else{");
			
			
			//=nuwan de silva 19-06-07------------------------------------------------------
			out.println("		                        check_condition_approv();"); //check the condtion approve or not
			
			out.println("	                            if(m_check_approv==1 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			out.println("                                     alert('Please check the condtions')");
			out.println("                               }");
			
			out.println("                               else{");
			//*************************************************************************************************************
			
			//	out.println("   document.Form1.hid_no_rec_doc_entity.value=arr_size_doc_entity;");//Added By Nuwan De Silva
			//	out.println("alert('arr_size'+document.Form1.hid_no_rec_doc_entity.value);");
			out.println("		                                  if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			
			out.println("		                                        for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		                                            document.Form1.elements[i].disabled=false;");
			out.println("                                               }");
			
			out.println("                                               document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("                                               document.Form1.hid_no_rec_con.value=arr_size_con;");//Added By Nuwan De Silva
			out.println("                                               document.Form1.hid_no_rec_doc.value=arr_size_doc;");//Added By Nuwan De Silva
			out.println("                                               document.Form1.hid_no_rec_doc_entity.value=arr_size_doc_entity;");//Added By Nuwan De Silva
			out.println("		                                        cal_values();");
			
			out.println("		                                        document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Purchase_Order';");  
			out.println("		                                        document.Form1.submit();	"); 
			out.println("		                                   }"); 
			out.println("		                           }");//end m_cond_status check
			
			out.println("		                        }");//end check status
			
			//out.println("		               }"); 
			
			//out.println("		             else{");  //disnakaa
			//out.println("		                    alert(\"Please pay the amount to be charged before issuing the purchase order\");");  /disnaka
			
			
			//this part added by ashini on 25-09-2007
			//out.println("		if(confirm(\"Are you sure you want to save this data ? \")){ ");   //comment by nuwan de silva on 12-11-07
			
			/**** comment by nuwan de silva on 06-07-2010*/
			/*************************************************
			out.println("		if(confirm(\"Are you sure you want to save this data ? \")){ "); 
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("}");
			
			out.println("   document.Form1.hid_no_rec.value=arr_size;");
			out.println("   document.Form1.hid_no_rec_con.value=arr_size_con;");
			out.println("   document.Form1.hid_no_rec_doc.value=arr_size_doc;");
			out.println("   document.Form1.hid_no_rec_doc_entity.value=arr_size_doc_entity;");
			
			out.println("		cal_values();");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Purchase_Order';");  
			out.println("		document.Form1.submit();	"); 
			
		out.println("		} "); 
			**********************************************/
			
			
			// out.println("		} ");  //comment by nuwan de silva on 12-11-07
			//end modifications done by ashini on 25-09-2007
			
			//out.println("		} "); //disnaka
			
			
			
			out.println("		}"); 
			
			out.println("		else{");
			//modified by madhawa 2009-10-19 changed the msg
			//out.println("		alert(\"Please enter all fields which checked follow up status\");");
			out.println("		alert(\"Please enter follow up remarks for the documents which have to be followed up\");");
			out.println("		} "); 
			
			out.println("		}");
			
			out.println("		else{");
			//modified by madhawa 2009-10-19 changed the msg
			//out.println("		alert(\"Please select 'followup status' if other status not checked\");");
			out.println("		alert(\"Please select Followup status if the document is applicable\");");
			out.println("		} "); 
			
			out.println("		}"); 
			
			out.println("		else{");
			out.println("		alert(\"Please Select The Required Documents\");");
			out.println("		} "); 
			
			out.println("		}"); 
			out.println("		}"); 
			out.println("		else{");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("		} "); 
			
			
			out.println("} "); 
			
			
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order';"); 
			//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order?application_no='+document.Form1.TXT_APPLICATION_NO.value+'';");  
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order?application_no='+document.Form1.TXT_APPLICATION_NO.value+'';");  
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CRO_PRO_PURCHASE_ORDER\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit - Purchase Order - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit - Purchase Order - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window();"); 
			//out.println("document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
			//out.println("document.Form1.TXT_VENDOR_CODE.disabled=false;"); 
			//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("}"); 
			out.println("}"); 
			//	out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;");
			out.println("document.Form1.TXT_PURCHASE_ORDER_NO.disabled=false;");
			
			//out.println(" if(confirm(\"Are you sure you want to Delete a record?\")){  ");
			
			//out.println("disable_controls();");
			out.println("clear_screen();");
			//out.println("load_pur_ord_data();"); 
			out.println("load_roll_value('Delete');"); 
			
			
			//out.println("}"); 
			
			out.println("}"); 
			
			out.println("else{");
			out.println("document.Form1.TXT_PURCHASE_ORDER_NO.disabled=false;"); 
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("}"); 
			
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("document.Form1.BUT_TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_VENDOR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_BRANCH_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_BRANCH_CODE.disabled=true;"); 
			
			
			out.println("}else if(m_val==\"DEL\"){");  
			//out.println("document.Form1.BUT_TXT_APPLICATION_NO.disabled=true;"); 
			//out.println("document.Form1.BUT_TXT_VENDOR_CODE.disabled=true;"); 
			//out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			//out.println("document.Form1.TXT_VENDOR_CODE.disabled=true;"); 
			
			out.println("document.Form1.hid_status.value=\"Delete\";");
			out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function clear_screen(){");
			
			out.println("document.Form1.TXT_APPLICATION_NO.value='';"); 
			out.println("document.Form1.TXT_VENDOR_CODE.value='';"); 
			out.println("document.Form1.TXT_VENDOR_NAME.value='';"); 
			out.println("document.Form1.TXT_BRANCH_CODE.value='';"); 
			
			out.println(""); 
			
			
			out.println("}"); 
			//----modified by : delanjali-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//----date				: 2007-07-16-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("function disable_new(){");	
			out.println("if(m_pur_ord_no1!=\"\" || m_pur_ord_no1!='null'){");
			out.println("document.Form1.btn_new.disabled=true;");
			out.println("document.Form1.btn_delete.disabled=true;");
			out.println("document.Form1.btn_new2.disabled=true;");
			out.println("document.Form1.btn_delete2.disabled=true;");
			
			out.println("document.Form1.TXT_PURCHASE_ORDER_NO.disabled=true;");
			
			out.println("}");
			out.println("if(m_pur_ord_no1==\"\" || m_pur_ord_no1=='null'){");
			out.println("document.Form1.btn_new.disabled=false;");
			out.println("document.Form1.btn_delete.disabled=true;");
			out.println("document.Form1.btn_new2.disabled=false;");
			out.println("document.Form1.btn_delete2.disabled=true;");
			out.println("document.Form1.TXT_PURCHASE_ORDER_NO.disabled=false;"); 
			out.println("}");
			out.println("}");
			
			
			out.println("function disable_controls(){");
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=true;");
			out.println("}");
			
			out.println("document.Form1.btn_save.disabled=false;");
			out.println("document.Form1.btn_help.disabled=false;");
			out.println("document.Form1.btn_cancel.disabled=false;");
			out.println("document.Form1.btn_close.disabled=false;");
			out.println("document.Form1.btn_letter.disabled=false;");
			
			out.println("document.Form1.btn_new2.disabled=false;");
			out.println("document.Form1.btn_delete2.disabled=false;");
			out.println("document.Form1.btn_save2.disabled=false;");
			out.println("document.Form1.btn_help2.disabled=false;");
			out.println("document.Form1.btn_cancel2.disabled=false;");
			out.println("document.Form1.btn_close2.disabled=false;");
			out.println("document.Form1.btn_letter2.disabled=false;");
			
			out.println("document.Form1.TXT_PURCHASE_ORDER_NO.disabled=false;"); 
			//	out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			
			out.println("}"); 
			
			
			out.println("function clear_data(IfCount) {");
			
			out.println("		if(IfCount==\"99\"){"); 
			out.println("document.Form1.TXT_PURCHASE_ORDER_NO.value='';");
			out.println("document.Form1.TXT_PURCHASE_ORDER_NO.focus();");
			out.println("	}");
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("document.Form1.TXT_APPLICATION_NO.value='';"); 
			out.println("document.Form1.TXT_APPLICATION_NO.focus();"); 
			out.println("}");
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("document.Form1.TXT_VENDOR_CODE.value='';");
			out.println("document.Form1.TXT_VENDOR_CODE.focus();");
			out.println("	}");
			
			out.println("		if(IfCount==\"3\"){"); 
			out.println("m_no=\"TXT_INVOICE_NO\"+document.Form1.hid_row_no.value;");
			out.println("document.Form1.elements[m_no].value='';"); 
			//	out.println("document.Form1.elements[m_no].focus();"); 
			out.println("}");
			
			out.println("		if(IfCount==\"4\"){"); 
			out.println("document.Form1.TXT_BRANCH_CODE.value='';"); 
			out.println("document.Form1.TXT_BRANCH_CODE.focus();"); 
			out.println("}");
			
			
			
			
			out.println("}");
			
			
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			/*	out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
			
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data();");
				out.println("	}else");
				
					
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
			
				
				out.println("if(IfCount=='99'){"); 
					out.println("		help_update_value_assign_99(oBj);"); 
					out.println("}");
					
					out.println("else if(IfCount=='1'){"); 
					out.println("		help_value_assign_1(oBj);"); 
					out.println("}");
					
					out.println("else if(IfCount=='2'){"); 
					out.println("		help_value_assign_2(oBj);"); 
					out.println("}");
					
					out.println("else if(IfCount=='3'){"); 
					out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
					out.println("}");
					
					out.println("else if(IfCount=='4'){"); 
					out.println("		help_value_branch(oBj);"); 
					out.println("}");
					
			
			
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
		
				*/
			
			
			
			//	out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			// out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(document.Form1.hid_help_type.value);");
			out.println("	}else");
			
			
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			out.println("if(document.Form1.hid_help_type.value=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
			
			out.println("else if(document.Form1.hid_help_type.value=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			
			out.println("else if(document.Form1.hid_help_type.value=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			
			out.println("else if(document.Form1.hid_help_type.value=='3'){"); 
			out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
			out.println("}");
			
			out.println("else if(document.Form1.hid_help_type.value=='4'){"); 
			out.println("		help_value_branch(oBj);"); 
			out.println("}");
			
			
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			
			//out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			
			//out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			//out.println("	clear_data(IfCount);");//Added To The Clear 
			
			out.println("	clear_data(document.Form1.hid_help_type.value);");//Added To The Clear 
			out.println("	}");
			
			
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 
			
			/*	out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				*/
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			
			
			
			
			
			
			
			
			
			
			/*	out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
			
				out.println("	"); 
				
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
							
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				
				
					out.println("if(oBj.valout[0]=='Next')  {");
					out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
					out.println("}");
					out.println("else if  (oBj.valout[0]=='Prev') {");
					out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
					out.println("}		");
					out.println("else if(oBj.valout[0] == 'Exit'){");
					out.println("}");
					out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
					
					out.println("if(IfCount=='99'){"); 
					out.println("		help_update_value_assign_99(oBj);"); 
					out.println("}");
					
					out.println("else if(IfCount=='1'){"); 
					out.println("		help_value_assign_1(oBj);"); 
					out.println("}");
					
					out.println("else if(IfCount=='2'){"); 
					out.println("		help_value_assign_2(oBj);"); 
					out.println("}");
					
					out.println("else if(IfCount=='3'){"); 
					out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
					out.println("}");
					
					out.println("else if(IfCount=='4'){"); 
					out.println("		help_value_branch(oBj);"); 
					out.println("}");
					
							
										
				out.println("	}"); 
				out.println("	}"); 
				
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				
				out.println("	else{");
				out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
				out.println("	}");
				
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
						
				*/						
			
			out.println("function help_button_1(Start,End,Hid_No,Sql,IfCount) {"); 
			
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"VERIFYL@\"+\"Y@\";"); 
			
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = Sql;"); 
			out.println("    m_criteria = Crit"); 
			
			
			
			out.println("    } ");
			out.println("    HelpBox('1','10','6');"); 
			//out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			
			out.println("if(document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("document.Form1.BUT_TXT_VENDOR_CODE.disabled=false");
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=false");
			out.println("get_IRR();");//Added By Sandun on 28-04-2009
			out.println("}"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_1(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			//ADDED BY DELANJALI ON 2007-09-19
			out.println("     document.Form1.TXT_ACT_DATE_DD.value=oBj.valout[7];");
			out.println("     document.Form1.TXT_ACT_DATE_MM.value=oBj.valout[8];"); 
			out.println("     document.Form1.TXT_ACT_DATE_YY.value=oBj.valout[9];"); 
			
			
			out.println("     document.Form1.TXT_NEXT_DATE_DD.value=oBj.valout[10];"); 
			out.println("     document.Form1.TXT_NEXT_DATE_MM.value=oBj.valout[11];"); 
			out.println("     document.Form1.TXT_NEXT_DATE_YY.value=oBj.valout[12];"); 
			
			
			
			out.println("get_doc_entity_apllication()");
			
			//out.println("			assignState('M20');"); 
			//	out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_documents_entity_app_no&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=Y\";");
			
			//	out.println("window.open(m_url);");
			//out.println("			load_interface(m_url,'XML');");					
			
			
			out.println("}"); 
			
			
			
			
			out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount) {"); 
			
			out.println("    Crit =document.Form1.TXT_VENDOR_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = Sql;"); 
			out.println("    m_criteria = Crit"); 
			out.println("    HelpBox('1','10','0');"); 
			
			//out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_2(oBj) {"); 
			
			out.println("i=0;");
			
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_VENDOR_NAME.value=oBj.valout[3];"); 
			
			out.println("m_inv_btn=\"BUT_TXT_INVOICE_NO_HELP\"+i");
			out.println("m_inv=\"TXT_INVOICE_NO\"+i");
			out.println("if(document.Form1.TXT_VENDOR_CODE.value!=\"\"){");
			out.println("document.Form1.TXT_BRANCH_CODE.disabled=false;");
			out.println("document.Form1.BUT_TXT_BRANCH_CODE.disabled=false;");
			out.println("document.Form1.elements[m_inv_btn].disabled=false;");
			out.println("document.Form1.elements[m_inv].disabled=false;}");
			
			
			out.println("}"); 
			
			
			out.println("function help_button_branch_pur(Start,End,Hid_No,Sql,IfCount) {"); 
			
			
			out.println("    Crit =document.Form1.TXT_VENDOR_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_BRANCH_CODE.value+\"@Y@\";"); 
			
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = Sql;"); 
			out.println("    m_criteria = Crit"); 
			out.println("    HelpBox('1','10','0');"); 
			
			//out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_branch(oBj) {"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];"); 
			
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.MORE_BUT.disabled=false;"); 
			
			
			
			out.println("}"); 
			
			
			/*----------------------------------------------			
			Purpose  :To Get The Invoice Number
			-----------------------------------------------*/
			out.println("function help_button_3(rowNo) {"); 
			out.println("    m_invoice = \"TXT_INVOICE_NO\"+rowNo;");
			out.println("    document.Form1.hid_row_no.value=rowNo;"); 
			out.println("    Sql  = \"m_help_TXT_INVOICE_PURCHASE\";"); 
			out.println("    Crit = document.Form1.TXT_VENDOR_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.elements[m_invoice].value+\"@Y@\";"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = Sql;"); 
			out.println("    m_criteria = Crit"); 
			out.println("    HelpBox('1','10','0');"); 
			//out.println("    HelpBox('1','10','0',Crit,Sql,'3');"); 
			out.println("}"); 
			//-----End Of help_button_3---------------------------
			
			/*----------------------------------------------			
			Purpose  :To Assign The Invoice Number
			-----------------------------------------------*/
			out.println("function help_value_assign_3(rowNo,oBj) {"); 
			out.println("doc_count=0;");//assign 0
			out.println("row_doc_count=0;");//assign 0
			out.println("m_invoice=\"TXT_INVOICE_NO\"+rowNo;");
			out.println("m_gross=\"TXT_GROSS\"+rowNo;");	
			out.println("m_net=\"TXT_NET\"+rowNo;");	
			out.println("m_vat=\"TXT_VAT\"+rowNo;");	
			out.println("m_capital=\"TXT_CAPITAL\"+rowNo;");	
			out.println("m_engine_no=\"TXT_ENGINE_NO\"+rowNo;");	
			out.println("m_chassiss_no=\"TXT_CHASSISS_NO\"+rowNo;");	
			out.println("m_asset_id=\"hid_TXT_ASSET_ID\"+rowNo");
			
			out.println("    document.Form1.elements[m_invoice].value=oBj.valout[2];"); 
			out.println("    document.Form1.elements[m_gross].value=oBj.valout[8];"); 
			out.println("    format_number(document.Form1.elements[m_gross],20);");
			out.println("    document.Form1.elements[m_net].value=oBj.valout[6];"); 
			out.println("    format_number(document.Form1.elements[m_net],20);");
			out.println("    document.Form1.elements[m_vat].value=oBj.valout[7];"); 
			out.println("    format_number(document.Form1.elements[m_vat],20);");
			out.println("    document.Form1.elements[m_capital].value=oBj.valout[9];"); 
			out.println("    format_number(document.Form1.elements[m_capital],6);");
			
			out.println("    document.Form1.elements[m_engine_no].value=oBj.valout[4];"); 
			out.println("    document.Form1.elements[m_chassiss_no].value=oBj.valout[5];"); 
			
			/*
			out.println("if(oBj.valout[4]=='-' || oBj.valout[4]=='null' ){");
			out.println("    document.Form1.elements[m_engine_no].value='';"); 
			out.println("}");
			out.println("else{");
			out.println("    document.Form1.elements[m_engine_no].value=oBj.valout[4];"); 
			out.println("}");
			out.println("if(oBj.valout[5]=='-' || oBj.valout[5]=='null' ){");
			out.println("    document.Form1.elements[m_chassiss_no].value='';"); 
			out.println("}");
			out.println("else{");
			out.println("    document.Form1.elements[m_chassiss_no].value=oBj.valout[5];"); 
			out.println("}");
			*/
			
			out.println("    document.Form1.elements[m_asset_id].value=oBj.valout[3];"); 
			out.println("check_exist_of_invoice_number(document.Form1.elements[m_invoice].value,rowNo);");
			out.println("if(b_inv_exsist==0){");
			out.println("m_invoice_hid_number=\"hid_TXT_INVOICE_NUMBER\"+rowNo");			
			out.println("if(document.Form1.elements[m_invoice_hid_number].value!=\"\"){");
			out.println("document.Form1.hid_inv_no_chk.value=document.Form1.elements[m_invoice_hid_number].value;");
			out.println("}");
			out.println("del_row_assign(rowNo);");
			out.println("show_invoice_doc(document.Form1.elements[m_invoice].value);");
			out.println("}"); 
			out.println("}"); 
			
			//-----End Of help_value_assign_3------------------------------------------------------
			
			
			
			
			//****************************************************************************************************
			
			out.println("function check_exist_of_invoice_number(val,rowNo){");
			//	out.println("alert('val'+val);");
			out.println("m_inv_tmp=\"TXT_INVOICE_NO\"+rowNo;");
			out.println("for(i=0;i<arr_size;i++){");
			out.println("m_inv=\"TXT_INVOICE_NO\"+i;");
			out.println("if(document.Form1.elements[m_inv].value==val && i!=rowNo){");
			out.println("alert('Invoice Number Already Seleceted');"); 
			out.println("m_inv=\"TXT_INVOICE_NO\"+rowNo;");
			out.println("m_gro=\"TXT_GROSS\"+rowNo;");	
			out.println("m_net_val=\"TXT_NET\"+rowNo;");	
			out.println("m_vat_val=\"TXT_VAT\"+rowNo;");	
			out.println("m_capital=\"TXT_CAPITAL\"+rowNo;");	
			out.println("m_engine_no=\"TXT_ENGINE_NO\"+rowNo;");	
			out.println("m_chassiss_no=\"TXT_CHASSISS_NO\"+rowNo;");	
			out.println("document.Form1.elements[m_inv].value=\"\" ");
			out.println("document.Form1.elements[m_gro].value=\"\" ");
			out.println("document.Form1.elements[m_net_val].value=\"\" ");
			out.println("document.Form1.elements[m_vat_val].value=\"\" ");
			out.println("document.Form1.elements[m_capital].value=\"\" ");
			out.println("document.Form1.elements[m_engine_no].value=\"\" ");
			out.println("document.Form1.elements[m_chassiss_no].value=\"\" ");
			out.println("b_inv_exsist=1;"); 
			out.println("break;}"); 
			out.println("else{"); 
			out.println("b_inv_exsist=0;}"); 
			out.println("}"); 		
			out.println("}"); 
			//*****************************************************************************************************
			
			out.println("function show_invoice_doc(val){");
			out.println("assignState('M9');"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_get_documents&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val2=\"+val+\"&ac_status=Y\";");
			// out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			/*	out.println("function show_invoice_doc2(val){");
				
				out.println("assignState('M11');"); 
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_documents&data_val=\"+val+\"&ac_status=Y\";");
				
	
		//   out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
				out.println("}");
			*/
			
			
			out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
			
			//out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"DEL\" ){ ");
			out.println("    Crit =document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"VERIFY@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = Sql;"); 
			out.println("    m_criteria = Crit"); 
			out.println("    HelpBox('1','10','0');"); 
			
			//out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			
			out.println("}"); 
			
			
			out.println("function help_update_value_assign_99(oBj) {"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_VENDOR_NAME.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[7];"); 
			//out.println("  get_sys_date()");
			out.println("    document_entity_edit();"); //display the documents clinet wise
			out.println("}"); 
			
			
			out.println("function header_doc(){");
			out.println("m_header='<TR><TD width=\"*%\"><U><B>Documents Required - Asset Wise</B></U></TD></TR>';");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_header+'</table>';");
			
			
			// out.println("m_writedata='<TR><TD><U><B>Documents Required - Asset Wise</B></U></TD></TR>' +");
			//out.println("'<TD WIDTH=\"20%\"><B>Description</B></TD><TD WIDTH=\"10%\"><B>Previous Remarks</B></TD><TD WIDTH=\"10%\"><B>Previos Status</B></TD><TD WIDTH=\"10%\"><B>Required</B></TD><TD WIDTH=\"10%\"><B>Not Applicable</B></TD><TD WIDTH=\"10%\"><B>Follow Up Status</B></TD><TD WIDTH=\"15%\"><B>Follow up Remarks</B></TD><TD WIDTH=\"15%\"><B>Remarks</B></TD>' +");
			out.println("m_writedata='<TR><TD WIDTH=\"20%\"><B>Description</B></TD><TD WIDTH=\"10%\"><B>Previous Remarks</B></TD><TD WIDTH=\"10%\"><B>Previous Status</B></TD><TD WIDTH=\"10%\"><B>Checked</B></TD><TD WIDTH=\"10%\"><B>Not Applicable</B></TD><TD WIDTH=\"15%\"><B>Remarks</B></TD><TD WIDTH=\"10%\"><B>Follow Up Status</B></TD><TD WIDTH=\"15%\"><B>Follow up Remarks</B></TD>' +");
			out.println("'</TR>';");
			
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			out.println("}");
			
			
			out.println("function header_doc_entity(data_vec_doc_entity){");
			
			out.println("m_table2.innerHTML=\"\" ");
			out.println("line_doc_entity=0;");
			out.println("arr_size_doc_entity=0;");
			
			out.println("if(data_vec_doc_entity.length>0){");
			
			out.println("m_header='<TR><TD><U><B>Documents Required - Applicant Wise</B></U></TD></TR>';");
			
			out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_header+'</table>';");
			
			// out.println("'<TD WIDTH=\"30%\"><B>Description</B></TD><TD WIDTH=\"10%\"><B>Previous Remarks</B></TD><TD WIDTH=\"10%\"><B>Previous Status</B></TD><TD WIDTH=\"10%\"><B>Required</B></TD><TD WIDTH=\"10%\"><B>Not Applicable</B></TD><TD WIDTH=\"10%\"><B>Follow up Status</B></TD><TD WIDTH=\"20%\"><B>Remarks</B></TD>' +");
			out.println("m_writedata='<TR><TD WIDTH=\"20%\"><B>Description</B></TD><TD WIDTH=\"10%\"><B>Previous Remarks</B></TD><TD WIDTH=\"10%\"><B>Previous Status</B></TD><TD WIDTH=\"10%\"><B>Checked</B></TD><TD WIDTH=\"10%\"><B>Not Applicable</B></TD><TD WIDTH=\"15%\"><B>Remarks</B></TD><TD WIDTH=\"10%\"><B>Follow Up Status</B></TD><TD WIDTH=\"15%\"><B>Follow up Remarks</B></TD>' +");
			out.println("'</TR>';");
			
			out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			out.println("}");
			
			out.println("}");
			
			
			
			out.println("function change_val_req(row_no){")	;
			out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
			out.println("m_chk_not_app=\"CHK_NOT_APPLICABLE\"+row_no;");
			
			
			out.println("if(document.Form1.elements[m_chk_required].checked==true && document.Form1.elements[m_chk_not_app].checked==true ){");
			//			out.println("alert('m_required'+m_chk_required)");
			out.println("document.Form1.elements[m_chk_required].value='on'");
			out.println("document.Form1.elements[m_chk_not_app].checked=false");
			out.println("document.Form1.elements[m_chk_not_app].value='off'");
			out.println("}else if(document.Form1.elements[m_chk_required].checked==true && document.Form1.elements[m_chk_not_app].checked==false){");
			out.println("document.Form1.elements[m_chk_required].value='on'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk_required].value='off'");
			out.println("}");	
			//	out.println("alert('m_required'+document.Form1.elements[m_chk_required].value)");
			out.println("}");	
			
			out.println("function change_val_req_entity(row_no){")	;
			out.println("m_chk_required_entity=\"CHK_REQUIRED_ENTITY\"+row_no;");
			out.println("m_chk_not_app_entity=\"CHK_NOT_APPLICABLE_ENTITY\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_required_entity].checked==true && document.Form1.elements[m_chk_not_app_entity].checked==true){");
			out.println("document.Form1.elements[m_chk_required_entity].value='on'");
			out.println("document.Form1.elements[m_chk_not_app_entity].checked=false");
			out.println("document.Form1.elements[m_chk_not_app_entity].value='off'");
			out.println("}else if(document.Form1.elements[m_chk_required_entity].checked==true && document.Form1.elements[m_chk_not_app_entity].checked==false){");
			out.println("document.Form1.elements[m_chk_required_entity].value='on'");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk_required_entity].value='off'");
			out.println("}");		
			
			out.println("}");	
			
			//added by nuwan de silva 19-06-07----------------
			out.println("function check_condition(obj){")	;
			
			out.println("if(obj.checked==true) {");	
			out.println("obj.value='on'");
			out.println("}");	
			
			out.println("else if(obj.checked==false) {");	
			out.println("obj.value='off'");	
			out.println("obj.checked=false");	
			out.println("}");	
			
			out.println("}");	
			
			//--------------------------------------------------
			
			
			
			out.println("function change_val_not_app(row_no){")	;
			out.println("m_chk_not_app=\"CHK_NOT_APPLICABLE\"+row_no;");
			out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_not_app].checked==true && document.Form1.elements[m_chk_required].checked==true){");
			
			out.println("document.Form1.elements[m_chk_not_app].value='on'");
			out.println("alert('Not Applicable selected');");
			out.println("document.Form1.elements[m_chk_required].checked=false");
			out.println("document.Form1.elements[m_chk_required].value='off'");
			
			out.println("}else if(document.Form1.elements[m_chk_not_app].checked==true && document.Form1.elements[m_chk_required].checked==false){");
			out.println("document.Form1.elements[m_chk_not_app].value='on'");
			out.println("alert('Not Applicable selected');");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk_not_app].value='off'");
			out.println("}");		
			out.println("}");	
			
			
			
			out.println("function change_val_not_app_entity(row_no){")	;
			out.println("m_chk_not_app_entity=\"CHK_NOT_APPLICABLE_ENTITY\"+row_no;");
			out.println("m_chk_required_entity=\"CHK_REQUIRED_ENTITY\"+row_no;");
			
			out.println("if(document.Form1.elements[m_chk_not_app_entity].checked==true && document.Form1.elements[m_chk_required_entity].checked==true){");
			
			out.println("document.Form1.elements[m_chk_not_app_entity].value='on'");
			out.println("alert('Not Applicable selected');");
			out.println("document.Form1.elements[m_chk_required_entity].checked=false");
			out.println("document.Form1.elements[m_chk_required_entity].value='off'");
			out.println("}else if(document.Form1.elements[m_chk_not_app_entity].checked==true && document.Form1.elements[m_chk_required_entity].checked==false){");
			out.println("document.Form1.elements[m_chk_not_app_entity].value='on'");
			out.println("alert('Not Applicable selected');");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk_not_app_entity].value='off'");
			out.println("}");		
			
			out.println("}");	
			
			
			
			
			out.println("function display_documents2(data_vec_doc){");
			
			out.println("var i=0;");
			
			out.println("header_doc();	");		
			
			out.println("while(i<data_vec_doc.length){");
			
			out.println("m_description='<TD WIDTH=\"20%\">'+data_vec_doc[i+2].replace(\"$\",\"&\")+'</TD>';");
			out.println("m_prv_remark='<TD WIDTH=\"10%\">'+data_vec_doc[i+3]+'</TD>';");		
			
			
			out.println("if(data_vec_doc[i+4]==\"Y\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">Yes</TD>';");	
			out.println("else if(data_vec_doc[i+4]==\"N\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">No</TD>';");	
			out.println("else if(data_vec_doc[i+4]==\"-\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");	
			
			out.println("m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+line_doc+' VALUE=off  onclick=\"change_val_req('+line_doc+')\" ></td>';");			
			
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE'+line_doc+' VALUE=off onclick=\"change_val_not_app('+line_doc+')\"></td>';");			
			
			
			out.println("m_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK'+line_doc+' VALUE=\"\" maxlength=\"50\" size=\"20\"></td>';");		
			
			//out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_DOC'+line_doc+' VALUE=\"off\" onclick=\"change_val_fol_up_doc('+line_doc+')\"></td>';");			
			
			out.println("					if(data_vec[i+6]==\"-\") ");
			out.println("					{ ");
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_DOC'+line_doc+' VALUE=\"off\"  disabled onclick=\"change_val_fol_up_doc('+line_doc+')\"></td>';");			
			out.println("					} ");
			
			out.println("					else ");
			out.println("					{ ");
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_DOC'+line_doc+' VALUE=\"on\" checked disabled onclick=\"change_val_fol_up_doc('+line_doc+')\"></td>';");			
			out.println("					} ");
			
			out.println("m_followup_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_FOLLOW_UP_REMARK_DOC'+line_doc+' VALUE=\"'+data_vec_doc[i+5]+'\" maxlength=\"50\" size=\"20\"></td>';");		
			
			
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DOC_CODE'+line_doc+'	VALUE='+data_vec_doc[i+1]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESCRIPTION'+line_doc+'	VALUE=\"'+data_vec_doc[i+2].replace(\"$\",\"&\")+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_REMARK'+line_doc+'	VALUE=\"'+data_vec_doc[i+3]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_STATUS'+line_doc+'	VALUE=\"'+data_vec_doc[i+4]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INV_NO'+line_doc+'	VALUE='+data_vec_doc[i]+'>';");
			
			out.println("m_writedata='<TR>'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;");
			
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			
			out.println("i=i+7;");
			out.println("line_doc=line_doc+1;");
			out.println("arr_size_doc=arr_size_doc+1;");		
			out.println("}"); //End while loop
			
			
			out.println("}");
			
			
			
			out.println("function display_documents_applicant(data_vec_doc,m_inv_no){");
			out.println("array_doc_applicant=data_vec_doc;");
			
			out.println("row_doc_count=0;");
			out.println("assignState('M17');"); 
			out.println("header_doc();	");		
			out.println("while(row_doc_count<array_doc_applicant.length){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_doc_applicant&data_val1=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val2=\"+m_inv_no+\"&data_val3=\"+data_vec_doc[row_doc_count];");
			//out.println("window.open(m_url);");
			
			out.println("load_interface(m_url,'XML');");
			out.println("row_doc_count=row_doc_count+2;");
			out.println("}");
			
			out.println("}");
			
			
			
			//*******************************************************************************************
			//*******************************************************************************************
			
			
			out.println("function display_documents3(data_vec_doc){");
			
			out.println("var i=0;");
			out.println("var count_app=0;");		
			out.println("header_doc();	");		
			
			
			out.println("while(i<data_vec_doc.length){");
			
			out.println("b_state=0");
			
			out.println("m_description='<TD WIDTH=\"20%\">'+data_vec_doc[i+1].replace(\"$\",\"&\")+'</TD>';");
			
			//		out.println("m_prv_remark='<TD WIDTH=\"10%\">-</TD>';");		
			//		out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");	
			
			out.println("m_prv_remark='<TD WIDTH=\"10%\">'+data_vec_doc[i+2]+'</TD>';");		
			
			
			out.println("if(data_vec_doc[i+3]==\"Y\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">Yes</TD>';");	
			
			out.println("else if(data_vec_doc[i+3]==\"N\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">No</TD>';");	
			
			out.println("else if(data_vec_doc[i+3]==\"A\"){");
			out.println("m_prv_status='<TD WIDTH=\"10%\" >Not Applicable </TD>';");	
			out.println("b_state=1;");
			out.println("count_app=count_app+1;");
			out.println("}");
			
			out.println("else if(data_vec_doc[i+3]==\"-\")");
			out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");	
			
			
			//out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");	
			
			
			
			//out.println("m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+line_doc+' VALUE=\"off\"  onclick=\"change_val_req('+line_doc+')\" ></td>';");	// commented by udara 21-07-2015
			
			// added by udara 21-07-2015
			out.println("if(data_vec_doc[i+3]==\"Y\"){");
			out.println("   m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+line_doc+' VALUE=\"on\"  onclick=\"change_val_req('+line_doc+')\" checked ></td>';");	
			out.println("}");
			out.println("else{");
			out.println("   m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+line_doc+' VALUE=\"off\"  onclick=\"change_val_req('+line_doc+')\" ></td>';");	
			out.println("}");
			// end by udara 21-07-2015
			
			out.println("if(data_vec_doc[i+3]==\"A\"){");
			
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE'+line_doc+' VALUE=\"on\" checked onclick=\"change_val_not_app('+line_doc+')\"></td>';");			
			
			out.println("}");
			
			out.println("else{");
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE'+line_doc+' VALUE=\"off\" onclick=\"change_val_not_app('+line_doc+')\"></td>';");			
			out.println("}");
			
			
			out.println("m_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK'+line_doc+' VALUE=\"\" maxlength=\"50\" size=\"20\"></td>';");		
			
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_DOC'+line_doc+' VALUE=\"off\" onclick=\"change_val_fol_up_doc('+line_doc+')\"></td>';");			
			
			out.println("m_followup_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_FOLLOW_UP_REMARK_DOC'+line_doc+' VALUE=\"\" maxlength=\"50\" size=\"20\"></td>';");		
			
			
			
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DOC_CODE'+line_doc+'	VALUE=\"'+data_vec_doc[i]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESCRIPTION'+line_doc+'	VALUE=\"'+data_vec_doc[i+1]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_REMARK'+line_doc+'	VALUE=\"'+data_vec_doc[i+2]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_STATUS'+line_doc+'	VALUE=\"'+data_vec_doc[i+3]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOC_POS'+line_doc+'	VALUE=\"'+data_vec_doc[i+4]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INV_NO'+line_doc+'	VALUE='+document.Form1.hid_inv_no.value+'>';");
			
			
			out.println("if(count_app==1 && b_state==1){");
			out.println("m_error='<TR STYLE=\"{color:red;}\"><TD WIDTH=\"100%\"><b>System Exceptions</TD></TR>';");			
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_error+'</table>';");
			
			out.println("}");
			
			out.println("if(b_state==1){");	
			out.println("m_writedata='<TR STYLE=\"{color:red;}\">'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;");
			out.println("array_doc[line_doc]=m_writedata;");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" bordercolor=\"black\"   border=\"1\" cellspacing=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			out.println("}");
			out.println("else");
			out.println("{");
			out.println("m_writedata='<TR>'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;");
			out.println("array_doc[line_doc]=m_writedata;");
			
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println("m_writedata+'</table>';");
			
			out.println("}");
			
			out.println("count_app=count_app+1;");
			out.println("i=i+5;");
			out.println("line_doc=line_doc+1;");
			out.println("arr_size_doc=arr_size_doc+1;");		
			out.println("}"); //End while loop
			
			//*********************************************************************
			
			
			
			
			
			
			out.println("}");
			
			//	************************************************************************************************
			//	************************************************************************************************
			
			
			
			/*-----------------------------
			Purpose        : This Function Show The Documents Are Required To The P/o Scrren.
			Created By     :Nuwan De Silva
			-------------------------------*/
			
			
			out.println("  function  display_documents_entity(data_vec_doc_entity){");
			
			out.println("var i=0;");
			
			out.println("header_doc_entity(data_vec_doc_entity);	");		
			
			out.println("while(i<data_vec_doc_entity.length){");
			
			out.println("m_description='<TD WIDTH=\"20%\">'+data_vec_doc_entity[i+1].replace('$','&')+'</TD>';");
			out.println("m_prv_remark='<TD WIDTH=\"10%\">-</TD>';");		
			
			out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");	
			out.println("m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED_ENTITY'+line_doc_entity+' VALUE=\"off\" onclick=\"change_val_req_entity('+line_doc_entity+')\"></td>';");			
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE_ENTITY'+line_doc_entity+' VALUE=\"off\" onclick=\"change_val_not_app_entity('+line_doc_entity+')\"></td>';");			
			
			out.println("m_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK_ENTITY'+line_doc_entity+' VALUE=\"\" maxlength=\"50\" size=\"20\"></td>';");		
			
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_ENTITY'+line_doc_entity+' VALUE=\"off\" onclick=\"change_val_fol_up_entity('+line_doc_entity+')\"></td>';");			
			
			out.println("m_followup_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_FOLLOW_UP_REMARK_ENTITY'+line_doc_entity+' VALUE=\"\" maxlength=\"50\" size=\"20\" disabled ></td>';");	// disabled by udara 08-04-2016	
			
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DOC_CODE_ENTITY'+line_doc_entity+'	VALUE='+data_vec_doc_entity[i]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESCRIPTION_ENTITY'+line_doc_entity+'	VALUE='+data_vec_doc_entity[i+1].replace('$','&')+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_REMARK_ENTITY'+line_doc+'	VALUE=\"'+data_vec_doc_entity[i+2]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_STATUS_ENTITY'+line_doc+'	VALUE=\"'+data_vec_doc_entity[i+3]+'\">'+");
			
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INV_NO_ENTITY'+line_doc+'	VALUE='+document.Form1.hid_inv_no.value+'>';");
			
			out.println("m_writedata='<TR>'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;"); 
			
			out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");
			
			out.println("i=i+2;");
			out.println("line_doc_entity=line_doc_entity+1;");
			out.println("arr_size_doc_entity=arr_size_doc_entity+1;");		
			out.println("}"); //End while loop
			
			out.println("load_pur_ord_data();");		 //Added By Nuwan De Silva 28-05-07--------------------
			
			out.println("}");		
			
			
			
			out.println("  function  display_documents_entity_app_no(data_vec_doc_entity){");
			
			out.println("var i=0;");
			out.println("var count_entity=0;");
			
			out.println("header_doc_entity(data_vec_doc_entity);	");		
			
			out.println("while(i<data_vec_doc_entity.length){");
			
			out.println("b_state=0");
			
			out.println("m_description='<TD WIDTH=\"20%\">'+data_vec_doc_entity[i+1].replace('$','&')+'</TD>';");
			
			//   out.println("m_prv_remark='<TD WIDTH=\"10%\">-</TD>';");		
			//out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");	
			
			out.println("m_prv_remark='<TD WIDTH=\"10%\">'+data_vec_doc_entity[i+2]+'</TD>';");		
			
			out.println("if(data_vec_doc_entity[i+3]==\"Y\"){");
			out.println("m_prv_status='<TD WIDTH=\"10%\">Yes</TD>';");	
			out.println("}");
			
			out.println("else if(data_vec_doc_entity[i+3]==\"N\"){");
			out.println("m_prv_status='<TD WIDTH=\"10%\">No</TD>';");	
			out.println("}");
			
			out.println("else if(data_vec_doc_entity[i+3]==\"A\"){");
			out.println("m_prv_status='<TD WIDTH=\"10%\" >Not Applicable </TD>';");	
			out.println("b_state=1;");
			out.println("count_entity=count_entity+1;");
			out.println("}");
			
			out.println("else if(data_vec_doc_entity[i+3]==\"-\"){");
			out.println("m_prv_status='<TD WIDTH=\"10%\">-</TD>';");	
			out.println("}");
			
			
			//out.println("m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED_ENTITY'+line_doc_entity+' VALUE=\"\" onclick=\"change_val_req_entity('+line_doc_entity+')\"></td>';");	// commented by udara 21-07-2015
			
			// added by udara 21-07-2015
			out.println("if(data_vec_doc_entity[i+3]==\"Y\"){");
			out.println("   m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED_ENTITY'+line_doc_entity+' VALUE=\"on\" onclick=\"change_val_req_entity('+line_doc_entity+')\" checked ></td>';");		
			out.println("}");
			out.println("else{");
			out.println("   m_required='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED_ENTITY'+line_doc_entity+' VALUE=\"off\" onclick=\"change_val_req_entity('+line_doc_entity+')\"></td>';");		
			out.println("}");
			// end by udara 21-07-2015

			
			//out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE_ENTITY'+line_doc_entity+' VALUE=\"\" onclick=\"change_val_not_app_entity('+line_doc_entity+')\"></td>';");			
			
			out.println("if(data_vec_doc_entity[i+3]==\"A\"){");
			
			//out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE'+line_doc+' VALUE=\"on\" checked onclick=\"change_val_not_app('+line_doc+')\"></td>';");			
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE_ENTITY'+line_doc_entity+' VALUE=\"on\" checked onclick=\"change_val_not_app_entity('+line_doc_entity+')\"></td>';");			
			out.println("}");
			
			out.println("else{");
			out.println("m_not_applicable='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE_ENTITY'+line_doc_entity+' VALUE=\"off\"  onclick=\"change_val_not_app_entity('+line_doc_entity+')\"></td>';");			
			out.println("}");
			
			out.println("m_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK_ENTITY'+line_doc_entity+' VALUE=\"\" maxlength=\"50\" size=\"20\"></td>';");		
			
			out.println("m_followup='<TD WIDTH=\"10%\"><INPUT TYPE=\"checkbox\" NAME=CHK_FOLLOW_UP_ENTITY'+line_doc_entity+' VALUE=\"off\" onclick=\"change_val_fol_up_entity('+line_doc_entity+')\"></td>';");			
			
			out.println("m_followup_remark='<TD WIDTH=\"15%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_FOLLOW_UP_REMARK_ENTITY'+line_doc_entity+' VALUE=\"\" maxlength=\"50\" size=\"20\" disabled ></td>';");	// disabled by udara 08-04-2016	
			
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DOC_CODE_ENTITY'+line_doc_entity+'	VALUE=\"'+data_vec_doc_entity[i]+'\" >'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESCRIPTION_ENTITY'+line_doc_entity+'	VALUE=\"'+data_vec_doc_entity[i+1].replace('$','&')+'\" >'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_REMARK_ENTITY'+line_doc_entity+'	VALUE=\"'+data_vec_doc_entity[i+2]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PRV_STATUS_ENTITY'+line_doc_entity+'	VALUE=\"'+data_vec_doc_entity[i+3]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOC_POS_ENTITY'+line_doc_entity+'	VALUE=\"'+data_vec_doc_entity[i+4]+'\">'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_INV_NO_ENTITY'+line_doc_entity+'	VALUE='+document.Form1.hid_inv_no.value+'>';");
			
			//out.println("m_writedata='<TR>'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;"); 
			
			//	out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			//	out.println("m_writedata+'</table>';");
			
			out.println("if(count_entity==1 && b_state==1){");
			//out.println("alert('test'+count);");
			out.println("m_error='<TR STYLE=\"{color:red;}\"><TD WIDTH=\"100%\"><b>System Exceptions</b></TD></TR>';");		
			
			out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_error+'</table>';");
			
			out.println("}");
			
			out.println("if(b_state==1){");
			
			out.println("m_writedata='<TR STYLE=\"{color:red;}\">'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;"); 
			
			out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" bordercolor=\"black\"  border=\"1\" cellspacing=\"0\" >'+");
			out.println("m_writedata+'</table>';");
			out.println("}");
			
			out.println("else");
			out.println("{");
			out.println("m_writedata='<TR>'+m_description+m_prv_remark+m_prv_status+m_required+m_not_applicable+m_remark+m_followup+m_followup_remark+'</TR>'+m_hid_input;"); 
			
			out.println("m_table2.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
			out.println("m_writedata+'</table>';");						
			
			out.println("}");
			
			
			out.println("i=i+5;");
			out.println("count_entity=count_entity+1;");
			out.println("line_doc_entity=line_doc_entity+1;");
			out.println("arr_size_doc_entity=arr_size_doc_entity+1;");		
			out.println("}"); //End while loop
			
			out.println("document.Form1.TXT_VENDOR_CODE.disabled=false");
			out.println("document.Form1.BUT_TXT_VENDOR_CODE.disabled=false");
			
			out.println("get_conditions();");
			out.println("get_IRR();");//Added by Sandun on 28-04-2009
			out.println("}");		
			
			
			
			
			
			
			
			
			out.println("function display_entity_doc(){ ");
			/*out.println("   if(document.Form1.TXT_APPLICATION_NO.value==''){ "); 
			out.println("header_conditions()");
			out.println("add_row_conditions()");
			out.println("assignState('M10');"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_val_documents_entity&ac_status=Y\";");
		// out.println("window.open(m_url);");
		out.println("load_interface(m_url,'XML');");
			out.println("}");*/
			out.println("load_pur_ord_data();");		
			
			out.println("}");
			
			
			
			
			out.println("function disable_app_no(){")			;
			out.println("i=0;");
			// out.println("document.Form1.TXT_PURCHASE_ORDER_NO.disabled=true;"); 
			//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
			out.println("document.Form1.BUT_TXT_VENDOR_CODE.disabled=true");
			out.println("m_inv_btn=\"BUT_TXT_INVOICE_NO_HELP\"+i");
			out.println("m_inv=\"TXT_INVOICE_NO\"+i");
			out.println("document.Form1.elements[m_inv_btn].disabled=true");
			out.println("document.Form1.elements[m_inv].disabled=true");						
			out.println("}");
			
			
			
			out.println("function View_Letter(){");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_View_Letter\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order?application_no="+m_application_no+"';"); 
			
			out.println("}");
			
			
			out.println("function change_val_fol_up_doc(row){");
			
			out.println("m_chk_status_fol_doc=\"CHK_FOLLOW_UP_DOC\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status_fol_doc].checked==true){");
			out.println("document.Form1.elements[m_chk_status_fol_doc].value='on'");
			out.println("document.Form1.elements[m_chk_status_fol_doc].checked=true");
			//	out.println("alert('value'+document.Form1.elements[m_chk_status_fol_doc].value);");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status_fol_doc].checked==false){");
			out.println("document.Form1.elements[m_chk_status_fol_doc].value='off'");
			out.println("document.Form1.elements[m_chk_status_fol_doc].checked=false");
			//	out.println("alert('value'+document.Form1.elements[m_chk_status_fol_doc].value);");
			out.println("}");
			
			out.println("}");
			
			
			out.println("function change_val_fol_up_entity(row){");
			
			out.println("m_chk_status_fol_entity=\"CHK_FOLLOW_UP_ENTITY\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status_fol_entity].checked==true){");
			out.println("document.Form1.elements[m_chk_status_fol_entity].value='on'");
			out.println("document.Form1.elements[m_chk_status_fol_entity].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status_fol_entity].checked==false){");
			out.println("document.Form1.elements[m_chk_status_fol_entity].value='off'");
			out.println("document.Form1.elements[m_chk_status_fol_entity].checked=false");
			out.println("}");
			
			out.println("}");
			
			
			out.println("function close_screen_pur() {");
			//out.println(" alert(document.Form1.hid_close_sts.value);");
			//out.println("		if(document.Form1.hid_close_sts.value=='Y'){ "); 
			out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		      window.close();"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=main_page';");
			//out.println("		     }"); 
			out.println("		 }"); 
			//out.println("		else { "); 
			//out.println("		     close_window();"); 
			//out.println("		}"); 
			out.println("}");
			
			
			out.println("function load_calendar(num) {");
			out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
			
			
			
			
			
			
			out.println("function load_c_date(val) {");
			
			//	out.println("alert('date valaue'+val);");
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
			// out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			
			out.println("  if(document.Form1.hid_cal_date.value=='2' && b_flag_date!=1 ){"); 
			out.println("     document.Form1.TXT_ACT_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_ACT_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_ACT_DATE_YY.value=val;");
			out.println("  }");				
			
			out.println("  else if(document.Form1.hid_cal_date.value=='3' && b_flag_date!=1){"); 
			out.println("     document.Form1.TXT_NEXT_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_NEXT_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_NEXT_DATE_YY.value=val;");
			
			out.println("validate_date(document.Form1.TXT_ACT_DATE_DD,document.Form1.TXT_ACT_DATE_MM,document.Form1.TXT_ACT_DATE_YY,document.Form1.TXT_NEXT_DATE_DD,document.Form1.TXT_NEXT_DATE_MM,document.Form1.TXT_NEXT_DATE_YY);");
			
			
			out.println("  }");				
			
			out.println("}");
			out.println("}");
			
			
			out.println("function check_foll_con(){");
			
			out.println("   document.Form1.hid_no_rec_con.value=arr_size_con;");//Added By Nuwan De Silva
			
			/*out.println("for(var d=0;d<document.Form1.hid_no_rec_con.value;d++){");
			out.println("if(document.Form1.elements[\"hid_TXT_STATUS\"+d].value=='Pending' || document.Form1.elements[\"hid_TXT_STATUS\"+d].value=='--'){");
			out.println("m_cond_status=1");
			out.println("}");
			out.println("else{");
			out.println("m_cond_status=0");
			out.println("}");	
		  out.println("if(m_cond_status==1){");
			out.println("break");
			out.println("}");	
			out.println("}");
			*/
			out.println("m_cond_status=0");
			
			out.println("}");
			
			//--added by nuwan de silva 19-06-07-------------------------
			
			out.println("function check_condition_approv(){");
			
			out.println("for(var d=0;d<arr_size_con;d++){");
			
			out.println("if((document.Form1.elements[\"hid_TXT_STATUS\"+d].value=='Pending' || document.Form1.elements[\"hid_TXT_STATUS\"+d].value=='Completed' ) &&  document.Form1.elements[\"CHK_CONDITION\"+d].checked==false ){");
			out.println("m_check_approv=1");
			out.println("}");
			out.println("else{");
			out.println("m_check_approv=0");
			out.println("}");	
			out.println("if(m_check_approv==1){");
			out.println("break");
			out.println("}");	
			out.println("}");
			out.println("}");
			//-------------------------------------------------------------
			
			
			
			out.println("function display_charges(){");
			
			//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Charges_Details?chksql=LOAD_CHARGES_DETAILS&chargeble_amount="+m_chargeble_amount+"&application_no='+document.Form1.TXT_APPLICATION_NO.value;"); 
			
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Receipt_Charges_Details?chksql=LOAD_CHARGES_DETAILS&client_code="+m_client_code+"\";");
			
			out.println("window.open(m_url,'displayWindow2','left=450,top=200,width=550,height=450,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			
			
			out.println("}");
			
			out.println("function display_break_down(){");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Initial_Payment_Breakdown?chksql=LOAD_CHARGES_DETAILS&application_no="+m_application_no1+"&client_code="+m_client_code+"\";");
			out.println("window.open(m_url,'displayWindow2','left=450,top=200,width=550,height=450,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			
			
			
			
			out.println("function validate_date_is_correct(DD,MM,YY,OBJ){");
			
			out.println("if( DD.value!=\"\" && MM.value!=\"\" && YY.value!=\"\" ) {");
			
			out.println("checkMonthLength(DD,MM,YY);");
			
			out.println("}");
			
			out.println("if(OBJ=='N'){");
			
			out.println("validate_date(document.Form1.TXT_ACT_DATE_DD,document.Form1.TXT_ACT_DATE_MM,document.Form1.TXT_ACT_DATE_YY,DD,MM,YY);");
			
			out.println("}");
			
			
			out.println("}");
			
			
			out.println("function validate_date(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){");
			
			
			out.println("chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY);");
			
			
			out.println("}");
			
			
			
			//==========Added By Nuwan De Silva ======================================================
			out.println("function chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
			out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
			out.println("if((parseInt(FROM_DD.value))>=(parseInt(TO_DD.value))){");
			out.println("if((parseInt(FROM_MM.value))<=(parseInt(TO_MM.value))){");
			out.println("if((parseInt(FROM_YY.value))<=(parseInt(TO_YY.value))){");
			out.println(" if(((parseInt(FROM_DD.value))<(parseInt(TO_DD.value)))&&");
			out.println("((parseInt(FROM_MM.value))==(parseInt(TO_MM.value)))&&");
			out.println("((parseInt(FROM_YY.value))==(parseInt(TO_YY.value)))){");
			out.println("}");
			out.println("else if(((parseInt(FROM_DD.value))>=(parseInt(TO_DD.value)))&&");
			out.println("((parseInt(FROM_MM.value))==(parseInt(TO_MM.value)))&&");
			out.println(" ((parseInt(FROM_YY.value))==(parseInt(TO_YY.value)))){");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("     } ");
			out.println("}");
			out.println("else{");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("return false;"); 
			out.println("}");
			out.println(" }");
			out.println(" else{");
			out.println("   if((parseInt(FROM_YY.value))>=(parseInt(TO_YY.value))){");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("return false;"); 
			out.println("   }");
			out.println("   else{");
			out.println("   } ");
			out.println(" }");
			out.println("}");
			out.println("else{");
			out.println(" if((parseInt(FROM_MM.value))<=(parseInt(TO_MM.value))){");
			out.println("  if((FROM_YY.value)<=(TO_YY.value)){");
			out.println(" }");
			out.println(" else{");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("return false;"); 
			out.println(" }");
			out.println("}");
			out.println("else{");
			out.println("   if((parseInt(FROM_YY.value))<(parseInt(TO_YY.value))){ ");
			out.println("    }");
			out.println("  else{");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("return false;"); 
			out.println("  }");
			out.println(" }");
			out.println("}");
			//	out.println("TO_DD.focus();");
			out.println("return true;");
			out.println("}");
			out.println("}");
			//===========================================================================================
			
			
			
			
			
			//_______________________________________________________________________________________________________________________________________________________________________________________________________________________________	
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"header(),add_row(),load_app_data(),display_entity_doc(),disable_app_no(),disable_new()\">"); //load_lock()//display_entity_doc()(THIS IS BEFORE disable_app_no()) REMOVED BY DELANJALI ON 2007-09-19 AS REQUESTED
			
			
			
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec_con' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec_doc' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec_doc_entity' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_TXT_SUM_VAT' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_TXT_SUM_NET' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cur_row_no' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_PURCHASE_ORDER\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_inv_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_TXT_CURR_CODE' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_TXT_EXCHANGE_RATE' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_inv_no_chk' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_followup_num' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_close_sts' VALUE=\"N\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_amount' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_new_value_invoice' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='HID_IRR' VALUE=0 >");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Purchase Order - New </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//----modified by : delanjali-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//----date				: 2007-07-16-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//2007-07-16---out.println("<tr><td width='10%' align='center'><input type=\"button\" name='btn_new' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//2007-07-16---out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
			//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			out.println("<tr><td width='10%' align='center'><input type=\"button\" name='btn_new' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_save'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  // commented by udara 28-03-2014
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_save'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='m_validate_insurance()' value=\"Save\"></td>");  // added by udara 28-03-2014
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_help'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_cancel'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_close'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen_pur()' value=\"Close\"></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_letter'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
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
			
			out.println("<table align='center' width='100%' border='0' class='table'>"); 
			
			out.println("<tr class=tr_input>"); 
			
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_PURCHASE_ORDER_NO'  class=div_input>Purchase Order No</DIV></td>"); 
			
			out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_PURCHASE_ORDER_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_PURCHASE_ORDER_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update('1','10','0','m_help_TXT_PURCHASE_ORDER_NO','99')\" disabled></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application Number * </DIV></td>"); 
			out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_1('1','10','0','m_help_TXT_APPLICATION_NO_PUR_ORD','1')\"></td>"); //m_help_TXT_APPLICATION_NO  //help_button_1('1','10','0','m_help_TXT_APPLICATION_NO_PUR_ORD','1')
			out.println("</tr>"); 
			
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VENDOR_CODE'  class=div_input>Vendor Code *</DIV></td>"); 
			out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_VENDOR_CODE' maxlength='10' size='10' disabled onblur=\"assignState('M56'),makeRequest(document.Form1.TXT_VENDOR_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_VENDOR_CODE' value=\"Help\" onClick=\"help_button_2('1','10','0','m_help_SUPPLIER_PURCHASE_ORDER','2')\"></td>"); 
			//out.println("</tr>"); 
			
			//out.println("<tr class=tr_input>"); 
			out.println("<td >Vendor Name</td>"); 
			out.println("<td ><input class='txt_input' type='text' name='TXT_VENDOR_NAME' maxlength='20' style=\"width:250px;\"  disabled></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>Branch ID *</DIV></td>"); 
			out.println("<td width='28%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10' disabled onblur=\"assignState('M72'),makeRequest(document.Form1.TXT_BRANCH_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_BRANCH_CODE' value=\"Help\" disabled onClick=\"help_button_branch_pur('1','10','0','m_help_BRANCH_CODE_PURCHASE_ORDER','4')\"></td>"); 
			//out.println("</tr>"); 
			
			//out.println("<tr class=tr_input>"); 
			out.println("<td >Branch Name</td>"); 
			out.println("<td ><input class='txt_input' type='text' name='TXT_BRANCH_NAME'  style=\"width:150px;\" maxlength='20'  disabled></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>");
			out.println("<td ID=DIV_ACTIVATED_DATE>Activated Date</td>");
			
			// commented by udara 04-07-2016
			/*
			out.println("<td><input name=\"TXT_ACT_DATE_DD\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=validate_date_is_correct(document.Form1.TXT_ACT_DATE_DD,document.Form1.TXT_ACT_DATE_MM,document.Form1.TXT_ACT_DATE_YY,'A')> ");
			out.println("    <input name=\"TXT_ACT_DATE_MM\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"   onchange=validate_date_is_correct(document.Form1.TXT_ACT_DATE_DD,document.Form1.TXT_ACT_DATE_MM,document.Form1.TXT_ACT_DATE_YY,'A')> ");
			out.println("    <input name=\"TXT_ACT_DATE_YY\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\"  onchange=validate_date_is_correct(document.Form1.TXT_ACT_DATE_DD,document.Form1.TXT_ACT_DATE_MM,document.Form1.TXT_ACT_DATE_YY,'A')><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
			*/
			
			// added by udara 04-07-2016
			out.println("<td><input name=\"TXT_ACT_DATE_DD\"  type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"   onchange=validate_date_is_correct(document.Form1.TXT_ACT_DATE_DD,document.Form1.TXT_ACT_DATE_MM,document.Form1.TXT_ACT_DATE_YY,'A') disabled > ");
			out.println("    <input name=\"TXT_ACT_DATE_MM\"  type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"   onchange=validate_date_is_correct(document.Form1.TXT_ACT_DATE_DD,document.Form1.TXT_ACT_DATE_MM,document.Form1.TXT_ACT_DATE_YY,'A') disabled > ");
			out.println("    <input name=\"TXT_ACT_DATE_YY\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\"   onchange=validate_date_is_correct(document.Form1.TXT_ACT_DATE_DD,document.Form1.TXT_ACT_DATE_MM,document.Form1.TXT_ACT_DATE_YY,'A') disabled > "); // <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>
			out.println("</td>");
			// end by udara 04-07-2016
			
			//out.println("<td ></td>");
			//out.println("<td></TD>");
			//out.println("</tr>");
			
			//out.println("<tr class=tr_input>");
			out.println("<td ID=DIV_NEXT_DATE style=\"display:none;\" >Next Payment Date</td>"); // mod by udara on 12-08-2016 by adding style="display:none;"
			
			// commented by udara 04-07-2016
			/*
			out.println("<td><input name=\"TXT_NEXT_DATE_DD\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=validate_date_is_correct(document.Form1.TXT_NEXT_DATE_DD,document.Form1.TXT_NEXT_DATE_MM,document.Form1.TXT_NEXT_DATE_YY,'N')> ");
			out.println("    <input name=\"TXT_NEXT_DATE_MM\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"   onchange=validate_date_is_correct(document.Form1.TXT_NEXT_DATE_DD,document.Form1.TXT_NEXT_DATE_MM,document.Form1.TXT_NEXT_DATE_YY,'N')> ");
			out.println("    <input name=\"TXT_NEXT_DATE_YY\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\"  onchange=validate_date_is_correct(document.Form1.TXT_NEXT_DATE_DD,document.Form1.TXT_NEXT_DATE_MM,document.Form1.TXT_NEXT_DATE_YY,'N')><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
			*/
			
			// added by udara 04-07-2016
			out.println("<td style=\"display:none;\" ><input name=\"TXT_NEXT_DATE_DD\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"   onchange=validate_date_is_correct(document.Form1.TXT_NEXT_DATE_DD,document.Form1.TXT_NEXT_DATE_MM,document.Form1.TXT_NEXT_DATE_YY,'N') disabled > "); // mod by udara on 12-08-2016 by adding style="display:none;"
			out.println("    <input name=\"TXT_NEXT_DATE_MM\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"   onchange=validate_date_is_correct(document.Form1.TXT_NEXT_DATE_DD,document.Form1.TXT_NEXT_DATE_MM,document.Form1.TXT_NEXT_DATE_YY,'N') disabled > ");
			out.println("    <input name=\"TXT_NEXT_DATE_YY\"   type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\"   onchange=validate_date_is_correct(document.Form1.TXT_NEXT_DATE_DD,document.Form1.TXT_NEXT_DATE_MM,document.Form1.TXT_NEXT_DATE_YY,'N') disabled > "); // <a href style='{cursor:hand; }' onclick=load_calendar('3') disabled >   Calendar</a>
			out.println("</td>");
			// end by udara 04-07-2016
			
			
			//out.println("<td ></td>");
			//out.println("<td></TD>");
			out.println("</tr>");
			
			
			out.println("</table>"); 
			out.println("<table align='center' width='100%' border='0' class='table'>"); 
			out.println("<tr class=tr_input>"); 
			out.println("<td width='20%' >Amount To be Charged</td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_OTHER_CHATGES' maxlength='20' size='20' STYLE=\"{text-align:right;}\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_DETAILS' value=\"Details\" onClick=\"display_charges()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_DET_BREAK_DOWN' value=\"Break Down\" onClick=\"display_break_down()\" style=\"{width:80px;}\" ></td>"); 
			out.println("</tr>");
			
			
			//******************************************************************************************************************************************************************************************************
			//*******(2007-02-08)***********************************************************************************************************************************************************************************
			//*******(delanjali)************************************************************************************************************************************************************************************
			
			//out.println("<tr class=tr_input>"); 
			//out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No</DIV></td>"); 
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='10' onblur=\"assignState('J'),makeRequest(document.Form1.TXT_FINANCE_NO)\">"); 
			//out.println("</tr>"); 
			
			
			//******************************************************************************************************************************************************************************************************
			
			
			out.println("</table>"); 
			
			out.println("<hr>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width='*%'><input class='but_input' type='button' name='MORE_BUT' value=\"Add\" onClick=\"add_row()\" disabled ></td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			
			out.println("</table>");
			
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table2'></DIV></td>");
			out.println("</tr>"); 
			
			out.println("</table>");
			
			
			
			
			
			out.println("<hr>");
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr align='left' class='tr_input'>");  
			out.println("<td width='*%'><b><u>Condition List</td> ");
			out.println("</tr>");  
			
			out.println("<tr align='left'>");  
			out.println("<td width='10'><input class='but_input' type='button' name='MORE_BUT_CON' value=\"Add\" onClick=\"add_row_conditions()\"></td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_con'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<br>");
			
			
			
			rs2= stmt2.executeQuery ("SELECT DISTINCT ISSUER_CODE,TO_CHAR(AMOUNT,'999,999,999,999,999.99'),TO_CHAR(ISSUED_DATE,'DD-MM-YYYY'),TO_CHAR(START_DATE,'DD-MM-YYYY'), "+
				"TO_CHAR(END_DATE,'DD-MM-YYYY'),STATUS "+
				"FROM "+m_schema_name+".AF_CO_PRO_APP_BANK_GUARANTEES "+	
				"WHERE APPLICATION_NO = '"+m_application_no+"'  "+
				"AND STATUS='Y' ");
			
			
			boolean more2=rs2.next();	
			int j=0;
			
			
			
			m_string=m_string+"<table align='center' width='100%' class='table' border=\"0\">"; 
			
			
			while(more2){
				
				if(j==0){
					m_string=m_string+"<HR>";
					
					m_string=m_string+"<tr>";
					m_string=m_string+"<td width='20%' style='{text-align:left;}'><b><u>Issuer Details :-</td>";
					m_string=m_string+"</tr>";
					
					m_string=m_string+"<tr>";
					m_string=m_string+"</tr>";
					m_string=m_string+"<tr>";
					m_string=m_string+"</tr>";
					m_string=m_string+"<tr class=\"pdn_txtpos2\">";
					
					m_string=m_string+"<td width='20%' style='{text-align:left;}'><b>Issuer Code</td>";
					m_string=m_string+"<td width='20%' style='{text-align:left;}'><b>Issuer Amount</td>";
					m_string=m_string+"<td width='20%' style='{text-align:left;}'><b>Issuer Date</td>";
					m_string=m_string+"<td width='20%' style='{text-align:left;}'><b>Start Date</td>";
					m_string=m_string+"<td width='20%' style='{text-align:left;}'><b>End Date</td></tr>";
					m_string=m_string+"<tr>";
				}
				
				
				m_string=m_string+"<td width=\"20%\" >"+rs2.getString(1)+"</td>";
				m_string=m_string+"<td width=\"20%\" >"+rs2.getString(2)+"</td>";
				m_string=m_string+"<td width=\"20%\" >"+rs2.getString(3)+"</td>";
				m_string=m_string+"<td width=\"20%\" >"+rs2.getString(4)+"</td>";
				m_string=m_string+"<td width=\"20%\" >"+rs2.getString(5)+"</td></tr>";
				
				more2=rs2.next();
				j=j+1;
			}
			
			m_string=m_string+"</table>";
			
			
			out.println(m_string);
			
			
			
			/*out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
				
			
			while(more2){
				
				if(j==0){
		  out.println("<HR>");

			out.println("<tr>");
			out.println("<td width='20%' style='{text-align:left;}'><b><u>Issuer Details :-</td>");
			out.println("</tr>");
							
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr class=\"pdn_txtpos2\">");
						
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Code</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Amount</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Date</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Start Date</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>End Date</td></tr>");
			out.println("<tr>");
			}
		
		
			out.println("<td width=\"20%\" >"+rs2.getString(1)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(2)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(3)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(4)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(5)+"</td></tr>");
			
			more2=rs2.next();
			j=j+1;
			}

			out.println("</table>");
			
			*/
			
			out.println("<br><br><br>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" name='btn_new2' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete2' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_save2'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  // commented by udara 28-03-2014
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_save2'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='m_validate_insurance()' value=\"Save\"></td>"); // added by udara 28-03-2014
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_help2'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_cancel2'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_close2'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen_pur()' value=\"Close\"></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_letter2'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
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
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			//    }
		}catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			//if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			//if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			//if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
		}
	}
}


