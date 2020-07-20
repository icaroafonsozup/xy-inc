package br.com.zup.xyinc.data.dtos

import br.com.zup.xyinc.data.documents.PointOfInterest
import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PointOfInterestRequest(
        @get:NotNull(message = "Coordenada Y não pode ser vazia.")
        @get:Min(value = 0L, message = "O Valor de X deve ser um inteiro positivo.")
        val xCoordinate: Int,
        @get:NotNull(message = "Coordenada Y não pode ser vazia.")
        @get:Min(value = 0L, message = "O Valor de Y deve ser um inteiro positivo.")
        val yCoordinate: Int,
        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:Length(min = 3, max = 50, message = "Nome deve possuir entre 3 e 50 caracteres.")
        val name: String,
        val id: Long? = null
)

fun PointOfInterestRequest.toPointOfInterest() =
        PointOfInterest(
                id = id,
                xCoordinate = xCoordinate,
                yCoordinate = yCoordinate,
                name = name
        )

