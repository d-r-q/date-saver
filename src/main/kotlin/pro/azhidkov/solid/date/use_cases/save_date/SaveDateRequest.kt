package pro.azhidkov.solid.date.use_cases.save_date


/*
 * Ответственности:
 * * Хранение информации о запросе на сохранение даты
 *
 * Стейкхолдеры:
 * * Архитектор
 * * Разработчики
 *
 * Причины для изменения:
 * * Изменение структуры даты
 *
 * Секрет:
 * Нет
 */
data class SaveDateRequest(
    val day: Int,
    val month: Int,
    val year: Int
)