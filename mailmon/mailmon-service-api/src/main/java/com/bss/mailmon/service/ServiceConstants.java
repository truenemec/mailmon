package com.bss.mailmon.service;

import java.util.Locale;

public interface ServiceConstants {
	/** По-умолчанию все символы: ISO-8859-1 */
	public static final String ENCODING_HTTP = "ISO-8859-1";
	/** Default encoding for transfered data: UTF-8 */
	public static final String ENCODING_UTF8 = "UTF-8";
	/** Default encoding for crc32: UTF-8 */
	public static final String ENCODING_CRC32 = "UTF-8";
	/** Default encoding for BLOB data in database: UTF-8 */
	public static final String ENCODING_BLOB = "UTF-8";
	/** Язык по-умолчанию: ru. */
	public static final String LANGUAGE_DEFAULT = "ru";
	/** Страна по-умолчанию: RU. */
	public static final String COUNTRY_DEFAULT = "RU";
	/** Локаль по-умолчанию: ru_RU. */
	public static final Locale LOCALE_DEFAULT = new Locale(LANGUAGE_DEFAULT,
			COUNTRY_DEFAULT);
	/** Алгоритм хэширования. */
	public static final String ALGORITHM = "MD5";
	/**
	 * Формат даты в заголовке Date-Last-Modified.<br />
	 * <br />
	 * EEE, dd MMM yyyy HH:mm:ss z
	 */
	public static final String DATE_LAST_MODIFIED_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
	/**
	 * Локаль даты в заголовке Date-Last-Modified.<br />
	 * <br />
	 * Locale.US
	 */
	public static final Locale DATE_LAST_MODIFIED_LOCALE = Locale.US;
	/**
	 * Часовой пояс всех дат в HTTP.<br />
	 * <br />
	 * GMT
	 */
	public static final String HTTP_TIMEZONE = "GMT";
	/**
	 * Часовой пояс даты в заголовке Date-Last-Modified.<br />
	 * <br />
	 * GMT
	 */
	public static final String DATE_LAST_MODIFIED_TIMEZONE = "GMT";
	/** Часовой пояс по-умолчанию: Europe/Moscow. */
	public static final String TIMEZONE_DEFAULT_NAME = "Europe/Moscow";
	/** Часовой пояс по-умолчанию: GMT+4. */
	public static final String TIMEZONE_DEFAULT_CODE = "GMT+4";
	/** Единицы измерения данных при постраничном запросе: resources. */
	public static final String RANGE_UNITS_DEFAULT = "resources";
	/** Номер первой записи при постраничном запросе: 0. */
	public static final int RANGE_FIRST_UNIT_DEFAULT = 0;
	/** Максимальное кол-во записей: 500. */
	public static final int RANGE_MAX_UNIT_DEFAULT = 500;
	/** application/x-www-form-urlencoded;charset=utf-8 */
	public static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded;charset=utf-8";
	/** application/json;charset=utf-8 */
	public static final String APPLICATION_JSON_VALUE = "application/json;charset=utf-8";
	/** application/vnd.ms-excel */
	public static final String APPLICATION_EXCEL_VALUE = "application/vnd.ms-excel";
	/** text/html;charset=utf-8 */
	public static final String TEXT_HTML_VALUE = "text/html;charset=utf-8";

	public static final String USER_REMOTE_HOST_HEADER = "UserRemoteHost";
}
