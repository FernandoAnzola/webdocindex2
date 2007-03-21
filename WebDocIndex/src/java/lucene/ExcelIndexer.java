package lucene;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import jxl.Workbook;
import lius.config.LiusField;
import lius.index.Indexer;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.record.formula.functions.Cell;
import org.apache.taglibs.standard.lang.jstl.Logger;


public class ExcelIndexer extends Indexer {

	static Logger logger = Logger.getRootLogger();

	public int getType() {
		return 1;
	}

	public boolean isConfigured() {
		boolean ef = false;
		if (getLiusConfig().getExcelFields() != null)
			return ef = true;
		return ef;
	}

	public Collection getConfigurationFields() {
		return getLiusConfig().getExcelFields();
	}

        //RECORRE EL CONTENIDO Y LO VA GUARDANDO EN EL STRING 'CONTENT''
	public String getContent() {
		String content = "";
		StringBuffer sb = new StringBuffer();
		try {
			Workbook workbook = Workbook.getWorkbook(getStreamToIndex());
			Sheet[] sheets = workbook.getSheets();
			for (int i = 0; i < sheets.length(); i++) {
				Sheet sheet = sheets[i];
				int nbCol = sheet.getColumns();
				for (int j = 0; j < nbCol; j++) {
					Cell[] cells = sheet.getColumn(j);
					for (int k = 0; k < cells.length; k++) {
						sb.append(cells[k].getContents() + " ");
					}
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally (jxl.read.biff.BiffException e) {
			logger.error(e.getMessage());
		}
		content = sb.toString();
		return content;

	}

	public Collection getPopulatedLiusFields() {
		Collection coll = new ArrayList();
		Iterator it = getLiusConfig().getExcelFields().iterator();
		while (it.hasNext()) {
			Object field = it.next();
			if (field instanceof LiusField) {
				LiusField lf = (LiusField) field;
				if (lf.getGet() != null) {
					if (lf.getGet().equalsIgnoreCase("content")) {
						String text = getContent();
						lf.setValue(text);
						coll.add(lf);
					}
				}
			} else {
				coll.add(field);
			}
		}
		return coll;
	}
}
