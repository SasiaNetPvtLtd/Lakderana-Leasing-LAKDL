var IW= 0;
var IH= 0;
var PX= 0;
var PY= 0;
var ZIN= 0;
var us= null;

function marking() {
	
	if (navigator.appVersion.indexOf("Mac") == -1) {
		oldIW= IW;
		oldIH= IH;
		oldPX= PX;
		oldPY= PY;
		if ((document.body.clientHeight != oldIH)||
						(document.body.clientWidth != oldIW)||
						(document.body.scrollLeft != oldPX)||
						(document.body.scrollTop != oldPY)) {
			alldivs= document.all.tags("DIV");
			if (us == null) {
				for (i=0; i<alldivs.length; i++) {
					us= alldivs(i);
				}
			}
			if(window != window.top){
				var ourDoc= "";
				var ourDocWidth= 0;
				var ourDocHeight= 0;
				var ourDocTest= 0;
			}
			if((ourDoc)&&(ourDoc != self)){
				us.style.display= "none";
			}
			if(((ourDoc)&&(ourDoc == self))||(!ourDoc)){
				if (us != null){
					PX= document.body.scrollLeft;
					PY= document.body.scrollTop;
					alldivs(0).style.top= 45;
					alldivs(0).style.left=0;
					alldivs(1).style.top= 45;
					alldivs(1).style.left=PX;
					alldivs(2).style.top=PY;
					alldivs(2).style.left=0;
					alldivs(3).style.top=PY;
					alldivs(3).style.left=PX;
					for (i=0; i<alldivs.length; i++){
						templay= alldivs(i);
						if (templay.style.zIndex > ZIN)
							us.style.zindex= (templay.style.zIndex + 1);
						}
						us.onmouseover= neat_mouseover;
						us.onmouseout= neat_mouseout; 
						us.style.display= "";
					}
				}
			}
		}
	}


function neat_mouseover(){
	if(us != null){
		window.status="";
		return true;
	}
}

function neat_mouseout(){
	if(us != null){
		window.status="";
		return true;
	}
}

setInterval("marking()",20);


