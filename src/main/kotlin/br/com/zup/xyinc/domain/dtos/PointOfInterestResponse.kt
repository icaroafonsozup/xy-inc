package br.com.zup.xyinc.domain.dtos

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PointOfInterestResponse(
        @get:JsonProperty(value = "xCoordinate")
        @get:ApiModelProperty(value = "Coordenada X")
        val xCoordinate: Int,
        @get:JsonProperty(value = "yCoordinate")
        @get:ApiModelProperty(value = "Coordenada Y")
        val yCoordinate: Int,
        @get:ApiModelProperty(value = "Nome do ponto de interesse")
        val name: String,
        @get:ApiModelProperty(value = "Id do ponto de interesse")
        val id: String? = null
)
