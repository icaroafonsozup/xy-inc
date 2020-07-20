package br.com.zup.xyinc.data.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PointOfInterest(
        val xCoordinate: Int,
        val yCoordinate: Int,
        val name: String,
        @Id val id: Long? = null
)