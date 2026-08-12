/*
	* Inesh
	* 2018-02-09
	* for generate folder names for fin number
*/

public class LAKDL_AF_MK_Document_Floder_Name_Gen{
	public static String getFolderName(String finNo){		
		String replaced =finNo.replace("/", "SLASH");
		return replaced;
	}
	public static String getFin(String folderName){		
		String replaced =folderName.replace("SLASH","/");
		return replaced;
	}
} 