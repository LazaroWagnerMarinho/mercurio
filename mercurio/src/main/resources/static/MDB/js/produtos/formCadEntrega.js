$(document).ready(function(){
//	$("#cpf").mask('000.000.000-00', {reverse: true});
	$('#celularContatoColeta, #celularContatoEntrega').mask('(99) 99999-9999');
//	$('#telefone').mask('(99) 9999-9999');
	$('#cepColeta, #cepEntrega').mask('00.000-000', {reverse: true});
});

//function home(){
//	window.history.back();
//}

var inputEnd;

function limpar(){
	$('#tituloEndColeta')[0].value="";
	$('#celularContatoColeta')[0].value="";
	$('#cepColeta')[0].value="";
	$('#cepColetaEndereco')[0].value="";
	$('#numero')[0].value="";
	$('#complemento')[0].value="";
	$('#cepColetaBairro')[0].value="";
	$('#cepColetaCidade')[0].value="";
	$('#cepColetaUf')[0].value="";
	
	$('#tituloEndEntrega')[0].value="";
	$('#celularContatoEntrega')[0].value="";
	$('#cepEntrega')[0].value="";
	$('#cepEntregaEndereco')[0].value="";
	$('#numeroEntrega')[0].value="";
	$('#complementoEntrega')[0].value="";
	$('#cepEntregaBairro')[0].value="";
	$('#cepEntregaCidade')[0].value="";
	$('#cepEntregaUf')[0].value="";
	
	$('tipo_Produtos')[0].value="";
	$('#pesoprod')[0].value="";
	$('#alturaprod')[0].value="";
	$('#largprod')[0].value="";
	$('#profprod')[0].value="";
	
}

function pesquisacep(cepInput,valor) {
	
	inputEnd = cepInput.id;

    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if(validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById(inputEnd+'Endereco').value="...";
            document.getElementById(inputEnd+'Bairro').value="...";
            document.getElementById(inputEnd+'Cidade').value="...";
            document.getElementById(inputEnd+'Uf').value="...";

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
        document.getElementById(inputEnd+'Endereco').value=(conteudo.logradouro);
        document.getElementById(inputEnd+'Bairro').value=(conteudo.bairro);
        document.getElementById(inputEnd+'Cidade').value=(conteudo.localidade);
        document.getElementById(inputEnd+'Uf').value=(conteudo.uf);
    } //end if.
    else {
        //CEP não Encontrado.
        limpa_formulário_cep();
        alert("CEP não encontrado.");
    }
}

function salvarDadosProdutos(idLogin){
	alert(idLogin);
	
}