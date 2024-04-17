package services

import dto.CityDto
import model.Cities
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction



data class City(val id: Int, val name: String, val population: Int?)

class CityService {

    private fun toRows(row: ResultRow): City {
        return City(
            id = row[Cities.id],
            name = row[Cities.name],
            population = row[Cities.population]
        )
    }

    fun getAlls(): List<City> {
        return transaction {
            Cities.selectAll().map { toRows(it) }
        }
    }

    fun getWhereName(pname: String): List<City>  {
        return transaction { Cities.selectAll()
            .adjustWhere { Cities.name eq pname }
            .toList().map { toRows(it) }
        }
    }

    fun find(id: CityId): CityDto? = transaction {
        Cities.select { Cities.id eq id }.firstOrNull()?.let {
            CityDto(
                it[Cities.id],
                it[Cities.name],
                it[Cities.population]
            )
        }
    }

    fun insert(person: CityDto): CityId = transaction {
        Cities.insert {
            it[name] = person.name
            it[population] = person.population

        }.resultedValues!!.map { it[Cities.id] }.first()
    }

    fun update(id: CityId, city: CityDto) {
        Cities.update({ Cities.id eq id }) {
            it[name] = city.name
            it[population] = city.population
        }
    }

    fun delete(id: CityId): Unit = transaction {
        Cities.deleteWhere {
            Cities.id eq id
        }
    }


}

typealias CityId = Int