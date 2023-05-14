package com.github.bloomspes.sample

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [SampleApplication::class])
class SampleApplicationTests {
	@Test
	fun contextLoads() {
	}
}
