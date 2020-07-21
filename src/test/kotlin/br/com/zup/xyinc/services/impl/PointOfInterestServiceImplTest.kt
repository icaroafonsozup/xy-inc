package br.com.zup.xyinc.services.impl


import br.com.zup.xyinc.data.documents.PointOfInterest
import br.com.zup.xyinc.data.repositories.PointOfInterestRepository
import br.com.zup.xyinc.services.PointOfInterestService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean


@SpringBootTest
internal class PointOfInterestServiceImplTest {

    @Autowired
    val poiService: PointOfInterestService? = null

    @MockBean
    private val poiRepository: PointOfInterestRepository? = null

    private val name = "teste"

    private val xCoordinate = 20

    private val yCoordinate = 10

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(poiRepository?.findByName(name)).willReturn(pointOfInterest())
        BDDMockito.given(poiRepository?.findByxCoordinateAndYCoordinate(xCoordinate, yCoordinate)).willReturn(pointOfInterest())
        BDDMockito.given(poiRepository?.findAll()).willReturn(pointOfInterestList())
        BDDMockito.given(poiRepository?.insert(pointOfInterest())).willReturn(pointOfInterest())
    }


    @Test
    fun findAll() {
        val poiList: List<PointOfInterest>? = poiService?.findAll()
        Assertions.assertNotNull(poiList)
        Assertions.assertEquals(7, poiList?.size)
    }

    @Test
    fun findByCoordinates() {
        val poi: PointOfInterest? = poiService?.findByCoordinates(xCoordinate, yCoordinate)
        Assertions.assertNotNull(poi)
        Assertions.assertEquals(xCoordinate, poi?.xCoordinate)
        Assertions.assertEquals(yCoordinate, poi?.yCoordinate)
        Assertions.assertEquals(name, poi?.name)
    }

    @Test
    fun findByName() {
        val poi: PointOfInterest? = poiService?.findByName(name)
        Assertions.assertNotNull(poi)
        Assertions.assertEquals(xCoordinate, poi?.xCoordinate)
        Assertions.assertEquals(yCoordinate, poi?.yCoordinate)
        Assertions.assertEquals(name, poi?.name)
    }

    @Test
    fun insert() {
        val poi: PointOfInterest? = poiService?.insert(pointOfInterest())
        Assertions.assertNotNull(poi)
        Assertions.assertEquals(xCoordinate, poi?.xCoordinate)
        Assertions.assertEquals(yCoordinate, poi?.yCoordinate)
        Assertions.assertEquals(name, poi?.name)

    }

    @Test
    fun findNearestOnes10Mts() {
        val poi: List<PointOfInterest>? = poiService?.findNearestOnes(xCoordinate, yCoordinate, 10)
        Assertions.assertNotNull(poi)
        Assertions.assertEquals(poi?.size, 4)
        val lanchonete = poi?.stream()?.filter { it.name == "Lanchonete" }?.findAny()?.orElse(null)
        Assertions.assertNotNull(lanchonete)
        val joalheria = poi?.stream()?.filter { it.name == "Joalheria" }?.findAny()?.orElse(null)
        Assertions.assertNotNull(joalheria)
        val pub = poi?.stream()?.filter { it.name == "Pub" }?.findAny()?.orElse(null)
        Assertions.assertNotNull(pub)
        val supermercado = poi?.stream()?.filter { it.name == "Supermercado" }?.findAny()?.orElse(null)
        Assertions.assertNotNull(supermercado)
        val posto = poi?.stream()?.filter { it.name == "Posto" }?.findAny()?.orElse(null)
        Assertions.assertNull(posto)
        val floricultura = poi?.stream()?.filter { it.name == "Floricultura" }?.findAny()?.orElse(null)
        Assertions.assertNull(floricultura)
        val churrascaria = poi?.stream()?.filter { it.name == "Churrascaria" }?.findAny()?.orElse(null)
        Assertions.assertNull(churrascaria)
    }


    private fun pointOfInterest(): PointOfInterest = PointOfInterest(xCoordinate, yCoordinate, "teste", "5f161d1ec0a8fe001aba8b1b")

    private fun pointOfInterestList(): List<PointOfInterest> =
            listOf(PointOfInterest(27, 12, "Lanchonete", "5f161ce4c0a8fe001aba8b17"),
                    PointOfInterest(31, 18, "Posto", "5f161cf2c0a8fe001aba8b18"),
                    PointOfInterest(15, 12, "Joalheria", "5f161cffc0a8fe001aba8b19"),
                    PointOfInterest(19, 21, "Floricultura", "5f161d0dc0a8fe001aba8b1a"),
                    PointOfInterest(12, 8, "Pub", "5f161d1ec0a8fe001aba8b1b"),
                    PointOfInterest(23, 6, "Supermercado", "5f161d2dc0a8fe001aba8b1c"),
                    PointOfInterest(28, 2, "Churrascaria", "5f161d3dc0a8fe001aba8b1d"))


}