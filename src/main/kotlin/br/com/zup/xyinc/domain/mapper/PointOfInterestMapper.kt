package br.com.zup.xyinc.domain.mapper

import br.com.zup.xyinc.data.documents.PointOfInterest
import br.com.zup.xyinc.domain.dtos.PointOfInterestRequest
import br.com.zup.xyinc.domain.dtos.PointOfInterestResponse


fun PointOfInterestRequest.toPointOfInterest() =
        PointOfInterest(
                id = id,
                xCoordinate = xCoordinate,
                yCoordinate = yCoordinate,
                name = name
        )


fun PointOfInterest.toPointOfInterestResponse() =
        PointOfInterestResponse(
                id = id,
                xCoordinate = xCoordinate,
                yCoordinate = yCoordinate,
                name = name
        )

fun List<PointOfInterest>?.toPointOfInterestResponse() = this?.map { it.toPointOfInterestResponse() }