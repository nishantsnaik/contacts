plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1" // Avro plugin
	id("java")
}


group = "com.nishant"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21


java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
	maven("https://packages.confluent.io/maven/")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.postgresql:postgresql")
	implementation("org.apache.avro:avro:1.11.1") // Avro library
	implementation("io.confluent:kafka-avro-serializer:7.5.1") // or match your Confluent platform version


	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "junit-vintage-engine")
	}
}

avro {
	fieldVisibility.set("PRIVATE")
	stringType.set("String")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		jvmTarget = "21"
	}
}
