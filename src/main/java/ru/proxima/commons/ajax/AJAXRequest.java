package ru.proxima.commons.ajax;

import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Класс-обертка для HttpServletRequest (чтобы сторонним разработчикам было достаточно
 * подключить лишь данную библиотеку, и не подключать Java Web API)
 * @author Шомин Владимир, ЗАО ИВЦ Инсофт
 */
public final class AJAXRequest {
/**
 * Единственный доступный конструктор. Параметром является объект класса
 * HttpServletRequest для оборачивания
 * @param request HttpServletRequest
 */
	public AJAXRequest(HttpServletRequest request) {
		this.request = request;
	}
	private final HttpServletRequest request;
/**
 * Оборачивает метод String getParameter(String name)
 * @param name Имя параметра для получения
 * @return String Значение параметра
 */
	public String getParameter(String name) {
		return request.getParameter(name);
	}
/**
 * Получает объект из сессии данного запроса
 * @param name Имя параметра для получения
 * @return Object Значение параметра
 */
	public Object getSessionParam(String name) {
		return request.getSession().getAttribute(name);
	}
/**
 * Устанавливает объект сессии данного запроса
 * @param name Имя параметра для получения
 * @param param Значение параметра
 */
	public void setSessionParam(String name, Object param) {
		request.getSession().setAttribute(name, param);
	}
/**
 * Оборачивает метод Enumeration&lt;String&gt; getParameterNames()
 * @return Enumeration&lt;String&gt; Перечисление имён параметров запроса
 */
	public Enumeration<String> getParameterNames() {
		return request.getParameterNames();
	}
/**
 * Парсит запрос для использования возможностей библиотеки apache commons fileupload
 * @return List&lt;FileItem&gt; Список элементов запроса (файлы, параметры)
 * @throws FileUploadException Не удалось разобрать запрос на элементы
 */
	public List<FileItem> parseMultipartRequest() throws FileUploadException {
		return new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	}

}
