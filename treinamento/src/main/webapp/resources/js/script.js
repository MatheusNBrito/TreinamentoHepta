//Listar Usuario

function listar() {
	fetch('http://localhost:8080/treinamento/rs/lotericas')
		.then(response => response.json())
		.then(dados => {
			dados.forEach(usuario => {
				//console.log(usuario)
				let nome = usuario.nome;
				let sobrenome = usuario.fullname;
				let agencia = usuario.fk_agencia;
				let id = usuario.id;
				let senha = usuario.password;
				let email = usuario.email;
				let user_data = usuario.user_data;
				let imagem = usuario.imagem;
				
				var dataNasci = new Date(user_data);

				let tabela = document.getElementById("user-table");

				var tr = document.createElement("tr");

				var col1 = tr.appendChild(document.createElement('td'));
				col1.innerHTML = id;

				var col2 = tr.appendChild(document.createElement('td'));
				col2.innerHTML = nome;

				var col3 = tr.appendChild(document.createElement('td'));
				col3.innerHTML = sobrenome;

				var col4 = tr.appendChild(document.createElement('td'));
				col4.innerHTML = senha;

				var col5 = tr.appendChild(document.createElement('td'));
				col5.innerHTML = email;

				var col6 = tr.appendChild(document.createElement('td'));
				col6.innerHTML = agencia;
				
				var col7 = tr.appendChild(document.createElement('td'));
				col7.innerHTML = formatDateBr(dataNasci);
				
				var col8 = tr.appendChild(document.createElement('td'));
				col8.innerHTML = imagem;

				var col9 = document.createElement('td');
				let btnEditar = document.createElement('button');
				col9.appendChild(btnEditar);
				btnEditar.innerHTML = "editar";
				btnEditar.setAttribute("onclick", "buscaUsuario(" + usuario.id + ")");
				tr.appendChild(col9);

				var col10 = document.createElement('td');
				let btnExcluir = document.createElement('button');
				col10.appendChild(btnExcluir);
				btnExcluir.innerHTML = "Excluir";
				btnExcluir.setAttribute('onclick', "deletarUsuario(" + usuario.id + ")");
				tr.appendChild(col10);

				tabela.appendChild(tr);

			})
		})


}

//Adicionar Usuario

function addUsuario() {
	//let id = document.getElementById('id').value;
	let nome = document.getElementById('nome').value;
	let sobrenome = document.getElementById('sobrenome').value;
	let senha = document.getElementById('senha').value;
	let email = document.getElementById('email').value;
	let fk_agencia = document.getElementById('fk_agencia').value;
	let user_data = document.getElementById('user_data').value;
	let imagem = document.getElementById('imagem').value;
	


	
	const usuarioAdd = {
		"id": null,
		"nome": nome,
		"sobrenome": sobrenome,
		"senha": senha,
		"mail": email,
		"fk_agencia": fk_agencia,
		"user_data": user_data,
		"imagem": imagem,

	}
	
	//console.log(u);
	
	fetch('http://localhost:8080/treinamento/rs/lotericas', {
		method: "POST",
		headers: {
			'Content-type': 'application/JSON'
		},
		body: JSON.stringify(usuarioAdd),
	})//.then(response => {
		//window.location = "../index.html";
	//});
}

//Editar Usuario

async function editarUsuario() {
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const id = urlParams.get('id')

	let nome = document.getElementById('nome').value;
	let sobrenome = document.getElementById('sobrenome').value;
	let senha = document.getElementById('senha').value;
	let email = document.getElementById('email').value;
	let fk_agencia = document.getElementById('fk_agencia').value;
	let user_data = document.getElementById('user_data').value;
	let imagem = document.getElementById('imagem').value;

	
	const usuarioAdd = {
		"id": id,
		"nome": nome,
		"sobrenome": sobrenome,
		"senha": senha,
		"mail": email,
		"fk_agencia": fk_agencia,
		"user_data": new Date(user_data),
		"imagem": imagem,
	}
	//console.log(user_data);
	
	await fetch('http://localhost:8080/treinamento/rs/lotericas/', {
		method: "PUT",
		headers: {
			'Content-type': 'application/JSON'
		},
		body: JSON.stringify(usuarioAdd),
	}).then(response => {
	window.location = "../index.html";
	});
}

//Deletar Usuario

function deletarUsuario(id) {
	if (confirm('Deseja deletar o usuario? ' + id)) {
		fetch('http://localhost:8080/treinamento/rs/lotericas/' + id, {
			method: "DELETE",
		}).then(response => {
			window.location = "./index.html";
		});;
	}
}

//Carrega dados a serem editados

