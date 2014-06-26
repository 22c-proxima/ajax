package ru.proxima.commons.ajax;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
/**
 * Класс-обертка для HttpServletResponse (чтобы сторонним разработчикам было достаточно
 * подключить лишь данную библиотеку, и не подключать Java Web API)
 * @author Шомин Владимир, ЗАО ИВЦ Инсофт
 */
public final class AJAXResponse {
/**
 * Единственный доступный конструктор. Параметром является объект класса
 * HttpServletResponse для оборачивания
 * @param response HttpServletResponse
 */
	public AJAXResponse(HttpServletResponse response) {
		this.response = response;
	}
	private final HttpServletResponse response;
/**
 * Оборачивает метод void setContentType(String contentType)
 * @param contentType MIME-тип содержимого ответа
 */
	public void setContentType(String contentType) {
		response.setContentType(contentType);
	}
/**
 * Устанавливает заголовок ответа Content-Disposition на параметр
 * @param contentDisposition Расположение содержимого ответа (чаще всего именованный файл)
 */
	public void setContentDisposition(String contentDisposition) {
		response.setHeader("Content-Disposition", contentDisposition);
	}
/**
 * Оборачивает метод OutputStream getOutputStream()
 * @return OutputStream поток байт ответа
 * @throws IOException Обнаружена ошибка ввода-вывода
 */
	public OutputStream getOutputStream() throws IOException {
		return response.getOutputStream();
	}
/**
 * Оборачивает метод PrintWriter getWriter()
 * @return PrintWriter печатаемый поток вывода ответа
 * @throws IOException Обнаружена ошибка ввода-вывода
 */
	public PrintWriter getWriter() throws IOException {
		return response.getWriter();
	}
/**
 * Если вышеуказанных методов не хватает :)
 * @return Исходный объект ответа сервера
 */
	public HttpServletResponse response() {
		return response;
	}

}
