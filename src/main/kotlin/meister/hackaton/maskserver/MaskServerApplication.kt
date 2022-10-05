package meister.hackaton.maskserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MaskServerApplication

fun main(args: Array<String>) {
	runApplication<MaskServerApplication>(*args)
}
