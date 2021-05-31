package pro.azhidkov.solid.date.view

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import pro.azhidkov.solid.date.view.save_date.SaveDateClicked
import pro.azhidkov.solid.event_bus.EventBus


/*
 * Ответственности:
 * * Отображение формы ввода и сохранения даты для пользователя
 *
 * Стейкхолдеры:
 * * Пользователь/продакт оунер (в зависимости от процесса)
 * * UI-дизайнер (визуальное представление программа)
 * * UX-дизайнер (поведение программы)
 *
 * Причины для изменения:
 * * Повышение визуальной привлекательности формы
 * * Повышение удобства использования формы
 * * Изменение структуры даты
 *
 * Секрет:
 * * Фреймворк представления
 * * Пользовательский интерфейс
 */
class DateView(
    private val eventBus: EventBus<SaveDateClicked>
) : VBox() {

    private val ddField = TextField().apply { maxWidth = 40.0 }
    private val mmField = TextField().apply { maxWidth = 40.0 }
    private val yyField = TextField().apply { maxWidth = 80.0 }
    private val feedbackLabel = Label("").apply { isVisible = false; padding = Insets(10.0) }

    var day: String
        get() = ddField.text
        set(day) {
            ddField.text = day
        }

    var month: String
        get() = mmField.text
        set(month) {
            mmField.text = month
        }

    var year: String
        get() = yyField.text
        set(year) {
            yyField.text = year
        }

    init {
        children.add(Label("Введите дату:"))
        val inputsPane = HBox().apply {
            padding = Insets(10.0)
            children.add(ddField)
            children.add(Label("."))
            children.add(mmField)
            children.add(Label("."))
            children.add(yyField)
            alignment = Pos.CENTER
        }
        children.add(inputsPane)
        children.add(feedbackLabel)
        val saveBtn = Button("Сохранить").apply {
            this.onAction = EventHandler {
                // Следование OCP: поведение кнопки  сохранить теперь открыто для расширения
                eventBus.publishEvent(SaveDateClicked(day, month, year))
            }
        }
        children.add(saveBtn)
        alignment = Pos.CENTER
    }

    fun showSaveFeedback(feedbackViewModel: FeedbackViewModel) {
        feedbackLabel.text = feedbackViewModel.text
        feedbackLabel.textFill = feedbackViewModel.color
        feedbackLabel.isVisible = true
    }

}