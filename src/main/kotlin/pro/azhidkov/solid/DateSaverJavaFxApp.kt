package pro.azhidkov.solid


import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage


/*
 * Ответственности:
 * * Запуск и инициализация приложения
 *
 * Стейкхолдеры:
 * * Разработчики
 *
 * Причины для изменения:
 * * изменение платформы приложения
 *
 * Секрет:
 * * Способ интеграции JavaFx в приложение
 */
class DateSaverJavaFxApp : Application() {

    override fun start(stage: Stage) {
        stage.scene = Scene(AppConfig.dateView, 640.0, 480.0)
        stage.show()
    }

}

fun main() {
    Application.launch(DateSaverJavaFxApp::class.java)
}