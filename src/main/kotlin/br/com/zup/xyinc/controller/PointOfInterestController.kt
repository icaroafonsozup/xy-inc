package br.com.zup.xyinc.controller

import br.com.zup.xyinc.data.documents.PointOfInterest
import br.com.zup.xyinc.domain.dtos.PointOfInterestRequest
import br.com.zup.xyinc.domain.dtos.PointOfInterestResponse
import br.com.zup.xyinc.domain.exceptions.BadRequestException
import br.com.zup.xyinc.domain.mapper.toPointOfInterest
import br.com.zup.xyinc.domain.mapper.toPointOfInterestResponse
import br.com.zup.xyinc.services.PointOfInterestService
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/pointOfInterest")
class PointOfInterestController(val pointOfInterestService: PointOfInterestService) {

    @Throws(RuntimeException::class)
    @PostMapping
    fun insert(@Valid @RequestBody request: PointOfInterestRequest, result: BindingResult): ResponseEntity<PointOfInterestResponse> {

        var errors: LinkedHashSet<String?> = LinkedHashSet()
        if (result.hasErrors()) {
            result.allErrors.forEach { item -> errors.add(item.defaultMessage) }
            throw BadRequestException(message = errors.toString(), messages = errors)
        }

        val pointOfInterestResponse: PointOfInterestResponse = pointOfInterestService
                .insert(request.toPointOfInterest())
                .toPointOfInterestResponse()

        return ResponseEntity.ok(pointOfInterestResponse)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<PointOfInterestResponse>>? {
        val pointOfInterests: List<PointOfInterest>? = pointOfInterestService.findAll()
        return pointOfInterests.toPointOfInterestResponse()?.let { ResponseEntity.ok(it) }
    }

    @Throws(RuntimeException::class)
    @GetMapping("/findByNearestOnes")
    fun findByNearestOnes(@Valid @RequestParam(required = true) x: Int,
                          @Valid @RequestParam(required = true) y: Int,
                          @Valid @RequestParam(required = true) maxDistance: Int): ResponseEntity<List<PointOfInterestResponse>?>? {

        return pointOfInterestService.findNearestOnes(x, y, maxDistance).toPointOfInterestResponse()?.let { ResponseEntity.ok(it) }
    }

}