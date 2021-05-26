package pro.azhidkov.solid.date.view.save_date


/*
 * Ответственности:
 * * Хранение информации о состоянии пользовательского интерфейса на момент нажатия кнопки
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
class SaveDateClicked(
    val day: String,
    val month: String,
    val year: String
)