

import dto.CityDto
import model.Cities
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import services.StarService
import services.CityService
import table.UserTable

fun main() {
    Database.connect("jdbc:pgsql://localhost:5432/kto_db",
        /*driver = "org.postgresql.Driver",*/
        user = "postgres", password = "postgres")

    println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
    println(" INICIANDO ")
    transaction {
        addLogger(StdOutSqlLogger)

        //SchemaUtils.create(Cities, UserTable)
        //SchemaUtils.create(Cities)
        //SchemaUtils.create(StarWarsFilms)

        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        println("Manipulation StarWarsFilm ")
        val starManager = StarService()
        //starManager.create("Mandalorian 3 T" , 3, "Okuma matata")
        val result = starManager.getAlls()
        for (s in result) {
            println("${s.id}: ${s.name}")
        }

        val seq = starManager.getWhereSeq(1)
        println("StarWarsFilms Where: ${seq}")
        println("StarWarsFilms Result: ${result}")




        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        println("manipulation Cities ")
        val cityService = CityService()
        //val sp = CityDto(null, "Campo Gra", 8500)
        //val spUp = CityDto(null, "Campo Grande", 3500)
        //val spId = cityService.insert(sp)
        //println("Cities ID: ${spId}")
        //cityService.update(spId , spUp)
        //cityService.delete(21)


        val cities = cityService.getAlls()
        for (s in cities) {
            println("${s.id}: ${s.name}")
        }

        val citi = cityService.find(21)
        println("AQUIIIII : ${citi}")
        if (citi != null) {
            println("Name = ${citi.name}")
        }




        val pragueName = Cities.select { Cities.id eq 21}.single()[Cities.name]
        println("pragueName = $pragueName")

        val citin = cityService.getWhereName(pragueName)
        println("List = $citin")


        UserTable.insert {
            //it[id] = "andrey"
            it[name] = "Andrey"
            it[cityId] = citi?.id
        }

//        Users.insert {
//            it[id] = "sergey"
//            it[name] = "Sergey"
//            it[Users.cityId] = munichId
//        }

//        Users.insert {
//            it[id] = "eugene"
//            it[name] = "Eugene"
//            it[Users.cityId] = munichId
//        }

//        Users.insert {
//            it[id] = "alex"
//            it[name] = "Alex"
//            it[Users.cityId] = null
//        }

//        Users.insert {
//            it[id] = "smth"
//            it[name] = "Something"
//            it[Users.cityId] = null
//        }

//        Users.update({ Users.id eq "alex" }) {
//            it[name] = "Alexey"
//        }
//
//        Users.deleteWhere{ Users.name like "%thing" }



//        (Users innerJoin Cities)
//            .slice(Users.name, Cities.name)
//            .select {
//                (Users.id.eq("andrey") or Users.name.eq("Sergey")) and
//                        Users.id.eq("sergey") and Users.cityId.eq(Cities.id)
//            }.forEach {
//                println("${it[Users.name]} lives in ${it[Cities.name]}")
//            }
//
//        println("Join with foreign key:")

//        (Users innerJoin Cities)
//            .slice(Users.name, Users.cityId, Cities.name)
//            .select { Cities.name.eq("St. Petersburg") or Users.cityId.isNull() }
//            .forEach {
//                if (it[Users.cityId] != null) {
//                    println("${it[Users.name]} lives in ${it[Cities.name]}")
//                }
//                else {
//                    println("${it[Users.name]} lives nowhere")
//                }
//            }
//
//        println("Functions and group by:")
//
//        ((Cities innerJoin Users)
//            .slice(Cities.name, Users.id.count())
//            .selectAll()
//            .groupBy(Cities.name)
//                ).forEach {
//                val cityName = it[Cities.name]
//                val userCount = it[Users.id.count()]
//
//                if (userCount > 0) {
//                    println("$userCount user(s) live(s) in $cityName")
//                } else {
//                    println("Nobody lives in $cityName")
//                }
//            }
//
//        SchemaUtils.drop(Users, Cities)
    }
}
