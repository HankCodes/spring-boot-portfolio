rootProject.name = "portfolio"
include("long-running-task")
include("tickets-api")
include("common")
include("common:test-utils")
findProject(":common:test-utils")?.name = "test-utils"
