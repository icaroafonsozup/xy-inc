package br.com.zup.xyinc.domain.dtos

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PointOfInterestRequest(
        @get:NotNull(message = "Coordenada Y não pode ser vazia.")
        @get:Min(value = 0L, message = "O Valor de X deve ser um inteiro positivo.")
        @get:JsonProperty(value = "xCoordinate")
        val xCoordinate: Int,
        @get:NotNull(message = "Coordenada Y não pode ser vazia.")
        @get:Min(value = 0L, message = "O Valor de Y deve ser um inteiro positivo.")
        @get:JsonProperty(value = "yCoordinate")
        val yCoordinate: Int,
        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:Length(min = 3, max = 50, message = "Nome deve possuir entre 3 e 50 caracteres.")
        val name: String,
        val id: String? = null
)

