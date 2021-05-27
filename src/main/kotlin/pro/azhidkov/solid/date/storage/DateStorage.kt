package pro.azhidkov.solid.date.storage

import pro.azhidkov.solid.date.domain.Date
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException


/*
 * Ответственности:
 * * Хранение информации о дате
 *
 * Стейкхолдеры:
 * * Администратор БД (роль)
 *
 * Причины для изменения:
 * * изменение типа хранилища
 * * изменение логической схемы БД
 * * изменение структуры данных даты
 *
 * Секрет:
 * * Хранилище
 * * Способ храненения даты
 */
class DateStorage {

    private val conn = DriverManager.getConnection("jdbc:h2:/tmp/date-saver")

    fun init() {
        conn.createStatement()
            .executeUpdate("CREATE TABLE IF NOT EXISTS the_date (id BIGINT PRIMARY KEY, date DATE)")
    }

    fun loadDate(): Date? =
        conn.createStatement().use { stmt ->
            val rs = stmt.executeQuery("SELECT date FROM the_date WHERE id = 1")
            if (rs.next()) {
                mapDate(rs)
            } else {
                null
            }
        }

    @Throws(SQLException::class)
    fun saveDate(date: Date) {
        conn.createStatement().executeUpdate(
            """
            MERGE INTO the_date 
            KEY (ID) 
            VALUES (1, '${date.year}-${date.month}-${date.day}');
            """.trimIndent()
        )
    }

    private fun mapDate(rs: ResultSet): Date {
        val date = rs.getDate(1)
        val day = date.date
        // Особенности хранения инкапсулируются в хранилище
        val month = date.month + 1
        val year = 1900 + date.year
        return Date(day, month, year)
    }

}