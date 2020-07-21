package br.com.zup.xyinc.domain.dtos

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PointOfInterestResponse(
        @get:JsonProperty(value = "xCoordinate")
        val xCoordinate: Int,
        @get:JsonProperty(value = "yCoordinate")
        val yCoordinate: Int,
        val name: String,
        val id: String? = null
)
