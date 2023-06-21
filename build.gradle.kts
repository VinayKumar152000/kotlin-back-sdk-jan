plugins {
	java
	`maven-publish`
	id("org.springframework.boot") version "2.7.12"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.example"
version = "0.0.2-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
	mavenCentral()
}

publishing {
	publishing {
		publications {
			register("mavenJava", MavenPublication::class) {
				from(components["java"])

				versionMapping {
					usage("java-api") {
						fromResolutionOf("runtimeClasspath")
					}
					usage("java-runtime") {
						fromResolutionResult()
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
