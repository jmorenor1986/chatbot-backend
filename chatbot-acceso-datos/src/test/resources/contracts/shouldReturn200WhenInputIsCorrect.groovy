import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return 200 when input is correct"
    request {
        method 'POST'
        url "v1/usuario"
        headers {
            contentType(applicationJson())
        }


    }
}