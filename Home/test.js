function validateData(type,obj){
	
	if(obj.value ==''){
		obj.value = '';
	}else{
		if(isNaN(obj.value)){
			alert('Please enter valid amount');
			obj.value = '';
		}else{
			if(parseInt(obj.value) > 100 || parseInt(obj.value) < 0){
				alert('Please enter valid amount');
				obj.value = '';
			}else{
				if(type == 'REBATE_FROM'){
					if(document.Form1.TXT_REBATE_TO.value != ''){
						if(parseInt(document.Form1.TXT_REBATE_TO.value) < parseInt(obj.value)){
							alert('Please enter valid range');
							obj.value = '';
						}
					}
				}else if(type == 'REBATE_TO'){
					if(document.Form1.TXT_REBATE_FROM.value != ''){
						if(parseInt(document.Form1.TXT_REBATE_FROM.value) > parseInt(obj.value)){
							alert('Please enter valid range');
							obj.value = '';
						}
					}
				}else if(type == 'ODI_FROM'){
					if(document.Form1.TXT_ODI_TO.value != ''){
						if(parseInt(document.Form1.TXT_ODI_TO.value) < parseInt(obj.value)){
							alert('Please enter valid range');
							obj.value = '';
						}
					}
				}else if(type == 'ODI_TO'){
					if(document.Form1.TXT_ODI_FROM.value != ''){
						if(parseInt(document.Form1.TXT_ODI_FROM.value) > parseInt(obj.value)){
							alert('Please enter valid range');
							obj.value = '';
						}
					}
				}
			}
		}
	}	
}

function clear_data(){
	if(document.Form1.SCREEN_NAME.value == 'NEW'){
		document.Form1.TXT_USER.value = '';
	}else{
		document.Form1.TXT_USER.value 			= '';
		document.Form1.TXT_REBATE_FROM.value 	= '';
		document.Form1.TXT_REBATE_TO.value 		= '';
		document.Form1.TXT_ODI_FROM.value 		= '';
		document.Form1.TXT_ODI_TO.value 		= '';
	}
	if(document.Form1.SCREEN_NAME.value == 'DEACT' || document.Form1.SCREEN_NAME.value == 'REACT'){
		disableFields('ENABLE');
	}
}
function disableFields(status){
	if(status == 'ENABLE'){
		document.Form1.TXT_REBATE_FROM.disabled = false;
		document.Form1.TXT_REBATE_TO.disabled 	= false;
		document.Form1.TXT_ODI_FROM.disabled 	= false;
		document.Form1.TXT_ODI_TO.disabled 		= false;
	}
	if(status == 'DISABLE'){
		document.Form1.TXT_REBATE_FROM.disabled = true;
		document.Form1.TXT_REBATE_TO.disabled 	= true;
		document.Form1.TXT_ODI_FROM.disabled 	= true;
		document.Form1.TXT_ODI_TO.disabled 		= true;
	}
}
function assignData(oBj){
	if(document.Form1.SCREEN_NAME.value == 'NEW'){
		document.Form1.TXT_USER.value = oBj.valout[2];
	}else{
		document.Form1.TXT_USER.value 			= oBj.valout[2];
		document.Form1.TXT_REBATE_FROM.value 	= oBj.valout[3];
		document.Form1.TXT_REBATE_TO.value 		= oBj.valout[4];
		document.Form1.TXT_ODI_FROM.value 		= oBj.valout[5];
		document.Form1.TXT_ODI_TO.value 		= oBj.valout[6];
	}
	if(document.Form1.SCREEN_NAME.value == 'DEACT' || document.Form1.SCREEN_NAME.value == 'REACT'){
		disableFields('DISABLE');
	}
}
function save_window(){	
	before_submit();
}

function validateForm(){
	resetDivs();
	var restults = true;
	
	if(document.Form1.TXT_USER.value ==''){
		DIV_TXT_USER.style.color = 'red';
		restults = false;
	}
	if(document.Form1.TXT_REBATE_FROM.value ==''){
		DIV_TXT_REBATE_FROM.style.color = 'red';
		restults = false;
	}
	if(document.Form1.TXT_REBATE_TO.value ==''){
		DIV_TXT_REBATE_TO.style.color = 'red';
		restults = false;
	}
	if(document.Form1.TXT_ODI_FROM.value ==''){
		DIV_TXT_ODI_FROM.style.color = 'red';
		restults = false;
	}
	if(document.Form1.TXT_ODI_TO.value ==''){
		DIV_TXT_ODI_TO.style.color = 'red';
		restults = false;
	}
	if(!restults){
		alert('Please fill required fields !');
	}
	return restults;
}
function resetDivs(){
	DIV_TXT_USER.style.color='black';
	DIV_TXT_REBATE_FROM.style.color='black';
	DIV_TXT_REBATE_TO.style.color='black';
	DIV_TXT_ODI_FROM.style.color='black';
	DIV_TXT_ODI_TO.style.color='black';
}
function restFormFields(){
	document.Form1.TXT_USER.value 			= '';
	document.Form1.TXT_REBATE_FROM.value 	= '';
	document.Form1.TXT_REBATE_TO.value 		= '';
	document.Form1.TXT_ODI_FROM.value 		= '';
	document.Form1.TXT_ODI_TO.value 		= '';
}