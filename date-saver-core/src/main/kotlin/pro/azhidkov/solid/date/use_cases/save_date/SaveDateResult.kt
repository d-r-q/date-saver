package pro.azhidkov.solid.date.use_cases.save_date

/*
 * Ответственности:
 * * Хранение информации о результате сохранения даты
 *
 * Стейкхолдеры:
 * * Архитектор
 * * Разработчики
 *
 * Причины для изменения:
 * * Изменение возможных исходов сохранения даты
 *
 * Секрет:
 * Нет
 */
interface SaveDateResult

class Ok : SaveDateResult

class Error(val reason: Exception) : SaveDateResult
