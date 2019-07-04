 $(document)
		.ready(
				function() {
					var max_fields = 1000// Maximo valor a ser criado
					var wrapper = $(".input_fields_wrap"); // div a ser
															// colocada
					var add_button = $(".add_field_button"); // adicionando
																// os botões

					var x = 1; // inicial contador

					$(add_button)
							.click(
									function(e) { // quando clicar
										e.preventDefault();
										var length = wrapper.find("input:text").length;

										if (x < max_fields) { // enquanto for
																// menor crie
											x++; // increment
											$(wrapper)
													.append(
															// adicione input
															// box
															
															'<div class="form-group row">'
																	+ '<div class =" col-sm-11">'
																	+'<label><i>*</i> Autor:</label>'
																	+ '<input type="text" name="autores['
																	+ (length + 1)
																	+ '].nome" th:field="*{autores['
																	+ (length + 1)
																	+ '].nome}" class="form-control form-control-user" placeholder="Nome e Sobrenome" required>'
																	+ '</div>'
																	+ '<div class =" col-sm-11">'
																	+'<label>CPF:</label>'
																	+ '<input type="text" name="autores['
																	+ (length + 1)
																	+ '].CPF" th:field="*{autores['
																	+ (length + 1)
																	+ '].CPF}" class="form-control form-control-user" id="cpf" maxlength="14" onKeyDown="Mascara(this,Cpf);" onKeyPress="Mascara(this,Cpf);" onKeyUp="Mascara(this,Cpf);" placeholder="CPF" required>'
																	+ '</div>'
																	+ '<div class =" col-sm-11">'
																	+'<label>E-mail:</label>'
																	+ '<input type="email" name="autores['
																	+ (length + 1)
																	+ '].email" th:field="*{autores['
																	+ (length + 1)
																	+ '].email}" class="form-control form-control-user" placeholder="" required>'
																	+ '</div>'
																	+ '<a class="remove_field col-sm-1 mt-5 pt-0">'
																	+ '<button class="btn btn-danger btn-circle"><i class="fas fa-user-minus"></i></button>'
																	+ '</a>'
																	+'<hr class="col-sm-12">'
																	+ '</div>'
													
													);
										}

										// Fazendo com que cada uma escreva seu
										// name
										// wrapper.find("input:text").each(function()
										// {
										// $(this).val($(this).attr('name'))
										// });
									});

					$(wrapper).on("click", ".remove_field", function(e) { // user
																			// click
																			// on
																			// remove
																			// text
						e.preventDefault();
						$(this).parent('div').remove();
						// x--;
					})
				}
			);
	    
	    
	    function excluirdado(valorClick) {

	    	if (valorClick === 1) {
	    		document.getElementById("limpar").value = null;
	    	}
	    } 