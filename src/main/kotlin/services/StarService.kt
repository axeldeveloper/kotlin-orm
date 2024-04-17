package services

import model.StarWarsFilms
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


data class StarWarsFilm(val id: Int, val name: String, val director: String)

class StarService {

    private fun toRows(row: ResultRow): StarWarsFilm {
        return StarWarsFilm(
            id = row[StarWarsFilms.sequelId],
            name = row[StarWarsFilms.name],
            director = row[StarWarsFilms.director]
        )
    }

    fun getAlls(): List<StarWarsFilm> {
        return transaction {
            StarWarsFilms.selectAll().map { toRows(it) }
        }
    }

    fun getWhereSeq(psequel: Int): List<StarWarsFilm>  {
        return transaction { StarWarsFilms.selectAll()
            .adjustWhere { StarWarsFilms.sequelId eq psequel }
            .toList().map { toRows(it) }

        }
    }

    fun create (pname: String, psequel: Int, pdirector: String): Int {
        val stat2 = StarWarsFilms.insert {
            it[name] = pname
            it[sequelId] = psequel
            it[director] = pdirector
        }
        return stat2.insertedCount;
    }
}