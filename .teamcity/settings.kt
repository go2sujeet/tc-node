version = "2023.1"

project {
    buildType(Build)
    buildType(RunDocker)
}

object Build : BuildType({
    name = "Build Docker Image"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            scriptContent = """
                docker build -t nodejs-teamcity-app .
            """
        }
    }
})

object RunDocker : BuildType({
    name = "Run Docker Image"

    steps {
        script {
            scriptContent = """
                docker run -d -p 3000:3000 nodejs-teamcity-app
            """
        }
    }

    dependencies {
        snapshot(Build) {}
    }
})
