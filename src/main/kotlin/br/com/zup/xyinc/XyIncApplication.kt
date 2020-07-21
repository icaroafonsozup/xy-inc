package br.com.zup.xyinc

import br.com.zup.xyinc.data.repositories.PointOfInterestRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class XyIncApplication(val poiRepository: PointOfInterestRepository) : CommandLineRunner {
	override fun run(vararg args: String?) {

	}


}

fun main(args: Array<String>) {
	SpringApplication.run(XyIncApplication::class.java, *args)
}
