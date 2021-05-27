package pro.azhidkov.solid

import pro.azhidkov.solid.date.storage.DateStorage
import pro.azhidkov.solid.date.use_cases.save_date.SaveDateInteractor
import pro.azhidkov.solid.date.view.DateView
import pro.azhidkov.solid.date.view.init_date.InitDateController


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

    private val saveDateInteractor = SaveDateInteractor(dateStorage)

    val dateView = DateView(saveDateInteractor)

    private val initDateController = InitDateController(dateStorage::loadDate, dateView)

    init {
        dateStorage.init()
        initDateController.initDate()
    }

}