
function loadDropDown(jsonData, el) {
	 let keys = Object.keys(jsonData);
	 keys.forEach((key) => {
		  const option = document.createElement("option");
		  option.value = key;
//		  option.setAttribute("th:value", key);
		  const cellText = document.createTextNode(jsonData[key]);
		  option.appendChild(cellText);
		  el.appendChild(option);
	  });
	
} 
/**
 * Selects value of drop down list
 * @param id
 * @param val
 * @returns
 */
function setSelectValue(id, val) {
    document.getElementById(id).value = val;
}
function generateCell(text) {
	const span = document.createElement("span");
	const aTag = document.createElement("a");
	aTag.classList.add('white-text');
	aTag.classList.add('templatemo-sort-by');
	span.classList.add('caret');
	// Create a <td> element and a text node, make the text
	// node the contents of the <td>, and put the <td> at
	// the end of the table row
	const cell = document.createElement("td");
	const cellText = document.createTextNode(text);
	aTag.appendChild(cellText);
	cell.appendChild(aTag);
	cell.appendChild(span);
	return cell;
}

function generateTable(jsonData, el) {
  // creates a <table> element and a <tbody> element
  const tbl = document.createElement("table");
  const tblBody = document.createElement("tbody");
  const tblHead = document.createElement("thead");
  

  tbl.classList.add('table');
  tbl.classList.add('table-striped');
  tbl.classList.add('table-bordered');
  tbl.classList.add('templatemo-user-table');

  const headerRow = document.createElement("tr");
	
   // Get the keys (column names) of the first object in the JSON data
   let cols = Object.keys(jsonData[0]);

   // Loop through the column names and create header cells
   cols.forEach((item) => {
		headerRow.appendChild(generateCell(item));
    });
   headerRow.appendChild(generateCell("Edit"));
   tblHead.appendChild(headerRow);
  // Loop through the JSON data and create table rows
  jsonData.forEach((item) => {
     
     // Get the values of the current object in the JSON data
     let vals = Object.values(item);

     let row = document.createElement("tr");

    vals.forEach((elem) => {
      // Create a <td> element and a text node, make the text
      // node the contents of the <td>, and put the <td> at
      // the end of the table row
      const cell = document.createElement("td");
      const cellText = document.createTextNode(elem);
      cell.appendChild(cellText);
      row.appendChild(cell);
    });
    

    const cell = document.createElement("td");
	const aTag = document.createElement("a");
    const cellText = document.createTextNode("Edit");
	aTag.classList.add('templatemo-edit-btn');
	aTag.href = "api/npd/product/update/manage-product-update/" + item["Product UID"];
    aTag.appendChild(cellText);
    cell.appendChild(aTag);
    row.appendChild(cell);

    // add the row to the end of the table body
    tblBody.appendChild(row);
  });

  // put the <tbody> in the <table>
  tbl.appendChild(tblBody);
  tbl.appendChild(tblHead);
  // appends <table> into <div>
  el.appendChild(tbl);
}
