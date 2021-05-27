package pro.azhidkov.solid


import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import pro.azhidkov.solid.date.view.DateView


class DateSaver : Application() {

    override fun start(stage: Stage) {
        val pane = initUi()
        stage.scene = Scene(pane, 640.0, 480.0)
        stage.show()
    }

    private fun initUi(): VBox {
        return DateView()
    }
}

fun main() {
    Application.launch(DateSaver::class.java)
}