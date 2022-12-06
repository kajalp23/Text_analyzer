const form = document.getElementById("myForm");
const result = document.getElementById("id1")


form.addEventListener("submit", function(event) {
	event.preventDefault();
	const content = document.getElementById("content").value;
	fetch('http://localhost:8080/text/analyze',{	
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'				
			},
			body: JSON.stringify({
				"content": content
			})
		})
		.then(response => response.json())
		.then(response => {
			//console.log(JSON.stringify(response))
			result.innerHTML = response.word_count   
			document.write(JSON.stringify(response))  
			
			
		})
});