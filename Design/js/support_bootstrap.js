// Create element fade on the screen
const fade = document.createElement("div");
fade.setAttribute('class', "offcanvas-backdrop fade show");

// _______________________________________________
// Dropdown
function drdActive(p) {
	setClass(p.getElementsByClassName("dropdown-menu")[0]);
}

// offcanvas 
function canvasActive(id) {
	var element = document.getElementById(id);
	setClass(element);
	// Set style visibility = boolean ? hidden : visible;
	if (cls.search("show") > -1) {
		document.body.removeChild(fade)
		element.style.visibility = "hidden";
	} else {
		document.body.appendChild(fade);
		element.style.visibility = "visible";
	}
}

// Accordion
function acdActive(id) {
	setClass(element, document.getElementById(id));
}

/**
* <h3>show acctive</h3>
* @param p's param("this") of the class you want to active
* @param clearClasses're any named of the class to inactive
*/
function buttonActive(p, namedClasses) {
	for (let e of document.getElementsByName(namedClasses)) {
		var txt = e.getAttribute("class");
		e.setAttribute("class", e!=p ? txt.replace(" active", '') : 
		txt.lastIndexOf(' active')>-1 ? txt : txt.concat(' active'));
	}
}

function buttonClick(element, open) {
	if (open) {
		element.style.visibility = "visible";
		cls = element.getAttribute("class");
		element.setAttribute('class', (cls += " show"));
	} else {
		element.style.visibility = "hidden";
		element.getAttribute("class").replace(" show", "");
	}
}