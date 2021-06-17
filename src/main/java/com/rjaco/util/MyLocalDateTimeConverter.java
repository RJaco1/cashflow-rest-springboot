package com.rjaco.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;

public class MyLocalDateTimeConverter implements AttributeConverter<java.time.LocalDateTime, java.sql.Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		return attribute == null ? null : java.sql.Timestamp.valueOf(attribute);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		return dbData == null ? null : dbData.toLocalDateTime();
	}

}