async function prepararEdicao() {
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const id = urlParams.get('id')
	//console.log(id);

	await fetch('http://localhost:8080/treinamento/rs/lotericas/' + id, {
		method: "GET",
		headers: {
			'Content-type': 'application/JSON'
		},
	}).then(response => response.json())
		.then(dados => {
			//console.log(dados);
			//var dataNasci = new Date(user_data);
			document.getElementById('nome').value = dados.nome;
			document.getElementById('sobrenome').value = dados.fullname;
			document.getElementById('senha').value = dados.password;
			document.getElementById('email').value = dados.email;
			document.getElementById('fk_agencia').value = dados.fk_agencia;
			document.getElementById('user_data').value = formatDate( new Date (dados.user_data));
			document.getElementById('imagem').value = dados.imagem;
		})
}

function buscaUsuario(id) {
	location.href = "./paginas/editar.html?id=" + id;
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}
 
function formatDateBr(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [day, month, year].join('/');
}

// -------------------------- Agencia ---------------------------------------

async function listarAgencia() {
	await fetch('http://localhost:8080/treinamento/rs/lotericas/agencia')
		.then(response => response.json())
		.then(dados => {
			dados.forEach(agencia => {
				//console.log(agencia)
				let nome = agencia.nome;
				let adress = agencia.adress;
				let telefone = agencia.telefone;
				let id = agencia.idag;
				let tabela = document.getElementById("agencia-table");

				var tr = document.createElement("tr");

				var col1 = tr.appendChild(document.createElement('td'));
				col1.innerHTML = id;

				var col4 = tr.appendChild(document.createElement('td'));
				col4.innerHTML = nome;

				var col2 = tr.appendChild(document.createElement('td'));
				col2.innerHTML = adress;

				var col3 = tr.appendChild(document.createElement('td'));
				col3.innerHTML = telefone;

				var col5 = document.createElement('td');
				let btnEditar = document.createElement('button');
				col5.appendChild(btnEditar);
				btnEditar.innerHTML = "editar";
				btnEditar.setAttribute("onclick", "buscaAgencia(" + agencia.idag + ")");
				tr.appendChild(col5);

				var col6 = document.createElement('td');
				let btnExcluir = document.createElement('button');
				col6.appendChild(btnExcluir);
				btnExcluir.innerHTML = "Excluir";
				btnExcluir.setAttribute('onclick', "deletarAgencia(" + agencia.idag + ")");
				tr.appendChild(col6);

				tabela.appendChild(tr);

			})
		})

}

//Adicionar Agencia

function addAgencia() {
	let nome = document.getElementById('nome').value;
	let adress = document.getElementById('adress').value;
	let telefone = document.getElementById('telefone').value;

	const agenciaAdd = {
		"idag": null,
		"nome": nome,
		"adress": adress,
		"telefone": telefone,

	}
	fetch('http://localhost:8080/treinamento/rs/lotericas/agencia', {
		method: "POST",
		headers: {
			'Content-type': 'application/JSON'
		},
		body: JSON.stringify(agenciaAdd),
	}).then(response => {
		window.location = "../index.html";
	});
}

// Buscar Agencia

function buscaAgencia(idag) {
	location.href = "./paginas/editar-agencia.html?idag=" + idag;
}

async function prepararEdicaoAgencia() {
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const id = urlParams.get('idag')


	await fetch('http://localhost:8080/treinamento/rs/lotericas/agencia/' + id, {
		method: "GET",
		headers: {
			'Content-type': 'application/JSON'
		},
	}).then(response => response.json())
		.then(dados => {
			//console.log(dados);
			//console.log("adress", dados.adress);
			document.getElementById('nome').value = dados.nome;
			//document.getElementById('adress').setAttribute('value', dados.adress);
			document.getElementById('adress').value = dados.adress;
			document.getElementById('telefone').value = dados.telefone;

		})
}





function editarAgencia() {
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const idag = urlParams.get('idag')

	//let id = document.getElementById('id').value;
	let adress = document.getElementById('adress').value;
	let nome = document.getElementById('nome').value;
	let telefone = document.getElementById('telefone').value;
	const agenciaAdd = {
		"idag": idag,
		"nome": nome,
		"adress": adress,
		"telefone": telefone,

	}
	fetch('http://localhost:8080/treinamento/rs/lotericas/agencia', {
		method: "PUT",
		headers: {
			'Content-type': 'application/JSON'
		},
		body: JSON.stringify(agenciaAdd),
	}).then(response => {
	window.location = "../index.html";
	});
}



//deletar agencia

function deletarAgencia(idag) {
	if (confirm('Deseja deletar a agencia? ' + idag)) {
		fetch('http://localhost:8080/treinamento/rs/lotericas/agencia/' + idag, {
			method: "DELETE",
		}).then(response => {
			//window.location = "./index.html";
		});;
	}
}


