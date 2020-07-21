package br.com.zup.xyinc.services.impl

import br.com.zup.xyinc.data.documents.PointOfInterest
import br.com.zup.xyinc.data.repositories.PointOfInterestRepository
import br.com.zup.xyinc.services.PointOfInterestService
import org.springframework.stereotype.Service
import kotlin.math.hypot

@Service
class PointOfInterestServiceImpl(val pointOfInterestRepository: PointOfInterestRepository) : PointOfInterestService {

    override fun findAll(): List<PointOfInterest>? = pointOfInterestRepository.findAll()

    override fun findByCoordinates(xCoordinate: Int, yCoordinate: Int): PointOfInterest? =
            pointOfInterestRepository.findByxCoordinateAndYCoordinate(xCoordinate, yCoordinate)

    override fun findByName(name: String): PointOfInterest? = pointOfInterestRepository.findByName(name)

    override fun insert(pointOfInterest: PointOfInterest): PointOfInterest = pointOfInterestRepository.insert(pointOfInterest)

    override fun findNearestOnes(xCoordinate: Int, yCoordinate: Int, maxDistance: Int): List<PointOfInterest> =
            pointOfInterestRepository.findAll().filter { calculateDistance(it, xCoordinate, yCoordinate, maxDistance) }

    /*
    * The distance between two points in a cartesian plane is calculated by:
    *      _________________
    *    √(xb-xa)²+(yb-ya)²
    *
    * */
    private fun calculateDistance(poi: PointOfInterest, xCoordinate: Int, yCoordinate: Int, maxDistance: Int): Boolean = hypot((poi.xCoordinate - xCoordinate).toDouble(), ((poi.yCoordinate - yCoordinate).toDouble())) <= maxDistance

}