plugins {
    id("fabric-loom") version "1.11-SNAPSHOT"
    id("maven-publish")
}

version = project.property("version") as String
group = project.property("group") as String
var id = project.property("mod_id") as String
var zstdVersion = project.property("zstd_version") as String
var brotliVersion = project.property("brotli_version") as String

base {
    archivesName.set(project.property("mod_name") as String)
}

loom {
    accessWidenerPath = file("src/main/resources/${id}.accesswidener")
    mixin {
        defaultRefmapName = "${id}.refmap.json"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings(loom.officialMojangMappings())
    //mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${project.property("fabric_loader_version")}")

    include(implementation("com.github.luben:zstd-jni:${zstdVersion}")!!)

    include(implementation("com.aayushatharva.brotli4j:brotli4j:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:service:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:native-linux-aarch64:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:native-linux-x86_64:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:native-linux-riscv64:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:native-osx-x86_64:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:native-osx-aarch64:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:native-windows-aarch64:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:native-windows-x86_64:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:native-linux-aarch64:${brotliVersion}")!!)
    include(implementation("com.aayushatharva.brotli4j:native-linux-aarch64:${brotliVersion}")!!)

    modImplementation(files("pchf-common-1.21.8-0.0.3+1.21.8.jar"))

    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_version")}")
}

tasks.processResources {
    inputs.property("mod_id", id)
    inputs.property("mod_name", project.property("mod_name"))
    inputs.property("version", project.version)
    inputs.property("description", project.property("description"))
    inputs.property("minecraft_version", project.property("minecraft_version"))
    inputs.property("fabric_loader_version", project.property("fabric_loader_version"))
    inputs.property("fabric_game_version_range", project.property("fabric_game_version_range"))
    inputs.property("homepage_url", project.property("homepage_url"))
    inputs.property("issues_url", project.property("issues_url"))
    inputs.property("license", project.property("license"))
    inputs.property("java_version", project.property("java_version"))
    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand(
            "mod_id" to id,
            "mod_name" to project.property("mod_name"),
            "version" to project.version,
            "description" to project.property("description"),
            "minecraft_version" to project.property("minecraft_version"),
            "fabric_loader_version" to project.property("fabric_loader_version"),
            "fabric_game_version_range" to project.property("fabric_game_version_range"),
            "homepage_url" to project.property("homepage_url"),
            "issues_url" to project.property("issues_url"),
            "license" to project.property("license"),
            "java_version" to project.property("java_version")
        )
    }
}

val targetJavaVersion = 21
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
    withSourcesJar()
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.property("mod_name")}" }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.property("mod_name") as String
            from(components["java"])
        }
    }

    repositories {
    }
}