package pro.azhidkov.solid.date.view.save_date

import pro.azhidkov.solid.date.use_cases.save_date.SaveDateInteractor
import pro.azhidkov.solid.date.use_cases.save_date.SaveDateRequest
import pro.azhidkov.solid.date.view.SaveResultPresenter

/*
 * Ответственности:
 * * Обработка сигнала от платформы о нажатии на кнопку "сохранить"
 *
 * Стейкхолдеры:
 * * Архитектор
 * * Разработчики
 *
 * Причины для изменения:
 * * Изменение механизма оповещения о нажатии на кнопку
 *
 * Секрет:
 * Механизм получния сигнала от платформы
 */

// контроллер завязан на механизм доставки сигналов
class SaveDateController(
    private val saveDateInteractor: SaveDateInteractor,
    private val saveResultPresenter: SaveResultPresenter
) {

    // цикл в зависимостях модулей контроллер (save_date) -> презентер/вью(view) -> событие (save_date)
    fun onSaveClicked(saveDateClicked: SaveDateClicked) {
        // отображение объекта из представления в объект из бизнес правил
        // надо ли - хороший вопрос
        val res = saveDateInteractor.saveDate(
            SaveDateRequest(saveDateClicked.day.toInt(), saveDateClicked.month.toInt(), saveDateClicked.year.toInt())
        )
        saveResultPresenter.showSaveResult(res)
    }

}