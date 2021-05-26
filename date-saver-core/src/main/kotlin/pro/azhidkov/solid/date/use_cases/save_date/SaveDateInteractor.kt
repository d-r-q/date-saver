package pro.azhidkov.solid.date.use_cases.save_date

import pro.azhidkov.solid.date.domain.Date
import pro.azhidkov.solid.date.domain.DateStorage
import pro.azhidkov.solid.date.domain.DateStoringFailed
import pro.azhidkov.solid.date.domain.DateValidator


/*
 * Ответственности:
 * * Реализация пользовательского сценария сохранения даты
 *
 * Стейкхолдеры:
 * * Пользователь/продакт оунер (в зависимости от процесса)
 *
 * Причины для изменения:
 * * Изменение пользовательского сценария сохренения даты
 *
 * Секрет:
 * * Пользовательский сценарий
 */
class SaveDateInteractor(private val dateStorage: DateStorage) {

    private val dateValidator = DateValidator()

    fun saveDate(saveDateRequest: SaveDateRequest): SaveDateResult {
        val validationResult = dateValidator.validate(saveDateRequest.day, saveDateRequest.month, saveDateRequest.year)
        if (validationResult != null) {
            return Error(validationResult)
        }

        return try {
            dateStorage.saveDate(Date(saveDateRequest.day, saveDateRequest.month, saveDateRequest.year))
            Ok()
        } catch (e: DateStoringFailed) {
            e.printStackTrace()
            Error(e)
        }
    }

}