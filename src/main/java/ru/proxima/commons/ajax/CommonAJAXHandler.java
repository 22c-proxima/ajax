package ru.proxima.commons.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.proxima.commons.json.JSONObject;
/**
 * Умолчания реализации классов-обработчиков AJAX-запросов
 * @author Шомин Владимир, ЗАО ИВЦ Инсофт
 */
public abstract class CommonAJAXHandler implements AJAXHandler {

	private final HashMap<String, String> params;

	public CommonAJAXHandler() {
		params = new HashMap<>();
	}
/**
 * Установка различных параметров обработчика
 * @param name Имя параметра
 * @param value Значение параметра
 */
	@Override public final void setParam(String name, String value) {
		params.put(name, value);
	}
/**
 * Получение параметра в обработчике
 * @param name Имя параметра
 * @return String значение
 */
	protected final String getParam(String name) {
		return params.get(name);
	}
/**
 * Получение имени обработчика (по сути - имя класса обработчика). Запрещён к переопределению
 * @return String имя обработчика
 */
	@Override public final String getAction() {
		return this.getClass().getSimpleName();
	}
/**
 * Получение имени модуля группировки для данного обработчика (берётся из ресурсов).
 * Запрещён к переопределению
 * @return String имя модуля обработчика
 */
	@Override public final String getModule() {
		String moduleName;
		try {
			Properties properties = new Properties();
			Class cls = this.getClass();
			String clsRes = "/" + cls.getName().replaceAll("\\.", "/") + ".class";
			String modRes = cls.getResource(clsRes).toString().replace(
				clsRes, "/META-INF/AJAX.properties");

			properties.load(new URL(modRes).openStream());
			moduleName = properties.getProperty("moduleName", getDefaultModuleName());
		} catch (IOException ex) {
			getLogger().debug("Не удалось получить имя AJAX-модуля", ex);
			moduleName = getDefaultModuleName();
		}
		return moduleName;
	}
/**
 * Возвращает логгер для AJAX событий
 * @return Logger Логгер AJAX событий
 */
	private static final Logger ajaxLogger = LoggerFactory.getLogger("portal.ajax");
	protected static Logger getLogger() {
		return ajaxLogger;
	}
/**
 * Возвращает стандартный JSONObject с сообщением об ошибке
 * @param message Сообщение об ошибке
 * @return JSONObject JSONObject с сообщением об ошибке
 */
	public static JSONObject getErrorJSON(String message) {
		return new JSONObject().put("error", new JSONObject().put("message", message));
	}
/**
 * Возвращает стандартный JSONObject с сообщением об ошибке
 * и пишет отладочное сообщение в логи с исключением
 * @param message Сообщение об ошибке
 * @param th Приведшее к ошибке исключение
 * @return JSONObject JSONObject с сообщением об ошибке
 */
	public static JSONObject getErrorJSON(String message, Throwable th) {
		ajaxLogger.error(message, th);
		return getErrorJSON(message);
	}
/**
 * Возвращает имя модуля обработчиков по умолчанию
 * @return	String	имя по умолчанию
 */
	public static String getDefaultModuleName() {
		return "core";
	}
/**
 * Обычно освобождение ресурсов сверх GC не требуется
 */
	@Override public void destroy(){}

	@Override
	public AJAXHandlerType getHandlerType() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getContentType() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getCrossDomains() {
		return null;
	}

	@Override
	public void process(AJAXRequest request, AJAXResponse response) throws AJAXExecuteException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void process(AJAXRequest request, PrintWriter out) throws AJAXExecuteException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void process(JSONObject params, AJAXResponse response) throws AJAXExecuteException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void process(JSONObject params, PrintWriter out) throws AJAXExecuteException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Object process(AJAXRequest request) throws AJAXExecuteException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Object process(JSONObject params) throws AJAXExecuteException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
