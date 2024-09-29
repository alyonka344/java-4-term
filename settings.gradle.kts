rootProject.name = "labs"

include("labs:Banks")

include("labs:Kotiki")
findProject(":labs:Kotiki")?.name = "Kotiki"
include("labs:Kotiki:Controller")
findProject(":labs:Kotiki:Controller")?.name = "Controller"
include("labs:Kotiki:DAO")
findProject(":labs:Kotiki:DAO")?.name = "DAO"
include("labs:Kotiki:Entities")
findProject(":labs:Kotiki:Entities")?.name = "Entities"
include("labs:Kotiki:Services")
findProject(":labs:Kotiki:Services")?.name = "Services"

include("labs:Kotiki2:Services")
findProject(":labs:Kotiki2:Services")?.name = "Services"
include("labs:Kotiki2")
findProject(":labs:Kotiki2")?.name = "Kotiki2"
include("labs:Kotiki2:Entities")
findProject(":labs:Kotiki2:Entities")?.name = "Entities"
include("labs:Kotiki2:Controllers")
findProject(":labs:Kotiki2:Controllers")?.name = "Controllers"
