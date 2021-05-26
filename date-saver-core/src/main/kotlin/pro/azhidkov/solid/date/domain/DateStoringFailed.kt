package pro.azhidkov.solid.date.domain


class DateStoringFailed(
    message: String?,
    cause: Throwable?,
) : Exception(message, cause)