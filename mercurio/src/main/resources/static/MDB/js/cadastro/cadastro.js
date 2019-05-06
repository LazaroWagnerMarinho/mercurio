$(document).ready(function(){
	$("#cpf").mask('000.000.000-00', {reverse: true});
	$('#celular').mask('(99) 99999-9999');
	$('#telefone').mask('(99) 9999-9999');
	$('#cep').mask('00.000-000', {reverse: true});
});


function limpar(){
	$('#nome')[0].value="";
	$('#cpf')[0].value="";
	$('#nascimento')[0].value="";
	$('#celular')[0].value="";
	$('#telefone')[0].value="";
	$('#cep')[0].value="";
	$('#endereco')[0].value="";
	$('#numero')[0].value="";
	$('#complemento')[0].value="";
	$('#bairro')[0].value="";
	$('#cidade')[0].value="";
	$('#uf')[0].value="";
	$('#email')[0].value="";
	$('#senha')[0].value="";
	$('#senhaconfirma')[0].value="";	
}