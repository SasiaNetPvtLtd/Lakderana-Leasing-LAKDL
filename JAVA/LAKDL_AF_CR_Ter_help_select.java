//Option Id is 6.0  
//This File was created by SVA on 17-05-2006 
//Marketing Help SQL
import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_CR_Ter_help_select  {  
	
	//Enter Filds Here
	
	public Object Ret_Object = new Object();
	public String ret_str;
	//String m_schema_name="";
	
	//Put the Sqlnames Bigining with m_*
	
	LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
	String m_schema_name=m_sn_methods.schema_name.trim();
	public String  ClientSqlteNEW="";
	public String ClientSqlteNEW_Header   		    = "Client Help ";
	public String   ClientSqlte12 ="";
	public String ClientSqlte12_Header   		    = "Client Help ";
	public String  ClientSqlte1     ="";
	public String ClientSqlte1_Header   		    = "Client Help ";
	public String ClientSql          		    = " ";
	public String ClientSql_Header   		    = "Client Help ";
	public String ClientTSql          	    = " ";
	public String ClientTSql_Header   	    = "Client Help "; 
	public String ClientTSql1          	    = " ";
	public String ClientTSql1_Header   	    = "Client Help "; 
	public String ClientTSql2          	    = " ";            //Added by Prabash on 10-02-2012
	public String ClientTSql2_Header   	    = "Client Help ";  
	public String PayeeSql          	    = " ";			  //Added by Prabash on 10-02-2012
	public String PayeeSql_Header   	    = "Payee Help "; 
	public String PayeeSql2          	    = " ";			  //Added by Prabash on 10-02-2012
	public String PayeeSql2_Header   	    = "Payee Help "; 
	public String Paymentno1          	    = " ";			  //Added by Prabash on 13-02-2012
	public String Paymentno1_Header   	    = "Payment no Help ";
	public String Paymentno2          	    = " ";			  //Added by Prabash on 14-02-2012
	public String Paymentno2_Header   	    = "Payment no Help "; 
	public String Termination1          	    = " ";			  //Added by Prabash on 15-02-2012
	public String Termination1_Header   	    = "Termination no Help ";
	public String VehicleSql         		    = " ";
	public String VehicleSql_Header         = " Help ";
	public String LeaseSql         		      = " ";
	public String LeaseSql_Header           = " Help ";
	public String TerminationNoSql 		      = " ";
	public String TerminationNoSql_Header   = " Help ";
	
	public String Finance_no_Sql 		      = " "; //Added By Sanudun on 03-12-2008
	public String Finance_no_Sql_Header   = "Finance No Help ";
	
	public String finNoTermAlloSql = "";
	public String finNoTermAlloSql_Header ="Help ";
	
	
	public String PO_GENERATIONSql = "";
	public String PO_GENERATIONSql_Header ="Help ";
	
	public String PO_GENERATIONSql2 = "";
	public String PO_GENERATIONSql2_Header ="Help ";
	
	public String PO_GENERATIONSql3 = "";
	public String PO_GENERATIONSql3_Header ="Help ";
	
	// Method for Sql Put Sqls Inside
	
	public Object getSql(Object reqObj1,Object reqObj2,Object reqObj3,Object reqObj4) {
		String Sql_Name  = (String) reqObj1;
		String Start_Val = (String) reqObj2;
		String End_Val   = (String) reqObj3;	
		String Criteria	 = (String) reqObj4;
		
		int m_val =(Integer.parseInt(End_Val));
		m_val++;
		End_Val = Integer.toString(m_val);
		
		Vector m_vector = new Vector();
		String m_substring="";
		int start_index, stop_index, cnt, i;
		start_index = 0;
		stop_index = 0;
		cnt = 0;
		int m_length = Criteria.lastIndexOf("@");
		
		if (m_length!=0) {
			//m_debtorsql	= "inside if "  + m_length;
			while (stop_index<m_length) {
				try {
					stop_index = Criteria.trim().indexOf("@",start_index);
					m_substring = Criteria.substring(start_index,stop_index);
					if (m_substring.length()>0) {
						m_vector.addElement(m_substring);
					}
					else {
						m_vector.addElement("");
					}
					start_index = stop_index+1;
				}
				catch (Exception e) {
					stop_index=m_length;
				}
			}
		}
		else if (m_length==0) {
			//m_debtorsql	= "inside else if "  + m_length;
			m_vector.addElement("");
		}
		
		cnt = m_vector.size();
		
		for ( i = cnt ; i < 8 ; i++ ) {
			m_vector.addElement("");
		}
		
		
		
		/*----------------------------------------------------------------
			Purpose  : select clients
		
			Used in  : CR TERMINATION 
		-----------------------------------------------------------------*/			
		
		ClientSql =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS "+
			"FROM "+
			"(SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS  "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        TEL_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       EMAIL     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        NIC_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
			"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
			"                        FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			"                      	 WHERE  APPLICATION_STATUS = 'ACTIVATED' AND "+
			"                               FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%')) "+
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
		
		ClientSqlte1 =   	/*"SELECT P.NO, P.FINANCE_NO FINANCE_NO, P.CLIENT_CODE CLIENT_NO,P.FIRST_NAME NAME,"+m_schema_name+".AF_CO_GET_VEHI(A.APPLICATION_NO) VEHICLE "+
			"FROM "+
			"(SELECT ROWNUM NO, FINANCE_NO, CLIENT_CODE,FIRST_NAME,"+m_schema_name+".AF_CO_GET_VEHI(A.APPLICATION_NO) "+
			"FROM "+
			"(SELECT A.FINANCE_NO , A.CLIENT_CODE, B.FIRST_NAME,"+m_schema_name+".AF_CO_GET_VEHI(A.APPLICATION_NO)  "+
			"	 FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B  "+
			"	 WHERE A.COLLECTION_OFFICER=B.EMP_CODE AND (UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(A.CLIENT_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"        UPPER(B.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"        UPPER(B.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR  "+
			"        UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE))     LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR  "+
			"        UPPER("+m_schema_name+".AF_CO_GET_CLIENT_CODE(A.APPLICATION_NO))     LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR  "+
			"        UPPER("+m_schema_name+".AF_CO_GET_VEHI(A.APPLICATION_NO))     LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR  "+
			"        UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR "+
			"        UPPER("+m_schema_name+".AF_CO_GET_VEHI(A.APPLICATION_NO))     LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR  "+
			
			"        UPPER(A.APPLICATION_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND A.APPLICATION_STATUS = 'ACTIVATED' "+
			" ORDER BY A.CLIENT_CODE )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	*/
			
			" "+
			" SELECT R.AAA, R.FINANCE_NO, R.Name, R.Vehicle_No, R.ADDRESS1, R.ADDRESS2, R.CITY_NAME, R.Status, R.Client  FROM ( "+ // new
			" SELECT  ROWNUM AAA, P.NO, P.FINANCE_NO FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 ADDRESS1 , P.ADDRESS2 ADDRESS2,P.CITY_NAME CITY_NAME,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+ // new
			//"SELECT DISTINCT P.NO, P.FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+
			" "+
			"FROM "+
			"(SELECT DISTINCT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE "+
			"FROM "+
			"(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE,  "+ //added by nuwan de silva 25-07-07
			"    "+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO) VEHICLE "+//ADDED MILINDA 
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			" 			 B.FINANCE_NO IS NOT NULL AND "+ //added by Prabash on 16-06-2012
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"		     C.ACTIVE_STATUS IN ('Y','T') AND  "+ /*added by ns on 14-12-2012*/
			"            B.APPLICATION_STATUS NOT IN ('CANCEL') AND "+ // added by udara on 26-06-2013 // ,'TERMI'
			/*"    (   UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
			"        FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        CHASSIS_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
			"            NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO),' ') LIKE '%"+m_vector.elementAt(0)+"%' OR "+ // added by udara on 11-07-2013
			
			"        c.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+*/
			//ADDED MILINDA 2014-02-06
			" (b.CLIENT_CODE LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(a.FULL_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" a.CLIENT_CODE LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			//" UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" A.CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//" UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//" UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" B.FINANCE_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" b.APPLICATION_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" A.NIC_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//" UPPER(c.ENGINE_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(C.REG_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" "+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO) LIKE '%"+m_vector.elementAt(0)+"%') "+ 
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ // new 
			//"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			" WHERE R.AAA >= "+ Start_Val+" AND R.AAA <= "+End_Val+" ";
		
		ClientSqlte12 =   	"SELECT P.NO, P.GUARANTOR_CODE GAR, P.APPLICATION_NO APP,GARANTOR GARANTOR "+
			"FROM "+
			"(SELECT ROWNUM NO, GUARANTOR_CODE, APPLICATION_NO,GARANTOR "+
			"FROM "+
			"(SELECT A.GUARANTOR_CODE , B.APPLICATION_NO,AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE) GARANTOR  "+
			"	 FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B   "+
			" WHERE A.APPLICATION_NO = B.APPLICATION_NO(+) "+ 
			" AND   A.ACTIVE_STATUS='Y' "+
			" AND B.APPLICATION_STATUS NOT IN ('CANCEL') "+
			" AND   B.FINANCE_NO  = UPPER('"+m_vector.elementAt(1)+"') "+
			
			" ORDER BY A.GUARANTOR_CODE )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
			
		
		ClientSqlteNEW =   	"SELECT P.NO, P.CLIENT_CODE CLIENT, P.FULL_NAME FULL_NAME,P.APPLICATION_NO APPLICATION "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,APPLICATION_NO "+
			"FROM "+
			"(SELECT A.CLIENT_CODE , A.FULL_NAME,B.APPLICATION_NO  "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B   "+
			"	 WHERE A.CLIENT_CODE = B.CLIENT_CODE(+) AND (UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(A.ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       A.CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        A.TEL_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(A.EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//"        UPPER(A.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        A.NIC_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//"	       UPPER("+m_schema_name+".AF_CO_GET_APP(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
			"	       "+m_schema_name+".AF_CO_GET_APP(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
			"        A.CLIENT_CODE IN (SELECT AA.CLIENT_CODE "+
			"                        FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS AA "+
			"                      	 WHERE  AA.APPLICATION_STATUS NOT IN ('CANCEL') AND "+
			"                               AA.FINANCE_NO = UPPER('"+m_vector.elementAt(1)+"')) AND ROWNUM=1 "+
			" ORDER BY A.CLIENT_CODE )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		/*----------------------------------------------------------------
			Purpose  : select clients
		
			Used in  : CR TERMINATION Allo and check
		-----------------------------------------------------------------*/			
		
		ClientTSql = 		"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS "+
			"FROM "+
			"(SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS  "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       CITY_CODE   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        TEL_NO      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(EMAIL)       LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        NIC_NO      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
			"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
			"                        FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
			//	"                        WHERE ACTIVE_STATUS = 'ENT' AND "+ //comment by Prabash on 10-02-2012
			"                        WHERE ACTIVE_STATUS = 'APPRO2'  "+ //Added by Prabash on 10-02-2012
			//	"                         AND TERMINATION_VALIDITY_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY')"+  //comment by Prabash on 10-02-2012
			" 		) "+
			
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		//---Added by Prabash on 10-02-2012----Client Code chack for --Credit - Purchase Order Approval---* 	
		ClientTSql2 = 		"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS "+
			"FROM "+
			"(SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS  "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	     CITY_CODE   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        TEL_NO      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	     UPPER(EMAIL)       LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        NIC_NO      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	     BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
			"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
			"                        FROM    "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" 						 WHERE A.APPLICATION_NO = B.APPLICATION_NO						"+
			"                        AND ACTIVE_STATUS IN ('ENT','ENT_N','ENT_D')) "+ 		
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------*
		//--Added by Prabash on 10-02-2012--Payee Name chack for--Finance-- Other Payments Approval 1--* 	
		
		PayeeSql = 		"SELECT P.NO, P.PAYEE_NAME Payee, P.PAYMENT_NO Paynent_no, SUS_REF_NO Ref_no "+
			"FROM "+
			"(SELECT ROWNUM NO,PAYEE_NAME, PAYMENT_NO, SUS_REF_NO "+
			"FROM "+
			"(SELECT A.PAYEE_NAME,A.PAYMENT_NO, A.SUS_REF_NO  "+
			"	 FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
			"	 WHERE (UPPER(PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR  "+
			"    PAYMENT_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
			" 	 AND PROCESS_STATUS='Y'    "+
			"    AND  ENTRY_TYPE<>'V'      "+
			" ORDER BY PAYMENT_NO )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------*
		//--Added by Prabash on 10-02-2012--Payee Name chack for--Finance-- Other Payments Approval 2--* 	
		
		PayeeSql2 = 		"SELECT P.NO, P.PAYEE_NAME Payee, P.PAYMENT_NO Paynent_no, SUS_REF_NO Ref_no "+
			"FROM "+
			"(SELECT ROWNUM NO,PAYEE_NAME, PAYMENT_NO, SUS_REF_NO "+
			"FROM "+
			"(SELECT A.PAYEE_NAME,A.PAYMENT_NO, A.SUS_REF_NO  "+
			"	 FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
			"	 WHERE (UPPER(PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR  "+
			"    PAYMENT_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
			" 	 AND  PROCESS_STATUS='APPRO1'    "+
			"    AND  ENTRY_TYPE<>'V'      "+
			" ORDER BY PAYMENT_NO )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------*
		//--Added by Prabash on 13-02-2012---Payment no  chack for--Finance--Payments Approval 1 & 2--* 	
		
		Paymentno1 = 		"SELECT P.NO, P.GROUP_PAYMENT_NO Payment_No,NVL(P.PAYEE_NAME,'-')Payee, P.SETTLE_MODE Settle_Mode "+
			"FROM "+
			"(SELECT ROWNUM NO,GROUP_PAYMENT_NO,PAYEE_NAME,SETTLE_MODE "+
			"FROM "+
			"(SELECT B.GROUP_PAYMENT_NO,B.PAYEE_NAME,B.SETTLE_MODE  "+
			"	 FROM "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A,"+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B "+
			"	WHERE A.PAYMENT_NO = B.PAYMENT_NO"+
			"      AND  B.PROCESS_STATUS  LIKE UPPER('"+m_vector.elementAt(1)+"') "+
			"      AND  A.ENTRY_TYPE = 'V' "+
			"      AND  B.GROUP_PAYMENT_NO IS NOT NULL "+
			" AND (B.GROUP_PAYMENT_NO  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"      A.SETTELED_AMOUNT  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"      UPPER(B.PAYEE_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%'))		"+
			"   GROUP BY B.GROUP_PAYMENT_NO,  	"+
			"               B.PAYEE_NAME,  		"+
			"               B.SETTLE_MODE 		"+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//-------------------------------------------------------------------------------------------*
		//--Added by Prabash on 14-02-2012--Payment no  chack for--Finance-- Cheque Disbursement-----* 	
		
		Paymentno2 = 		"SELECT P.NO, P.PAYMENT_NO PAYMENT_NO, NVL(P.PAYEE_NAME,'-') PAYEE, CHEQUE_NO CHEQUE_NO,PAYMENT_TYPE TYPE "+
			"FROM "+
			"(SELECT ROWNUM NO,PAYMENT_NO,PAYEE_NAME,CHEQUE_NO,PAYMENT_TYPE "+
			"FROM "+
			"(SELECT B.GROUP_PAYMENT_NO PAYMENT_NO, "+
			"		 B.PAYEE_NAME, "+
			"		 B.CHEQUE_NO, "+
			"		'VENDOR' PAYMENT_TYPE "+
			"		FROM    "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+
			"		"+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B "+
			"		WHERE  A.PAYMENT_NO = B.PAYMENT_NO "+
			"		AND    B.PROCESS_STATUS = 'PRINT' "+
			"		AND    A.ENTRY_TYPE = 'V' "+
			"		AND    B.GROUP_PAYMENT_NO IS NOT NULL"+
			" 		AND (B.GROUP_PAYMENT_NO  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"      	UPPER( B.PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"      	B.CHEQUE_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			"		GROUP BY B.GROUP_PAYMENT_NO, "+
			"		B.PAYEE_NAME, "+
			"		B.SETTLE_MODE, "+
			"		B.CHEQUE_NO "+
			
			"		UNION ALL"+
			
			"		SELECT A.PAYMENT_NO, "+
			"		A.PAYEE_NAME, "+
			"		A.CHEQUE_NO, "+
			"		'OTHER' PAYMENT_TYPE "+
			"		FROM    "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
			"		WHERE  A.PROCESS_STATUS = 'PRINT' "+
			" 		AND (A.PAYMENT_NO  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"      	UPPER( A.PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"      	A.CHEQUE_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+	
			"		AND    A.GROUP_PAYMENT_NO IS NULL     "+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//------------------------------------------------------------------------------------------*
		//--Added by Prabash on 15-02-2012---Termination No  chack for--Termination--Legaltermination-Approval 1 & 2--* 	
		
		Termination1 = 		"SELECT P.NO, P.TERMINATION_NO Termination_No ,P.FINANCE_NO Finance_No, P.APPLICATION_NO Application_No,P.CLIENT_NAME Client "+
			"FROM "+
			"(SELECT ROWNUM NO,TERMINATION_NO,FINANCE_NO,APPLICATION_NO,CLIENT_NAME "+
			"FROM "+
			"(SELECT TERMINATION_NO,FINANCE_NO,APPLICATION_NO, "+
			"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME"+
			"  FROM   "+m_schema_name+".AF_CR_PRO_LEGAL_TERMINATION "+
			"  WHERE (TERMINATION_NO  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"      FINANCE_NO  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"      UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE))    LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"  AND ACTIVE_STATUS =UPPER('"+m_vector.elementAt(1)+"') AND  CLIENT_CODE LIKE '%"+m_vector.elementAt(2)+"%' "+
			
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//-------------------------------------------------------------------------------------------*
		
		// THAMALI 2012.02.15
		PO_GENERATIONSql = 		"SELECT P.NO, P.CLIENT_CODE Client_Code ,P.CLIENT_NAME Client_Name "+
			"FROM "+
			"(SELECT ROWNUM NO,CLIENT_CODE,CLIENT_NAME "+
			"FROM "+
			"(SELECT DISTINCT CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME "+
			"  FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"  WHERE (CLIENT_CODE LIKE '%"+m_vector.elementAt(0)+"%' OR 	"+
			"        UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE))    LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        APPLICATION_STATUS IN ('VERIFYL') AND "+
			"        "+m_schema_name+".AF_CO_PUR_ORDER_GEN_COUNT(APPLICATION_NO) > 0 "+ 
			"  ORDER BY CLIENT_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		// THAMALI 2012.02.15
		PO_GENERATIONSql2 = 		"SELECT P.NO, P.CLIENT_CODE Client_Code ,P.CLIENT_NAME Client_Name, P.APPLICATION_NO Application_No ,P.REG_NO,P.FINANCE_NO "+
			"FROM "+
			"(SELECT ROWNUM NO,CLIENT_CODE,CLIENT_NAME,APPLICATION_NO,REG_NO,FINANCE_NO "+
			"FROM "+
			"(SELECT CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME,APPLICATION_NO,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (FINANCE_NO),'-')REG_NO, "+ 
			"  NVL(FINANCE_NO,'-') FINANCE_NO "+    //added by prabash on 13-07-2012
			"  FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"  WHERE (CLIENT_CODE LIKE '%"+m_vector.elementAt(0)+"%' OR 	"+
			"        APPLICATION_NO  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"  UPPER("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (FINANCE_NO)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //Added by Prabash on 22-03-2012
			
			"        UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE))    LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        APPLICATION_STATUS IN ('VERIFYL') AND "+
			"        "+m_schema_name+".AF_CO_PUR_ORDER_GEN_COUNT(APPLICATION_NO) > 0 "+ 
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		// THAMALI 2012.02.15
		PO_GENERATIONSql3 = 		"SELECT P.NO, P.CLIENT_CODE Client_Code ,P.CLIENT_NAME Client_Name, P.APPLICATION_NO Application_No,P.REG_NO,P.FINANCE_NO "+
			"FROM "+
			"(SELECT ROWNUM NO,CLIENT_CODE,CLIENT_NAME,APPLICATION_NO,REG_NO,FINANCE_NO "+
			"FROM "+
			"(SELECT CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME,APPLICATION_NO,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (FINANCE_NO),'-')REG_NO, "+
			"  NVL(FINANCE_NO,'-') FINANCE_NO "+    //added by prabash on 13-07-2012
			"  FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"  WHERE (CLIENT_CODE = '"+m_vector.elementAt(1)+"' AND 	"+
			"        APPLICATION_NO  LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND  "+
			//"        UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE))    LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"        APPLICATION_STATUS IN ('VERIFYL') AND "+
			"        "+m_schema_name+".AF_CO_PUR_ORDER_GEN_COUNT(APPLICATION_NO) > 0 "+ 
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
		//Added by Dineth on 2008-09-02
		
		ClientTSql1 =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS "+
			"FROM "+
			"(SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS  "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       CITY_CODE   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        TEL_NO      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(EMAIL)       LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        NIC_NO      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
			"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
			"                        FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
			"                        WHERE  ACTIVE_STATUS = 'ENT' AND "+
			"                               TERMINATION_VALIDITY_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY')) "+
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		
		//Added by Dineth on 2008-09-02
		
		
		
		
		
		
		/*----------------------------------------------------------------
				Purpose  : select Lease No
			
				Used in  : CR Termination
			-----------------------------------------------------------------*/			
		
		
		// commented by udara 29-06-2015
		/*
		LeaseSql = "SELECT P.NO,FINANCE_NO \"Finance No\",APPLICATION_NO \"Application No\",Name,FACILITY_NO Facility, "+
			"		      CLIENT_CODE Code,APPLICATION_STATUS Status,CO_APPLICANT "+
			" ,NVL("+m_schema_name+".AF_CO_CLOSING_RATE(APPLICATION_NO),0) \"Closing Rate\" "+ // added by udara on 12-09-2012
			"FROM "+
			"(SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,Name, "+
			"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO "+
			"FROM "+
			"( SELECT FINANCE_NO,APPLICATION_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) Name, "+
			"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO "+
			"	 FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			//"	 WHERE  APPLICATION_STATUS = 'ACTIVATED' AND "+
			//"	 WHERE  APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') AND "+
			" WHERE  "+
			"         CLIENT_CODE LIKE '%"+m_vector.elementAt(1)+"%' AND "+
			"         (FINANCE_NO LIKE '%"+m_vector.elementAt(0)+"%' OR  "+
			"          APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%') )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		*/
		
		// added by udara 29-06-2015
		LeaseSql = "SELECT P.NO,FINANCE_NO \"Finance No\",APPLICATION_NO \"Application No\",Name,FACILITY_NO Facility, "+
			"		      CLIENT_CODE Code,APPLICATION_STATUS Status,CO_APPLICANT "+
			" ,NVL("+m_schema_name+".AF_CO_CLOSING_RATE(APPLICATION_NO),0) \"Closing Rate\", RENTAL_FREEZ \"Rental Freez Status\" , PLEDGE_STATUS \"Pledge Status\"  "+ // added RENTAL_FREEZ \"Rental Freez Status\" by udara 11-12-2017 // added by udara on 12-09-2012
			"FROM "+
			"(SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,Name, "+
			"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO, RENTAL_FREEZ, PLEDGE_STATUS "+ // added RENTAL_FREEZ by udara on 11-12-2017
			"FROM "+
			"( SELECT FINANCE_NO,APPLICATION_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) Name, "+
			"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO, DECODE(RENTAL_FREEZ,'N','NO','Y','YES') RENTAL_FREEZ, "+ // added DECODE(RENTAL_FREEZ,'N','NO','Y','YES') RENTAL_FREEZ by udara on 11-12-2017
			" "+m_schema_name+".AF_CO_GET_PLEDGE_STATUS(FINANCE_NO) PLEDGE_STATUS "+ // added by udara 10-10-2018
			"	 FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			//"	 WHERE  APPLICATION_STATUS = 'ACTIVATED' AND "+
			"	 WHERE  APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS')  "+//Uncommented by Jithendra 06-01-2016
			/*" WHERE  "+
			
			" APPLICATION_STATUS NOT IN ('VERIFY2','VERIFY-M','ENT_CON','ENTERED','VERIFYL','V-RECOM','V-APP','VERIFY1') "+*/  //Commented by Jithendra 06-01-2016
			
			"        AND  CLIENT_CODE LIKE '%"+m_vector.elementAt(1)+"%' AND "+
			"         (FINANCE_NO LIKE '%"+m_vector.elementAt(0)+"%' OR  "+
			"          APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%') )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
				Purpose  : select Vehicle No
			
				Used in  : CR Termination
			-----------------------------------------------------------------*/			
		
		VehicleSql = "SELECT P.NO,REG_NO,APPLICATION_NO,Name,CLIENT_CODE,ASSET_ID, "+
			"       ENGINE_NO,CHASSIS_NO,REG_DATE "+
			"FROM "+
			"(SELECT ROWNUM NO,REG_NO,APPLICATION_NO,Name,CLIENT_CODE, "+
			"        ASSET_ID,ENGINE_NO,CHASSIS_NO,REG_DATE "+
			"FROM "+
			"( SELECT A.REG_NO,A.APPLICATION_NO,"+
			"         "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) Name, "+
			"         B.CLIENT_CODE, A.ASSET_ID, A.ENGINE_NO,A.CHASSIS_NO, "+
			"         A.REG_DATE "+
			"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+
			"         "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"  WHERE  A.APPLICATION_NO= B.APPLICATION_NO AND APPLICATION_STATUS = 'ACTIVATED' AND "+
			"         ACTIVE_STATUS = 'Y' AND "+
			"          FINANCE_NO LIKE '%"+m_vector.elementAt(2)+"%' AND "+
			"          CLIENT_CODE LIKE '%"+m_vector.elementAt(1)+"%' AND  "+
			"         (A.APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%' OR "+
			"          REG_NO LIKE '%"+m_vector.elementAt(0)+"%') )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		/*----------------------------------------------------------------
				Purpose  : select Termination No
			
				Used in  : CR Termination
			-----------------------------------------------------------------*/			
		
		TerminationNoSql = "SELECT P.NO,TERMINATION_NO, FINANCE_NO, APPLICATION_NO, CLIENT_CODE, "+
			"        TERMINATION_VALIDITY_DATE,APPLY_DATE,REQUESTED_BY,RATE, AMOUNT, REMARKS, "+
			"        CHARGES, TERMINATION_COUNT,DUE_AMOUNT,ACTIVE_STATUS "+
			
			"FROM "+
			"(SELECT ROWNUM NO,TERMINATION_NO, FINANCE_NO, APPLICATION_NO, CLIENT_CODE, "+
			"        TERMINATION_VALIDITY_DATE,APPLY_DATE,REQUESTED_BY,RATE, AMOUNT, REMARKS, "+
			"        CHARGES, TERMINATION_COUNT,DUE_AMOUNT,ACTIVE_STATUS "+
			"FROM "+
			"( SELECT TERMINATION_NO, FINANCE_NO, APPLICATION_NO, CLIENT_CODE, "+
			"         TO_CHAR(TERMINATION_VALIDITY_DATE,'DD-MM-YYYY') TERMINATION_VALIDITY_DATE, "+
			"         TO_CHAR(APPLY_DATE,'DD-MM-YYYY') APPLY_DATE, "+   
			"         REQUESTED_BY,RATE, AMOUNT, REMARKS, "+
			"         CHARGES, TERMINATION_COUNT,DUE_AMOUNT,ACTIVE_STATUS "+
			"  FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
			"  WHERE  ACTIVE_STATUS = 'ENT' AND "+
			"         ( TERMINATION_NO LIKE '%"+m_vector.elementAt(0)+"%' OR "+
			"           FINANCE_NO     LIKE '%"+m_vector.elementAt(0)+"%' OR  "+
			"           CLIENT_CODE    LIKE '%"+m_vector.elementAt(0)+"%' ) )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		
		
		Finance_no_Sql  = " SELECT P.NO,P.FINANCE_NO,P.TERMINATION_NO,P.CLIENT_CODE,P.CLIENT_NAME,P.REG_NO,P.OFFICER_NAME,P.DESIGNATION "+ //ADDED BY SANDUN ON 03-12-2008
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,TERMINATION_NO,CLIENT_CODE,REG_NO,COLLECTION_OFFICER,OFFICER_NAME,CLIENT_NAME,DESIGNATION "+
			" FROM "+
			" (SELECT DISTINCT A.FINANCE_NO FINANCE_NO, "+//1
			"  A.APPLICATION_NO APPLICATION_NO, "+//2
			"  A.TERMINATION_NO TERMINATION_NO, "+//3
			"  A.CLIENT_CODE CLIENT_CODE, "+//4
			"  NVL(D.REG_NO,'-') REG_NO, "+//5
			"  B.COLLECTION_OFFICER COLLECTION_OFFICER, "+//6
			"  C.TITLE || ' ' ||C.FIRST_NAME || '' || C.LAST_NAME OFFICER_NAME, "+//7
			"  "+m_schema_name+".AF_CO_GET_DESIGNATION_DESC("+m_schema_name+".AF_CO_GET_DESIGNATION_CODE(B.COLLECTION_OFFICER))  DESIGNATION, "+//8
			"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME "+//9
			"  FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".CO_CO_MAS_EMPLOYEE C, "+
			"  "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
			"  WHERE  A.APPLICATION_NO     = B.APPLICATION_NO "+ 
			"  AND    B.COLLECTION_OFFICER = C.EMP_CODE "+
			"  AND    D.APPLICATION_NO     = A.APPLICATION_NO "+
			"  AND    A.ACTIVE_STATUS      = 'TERM_CHECK' "+
			"  AND    A.FINANCE_NO LIKE '%"+m_vector.elementAt(0)+"%' "+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
				Purpose  : select Lease No
			
				Used in  : CR Termination
			-----------------------------------------------------------------*/			
		
		finNoTermAlloSql = " SELECT P.NO,FINANCE_NO \"Finance No\" "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO "+
			" FROM "+
			" (SELECT FINANCE_NO "+
			"	FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION  "+
			"	WHERE  ACTIVE_STATUS ='APPRO2' AND "+
			" FINANCE_NO LIKE '%"+m_vector.elementAt(0)+"%'  "+
			" )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		
		//================================================================================================================
		
		
		// ****Do not Alter The Parts Below
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}

/*----------------------------------------------------------------*/


