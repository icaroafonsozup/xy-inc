package br.com.zup.xyinc.services.impl

import br.com.zup.xyinc.data.documents.PointOfInterest
import br.com.zup.xyinc.data.repositories.PointOfInterestRepository
import br.com.zup.xyinc.services.PointOfInterestService
import org.springframework.stereotype.Service

@Service
class PointOfInterestServiceImpl(val pointOfInterestRepository: PointOfInterestRepository) : PointOfInterestService {

    override fun findAll(): List<PointOfInterest>? = pointOfInterestRepository.findAll()

    override fun findByCoordinates(xCoordinate: Int, yCoordinate: Int): PointOfInterest? {
        return pointOfInterestRepository.findByxCoordinateAndyCoordinate(xCoordinate, yCoordinate)
    }

    override fun findByName(name: String): PointOfInterest? {
        return pointOfInterestRepository.findByName(name)
    }

    override fun insert(pointOfInterest: PointOfInterest): PointOfInterest {
        return pointOfInterestRepository.insert(pointOfInterest)
    }
}