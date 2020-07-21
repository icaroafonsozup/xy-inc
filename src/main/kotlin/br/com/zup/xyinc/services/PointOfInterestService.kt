package br.com.zup.xyinc.services

import br.com.zup.xyinc.data.documents.PointOfInterest
import org.springframework.stereotype.Service

@Service
interface PointOfInterestService {

    fun findAll(): List<PointOfInterest>?

    fun findByCoordinates(xCoordinate: Int, yCoordinate: Int): PointOfInterest?

    fun findByName(name: String): PointOfInterest?

    fun insert(pointOfInterest: PointOfInterest): PointOfInterest

    fun findNearestOnes(xCoordinate: Int, yCoordinate: Int, maxDistance: Int): List<PointOfInterest>
}

