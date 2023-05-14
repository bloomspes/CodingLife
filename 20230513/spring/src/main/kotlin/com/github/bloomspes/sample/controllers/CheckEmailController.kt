package com.github.bloomspes.sample.controllers

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import com.github.bloomspes.sample.services.CheckEmailService
import com.github.bloomspes.sample.services.dtos.CheckEmailResultDto
import jakarta.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/users")
class CheckEmailController(
    private val checkEmailService: CheckEmailService,
) {
    @GetMapping
    fun checkEmail(
        @RequestParam("email") email: String,
    ): CheckEmailResultDto {
        return CheckEmailResultDto(
            existing = checkEmailService.checkEmailExisting(email = email)
        )
    }

    @GetMapping("/http-servlet-response")
    fun checkEmailUsingHttpServletResponse(
        @RequestParam("email") email: String, response: HttpServletResponse
    ) {
        val isEmailDuplicated =
            checkEmailService.checkEmailExisting(email = email)

        if (isEmailDuplicated) {
            response.addHeader("X-Email-Duplicate", "true")
            response.sendError(HttpServletResponse.SC_CONFLICT)
        }

        if (!isEmailDuplicated) {
            response.addHeader("X-Email-Duplicate", "false")
            response.status
        }
    }

    @GetMapping("/response-entity")
    fun checkEmailUsingResponseEntity(
        @RequestParam("email") email: String,
    ): ResponseEntity<String> {
        val isEmailDuplicated =
            checkEmailService.checkEmailExisting(email = email)
        val responseHeaders = HttpHeaders()

        if (isEmailDuplicated) {
            responseHeaders.set("X-Email-Duplicate", "true")

            return ResponseEntity.status(HttpStatus.CONFLICT)
                .headers(responseHeaders)
                .body("This email is not available")
        }

        responseHeaders.set("X-Email-Duplicate", "false")

        return ResponseEntity.ok()
            .headers(responseHeaders)
            .body("This email is available")
    }
}
