package pro.azhidkov.solid.date.view

import javafx.scene.paint.Color
import pro.azhidkov.solid.date.domain.ValidationFailed
import pro.azhidkov.solid.date.use_cases.save_date.Error
import pro.azhidkov.solid.date.use_cases.save_date.Ok
import pro.azhidkov.solid.date.use_cases.save_date.SaveDateResult


/*
 * Ответственности:
 * * "Перевод" результата сохранения данных с языка домена на язык пользовательского представления
 *
 * Стейкхолдеры:
 * * Архитектор
 * * Разработчики
 * * UI-дизайнер
 * * UX-дизайнер
 *
 * Причины для изменения:
 * * Изменение возможных исходов сохранения даты
 * * Изменение отображения результата сохранения даты
 *
 * Секрет:
 * * Правила отображения результата пользователя в характеристики пользовательского интерфейса
 */
class SaveResultPresenter(
    private val dateView: DateView
) {

    fun showSaveResult(saveResult: SaveDateResult) {
        // Поместить этот маппинг в SaveDateResult нельзя, потому что он является частью бизнес правил,
        // а этот маппинг - часть представления
        val text = when {
            saveResult is Ok -> "Ок!"
            saveResult is Error && saveResult.reason is ValidationFailed -> "Невалидная дата"
            else -> "Ошибка сохранения"
        }
        // По феншую тут надо завести собственный енам и отвязать презентер от javafx, но в данном случае это перебор
        val color = when (saveResult) {
            is Ok -> Color.GREEN
            else -> Color.RED
        }
        dateView.showSaveFeedback(FeedbackViewModel(text, color))
    }

}

/*
 * Ответственности:
 * * Хранение плана отображения обратной связи сохранения даты
 *
 * Стейкхолдеры:
 * * Архитектор
 *
 * Причины для изменения:
 * * Изменение в представлении обратной связи
 *
 * Секрет:
 * Нет
 */
data class FeedbackViewModel(
    val text: String,
    val color: Color
)
