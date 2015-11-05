package org.fluxtream.connectors.google_spreadsheets;

import org.codehaus.jackson.annotate.JsonRawValue;
import org.fluxtream.core.OutsideTimeBoundariesException;
import org.fluxtream.core.TimeInterval;
import org.fluxtream.core.connectors.dao.JPAFacetDao;
import org.fluxtream.core.connectors.vos.AbstractInstantFacetVO;
import org.fluxtream.core.connectors.vos.VOHelper;
import org.fluxtream.core.domain.GuestSettings;

import java.util.Iterator;

import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by candide on 30/12/14.
 */
public class GoogleSpreadsheetRowFacetVO extends AbstractInstantFacetVO<GoogleSpreadsheetRowFacet> {

    String itemLabel;

    @JsonRawValue
    String cells;

    @Override
    protected void fromFacet(GoogleSpreadsheetRowFacet facet, TimeInterval timeInterval, GuestSettings settings) throws OutsideTimeBoundariesException {
        Iterator<GoogleSpreadsheetCellFacet> cells = facet.cells.iterator();
        JPAFacetDao jpaFacetDao = VOHelper.jpaFacetDao.get();
        GoogleSpreadsheetDocumentFacet documentFacet = (GoogleSpreadsheetDocumentFacet) jpaFacetDao.getFacetById(GoogleSpreadsheetDocumentFacet.class, facet.document_id);
        this.itemLabel = documentFacet.itemLabel;
        String[] columnNames = documentFacet.columnNames.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i=0;
        for (String columnName : columnNames) {
            columnName = columnName.trim();
            if (!cells.hasNext())
                continue;
            String cellValue = cells.next().value;
            System.out.println(columnName + documentFacet.dateTimeColumnName);
            if (columnName.equals(documentFacet.dateTimeColumnName.trim())) {
                this.start = handleTimeColumn(cellValue, documentFacet);
            } else {
                if (i>0) sb.append(", ");
                sb.append("{");
                sb.append("\"columnName\":\"" + columnName + "\",");
                sb.append("\"cellValue\":\"" + cellValue + "\"");
                sb.append("}");
            }
            i++;
        }
        sb.append("]");
        this.cells = sb.toString();
    }

    private long handleTimeColumn(String cellValue, GoogleSpreadsheetDocumentFacet documentFacet) {
        String timeFormat = documentFacet.dateTimeFormat;
        if (timeFormat.equals("epochSeconds"))
            return Long.valueOf(cellValue)*1000;
        else if (timeFormat.equals("epochMillis"))
            return Long.valueOf(cellValue);
        else {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(documentFacet.dateTimeFormat);
            if (documentFacet.timeZone!=null&&!documentFacet.timeZone.equals("none")) {
                dateTimeFormatter.withZone(DateTimeZone.forID(documentFacet.timeZone));
            }
            return dateTimeFormatter.parseMillis(cellValue);
        }
    }
}
