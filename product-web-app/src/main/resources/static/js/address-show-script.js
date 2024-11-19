/*
 *	
 *******************************************************/

/* HTML document is loaded. DOM is ready. 
-----------------------------------------*/
$(document).ready(function(){
	/* Close the widget when clicked on address lable*/
	$("#address").click(function(){
		$("#address-div").slideToggle();
	});
});