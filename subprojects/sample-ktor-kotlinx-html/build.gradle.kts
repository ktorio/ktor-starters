plugins {
    id("hackathon.kotlin-jvm")
}

dependencies {
    implementation(projects.subprojects.extensionKtor)
    implementation(projects.subprojects.extensionKotlinxHtml)
}
