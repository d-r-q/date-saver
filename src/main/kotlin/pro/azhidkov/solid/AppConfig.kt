package pro.azhidkov.solid

import pro.azhidkov.solid.date.storage.DateStorage
import pro.azhidkov.solid.date.view.DateView


/*
 * Ответственности:
 * * Создание графа объектов приложения
 *
 * Стейкхолдеры:
 * * Архитектор
 *
 * Причины для изменения:
 * * Добавление новой функциональности
 * * Рефакторинг
 *
 * * Секрет:
 * * Список объектов приложения
 * * Инициализация приложения
 */
object AppConfig {

    private val dateStorage = DateStorage()

    val dateView = DateView(dateStorage)

    init {
        dateStorage.init()
    }

}