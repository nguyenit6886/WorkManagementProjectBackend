"use strict"

document.getElementById('example2_filter').classList.add('row', 'w-100', 'mx-0', 'text-left');

//div 1
let div1 = document.createElement("div");
div1.setAttribute('class', 'col-4')
document.getElementById('example2_filter').appendChild(div1);
div1.appendChild(document.getElementById('example2_filter').firstChild);

//div 2
let div2 = document.createElement("div");
div2.setAttribute('class', 'col-8 d-inline-flex');
document.getElementById('example2_filter').appendChild(div2);

//label
let label = document.createElement("label");
label.setAttribute('class', 'pt-2 mr-1');
label.innerHTML = 'Add:';
div2.appendChild(label);

//select
let select = document.createElement("select");
select.setAttribute('class', 'js-example-programmatic-multi');
select.setAttribute('multiple', 'multiple');
select.innerHTML = `
    <option value="AL">Alabama</option>
    <option value="WY">Wyoming</option>
    <option value="UI">dlf</option>
`;
div2.appendChild(select);

//button add
let btn_add = document.createElement('button');
btn_add.setAttribute('class', 'btn btn-primary mb-2 mx-1');
btn_add.innerHTML = 'Add';
div2.appendChild(btn_add);

//button clear
let btn_clear = document.createElement('button');
btn_clear.setAttribute('class', 'js-programmatic-multi-clear btn btn-primary mb-2');
btn_clear.innerHTML = 'Clear';
div2.appendChild(btn_clear);
