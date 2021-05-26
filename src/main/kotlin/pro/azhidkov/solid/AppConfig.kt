package pro.azhidkov.solid

import pro.azhidkov.solid.date.storage.H2DateStorage
import pro.azhidkov.solid.date.use_cases.save_date.SaveDateInteractor
import pro.azhidkov.solid.date.view.DatePresenter
import pro.azhidkov.solid.date.view.DateView
import pro.azhidkov.solid.date.view.SaveResultPresenter
import pro.azhidkov.solid.date.view.init_date.InitDateController
import pro.azhidkov.solid.date.view.save_date.SaveDateClicked
import pro.azhidkov.solid.date.view.save_date.SaveDateController
import pro.azhidkov.solid.event_bus.EventBus


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

    private val dateStorage = H2DateStorage()

    private val eventBus = EventBus<SaveDateClicked>()

    val dateView = DateView(eventBus)

    private val datePresenter = DatePresenter(dateView)

    private val initDateController = InitDateController(dateStorage::loadDate, datePresenter)

    private val saveDateInteractor = SaveDateInteractor(dateStorage)

    private val saveResultPresenter = SaveResultPresenter(dateView)

    private val saveDateController = SaveDateController(saveDateInteractor, saveResultPresenter)

    init {
        dateStorage.init()
        initDateController.initDate()
        // фактический цикл в зависимостях: контроллер -> презентер -> вью -> эвент бус -> контроллер
        // вынуждает использовать изменяемое состояние
        eventBus.addListener(saveDateController)
    }

}