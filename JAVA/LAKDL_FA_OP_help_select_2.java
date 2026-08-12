import java.io.*;
import java.util.*;
import java.lang.*;

// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:19-10-2007

public class LAKDL_FA_OP_help_select_2  {  

	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();
	
	public String m_help_DIV_TXT_FACILITY_NON_SALE_sql="";
	public String m_help_DIV_TXT_FACILITY_NON_SALE_sql_Header="Operation Process - Facility ";

	public String m_help_DIV_TXT_RECEIPT_DETAILS_sql="";
	public String m_help_DIV_TXT_RECEIPT_DETAILS_sql_Header="Operation Process - Receipt ";

	public String m_help_DIV_TXT_FACILITY_NON_SALE_DELE_sql="";
	public String m_help_DIV_TXT_FACILITY_NON_SALE_DELE_sql_Header="Operation Process - Facility ";
	
	public String m_help_DIV_TXT_RECEIPT_DETAILS_DELE_sql="";
	public String m_help_DIV_TXT_RECEIPT_DETAILS_DELE_sql_Header="Operation Process - Receipt ";
	
	
	
	public Object getSql(Object reqObj1,Object reqObj2,Object reqObj3,Object reqObj4,Object reqObj5) {
		String Sql_Name  = (String) reqObj1;
		String Start_Val = (String) reqObj2;
		String End_Val   = (String) reqObj3;	
		String Criteria  =	(String) reqObj4;
		String m_user  =   (String) reqObj5;

		int m_val =(Integer.parseInt(End_Val));
		m_val++;
		End_Val = Integer.toString(m_val);
		
		Vector m_vector = new Vector();
		String m_substring="";
		int start_index,stop_index,cnt,i;
		start_index = 0;
		stop_index = 0;
		cnt = 0;
		int m_length = Criteria.lastIndexOf("@");
		
		if (m_length!=0) {
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
			m_vector.addElement("");
		}
		
		cnt = m_vector.size();
		
		for ( i = cnt ; i < 8 ; i++ ) {
			m_vector.addElement("");
		}
		

		
			
			
		m_help_DIV_TXT_FACILITY_NON_SALE_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
   m_help_DIV_TXT_FACILITY_NON_SALE_DELE_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+
			" SELECT DISTINCT A.FACILITY_NO,A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)  FULL_NAME "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A, "+
 			"      "+m_schema_name+".FA_OP_PRO_NON_SALE_ADJUSTMENT B "+
				
 			" WHERE A.FACILITY_STATUS='Y' AND ( (A.FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (A.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			" AND A.FACILITY_NO = B.FACILITY_NO "+
			" AND A.CLIENT_CODE = B.CLIENT_CODE "+
			" AND B.STATUS ='E' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";


		
		
		m_help_DIV_TXT_RECEIPT_DETAILS_sql=
			" SELECT L.NO,L.RECEIPT_NO,L.RECEIPT_TYPE,L.REC_AMOUNT,L.SETT_MODE, "+
			" L.CLIENT_CODE,L.FACILITY_NO,L.DEBTOR_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.RECEIPT_TYPE,P.REC_AMOUNT,P.SETT_MODE, "+
			" P.CLIENT_CODE,P.FACILITY_NO,P.DEBTOR_CODE  "+
			" FROM( "+
			" SELECT RECEIPT_NO,  "+
			" RECEIPT_TYPE, "+
			" REC_AMOUNT, "+
			" DECODE(SETTLE_MODE,'CHEQUE',SETTLE_MODE ||'-'||CHEQUE_NO,SETTLE_MODE) SETT_MODE,  "+
			" NVL(CLIENT_CODE,'-') CLIENT_CODE,  "+
			" NVL(FACILITY_NO,'-') FACILITY_NO,  "+
			" NVL(DEBTOR_CODE,'-') DEBTOR_CODE "+ 
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+
			" WHERE RECON_STATUS='Y'  AND  BALANCE_AMOUNT > 0 AND "+
			" ( CLIENT_CODE LIKE '"+m_vector.elementAt(1)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) LIKE '%"+m_vector.elementAt(1)+"%' OR "+
			" FACILITY_NO LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" DEBTOR_CODE LIKE '"+m_vector.elementAt(1)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) LIKE '%"+m_vector.elementAt(1)+"%'  "+
			"  ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
					
			
 m_help_DIV_TXT_RECEIPT_DETAILS_DELE_sql=		
			" SELECT L.NO,L.RECEIPT_NO,L.RECEIPT_TYPE,L.REC_AMOUNT,L.SETT_MODE, "+
			" L.CLIENT_CODE,L.FACILITY_NO,L.DEBTOR_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.RECEIPT_TYPE,P.REC_AMOUNT,P.SETT_MODE, "+
			" P.CLIENT_CODE,P.FACILITY_NO,P.DEBTOR_CODE  "+
			" FROM( "+
			" SELECT A.RECEIPT_NO,  "+
			" A.RECEIPT_TYPE, "+
			" A.REC_AMOUNT, "+
			" DECODE(A.SETTLE_MODE,'CHEQUE',A.SETTLE_MODE ||'-'||A.CHEQUE_NO,A.SETTLE_MODE) SETT_MODE,  "+
			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE,  "+
			" NVL(A.FACILITY_NO,'-') FACILITY_NO,  "+
			" NVL(A.DEBTOR_CODE,'-') DEBTOR_CODE "+ 
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,  "+
			"      "+m_schema_name+".FA_OP_PRO_NON_SALE_ADJUSTMENT B  "+
			" WHERE /*RECON_STATUS='Y'  AND  BALANCE_AMOUNT > 0 AND*/ "+
			" A.RECEIPT_NO =B.RECEIPT_NO "+
			" AND A.CLIENT_CODE =B.CLIENT_CODE "+
			" AND A.FACILITY_NO =B.FACILITY_NO "+
			" AND B.STATUS ='E' "+
			" AND ( A.CLIENT_CODE LIKE '"+m_vector.elementAt(1)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) LIKE '%"+m_vector.elementAt(1)+"%' OR "+
			" A.FACILITY_NO LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" A.DEBTOR_CODE LIKE '"+m_vector.elementAt(1)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) LIKE '%"+m_vector.elementAt(1)+"%'  "+
			"  ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";


		//------------------------------------------------------------
		//------------------------------------------------------------
		
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


