package ru.proxima.commons.ajax;

import java.io.PrintWriter;
import ru.proxima.commons.json.JSONObject;
/**
 * Интерфейс классов-обработчиков AJAX-запросов
 * @author Шомин Владимир, ЗАО ИВЦ Инсофт
 */
public interface AJAXHandler {
/**
 * Варианты AJAX-обработчиков, влияющие на выбор метода для обработки AJAX-запроса
 */
	enum AJAXHandlerType {
/**
 * Обработка AJAX-запроса на самом низком уровне - используя обёртки классов
 * HttpServletRequest и HttpServletResponse
 */
		RAW,
/**
 * Обработка AJAX-запроса на низком уровне - используя обёртку класса
 * HttpServletRequest, печатая ответ в {@link PrintWriter}
 */
		TEXT,
/**
 * Обработка AJAX-запроса на низком уровне - используя обёртку класса
 * HttpServletResponse, получая параметры в виде JSONObject
 */
		JSON_INPUT_RAW,
/**
 * Обработка AJAX-запроса на среднем уровне абстракции - печатая ответ в {@link PrintWriter},
 * получая параметры в виде JSONObject
 */
		JSON_INPUT,
/**
 * Обработка AJAX-запроса на среднем уровне абстракции - ответ возвращается как {@link Object}
 * (чаще всего JSONObject), параметры передаются как класс-обёртка HttpServletRequest
 */
		JSON_OUTPUT,
/**
 * Обработка AJAX-запроса на высшем уровне абстракции - ответ возвращается как {@link Object}
 * (чаще всего JSONObject), параметры передаются как JSONObject
 */
		JSON_IN_OUT
	}
/**
 * Установка различных параметров обработчика
 * @param name Имя параметра
 * @param value Значение параметра
 */
	void setParam(String name, String value);
/**
 * Получение типа обработчика, что определит который метод будет вызван для исполнения запроса.
 * Это повлияет и на конвенцию для передаваемых параметров и возвращаемых значений
 * @return AJAXHandlerType тип AJAX-обработчика
 */
	AJAXHandlerType getHandlerType();
/**
 * Получение MIME-типа возвращаемых обработчиком данных для заголовка ответа Content-Type
 * @return String MIME-тип
 */
	String getContentType();
/**
 * Получение имени модуля группировки общих по какому-либо признаку обработчиков.
 * Используется при вызове модуля AJAX?[module=module_name&amp;]action=xxx,
 * если не указан - подразумевается модуль core
 * @return String имя модуля
 */
	String getModule();
/**
 * Получение имени обработчика, используется имя класса обработчика.
 * Используется при вызове модуля AJAX?module=xxx&amp;action=action_name
 * @return String имя обработчика
 */
	String getAction();
/**
 * Возвращает список доменов, с которых разрешено делать Cross Domain Request.
 * Для запрета - возвращайте null
 * @return Домены для установки заголовка Access-Control-Allow-Origin
 */
	String getCrossDomains();
/**
 * Обработка AJAX-запроса на самом низком уровне - используя обёртки классов
 * HttpServletRequest и HttpServletResponse
 * @param request Класс-обёртка HttpServletRequest
 * @param response Класс-обёртка HttpServletResponse
 * @throws AJAXExecuteException Работа AJAX-обработчика завершилась со сбоем
 */
	void process(AJAXRequest request, AJAXResponse response) throws AJAXExecuteException;
/**
 * Обработка AJAX-запроса на низком уровне - используя обёртку класса
 * HttpServletRequest, печатая ответ в {@link PrintWriter}
 * @param request Класс-обёртка HttpServletRequest
 * @param out Печатный поток для получения данных
 * @throws AJAXExecuteException Работа AJAX-обработчика завершилась со сбоем
 */
	void process(AJAXRequest request, PrintWriter out) throws AJAXExecuteException;
/**
 * Обработка AJAX-запроса на низком уровне - используя обёртку класса
 * HttpServletResponse, получая параметры в виде JSONObject
 * @param params JSONObject, содержащий все входные параметры обработчика
 * @param response Класс-обёртка HttpServletResponse
 * @throws AJAXExecuteException Работа AJAX-обработчика завершилась со сбоем
 */
	void process(JSONObject params, AJAXResponse response) throws AJAXExecuteException;
/**
 * Обработка AJAX-запроса на среднем уровне абстракции - печатая ответ в {@link PrintWriter},
 * получая параметры в виде JSONObject
 * @param params JSONObject, содержащий все входные параметры обработчика
 * @param out Печатный поток для получения данных
 * @throws AJAXExecuteException Работа AJAX-обработчика завершилась со сбоем
 */
	void process(JSONObject params, PrintWriter out) throws AJAXExecuteException;
/**
 * Обработка AJAX-запроса на среднем уровне абстракции - ответ возвращается как {@link Object}
 * (чаще всего JSONObject), параметры передаются как класс-обёртка HttpServletRequest
 * @param request Класс-обёртка HttpServletRequest
 * @return Object Объект печатаемый в выходной поток ответа
 * @throws AJAXExecuteException Работа AJAX-обработчика завершилась со сбоем
 */
	Object process(AJAXRequest request) throws AJAXExecuteException;
/**
 * Обработка AJAX-запроса на высшем уровне абстракции - ответ возвращается как {@link Object}
 * (чаще всего JSONObject), параметры передаются как JSONObject
 * @param params JSONObject, содержащий все входные параметры обработчика
 * @return Object Объект печатаемый в выходной поток ответа
 * @throws AJAXExecuteException Работа AJAX-обработчика завершилась со сбоем
 */
	Object process(JSONObject params) throws AJAXExecuteException;
/**
 * Вызывается при выгрузке сервлета из памяти, позволяет выполнить какие-либо действия по освобождению ресурсов
 */
	void destroy();

}
