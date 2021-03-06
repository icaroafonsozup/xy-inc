package br.com.zup.xyinc.data.documents

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PointOfInterest(
        @get:JsonProperty(value = "xCoordinate")
        val xCoordinate: Int,
        @get:JsonProperty(value = "yCoordinate")
        val yCoordinate: Int,
        val name: String,
        @Id val id: String? = null
)