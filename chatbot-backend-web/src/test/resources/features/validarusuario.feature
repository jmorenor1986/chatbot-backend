Feature: ValidarUsuario funcionalidades

	Scenario: validar usuario success
	    Given un usuario con los siguientes datos
	    | telefono | colaIdentificacion |
        | 30144001617 | 22903 |
		When llama el servicio validarUsuario
		Then retorna resultastatusCode "403"


