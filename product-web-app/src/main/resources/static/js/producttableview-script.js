$(document).ready(function(){
	   fetch(`api/npd/product`)
		.then((res) => res.json())
		.then((x) => {
			const el = document.querySelector("#product-table");
			generateTable(x, el);  
		});
  });

