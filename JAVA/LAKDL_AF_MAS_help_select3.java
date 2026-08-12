import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_MAS_help_select3  {  
	
	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();
	
	
	//------------------------------------------------------------------------
	
	public String m_dash="";
	public String m_dash_Header ="Dash Help";
	
	public String m_help_client_group=""; //Added by nuwan de silva on 14-11-07
	public String m_help_client_group_Header ="Client Group Help";
	

	public String m_help_TXT_DOCUMENT_TYPE_sql="";
    public String m_help_TXT_DOCUMENT_TYPE_sql_Header="Document Types"; //INESH 2018-01-09 JB02012018-02276
	
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
		
		
		m_help_client_group=
			" SELECT L.NO ,L.GROUP_ID,L.GROUP_MASTER_ID,L.GROUP_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GROUP_ID,P.GROUP_MASTER_ID,P.GROUP_NAME "+
			" FROM( "+ 
			" SELECT "+
			" GROUP_ID, "+
			" GROUP_MASTER_ID, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(GROUP_MASTER_ID) GROUP_NAME, "+
			" CLIENT_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
			" WHERE CLIENT_TYPE='M' "+
			"  )P)L  "+ 
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			m_help_TXT_DOCUMENT_TYPE_sql =
			/* " SELECT L.NO,L.DOC_NAME,L.DOC_DESCRIPTION,L.DOC_ID "+
			 " FROM( "+
			 "     SELECT ROWNUM NO,P.DOC_NAME,P.DOC_DESCRIPTION,P.DOC_ID "+
			 "     FROM ( "+*/
			 "         SELECT UPPER(DOC_NAME) DOC_NAME,DOC_ID,NVL(DOC_DESCRIPTION,'_') DOC_DESCRIPTION "+
			 "         FROM " + m_schema_name + ".AF_CO_MAS_DOCUMENT_TYPES ";
		/*	 "         WHERE ACTIVE_STATUS ='"+m_vector.elementAt(1)+"' ";
		if(m_vector.elementAt(0)!=null && !m_vector.elementAt(0).equals("")){
			m_help_TXT_DOCUMENT_TYPE_sql+=
			 "         AND ( "+
			 "           DOC_NAME LIKE UPPER('%%') OR DOC_DESCRIPTION LIKE UPPER('%%') OR DOC_ID LIKE UPPER('%%') "+
			 "         ) ";
			}*/
		//m_help_TXT_DOCUMENT_TYPE_sql +=	 
		//	 "         )P "+
		//	 "     ) L ";
			 //" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			//------------------------------------------------------------------------------------------------------------------------------------------------
			Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	}
	
}