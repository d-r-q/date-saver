package pro.azhidkov.solid.event_bus


// предельно упрощённая реализация
interface EventListener<T> {
    fun onSaveClicked(saveDateClicked: T)
}

/*
 * Ответственности:
 * * Оповещение слушателей о событиях в системе
 *
 * Стейкхолдеры:
 * * Архитектор
 *
 * Причины для изменения:
 * * Появление новых требований (поддержка асинхронных слушателей, поддержка транзакций)
 *
 * Секрет:
 * * Способ хранения слушателей
 * * Способ выбора слушателей для оповещения
 */
class EventBus<T> {

    private lateinit var listener: EventListener<T>

    // Нарушал бы LSP, если бы был интерфейс
    // у него наврняка был бы явный или неявный контракт, что слушатель получает все события, опубликованные после его добавления
    // что в данном случае будет нарушено для первого слушателя, после добавления второго
    fun addListener(listener: EventListener<T>) {
        this.listener = listener
    }

    fun publishEvent(saveDateClicked: T) {
        listener.onSaveClicked(saveDateClicked)
    }
}