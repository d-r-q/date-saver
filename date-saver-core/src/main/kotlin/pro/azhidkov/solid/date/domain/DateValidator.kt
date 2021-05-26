package pro.azhidkov.solid.date.domain


/*
 * Ответственности:
 * * Проверка образуют ли три целых числа корректную дату
 *
 * Стейкхолдеры:
 * * Пользователь/продакт оунер (в зависимости от процесса)
 *
 * Причины для изменения:
 * * Изменение бизнес правила "требования к корректной дате"
 * * Изменение структуры даты
 *
 * Секрет:
 * * Требования к формату корректной даты
 */
class DateValidator {

    fun validate(day: Int, month: Int, year: Int): ValidationFailed? {

        if (day !in 1..31 || month !in 1..12 || year !in 2000..2100) {
            return ValidationFailed()
        }

        return null
    }

}

class ValidationFailed : Exception()

