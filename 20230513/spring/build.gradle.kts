import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "com.github.bloomspes"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Spring Boot Devtools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")


	// PostgreSQL
	implementation("org.postgresql:postgresql:42.6.0")

	// Spring Data JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// Mockk
	testImplementation("io.mockk:mockk:1.13.5")

	// MockkBean
	testImplementation("com.ninja-squad:springmockk:4.0.2")

	// TSID
	implementation("io.hypersistence:hypersistence-tsid:2.0.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
