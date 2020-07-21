package br.com.zup.xyinc.controller

import br.com.zup.xyinc.data.documents.PointOfInterest
import br.com.zup.xyinc.domain.dtos.PointOfInterestRequest
import br.com.zup.xyinc.services.impl.PointOfInterestServiceImpl
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class PointOfInterestControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val pointOfInterestService: PointOfInterestServiceImpl? = null

    private val urlBase: String = "/pointOfInterest"
    private val idPoi: String = "5f161d1ec0a8fe001aba8b1b"
    private val validX: Int = 1
    private val invalidX: Int = -1
    private val validY: Int = 2
    private val invalidY: Int = -1
    private val validName: String = "Teste"
    private val invalidEmptyName: String = ""
    private val invalidLongName: String = "qwertyuiopasdfghjklçzxcvbnmqwertyuiopasdfghjklçzxcvbnm"
    private val invalidShortName: String = "nm"


    @Test
    @Throws(Exception::class)
    fun insert() {
        val pointOfInterest: PointOfInterest = pointOfInterest()
        BDDMockito.given<PointOfInterest>(pointOfInterestService?.insert(pointOfInterestIsert())).willReturn(pointOfInterest)

        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(getPostRequest(validX, validY, validName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id").value(idPoi))
                .andExpect(jsonPath("$.name").value(pointOfInterest.name))
                .andExpect(jsonPath("$.xCoordinate").value(pointOfInterest.xCoordinate))
                .andExpect(jsonPath("$.yCoordinate").value(pointOfInterest.yCoordinate))
    }

    @Test
    @Throws(Exception::class)
    fun insertInvalidX() {
        val pointOfInterest: PointOfInterest = pointOfInterest()
        BDDMockito.given<PointOfInterest>(pointOfInterestService?.insert(pointOfInterestIsert())).willReturn(pointOfInterest)

        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(getPostRequest(invalidX, validY, validName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.code").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.messages[*]").value("O Valor de X deve ser um inteiro positivo."))
    }

    @Test
    @Throws(Exception::class)
    fun insertInvalidY() {
        val pointOfInterest: PointOfInterest = pointOfInterest()
        BDDMockito.given<PointOfInterest>(pointOfInterestService?.insert(pointOfInterestIsert())).willReturn(pointOfInterest)

        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(getPostRequest(validX, invalidY, validName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.code").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.messages[*]").value("O Valor de Y deve ser um inteiro positivo."))
    }

    @Test
    @Throws(Exception::class)
    fun insertInvalidEmptyName() {
        val pointOfInterest: PointOfInterest = pointOfInterest()
        BDDMockito.given<PointOfInterest>(pointOfInterestService?.insert(pointOfInterestIsert())).willReturn(pointOfInterest)

        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(getPostRequest(validX, validY, invalidEmptyName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.code").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.messages[?(@=='Nome não pode ser vazio.')]").isNotEmpty)
                .andExpect(jsonPath("$.messages[?(@=='Nome deve possuir entre 3 e 50 caracteres.')]").isNotEmpty)
    }

    @Test
    @Throws(Exception::class)
    fun insertInvalidShortName() {
        val pointOfInterest: PointOfInterest = pointOfInterest()
        BDDMockito.given<PointOfInterest>(pointOfInterestService?.insert(pointOfInterestIsert())).willReturn(pointOfInterest)

        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(getPostRequest(validX, validY, invalidShortName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.code").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.messages[0]").value("Nome deve possuir entre 3 e 50 caracteres."))
    }

    @Test
    @Throws(Exception::class)
    fun insertInvalidLongName() {
        val pointOfInterest: PointOfInterest = pointOfInterest()
        BDDMockito.given<PointOfInterest>(pointOfInterestService?.insert(pointOfInterestIsert())).willReturn(pointOfInterest)

        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(getPostRequest(validX, validY, invalidLongName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.code").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.messages[0]").value("Nome deve possuir entre 3 e 50 caracteres."))
    }

    @Test
    fun findAll() {
        val pointOfInterest: List<PointOfInterest> = pointOfInterestList()
        BDDMockito.given<List<PointOfInterest>>(pointOfInterestService?.findAll()).willReturn(pointOfInterest)

        mvc!!.perform(MockMvcRequestBuilders.get(urlBase)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[0].xCoordinate").value(validX))
                .andExpect(jsonPath("$.[0].yCoordinate").value(validY))
                .andExpect(jsonPath("$.[0].name").value(validName))
                .andExpect(jsonPath("$.[0].id").value(idPoi))
                .andExpect(jsonPath("$.length()", `is`(3)))

    }

    @Test
    fun findByNearestOnes() {
        val pointOfInterest: List<PointOfInterest> = pointOfInterestList()
        BDDMockito.given<List<PointOfInterest>>(pointOfInterestService?.findNearestOnes(validX, validY, 10)).willReturn(pointOfInterest)

        mvc!!.perform(MockMvcRequestBuilders.get("$urlBase/findByNearestOnes?x=$validX&y=$validY&maxDistance=10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[0].xCoordinate").value(validX))
                .andExpect(jsonPath("$.[0].yCoordinate").value(validY))
                .andExpect(jsonPath("$.[0].name").value(validName))
                .andExpect(jsonPath("$.[0].id").value(idPoi))
                .andExpect(jsonPath("$.length()", `is`(3)))
    }


    @Throws(JsonProcessingException::class)
    private fun getPostRequest(x: Int, y: Int, name: String): String {
        val poiDto: PointOfInterestRequest = PointOfInterestRequest(x, y, name)
        val mapper = ObjectMapper()
        return mapper.writeValueAsString(poiDto)
    }

    private fun pointOfInterestIsert(): PointOfInterest = PointOfInterest(validX, validY, validName)

    private fun pointOfInterest(): PointOfInterest = PointOfInterest(validX, validY, validName, idPoi)

    private fun pointOfInterestList(): List<PointOfInterest> =
            listOf(PointOfInterest(validX, validY, validName, idPoi),
                    PointOfInterest(3, 4, "teste 2", "5f161d1ec0a8fe001aba8b12"),
                    PointOfInterest(3, 5, "teste 3", "5f161d1ec0a8fe001aba8b1e"))
}