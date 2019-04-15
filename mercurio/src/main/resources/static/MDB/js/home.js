$(document).ready(function(){
	$('#cadastro').live('click',function(){
		$('#conteudo').load($(this).attr('/cadastrar'));
	})
	return false;
})