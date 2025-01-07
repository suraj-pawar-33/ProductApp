package com.apps.digiple.npdapp.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.apps.digiple.npdapp.impl.AbstractDataProcessor;
import com.apps.digiple.npdapp.impl.BankDataProcessor;
import com.apps.digiple.npdapp.impl.ContactDataProcessor;
import com.apps.digiple.npdapp.impl.GeneralDataProcessor;
import com.apps.digiple.npdapp.impl.OrderDataProcessor;
import com.apps.digiple.npdapp.impl.ProductDataProcessor;
import com.apps.digiple.npdapp.impl.SubscrDataProcessor;
import com.apps.digiple.npdapp.models.FieldInfo;
import com.apps.digiple.npdapp.services.ServiceFactory;
import com.apps.digiple.npdapp.table.edit.FieldDataType;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ContentPanel extends StackPane {
	private static final Logger LOGGER = Logger.getLogger(ContentPanel.class);

	protected static final String REF_VIEW = "Refreshing View > ";
	private BankDataProcessor bankProcessor = null;
	private ContactDataProcessor contactProcessor = null;
	private ProductDataProcessor productProcessor = null;
	private OrderDataProcessor orderProcessor = null;
	private SubscrDataProcessor subscrProcessor = null;

	private GeneralDataProcessor generalProcessor;

	public ContentPanel() {
		// TODO: Add processors as argument
		// Setting size for the pane
		setMinSize(500, 500);
		setPrefSize(2000, 1000);
		setAlignment(Pos.CENTER);
		setBackground(CustFontColor.BEIGE.getBackGround());
	}

	private void showBanksPane() {

		if (bankProcessor == null) {
			this.bankProcessor = new BankDataProcessor();
			add(bankProcessor.getPanel());
			loadDataInTables(bankProcessor);
		} else {
			getChildren().clear();
			add(bankProcessor.getPanel());
		}
	}

	public void add(GridPane editPanel) {
		getChildren().add(editPanel);
	}

	public void clear() {
		getChildren().clear();
	}

	private void showOrdersPane() {
		if (orderProcessor == null) {
			this.orderProcessor = new OrderDataProcessor();
			add(orderProcessor.getPanel());
			loadDataInTables(orderProcessor);
		} else {
			getChildren().clear();
			add(orderProcessor.getPanel());
		}
	}

	private void showSubPane() {
		if (subscrProcessor == null) {
			this.subscrProcessor = new SubscrDataProcessor();
			add(subscrProcessor.getPanel());
			loadDataInTables(subscrProcessor);
		} else {
			getChildren().clear();
			add(subscrProcessor.getPanel());
		}
	}

	private void showContactsPane() {
		if (contactProcessor == null) {
			this.contactProcessor = new ContactDataProcessor();
			add(contactProcessor.getPanel());
			loadDataInTables(contactProcessor);
		} else {
			getChildren().clear();
			add(contactProcessor.getPanel());
		}
	}

	public void showPane(String paneName) {
		if (paneName.equals("order")) {
			showOrdersPane();
		} else if (paneName.equals("bank")) {
			showBanksPane();
		} else if (paneName.equals("sub")) {
			showSubPane();
		} else if (paneName.equals("contact")) {
			showContactsPane();
		} else if (paneName.equals("product")) {
			showProductsPane();
		} else if (paneName.equals("general")) {
			showGeneralPane();
		}

	}

	private void showGeneralPane() {
		if (generalProcessor == null) {
			Map<String, String> columnNames = new HashMap<String, String>();
			columnNames.put("Product Name", "product_name");
			columnNames.put("Short Name", "short_name");
			columnNames.put("Product Type", "pro_type_key");
			columnNames.put("Cost", "cost");

			FieldInfo[] fieldInfoList = new FieldInfo[4];
			fieldInfoList[0] = (new FieldInfo("Product Name", "product_name", "stringProperty01", FieldDataType.STRING_FIELD));
			fieldInfoList[1] = (new FieldInfo("Short Name", "short_name", "stringProperty02", FieldDataType.STRING_FIELD));
			fieldInfoList[2] = (new FieldInfo("Product Type", "pro_type_key", "stringProperty03", FieldDataType.STRING_FIELD));
			fieldInfoList[3] = (new FieldInfo("Cost", "cost", "stringProperty04", FieldDataType.STRING_FIELD));

			Map<String, FieldDataType> fieldDef = new HashMap<>();
			fieldDef.put("Product Name", FieldDataType.STRING_FIELD);
			fieldDef.put("Short Name", FieldDataType.STRING_FIELD);
			fieldDef.put("Product Type", FieldDataType.LONG_FIELD);
			fieldDef.put("Cost", FieldDataType.LONG_FIELD);

			String dbTableName = "product";
			this.generalProcessor = ServiceFactory.getGenProcessorInstance();
			add(generalProcessor.getPanel());
			// loadDataInTables(generalProcessor);
		} else {
			getChildren().clear();
			add(generalProcessor.getPanel());
		}
	}

	private void showProductsPane() {
		if (productProcessor == null) {
			this.productProcessor = new ProductDataProcessor();
			add(productProcessor.getPanel());
			loadDataInTables(productProcessor);
		} else {
			getChildren().clear();
			add(productProcessor.getPanel());
		}
	}

	private void loadDataInTables(AbstractDataProcessor abstractDataProcessor) {

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				LOGGER.info(REF_VIEW + abstractDataProcessor.getClass().getSimpleName());
				try {
					abstractDataProcessor.refreshView();
				} catch (Exception e) {
					LOGGER.error(e.getLocalizedMessage());
					LOGGER.debug(e);
				}
			}
		}, REF_VIEW + abstractDataProcessor.getClass());
		t.start();

	}

}
