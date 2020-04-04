Feature: KeepAlive funcionalities

	Scenario: validate return keep alive
		When call the service keepalive
		Then the response data is returned