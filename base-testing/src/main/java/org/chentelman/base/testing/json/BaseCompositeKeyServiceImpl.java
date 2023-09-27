package org.chentelman.base.testing.json;

import java.util.Base64;

import org.chentelman.base.testing.service.BaseStorageImpl;

import io.cucumber.datatable.DataTable;

public class BaseCompositeKeyServiceImpl extends BaseStorageImpl<String> implements BaseCompositeKeyService {
	private static final long serialVersionUID = 1L;

	private final BaseJsonService jsonService;

	public BaseCompositeKeyServiceImpl (BaseJsonService jsonService) {
		this.jsonService = jsonService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String generateBase64 (String data) {
		return new String(Base64.getDecoder().decode(data));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String generatePaddedBase64 (String data) {
		switch (data.length() % 3) {
		case 1: data = data + "  "; break;
		case 2: data = data + " ";  break;
		default:
			// no padding is needed
		}
		return generateBase64 (data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String build (DataTable data) {
		return generatePaddedBase64(jsonService.generateJsonObject(data).getAsString());
	}

}



