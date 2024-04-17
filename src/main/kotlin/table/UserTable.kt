package table

import model.Cities
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable() {
    val name: Column<String> = varchar("name", length = 50)
    val cityId: Column<Int?> = (integer("city_id") references Cities.id).nullable()
}


/*
object User : Table() {
    val id: Column<String> = varchar("id", 10)
    val name: Column<String> = varchar("name", length = 50)
    val cityId: Column<Int?> = (integer("city_id") references Cities.id).nullable()
    override val primaryKey = PrimaryKey(id, name = "PK_User_ID") // name is optional here
}
*/