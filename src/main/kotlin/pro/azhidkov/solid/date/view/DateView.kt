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
import pro.azhidkov.solid.date.domain.DateValidator
import pro.azhidkov.solid.date.storage.DateStorage


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
class DateView : VBox() {

    private val storage = DateStorage()
    private val ddField = TextField().apply { maxWidth = 40.0 }
    private val mmField = TextField().apply { maxWidth = 40.0 }
    private val yyField = TextField().apply { maxWidth = 80.0 }
    private val feedbackLabel = Label("").apply { isVisible = false; padding = Insets(10.0) }

    init {
        storage.init()

        children.add(Label("Введите дату:"))
        val inputsPane = HBox().apply {
            padding = Insets(10.0)
            val date = storage.loadDate()
            if (date != null) {
                ddField.text = date.day.toString()
                mmField.text = date.month.toString()
                yyField.text = date.year.toString()
            }
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
                if (DateValidator().validate(
                        ddField.text.toInt(),
                        mmField.text.toInt(),
                        yyField.text.toInt()
                    ) != null
                ) {
                    feedbackLabel.text = "Невалидная дата"
                    feedbackLabel.textFill = Color.RED
                    feedbackLabel.isVisible = true
                    return@EventHandler
                }
                try {
                    storage.saveDate(Date(ddField.text.toInt(), mmField.text.toInt(), yyField.text.toInt()))
                    feedbackLabel.text = "Ок!"
                    feedbackLabel.textFill = Color.GREEN
                    feedbackLabel.isVisible = true
                } catch (e: Exception) {
                    feedbackLabel.text = "Ошибка сохранения"
                    feedbackLabel.textFill = Color.RED
                    feedbackLabel.isVisible = true
                }
            }
        }
        children.add(saveBtn)
        alignment = Pos.CENTER
    }

}