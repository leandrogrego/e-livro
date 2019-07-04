//Método que realizar a previa da imagem
function imgPrevia() {
	var oFReader = new FileReader();
	oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

	oFReader.onload = function(oFREvent) {
		document.getElementById("uploadPreview").src = oFREvent.target.result;
	};
};

// Metodo que excluir a foto caso o usuário não queira mais aquela
function excluir(valorClick) {

	if (valorClick === 1) {
		document.getElementById("uploadPreview").src = "/img/livro.jpg";
		document.getElementById("uploadImage").value = null;
	}
}

$(function() {

	// escondendo o input file original
	$("#uploadImage").hide();

});