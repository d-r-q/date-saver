package pro.azhidkov.solid.date.view

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import pro.azhidkov.solid.date.domain.Date
import pro.azhidkov.solid.date.domain.ValidationFailed
import pro.azhidkov.solid.date.use_cases.save_date.Error
import pro.azhidkov.solid.date.use_cases.save_date.Ok
import pro.azhidkov.solid.date.use_cases.save_date.SaveDateInteractor
import pro.azhidkov.solid.date.use_cases.save_date.SaveDateRequest


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
    private val saveDateInteractor: SaveDateInteractor
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
                val res = saveDateInteractor.saveDate(
                    SaveDateRequest(
                        ddField.text.toInt(),
                        mmField.text.toInt(),
                        yyField.text.toInt()
                    )
                )
                feedbackLabel.isVisible = true
                val color = if (res is Ok) {
                    Color.GREEN
                } else {
                    Color.RED
                }
                val text = when {
                    res is Ok -> "Ок!"
                    res is Error && res.reason is ValidationFailed -> "Невалидная дата"
                    else -> "Ошибка сохранения"
                }

                feedbackLabel.text = text
                feedbackLabel.textFill = color
                feedbackLabel.isVisible = true
            }
        }
        children.add(saveBtn)
        alignment = Pos.CENTER
    }

    fun showDate(date: Date?) {
        day = date?.day?.toString() ?: "дд"
        month = date?.month?.toString() ?: "мм"
        year = date?.year?.toString() ?: "гг"
    }

}