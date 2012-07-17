package ru.proxima.commons.ajax;

import java.io.IOException;
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

	public CommonAJAXHandler() {
		params = new HashMap<String, String>();
	}
	private final HashMap<String, String> params;
/**
 * Установка различных параметров обработчика
 * @param name Имя параметра
 * @param value Значение параметра
 */
	public final void setParam(String name, String value) {
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
	public final String getAction() {
		return this.getClass().getSimpleName();
	}
/**
 * Получение имени модуля группировки для данного обработчика (берётся из ресурсов).
 * Запрещён к переопределению
 * @return String имя модуля обработчика
 */
	public final String getModule() {
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
	private static Logger ajaxLogger = LoggerFactory.getLogger("portal.ajax");
	protected static Logger getLogger() {
		return ajaxLogger;
	}
/**
 * Возвращает стандартный JSONObject с сообщением об ошибке
 * @return JSONObject JSONObject с сообщением об ошибке
 */
	public static JSONObject getErrorJSON(String message) {
		return new JSONObject().put("error", new JSONObject().put("message", message));
	}
/**
 * Возвращает имя модуля обработчиков по умолчанию
 * @return	String	имя по умолчанию
 */
	public static String getDefaultModuleName() {
		return "core";
	}

}
