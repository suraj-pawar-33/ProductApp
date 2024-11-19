$(document).ready(function(){
	  fetch(`http://192.168.1.10:8082/api/npd/productTypeN`)
		.then((res) => res.json())
		.then((x) => {
			const el = document.querySelector("#proType");
			loadDropDown(x, el);
		});
  });

