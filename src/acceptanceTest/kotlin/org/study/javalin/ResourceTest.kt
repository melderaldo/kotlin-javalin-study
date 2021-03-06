package org.study.javalin

import com.github.kittinunf.fuel.Fuel
import org.assertj.core.api.Assertions.assertThat
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ResourceTest {
    private val app = startServer()
    private val baseUrl = "http://localhost:${app.server.port()}"

    @Test
    fun `should receive the resource service health`() {
        val request = Fuel.get("$baseUrl/health")
        val response = request.response().second
        val expectedBody = "{\"status\":\"UP\"}"
        val responseBodyMatch = "Body : \\((.*)\\)".toRegex().find(response.toString())
        val (_, bodyValue) = responseBodyMatch!!.groupValues
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK_200)
        assertThat(bodyValue).isEqualTo(expectedBody)
        assertNotNull(response)
    }

    @Test
    fun `should receive successfully resource list data`() {
        val request = Fuel.get("$baseUrl/resources")
        val response = request.response().second
        val expectedBody = """[{"id":1,"title":"O Mundo de Sofia","description":"História sobre uma garota que aprender filosofia através de cartas anônimas","type":"BOOK"}]"""
        val responseBodyMatch = "Body : \\((.*)\\)".toRegex().find(response.toString())
        val (_, bodyValue) = responseBodyMatch!!.groupValues
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK_200)
        assertThat(bodyValue).isEqualTo(expectedBody)
        assertNotNull(response)
    }

    @Test
    fun `should receive successfully a resource object data`() {
        val request = Fuel.get("$baseUrl/resources/1")
        val response = request.response().second
        val expectedBody = """{"id":1,"title":"O Mundo de Sofia","description":"História sobre uma garota que aprender filosofia através de cartas anônimas","type":"BOOK"}"""
        val responseBodyMatch = "Body : \\((.*)\\)".toRegex().find(response.toString())
        val (_, bodyValue) = responseBodyMatch!!.groupValues
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK_200)
        assertThat(bodyValue).isEqualTo(expectedBody)
        assertNotNull(response)
    }
}