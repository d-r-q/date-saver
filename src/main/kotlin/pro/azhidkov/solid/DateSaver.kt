package pro.azhidkov.solid


import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import java.sql.DriverManager


class DateSaver : Application() {

    private val conn = DriverManager.getConnection("jdbc:h2:/tmp/date-saver")
    private val ddField = TextField().apply { maxWidth = 40.0; promptText = "дд" }
    private val mmField = TextField().apply { maxWidth = 40.0; promptText = "мм" }
    private val yyField = TextField().apply { maxWidth = 80.0; promptText = "гггг" }
    private val feedbackLabel = Label("").apply { isVisible = false; padding = Insets(10.0) }

    override fun start(stage: Stage) {
        initDb()
        val pane = initUi()
        stage.scene = Scene(pane, 640.0, 480.0)
        stage.show()
    }

    private fun initUi(): VBox {
        return VBox().apply {
            children.add(Label("Введите дату:"))
            val inputsPane = HBox().apply {
                padding = Insets(10.0)
                with(conn.createStatement().executeQuery("SELECT date FROM the_date WHERE id = 1")) {
                    if (next()) {
                        val date = getDate(1)
                        ddField.text = date.date.toString()
                        mmField.text = (date.month + 1).toString()
                        yyField.text = (1900 + date.year).toString()
                    } else {
                        null
                    }
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
                    if (ddField.text.toInt() !in 1..31 ||
                        mmField.text.toInt() !in 1..12 ||
                        yyField.text.toInt() !in 2000..2100
                    ) {
                        feedbackLabel.text = "Невалидная дата"
                        feedbackLabel.textFill = Color.RED
                        feedbackLabel.isVisible = true
                        return@EventHandler
                    }
                    try {
                        conn.createStatement().executeUpdate(
                            """
                        MERGE INTO the_date KEY (ID) VALUES (1, '${yyField.text}-${mmField.text}-${ddField.text}');
                    """.trimIndent()
                        )
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

    private fun initDb() {
        conn.createStatement()
            .executeUpdate("CREATE TABLE IF NOT EXISTS the_date (id BIGINT PRIMARY KEY, the_date DATE)")
    }
}

fun main() {
    Application.launch(DateSaver::class.java)
}