package ru.proxima.commons.ajax.impl;

import java.io.PrintWriter;
import ru.proxima.commons.ajax.AJAXExecuteException;
import ru.proxima.commons.ajax.AJAXRequest;
import ru.proxima.commons.ajax.AJAXResponse;
import ru.proxima.commons.ajax.CommonAJAXHandler;
import ru.proxima.commons.json.JSONObject;
/**
 * Умолчания реализации классов-обработчиков AJAX-запросов для типа RAW
 * @author Шомин Владимир, ЗАО ИВЦ Инсофт
 */
public abstract class AJAXHandlerRaw extends CommonAJAXHandler {
/**
 * Получение типа обработчика, что определит который метод будет вызван для исполнения запроса.
 * Это повлияет и на конвенцию для передаваемых параметров и возвращаемых значений
 * @return AJAXHandlerType AJAXHandlerType.RAW
 */
	public final AJAXHandlerType getHandlerType() {
		return AJAXHandlerType.RAW;
	}
/**
 * Получение MIME-типа возвращаемых обработчиком данных для заголовка ответа Content-Type.
 * Т.к. MIME-тип заранее не известен данный метод не имеет смысл и вызовет исключение.
 * Content-Type будет установлен в ходе вызова метода process
 * @return String Метод вызовет исключение
 * @throws UnsupportedOperationException Вызов не имеет смысла
 */
	public final String getContentType() {
		throw new UnsupportedOperationException("Для данного обработчика эта операция не имеет смысла");
	}
/**
 * Обработка AJAX-запроса на самом низком уровне - используя обёртки классов
 * HttpServletRequest и HttpServletResponse
 * @param request Класс-обёртка HttpServletRequest
 * @param response Класс-обёртка HttpServletResponse
 * @throws AJAXExecuteException Работа AJAX-обработчика завершилась со сбоем
 */
	public abstract void process(AJAXRequest request, AJAXResponse response) throws AJAXExecuteException;
/**
 * Обработка AJAX-запроса на низком уровне - используя обёртку класса
 * HttpServletRequest, печатая ответ в {@link PrintWriter}.
 * @param request Класс-обёртка HttpServletRequest
 * @param out Печатный поток для получения данных
 * @throws UnsupportedOperationException В данном подклассе и его потомках - не поддерживается
 */
	public final void process(AJAXRequest request, PrintWriter out)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
			"Данный обработчик не может работать с таким типом запроса");
	}
/**
 * Обработка AJAX-запроса на низком уровне - используя обёртку класса
 * HttpServletResponse, получая параметры в виде {@link JSONObject}.
 * @param params JSONObject, содержащий все входные параметры обработчика
 * @param response Класс-обёртка HttpServletResponse
 * @throws UnsupportedOperationException В данном подклассе и его потомках - не поддерживается
 */
	public final void process(JSONObject params, AJAXResponse response)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
			"Данный обработчик не может работать с таким типом запроса");
	}
/**
 * Обработка AJAX-запроса на низком уровне - используя обёртку класса
 * {@link PrintWriter}, получая параметры в виде JSONObject.
 * Абстрактный, поэтому требует реализации подклассом
 * @param	params	JSONObject, содержащий все входные параметры обработчика
 * @param	out		Печатный поток для получения данных
 * @throws UnsupportedOperationException В данном подклассе и его потомках - не поддерживается
 */
	public final void process(JSONObject params, PrintWriter out)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
			"Данный обработчик не может работать с таким типом запроса");
	}
/**
 * Обработка AJAX-запроса на среднем уровне абстракции - ответ возвращается как {@link Object}
 * (чаще всего {@link JSONObject}), параметры передаются как класс-обёртка HttpServletRequest.
 * @param request Класс-обёртка HttpServletRequest
 * @return Object Объект печатаемый в выходной поток ответа
 * @throws UnsupportedOperationException В данном подклассе и его потомках - не поддерживается
 */
	public final Object process(AJAXRequest request)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
			"Данный обработчик не может работать с таким типом запроса");
	}
/**
 * Обработка AJAX-запроса на высшем уровне абстракции - ответ возвращается как {@link Object}
 * (чаще всего {@link JSONObject}), параметры передаются как {@link JSONObject}.
 * @param params JSONObject, содержащий все входные параметры обработчика
 * @return Object Объект печатаемый в выходной поток ответа
 * @throws UnsupportedOperationException В данном подклассе и его потомках - не поддерживается
 */
	public final Object process(JSONObject params)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
			"Данный обработчик не может работать с таким типом запроса");
	}

}
