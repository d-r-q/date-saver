package pro.azhidkov.solid.date.view


interface DateView {
    var day: String
    var month: String
    var year: String

    fun showSaveFeedback(feedbackViewModel: FeedbackViewModel)
}