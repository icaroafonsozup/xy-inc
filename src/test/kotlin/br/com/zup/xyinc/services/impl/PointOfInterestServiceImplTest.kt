package br.com.zup.xyinc.services.impl


import br.com.zup.xyinc.data.documents.PointOfInterest
import br.com.zup.xyinc.data.repositories.PointOfInterestRepository
import br.com.zup.xyinc.services.PointOfInterestService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
internal class PointOfInterestServiceImplTest {

    @Autowired
    val poiService: PointOfInterestService? = null

    @MockBean
    private val poiRepository: PointOfInterestRepository? = null

    private val name = "teste"

    private val xCoordinate = 1

    private val yCoordinate = 2

    @Before
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(poiRepository?.findByName(name)).willReturn(pointOfInterest())
        BDDMockito.given(poiRepository?.findByxCoordinateAndyCoordinate(xCoordinate, yCoordinate)).willReturn(pointOfInterest())
        BDDMockito.given(poiRepository?.findAll()).willReturn(pointOfInterestList())
        BDDMockito.given(poiRepository?.insert(pointOfInterest())).willReturn(pointOfInterest())
    }


    @Test
    fun findAll() {
        val poiList: List<PointOfInterest>? = poiService?.findAll()
        Assert.assertNotNull(poiList)
        Assert.assertEquals(3, poiList?.size)
    }

    @Test
    fun findByCoordinates() {
        val poi: PointOfInterest? = poiService?.findByCoordinates(xCoordinate, yCoordinate)
        Assert.assertNotNull(poi)
        Assert.assertEquals(xCoordinate, poi?.xCoordinate)
        Assert.assertEquals(yCoordinate, poi?.yCoordinate)
        Assert.assertEquals(name, poi?.name)
    }

    @Test
    fun findByName() {
        val poi: PointOfInterest? = poiService?.findByName(name)
        Assert.assertNotNull(poi)
        Assert.assertEquals(xCoordinate, poi?.xCoordinate)
        Assert.assertEquals(yCoordinate, poi?.yCoordinate)
        Assert.assertEquals(name, poi?.name)
    }

    @Test
    fun insert() {
        val poi: PointOfInterest? = poiService?.insert(pointOfInterest())
        Assert.assertNotNull(poi)
        Assert.assertEquals(1.toLong(), poi?.id)
        Assert.assertEquals(xCoordinate, poi?.xCoordinate)
        Assert.assertEquals(yCoordinate, poi?.yCoordinate)
        Assert.assertEquals(name, poi?.name)

    }

    private fun pointOfInterest(): PointOfInterest = PointOfInterest(1, 2, "teste", 1)


    private fun pointOfInterestList(): List<PointOfInterest> =
            listOf(PointOfInterest(1, 2, "teste", 1),
                    PointOfInterest(3, 4, "teste 2", 2),
                    PointOfInterest(3, 5, "teste 3", 3))

}