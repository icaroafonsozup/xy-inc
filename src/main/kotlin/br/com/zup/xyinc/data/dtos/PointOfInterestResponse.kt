package br.com.zup.xyinc.data.dtos

import br.com.zup.xyinc.data.documents.PointOfInterest
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PointOfInterestResponse(
        val xCoordinate: Int,
        val yCoordinate: Int,
        val name: String,
        val id: Long? = null
)

fun PointOfInterest.toPointOfInterestResponse() =
        PointOfInterestResponse(
                id = id,
                xCoordinate = xCoordinate,
                yCoordinate = yCoordinate,
                name = name
        )

fun List<PointOfInterest>.toPointOfInterestResponse() = map { it.toPointOfInterestResponse() }