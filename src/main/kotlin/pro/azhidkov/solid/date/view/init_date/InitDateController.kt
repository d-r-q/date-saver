package pro.azhidkov.solid.date.view.init_date

import pro.azhidkov.solid.date.domain.Date
import pro.azhidkov.solid.date.view.DateView


// Объединил контрллер с интерактором, потому сценарий тривиальный - вызов loadDate
class InitDateController(
    private val loadDate: () -> Date?,
    private val dateView: DateView
) {

    fun initDate() {
        dateView.showDate(loadDate())
    }

}