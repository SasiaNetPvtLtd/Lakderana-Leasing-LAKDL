var data_vec=null;

function load_interface_data(m_url,m_type){
	var http_request = false;
	
	if (window.XMLHttpRequest) {
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {
			http_request.overrideMimeType('text/xml');
		}
	} 
	else if (window.ActiveXObject) { 
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} 
		catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} 
			catch (e) {}
		}
	}
	
	if (!http_request) {
		alert('Giving up :( Cannot create an XMLHTTP instance');
		return false;
	}
	http_request.onreadystatechange = function() {
		if(m_type=="XML"){//retrun in xml format
			load_data_rights(http_request); 
		}
		else{
			load_data_normal(http_request); 
		}
	};
	
	/*
	data_vec= new Array();
	
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
				var xmlbody=http_request.responseXML.documentElement;
				var vsize=0;
					for(var i=0;i<xmlbody.childNodes.length;i++){
						for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){
							data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;
							vsize++;
						}
					}
				get_vector(data_vec);
			}
	}
	*/
	http_request.open('GET',m_url, true);
	http_request.send(null);
	
}






function load_data_rights(http_request){ // Modified by Chatura Jayawardena on 19-03-2010

	data_vec= new Array();
	
	if (http_request.readyState == 4) {
		if (http_request.status == 200) {
			
			
			xmlDoc=http_request.responseXML;
			var not_whitespace = new RegExp(/[^\s]/);
			var vsize=0;
			for(var i=0;i<xmlDoc.getElementsByTagName("ITEM").length;i++){
				
				for(var j=0;j<xmlDoc.getElementsByTagName("ITEM")[i].childNodes.length;j++){
					
					if(xmlDoc.getElementsByTagName("ITEM")[i].childNodes[j].firstChild!=null ){
						
						if(not_whitespace.test(xmlDoc.getElementsByTagName("ITEM")[i].childNodes[j].firstChild.nodeValue)&& xmlDoc.getElementsByTagName("ITEM")[i].childNodes[j].firstChild.nodeType == 3){
							
							data_vec[vsize]=xmlDoc.getElementsByTagName("ITEM")[i].childNodes[j].firstChild.nodeValue;
							vsize++;
						}
						
					}
					else{
						if(xmlDoc.getElementsByTagName("ITEM")[i].childNodes[j].nodeValue==null){
							data_vec[vsize]= " ";
							vsize++;
						}
						
						
					}
					
				}
			}
			get_data_vector(data_vec);
			
		}
	}
	
}



	   function check_user_rights(m_screen,m_user,m_company){ 
		m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_CR_sql_validations_4?chksql=chk_user_rights&screen_name="+m_screen+"&user_name="+m_user+"&company_name="+m_company;
		load_interface_data(m_url,"XML");
        } 
            




  function get_data_vector(data_vec){
	var access_right; 	
	
	if(data_vec.length>0 ){ 
		access_right=data_vec[0]; 
		if(access_right.substring(0,1)!='Y'){
		  chkObject ('BUT_NEW');
		}
		
		if(access_right.substring(1,2)!='Y'){
		 chkObject ('BUT_EDIT');
		}
		
		if(access_right.substring(2,3)!='Y'){
		 chkObject ('BUT_DEACT');
		}
				
		if(access_right.substring(3,4)!='Y'){
		 chkObject ('BUT_REACT');
		}		
		
		if(access_right.substring(4,5)!='Y'){
		 chkObject ('BUT_VIEW');
		}
		
      } 
  } 




  function chkObject (theVal){
       if (document.getElementById(theVal) != null)
        {
		//document.getElementById(theVal).style.display = 'none';	
     	document.getElementById(theVal).disabled = true;
		document.getElementById(theVal).style.color='#D1D1D1';
		//document.getElementById(theVal).backgroundColor='#FFFFFF';
        }
     }



