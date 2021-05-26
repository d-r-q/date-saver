package pro.azhidkov.solid.date.domain


interface DateStorage {

    // Выброс DateStoringFailed - это контракт
    // Сигнализация об ошибке сохранения каким-то другим исключением - нарушение контракта
    @Throws(DateStoringFailed::class)
    fun loadDate(): Date?

    @Throws(DateStoringFailed::class)
    fun saveDate(date: Date)

}