/* HTML document is loaded. DOM is ready. 
-----------------------------------------*/
$(document).ready(function(){

	/* Mobile menu */
	$('input.quantity').keyup(function(){
		
		$(this).parent().parent().find('input.amount').val(
				$(this).parent().parent().find('input.quantity').val() * $(this).parent().parent().find('input.cost').val()
			);	
		$('input#totalAmount').val(LoopAccountCodeRows());
	});
});

function LoopAccountCodeRows() {
	  var total = 0;	  
	  $('table#subproducttable tbody tr').each(function () {
		total = total + parseInt($(this).find('input.amount').val());
	  });  
	  return total; 
	}