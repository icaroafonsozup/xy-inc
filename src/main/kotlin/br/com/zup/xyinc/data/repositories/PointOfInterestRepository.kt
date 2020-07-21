package br.com.zup.xyinc.data.repositories

import br.com.zup.xyinc.data.documents.PointOfInterest
import org.springframework.data.mongodb.repository.MongoRepository

interface PointOfInterestRepository : MongoRepository<PointOfInterest, Long> {

    fun findByxCoordinateAndYCoordinate(x: Int, y: Int): PointOfInterest?

    fun findByName(name: String): PointOfInterest?
}