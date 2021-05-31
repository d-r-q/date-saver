package pro.azhidkov.solid.date.view

import pro.azhidkov.solid.date.domain.Date


class DatePresenter(
    private val dateView: DateView
) {

    fun showDate(date: Date?) {
        dateView.day = date?.day?.toString() ?: "дд"
        dateView.month = date?.month?.toString() ?: "мм"
        dateView.year = date?.year?.toString() ?: "гг"
    }

}