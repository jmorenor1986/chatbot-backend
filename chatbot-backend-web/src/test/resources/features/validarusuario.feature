Feature: ValidarUsuario funcionalidades

Background: Previo al llamado de servicios se realiza el login de aplicacion
 Given un usuario de aplicacion con los siguientes datos
 | usuario             | contrasenia |
 | jnsierrac@gmail.com | 1234       |
 When envio la informacion al servicio de login
 Then debo tener un token de autenticacion "Bearer "

Scenario: validar usuario success
    Given un usuario con los siguientes datos
    | telefono    | colaIdentificacion |
    | 30144001617 | 22903              |
    When llama el servicio validarUsuario
    Then retorna resultastatusCode "200"


