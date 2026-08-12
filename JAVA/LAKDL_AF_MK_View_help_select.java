import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_MK_View_help_select  {  

	public Object Ret_Object = new Object();
	//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
	//Added by mahela on 20-07-2007
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();

	//------------------------------------------------------------------------
	
	
		
	
	public String m_help_TXT_INVOICE_NO_sql="";
	public String m_help_TXT_INVOICE_NO_sql_Header="Application Processing - Invoice Help ";
		
	//-------------------------------------------------------------------------
	
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
		
		
		/*------------------ ID        : Performa Invoice Process ----------------------------------
			--------------------Purpose    : Invoice no Help ----------------------------------------------
		  ------------------- Added By   : Nuwan De Silva------------------------------------------------------
		  -------------------- Date      : 30-11-2006---------------------------------------------------------*/
			
		
		m_help_TXT_INVOICE_NO_sql=
		
		" SELECT L.NO,L.INVOICE_NO,L.APPLICATION_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE,L.SUM_INSURED,L.AREA,L.POLICE,L.OWNER_ADDRESS,L.COLLECTON_SECURITY,L.LIC_AUTH,L.VEHICAL_AGA"+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_NO,P.REG_DATE,P.PRICING_NO,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.SUM_INSURED,P.AREA,B.POLICE,P.OWNER_ADDRESS,P.COLLECTON_SECURITY,P.LIC_AUTH,P.VEHICAL_AGA"+
		" FROM( "+ 
		" SELECT "+
    " A.INVOICE_NO, "+
    " A.APPLICATION_NO, "+
    " ASSET_ID, "+
    " ENGINE_NO, "+
    " CHASSIS_NO, "+
    " NVL(REG_NO,'-') REG_NO, "+
    " REG_DATE , "+
    " PRICING_NO, "+
    " SUB_MODEL_CODE, "+
    " COLOUR, "+
    " SEATING_CAPACITY, "+
    " NET_PRICE, "+
    " VAT, "+
    " TOTAL_AMOUNT, "+
    " TO_BE_DELIVERD_TO, "+
    " VALUE, "+
    " NVL(CURR_CODE,'-') CURR_CODE , "+
    " MODEL_CODE ,  "+
	" NVL(B.SUM_INSURED,0) SUM_INSURED,B.AREA,B.POLICE,B.OWNER_ADDRESS,B.COLLECTON_SECURITY,B.LIC_AUTH,B.VEHICAL_AGA "+ //added by prabash on30-04-2012
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_SUM_INSURED_DETAILS B  "+
	
    " WHERE A.INVOICE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
		" AND A.INVOICE_NO=B.INVOICE_NO  )P)L  "+
	  " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
	

		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


