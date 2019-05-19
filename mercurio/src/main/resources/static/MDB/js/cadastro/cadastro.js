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

function pesquisacep(valor) {

    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if(validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('endereco').value="...";
            document.getElementById('bairro').value="...";
            document.getElementById('cidade').value="...";
            document.getElementById('uf').value="...";

            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);

        } //end if.
        else {
            //cep é inválido.
            limpa_formulário_cep();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulário_cep();
    }
};

function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('endereco').value=(conteudo.logradouro);
        document.getElementById('bairro').value=(conteudo.bairro);
        document.getElementById('cidade').value=(conteudo.localidade);
        document.getElementById('uf').value=(conteudo.uf);
    } //end if.
    else {
        //CEP não Encontrado.
        limpa_formulário_cep();
        alert("CEP não encontrado.");
    }
}