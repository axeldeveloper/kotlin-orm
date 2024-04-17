package model

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Cities : Table() {
    val id: Column<Int> = integer("id").autoIncrement("seq_id_cities")
    val name: Column<String> = varchar("name", length = 50)
    val population: Column<Int?> = (integer("population") ).nullable()

    override val primaryKey = PrimaryKey(id, name = "cities_pkey") // name is optional here
}
