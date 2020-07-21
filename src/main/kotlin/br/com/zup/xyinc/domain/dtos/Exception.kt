package br.com.zup.xyinc.domain.dtos

import org.springframework.http.HttpStatus

data class Exception(val code: HttpStatus, val timestamp: String, val messages: List<String>)