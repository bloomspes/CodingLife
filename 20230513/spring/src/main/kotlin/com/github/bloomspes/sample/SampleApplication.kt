package com.github.bloomspes.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class SampleApplication
fun main(args: Array<String>) {
	runApplication<SampleApplication>(*args)
}
