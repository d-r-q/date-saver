package pro.azhidkov.solid.date.view.init_date

import pro.azhidkov.solid.date.domain.Date
import pro.azhidkov.solid.date.view.DatePresenter


// Объединил контрллер с интерактором, потому сценарий тривиальный - вызов loadDate
class InitDateController(
    private val loadDate: () -> Date?,
    private val datePresenter: DatePresenter
) {

    fun initDate() {
        datePresenter.showDate(loadDate())
    }

}