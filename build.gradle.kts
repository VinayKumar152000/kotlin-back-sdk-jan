plugins {
	java
	`maven-publish`
	id("org.springframework.boot") version "2.7.12"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
	mavenCentral()
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			artifactId = "common-lib"
			groupId = "com.example"
			from(components["java"])
			pom {
				name.set("Kotlin backend common library")
				description.set("This library contains different resolutions with ktor, exposed, serialization")
				licenses {
					license {
						name.set("The Apache License, Version 2.0")
						url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
					}
				}
			}
		}
	}
	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/VinayKumar152000/kotlin-back-sdk")
			credentials {
				username = System.getenv("GITHUB_ACTOR")
				password = System.getenv("GITHUB_TOKEN")

			}
		}
	}
}
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
