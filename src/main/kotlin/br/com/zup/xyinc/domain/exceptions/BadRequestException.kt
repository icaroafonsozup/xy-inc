package br.com.zup.xyinc.domain.exceptions

class BadRequestException(override val message: String, val messages: LinkedHashSet<String?>) : RuntimeException(message)